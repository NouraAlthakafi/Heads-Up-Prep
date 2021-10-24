package com.example.myheadsup

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity2 : AppCompatActivity() {
    lateinit var etAddCelebrity: EditText
    lateinit var etTaboo1: EditText
    lateinit var etTaboo2: EditText
    lateinit var etTaboo3: EditText
    lateinit var buttonLetsAdd: Button
    lateinit var buttonBack: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)

        etAddCelebrity = findViewById(R.id.etAddCelebrity)
        etTaboo1 = findViewById(R.id.etTaboo1)
        etTaboo2 = findViewById(R.id.etTaboo2)
        etTaboo3 = findViewById(R.id.etTaboo3)
        buttonLetsAdd = findViewById(R.id.buttonLetsAdd)
        buttonBack = findViewById(R.id.buttonBack)

        val apiInterface = APICelebrity().getCelebrity()?.create(APIInterface::class.java)

        buttonLetsAdd.setOnClickListener{
            val na = etAddCelebrity.text.toString()
            val ta1 = etTaboo1.text.toString()
            val ta2 = etTaboo2.text.toString()
            val ta3 = etTaboo3.text.toString()

            apiInterface?.addInfo(addCelebrity(na, ta1, ta2, ta3))?.enqueue(object:
                Callback<addCelebrity> {
                override fun onResponse(call: Call<addCelebrity>, response: Response<addCelebrity>){
                    Toast.makeText(this@MainActivity2, "Celebrity Added", Toast.LENGTH_LONG).show()
                }

                override fun onFailure(call:Call<addCelebrity>, t: Throwable){
                    Toast.makeText(this@MainActivity2, "No Celebrity Added", Toast.LENGTH_LONG).show()
                }
            })
            etAddCelebrity.text.clear()
            etAddCelebrity.clearFocus()
            etTaboo1.text.clear()
            etTaboo1.clearFocus()
            etTaboo2.text.clear()
            etTaboo2.clearFocus()
            etTaboo3.text.clear()
            etTaboo3.clearFocus()
        }
        buttonBack.setOnClickListener{
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }
    }
}