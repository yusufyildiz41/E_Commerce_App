package com.yusufyildiz.ecommerceappnew.ui

import android.content.Intent
import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.databinding.DataBindingComponent
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import com.google.firebase.firestore.FirebaseFirestore
import com.yusufyildiz.ecommerceappnew.MainActivity
import com.yusufyildiz.ecommerceappnew.R
import com.yusufyildiz.ecommerceappnew.common.AuthOperations
import com.yusufyildiz.ecommerceappnew.databinding.FragmentProfileBinding
import com.yusufyildiz.ecommerceappnew.viewmodel.ProfileViewModel
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject


@AndroidEntryPoint
class ProfileFragment : Fragment()  {

    private lateinit var binding : FragmentProfileBinding
    @Inject
    lateinit var authOperations: AuthOperations
    private val profileViewModel: ProfileViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater,R.layout.fragment_profile,container,false)
        val view = binding.root

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val currentUser = authOperations.getCurrentUser()
        currentUser?.let {
            profileViewModel.getUserData(it.email.toString())
            initObservers()
        }
        binding.signOutButton.setOnClickListener { signOut ->
            authOperations.signOut().also {
                startActivity(Intent(requireActivity(),MainActivity::class.java))
            }
        }
    }

    fun initObservers()
    {
        profileViewModel.userInfoLiveData.observe(viewLifecycleOwner, Observer {
            binding.cityNameText.text = it.toString()
        })

/*
        profileViewModel.userInfoLiveData.observe(viewLifecycleOwner, Observer { userData ->
            binding.cityNameText.text = userData.city
            binding.countryNameText.text = userData.country
            binding.phoneNumberTextProfile.text = userData.phoneNumber
            binding.firstNameText.text = userData.name
            binding.lastNameText.text = userData.surname
            binding.shortAddressText.text = userData.shortAddress
        })

*/
    }



}