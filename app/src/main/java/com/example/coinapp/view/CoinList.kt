package com.example.coinapp.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.coinapp.R
import com.example.coinapp.adapter.CoinAdapter
import com.example.coinapp.databinding.FragmentCoinListBinding
import com.example.coinapp.model.Coin
import com.example.coinapp.service.CoinAPI
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory


class CoinList : Fragment() {

    private var _binding :FragmentCoinListBinding? = null
    private val binding get() = _binding!!
    private val BASE_URL="https://raw.githubusercontent.com/"
    private var compositeDisposable: CompositeDisposable? = null
    private var coinLists : ArrayList<Coin>? = null
    private var coinAdapter = CoinAdapter(arrayListOf())



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        compositeDisposable = CompositeDisposable()



    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentCoinListBinding.inflate(layoutInflater , container , false)
        val view = binding.root
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        val layoutManager : RecyclerView.LayoutManager = LinearLayoutManager(requireContext())
        binding.recyclerView.layoutManager = layoutManager
        println("aa")
        loadData()
        println("xx")
    }

    private fun loadData(){
        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build().create(CoinAPI::class.java)

        compositeDisposable?.add(retrofit.getData()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(this::handleResponse))
    }

    private fun handleResponse(coinList : List<Coin>){
        coinLists = ArrayList(coinList)

        coinLists?.let {
            coinAdapter = CoinAdapter(it)
            binding.recyclerView.adapter = coinAdapter
        }

    }

    override fun onDestroy() {
        super.onDestroy()

        compositeDisposable?.clear()
    }


}