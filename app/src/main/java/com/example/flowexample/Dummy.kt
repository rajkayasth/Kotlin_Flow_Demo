package com.example.flowexample

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

/*
fun getShippingDetails(userList: List<User>): Flow<ShoppingDetails> {
    return flow {
        userList.forEach {
            val user = getUser(it.id)
            val shippingAddress = getShippingAddress(user.address)
            val shippingDetails = calculateShippingCharges(shippingAddress)
        }
    }
}

fun main() {
    val userList = getUserList()
    val shippingDetails: Flow<ShippingDetails> = getShippingDetails(userList)
}*/
