package com.example.martabakdanterangbulan

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

class TransactionActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_transaction)

        // Setelah transaksi, keranjang harus dikosongkan
        CartManager.clearCart()

        val btnSelesai = findViewById<Button>(R.id.btnSelesai)
        btnSelesai.setOnClickListener {
            // Kembali ke MainActivity dan menutup semua riwayat halaman sebelumnya
            val intent = Intent(this, MainActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            startActivity(intent)
        }
    }
}