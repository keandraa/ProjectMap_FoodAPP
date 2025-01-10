package com.example.projekfoodapp

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import java.text.NumberFormat
import java.util.Locale

class ChartActivity : AppCompatActivity() {
    private var quantityBurger = 0
    private var quantityMilkshake = 0
    private val pricePerBurger = 55000
    private val pricePerMilkshake = 30000

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_chart)


        val intent = intent
        quantityBurger = intent.getIntExtra("QUANTITY_BURGER", 0)
        quantityMilkshake = intent.getIntExtra("QUANTITY_MILKSHAKE", 0)


        val txtBurgerDetails = findViewById<TextView>(R.id.txtBurgerDetails)
        val txtMilkshakeDetails = findViewById<TextView>(R.id.txtMilkshakeDetails)
        val txtTotalPrice = findViewById<TextView>(R.id.txtTotalPrice)
        val btnConfirmPayment = findViewById<Button>(R.id.btnConfirmPayment)
        val btnBackToProduct = findViewById<Button>(R.id.btnBackToProduct)


        val currencyFormatter = NumberFormat.getCurrencyInstance(Locale("id", "ID"))


        fun updateDisplay() {
            val burgerTotal = quantityBurger * pricePerBurger
            val milkshakeTotal = quantityMilkshake * pricePerMilkshake
            val totalPrice = burgerTotal + milkshakeTotal

            txtBurgerDetails.text = if (quantityBurger > 0) {
                "Burger x$quantityBurger: ${currencyFormatter.format(burgerTotal)}"
            } else {
                ""
            }

            txtMilkshakeDetails.text = if (quantityMilkshake > 0) {
                "Milkshake x$quantityMilkshake: ${currencyFormatter.format(milkshakeTotal)}"
            } else {
                ""
            }

            txtTotalPrice.text = "Total: ${currencyFormatter.format(totalPrice)}"
        }


        updateDisplay()


        txtBurgerDetails.setOnLongClickListener {
            if (quantityBurger > 0) {
                quantityBurger -= 1
                updateDisplay()
            }
            true
        }

        txtMilkshakeDetails.setOnLongClickListener {
            if (quantityMilkshake > 0) {
                quantityMilkshake -= 1
                updateDisplay()
            }
            true
        }


        btnConfirmPayment.setOnClickListener {
            val burgerTotal = quantityBurger * pricePerBurger
            val milkshakeTotal = quantityMilkshake * pricePerMilkshake
            val totalPrice = burgerTotal + milkshakeTotal


            val paymentIntent = Intent(this, PaymentActivity::class.java)


            paymentIntent.putExtra("TOTAL_PRICE", totalPrice.toDouble())

            startActivity(paymentIntent)
            finish()
        }

        btnBackToProduct.setOnClickListener {
            val backIntent = Intent(this, ProductActivity::class.java)
            backIntent.putExtra("QUANTITY_BURGER", quantityBurger)
            backIntent.putExtra("QUANTITY_MILKSHAKE", quantityMilkshake)
            startActivity(backIntent)
            finish()
        }
    }
}


