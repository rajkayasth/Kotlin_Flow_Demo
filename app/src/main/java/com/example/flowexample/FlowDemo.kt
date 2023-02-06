package com.example.flowexample

import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.runBlocking

fun main() {
    runBlocking {
        createFlow().collect {
            println(it)
        }
    }

    runBlocking {
        createFlowOf().collect {
            println("CreateFlows $it")
        }
    }
}

/**
 * flow<T> {} you can pass any suspendable value
 * */
fun createFlow() = flow<Int> {
    (1..5).forEach {
        emit(it)
    }
}

/**
 * flowOf() you can Flow from a fixed set of values.
 * */

fun createFlowOf() = flowOf(1, 2, 3, 4, 5)