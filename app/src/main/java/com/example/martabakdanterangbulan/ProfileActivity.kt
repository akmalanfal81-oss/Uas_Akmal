package com.example.martabakdanterangbulan

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity

class ProfileActivity : AppCompatActivity() {

    // Variabel untuk menampung gambar yang dipilih
    private lateinit var ivProfilePicture: ImageView

    // 'Mesin Penangkap Gambar' dari galeri
    private val pickImageLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
        if (result.resultCode == Activity.RESULT_OK && result.data != null) {
            val selectedImageUri: Uri? = result.data?.data
            if (selectedImageUri != null) {
                // Jika gambar berhasil dipilih, pasang ke ImageView
                ivProfilePicture.setImageURI(selectedImageUri)

                // Matikan warna putih (tint) bawaan agar fotonya asli
                ivProfilePicture.imageTintList = null
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile)

        val btnSaveProfile = findViewById<Button>(R.id.btnSaveProfile)
        val etName = findViewById<EditText>(R.id.etProfileName)

        // Hubungkan variabel ImageView dengan XML
        ivProfilePicture = findViewById(R.id.ivProfilePicture)
        val tvChangePhoto = findViewById<TextView>(R.id.tvChangePhoto)

        // Fungsi membuka galeri
        val openGalleryAction = {
            val intent = Intent(Intent.ACTION_PICK)
            intent.type = "image/*"
            pickImageLauncher.launch(intent)
        }

        // Panggil fungsi saat gambar atau tulisan ditekan
        ivProfilePicture.setOnClickListener { openGalleryAction() }
        tvChangePhoto.setOnClickListener { openGalleryAction() }

        // Menemukan tombol kembali dan memberinya perintah 'finish'
        val btnBackProfile = findViewById<android.widget.ImageButton>(R.id.btnBackProfile)
        btnBackProfile.setOnClickListener {
            finish() // Menutup halaman Profil dan kembali ke halaman sebelumnya
        }

        // Logika simpan profil
        btnSaveProfile.setOnClickListener {
            val namaBaru = etName.text.toString()
            Toast.makeText(this, "Data $namaBaru berhasil disimpan!", Toast.LENGTH_SHORT).show()
            finish()
        }
    }
}