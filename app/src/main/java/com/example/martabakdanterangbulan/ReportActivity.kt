package com.example.martabakdanterangbulan

import android.os.Bundle
import android.widget.ImageButton
import androidx.appcompat.app.AppCompatActivity

class ReportActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_report)

        // Logika sederhana untuk tombol kembali
        val btnBackReport = findViewById<ImageButton>(R.id.btnBackReport)
        btnBackReport.setOnClickListener {
            finish()
        }
    }
}