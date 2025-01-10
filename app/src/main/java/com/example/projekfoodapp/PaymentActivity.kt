package com.example.projekfoodapp

import android.os.Bundle
import android.widget.TextView
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import java.text.NumberFormat
import java.util.Locale

class PaymentActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_payment)


        val totalPrice = intent.getDoubleExtra("TOTAL_PRICE", 0.0)


        val txtPaymentStatus = findViewById<TextView>(R.id.txtPaymentStatus)
        val btnBackToProduct = findViewById<Button>(R.id.btnBackToProduct)


        val currencyFormatter = NumberFormat.getCurrencyInstance(Locale("id", "ID"))
        val formattedTotalPrice = currencyFormatter.format(totalPrice)


        txtPaymentStatus.text = "Pesanan sudah diproses\nTotal: $formattedTotalPrice"


        btnBackToProduct.setOnClickListener {

            finish()
        }
    }
}
