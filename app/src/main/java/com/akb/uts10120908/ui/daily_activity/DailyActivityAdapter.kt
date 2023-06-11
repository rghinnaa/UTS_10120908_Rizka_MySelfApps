package com.akb.uts10120908.ui.daily_activity

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.akb.uts10120908.databinding.ItemDailyActivityBinding
import com.akb.uts10120908.ui.daily_activity.model.DailyActivityModel
import com.akb.uts10120908.util.loadImage
import com.akb.uts10120908.util.textOrNull

/**
 * Tanggal Pengerjaan : 09/06/2023
 * NIM : 10120908
 * Nama : Rizka Ghinna Auliya
 * Kelas : IF-10
 * */

class DailyActivityAdapter() : RecyclerView.Adapter<DailyActivityAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemDailyActivityBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )

        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(item = differ.currentList[position])
    }

    override fun getItemCount(): Int = differ.currentList.size

    inner class ViewHolder(private val binding: ItemDailyActivityBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: DailyActivityModel) {
            binding.run {
                ivActivity.loadImage("https://t.ly/${item.image}")
                tvCaption.textOrNull = item.caption

                ivActivity.setOnClickListener {
                    onItemClickListener?.invoke("https://t.ly/${item.image}")
                }
            }
        }
    }

    private val differCallBack = object : DiffUtil.ItemCallback<DailyActivityModel>() {
        override fun areItemsTheSame(
            oldExampleModel: DailyActivityModel, newExampleModel: DailyActivityModel
        ): Boolean {
            return oldExampleModel.image == newExampleModel.image
        }

        override fun areContentsTheSame(
            oldExampleModel: DailyActivityModel, newExampleModel: DailyActivityModel
        ): Boolean {
            return oldExampleModel == newExampleModel
        }
    }

    val differ = AsyncListDiffer(this, differCallBack)

    private var onItemClickListener: ((String) -> Unit)? = null

    fun setOnItemClickListener(listener: (String) -> Unit) {
        onItemClickListener = listener
    }

}