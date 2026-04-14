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

        // Jika tombol Martabak diklik
        btnMartabak.setOnClickListener {
            val intent = Intent(this, MenuActivity::class.java)
            // Mengirim data "KATEGORI" berisi "MARTABAK"
            intent.putExtra("KATEGORI", "MARTABAK")
            startActivity(intent)
        }

        // Jika tombol Terang Bulan diklik
        btnTerangBulan.setOnClickListener {
            val intent = Intent(this, MenuActivity::class.java)
            // Mengirim data "KATEGORI" berisi "TERANGBULAN"
            intent.putExtra("KATEGORI", "TERANGBULAN")
            startActivity(intent)
        }

        // Kode untuk tombol profil
        val btnProfile = findViewById<ImageButton>(R.id.btnProfile)

        btnProfile.setOnClickListener {
            val intent = Intent(this, ProfileActivity::class.java)
            startActivity(intent)
        }
    }
}