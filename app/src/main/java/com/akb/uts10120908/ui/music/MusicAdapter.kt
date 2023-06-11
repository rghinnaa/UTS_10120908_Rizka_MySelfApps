package com.akb.uts10120908.ui.music

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.akb.uts10120908.R
import com.akb.uts10120908.databinding.ItemMusicBinding
import com.akb.uts10120908.ui.music.model.MusicModel
import com.akb.uts10120908.util.emptyInt
import com.akb.uts10120908.util.loadImage
import com.akb.uts10120908.util.textOrNull

/**
 * Tanggal Pengerjaan : 10/06/2023
 * NIM : 10120908
 * Nama : Rizka Ghinna Auliya
 * Kelas : IF-10
 * */

class MusicAdapter() : RecyclerView.Adapter<MusicAdapter.ViewHolder>() {

    private var isPlaying = emptyInt

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemMusicBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )

        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(item = differ.currentList[position])
    }

    override fun getItemCount(): Int = differ.currentList.size

    inner class ViewHolder(private val binding: ItemMusicBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: MusicModel) {
            binding.run {
                ivCover.loadImage("https://t.ly/${item.image}")
                tvTitle.textOrNull = item.title
                tvSinger.textOrNull = item.singer

                root.setOnClickListener {
                    onItemClickListener?.invoke(item.song, item.url.toString(), bindingAdapterPosition)
                }

                ibPlay.setOnClickListener {
                    onItemClickListener?.invoke(item.song, item.url.toString(), bindingAdapterPosition)
                }

                if(isPlaying == bindingAdapterPosition) {
                    ibPlay.loadImage(R.drawable.ic_pause)
                }
                if(isPlaying != bindingAdapterPosition) {
                    ibPlay.loadImage(R.drawable.ic_play)
                }

            }
        }
    }

    private val differCallBack = object : DiffUtil.ItemCallback<MusicModel>() {
        override fun areItemsTheSame(
            oldExampleModel: MusicModel, newExampleModel: MusicModel
        ): Boolean {
            return oldExampleModel.title == newExampleModel.title
        }

        override fun areContentsTheSame(
            oldExampleModel: MusicModel, newExampleModel: MusicModel
        ): Boolean {
            return oldExampleModel == newExampleModel
        }
    }

    val differ = AsyncListDiffer(this, differCallBack)

    private var onItemClickListener: ((Int?, String, Int) -> Unit)? = null

    fun setOnItemClickListener(listener: (Int?, String, Int) -> Unit) {
        onItemClickListener = listener
    }

    fun setPlay(play: Int = emptyInt){
        isPlaying = play
    }

}