package com.akb.uts10120908.ui.music

import android.net.Uri
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.MediaController
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.akb.uts10120908.databinding.ItemVideoBinding
import com.akb.uts10120908.ui.music.model.VideoModel
import com.akb.uts10120908.util.textOrNull

/**
 * Tanggal Pengerjaan : 10/06/2023
 * NIM : 10120908
 * Nama : Rizka Ghinna Auliya
 * Kelas : IF-10
 * */

class VideoAdapter() : RecyclerView.Adapter<VideoAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemVideoBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )

        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(item = differ.currentList[position])
    }

    override fun getItemCount(): Int = differ.currentList.size

    inner class ViewHolder(private val binding: ItemVideoBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: VideoModel) {
            binding.run {
                val uri = Uri.parse(item.video)
                vVideo.setVideoURI(uri)
                tvTitle.textOrNull = item.title
                tvSinger.text = item.singer

                val mController = MediaController(root.context)
                mController.setAnchorView(vVideo)
                mController.setMediaPlayer(vVideo)
                vVideo.setMediaController(mController)
            }
        }
    }

    private val differCallBack = object : DiffUtil.ItemCallback<VideoModel>() {
        override fun areItemsTheSame(
            oldExampleModel: VideoModel, newExampleModel: VideoModel
        ): Boolean {
            return oldExampleModel.title == newExampleModel.title
        }

        override fun areContentsTheSame(
            oldExampleModel: VideoModel, newExampleModel: VideoModel
        ): Boolean {
            return oldExampleModel == newExampleModel
        }
    }

    val differ = AsyncListDiffer(this, differCallBack)

}