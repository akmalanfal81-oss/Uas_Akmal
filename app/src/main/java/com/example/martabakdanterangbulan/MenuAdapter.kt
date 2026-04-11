package com.example.martabakdanterangbulan

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class MenuAdapter(private val listMenu: ArrayList<MenuItem>) : RecyclerView.Adapter<MenuAdapter.MenuViewHolder>() {

    // 1. Memanggil cetakan XML (item_menu.xml)
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MenuViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_menu, parent, false)
        return MenuViewHolder(view)
    }

    // 2. Memasukkan data Nama dan Harga ke dalam cetakan
    override fun onBindViewHolder(holder: MenuViewHolder, position: Int) {
        val menu = listMenu[position]
        holder.tvName.text = menu.namaMenu
        holder.tvPrice.text = menu.hargaMenu
    }

    // 3. Menghitung ada berapa banyak kue (menu) yang mau dicetak
    override fun getItemCount(): Int {
        return listMenu.size
    }

    // 4. Mengenalkan ID dari item_menu.xml ke Kotlin
    class MenuViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val tvName: TextView = itemView.findViewById(R.id.tvMenuName)
        val tvPrice: TextView = itemView.findViewById(R.id.tvMenuPrice)
    }
}