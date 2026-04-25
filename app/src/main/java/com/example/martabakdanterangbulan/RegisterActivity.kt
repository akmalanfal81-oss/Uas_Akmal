package com.example.martabakdanterangbulan

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ImageButton
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class RegisterActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        val btnBackRegister = findViewById<ImageButton>(R.id.btnBackRegister)
        val etRegNama = findViewById<EditText>(R.id.etRegNama)
        val etRegEmail = findViewById<EditText>(R.id.etRegEmail)
        val etRegPassword = findViewById<EditText>(R.id.etRegPassword)
        val btnBuatAkun = findViewById<Button>(R.id.btnBuatAkun)

        // Tombol panah kembali ke halaman Login
        btnBackRegister.setOnClickListener {
            finish()
        }

        // Logika saat tombol daftar ditekan
        btnBuatAkun.setOnClickListener {
            val nama = etRegNama.text.toString().trim()
            val email = etRegEmail.text.toString().trim()
            val password = etRegPassword.text.toString().trim()

            if (nama.isEmpty() || email.isEmpty() || password.isEmpty()) {
                Toast.makeText(this, "Semua kolom wajib diisi!", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(this, "Akun $nama berhasil dibuat! Silakan masuk.", Toast.LENGTH_LONG).show()
                // Kembali ke halaman Login setelah sukses mendaftar
                finish()
            }
        }
    }
}