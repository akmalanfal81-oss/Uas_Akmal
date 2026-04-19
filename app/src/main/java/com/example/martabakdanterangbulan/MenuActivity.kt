package com.example.martabakdanterangbulan

import android.content.Intent
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
            finish() // Menutup halaman Menu dan kembali ke halaman sebelumnya
        }

        // Mengatur agar daftar menu memanjang ke bawah (vertikal)
        rvMenu.layoutManager = LinearLayoutManager(this)

        // Menangkap pesan Intent
        val kategoriDipilih = intent.getStringExtra("KATEGORI")

        // Menyiapkan keranjang kosong menggunakan struktur MenuItem
        val daftarMenu = ArrayList<MenuItem>()

        // Mengisi keranjang sesuai tombol atau menu yang diklik
        if (kategoriDipilih == "MARTABAK") {
            tvKategoriTitle.text = "Daftar Menu Martabak"
            daftarMenu.add(MenuItem("Martabak Telur Bebek (2 Telur)", "Rp 30.000"))
            daftarMenu.add(MenuItem("Martabak Daging Sapi Spesial", "Rp 45.000"))
            daftarMenu.add(MenuItem("Martabak Ayam Jamur", "Rp 35.000"))

        } else if (kategoriDipilih == "TERANGBULAN") {
            tvKategoriTitle.text = "Pilih Adonan Terang Bulan"
            daftarMenu.add(MenuItem("1. Terang Bulan Original (Putih/Kuning)", "Ketuk untuk pilih toping"))
            daftarMenu.add(MenuItem("2. Terang Bulan Pandan", "Ketuk untuk pilih toping"))
            daftarMenu.add(MenuItem("3. Terang Bulan Red Velvet", "Ketuk untuk pilih toping"))

            // KATEGORI BARU UNTUK MASING-MASING ADONAN
        } else if (kategoriDipilih == "TOPING_ORIGINAL") {
            tvKategoriTitle.text = "Toping - Adonan Original"
            daftarMenu.add(MenuItem("Original Coklat Kacang", "Rp 25.000"))
            daftarMenu.add(MenuItem("Original Keju Susu", "Rp 30.000"))
            daftarMenu.add(MenuItem("Original Campur Spesial", "Rp 35.000"))

        } else if (kategoriDipilih == "TOPING_PANDAN") {
            tvKategoriTitle.text = "Toping - Adonan Pandan"
            daftarMenu.add(MenuItem("Pandan Coklat Keju", "Rp 28.000"))
            daftarMenu.add(MenuItem("Pandan Jagung Keju", "Rp 32.000"))

        } else if (kategoriDipilih == "TOPING_RED_VELVET") {
            tvKategoriTitle.text = "Toping - Red Velvet"
            daftarMenu.add(MenuItem("Red Velvet Oreo Cream Cheese", "Rp 35.000"))
            daftarMenu.add(MenuItem("Red Velvet Nutella", "Rp 38.000"))
        }

        // TAHAP AKHIR: Nyalakan mesin Adapter
        val adapterMenu = MenuAdapter(daftarMenu)

        // MENANGKAP KLIK DARI KOTAK MENU
        adapterMenu.onItemClick = { menuYangDiklik ->
            // Kita hanya pindah halaman JIKA pengguna sedang berada di halaman pemilihan adonan
            if (kategoriDipilih == "TERANGBULAN") {
                val intentBaru = Intent(this, MenuActivity::class.java)

                // Cek adonan mana yang diklik berdasarkan nama menunya
                if (menuYangDiklik.namaMenu.contains("Original")) {
                    intentBaru.putExtra("KATEGORI", "TOPING_ORIGINAL")
                } else if (menuYangDiklik.namaMenu.contains("Pandan")) {
                    intentBaru.putExtra("KATEGORI", "TOPING_PANDAN")
                } else if (menuYangDiklik.namaMenu.contains("Red Velvet")) {
                    intentBaru.putExtra("KATEGORI", "TOPING_RED_VELVET")
                }

                startActivity(intentBaru)
            }
        }

        // Letakkan di Rak (RecyclerView)
        rvMenu.adapter = adapterMenu
    }
}