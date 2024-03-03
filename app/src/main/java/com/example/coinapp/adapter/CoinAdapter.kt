package com.example.coinapp.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.example.coinapp.databinding.CoinDetailsBinding
import com.example.coinapp.model.Coin
import com.example.coinapp.view.CoinListDirections

class CoinAdapter(private var coinList : ArrayList<Coin>) :RecyclerView.Adapter<CoinAdapter.CoinVH>() {
    class CoinVH(val binding: CoinDetailsBinding) : RecyclerView.ViewHolder(binding.root) {
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CoinVH {
        val binding = CoinDetailsBinding.inflate(LayoutInflater.from(parent.context) , parent , false)
        return CoinVH(binding)
    }
    override fun getItemCount(): Int {
        return coinList.size
    }
    override fun onBindViewHolder(holder: CoinVH, position: Int) {
        holder.binding.textName.text = coinList[position].currency
        holder.binding.textPrice.text = coinList[position].price
        holder.itemView.setOnClickListener {
            val action = CoinListDirections.actionCoinListToCoinDetails(coinList[position].currency , coinList[position].price)
            Navigation.findNavController(it).navigate(action)
        }
    }
}