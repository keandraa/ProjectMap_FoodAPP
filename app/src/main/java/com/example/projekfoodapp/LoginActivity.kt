package com.example.projekfoodapp

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class LoginActivity : AppCompatActivity() {

    lateinit var btnLogin: Button
    lateinit var etEmail: EditText
    lateinit var etPassword: EditText
    lateinit var txtRegister: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        val sharedPreference = getSharedPreferences(
            "app_preference", Context.MODE_PRIVATE
        )

        val id = sharedPreference.getString("id", "")


        if (!id.isNullOrBlank()) {
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
        }

        btnLogin = findViewById(R.id.btn_login)
        etEmail = findViewById(R.id.et_email)
        etPassword = findViewById(R.id.et_password)
        txtRegister = findViewById(R.id.text_page_register)

        txtRegister.setOnClickListener {
            val intent = Intent(this, RegisterActivity::class.java)
            startActivity(intent)
        }

        btnLogin.setOnClickListener {
            val email = etEmail.text.toString().trim()
            val password = etPassword.text.toString().trim()

            if (email.isEmpty() || password.isEmpty()) {
                Toast.makeText(
                    applicationContext,
                    "Email dan Password tidak boleh kosong!",
                    Toast.LENGTH_SHORT
                ).show()
                return@setOnClickListener
            }


            auth(email, password) { isValid ->
                if (isValid) {
                    Toast.makeText(
                        applicationContext,
                        "Login berhasil!",
                        Toast.LENGTH_SHORT
                    ).show()

                    val intent = Intent(this, ProductActivity::class.java)
                    startActivity(intent)
                    finish()
                } else {
                    Toast.makeText(
                        applicationContext,
                        "Email atau password salah!",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
        }
    }

    private fun auth(email: String, password: String, checkResult: (isValid: Boolean) -> Unit) {
        val db = Firebase.firestore

        db.collection("users").whereEqualTo("email", email)
            .get()
            .addOnSuccessListener { documents ->
                if (documents.isEmpty) {
                    checkResult.invoke(false)
                    return@addOnSuccessListener
                }

                var isUserValid = false
                for (document in documents) {
                    val pass = document.data["password"].toString()
                    if (pass == PasswordHelper.md5(password)) {
                        // Simpan data ke shared preferences
                        val sharedPreference = getSharedPreferences(
                            "app_preference", Context.MODE_PRIVATE
                        )

                        val editor = sharedPreference.edit()
                        editor.putString("id", document.id)
                        editor.putString("name", document.data["name"].toString())
                        editor.putString("email", document.data["email"].toString())
                        editor.apply()

                        isUserValid = true
                        break
                    }
                }

                checkResult.invoke(isUserValid)
            }
            .addOnFailureListener {
                Toast.makeText(applicationContext, "Gagal terhubung ke server!", Toast.LENGTH_SHORT).show()
                checkResult.invoke(false)
            }
    }
}
