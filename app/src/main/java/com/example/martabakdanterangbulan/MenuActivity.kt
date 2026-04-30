package com.example.martabakdanterangbulan

import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import java.util.Locale

class MenuActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_menu)

        val tvKategoriTitle = findViewById<TextView>(R.id.tvKategoriTitle)
        val rvMenu = findViewById<RecyclerView>(R.id.rvMenu)
        val etSearchMenu = findViewById<EditText>(R.id.etSearchMenu) // Tarik EditText Pencarian

        // Menemukan tombol kembali dan memberinya perintah 'finish'
        val btnBackMenu = findViewById<android.widget.ImageButton>(R.id.btnBackMenu)
        btnBackMenu.setOnClickListener {
            finish() // Menutup halaman Menu dan kembali ke halaman sebelumnya
        }

        // Mengatur agar daftar menu memanjang ke bawah (vertikal)
        rvMenu.layoutManager = LinearLayoutManager(this)

        // Menangkap pesan Intent
        val kategoriDipilih = intent.getStringExtra("KATEGORI")

        // Menyiapkan keranjang kosong menggunakan struktur MenuItem
        val daftarMenu = ArrayList<MenuItem>()

        // Mengisi keranjang sesuai tombol atau menu yang diklik
        if (kategoriDipilih == "MARTABAK") {
            tvKategoriTitle.text = "Daftar Menu Martabak"
            val img = R.drawable.martabak_telur // Gambar Martabak

            daftarMenu.add(MenuItem("Martabak Telur Biasa", "Rp 25.000", img))
            daftarMenu.add(MenuItem("Martabak Telur Biasa + Keju", "Rp 30.000", img))
            daftarMenu.add(MenuItem("Martabak Telur Spesial", "Rp 32.000", img))
            daftarMenu.add(MenuItem("Martabak Telur Spesial + Keju", "Rp 35.000", img))
            daftarMenu.add(MenuItem("Martabak Telur Istimewa", "Rp 40.000", img))
            daftarMenu.add(MenuItem("Martabak Telur Istimewa + Keju", "Rp 45.000", img))
            daftarMenu.add(MenuItem("Martabak Telur Super", "Rp 50.000", img))
            daftarMenu.add(MenuItem("Martabak Telur Super + Keju", "Rp 55.000", img))

        } else if (kategoriDipilih == "TERANGBULAN") {
            tvKategoriTitle.text = "Pilih Adonan Terang Bulan"
            daftarMenu.add(MenuItem("1. Terang Bulan Original", "Ketuk untuk pilih toping", R.drawable.terang_bulan_original))
            daftarMenu.add(MenuItem("2. Terang Bulan Pandan", "Ketuk untuk pilih toping", R.drawable.terang_bulan_pandan))
            daftarMenu.add(MenuItem("3. Terang Bulan Red Velvet", "Ketuk untuk pilih toping", R.drawable.terang_bulan_red_velvet))

        } else if (kategoriDipilih == "TOPING_ORIGINAL") {
            tvKategoriTitle.text = "Toping - Adonan Original"
            val img = R.drawable.terang_bulan_original // Gambar Terbul Ori

            daftarMenu.add(MenuItem("Original Coklat Susu", "Rp 16.000", img))
            daftarMenu.add(MenuItem("Original Kacang Susu", "Rp 18.000", img))
            daftarMenu.add(MenuItem("Original Coklat Kacang Susu", "Rp 20.000", img))
            daftarMenu.add(MenuItem("Original Keju Susu", "Rp 22.000", img))
            daftarMenu.add(MenuItem("Original Coklat Keju Susu", "Rp 24.000", img))
            daftarMenu.add(MenuItem("Original Kacang Keju Susu", "Rp 25.000", img))
            daftarMenu.add(MenuItem("Original Coklat Kacang Keju Susu", "Rp 27.000", img))
            daftarMenu.add(MenuItem("Original Pisang Susu", "Rp 23.000", img))
            daftarMenu.add(MenuItem("Original Pisang Keju Susu", "Rp 26.000", img))
            daftarMenu.add(MenuItem("Original Pisang Coklat Susu", "Rp 24.000", img))
            daftarMenu.add(MenuItem("Original Pisang Kacang Susu", "Rp 25.000", img))
            daftarMenu.add(MenuItem("Original Pisang Coklat Kacang Susu", "Rp 26.000", img))
            daftarMenu.add(MenuItem("Original Pisang Keju Kacang Susu", "Rp 30.000", img))
            daftarMenu.add(MenuItem("Original Pisang Coklat Keju Susu", "Rp 28.000", img))
            daftarMenu.add(MenuItem("Original Pisang Coklat Kacang Keju Susu", "Rp 32.000", img))
            daftarMenu.add(MenuItem("Original Double Keju Susu", "Rp 33.000", img))
            daftarMenu.add(MenuItem("Original Double Keju Coklat Susu", "Rp 35.000", img))
            daftarMenu.add(MenuItem("Original Double Keju Kacang Susu", "Rp 36.000", img))
            daftarMenu.add(MenuItem("Original Double Keju Coklat Kacang Susu", "Rp 38.000", img))
            daftarMenu.add(MenuItem("Original Komplit", "Rp 42.000", img))

        } else if (kategoriDipilih == "TOPING_PANDAN") {
            tvKategoriTitle.text = "Toping - Adonan Pandan"
            val img = R.drawable.terang_bulan_pandan // Gambar Terbul Pandan

            daftarMenu.add(MenuItem("Pandan Coklat Susu", "Rp 18.000", img))
            daftarMenu.add(MenuItem("Pandan Kacang Susu", "Rp 20.000", img))
            daftarMenu.add(MenuItem("Pandan Coklat Kacang Susu", "Rp 22.000", img))
            daftarMenu.add(MenuItem("Pandan Keju Susu", "Rp 24.000", img))
            daftarMenu.add(MenuItem("Pandan Coklat Keju Susu", "Rp 26.000", img))
            daftarMenu.add(MenuItem("Pandan Kacang Keju Susu", "Rp 27.000", img))
            daftarMenu.add(MenuItem("Pandan Coklat Kacang Keju Susu", "Rp 29.000", img))
            daftarMenu.add(MenuItem("Pandan Pisang Susu", "Rp 25.000", img))
            daftarMenu.add(MenuItem("Pandan Pisang Keju Susu", "Rp 28.000", img))
            daftarMenu.add(MenuItem("Pandan Pisang Coklat Susu", "Rp 26.000", img))
            daftarMenu.add(MenuItem("Pandan Pisang Kacang Susu", "Rp 27.000", img))
            daftarMenu.add(MenuItem("Pandan Pisang Coklat Kacang Susu", "Rp 28.000", img))
            daftarMenu.add(MenuItem("Pandan Pisang Keju Kacang Susu", "Rp 32.000", img))
            daftarMenu.add(MenuItem("Pandan Pisang Coklat Keju Susu", "Rp 30.000", img))
            daftarMenu.add(MenuItem("Pandan Pisang Coklat Kacang Keju Susu", "Rp 34.000", img))
            daftarMenu.add(MenuItem("Pandan Double Keju Susu", "Rp 35.000", img))
            daftarMenu.add(MenuItem("Pandan Double Keju Coklat Susu", "Rp 37.000", img))
            daftarMenu.add(MenuItem("Pandan Double Keju Kacang Susu", "Rp 38.000", img))
            daftarMenu.add(MenuItem("Pandan Double Keju Coklat Kacang Susu", "Rp 40.000", img))
            daftarMenu.add(MenuItem("Pandan Komplit", "Rp 44.000", img))

        } else if (kategoriDipilih == "TOPING_RED_VELVET") {
            tvKategoriTitle.text = "Toping - Adonan Red Velvet"
            val img = R.drawable.terang_bulan_red_velvet // Gambar Terbul Red Velvet

            daftarMenu.add(MenuItem("Red Velvet Coklat Susu", "Rp 20.000", img))
            daftarMenu.add(MenuItem("Red Velvet Kacang Susu", "Rp 22.000", img))
            daftarMenu.add(MenuItem("Red Velvet Coklat Kacang Susu", "Rp 24.000", img))
            daftarMenu.add(MenuItem("Red Velvet Keju Susu", "Rp 26.000", img))
            daftarMenu.add(MenuItem("Red Velvet Coklat Keju Susu", "Rp 30.000", img))
            daftarMenu.add(MenuItem("Red Velvet Kacang Keju Susu", "Rp 29.000", img))
            daftarMenu.add(MenuItem("Red Velvet Coklat Kacang Keju Susu", "Rp 31.000", img))
            daftarMenu.add(MenuItem("Red Velvet Pisang Susu", "Rp 27.000", img))
            daftarMenu.add(MenuItem("Red Velvet Pisang Keju Susu", "Rp 30.000", img))
            daftarMenu.add(MenuItem("Red Velvet Pisang Coklat Susu", "Rp 28.000", img))
            daftarMenu.add(MenuItem("Red Velvet Pisang Kacang Susu", "Rp 29.000", img))
            daftarMenu.add(MenuItem("Red Velvet Pisang Coklat Kacang Susu", "Rp 30.000", img))
            daftarMenu.add(MenuItem("Red Velvet Pisang Keju Kacang Susu", "Rp 34.000", img))
            daftarMenu.add(MenuItem("Red Velvet Pisang Coklat Keju Susu", "Rp 32.000", img))
            daftarMenu.add(MenuItem("Red Velvet Pisang Coklat Kacang Keju Susu", "Rp 36.000", img))
            daftarMenu.add(MenuItem("Red Velvet Double Keju Susu", "Rp 37.000", img))
            daftarMenu.add(MenuItem("Red Velvet Double Keju Coklat Susu", "Rp 39.000", img))
            daftarMenu.add(MenuItem("Red Velvet Double Keju Kacang Susu", "Rp 40.000", img))
            daftarMenu.add(MenuItem("Red Velvet Double Keju Coklat Kacang Susu", "Rp 42.000", img))
            daftarMenu.add(MenuItem("Red Velvet Komplit", "Rp 46.000", img))
        }

        // TAHAP SEBELUM ADAPTER: Masukkan deskripsi otomatis ke semua menu
        val descMartabak = "Martabak telur gurih dengan isian daging cincang, potongan daun bawang segar, dan bumbu rempah pilihan. Digoreng garing dengan balutan kulit lumpia renyah. Disajikan dengan kuah cuka (cuko) dan acar mentimun."
        val descTerbul = "Terang bulan manis bersarang sempurna. Terbuat dari adonan tepung premium, telur, dan mentega wangi. Disajikan dengan olesan mentega dan isian toping yang sangat melimpah."

        for (kue in daftarMenu) {
            if (kategoriDipilih == "MARTABAK") {
                kue.deskripsiMenu = descMartabak
            } else if (kategoriDipilih != "TERANGBULAN") { // Ini berarti pilihan Toping akhir
                kue.deskripsiMenu = descTerbul
            }
        }

        // Nyalakan mesin Adapter
        val adapterMenu = MenuAdapter(daftarMenu)

        // MENANGKAP KLIK DARI KOTAK MENU
        adapterMenu.onItemClick = { menuYangDiklik ->
            if (kategoriDipilih == "TERANGBULAN") {
                // Pindah ke pilihan toping (ini tetap jalan seperti biasa)
                val intentBaru = Intent(this, MenuActivity::class.java)
                if (menuYangDiklik.namaMenu.contains("Original")) {
                    intentBaru.putExtra("KATEGORI", "TOPING_ORIGINAL")
                } else if (menuYangDiklik.namaMenu.contains("Pandan")) {
                    intentBaru.putExtra("KATEGORI", "TOPING_PANDAN")
                } else if (menuYangDiklik.namaMenu.contains("Red Velvet")) {
                    intentBaru.putExtra("KATEGORI", "TOPING_RED_VELVET")
                }
                startActivity(intentBaru)
            } else {
                // BUKA HALAMAN INFO PRODUK KARENA MENU AKHIR DIKLIK
                val intentInfo = Intent(this, InfoActivity::class.java)
                intentInfo.putExtra("NAMA_MENU", menuYangDiklik.namaMenu)
                intentInfo.putExtra("HARGA_MENU", menuYangDiklik.hargaMenu)
                intentInfo.putExtra("DESC_MENU", menuYangDiklik.deskripsiMenu)
                intentInfo.putExtra("GAMBAR_MENU", menuYangDiklik.gambarMenu) // <-- KIRIM GAMBAR KE INFO
                startActivity(intentInfo)
            }
        }

        // Letakkan di Rak (RecyclerView)
        rvMenu.adapter = adapterMenu

        // --- FITUR BARU: LOGIKA PENCARIAN (SEARCH) ---
        etSearchMenu.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}

            override fun afterTextChanged(s: Editable?) {
                val textToSearch = s.toString().lowercase(Locale.getDefault())
                val filteredList = ArrayList<MenuItem>()

                // Mencocokkan ketikan dengan daftar menu asli
                for (menu in daftarMenu) {
                    if (menu.namaMenu.lowercase(Locale.getDefault()).contains(textToSearch)) {
                        filteredList.add(menu)
                    }
                }
                // Update tampilan list
                adapterMenu.updateList(filteredList)
            }
        })
    }
}