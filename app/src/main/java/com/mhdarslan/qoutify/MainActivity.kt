package com.mhdarslan.qoutify

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.ViewModelProvider
import com.mhdarslan.qoutify.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    lateinit var mainViewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        mainViewModel = ViewModelProvider(this, MainViewModelFactory(application)).get(MainViewModel::class.java)
        setQuote(mainViewModel.getQuote())

        binding.btnNext.setOnClickListener {
            onNext()
        }

        binding.btnPrevious.setOnClickListener {
            onPrevious()
        }

        binding.floatingActionButton.setOnClickListener {
            onShare()
        }

    }

    private fun setQuote(quote: QuoteModel) {
        binding.quoteText.text = quote.text
    }

    private fun onNext(){
        setQuote(mainViewModel.nextQuote())
    }

    private fun onPrevious(){
        setQuote(mainViewModel.previousQuote())
    }

    private fun onShare(){
        val intent = Intent(Intent.ACTION_SEND)
        intent.setType("text/plain")
        intent.putExtra(Intent.EXTRA_TEXT, mainViewModel.getQuote().text)
        startActivity(intent)
    }
}