package com.example.verotask.retrofit.models

data class UsersResponse(
    val apiVersion: String,
    val oauth: Oauth,
    val permissions: List<String>,
    val showPasswordPrompt: Boolean,
    val userInfo: UserInfo
)