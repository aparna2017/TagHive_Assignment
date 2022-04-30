package com.projects.myapplication.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.projects.myapplication.api.CurrencyRepo
import com.projects.myapplication.pojo.CrypCurr
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class CurrencyViewModel constructor(private val repository: CurrencyRepo) : ViewModel() {

    val currencyList = MutableLiveData<List<CrypCurr>>()
    val currencyObj = MutableLiveData<CrypCurr>()
    val errorMessage = MutableLiveData<String>()

    fun getCurrency() {
        val response = repository.getAllCurrencies()
        if (response != null) {
            response.enqueue(object : Callback<List<CrypCurr>> {
                override fun onResponse(
                    call: Call<List<CrypCurr>>,
                    response: Response<List<CrypCurr>>
                ) {
                    currencyList.postValue(response.body())
                }

                override fun onFailure(call: Call<List<CrypCurr>>, t: Throwable) {
                    errorMessage.postValue(t.message)
                }
            })
        }
    }

    fun getCurrency(symbol: String) {
        val response = repository.getCurrency(symbol)
        if (response != null) {
            response.enqueue(object : Callback<List<CrypCurr>> {
                override fun onResponse(
                    call: Call<List<CrypCurr>>,
                    response: Response<List<CrypCurr>>
                ) {
                    for (currencyVal in response.body()) {
                        if (currencyVal.symbol.equals(symbol)) {
                            currencyObj.postValue(currencyVal)
                        }
                    }
                }

                override fun onFailure(call: Call<List<CrypCurr>>, t: Throwable) {
                    errorMessage.postValue(t.message)
                }
            })
        }
    }

}