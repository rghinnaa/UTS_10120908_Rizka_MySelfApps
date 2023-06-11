package com.akb.uts10120908.ui.music

import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.akb.uts10120908.ui.music.model.GenreModel
import javax.inject.Inject

/**
 * Tanggal Pengerjaan : 10/06/2023
 * NIM : 10120908
 * Nama : Rizka Ghinna Auliya
 * Kelas : IF-10
 * */

class GenrePagerAdapter @Inject constructor(fragment: Fragment) : FragmentStateAdapter(fragment) {

    private var genre: Array<GenreModel> = arrayOf()

    fun setGenre(genre: Array<GenreModel>) {
        this.genre = genre
    }

    override fun getItemCount(): Int = genre.size

    override fun createFragment(position: Int): Fragment =
        GenreFragment().apply {
            arguments = bundleOf("genre" to genre[position])
        }

}