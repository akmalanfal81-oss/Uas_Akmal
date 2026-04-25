package com.example.martabakdanterangbulan

object CartManager {
    // List ini akan menyimpan semua pesanan user
    val cartList = ArrayList<CartItem>()

    // Fungsi untuk mengosongkan keranjang setelah selesai transaksi
    fun clearCart() {
        cartList.clear()
    }
}