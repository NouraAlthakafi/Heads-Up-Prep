package com.example.myheadsup

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Response
import retrofit2.Callback

class MainActivity3 : AppCompatActivity() {
    lateinit var etUpdateName: EditText
    lateinit var etUpdateTaboo1: EditText
    lateinit var etUpdateTaboo2: EditText
    lateinit var etUpdateTaboo3: EditText
    lateinit var buttonDelete: Button
    lateinit var buttonUpdate: Button
    lateinit var buttonDone: Button

    var ID = 0

    val apiInterface = APICelebrity().getCelebrity()?.create(APIInterface::class.java)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main3)

        etUpdateName = findViewById(R.id.etUpdateName)
        etUpdateTaboo1 = findViewById(R.id.etUpdateTaboo1)
        etUpdateTaboo2 = findViewById(R.id.etUpdateTaboo2)
        etUpdateTaboo3 = findViewById(R.id.etUpdateTaboo3)
        buttonDelete = findViewById(R.id.buttonDelete)
        buttonUpdate = findViewById(R.id.buttonUpdate)
        buttonDone = findViewById(R.id.buttonDone)


        ID = intent.getIntExtra("ID", 0)

        buttonDelete.setOnClickListener{
            apiInterface?.deleteInfo(ID)?.enqueue(object:
                retrofit2.Callback<Void> {
                override fun onResponse(call: Call<Void>, response: Response<Void>) {
                    Toast.makeText(this@MainActivity3, "Celebrity Deleted", Toast.LENGTH_LONG).show()
                }

                override fun onFailure(call: Call<Void>, t: Throwable) {
                    Toast.makeText(this@MainActivity3, "No Celebrity Deleted", Toast.LENGTH_LONG).show()
                }
            })
        }

        buttonUpdate.setOnClickListener{
            if(etUpdateName.text.isNotEmpty() && etUpdateTaboo1.text.isNotEmpty() && etUpdateTaboo2.text.isNotEmpty() && etUpdateTaboo3.text.isNotEmpty()){
                updatingInfo()
            }
        }

        buttonDone.setOnClickListener{

            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }
    }
    fun updatingInfo(){
        apiInterface?.updateInfo(
            ID,
            celebrityList(
                ID,
                etUpdateName.text.toString(),
                etUpdateTaboo1.text.toString(),
                etUpdateTaboo2.text.toString(),
                etUpdateTaboo3.text.toString()
            )
        )?.enqueue(object: Callback<celebrityList> {
            override fun onResponse(call: Call<celebrityList>, response: Response<celebrityList>) {
                Toast.makeText(this@MainActivity3, "Celebrity Updated", Toast.LENGTH_LONG).show()
            }

            override fun onFailure(call: Call<celebrityList>, t: Throwable) {
                Toast.makeText(this@MainActivity3, "No Celebrity Updated", Toast.LENGTH_LONG).show()
            }
        })
    }
}