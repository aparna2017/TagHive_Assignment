package com.projects.myapplication.api

import com.projects.myapplication.pojo.CrypCurr

class CurrencyRepo constructor(private val retrofitInterface: RetrofitInterface) {

    private val apiKey = "vmPUZE6mv9SD5VNHk4HlWFsOr6aKE2zvsw0MuIgwCIPy6utIco14y7Ju91duEh8A"
    private val secretKey = "NhqPtmdSJYdKjVHjA7PZj4Mge3R5YNiP1e3UZjInClVN65XAbvqqM6A7H5fATj0j"

    fun getAllCurrencies() = retrofitInterface.getCurrencyList()

    fun getCurrency(symbol: String) = retrofitInterface.getCurrency(symbol)

    fun buyCurrency(symbol: String) = retrofitInterface.buyCurrency(
        symbol, "buy", "limit", 1, 0.1,
        5000, apiKey, secretKey, System.currentTimeMillis()
    )

    fun sellCurrency(symbol: String) = retrofitInterface.sellCurrency(
        symbol, "sell", "limit", 1, 0.1,
        5000, apiKey, secretKey, System.currentTimeMillis()
    )
}