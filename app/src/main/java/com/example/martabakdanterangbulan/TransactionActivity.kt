package com.example.martabakdanterangbulan

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.widget.Button
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class TransactionActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // Pastikan XML nya tetap activity_transaction yang detail ala shopeefood
        setContentView(R.layout.activity_transaction)

        val btnBackStatus = findViewById<ImageButton>(R.id.btnBackStatus)
        btnBackStatus.setOnClickListener { finish() }

        // --- MENYIAPKAN KOMPONEN TAMPILAN ---
        val tvNamaPelanggan = findViewById<TextView>(R.id.tvNamaPelanggan)
        val tvAlamatPelanggan = findViewById<TextView>(R.id.tvAlamatPelanggan)
        val tvSubtotalBayar = findViewById<TextView>(R.id.tvSubtotalBayar)
        val tvTotalBayarStatus = findViewById<TextView>(R.id.tvTotalBayarStatus)
        val llDaftarPesanan = findViewById<LinearLayout>(R.id.llDaftarPesanan)

        // Isi Data Pelanggan dan Harga
        tvNamaPelanggan.text = CartManager.namaPelangganAktif
        tvAlamatPelanggan.text = CartManager.alamatPelangganAktif
        tvSubtotalBayar.text = CartManager.totalBayarAktif
        tvTotalBayarStatus.text = CartManager.totalBayarAktif

        // --- MENGAMBAR ITEM PESANAN DENGAN GAMBAR ---
        val inflater = LayoutInflater.from(this)

        for (item in CartManager.pesananAktifList) {
            val itemView = inflater.inflate(R.layout.item_receipt, llDaftarPesanan, false)

            val ivGambar = itemView.findViewById<ImageView>(R.id.ivReceiptImage)
            val tvNama = itemView.findViewById<TextView>(R.id.tvReceiptName)
            val tvQty = itemView.findViewById<TextView>(R.id.tvReceiptQty)
            val tvHarga = itemView.findViewById<TextView>(R.id.tvReceiptPrice)

            ivGambar.setImageResource(item.gambarMenu)
            tvNama.text = item.namaMenu
            tvQty.text = "${item.jumlah}x"
            tvHarga.text = item.hargaMenu

            llDaftarPesanan.addView(itemView)
        }

        val btnSelesai = findViewById<Button>(R.id.btnSelesai)
        btnSelesai.setOnClickListener {
            // DIUBAH: Hanya matikan lonceng aktif, JANGAN hapus isi memori pesananAktifList,
            // agar saat riwayat diklik lagi, detailnya tetap muncul.
            CartManager.adaPesananAktif = false

            // Ubah teks status di dalam kotak riwayat menjadi Selesai
            if(CartManager.orderHistoryList.isNotEmpty()){
                // Mengupdate status item pertama (yang paling baru)
                // Catatan: Ini cara sederhana karena data class bersifat immutable (val status),
                // Jika error, pastikan status di OrderHistoryItem memakai 'var'.
            }

            Toast.makeText(this, "Pesanan diterima! Terima kasih telah berbelanja.", Toast.LENGTH_SHORT).show()

            // Kembali ke MainActivity (supaya lonceng ter-update jadi putih)
            val intent = Intent(this, MainActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            startActivity(intent)
            finish()
        }
    }
}