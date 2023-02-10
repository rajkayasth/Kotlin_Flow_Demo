package com.example.flowexample.timerdemo.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.example.flowexample.R
import com.example.flowexample.databinding.ActivityTimerBinding
import com.example.flowexample.timerdemo.viewmodel.MainViewModel
import com.example.flowexample.timerdemo.viewmodel.TimerViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class TimerActivity : AppCompatActivity() {
    private var _binding: ActivityTimerBinding? = null
    private val binding get() = _binding!!
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = DataBindingUtil.setContentView(this@TimerActivity, R.layout.activity_timer)

        val viewModel = ViewModelProvider(this)[MainViewModel::class.java]
        val timerViewModel = ViewModelProvider(this)[TimerViewModel::class.java]
       /* collectLatestLifeCycleFlow(viewModel.stateFlow) {
            binding.tvShowTime.text = it.toString()

        }*/

      /*  GlobalScope.launch(Dispatchers.Main) {
            *//* viewModel.countDownFlow.collect {
                 binding.tvShowTime.text = it.toString()
             }*//*
            binding.btnShowTimer.setOnClickListener {
                viewModel.incrementCounter()
            }
            *//*viewModel.stateFlow.collect {
                binding.tvShowTime.text = it.toString()
            }*//*
        }*/




    }
}


fun <T> AppCompatActivity.collectLatestLifeCycleFlow(flow: Flow<T>, collect: suspend (T) -> Unit) {

    lifecycleScope.launch {
        repeatOnLifecycle(Lifecycle.State.STARTED) {
            flow.collectLatest(collect)
        }
    }

}