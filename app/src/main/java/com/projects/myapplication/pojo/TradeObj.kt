package com.projects.myapplication.pojo

data class TradeObj(
    val symbol: String,
    val id: Int,
    val price: String,
    val origQty: String,
    val executedQty: String,
    val status: String,
    val type: String,
    val side: String,
    val updatedTime: Long,
    val createdTime: Long
)
