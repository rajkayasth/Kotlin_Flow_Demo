package com.example.flowexample.timerdemo.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

class MainViewModel : ViewModel() {

    val countDownFlow = flow<Int> {
        val startingValue = 5
        var currentValue = startingValue
        emit(startingValue)
        while (currentValue > 0) {
            delay(1000)
            currentValue--
            emit(currentValue)
        }
    }

    init {
        collectFlow()
    }

    private fun collectFlow() {
        viewModelScope.launch {

            val flow1 = flow {
                emit(1)
                delay(500)
                emit(2)
            }


            /* val count = countDownFlow
                 .filter { time ->
                     time % 2 == 0
                 }
                 .map { time ->
                     time * time
                 }
                 .count { time ->
                     time % 2 == 0
                 }
             println("Current Time is $count")
             *//*.collect { time ->
                println("Current Time is $time")
            }*//*
            */

            val reduceResult = countDownFlow
                .fold(100)
                { accumulator, value ->
                    accumulator + value
                }
            println("Current Time is $reduceResult")


        }

    }

}