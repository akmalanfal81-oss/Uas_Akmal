package com.example.martabakdanterangbulan

// Tambahkan "gambarMenu" berupa Int (karena ID resource di Android dibaca sebagai angka/Int)
data class MenuItem(
    val namaMenu: String,
    val hargaMenu: String,
    val gambarMenu: Int,
    var deskripsiMenu: String = ""
)