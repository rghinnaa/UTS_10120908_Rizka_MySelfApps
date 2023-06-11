package com.akb.uts10120908.ui.gallery

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.akb.uts10120908.R
import com.akb.uts10120908.databinding.DialogViewImageBinding
import com.akb.uts10120908.databinding.FragmentDetailGalleryBinding
import com.akb.uts10120908.util.*

/**
 * Tanggal Pengerjaan : 09/06/2023
 * NIM : 10120908
 * Nama : Rizka Ghinna Auliya
 * Kelas : IF-10
 * */

class GalleryDetailFragment: Fragment(R.layout.fragment_detail_gallery) {

    private val binding by viewBinding<FragmentDetailGalleryBinding>()
    private val args by navArgs<GalleryDetailFragmentArgs>()

    private val galleryAdapter = GalleryAdapter()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.rvGallery.adapter = galleryAdapter
        binding.tvToolbar.textOrNull = args.type
        val data = when(args.type) {
            "Me" -> DataCollection.meList
            "College" -> DataCollection.collegeList
            "High School" -> DataCollection.highSchoolList
            "Family" -> DataCollection.familyList
            else -> DataCollection.catList
        }
        galleryAdapter.differ.submitList(data)

        initAdapterListener()

    }

    private fun initAdapterListener() {
        galleryAdapter.setOnItemClickListener {
            initViewImageDialog(it)
        }
    }

    private fun initViewImageDialog(image: Any) {
        val dialogBinding = DialogViewImageBinding.inflate(layoutInflater)

        context?.alertDialog(dialogBinding.root, true)?.apply {
            show()
            window?.setBackgroundDrawableResource(android.R.color.transparent)

            dialogBinding.apply {
                ivDetail.loadImage(image)
            }
        }
    }

}
