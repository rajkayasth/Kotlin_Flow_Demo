package com.example.flowexample

import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.runBlocking

fun main() {
    /*runBlocking {
        createFlow().collect {
            println(it)
        }
    }*/

    /* runBlocking {
         createFlowOf().collect {
             println("CreateFlows $it")
         }
     }*/


    /*runBlocking {
        createFlowAS().collect {
            println("As Flow $it")
        }
    }*/


    runBlocking {
        /**
         *
         *
         * @filter() operator to get the numbers we need
         * @map() operator to transform the data to uiModel which is a better abstraction for this layer of the app
         * @catch() operator catches exceptions that could happen while processing items in the upstream flow
         * */
        requestNumbers()

            .filter { number -> number > 2 }
            .map { number -> toUiModel(number) }
            .catch { error -> println(error) }
            .collect { println(it) }
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


/**
 * asFlow() u can convert any collection like(List,Arraylist,etc) and sequence to flow using asFlow()
 * */

fun createFlowAS() = listOf<Int>(1, 2, 3, 4, 5).asFlow()

/**
 * Flow Operations
 * @property = intermediate :- Intermediate operators are used to modifying the data flow between the producer and the consumer in order to meet the requirements of the following layer.
 * @property = terminal
 *
 * @Links = https://medium.com/yemeksepeti-teknoloji/introduction-to-kotlin-flows-827f5a71ad7e
 * */

fun requestNumbers() = flow {
    (1..5).forEach {
        emit(it)
        delay(100)
    }
}

fun toUiModel(number: Int) = "Model $number"
