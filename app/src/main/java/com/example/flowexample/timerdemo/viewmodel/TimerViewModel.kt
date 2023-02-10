package com.example.flowexample.timerdemo.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.flowexample.timerdemo.model.Post
import com.example.flowexample.timerdemo.model.ProfileState
import com.example.flowexample.timerdemo.model.User
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.*

class TimerViewModel : ViewModel() {

    private val isAutheticated = MutableStateFlow(true)
    private val user = MutableStateFlow<User?>(null)
    private val posts = MutableStateFlow(emptyList<Post>())

    private val _profileState = MutableStateFlow<ProfileState?>(null)
    val profileState = _profileState.asStateFlow()

    private val flow1 = (1..10).asFlow().onEach { delay(1000) }
    private val flow2 = (10..20).asFlow().onEach { delay(300) }
    var numberString = MutableStateFlow("")
        private set

    init {
        isAutheticated.combine(user) { isAutheticated, user ->
            if (isAutheticated) user else null

        }.combine(posts) { user, posts ->
            user?.let {
                _profileState.value = profileState.value?.copy(
                    profilePicUrl = user.profilePicUrl,
                    userName = user.userName,
                    description = user.description,
                    post = posts
                )
            }
        }.launchIn(viewModelScope)

        flow1.zip(flow2) { number1, number2 ->



        }
    }

}