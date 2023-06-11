package com.akb.uts10120908.ui.profile

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.akb.uts10120908.R
import com.akb.uts10120908.databinding.DialogAboutAppBinding
import com.akb.uts10120908.databinding.DialogViewImageBinding
import com.akb.uts10120908.databinding.FragmentProfileBinding
import com.akb.uts10120908.util.alertDialog
import com.akb.uts10120908.util.loadImage
import com.akb.uts10120908.util.toast
import com.akb.uts10120908.util.viewBinding
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import java.net.URLEncoder

/**
 * Tanggal Pengerjaan : 11/06/2023
 * NIM : 10120908
 * Nama : Rizka Ghinna Auliya
 * Kelas : IF-10
 * */

class ProfileFragment: Fragment(R.layout.fragment_profile), OnMapReadyCallback {

    private val binding by viewBinding<FragmentProfileBinding>()

    private var latLong = LatLng(-6.882012742935052, 107.6124605005545)
    private val instagram = "rghinnaa"
    private val whatsapp = "85219515233"
    private val linkedIn = "https://www.linkedin.com/in/rizka-ghinna-auliya-88a6b41ba/"

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initMap()
        initOnClick()

    }

    private fun initMap() {
        val mapFragment =
            childFragmentManager.findFragmentById(R.id.fLocation) as? SupportMapFragment
        mapFragment?.getMapAsync(this)
    }

    private fun initAboutApp() {
        val dialogBinding = DialogAboutAppBinding.inflate(layoutInflater)

        context?.alertDialog(dialogBinding.root, true)?.apply {
            show()
            window?.setBackgroundDrawableResource(android.R.color.transparent)
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

    private fun sendWhatsApp() {
        try {
            val whatsApp = "https://api.whatsapp.com/send?phone=+62$whatsapp&text="
            val packageManager = context?.packageManager
            val whatsappIntent = Intent(Intent.ACTION_VIEW)
            val url = whatsApp + URLEncoder.encode(
                "Hi",
                "UTF-8"
            )
            whatsappIntent.setPackage("com.whatsapp")
            whatsappIntent.data = Uri.parse(url)
            if (packageManager?.let { whatsappIntent.resolveActivity(it) } != null) {
                startActivity(whatsappIntent)
            }
        } catch (e: java.lang.Exception) {
            context?.toast("WhatsApp not Installed")
        }
    }

    private fun sendTelegram() {
        try {
            val telegram = "https://t.me/rghinnaa"
            val packageManager = context?.packageManager
            val telegramIntent = Intent(Intent.ACTION_VIEW)
            telegramIntent.type = "text/plain"
            telegramIntent.setPackage("org.telegram.messenger")
            telegramIntent.data = Uri.parse(telegram)
            if (packageManager?.let { telegramIntent.resolveActivity(it) } != null) {
                startActivity(telegramIntent)
            }
        } catch (e: java.lang.Exception) {
            context?.toast("Telegram not Installed")
        }
    }

    private fun openInstagram() {
        try {
            val packageManager = context?.packageManager
            val instagramIntent = Intent(Intent.ACTION_VIEW)
            val url = "http://instagram.com/_u/$instagram"
            val browserUrl = "http://instagram.com/$instagram"
            instagramIntent.setPackage("com.instagram.android")
            instagramIntent.data = Uri.parse(url)

            if (packageManager?.let { instagramIntent.resolveActivity(it) } != null) {
                startActivity(instagramIntent)
            } else {
                val instagramBrowserIntent = Intent(Intent.ACTION_VIEW)
                instagramBrowserIntent.data = Uri.parse(browserUrl)
                startActivity(instagramBrowserIntent)
            }

        } catch (e: java.lang.Exception) {
            context.toast("Instagram not Installed")
        }
    }

    private fun sendGmail() {
        try {
            val gmail = "rizkaghinna29@gmail.com"
            val packageManager = context?.packageManager
            val gmailIntent = Intent(Intent.ACTION_VIEW)
            gmailIntent.setPackage("com.google.android.gm")
            gmailIntent.putExtra(Intent.EXTRA_EMAIL, gmail)
            gmailIntent.putExtra(Intent.EXTRA_SUBJECT, "Saya dari aplikasi My Self App")
            gmailIntent.putExtra(Intent.EXTRA_TEXT, "hello. this is a message sent from your self app")
            gmailIntent.data = Uri.parse("mailto:")
            if (packageManager?.let { gmailIntent.resolveActivity(it) } != null) {
                startActivity(gmailIntent)
            }
        } catch (e: java.lang.Exception) {
            context?.toast("Gmail not Installed")
        }
    }

    private fun openLinkedIn() {
        try {
            val instagramBrowserIntent = Intent(Intent.ACTION_VIEW)
            instagramBrowserIntent.data = Uri.parse(linkedIn)
            startActivity(instagramBrowserIntent)

        } catch (e: java.lang.Exception) {
            context.toast("Can't open browser")
        }
    }

    private fun initOnClick() {
        binding.apply {
            ivProfile.setOnClickListener(onClickCallback)
            cvAbout.setOnClickListener(onClickCallback)
            ivWhatsApp.setOnClickListener(onClickCallback)
            ivTelegram.setOnClickListener(onClickCallback)
            ivInstagram.setOnClickListener(onClickCallback)
            ivGmail.setOnClickListener(onClickCallback)
            ivLinkedIn.setOnClickListener(onClickCallback)
        }
    }

    private val onClickCallback = View.OnClickListener { view ->
        when (view) {
            binding.ivProfile -> initViewImageDialog(R.drawable.img_me)
            binding.cvAbout -> initAboutApp()
            binding.ivWhatsApp -> sendWhatsApp()
            binding.ivTelegram -> sendTelegram()
            binding.ivInstagram -> openInstagram()
            binding.ivGmail -> sendGmail()
            binding.ivLinkedIn -> openLinkedIn()
        }
    }

    override fun onMapReady(googleMap: GoogleMap) {
        val place = latLong
        googleMap.addMarker(
            MarkerOptions()
                .position(place)
        )
        googleMap.moveCamera(CameraUpdateFactory.newLatLng(place))
        googleMap.moveCamera(CameraUpdateFactory.zoomTo(17.0f))
        googleMap.uiSettings.apply {
            isScrollGesturesEnabled = false
            isZoomControlsEnabled = true
        }
    }

}
