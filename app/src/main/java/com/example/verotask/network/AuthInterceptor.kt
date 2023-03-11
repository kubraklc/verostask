package com.example.verotask.network

import android.content.Context
import okhttp3.Interceptor
import okhttp3.Request
import okhttp3.Response

class AuthInterceptor(context: Context) : Interceptor {
    private val sessionManager = AuthManager(context)

    override fun intercept(chain: Interceptor.Chain): Response {
        val requestBuilder = chain.request().newBuilder()

        // If token has been saved, add it to the request
        // İSTEK ATARKEN REQUEST İLE RESPONSE ARASINA MÜDAHALE ETMEK İÇİN INTERCEPTOR KULLANIYORUZ
        // 2 TANE AUTHORIZATION GÖNDERİNCE HATA VERİYOR O YÜZDEN
        // HEADER'A AUTHORIZATION EKLERKEN ÖNCEKİLERİ SİLİYORUZ
        sessionManager.fetchAuthToken()?.let {
            requestBuilder.removeHeader("Authorization")
            requestBuilder.addHeader("Authorization", "Bearer $it")
        }

        return chain.proceed(requestBuilder.build())
    }
}