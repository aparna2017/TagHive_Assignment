package com.projects.myapplication.adapters

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView
import com.projects.myapplication.R
import com.projects.myapplication.SymbolActivity
import com.projects.myapplication.Utils
import com.projects.myapplication.Utils.getDateFromTimestamp
import com.projects.myapplication.VIEW_CURRENCY
import com.projects.myapplication.databinding.ItemBinding
import com.projects.myapplication.pojo.CrypCurr
import java.util.*

class MainAdapter : RecyclerView.Adapter<MainViewHolder>() {
    var currencies = mutableListOf<CrypCurr>()
    lateinit var ctx: Context
    fun setCurrencyList(currencies: List<CrypCurr>) {
        this.currencies = currencies.toMutableList()
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainViewHolder {
        ctx = parent.context
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemBinding.inflate(inflater, parent, false)
        return MainViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MainViewHolder, position: Int) {
        val crypCurr = currencies[position]
        holder.binding.symbolTitle.text = crypCurr.symbol
        holder.binding.quoteAsset.text = crypCurr.quoteAsset
        holder.binding.baseAsset.text = crypCurr.baseAsset
        holder.binding.time.text = getDateFromTimestamp(crypCurr.at, TimeZone.getDefault())
        holder.binding.askPrice.text =
            ctx.resources.getString(R.string.ask_price) + crypCurr.askPrice
        holder.binding.bidPrice.text =
            ctx.resources.getString(R.string.bid_price) + crypCurr.bidPrice
        holder.binding.lowPrice.text =
            ctx.resources.getString(R.string.low_price) + crypCurr.lowPrice
        holder.binding.openPrice.text =
            ctx.resources.getString(R.string.open_price) + crypCurr.openPrice
        holder.binding.lastPrice.text =
            ctx.resources.getString(R.string.last_price) + crypCurr.lastPrice
        holder.binding.highPrice.text =
            ctx.resources.getString(R.string.high_price) + crypCurr.highPrice
        holder.binding.cardView.setOnClickListener {
            val viewSymbol = Intent(ctx, SymbolActivity::class.java)
            viewSymbol.putExtra(VIEW_CURRENCY, crypCurr.symbol)
            ctx.startActivity(viewSymbol)
        }
    }

    override fun getItemCount(): Int {
        return currencies.size
    }
}

class MainViewHolder(val binding: ItemBinding) : RecyclerView.ViewHolder(binding.root) {
}