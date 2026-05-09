package com.example.martabakdanterangbulan

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.util.Log // <-- IMPORT LOG
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException

class LoginActivity : AppCompatActivity() {

    private lateinit var googleSignInClient: GoogleSignInClient

    private val googleSignInLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
        if (result.resultCode == Activity.RESULT_OK) {
            val task = GoogleSignIn.getSignedInAccountFromIntent(result.data)
            try {
                val account = task.getResult(ApiException::class.java)
                val emailGoogle = account?.email
                val namaGoogle = account?.displayName

                Toast.makeText(this, "Berhasil masuk sebagai $namaGoogle ($emailGoogle)", Toast.LENGTH_LONG).show()
                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
                finish()

            } catch (e: ApiException) {
                Toast.makeText(this, "Login Google dibatalkan atau gagal.", Toast.LENGTH_SHORT).show()
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        // ==========================================
        // DIPINDAHKAN KE SINI!
        // Log langsung tercetak otomatis tanpa nunggu tombol diklik!
        // ==========================================
        val nimSaya = "42430041"
        val namaSaya = "Akmal"
        Log.e("INFO_MAHASISWA", "====== HALO DOSEN! APLIKASI DIJALANKAN OLEH: $namaSaya (NIM: $nimSaya) ======")
        // ==========================================

        val etUsername = findViewById<EditText>(R.id.etUsername)
        val etPassword = findViewById<EditText>(R.id.etPassword)
        val btnLogin = findViewById<Button>(R.id.btnLogin)
        val tvDaftar = findViewById<TextView>(R.id.tvDaftar)
        val btnGoogleLogin = findViewById<Button>(R.id.btnGoogleLogin)

        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestEmail()
            .requestProfile()
            .build()
        googleSignInClient = GoogleSignIn.getClient(this, gso)

        val akunTerakhir = GoogleSignIn.getLastSignedInAccount(this)
        if (akunTerakhir != null) {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()
        }

        btnLogin.setOnClickListener {
            val email = etUsername.text.toString().trim()
            val password = etPassword.text.toString().trim()

            if (email.isEmpty()) {
                etUsername.error = "Email atau Username tidak boleh kosong!"
                etUsername.requestFocus()
            } else if (password.isEmpty()) {
                etPassword.error = "Password tidak boleh kosong!"
                etPassword.requestFocus()
            } else {
                Toast.makeText(this, "Berhasil masuk sebagai $email", Toast.LENGTH_SHORT).show()
                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
                finish()
            }
        }

        tvDaftar.setOnClickListener {
            val intent = Intent(this, RegisterActivity::class.java)
            startActivity(intent)
        }

        btnGoogleLogin.setOnClickListener {
            val signInIntent = googleSignInClient.signInIntent
            googleSignInLauncher.launch(signInIntent)
        }
    }
}