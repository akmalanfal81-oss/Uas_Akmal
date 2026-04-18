package com.example.martabakdanterangbulan

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MenuActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_menu)

        val tvKategoriTitle = findViewById<TextView>(R.id.tvKategoriTitle)
        val rvMenu = findViewById<RecyclerView>(R.id.rvMenu)

        // Menemukan tombol kembali dan memberinya perintah 'finish'
        val btnBackMenu = findViewById<android.widget.ImageButton>(R.id.btnBackMenu)
        btnBackMenu.setOnClickListener {
            finish() // Menutup halaman Menu dan kembali ke halaman utama
        }

        // Mengatur agar daftar menu memanjang ke bawah (vertikal)
        rvMenu.layoutManager = LinearLayoutManager(this)

        // Menangkap pesan Intent dari MainActivity
        val kategoriDipilih = intent.getStringExtra("KATEGORI")

        // Menyiapkan keranjang kosong menggunakan struktur MenuItem
        val daftarMenu = ArrayList<MenuItem>()

        // Mengisi keranjang sesuai tombol yang diklik
        if (kategoriDipilih == "MARTABAK") {
            tvKategoriTitle.text = "Daftar Menu Martabak"

            daftarMenu.add(MenuItem("Martabak Telur Bebek (2 Telur)", "Rp 30.000"))
            daftarMenu.add(MenuItem("Martabak Daging Sapi Spesial", "Rp 45.000"))
            daftarMenu.add(MenuItem("Martabak Ayam Jamur", "Rp 35.000"))

        } else if (kategoriDipilih == "TERANGBULAN") {
            tvKategoriTitle.text = "Daftar Menu Terang Bulan"

            daftarMenu.add(MenuItem("Terang Bulan Coklat Kacang", "Rp 25.000"))
            daftarMenu.add(MenuItem("Terang Bulan Keju Susu", "Rp 30.000"))
            daftarMenu.add(MenuItem("Terang Bulan Red Velvet Oreo", "Rp 35.000"))
        }

        // TAHAP AKHIR: Nyalakan mesin Adapter dan letakkan di Rak (RecyclerView)
        val adapterMenu = MenuAdapter(daftarMenu)
        rvMenu.adapter = adapterMenu
    }
}