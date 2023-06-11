package com.akb.uts10120908.ui.music.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

/**
 * Tanggal Pengerjaan : 10/06/2023
 * NIM : 10120908
 * Nama : Rizka Ghinna Auliya
 * Kelas : IF-10
 * */

@Parcelize
data class GenreModel(
    val image: String? = null,
    val title: String? = null,
    val totalSongs: String? = null,
    val genre: String? = null
): Parcelable