package com.example.verotask

import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.widget.ImageView
import android.widget.SearchView.OnQueryTextListener
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.verotask.adapter.RecAdapter
import com.example.verotask.adapter.VeroAdapter
import com.example.verotask.databinding.ActivityHomeBinding
import com.example.verotask.retrofit.UsersService
import com.example.verotask.retrofit.models.TaskResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.*
import kotlin.collections.ArrayList

class HomeActivity : AppCompatActivity() {
    private lateinit var binding : ActivityHomeBinding
    private lateinit var service: UsersService
    private lateinit var adapter: VeroAdapter
    private lateinit var rfrecyclerView: RecyclerView
    private lateinit var veriler: ArrayList<TaskResponse>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        val view1 = binding.root
        setContentView(view1)


        //SearchView de bulunan Arama iconu tanımlayıp rengini belirledim
        val searchIcon = binding.search.findViewById<ImageView>(androidx.appcompat.R.id.search_mag_icon)
        searchIcon.setColorFilter(Color.BLACK)
        //SearchView de bulunan kapama iconunu tanımlayıp rengini belirledim
        val cancelIcon = binding.search.findViewById<ImageView>(androidx.appcompat.R.id.search_close_btn)
        cancelIcon.setColorFilter(Color.BLACK)


        val recyclerView = findViewById<RecyclerView>(R.id.rfrecyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)


        rfrecyclerView= findViewById(R.id.rfrecyclerView)
        rfrecyclerView.layoutManager= LinearLayoutManager(rfrecyclerView.context)
        rfrecyclerView.setHasFixedSize(true)
        //Listelemedeki çizgileri oluşturmak için Recyclerview özellik ekliyorum.
        val decorator = DividerItemDecoration(applicationContext, LinearLayoutManager.VERTICAL)
        rfrecyclerView.addItemDecoration(decorator)

        //SearchView alanına aranacak kelime yazılırken karakter değişikliklerini dinleyen metod
        // Aramada kullanıcı yazarken karakter değişikliklerini dinler.
        binding.search.setOnQueryTextListener(object : OnQueryTextListener,
            androidx.appcompat.widget.SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                return false
            }
            override fun onQueryTextChange(newText: String?): Boolean {
                //Karakter değişikliğini RecyclerView_Adapter sınıfına aktararak filtrelemeyi sağlar
                adapter.filter.filter(newText)
                return false
            }

        })

        service = UsersService()

        service.getApiService(this@HomeActivity).callListTask().enqueue(object : Callback<List<TaskResponse>>{
            override fun onResponse(
                call: Call<List<TaskResponse>>,
                response: Response<List<TaskResponse>>,
            ) {
                if (response.isSuccessful) {
                    val veroListWithEmojis = ArrayList<String>()
                    veriler = (response.body() as ArrayList<TaskResponse>?)!!
                    rfrecyclerView.adapter = RecAdapter(veriler)
                    for(veri in veriler) {
                        veroListWithEmojis.add(veri.title)
                        adapter = VeroAdapter(veroListWithEmojis)
                        rfrecyclerView.adapter = adapter
                    }
                }
            }

            override fun onFailure(call: Call<List<TaskResponse>>, t: Throwable) {
                Toast.makeText(this@HomeActivity, t.message, Toast.LENGTH_SHORT).show()
            }

        })


    }

    private fun getListOfVeroIn() {
        val getChars = Locale.getAvailableLocales()
        val veroListWithEmojis = ArrayList<String>()
        for(veroCode in getChars){
            val locale = Locale("", veroCode.toString())
            val veroName = locale.displayName
            veroListWithEmojis.add(veroName)
        }

       adapter = VeroAdapter(veroListWithEmojis)
       rfrecyclerView.adapter=adapter
    }
}


