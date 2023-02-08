package com.example.flowexample

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.launch

class SharedFlowActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_shared_flow)
        //shareFlowDemo()
        stateFlowDemo()
    }

    private fun stateFlowDemo() {
        GlobalScope.launch(Dispatchers.Main) {
            val result = producerStateFlow()
            delay(6000)
            result.collect {
                Log.d("stateFlowDemo", "stateFlowDemo: Item -$it")
            }
        }
    }

    private fun shareFlowDemo() {
        GlobalScope.launch(Dispatchers.Main) {
            val result = producer()
            result.collect {
                Log.d("shareFlowDemo", "shareFlowDemo: Item -$it")
            }
        }
        GlobalScope.launch(Dispatchers.Main) {
            val result = producer()
            delay(2500)
            result.collect {
                Log.d("shareFlowDemo2", "shareFlowDemo: Item -$it")
            }
        }
    }

    private fun producer(): Flow<Int> {
        val mutableShareFlowDemo = MutableSharedFlow<Int>()
        GlobalScope.launch {
            val list = listOf(1, 2, 3, 4, 5)
            list.forEach {
                mutableShareFlowDemo.emit(it)
                delay(1000)
            }
        }
        return mutableShareFlowDemo
    }

    private fun producerStateFlow(): Flow<Int> {
        val mutableStateFlow = MutableStateFlow(10)
        GlobalScope.launch {
            delay(2000)
            mutableStateFlow.emit(20)
            delay(2000)
            mutableStateFlow.emit(30)
        }
        return mutableStateFlow
    }
}