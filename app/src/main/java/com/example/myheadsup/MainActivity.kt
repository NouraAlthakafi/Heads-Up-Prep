package com.example.myheadsup

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {

    lateinit var rvCelebrity: RecyclerView
    lateinit var buttonAdd: Button
    lateinit var etCelebrity: EditText
    lateinit var buttonSubmit: Button

    lateinit var celebrityArray: ArrayList <celebrityList>

    var searchPk = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        rvCelebrity = findViewById(R.id.rvCelebrity)
        buttonAdd = findViewById(R.id.buttonAdd)
        etCelebrity = findViewById(R.id.etCelebrity)
        buttonSubmit = findViewById(R.id.buttonSubmit)

        celebrityArray = arrayListOf()

        rvCelebrity.adapter = RVcelebrity(celebrityArray)
        rvCelebrity.layoutManager = LinearLayoutManager(this)

        val apiInterface = APICelebrity().getCelebrity()?.create(APIInterface::class.java)
        val call: Call<List<celebrityList?>> = apiInterface!!.showInfo()

        call?.enqueue(object: Callback<List<celebrityList?>>{
            override fun onResponse(
                call: Call<List<celebrityList?>>,
                response: Response<List<celebrityList?>>
            ) {
                val resource: List<celebrityList?>? = response.body()
                if (resource != null){
                    for (i in resource){
                        celebrityArray.add(celebrityList(i!!.pk, i.name, i.taboo1, i.taboo2, i.taboo3))
                        rvCelebrity.adapter?.notifyDataSetChanged()
                        rvCelebrity.scrollToPosition(celebrityArray.size-1)
                    }
                }
            }

            override fun onFailure(call: Call<List<celebrityList?>>, t: Throwable) {
                call.cancel()
            }

            })
        buttonAdd.setOnClickListener{
            val intent = Intent(this, MainActivity2::class.java)
            startActivity(intent)
        }
        buttonSubmit.setOnClickListener{
            for(i in celebrityArray){
                if (i.name == etCelebrity.text.toString()){
                    searchPk = i.pk!!.toInt()
                }
            }
            val intent = Intent(this, MainActivity3::class.java)
            intent.putExtra("ID", searchPk)
            startActivity(intent)
        }
    }
}