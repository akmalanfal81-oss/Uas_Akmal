package com.example.martabakdanterangbulan

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.ImageButton
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import java.text.NumberFormat
import java.util.Locale

class CartActivity : AppCompatActivity() {

    private lateinit var tvTotalHarga: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cart)

        val btnBackCart = findViewById<ImageButton>(R.id.btnBackCart)
        btnBackCart.setOnClickListener { finish() }

        tvTotalHarga = findViewById(R.id.tvTotalHarga)

        val rvCart = findViewById<RecyclerView>(R.id.rvCart)
        rvCart.layoutManager = LinearLayoutManager(this)

        // Memasang adapter dan fungsi hitung ulang otomatis
        rvCart.adapter = CartAdapter(CartManager.cartList) {
            hitungTotalTagihan()
        }

        // Hitung harga saat halaman pertama kali dibuka
        hitungTotalTagihan()

        val btnCheckout = findViewById<Button>(R.id.btnCheckout)
        btnCheckout.setOnClickListener {
            // Mengecek apakah ada setidaknya 1 menu yang diceklis
            val adaYangDipilih = CartManager.cartList.any { it.isSelected }

            if (CartManager.cartList.isEmpty()) {
                Toast.makeText(this, "Keranjang masih kosong!", Toast.LENGTH_SHORT).show()
            } else if (!adaYangDipilih) {
                // Notifikasi jika user lupa menceklis menunya
                Toast.makeText(this, "Pilih minimal 1 menu untuk dibayar!", Toast.LENGTH_SHORT).show()
            } else {
                val intent = Intent(this, PaymentActivity::class.java)
                intent.putExtra("TOTAL_BAYAR", tvTotalHarga.text.toString())
                startActivity(intent)
            }
        }
    }

    // FUNGSI KALKULATOR OTOMATIS
    private fun hitungTotalTagihan() {
        var total = 0

        for (item in CartManager.cartList) {
            // HANYA MENGHITUNG MENU YANG DICEKLIS SAJA
            if (item.isSelected) {
                val hargaAngka = item.hargaMenu.replace(Regex("[^0-9]"), "").toIntOrNull() ?: 0
                total += hargaAngka
            }
        }

        if (total > 0) {
            val formatRupiah = NumberFormat.getNumberInstance(Locale("in", "ID"))
            val totalDenganTitik = formatRupiah.format(total)
            tvTotalHarga.text = "Rp $totalDenganTitik"
        } else {
            // Jika tidak ada yang diceklis, harganya kembali jadi 0
            tvTotalHarga.text = "Rp 0"
        }
    }
}