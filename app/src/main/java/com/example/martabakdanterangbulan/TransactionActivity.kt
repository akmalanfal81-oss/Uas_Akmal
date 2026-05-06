package com.example.martabakdanterangbulan

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import android.view.View
import android.widget.Button
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class TransactionActivity : AppCompatActivity() {

    // Mesin Pewaktu untuk Animasi Bertahap
    private val handler = Handler(Looper.getMainLooper())
    private lateinit var runnableDiantar: Runnable
    private lateinit var runnableTiba: Runnable

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_transaction)

        val btnBackStatus = findViewById<ImageButton>(R.id.btnBackStatus)
        btnBackStatus.setOnClickListener { finish() }

        // --- KOMPONEN TIMELINE ANIMASI ---
        val tvStatusTitle = findViewById<TextView>(R.id.tvStatusTitle)
        val tvStatusDesc = findViewById<TextView>(R.id.tvStatusDesc)

        val tvIconChef = findViewById<TextView>(R.id.tvIconChef)

        val viewLine2 = findViewById<View>(R.id.viewLine2)
        val tvIconBike = findViewById<TextView>(R.id.tvIconBike)

        val viewLine3 = findViewById<View>(R.id.viewLine3)
        val tvIconHome = findViewById<TextView>(R.id.tvIconHome)

        // --- KOMPONEN TAMPILAN DATA ---
        val tvNamaPelanggan = findViewById<TextView>(R.id.tvNamaPelanggan)
        val tvAlamatPelanggan = findViewById<TextView>(R.id.tvAlamatPelanggan)
        val tvSubtotalBayar = findViewById<TextView>(R.id.tvSubtotalBayar)
        val tvTotalBayarStatus = findViewById<TextView>(R.id.tvTotalBayarStatus)
        val llDaftarPesanan = findViewById<LinearLayout>(R.id.llDaftarPesanan)

        tvNamaPelanggan.text = CartManager.namaPelangganAktif
        tvAlamatPelanggan.text = CartManager.alamatPelangganAktif
        tvSubtotalBayar.text = CartManager.totalBayarAktif
        tvTotalBayarStatus.text = CartManager.totalBayarAktif

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

        // ==========================================
        // FITUR BARU: ANIMASI SIMULASI 3 TAHAP
        // ==========================================

        // TAHAP 1: Koki Memasak (Langsung Jalan)
        tvIconChef.animate().scaleX(1.2f).scaleY(1.2f).setDuration(500).withEndAction {
            tvIconChef.animate().scaleX(1.0f).scaleY(1.0f).setDuration(500).start()
        }.start()

        // TAHAP 2: Pesanan Sedang Diantar (Jalan di Detik ke-4)
        runnableDiantar = Runnable {
            tvStatusTitle.text = "Pesanan Sedang Diantar! 🛵"
            tvStatusDesc.text = "Driver sedang meluncur ke lokasimu."

            viewLine2.setBackgroundColor(Color.parseColor("#F57C00")) // Garis kedua Oranye
            tvIconBike.alpha = 1.0f // Nyalakan motor

            // Animasi Motor Melompat (Bouncing)
            tvIconBike.animate()
                .translationY(-30f).setDuration(300).withEndAction {
                    tvIconBike.animate().translationY(0f).setDuration(300).withEndAction {
                        tvIconBike.animate().translationY(-15f).setDuration(200).withEndAction {
                            tvIconBike.animate().translationY(0f).setDuration(200).start()
                        }.start()
                    }.start()
                }.start()
        }

        // TAHAP 3: Pesanan Telah Tiba (Jalan di Detik ke-8)
        runnableTiba = Runnable {
            tvStatusTitle.text = "Pesanan Telah Tiba! 🏠"
            tvStatusDesc.text = "Driver sudah sampai di titik tujuan."

            viewLine3.setBackgroundColor(Color.parseColor("#F57C00")) // Garis ketiga Oranye
            tvIconHome.alpha = 1.0f // Nyalakan rumah

            // Animasi Rumah Membesar dan mengecil (Pulse)
            tvIconHome.animate().scaleX(1.3f).scaleY(1.3f).setDuration(400).withEndAction {
                tvIconHome.animate().scaleX(1.0f).scaleY(1.0f).setDuration(400).start()
            }.start()
        }

        // Aktifkan waktu tunggu
        handler.postDelayed(runnableDiantar, 4000) // Detik ke 4
        handler.postDelayed(runnableTiba, 8000)    // Detik ke 8
        // ==========================================


        val btnSelesai = findViewById<Button>(R.id.btnSelesai)
        btnSelesai.setOnClickListener {
            CartManager.adaPesananAktif = false
            Toast.makeText(this, "Pesanan diterima! Terima kasih telah berbelanja.", Toast.LENGTH_SHORT).show()

            val intent = Intent(this, MainActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            startActivity(intent)
            finish()
        }
    }

    // Mencegah animasi terus berjalan di background jika user keluar aplikasi
    override fun onDestroy() {
        super.onDestroy()
        handler.removeCallbacks(runnableDiantar)
        handler.removeCallbacks(runnableTiba)
    }
}