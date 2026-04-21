package com.example.martabakdanterangbulan

import android.os.Bundle
import android.widget.ImageButton
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class InfoActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_info)

        // Tombol kembali (X)
        val btnBackInfo = findViewById<ImageButton>(R.id.btnBackInfo)
        btnBackInfo.setOnClickListener {
            finish()
        }

        // Menghubungkan variabel dengan XML
        val tvNama = findViewById<TextView>(R.id.tvInfoNamaMenu)
        val tvHarga = findViewById<TextView>(R.id.tvInfoHargaMenu)
        val tvDesc = findViewById<TextView>(R.id.tvInfoDeskripsi)

        // Mengambil data yang dikirimkan dari kotak menu yang diklik
        tvNama.text = intent.getStringExtra("NAMA_MENU")
        tvHarga.text = intent.getStringExtra("HARGA_MENU")
        tvDesc.text = intent.getStringExtra("DESC_MENU")
    }
}