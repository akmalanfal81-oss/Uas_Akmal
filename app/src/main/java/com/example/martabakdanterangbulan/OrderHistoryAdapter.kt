package com.example.martabakdanterangbulan

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class OrderHistoryAdapter(private val historyList: ArrayList<OrderHistoryItem>) : RecyclerView.Adapter<OrderHistoryAdapter.HistoryViewHolder>() {

    // Untuk klik seluruh kotak (Buka Status)
    var onItemClick: ((OrderHistoryItem) -> Unit)? = null

    // FUNGSI BARU: Untuk klik tombol "Pesan Lagi" saja
    var onReorderClick: ((OrderHistoryItem) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HistoryViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_order_history, parent, false)
        return HistoryViewHolder(view)
    }

    override fun onBindViewHolder(holder: HistoryViewHolder, position: Int) {
        val item = historyList[position]
        holder.tvDate.text = item.tanggal
        holder.ivImage.setImageResource(item.gambarMenu)
        holder.tvMenuName.text = item.deskripsiMenuUtama
        holder.tvPrice.text = item.totalHarga
        holder.tvItemsCount.text = "${item.jumlahMenu} menu >"

        holder.tvStatus.text = item.status
        if (item.status == "Dibatalkan") {
            holder.tvStatus.setTextColor(android.graphics.Color.parseColor("#E53935")) // Merah
        } else {
            holder.tvStatus.setTextColor(android.graphics.Color.parseColor("#AAAAAA")) // Abu-abu
        }

        // Klik seluruh kotak
        holder.itemView.setOnClickListener {
            onItemClick?.invoke(item)
        }

        // FUNGSI BARU: Klik tombol "Pesan Lagi"
        holder.btnPesanLagi.setOnClickListener {
            onReorderClick?.invoke(item)
        }
    }

    override fun getItemCount(): Int {
        return historyList.size
    }

    class HistoryViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val tvDate: TextView = itemView.findViewById(R.id.tvHistoryDate)
        val ivImage: ImageView = itemView.findViewById(R.id.ivHistoryImage)
        val tvMenuName: TextView = itemView.findViewById(R.id.tvHistoryMenuName)
        val tvPrice: TextView = itemView.findViewById(R.id.tvHistoryPrice)
        val tvItemsCount: TextView = itemView.findViewById(R.id.tvHistoryItemsCount)
        val tvStatus: TextView = itemView.findViewById(R.id.tvHistoryStatus)
        // Kenalkan tombol Pesan Lagi
        val btnPesanLagi: Button = itemView.findViewById(R.id.btnPesanLagi)
    }
}