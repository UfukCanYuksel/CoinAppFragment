package com.example.coinapp.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.coinapp.R
import com.example.coinapp.databinding.CoinDetailsBinding
import com.example.coinapp.databinding.FragmentCoinDetailsBinding


class CoinDetails : Fragment() {

    private var _bindig : FragmentCoinDetailsBinding? = null
    private val binding get() = _bindig!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _bindig = FragmentCoinDetailsBinding.inflate(layoutInflater , container , false)
        val view = binding.root
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        arguments?.let {
            val getCurrency = CoinDetailsArgs.fromBundle(it).currency
            val getPrice = CoinDetailsArgs.fromBundle(it).price
            binding.tvName.text = getCurrency
            binding.tvPrice.text = getPrice
        }

    }
}