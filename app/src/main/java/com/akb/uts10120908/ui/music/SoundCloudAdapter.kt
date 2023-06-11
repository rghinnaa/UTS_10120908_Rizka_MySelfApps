package com.akb.uts10120908.ui.music

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebViewClient
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.akb.uts10120908.R
import com.akb.uts10120908.databinding.ItemAboutBinding
import com.akb.uts10120908.databinding.ItemMusicBinding
import com.akb.uts10120908.databinding.ItemSoundCloudBinding
import com.akb.uts10120908.ui.home.model.AboutModel
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

class SoundCloudAdapter() : RecyclerView.Adapter<SoundCloudAdapter.ViewHolder>() {

    private var isPlaying = emptyInt
    private var isLoading = emptyInt
    private var isPause = emptyInt
    private var currentPosition = emptyInt

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemSoundCloudBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )

        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(item = differ.currentList[position])
    }

    override fun getItemCount(): Int = differ.currentList.size

    inner class ViewHolder(private val binding: ItemSoundCloudBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: String) {
            binding.run {
                wbSoundCloud.apply {
                    settings.loadsImagesAutomatically = true
                    settings.javaScriptEnabled = true
                    settings.useWideViewPort = true
                    settings.loadWithOverviewMode = true
                    scrollBarStyle = View.SCROLLBARS_INSIDE_OVERLAY
                    webViewClient = WebViewClient()
                    loadDataWithBaseURL("",item,"text/html", "UTF-8", "")
                }
            }
        }
    }

    private val differCallBack = object : DiffUtil.ItemCallback<String>() {
        override fun areItemsTheSame(
            oldExampleModel: String, newExampleModel: String
        ): Boolean {
            return oldExampleModel == newExampleModel
        }

        override fun areContentsTheSame(
            oldExampleModel: String, newExampleModel: String
        ): Boolean {
            return oldExampleModel == newExampleModel
        }
    }

    val differ = AsyncListDiffer(this, differCallBack)

    private var onItemClickListener: ((String, Int) -> Unit)? = null

    fun setOnItemClickListener(listener: (String, Int) -> Unit) {
        onItemClickListener = listener
    }

    fun setLoading(loading: Int = emptyInt) {
        isLoading = loading
    }

    fun setPlay(play: Int = emptyInt){
        isPlaying = play
    }

    fun setPause(pause: Int = emptyInt){
        isPause = pause
    }

}