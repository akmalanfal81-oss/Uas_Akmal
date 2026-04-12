package com.example.martabakdanterangbulan

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

class LoginActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        val btnLogin = findViewById<Button>(R.id.btnLogin)

        // Jika tombol Masuk diklik, pindah ke Halaman Utama (MainActivity)
        btnLogin.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)

            // finish() digunakan agar saat pengguna menekan tombol 'Back' di HP,
            // mereka tidak kembali ke halaman login, melainkan keluar aplikasi.
            finish()
        }
    }
}