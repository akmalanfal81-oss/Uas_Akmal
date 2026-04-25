package com.example.martabakdanterangbulan

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class CartAdapter(
    private val listCart: ArrayList<CartItem>,
    private val onCartUpdated: () -> Unit // Mesin pelapor jika ceklis berubah
) : RecyclerView.Adapter<CartAdapter.CartViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CartViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_cart, parent, false)
        return CartViewHolder(view)
    }

    override fun onBindViewHolder(holder: CartViewHolder, position: Int) {
        val item = listCart[position]
        holder.tvName.text = item.namaMenu
        holder.tvPrice.text = item.hargaMenu

        // Atur status awal ceklis agar tidak kacau saat di-scroll
        holder.cbItem.setOnCheckedChangeListener(null)
        holder.cbItem.isChecked = item.isSelected

        // Jika kotak ceklis ditekan oleh pengguna
        holder.cbItem.setOnCheckedChangeListener { _, isChecked ->
            item.isSelected = isChecked // Simpan status ceklisnya
            onCartUpdated() // Lapor ke CartActivity untuk hitung ulang harga!
        }
    }

    override fun getItemCount(): Int {
        return listCart.size
    }

    class CartViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val cbItem: CheckBox = itemView.findViewById(R.id.cbItemCart) // ID Ceklis
        val tvName: TextView = itemView.findViewById(R.id.tvCartNama)
        val tvPrice: TextView = itemView.findViewById(R.id.tvCartHarga)
    }
}