package com.example.martabakdanterangbulan

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class ProfileActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile)

        val btnSaveProfile = findViewById<Button>(R.id.btnSaveProfile)

        // Membaca inputan dari EditText
        val etName = findViewById<EditText>(R.id.etProfileName)
        val etPhone = findViewById<EditText>(R.id.etProfilePhone)

        btnSaveProfile.setOnClickListener {
            // Mengambil teks yang baru saja diketik pengguna
            val namaBaru = etName.text.toString()

            // Menampilkan pesan pop-up (Toast) di layar bawah
            Toast.makeText(this, "Data $namaBaru berhasil disimpan!", Toast.LENGTH_SHORT).show()

            // Kembali ke halaman sebelumnya (Halaman Utama)
            finish()
        }
    }
}