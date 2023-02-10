package com.example.flowexample.timerdemo.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.example.flowexample.R
import com.example.flowexample.databinding.ActivityTimerBinding
import com.example.flowexample.timerdemo.viewmodel.MainViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class TimerActivity : AppCompatActivity() {
    private var _binding: ActivityTimerBinding? = null
    private val binding get() = _binding!!
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = DataBindingUtil.setContentView(this@TimerActivity, R.layout.activity_timer)

        val viewModel = ViewModelProvider(this)[MainViewModel::class.java]
        GlobalScope.launch(Dispatchers.Main) {
            viewModel.countDownFlow.collect {
                binding.tvShowTime.text = it.toString()
            }
        }
    }
}