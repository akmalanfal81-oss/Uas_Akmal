package com.example.martabakdanterangbulan

import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.ImageButton
import android.widget.LinearLayout
import android.widget.RadioGroup
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import java.text.NumberFormat
import java.util.Locale

class PaymentActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_payment)

        val btnBackPayment = findViewById<ImageButton>(R.id.btnBackPayment)
        btnBackPayment.setOnClickListener { finish() }

        // Menerima total harga
        val totalBayar = intent.getStringExtra("TOTAL_BAYAR") ?: "Rp 0"
        val tvPaymentTotal = findViewById<TextView>(R.id.tvPaymentTotal)
        tvPaymentTotal.text = totalBayar

        // Variabel elemen
        val rgPaymentMethod = findViewById<RadioGroup>(R.id.rgPaymentMethod)
        val layoutBankDetails = findViewById<LinearLayout>(R.id.layoutBankDetails)
        val layoutQrisDetails = findViewById<LinearLayout>(R.id.layoutQrisDetails)
        val rgBankList = findViewById<RadioGroup>(R.id.rgBankList)
        val etNoRekening = findViewById<EditText>(R.id.etNoRekening)
        val etNominalBayar = findViewById<EditText>(R.id.etNominalBayar)
        val tvQrisPetunjuk = findViewById<TextView>(R.id.tvQrisPetunjuk)

        val btnBayarSekarang = findViewById<Button>(R.id.btnBayarSekarang)
        val btnQrisBerhasil = findViewById<Button>(R.id.btnQrisBerhasil)
        val btnQrisGagal = findViewById<Button>(R.id.btnQrisGagal)

        val angkaTagihanMurni = totalBayar.replace(Regex("[^0-9]"), "")

        // FITUR PENGAWAS KETIKAN RUPIAH
        etNominalBayar.addTextChangedListener(object : TextWatcher {
            private var current = ""
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}

            override fun afterTextChanged(s: Editable?) {
                if (s.toString() != current) {
                    etNominalBayar.removeTextChangedListener(this)
                    val cleanString = s.toString().replace(Regex("[^0-9]"), "")
                    if (cleanString.isNotEmpty()) {
                        val parsed = cleanString.toDouble()
                        val formatted = NumberFormat.getNumberInstance(Locale("id", "ID")).format(parsed)
                        current = formatted
                        etNominalBayar.setText(formatted)
                        etNominalBayar.setSelection(formatted.length)
                    } else {
                        current = ""
                        etNominalBayar.setText("")
                    }
                    etNominalBayar.addTextChangedListener(this)
                }
            }
        })

        // LOGIKA TAMPILAN METODE
        rgPaymentMethod.setOnCheckedChangeListener { _, checkedId ->
            if (checkedId == R.id.rbBank) {
                layoutBankDetails.visibility = View.VISIBLE
                layoutQrisDetails.visibility = View.GONE
                btnBayarSekarang.visibility = View.VISIBLE
            } else if (checkedId == R.id.rbQris) {
                layoutBankDetails.visibility = View.GONE
                layoutQrisDetails.visibility = View.VISIBLE
                btnBayarSekarang.visibility = View.GONE
                tvQrisPetunjuk.text = "Scan QRIS di bawah ini untuk membayar:\n$totalBayar"
            }
        }

        // --- FUNGSI BARU: PROSES DATA PESANAN SEBELUM PINDAH HALAMAN ---
        fun prosesPesananSelesai() {
            // Ambil barang yang di-ceklis dari keranjang
            val pesananTerpilih = CartManager.cartList.filter { it.isSelected }

            CartManager.pesananAktifList.clear()
            if (pesananTerpilih.isNotEmpty()) {
                CartManager.pesananAktifList.addAll(pesananTerpilih)
            } else {
                // Jika dari tombol "Beli Langsung" (Keranjang kosong)
                CartManager.pesananAktifList.add(CartItem("Pesanan Langsung", totalBayar, R.drawable.logo, 1, true))
            }

            CartManager.clearCart() // Kosongkan keranjang asli
            CartManager.adaPesananAktif = true
            CartManager.totalBayarAktif = totalBayar

            val intent = Intent(this, MainActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            startActivity(intent)
            finish()
        }

        // LOGIKA BAYAR BANK
        btnBayarSekarang.setOnClickListener {
            if (rgPaymentMethod.checkedRadioButtonId == -1) {
                Toast.makeText(this, "Pilih metode pembayaran dulu!", Toast.LENGTH_SHORT).show()
            } else if (rgPaymentMethod.checkedRadioButtonId == R.id.rbBank) {
                val inputRekening = etNoRekening.text.toString()
                val inputNominalKotor = etNominalBayar.text.toString()
                val inputNominalBersih = inputNominalKotor.replace(Regex("[^0-9]"), "")

                if (rgBankList.checkedRadioButtonId == -1) {
                    Toast.makeText(this, "Silakan pilih Bank Tujuan!", Toast.LENGTH_SHORT).show()
                } else if (inputRekening.isEmpty() || inputNominalKotor.isEmpty()) {
                    Toast.makeText(this, "Nomor Rekening dan Nominal wajib diisi!", Toast.LENGTH_SHORT).show()
                } else if (inputNominalBersih != angkaTagihanMurni) {
                    Toast.makeText(this, "Gagal! Nominal harus pas: Rp $angkaTagihanMurni", Toast.LENGTH_LONG).show()
                } else {
                    prosesPesananSelesai() // Panggil fungsi di atas
                }
            }
        }

        // SIMULASI QRIS BERHASIL
        btnQrisBerhasil.setOnClickListener {
            prosesPesananSelesai() // Panggil fungsi di atas
        }

        btnQrisGagal.setOnClickListener {
            Toast.makeText(this, "Transaksi QRIS Gagal! Silakan coba scan ulang.", Toast.LENGTH_LONG).show()
        }
    }
}