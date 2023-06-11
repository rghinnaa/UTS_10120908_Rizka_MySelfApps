package com.akb.uts10120908.ui.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.akb.uts10120908.databinding.ItemAboutBinding
import com.akb.uts10120908.ui.home.model.AboutModel
import com.akb.uts10120908.util.loadImage
import com.akb.uts10120908.util.textOrNull

/**
 * Tanggal Pengerjaan : 09/06/2023
 * NIM : 10120908
 * Nama : Rizka Ghinna Auliya
 * Kelas : IF-10
 * */

class AboutAdapter() : RecyclerView.Adapter<AboutAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemAboutBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )

        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(item = differ.currentList[position])
    }

    override fun getItemCount(): Int = differ.currentList.size

    inner class ViewHolder(private val binding: ItemAboutBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: AboutModel) {
            binding.run {
                ivImage.loadImage("https://t.ly/${item.image}")
                tvTitle.textOrNull = item.title

                ivImage.setOnClickListener {
                    onItemClickListener?.invoke("https://t.ly/${item.image}")
                }
            }
        }
    }

    private val differCallBack = object : DiffUtil.ItemCallback<AboutModel>() {
        override fun areItemsTheSame(
            oldExampleModel: AboutModel, newExampleModel: AboutModel
        ): Boolean {
            return oldExampleModel.title == newExampleModel.title
        }

        override fun areContentsTheSame(
            oldExampleModel: AboutModel, newExampleModel: AboutModel
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