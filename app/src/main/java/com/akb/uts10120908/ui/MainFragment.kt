package com.akb.uts10120908.ui

import android.os.Bundle
import android.view.View
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.navigation.ui.NavigationUI
import com.akb.uts10120908.R
import com.akb.uts10120908.databinding.FragmentMainBinding
import com.akb.uts10120908.util.BackButtonBehaviour
import com.akb.uts10120908.util.setupWithNavController
import com.akb.uts10120908.util.textOrNull
import com.akb.uts10120908.util.viewBinding

/**
 * Tanggal Pengerjaan : 08/06/2023
 * NIM : 10120908
 * Nama : Rizka Ghinna Auliya
 * Kelas : IF-10
 * */

class MainFragment: Fragment(R.layout.fragment_main) {

    private val bottomNavSelectedItemIdKey = "BOTTOM_NAV_SELECTED_ITEM_ID_KEY"
    private var bottomNavSelectedItemId = R.id.home

    private val binding by viewBinding<FragmentMainBinding>()

    private val resToolbarId = mutableListOf(
        R.id.splash_screen_fragment,
        R.id.home_fragment,
        R.id.gallery_detail_fragment,
        R.id.music_fragment
    )
    private val resNavigationId = mutableListOf(
        R.id.splash_screen_fragment,
        R.id.gallery_detail_fragment
    )

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        savedInstanceState?.let {
            bottomNavSelectedItemId = it.getInt(bottomNavSelectedItemIdKey, bottomNavSelectedItemId)
        }

        setUpBottomNavBar()
    }

    private fun setUpBottomNavBar() {
        binding.bottomNavigation.selectedItemId = bottomNavSelectedItemId
        binding.bottomNavigation.itemIconTintList = null

        val navGraphIds = listOf(
            R.navigation.nav_home_graph,
            R.navigation.nav_daily_activity_graph,
            R.navigation.nav_gallery_graph,
            R.navigation.nav_music_graph,
            R.navigation.nav_profile_graph
        )

        val controller = binding.bottomNavigation.setupWithNavController(
            fragmentManager = childFragmentManager,
            navGraphIds = navGraphIds,
            backButtonBehaviour = BackButtonBehaviour.POP_HOST_FRAGMENT,
            containerId = binding.navHostContainer.id,
            firstItemId = R.id.home,
            intent = requireActivity().intent
        )

        controller.observe(viewLifecycleOwner) { navController ->
            NavigationUI.setupWithNavController(binding.toolbar, navController)
            binding.tvToolbar.textOrNull = binding.toolbar.title
//            binding.tvToolbarChild.textOrNull = binding.toolbar.title
            bottomNavSelectedItemId = navController.graph.id

            navController.addOnDestinationChangedListener { _, destination, _ ->
                binding.tvToolbar.isVisible = !resToolbarId.any { resId ->
                    destination.id == resId
                }

                binding.bottomNavigation.isVisible = !resNavigationId.any { resId ->
                    destination.id == resId
                }
            }
        }
    }

}
