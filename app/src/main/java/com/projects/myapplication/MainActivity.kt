package com.projects.myapplication

import android.os.Bundle
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.projects.myapplication.adapters.MainAdapter
import com.projects.myapplication.api.CurrencyRepo
import com.projects.myapplication.api.RetrofitInterface
import com.projects.myapplication.databinding.ActivityMainBinding
import com.projects.myapplication.viewmodel.CurrencyViewModel
import com.projects.myapplication.viewmodel.MyViewModelFactory

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var viewModel: CurrencyViewModel
    private val retrofitInterface = RetrofitInterface.getInstance()
    val adapter = MainAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel = ViewModelProvider(this, MyViewModelFactory(CurrencyRepo(retrofitInterface))).get(CurrencyViewModel::class.java)
        binding.rvMain.layoutManager = LinearLayoutManager(binding.rvMain.context)
        binding.rvMain.adapter = adapter
        viewModel.currencyList.observe(this, Observer {
            adapter.setCurrencyList(it)
        })
        viewModel.errorMessage.observe(this, Observer {
        })
        viewModel.getCurrency()
        setSupportActionBar(binding.toolbar)
        binding.toolbar.setTitle(R.string.app_name)

    }

    override fun onSupportNavigateUp(): Boolean {
        super.onSupportNavigateUp()
        return true
    }
}