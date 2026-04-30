package com.example.martabakdanterangbulan

object CartManager {
    // List pesanan keranjang saat ini
    val cartList = ArrayList<CartItem>()

    // List untuk menyimpan Riwayat Pesanan (ShopeeFood style)
    val orderHistoryList = ArrayList<OrderHistoryItem>()

    // --- VARIABEL UNTUK STATUS PESANAN (JANGAN DIHAPUS) ---
    var adaPesananAktif: Boolean = false
    var totalBayarAktif: String = "Rp 0"

    // Data dummy pelanggan
    var namaPelangganAktif: String = "Akmal"
    var alamatPelangganAktif: String = "Jl. Teknologi No. 1, Kota Surabaya"

    // Menyimpan daftar item yang sedang diproses saat ini
    val pesananAktifList = ArrayList<CartItem>()
    // ------------------------------------------------------

    // Fungsi untuk mengosongkan keranjang
    fun clearCart() {
        cartList.clear()
    }
}