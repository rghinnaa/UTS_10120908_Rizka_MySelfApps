package com.akb.uts10120908.ui.daily_activity

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.akb.uts10120908.R
import com.akb.uts10120908.databinding.DialogFriendBinding
import com.akb.uts10120908.databinding.DialogViewImageBinding
import com.akb.uts10120908.databinding.FragmentDailyActivityBinding
import com.akb.uts10120908.ui.daily_activity.model.FriendsModel
import com.akb.uts10120908.util.*

/**
 * Tanggal Pengerjaan : 09/06/2023
 * NIM : 10120908
 * Nama : Rizka Ghinna Auliya
 * Kelas : IF-10
 * */

class DailyActivityFragment: Fragment(R.layout.fragment_daily_activity) {

    private val binding by viewBinding<FragmentDailyActivityBinding>()

    private val activityAdapter = DailyActivityAdapter()
    private val friendAdapter = FriendAdapter()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.rvFriend.adapter = friendAdapter
        friendAdapter.differ.submitList(DataCollection.friendList)

        binding.rvDailyActivity.adapter = activityAdapter
        activityAdapter.differ.submitList(DataCollection.dailyActivity)

        initAdapterListener()

    }

    private fun initAdapterListener() {
        friendAdapter.setOnItemClickListener { item ->
            initFriendDialog(item)
        }
        activityAdapter.setOnItemClickListener {
            initViewImageDialog(it)
        }
    }

    private fun initFriendDialog(item: FriendsModel) {
        val dialogBinding = DialogFriendBinding.inflate(layoutInflater)

        context?.alertDialog(dialogBinding.root, true)?.apply {
            show()
            window?.setBackgroundDrawableResource(android.R.color.transparent)

            dialogBinding.apply {
                ivProfile.loadImage("https://t.ly/${item.image}")
                tvName.textOrNull = item.name
                tvActivity.textOrNull = item.profession
                tvInstagram.textOrNull = item.instagram

                ivProfile.setOnClickListener {
                    initViewImageDialog("https://t.ly/${item.image}")
                }
                cvInstagram.setOnClickListener{
                    openInstagram(item.instagram)
                }
            }
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

    private fun openInstagram(instagram: String) {
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

}
