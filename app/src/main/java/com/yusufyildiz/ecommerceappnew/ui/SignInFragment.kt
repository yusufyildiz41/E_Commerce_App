package com.yusufyildiz.ecommerceappnew.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import com.google.android.gms.auth.api.identity.Identity
import com.google.android.gms.auth.api.identity.SignInClient
import com.google.firebase.auth.FirebaseAuth
import com.yusufyildiz.ecommerceappnew.R
import com.yusufyildiz.ecommerceappnew.common.AuthOperations

import com.yusufyildiz.ecommerceappnew.common.showToast
import com.yusufyildiz.ecommerceappnew.databinding.FragmentSignInBinding
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class SignInFragment : Fragment() {

    private lateinit var binding: FragmentSignInBinding

    private val TAG = "SignInFragment"
    private lateinit var oneTapClient : SignInClient

    @Inject
    lateinit var authOperations: AuthOperations

    private var auth = FirebaseAuth.getInstance()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {


        binding = DataBindingUtil.inflate(inflater,R.layout.fragment_sign_in,container,false)
        val view = binding.root
        // Inflate the layout for this fragment
        return view


    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        oneTapClient = Identity.getSignInClient(requireActivity())

        authOperations.checkCurrentUser {
            view.findNavController().navigate(R.id.homeFragment)
        }

        val navHostFragment = requireActivity().supportFragmentManager.findFragmentById(R.id.fragmentContainerView) as NavHostFragment
        val navController = navHostFragment.navController


//        authOperations.checkCurrentUser{
//            findNavController().navigate(R.id.action_signInFragment_to_homeFragment)
//        }


        binding.loginButton.setOnClickListener {

            val email = binding.emailEdtTextLogin.text
            val password = binding.passwordEdtTextLogin.text

            if(email.trim().toString() != "" && password.trim().toString() != "")
            {
                authOperations.signInWithEmailAndPassword(email.toString(),password.toString(), {
                    val action = SignInFragmentDirections.actionSignInFragmentToHomeFragment()
                    findNavController().navigate(action)
                    context showToast "Successful"
                }, {
                        context showToast it
                    }

                )
            }
            else
            {
                context showToast "Please fill all the fields"
            }

        }

        binding.signUpText.setOnClickListener { view ->

            val action = SignInFragmentDirections.actionSignInFragmentToSignUpFragment()
            view.findNavController().navigate(action)


        }

        binding.googleButton.setOnClickListener {
            authOperations.signInWithGoogle(requireActivity(),oneTapClient,{
                googleSignInIntentResultLauncher.launch(it)
            },{
                context showToast it
            }
            )
        }

        binding.githubButton.setOnClickListener {
            authOperations.signInWithGithub(requireActivity(),{
                val action = SignInFragmentDirections.actionSignInFragmentToHomeFragment()
                findNavController().navigate(action)
                context showToast "Successful Login"
            },{
                context showToast it
            })
        }

        binding.twitterButton.setOnClickListener {
            authOperations.signInWithTwitter(requireActivity(),{
                val action = SignInFragmentDirections.actionSignInFragmentToHomeFragment()
                findNavController().navigate(action)
                context showToast "Successful Login"
            },{
                context showToast it
            })
        }

    }

    private val googleSignInIntentResultLauncher =
        registerForActivityResult(ActivityResultContracts.StartIntentSenderForResult()){ result ->
            if(result!=null)
            {
                val credential = oneTapClient.getSignInCredentialFromIntent(result.data)
                val idToken = credential.googleIdToken
                idToken?.let {
                    val action = SignInFragmentDirections.actionSignInFragmentToHomeFragment()
                    findNavController().navigate(action)
                    context showToast "Successful Login"
                }
            }
        }

}