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

        // EKSTRAK ANGKA MURNI DARI TAGIHAN (Contoh: "Rp 25.000" jadi "25000")
        val angkaTagihanMurni = totalBayar.replace(Regex("[^0-9]"), "")

        // --- FITUR BARU YANG SEBELUMNYA KETINGGALAN: PENGAWAS KETIKAN RUPIAH ---
        etNominalBayar.addTextChangedListener(object : TextWatcher {
            private var current = ""
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}

            override fun afterTextChanged(s: Editable?) {
                if (s.toString() != current) {
                    // Matikan pengawas sebentar
                    etNominalBayar.removeTextChangedListener(this)

                    // Bersihkan semua titik dan huruf yang ada
                    val cleanString = s.toString().replace(Regex("[^0-9]"), "")

                    if (cleanString.isNotEmpty()) {
                        // Format ulang jadi gaya Indonesia (ada titiknya)
                        val parsed = cleanString.toDouble()
                        val formatted = NumberFormat.getNumberInstance(Locale("id", "ID")).format(parsed)

                        current = formatted
                        etNominalBayar.setText(formatted)
                        etNominalBayar.setSelection(formatted.length) // Taruh kursor di paling kanan
                    } else {
                        current = ""
                        etNominalBayar.setText("")
                    }

                    // Hidupkan pengawas lagi
                    etNominalBayar.addTextChangedListener(this)
                }
            }
        })
        // ----------------------------------------------------------------------

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

                // Tambahkan harga realistis di atas gambar QR
                tvQrisPetunjuk.text = "Scan QRIS di bawah ini untuk membayar:\n$totalBayar"
            }
        }

        // LOGIKA BAYAR (CEK INPUTAN BANK)
        btnBayarSekarang.setOnClickListener {
            if (rgPaymentMethod.checkedRadioButtonId == -1) {
                Toast.makeText(this, "Pilih metode pembayaran dulu!", Toast.LENGTH_SHORT).show()
            } else if (rgPaymentMethod.checkedRadioButtonId == R.id.rbBank) {
                val inputRekening = etNoRekening.text.toString()

                // Bersihkan titik dari inputan nominal biar bisa dicocokkan dengan tagihan asli
                val inputNominalKotor = etNominalBayar.text.toString()
                val inputNominalBersih = inputNominalKotor.replace(Regex("[^0-9]"), "")

                if (rgBankList.checkedRadioButtonId == -1) {
                    Toast.makeText(this, "Silakan pilih Bank Tujuan (BCA/Mandiri/BRI)!", Toast.LENGTH_SHORT).show()
                } else if (inputRekening.isEmpty() || inputNominalKotor.isEmpty()) {
                    Toast.makeText(this, "Nomor Rekening dan Nominal wajib diisi!", Toast.LENGTH_SHORT).show()
                } else if (inputNominalBersih != angkaTagihanMurni) {
                    // Validasi jika nominal yang diinput tidak sama dengan tagihan asli
                    Toast.makeText(this, "Gagal! Nominal harus pas: Rp $angkaTagihanMurni", Toast.LENGTH_LONG).show()
                } else {
                    // Lolos semua, transaksi sukses!
                    val intent = Intent(this, TransactionActivity::class.java)
                    startActivity(intent)
                    finish()
                }
            }
        }

        // SIMULASI QRIS BERHASIL & GAGAL
        btnQrisBerhasil.setOnClickListener {
            val intent = Intent(this, TransactionActivity::class.java)
            startActivity(intent)
            finish()
        }

        btnQrisGagal.setOnClickListener {
            Toast.makeText(this, "Transaksi QRIS Gagal! Silakan coba scan ulang.", Toast.LENGTH_LONG).show()
        }
    }
}