package com.example.verotask

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.service.autofill.UserData
import android.text.method.TextKeyListener.clear
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.verotask.databinding.ActivityLoginBinding
import com.example.verotask.network.AuthManager
import com.example.verotask.retrofit.UsersApi
import com.example.verotask.retrofit.UsersService
import com.example.verotask.retrofit.models.UsersInformation
import com.example.verotask.retrofit.models.UsersResponse
import kotlinx.android.synthetic.main.activity_login.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.lang.Exception

class LoginActivity : AppCompatActivity() {
    private lateinit var binding : ActivityLoginBinding
    private lateinit var authManager: AuthManager
    private lateinit var service: UsersService

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // LOGİN OLURKEN SHARED PREFERENCEDA ACCESS_TOKEN OLMAMASI İÇİN
        // SHARED PREFERENCEDAKİ ACCESS_TOKEN'I SİLİYORUZ
        val mSharedPreferences = getSharedPreferences("VeroTask", Context.MODE_PRIVATE).edit()
        mSharedPreferences.remove("access_token").apply()

        authManager = AuthManager(this)

        binding.apply {
            loginButton.setOnClickListener {
                val userNameText = userNameEditText.text.toString()
                val passwordText = passwordEditText.text.toString()

                if (!userNameText.isEmpty() && !passwordText.isEmpty()){

                    val usersInformation = UsersInformation ( username = userNameText, password = passwordText )

                    service = UsersService()

                    service.getApiService(this@LoginActivity).callGetUser(usersInformation).enqueue(object : Callback<UsersResponse>{
                        override fun onResponse(
                            call: Call<UsersResponse>,
                            response: Response<UsersResponse>,
                        ) {
                            // ÖNCELİKLE RESPONSE'UN BAŞARILI OLUP OLMADIĞINI response.isSuccesfull ile kontrol ediyoruz
                            // Daha sonra gelen repsonse'un body'sinin boş olmadığını kontrol ediyoruz response.body != null ile
                            if(response.isSuccessful && response.body() != null) {
                                authManager.saveAuthToken(response.body()!!.oauth.access_token)

                                val intent = Intent(this@LoginActivity,HomeActivity::class.java)
                                startActivity(intent)
                                finish()
                            } else {
                                Toast.makeText(this@LoginActivity, "İSTEKTEN HATA DÖNDÜ", Toast.LENGTH_SHORT).show()
                            }
                        }

                        override fun onFailure(call: Call<UsersResponse>, t: Throwable) {
                            Log.d("ON FAILURE => ", t.message.toString())
                        }

                    })
                } else {
                    Toast.makeText(this@LoginActivity, "Email veya parola yanlış.", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }
}
