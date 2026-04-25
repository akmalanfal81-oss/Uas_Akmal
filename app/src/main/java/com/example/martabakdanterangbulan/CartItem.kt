package com.example.martabakdanterangbulan

data class CartItem(
    val namaMenu: String,
    val hargaMenu: String,
    var jumlah: Int = 1,
    var isSelected: Boolean = true
)