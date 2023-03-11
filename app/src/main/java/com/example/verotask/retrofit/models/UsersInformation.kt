package com.example.verotask.retrofit.models

import com.google.gson.annotations.SerializedName

data class UsersInformation(
    @SerializedName("username") val username : String,
    @SerializedName("password") val password : String
)



