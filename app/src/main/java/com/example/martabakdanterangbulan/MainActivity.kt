package com.example.martabakdanterangbulan

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.ImageButton
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat

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

        // LOGIKA KERANJANG (Buka CartActivity)
        val btnCart = findViewById<ImageButton>(R.id.btnCart)
        btnCart.setOnClickListener {
            val intent = Intent(this, CartActivity::class.java)
            startActivity(intent)
        }
    }

    override fun onResume() {
        super.onResume()

        val btnNotification = findViewById<ImageButton>(R.id.btnNotification)

        // Cek apakah ada notifikasi pesanan
        if (CartManager.adaPesananAktif) {
            // Ubah warna lonceng jadi ORANYE
            btnNotification.setColorFilter(ContextCompat.getColor(this, R.color.orange_primary))

            btnNotification.setOnClickListener {
                // BUKA HALAMAN STATUS PESANAN
                val intent = Intent(this, TransactionActivity::class.java)
                startActivity(intent)
            }
        } else {
            // Jika tidak ada pesanan, kembalikan warna PUTIH
            btnNotification.setColorFilter(ContextCompat.getColor(this, R.color.white))

            btnNotification.setOnClickListener {
                Toast.makeText(this, "Belum ada pesanan yang sedang diproses.", Toast.LENGTH_SHORT).show()
            }
        }
    }
}