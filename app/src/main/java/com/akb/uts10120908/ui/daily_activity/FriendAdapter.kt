package com.akb.uts10120908.ui.daily_activity

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.akb.uts10120908.databinding.ItemDailyActivityBinding
import com.akb.uts10120908.databinding.ItemFriendBinding
import com.akb.uts10120908.ui.daily_activity.model.DailyActivityModel
import com.akb.uts10120908.ui.daily_activity.model.FriendsModel
import com.akb.uts10120908.util.loadImage
import com.akb.uts10120908.util.textOrNull

/**
 * Tanggal Pengerjaan : 09/06/2023
 * NIM : 10120908
 * Nama : Rizka Ghinna Auliya
 * Kelas : IF-10
 * */

class FriendAdapter() : RecyclerView.Adapter<FriendAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemFriendBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )

        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(item = differ.currentList[position])
    }

    override fun getItemCount(): Int = differ.currentList.size

    inner class ViewHolder(private val binding: ItemFriendBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: FriendsModel) {
            binding.run {
                ivProfile.loadImage("https://t.ly/${item.image}")
                tvName.textOrNull = item.name

                ivProfile.setOnClickListener {
                    onItemClickListener?.invoke(item)
                }
            }
        }
    }

    private val differCallBack = object : DiffUtil.ItemCallback<FriendsModel>() {
        override fun areItemsTheSame(
            oldExampleModel: FriendsModel, newExampleModel: FriendsModel
        ): Boolean {
            return oldExampleModel.image == newExampleModel.image
        }

        override fun areContentsTheSame(
            oldExampleModel: FriendsModel, newExampleModel: FriendsModel
        ): Boolean {
            return oldExampleModel == newExampleModel
        }
    }

    val differ = AsyncListDiffer(this, differCallBack)

    private var onItemClickListener: ((FriendsModel) -> Unit)? = null

    fun setOnItemClickListener(listener: (FriendsModel) -> Unit) {
        onItemClickListener = listener
    }

}