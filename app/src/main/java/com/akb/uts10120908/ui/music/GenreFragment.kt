package com.akb.uts10120908.ui.music

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.akb.uts10120908.R
import com.akb.uts10120908.databinding.FragmentMusicGenreBinding
import com.akb.uts10120908.ui.music.model.GenreModel
import com.akb.uts10120908.util.loadImage
import com.akb.uts10120908.util.textOrNull
import com.akb.uts10120908.util.viewBinding

/**
 * Tanggal Pengerjaan : 10/06/2023
 * NIM : 10120908
 * Nama : Rizka Ghinna Auliya
 * Kelas : IF-10
 * */

class GenreFragment: Fragment(R.layout.fragment_music_genre) {

    private val binding by viewBinding<FragmentMusicGenreBinding>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val genre = arguments?.getParcelable("genre") ?: GenreModel()

        binding.ivGenre.loadImage("https://t.ly/${genre.image}")
        binding.tvGenre.textOrNull = genre.title
        binding.tvTotal.textOrNull = genre.totalSongs

    }

}
