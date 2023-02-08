package com.example.flowexample

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import kotlin.math.log
import kotlin.system.measureTimeMillis

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        /*val job =*/ GlobalScope.launch(Dispatchers.Main) {

            producer()
                .flowOn(Dispatchers.IO)
                /*
            .onStart {
                Log.d("FLOWSDEMO", "onCreate: Starting Outl")
            }
            .onCompletion {
                Log.d("FLOWSDEMO", "Completed")

            }
            .onEach {
                Log.d("FLOWSDEMO", "About to Emit $it")

            }
                */

                /*  .map {
                      */
                /**
                 * convert the data in to other form
                 * @ex :- we have multiply the result with 2
                 * *//*
                    it * 2
                }
                .filter {
                    // filter the record which consumer
                    it < 8
                }*/

                //.buffer(3)

                .collect {
                    //delay(1500)
                    Log.d("KOTLIN_FLOW", "Consumer Thread: ${Thread.currentThread().name} ")
                    // Log.d("FLOWSDEMO", it.toString())
                }

        }
        /*
        GlobalScope.launch {
            */
        /**
         * to cancel a flow if flow have no consumer to collect the flow it automatic cancel the flow
         *
         * @other that we can cancel the coroutine scope which user to collect flow
         *
         * in this method we have added delay of 3500 millis and after then job object cancel the scope
         * *//*
            delay(3500)
            job.cancel()
        }
        */


        /**
         * create multiple consumer to consume the same flow
         * */

        /* GlobalScope.launch {
             val data: Flow<Int> = producer()
             delay(2500)
             data.collect {
                 Log.d("FLOWSDEMO - 2", it.toString())
             }
         }*/

    }

    fun producer() = flow<Int> {
        val list = listOf<Int>(1, 2, 3, 4, 5/*, 6, 7, 8, 9, 10*/)
        list.forEach {
            delay(1000)
            Log.d("KOTLIN_FLOW", "Emitter Thread: ${Thread.currentThread().name} ")
            emit(it)
        }
    }
}

