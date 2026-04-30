package com.example.martabakdanterangbulan

object CartManager {
    // List ini akan menyimpan semua pesanan user di keranjang
    val cartList = ArrayList<CartItem>()

    // --- VARIABEL BARU UNTUK STATUS PESANAN ---
    var adaPesananAktif: Boolean = false
    var totalBayarAktif: String = "Rp 0"

    // Data dummy pelanggan (Bisa diganti nanti jika ada fitur Login/Database asli)
    var namaPelangganAktif: String = "Akmal"
    var alamatPelangganAktif: String = "Jl. Teknologi No. 1, Kota Surabaya"

    // Menyimpan daftar item yang sudah dibayar
    val pesananAktifList = ArrayList<CartItem>()

    // Fungsi untuk mengosongkan keranjang setelah selesai transaksi
    fun clearCart() {
        cartList.clear()
    }
}