package com.yusufyildiz.ecommerceappnew.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.yusufyildiz.ecommerceappnew.R
import com.yusufyildiz.ecommerceappnew.common.showToast
import com.yusufyildiz.ecommerceappnew.data.model.remote.countries.Country
import com.yusufyildiz.ecommerceappnew.data.model.remote.user.User
import com.yusufyildiz.ecommerceappnew.databinding.FragmentSignUpBinding
import com.yusufyildiz.ecommerceappnew.viewmodel.SignUpViewModel
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class SignUpFragment : Fragment() {

    private lateinit var binding: FragmentSignUpBinding
    private val signUpViewModel: SignUpViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_sign_up, container, false)
        val view = binding.root
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.signUpButton.setOnClickListener {

            val email = binding.emailEdtTextSignUp.text.toString()
            val password = binding.passwordEdtTextSignUp.text.toString()
            val passwordAgain = binding.passwordAgainEdtTextSignUp.text.toString()
            val name = binding.firstNameEdtText.text.toString()
            val surname = binding.lastNameEdtText.text.toString()
            val phone = binding.phoneNumberText.text.toString()
            val shortAddress = binding.shortAddress.text.toString()
            val country = binding.countrySelectText.text.toString()
            val city = binding.citySelectText.text.toString()

            if (checkFields(
                    email,
                    password,
                    passwordAgain,
                    name,
                    surname,
                    phone,
                    shortAddress,
                    country,
                    city
                )
            ) {
                if (password.trim() != passwordAgain.trim()) {
                    context showToast "Passwords does not match!!!"
                } else {
                    signUpViewModel.signUp(email, password)
                    saveUser("1",email,name,surname,phone,country,city,shortAddress)
                }
            } else {
                context showToast "Please fill all the fields !!!"
            }
        }

        binding.countrySelectText.setOnItemClickListener { _, _, _, _ ->
            val item = binding.countrySelectText.text.toString()
            val country = Country(item)
            signUpViewModel.cities(country)
        }

        binding.citySelectText.setOnItemClickListener { _, _, _, _ ->
            val item = binding.citySelectText.text.toString()
            Toast.makeText(requireContext(), item, Toast.LENGTH_SHORT).show()
        }
        initObservers()
    }

    private fun initObservers() {
        signUpViewModel.countriesLiveData.observe(viewLifecycleOwner) { countries ->
            val countryAdapterItems =
                ArrayAdapter(requireContext(), R.layout.list_item, countries)
            binding.countrySelectText.setAdapter(countryAdapterItems)
        }
        signUpViewModel.citiesLiveData.observe(viewLifecycleOwner) { cities ->

            val cityAdapterItems =
                ArrayAdapter(requireContext(), R.layout.list_item, cities)
            binding.citySelectText.setAdapter(cityAdapterItems)
        }
        signUpViewModel.signUpResult.observe(viewLifecycleOwner) { message ->
            if (message == "Successful") {
                findNavController().navigate(R.id.action_signUpFragment_to_signInFragment)
            } else {
                context showToast "Failed"
            }
        }
    }

    private fun saveUser(
        userId: String,
        email: String,
        name: String,
        surname: String,
        phoneNumber: String,
        country: String,
        city: String,
        shortAddress: String
    )
    {
        val user = User(userId, email, name, surname, phoneNumber, country, city, shortAddress)
        signUpViewModel.saveUser(user)
        println("User id : ${user.userId} ")
    }

    private fun checkFields(
        email: String,
        password: String,
        passwordAgain: String,
        name: String,
        surname: String,
        phone: String,
        shortAddress: String,
        country: String,
        city: String
    ): Boolean {

        val check = when {
            email.trim().isEmpty() -> false
            password.trim().isEmpty() -> false
            passwordAgain.trim().isEmpty() -> false
            name.trim().isEmpty() -> false
            surname.trim().isEmpty() -> false
            phone.trim().isEmpty() -> false
            shortAddress.trim().isEmpty() -> false
            country.trim().isEmpty() -> false
            city.trim().isEmpty() -> false

            else -> true

        }

        return check
    }
}