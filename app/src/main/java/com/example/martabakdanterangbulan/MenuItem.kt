package com.example.martabakdanterangbulan

// Ini adalah struktur adonan datanya
data class MenuItem(
    val namaMenu: String,
    val hargaMenu: String,
    var deskripsiMenu: String = "" // Tambahan untuk menyimpan info bahan
)