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


    private val _stateFlow = MutableStateFlow(0)
    val stateFlow = _stateFlow.asStateFlow()
    private val _sharedFlow = MutableSharedFlow<Int>(0)
    val sharedFlow = _sharedFlow.asSharedFlow()

    fun incrementCounter() {
        _stateFlow.value += 1
    }

    fun squareNumber(number: Int) {
        viewModelScope.launch {
            _sharedFlow.emit(number * number)
        }
    }

    init {
        // collectFlow()
        viewModelScope.launch {
            sharedFlow.collect {
                delay(2000)
                println("FIRST_FLOW: The received number is $it")
            }
        }
        viewModelScope.launch {
            sharedFlow.collect {
                delay(3000)
                println("SECOND_FLOW: The received number is $it")
            }
        }
        squareNumber(3)

    }

    private fun collectFlow() {

        val flow = flow {
            delay(250)
            emit("Appetizer")
            delay(1000)
            emit("Main dish")
            delay(100)
            emit("Dessert")
        }

        viewModelScope.launch {

            flow.onEach {
                println("FLOW: $it is delivered")
            }
                .conflate()
                .collect {
                    println("FLOW: Now Eating $it")
                    delay(1500)
                    println("FLOW: Finished Eating $it")

                }

            // val flow1 = (1..5).asFlow()


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
/*

            val reduceResult = countDownFlow
                .fold(100)
                { accumulator, value ->
                    accumulator + value
                }
            println("Current Time is $reduceResult")
*/
/*
            flow1.flatMapConcat { id ->
                getRecipeById(id)
            }.collect {
                println("Value Is $it")
            }*/


        }

    }

}