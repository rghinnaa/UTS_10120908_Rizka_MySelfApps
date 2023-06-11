package com.akb.uts10120908.ui.splash

import android.annotation.SuppressLint
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.View
import android.view.WindowManager
import androidx.fragment.app.Fragment
import com.akb.uts10120908.R
import com.akb.uts10120908.util.color
import com.akb.uts10120908.util.navController
import com.akb.uts10120908.util.navigateOrNull

/**
 * Tanggal Pengerjaan : 08/06/2023
 * NIM : 10120908
 * Nama : Rizka Ghinna Auliya
 * Kelas : IF-10
 * */

@SuppressLint("CustomSplashScreen")
class SplashScreenFragment: Fragment(R.layout.fragment_splash_screen) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        apply {
            activity?.window?.apply {
                addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
            }
        }

        Handler(Looper.getMainLooper()).postDelayed({
            navController.navigateOrNull(
                SplashScreenFragmentDirections.actionToOnBoarding()
            )
        }, 2000)
    }

}
