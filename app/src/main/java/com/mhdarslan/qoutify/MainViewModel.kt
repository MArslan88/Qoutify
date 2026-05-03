package com.mhdarslan.qoutify

import android.content.Context
import androidx.lifecycle.ViewModel
import com.google.gson.Gson

class MainViewModel(val context: Context): ViewModel() {
    private var quoteList: Array<QuoteModel> = emptyArray()
    private var index = 0

    init {
        quoteList = loadQuoteFromAssetes()
    }

    private fun loadQuoteFromAssetes(): Array<QuoteModel> {
        val inputStream = context.assets.open("quote.json")
        val size: Int =inputStream.available()
        val buffer = ByteArray(size)

        inputStream.read(buffer)
        inputStream.close()

        val json = String(buffer, Charsets.UTF_8)
        val gson = Gson()
        return gson.fromJson(json, Array<QuoteModel>::class.java)

    }

    fun getQuote() = quoteList[index]
    fun nextQuote() = quoteList[++index % quoteList.size]
    fun previousQuote() = quoteList[(--index + quoteList.size) % quoteList.size]
}