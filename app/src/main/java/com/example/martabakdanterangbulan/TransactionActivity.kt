package com.example.martabakdanterangbulan

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.ImageButton
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class TransactionActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_transaction)

        val btnBackStatus = findViewById<ImageButton>(R.id.btnBackStatus)
        btnBackStatus.setOnClickListener {
            kembaliKeHalamanUtama()
        }

        // --- MENAMPILKAN DATA PELANGGAN & PESANAN ---
        val tvNamaPelanggan = findViewById<TextView>(R.id.tvNamaPelanggan)
        val tvAlamatPelanggan = findViewById<TextView>(R.id.tvAlamatPelanggan)
        val tvDaftarPesanan = findViewById<TextView>(R.id.tvDaftarPesanan)
        val tvTotalBayarStatus = findViewById<TextView>(R.id.tvTotalBayarStatus)

        tvNamaPelanggan.text = CartManager.namaPelangganAktif
        tvAlamatPelanggan.text = CartManager.alamatPelangganAktif
        tvTotalBayarStatus.text = CartManager.totalBayarAktif

        // Membentuk susunan kalimat dari list pesanan (misal: "2x Martabak Telur Spesial")
        var teksPesanan = ""
        for (item in CartManager.pesananAktifList) {
            teksPesanan += "${item.jumlah}x ${item.namaMenu}\n"
        }
        tvDaftarPesanan.text = teksPesanan.trim()
        // ----------------------------------------------

        val btnSelesai = findViewById<Button>(R.id.btnSelesai)
        btnSelesai.setOnClickListener {
            // Selesaikan pesanan & bersihkan memori
            CartManager.adaPesananAktif = false
            CartManager.pesananAktifList.clear()
            CartManager.totalBayarAktif = "Rp 0"

            Toast.makeText(this, "Pesanan diterima! Terima kasih telah berbelanja.", Toast.LENGTH_SHORT).show()
            kembaliKeHalamanUtama()
        }
    }

    override fun onBackPressed() {
        kembaliKeHalamanUtama()
    }

    private fun kembaliKeHalamanUtama() {
        val intent = Intent(this, MainActivity::class.java)
        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        startActivity(intent)
        finish()
    }
}