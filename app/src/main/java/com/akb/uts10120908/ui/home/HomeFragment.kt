package com.akb.uts10120908.ui.home

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.akb.uts10120908.R
import com.akb.uts10120908.databinding.DialogAboutBinding
import com.akb.uts10120908.databinding.DialogViewImageBinding
import com.akb.uts10120908.databinding.FragmentHomeBinding
import com.akb.uts10120908.util.*

/**
 * Tanggal Pengerjaan : 09/06/2023
 * NIM : 10120908
 * Nama : Rizka Ghinna Auliya
 * Kelas : IF-10
 * */

class HomeFragment: Fragment(R.layout.fragment_home) {

    private val binding by viewBinding<FragmentHomeBinding>()

    private val aboutAdapter = AboutAdapter()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initOnClick()

    }

    private fun initHobbyDialog() {
        val dialogBinding = DialogAboutBinding.inflate(layoutInflater)

        context?.alertDialog(dialogBinding.root, true)?.apply {
            show()
            window?.setBackgroundDrawableResource(android.R.color.transparent)

            dialogBinding.apply {
                tvTitle.textOrNull = resources.getString(R.string.my_hobbies)

                aboutAdapter.differ.submitList(DataCollection.hobby)
                rvAbout.adapter = aboutAdapter

                aboutAdapter.setOnItemClickListener { initViewImageDialog(it) }
            }
        }
    }

    private fun initFoodDialog() {
        val dialogBinding = DialogAboutBinding.inflate(layoutInflater)

        context?.alertDialog(dialogBinding.root, true)?.apply {
            show()
            window?.setBackgroundDrawableResource(android.R.color.transparent)

            dialogBinding.apply {
                tvTitle.textOrNull = resources.getString(R.string.my_foods)

                aboutAdapter.differ.submitList(DataCollection.food)
                rvAbout.adapter = aboutAdapter

                aboutAdapter.setOnItemClickListener { initViewImageDialog(it) }
            }
        }
    }

    private fun initDrinkDialog() {
        val dialogBinding = DialogAboutBinding.inflate(layoutInflater)

        context?.alertDialog(dialogBinding.root, true)?.apply {
            show()
            window?.setBackgroundDrawableResource(android.R.color.transparent)

            dialogBinding.apply {
                tvTitle.textOrNull = resources.getString(R.string.my_drinks)

                aboutAdapter.differ.submitList(DataCollection.drink)
                rvAbout.adapter = aboutAdapter

                aboutAdapter.setOnItemClickListener { initViewImageDialog(it) }
            }
        }
    }

    private fun initInterestDialog() {
        val dialogBinding = DialogAboutBinding.inflate(layoutInflater)

        context?.alertDialog(dialogBinding.root, true)?.apply {
            show()
            window?.setBackgroundDrawableResource(android.R.color.transparent)

            dialogBinding.apply {
                tvTitle.textOrNull = resources.getString(R.string.my_interest)

                aboutAdapter.differ.submitList(DataCollection.interest)
                rvAbout.adapter = aboutAdapter

                aboutAdapter.setOnItemClickListener { initViewImageDialog(it) }
            }
        }
    }

    private fun initGoalsDialog() {
        val dialogBinding = DialogAboutBinding.inflate(layoutInflater)

        context?.alertDialog(dialogBinding.root, true)?.apply {
            show()
            window?.setBackgroundDrawableResource(android.R.color.transparent)

            dialogBinding.apply {
                tvTitle.textOrNull = resources.getString(R.string.my_goals)

                aboutAdapter.differ.submitList(DataCollection.goals)
                rvAbout.adapter = aboutAdapter

                aboutAdapter.setOnItemClickListener { initViewImageDialog(it) }
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


    private fun initOnClick() {
        binding.apply {
            tvHobby.setOnClickListener(onClickCallback)
            tvFood.setOnClickListener(onClickCallback)
            tvDrink.setOnClickListener(onClickCallback)
            tvInterest.setOnClickListener(onClickCallback)
            tvGoals.setOnClickListener(onClickCallback)
            ivProfile.setOnClickListener(onClickCallback)
            ivMe1.setOnClickListener(onClickCallback)
            ivMe2.setOnClickListener(onClickCallback)
            ivMeBirthday.setOnClickListener(onClickCallback)
            ivMeJob.setOnClickListener(onClickCallback)
            ivCollege.setOnClickListener(onClickCallback)
            ivCollege2.setOnClickListener(onClickCallback)
        }
    }

    private val onClickCallback = View.OnClickListener { view ->
        when (view) {
            binding.tvHobby -> initHobbyDialog()
            binding.tvFood -> initFoodDialog()
            binding.tvDrink -> initDrinkDialog()
            binding.tvInterest -> initInterestDialog()
            binding.tvGoals -> initGoalsDialog()
            binding.ivProfile -> initViewImageDialog(R.drawable.img_profile)
            binding.ivMe1 -> initViewImageDialog(R.drawable.img_me_1)
            binding.ivMe2 -> initViewImageDialog(R.drawable.img_me_2)
            binding.ivMeBirthday -> initViewImageDialog(R.drawable.img_me_birthday)
            binding.ivMeJob -> initViewImageDialog(R.drawable.img_me_job)
            binding.ivCollege -> initViewImageDialog(R.drawable.img_me_college)
            binding.ivCollege2 -> initViewImageDialog(R.drawable.img_me_college_2)
        }
    }

}
