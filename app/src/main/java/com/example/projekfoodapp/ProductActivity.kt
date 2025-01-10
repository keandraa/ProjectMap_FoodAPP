package com.example.projekfoodapp

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class ProductActivity : AppCompatActivity() {
    private var quantityBurger = 0
    private var quantityMilkshake = 0
    private val pricePerBurger = 55000
    private val pricePerMilkshake = 30000

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_product)

        val txtQuantityBurger = findViewById<TextView>(R.id.txtQuantityBurger)
        val txtProductPriceBurger = findViewById<TextView>(R.id.txtProductPriceBurger)
        val btnIncreaseBurger = findViewById<Button>(R.id.btnIncreaseBurger)
        val btnDecreaseBurger = findViewById<Button>(R.id.btnDecreaseBurger)

        val txtQuantityMilkshake = findViewById<TextView>(R.id.txtQuantityMilkshake)
        val txtProductPriceMilkshake = findViewById<TextView>(R.id.txtProductPriceMilkshake)
        val btnIncreaseMilkshake = findViewById<Button>(R.id.btnIncreaseMilkshake)
        val btnDecreaseMilkshake = findViewById<Button>(R.id.btnDecreaseMilkshake)


        val txtTotalPrice = findViewById<TextView>(R.id.txtTotalPrice)


        val btnAddBoth = findViewById<Button>(R.id.btnAddBoth)


        fun updateBurgerPrice() {
            val totalPrice = if (quantityBurger > 0) quantityBurger * pricePerBurger else pricePerBurger
            txtProductPriceBurger.text = "Rp.${totalPrice}"
        }


        fun updateMilkshakePrice() {
            val totalPrice = if (quantityMilkshake > 0) quantityMilkshake * pricePerMilkshake else pricePerMilkshake
            txtProductPriceMilkshake.text = "Rp.${totalPrice}"
        }


        fun updateTotalPrice() {
            val totalPrice = (quantityBurger * pricePerBurger) + (quantityMilkshake * pricePerMilkshake)
            txtTotalPrice.text = "Total: Rp.${totalPrice}"
        }


        btnIncreaseBurger.setOnClickListener {
            quantityBurger++
            txtQuantityBurger.text = quantityBurger.toString()
            updateBurgerPrice()
            updateTotalPrice()
        }

        btnDecreaseBurger.setOnClickListener {
            if (quantityBurger > 0) {
                quantityBurger--
                txtQuantityBurger.text = quantityBurger.toString()
                updateBurgerPrice()
                updateTotalPrice()
            }
        }


        btnIncreaseMilkshake.setOnClickListener {
            quantityMilkshake++
            txtQuantityMilkshake.text = quantityMilkshake.toString()
            updateMilkshakePrice()
            updateTotalPrice()
        }


        btnDecreaseMilkshake.setOnClickListener {
            if (quantityMilkshake > 0) {
                quantityMilkshake--
                txtQuantityMilkshake.text = quantityMilkshake.toString()
                updateMilkshakePrice()
                updateTotalPrice()
            }
        }


        btnAddBoth.setOnClickListener {
            val totalPrice = (quantityBurger * pricePerBurger) + (quantityMilkshake * pricePerMilkshake)


            val intent = Intent(this, ChartActivity::class.java)
            intent.putExtra("QUANTITY_BURGER", quantityBurger)
            intent.putExtra("QUANTITY_MILKSHAKE", quantityMilkshake)
            intent.putExtra("TOTAL_PRICE", totalPrice)
            startActivity(intent)
        }

        updateBurgerPrice()
        updateMilkshakePrice()
        updateTotalPrice()
    }
}
