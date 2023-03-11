package com.example.verotask.retrofit


import com.example.verotask.retrofit.models.TaskResponse
import com.example.verotask.retrofit.models.UsersInformation
import com.example.verotask.retrofit.models.UsersResponse
import retrofit2.Call
import retrofit2.http.*

interface UsersApi {

    @Headers(
        "Content-Type: application/json",
        "Authorization: Basic QVBJX0V4cGxvcmVyOjEyMzQ1NmlzQUxhbWVQYXNz"
    )
    @POST("index.php/login")
    fun callGetUser(@Body userdata : UsersInformation) : Call<UsersResponse>

    @Headers(
        "Content-Type: application/json"
    )
    @GET("dev/index.php/v1/tasks/select")
    fun  callListTask(): Call<List<TaskResponse>>

    // GET İSTEĞİ BURAYA OLUŞTURULACAK
    // ADRES => https://api.baubuddy.de/dev/index.php/v1/tasks/select
    // HEADER'A SHARED PREFERENCEDEKI BEARER TOKEN NASIL EKLENİR ONU ÖĞREN
    // HEADER'A TOKEN EKLEYECEKSİN
    //

}