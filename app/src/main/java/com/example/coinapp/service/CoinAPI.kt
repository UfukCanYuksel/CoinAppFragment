package com.example.coinapp.service

import com.example.coinapp.model.Coin
import io.reactivex.Observable
import retrofit2.http.GET

interface CoinAPI {

    @GET("atilsamancioglu/K21-JSONDataSet/master/crypto.json")
    fun getData() : Observable<List<Coin>> 
}