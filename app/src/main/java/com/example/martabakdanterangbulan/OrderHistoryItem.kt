package com.example.martabakdanterangbulan

data class OrderHistoryItem(
    val namaToko: String,
    val deskripsiMenuUtama: String,
    val tanggal: String,
    val totalHarga: String,
    val jumlahMenu: Int,
    val gambarMenu: Int,
    val status: String
)