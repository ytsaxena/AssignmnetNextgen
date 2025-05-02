package com.sachin.mvvmapi.Fragments.Profile

import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.sachin.mvvmapi.R
import com.sachin.mvvmapi.Utility.SessionManager
import com.sachin.mvvmapi.databinding.FragmentProfileBinding
import com.sachin.mvvmapi.databinding.FragmentSearchBinding
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class ProfileFragment : Fragment() {

    lateinit var binding : FragmentProfileBinding

   // private val args: ProfileFragmentArgs by navArgs()

    private lateinit var sessionManager: SessionManager

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
//        return inflater.inflate(R.layout.fragment_profile, container, false)

        binding = FragmentProfileBinding.inflate(layoutInflater)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
//        val user = args.USER
//        val name = args.NAME
//        val email = args.EMAIL
//        val photo = args.PROFILE

        sessionManager = SessionManager(requireContext())

        val userId = sessionManager.getUserId()
        val name = sessionManager.getName()
        val email = sessionManager.getEmail()
        val photoUrl = sessionManager.getPhotoUrl()

        val photoUri = photoUrl?.let { Uri.parse(it) }

        binding.nameTextView.text = name
       binding.emailTextView.text = email
        binding.userIdTextView.text = userId

        Glide.with(this)
            .load(photoUri)
            .placeholder(R.drawable.baseline_person_pin_24)
            .into(binding.profileImageView)






    }


}