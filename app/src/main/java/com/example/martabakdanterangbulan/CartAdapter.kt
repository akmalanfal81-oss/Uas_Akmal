package com.example.martabakdanterangbulan

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.CheckBox
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class CartAdapter(
    private val listCart: ArrayList<CartItem>,
    private val onCartUpdated: () -> Unit // Mesin pelapor jika ceklis/jumlah/hapus berubah
) : RecyclerView.Adapter<CartAdapter.CartViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CartViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_cart, parent, false)
        return CartViewHolder(view)
    }

    override fun onBindViewHolder(holder: CartViewHolder, position: Int) {
        val item = listCart[position]
        holder.tvName.text = item.namaMenu
        holder.tvPrice.text = item.hargaMenu

        // Memasang Gambar & Jumlah
        holder.ivImage.setImageResource(item.gambarMenu)
        holder.tvJumlah.text = item.jumlah.toString()

        // Logika Ceklis
        holder.cbItem.setOnCheckedChangeListener(null)
        holder.cbItem.isChecked = item.isSelected
        holder.cbItem.setOnCheckedChangeListener { _, isChecked ->
            item.isSelected = isChecked
            onCartUpdated()
        }

        // Logika Tombol Plus
        holder.btnPlus.setOnClickListener {
            item.jumlah++
            holder.tvJumlah.text = item.jumlah.toString()
            onCartUpdated() // Hitung ulang harga
        }

        // Logika Tombol Minus
        holder.btnMinus.setOnClickListener {
            if (item.jumlah > 1) { // Mencegah pesanan jadi 0 via tombol minus
                item.jumlah--
                holder.tvJumlah.text = item.jumlah.toString()
                onCartUpdated() // Hitung ulang harga
            }
        }

        // --- LOGIKA: TOMBOL HAPUS ---
        holder.btnDelete.setOnClickListener {
            val currentPos = holder.adapterPosition
            if (currentPos != RecyclerView.NO_POSITION) {
                // 1. Hapus dari memori list
                listCart.removeAt(currentPos)
                // 2. Beritahu layar bahwa item ini hilang
                notifyItemRemoved(currentPos)
                notifyItemRangeChanged(currentPos, listCart.size)
                // 3. Hitung ulang total harga
                onCartUpdated()
            }
        }
    }

    override fun getItemCount(): Int {
        return listCart.size
    }

    class CartViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val cbItem: CheckBox = itemView.findViewById(R.id.cbItemCart)
        val tvName: TextView = itemView.findViewById(R.id.tvCartNama)
        val tvPrice: TextView = itemView.findViewById(R.id.tvCartHarga)

        val ivImage: ImageView = itemView.findViewById(R.id.ivCartImage)
        val tvJumlah: TextView = itemView.findViewById(R.id.tvCartJumlah)
        val btnPlus: Button = itemView.findViewById(R.id.btnPlus)
        val btnMinus: Button = itemView.findViewById(R.id.btnMinus)

        // Mengenalkan Tombol Hapus Baru
        val btnDelete: ImageButton = itemView.findViewById(R.id.btnDeleteCartItem)
    }
}