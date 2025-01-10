package com.example.projekfoodapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast

class MainActivity : AppCompatActivity() {

    lateinit var btnMasuk: Button
    lateinit var txtResult: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btnMasuk = findViewById(R.id.btn_Main)
        txtResult = findViewById(R.id.textView2)

        btnMasuk.setOnClickListener {
            val message = "Welcome to Makan Lurr!"
            txtResult.text = message
            Toast.makeText(this, message, Toast.LENGTH_SHORT).show()


            val Button = findViewById<Button>(R.id.btn_Main)
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
        }
    }
}
