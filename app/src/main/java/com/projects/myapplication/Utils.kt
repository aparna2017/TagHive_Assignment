package com.projects.myapplication

import java.text.SimpleDateFormat
import java.util.*

object Utils {

    public fun getDateFromTimestamp(dateMillis: Long, timeZone: TimeZone?): String? {
        //  2020-02-11T08:40:58+0000
        val df_derived_v2 = SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.ENGLISH)
        df_derived_v2.timeZone = timeZone
        return df_derived_v2.format(Date(dateMillis))
    }


}