package com.akb.uts10120908.ui.gallery

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.akb.uts10120908.databinding.ItemAlbumBinding
import com.akb.uts10120908.databinding.ItemDailyActivityBinding
import com.akb.uts10120908.ui.daily_activity.model.DailyActivityModel
import com.akb.uts10120908.ui.daily_activity.model.FriendsModel
import com.akb.uts10120908.ui.gallery.model.AlbumModel
import com.akb.uts10120908.util.loadImage
import com.akb.uts10120908.util.textOrNull

/**
 * Tanggal Pengerjaan : 09/06/2023
 * NIM : 10120908
 * Nama : Rizka Ghinna Auliya
 * Kelas : IF-10
 * */

class AlbumAdapter() : RecyclerView.Adapter<AlbumAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemAlbumBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )

        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(item = differ.currentList[position])
    }

    override fun getItemCount(): Int = differ.currentList.size

    inner class ViewHolder(private val binding: ItemAlbumBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: AlbumModel) {
            binding.run {
                ivAlbum.loadImage("https://t.ly/${item.image}")
                tvTitle.textOrNull = item.title

                root.setOnClickListener {
                    onItemClickListener?.invoke(item.title)
                }
            }
        }
    }

    private val differCallBack = object : DiffUtil.ItemCallback<AlbumModel>() {
        override fun areItemsTheSame(
            oldExampleModel: AlbumModel, newExampleModel: AlbumModel
        ): Boolean {
            return oldExampleModel.image == newExampleModel.image
        }

        override fun areContentsTheSame(
            oldExampleModel: AlbumModel, newExampleModel: AlbumModel
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