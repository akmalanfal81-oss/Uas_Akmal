package com.example.martabakdanterangbulan

import android.content.Intent
import android.os.Bundle
import android.widget.ImageButton
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class HistoryActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_history)

        val btnBackHistory = findViewById<ImageButton>(R.id.btnBackHistory)
        btnBackHistory.setOnClickListener { finish() }

        val rvOrderHistory = findViewById<RecyclerView>(R.id.rvOrderHistory)
        rvOrderHistory.layoutManager = LinearLayoutManager(this)

        // Tampilkan data dari memori
        val adapter = OrderHistoryAdapter(CartManager.orderHistoryList)

        // JIKA KOTAK RIWAYAT DIKLIK -> BUKA STATUS DETAIL
        adapter.onItemClick = { clickedItem ->
            val intent = Intent(this, TransactionActivity::class.java)
            startActivity(intent)
        }

        // --- FUNGSI BARU: JIKA TOMBOL "PESAN LAGI" DIKLIK ---
        adapter.onReorderClick = { clickedItem ->
            // Masukkan data dari riwayat ke dalam Keranjang Belanja
            CartManager.cartList.add(
                CartItem(
                    namaMenu = clickedItem.deskripsiMenuUtama,
                    hargaMenu = clickedItem.totalHarga,
                    gambarMenu = clickedItem.gambarMenu,
                    jumlah = 1,
                    isSelected = true
                )
            )

            Toast.makeText(this, "Ditambahkan kembali ke keranjang!", Toast.LENGTH_SHORT).show()

            // Buka Halaman Keranjang
            val intent = Intent(this, CartActivity::class.java)
            startActivity(intent)
            finish() // Tutup halaman histori
        }
        // -----------------------------------------------------

        rvOrderHistory.adapter = adapter
    }
}