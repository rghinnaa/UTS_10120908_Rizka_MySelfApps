package com.akb.uts10120908.ui.gallery

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.akb.uts10120908.R
import com.akb.uts10120908.databinding.FragmentGalleryBinding
import com.akb.uts10120908.util.DataCollection
import com.akb.uts10120908.util.navController
import com.akb.uts10120908.util.navigateOrNull
import com.akb.uts10120908.util.viewBinding

/**
 * Tanggal Pengerjaan : 09/06/2023
 * NIM : 10120908
 * Nama : Rizka Ghinna Auliya
 * Kelas : IF-10
 * */

class GalleryFragment: Fragment(R.layout.fragment_gallery) {

    private val binding by viewBinding<FragmentGalleryBinding>()

    private val albumAdapter = AlbumAdapter()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.rvAlbum.adapter = albumAdapter
        albumAdapter.differ.submitList(DataCollection.albumList)

        initAdapterListener()

    }

    private fun initAdapterListener() {
        albumAdapter.setOnItemClickListener {
            navController.navigateOrNull(
                GalleryFragmentDirections.actionGalleryDetail(it)
            )
        }
    }

}
