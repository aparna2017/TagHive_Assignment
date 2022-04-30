package com.projects.myapplication.api

import com.projects.myapplication.pojo.CrypCurr
import com.projects.myapplication.pojo.TradeObj
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.*

interface RetrofitInterface {

    @GET("sapi/v1/tickers/24hr")
    fun getCurrencyList(): Call<List<CrypCurr>>

    @GET("sapi/v1/tickers/24hr")
    fun getCurrency(@Query("symbol") symbol: String): Call<List<CrypCurr>>

    @FormUrlEncoded
    @POST("sapi/v1/order/buy")
    fun buyCurrency(
        @Field("symbol") symbol: String,
        @Field("side") side: String,
        @Field("type") type: String,
        @Field("quantity") quantity: Int,
        @Field("price") price: Double,
        @Field("recvWindow") recvWindow: Int,
        @Field("apiKey") apiKey: String,
        @Field("secretKey") secretKey: String,
        @Field("timestamp") timestamp: Long
    ): Call<TradeObj>

    @FormUrlEncoded
    @POST("sapi/v1/order/sell")
    fun sellCurrency(
        @Field("symbol") symbol: String,
        @Field("side") side: String,
        @Field("type") type: String,
        @Field("quantity") quantity: Int,
        @Field("price") price: Double,
        @Field("recvWindow") recvWindow: Int,
        @Header("apiKey") apiKey: String,
        @Header("secretKey") secretKey: String,
        @Field("timestamp") timestamp: Long
    ): Call<TradeObj>

    companion object {
        var retrofitInterface: RetrofitInterface? = null

        fun getInstance(): RetrofitInterface {
            if (retrofitInterface == null) {
                val retrofit = Retrofit.Builder()
                    .baseUrl("https://api.wazirx.com/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
                retrofitInterface = retrofit.create(RetrofitInterface::class.java)
            }
            return retrofitInterface!!
        }
    }
}