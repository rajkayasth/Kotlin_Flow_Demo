package com.example.flowexample

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
       val job = GlobalScope.launch {
            val data: Flow<Int> = producer()
            data.collect {
                Log.d("FLOWSDEMO", it.toString())
            }
        }
        GlobalScope.launch {
            /**
             * to cancel a flow if flow have no consumer to collect the flow it automatic cancel the flow
             *
             * @other that we can cancel the coroutine scope which user to collect flow
             *
             * in this method we have added delay of 3500 millis and after then job object cancel the scope
             * */
            delay(3500)
            job.cancel()
        }
    }

    fun producer() = flow<Int> {
        val list = listOf<Int>(1, 2, 3, 4, 5, 6, 7, 8, 9, 10)
        list.forEach {
            delay(1000)
            emit(it)
        }
    }
}

