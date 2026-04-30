package com.example.martabakdanterangbulan

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class InfoActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_info)

        val btnBackInfo = findViewById<ImageButton>(R.id.btnBackInfo)
        btnBackInfo.setOnClickListener { finish() }

        val tvNama = findViewById<TextView>(R.id.tvInfoNamaMenu)
        val tvHarga = findViewById<TextView>(R.id.tvInfoHargaMenu)
        val tvDesc = findViewById<TextView>(R.id.tvInfoDeskripsi)
        val ivGambar = findViewById<ImageView>(R.id.ivInfoGambar)

        val namaMenu = intent.getStringExtra("NAMA_MENU") ?: ""
        val hargaMenu = intent.getStringExtra("HARGA_MENU") ?: ""

        val gambarMenu = intent.getIntExtra("GAMBAR_MENU", R.drawable.logo)
        ivGambar.setImageResource(gambarMenu)

        tvNama.text = namaMenu
        tvHarga.text = hargaMenu
        tvDesc.text = intent.getStringExtra("DESC_MENU")

        val btnTambahKeranjang = findViewById<Button>(R.id.btnTambahKeranjang)
        val btnBeliLangsung = findViewById<Button>(R.id.btnBeliLangsung)
        // PENGENALAN IKON KERANJANG BARU
        val btnCartInfo = findViewById<ImageButton>(R.id.btnCartInfo)

        btnTambahKeranjang.setOnClickListener {
            CartManager.cartList.add(CartItem(namaMenu, hargaMenu, gambarMenu))
            Toast.makeText(this, "$namaMenu berhasil masuk keranjang!", Toast.LENGTH_SHORT).show()
        }

        // KLIK IKON KERANJANG ATAS
        btnCartInfo.setOnClickListener {
            val intent = Intent(this, CartActivity::class.java)
            startActivity(intent)
        }

        btnBeliLangsung.setOnClickListener {
            val intent = Intent(this, PaymentActivity::class.java)
            intent.putExtra("TOTAL_BAYAR", hargaMenu)
            startActivity(intent)
        }
    }
}