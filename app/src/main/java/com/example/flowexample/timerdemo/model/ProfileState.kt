package com.example.flowexample.timerdemo.model

data class ProfileState(
    val userName: String? = null,
    val description: String? = null,
    val profilePicUrl: String? = null,
    val post: List<Post> = emptyList()
)
