package com.codingurkan.rickandmortyapp.ui.fragments.register

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.codingurkan.rickandmortyapp.R
import com.codingurkan.rickandmortyapp.databinding.FragmentRegisterBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase


class RegisterFragment : Fragment() {

    private var binding: FragmentRegisterBinding? = null
    private lateinit var auth: FirebaseAuth


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        auth = Firebase.auth
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        binding = FragmentRegisterBinding.inflate(layoutInflater)
        return binding?.root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        registerActions()
    }
    private fun registerActions(){
        binding?.buttonReg?.setOnClickListener {
            val userName = binding?.nameLogin?.text.toString()
            val email = binding?.emailLogin?.text.toString()
            val password = binding?.passwordLogin?.text.toString()

            if (userName.isNotEmpty() && email.isNotEmpty() && password.isNotEmpty()){
                auth.createUserWithEmailAndPassword(email, password).addOnSuccessListener {
                    findNavController().navigate(R.id.action_registerFragment_to_loginFragment)
                }.addOnFailureListener { _exception ->
                    Toast.makeText(requireContext(), _exception.localizedMessage, Toast.LENGTH_SHORT).show()
                }
            }else{
                Toast.makeText(requireContext(), "You entered incomplete information", Toast.LENGTH_SHORT).show()
            }
        }
    }
}