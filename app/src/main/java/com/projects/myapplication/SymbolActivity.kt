package com.projects.myapplication

import android.os.Build
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.projects.myapplication.api.CurrencyRepo
import com.projects.myapplication.api.RetrofitInterface
import com.projects.myapplication.databinding.ActivitySymbolBinding
import com.projects.myapplication.pojo.CrypCurr
import com.projects.myapplication.pojo.TradeObj
import com.projects.myapplication.viewmodel.CurrencyViewModel
import com.projects.myapplication.viewmodel.MyViewModelFactory
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.*

const val VIEW_CURRENCY = ""

class SymbolActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySymbolBinding
    private lateinit var obj: CrypCurr
    private lateinit var viewModel: CurrencyViewModel
    private val retrofitInterface = RetrofitInterface.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySymbolBinding.inflate(layoutInflater)
        setContentView(binding.root)
        viewModel =
            ViewModelProvider(this, MyViewModelFactory(CurrencyRepo(retrofitInterface))).get(
                CurrencyViewModel::class.java
            )
        viewModel.currencyObj.observe(this, Observer {
            obj = viewModel.currencyObj.value!!
            binding.symbolTitle.text = it.symbol
            binding.quoteAsset.text = it.quoteAsset
            binding.baseAsset.text = it.baseAsset
            binding.time.text = Utils.getDateFromTimestamp(it.at, TimeZone.getDefault())
            binding.askPrice.text = resources.getString(R.string.ask_price) + it.askPrice
            binding.bidPrice.text = resources.getString(R.string.bid_price) + it.bidPrice
            binding.lowPrice.text = resources.getString(R.string.low_price) + it.lowPrice
            binding.openPrice.text = resources.getString(R.string.open_price) + it.openPrice
            binding.lastPrice.text = resources.getString(R.string.last_price) + it.lastPrice
            binding.highPrice.text = resources.getString(R.string.high_price) + it.highPrice
        })
        viewModel.errorMessage.observe(this, Observer {
        })
        intent.getStringExtra(VIEW_CURRENCY)?.let { viewModel.getCurrency(it) }
        binding.buyBtn.setOnClickListener {
            val response = CurrencyRepo(retrofitInterface).buyCurrency(obj.symbol)
            if (response != null) {
                response.enqueue(object : Callback<TradeObj> {
                    override fun onResponse(
                        call: Call<TradeObj>,
                        response: Response<TradeObj>
                    ) {
                        println("api response" + response.code() + response.message() + response.body())
                    }

                    override fun onFailure(call: Call<TradeObj>, t: Throwable) {
                        println("api response" + call.request().url + t.fillInStackTrace())
                    }
                })
            }
        }
        binding.sellBtn.setOnClickListener {
            val response = CurrencyRepo(retrofitInterface).sellCurrency(obj.symbol)
            if (response != null) {
                response.enqueue(object : Callback<TradeObj> {
                    override fun onResponse(
                        call: Call<TradeObj>,
                        response: Response<TradeObj>
                    ) {
                        println("api response" + response.code() + response.message() + response.body())
                    }

                    override fun onFailure(call: Call<TradeObj>, t: Throwable) {
                        println("api response" + call.request().url + t.fillInStackTrace())
                    }
                })
            }
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            binding.bidBtn.setTextColor(resources.getColor(R.color.black, null))
        }
    }


}