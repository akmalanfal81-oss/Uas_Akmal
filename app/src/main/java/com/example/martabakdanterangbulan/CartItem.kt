package com.example.martabakdanterangbulan

data class CartItem(
    val namaMenu: String,
    val hargaMenu: String,
    val gambarMenu: Int,
    var jumlah: Int = 1,
    var isSelected: Boolean = true
)