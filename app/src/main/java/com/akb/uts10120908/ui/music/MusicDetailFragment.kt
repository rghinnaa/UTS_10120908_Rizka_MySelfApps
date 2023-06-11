package com.akb.uts10120908.ui.music

import android.content.Intent
import android.media.MediaPlayer
import android.net.Uri
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.viewpager2.widget.ViewPager2
import com.akb.uts10120908.R
import com.akb.uts10120908.databinding.FragmentMusicDetailBinding
import com.akb.uts10120908.util.DataCollection
import com.akb.uts10120908.util.emptyInt
import com.akb.uts10120908.util.toast
import com.akb.uts10120908.util.viewBinding
import com.google.android.material.tabs.TabLayoutMediator

/**
 * Tanggal Pengerjaan : 10/06/2023
 * NIM : 10120908
 * Nama : Rizka Ghinna Auliya
 * Kelas : IF-10
 * */

class MusicDetailFragment : Fragment(R.layout.fragment_music_detail) {

    private val binding by viewBinding<FragmentMusicDetailBinding>()

    private val musicAdapter = MusicAdapter()
    private var mPlayer: MediaPlayer? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.vpGenre.apply {
            adapter = GenrePagerAdapter(this@MusicDetailFragment).apply {
                setGenre(DataCollection.genreList.toTypedArray())
            }
            TabLayoutMediator(
                binding.tlGenre,
                this
            ) { tab, position ->
            }.attach()
            registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
                override fun onPageSelected(position: Int) {
                    super.onPageSelected(position)

                    initStopAudio()
                    initSetMusic(position)

                }
            })
        }

        initSetMusic(binding.vpGenre.currentItem)
        initAdapterListener()

    }

    private fun initSetMusic(position: Int) {
        val data = when (position) {
            0 -> DataCollection.kpopList
            1 -> DataCollection.jpopList
            else -> DataCollection.popList
        }

        binding.rvMusic.adapter = musicAdapter
        musicAdapter.differ.submitList(data)
    }

    private fun initAdapterListener() {
        musicAdapter.setOnItemClickListener { song, url, position ->

            if(mPlayer?.isPlaying == true) initPauseAudio()
            else {
                if(song != null) initPlayAudio(song, position)
                else openSpotify(url)
            }
        }
    }

    private fun initPlayAudio(song: Int, position: Int) {
        if (mPlayer == null) {
            mPlayer = MediaPlayer.create(context, song)
            mPlayer?.start()
        } else mPlayer?.start()

        musicAdapter.setPlay(position)
        musicAdapter.notifyDataSetChanged()
    }

    private fun initPauseAudio() {
        mPlayer?.pause()

        musicAdapter.setPlay(emptyInt)
        musicAdapter.notifyDataSetChanged()
    }

    private fun initStopAudio() {
        mPlayer?.stop()
        mPlayer = null

        musicAdapter.setPlay(emptyInt)
        musicAdapter.notifyDataSetChanged()
    }

    private fun openSpotify(spotify: String) {
        try {
            val packageManager = context?.packageManager
            val spotifyIntent = Intent(Intent.ACTION_VIEW)
            spotifyIntent.setPackage("com.spotify.mobile.android")
            spotifyIntent.data = Uri.parse(spotify)

            if (packageManager?.let { spotifyIntent.resolveActivity(it) } != null) {
                startActivity(spotifyIntent)
            } else {
                val spotifyBrowserIntent = Intent(Intent.ACTION_VIEW)
                spotifyBrowserIntent.data = Uri.parse(spotify)
                startActivity(spotifyBrowserIntent)
            }

        } catch (e: java.lang.Exception) {
            context.toast("Spotify not Installed")
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()

        initStopAudio()
        musicAdapter.notifyDataSetChanged()
    }

    override fun onDestroy() {
        super.onDestroy()

        initStopAudio()
        musicAdapter.notifyDataSetChanged()
    }
}
