package com.akb.uts10120908.ui.onboarding

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import javax.inject.Inject

/**
 * Tanggal Pengerjaan : 08/06/2023
 * NIM : 10120908
 * Nama : Rizka Ghinna Auliya
 * Kelas : IF-10
 * */

class OnBoardingPagerAdapter @Inject constructor(fragment: Fragment) : FragmentStateAdapter(fragment) {

    private val pages = listOf(
        OnBoardingFragmentOne(),
        OnBoardingFragmentTwo(),
        OnBoardingFragmentThree()
    )

    override fun getItemCount(): Int = pages.size

    override fun createFragment(position: Int): Fragment = pages[position]

    private val pagesHash = pages.map { it.hashCode().toLong() }

    override fun getItemId(position: Int): Long = pages[position].hashCode().toLong()

    override fun containsItem(itemId: Long) = pagesHash.contains(itemId)

}