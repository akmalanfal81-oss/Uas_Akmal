package com.example.martabakdanterangbulan

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class MenuAdapter(private val listMenu: ArrayList<MenuItem>) : RecyclerView.Adapter<MenuAdapter.MenuViewHolder>() {

    var onItemClick: ((MenuItem) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MenuViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_menu, parent, false)
        return MenuViewHolder(view)
    }

    override fun onBindViewHolder(holder: MenuViewHolder, position: Int) {
        val menu = listMenu[position]
        holder.tvName.text = menu.namaMenu
        holder.tvPrice.text = menu.hargaMenu

        // BAGIAN BARU: Memasang gambar ke dalam kotak menu
        holder.ivImage.setImageResource(menu.gambarMenu)

        holder.itemView.setOnClickListener {
            onItemClick?.invoke(menu)
        }
    }

    override fun getItemCount(): Int {
        return listMenu.size
    }

    class MenuViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val tvName: TextView = itemView.findViewById(R.id.tvMenuName)
        val tvPrice: TextView = itemView.findViewById(R.id.tvMenuPrice)
        // BAGIAN BARU: Mengenalkan ImageView
        val ivImage: ImageView = itemView.findViewById(R.id.ivMenuImage)
    }
}