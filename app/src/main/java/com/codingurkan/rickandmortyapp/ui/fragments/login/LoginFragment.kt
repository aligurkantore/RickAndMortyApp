package com.codingurkan.rickandmortyapp.ui.fragments.login

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.codingurkan.rickandmortyapp.R
import com.codingurkan.rickandmortyapp.databinding.FragmentLoginBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class LoginFragment : Fragment() {

    private var binding : FragmentLoginBinding? = null
    private var viewModel : LoginFragmentViewModel? = null
    private lateinit var auth : FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        auth = Firebase.auth
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        binding = FragmentLoginBinding.inflate(layoutInflater)
        return binding?.root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        loginActions()
        navigateToRegisterPage()
    }
    private fun initViewModel(){
        viewModel = ViewModelProvider(this)[LoginFragmentViewModel::class.java]
    }
    private fun loginActions(){
        binding?.buttonLogin?.setOnClickListener {
            val email = binding?.emailLogin?.text.toString()
            val password = binding?.passwordLogin?.text.toString()
            /*
            if (email.isNotEmpty() && password.isNotEmpty()){
                auth.signInWithEmailAndPassword(email, password).addOnSuccessListener {
                    findNavController().navigate(R.id.action_loginFragment_to_charactersListFragment)
                }.addOnFailureListener { _exception ->
                    Toast.makeText(requireContext(), _exception.localizedMessage, Toast.LENGTH_SHORT).show()
                }
            }else{
                Toast.makeText(requireContext(), "You entered incomplete information", Toast.LENGTH_SHORT).show()
            }

             */
            findNavController().navigate(R.id.action_loginFragment_to_charactersListFragment)
        }
    }
    private fun navigateToRegisterPage(){
        binding?.buttonReg?.setOnClickListener {
            findNavController().navigate(R.id.action_loginFragment_to_registerFragment)
        }
    }
}