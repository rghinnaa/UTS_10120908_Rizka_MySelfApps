package com.akb.uts10120908.ui.music

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.akb.uts10120908.R
import com.akb.uts10120908.databinding.FragmentMusicBinding
import com.akb.uts10120908.databinding.FragmentVideoDetailBinding
import com.akb.uts10120908.util.DataCollection
import com.akb.uts10120908.util.viewBinding
import com.google.android.material.tabs.TabLayoutMediator

/**
 * Tanggal Pengerjaan : 10/06/2023
 * NIM : 10120908
 * Nama : Rizka Ghinna Auliya
 * Kelas : IF-10
 * */

class VideoDetailFragment : Fragment(R.layout.fragment_video_detail) {

    private val binding by viewBinding<FragmentVideoDetailBinding>()

    private val videoAdapter = VideoAdapter()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.rvVideo.adapter = videoAdapter
        videoAdapter.differ.submitList(DataCollection.videoList)

    }

}
