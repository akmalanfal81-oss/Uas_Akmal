package com.example.martabakdanterangbulan

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.widget.Button
import android.widget.ImageButton
import android.widget.LinearLayout
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val btnMartabak = findViewById<Button>(R.id.btnKategoriMartabak)
        val btnTerangBulan = findViewById<Button>(R.id.btnKategoriTerangBulan)

        btnMartabak?.setOnClickListener {
            val intent = Intent(this, MenuActivity::class.java)
            intent.putExtra("KATEGORI", "MARTABAK")
            startActivity(intent)
        }

        btnTerangBulan?.setOnClickListener {
            val intent = Intent(this, MenuActivity::class.java)
            intent.putExtra("KATEGORI", "TERANGBULAN")
            startActivity(intent)
        }

        val btnProfile = findViewById<ImageButton>(R.id.btnProfile)
        btnProfile?.setOnClickListener {
            val intent = Intent(this, ProfileActivity::class.java)
            startActivity(intent)
        }

        val btnCart = findViewById<ImageButton>(R.id.btnCart)
        btnCart?.setOnClickListener {
            val intent = Intent(this, CartActivity::class.java)
            startActivity(intent)
        }

        val btnRek1 = findViewById<LinearLayout>(R.id.btnRek1)
        val btnRek2 = findViewById<LinearLayout>(R.id.btnRek2)
        val btnRek3 = findViewById<LinearLayout>(R.id.btnRek3)

        val descMartabak = "Martabak telur gurih dengan isian daging cincang, potongan daun bawang segar, dan bumbu rempah pilihan. Digoreng garing dengan balutan kulit lumpia renyah. Disajikan dengan kuah cuka (cuko) dan acar mentimun."
        val descTerbul = "Terang bulan manis bersarang sempurna. Terbuat dari adonan tepung premium, telur, dan mentega wangi. Disajikan dengan olesan mentega dan isian toping yang sangat melimpah."

        btnRek1?.setOnClickListener {
            val intent = Intent(this, InfoActivity::class.java)
            intent.putExtra("NAMA_MENU", "Martabak Telur Spesial + Keju")
            intent.putExtra("HARGA_MENU", "Rp 35.000")
            intent.putExtra("DESC_MENU", descMartabak)
            intent.putExtra("GAMBAR_MENU", R.drawable.logo)
            startActivity(intent)
        }

        btnRek2?.setOnClickListener {
            val intent = Intent(this, InfoActivity::class.java)
            intent.putExtra("NAMA_MENU", "Red Velvet Keju Susu")
            intent.putExtra("HARGA_MENU", "Rp 26.000")
            intent.putExtra("DESC_MENU", descTerbul)
            intent.putExtra("GAMBAR_MENU", R.drawable.logo)
            startActivity(intent)
        }

        btnRek3?.setOnClickListener {
            val intent = Intent(this, InfoActivity::class.java)
            intent.putExtra("NAMA_MENU", "Pandan Coklat Kacang Susu")
            intent.putExtra("HARGA_MENU", "Rp 22.000")
            intent.putExtra("DESC_MENU", descTerbul)
            intent.putExtra("GAMBAR_MENU", R.drawable.logo)
            startActivity(intent)
        }

        // PERBAIKAN KLIK TOMBOL LAPORAN
        val btnLaporan = findViewById<Button>(R.id.btnLaporan)
        btnLaporan.setOnClickListener {
            val intent = Intent(this, ReportActivity::class.java)
            startActivity(intent)
        }
    }

    override fun onResume() {
        super.onResume()
        val btnNotification = findViewById<ImageButton>(R.id.btnNotification)

        if (CartManager.adaPesananAktif) {
            btnNotification?.setColorFilter(Color.parseColor("#F57C00"))
        } else {
            btnNotification?.setColorFilter(Color.parseColor("#FFFFFF"))
        }

        btnNotification?.setOnClickListener {
            val intent = Intent(this, HistoryActivity::class.java)
            startActivity(intent)
        }
    }
}