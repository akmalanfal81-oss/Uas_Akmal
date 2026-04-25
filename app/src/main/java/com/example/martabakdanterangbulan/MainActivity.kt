package com.example.martabakdanterangbulan

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.ImageButton
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val btnMartabak = findViewById<Button>(R.id.btnKategoriMartabak)
        val btnTerangBulan = findViewById<Button>(R.id.btnKategoriTerangBulan)

        btnMartabak.setOnClickListener {
            val intent = Intent(this, MenuActivity::class.java)
            intent.putExtra("KATEGORI", "MARTABAK")
            startActivity(intent)
        }

        btnTerangBulan.setOnClickListener {
            val intent = Intent(this, MenuActivity::class.java)
            intent.putExtra("KATEGORI", "TERANGBULAN")
            startActivity(intent)
        }

        val btnProfile = findViewById<ImageButton>(R.id.btnProfile)
        btnProfile.setOnClickListener {
            val intent = Intent(this, ProfileActivity::class.java)
            startActivity(intent)
        }

        // LOGIKA LONCENG (Buka Keranjang Langsung)
        val btnCartBell = findViewById<ImageButton>(R.id.btnCartBell)
        btnCartBell.setOnClickListener {
            val intent = Intent(this, CartActivity::class.java)
            startActivity(intent)
        }
    }
}