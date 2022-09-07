package com.codingurkan.rickandmortyapp.ui.fragments.login

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.codingurkan.rickandmortyapp.R
import com.codingurkan.rickandmortyapp.databinding.FragmentLoginBinding
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.GoogleAuthProvider
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class LoginFragment : Fragment() {

    private var binding : FragmentLoginBinding? = null
    private var viewModel : LoginFragmentViewModel? = null
    private lateinit var auth : FirebaseAuth
    private lateinit var googleSignInClient : GoogleSignInClient

    private companion object{
        private const val RC_SIGN_IN = 1001
        private const val TAG = "GOOGLE_SIGN_IN_TAG"
    }

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
        //initViewModel()
        loginActions()
        //userObserver()
        navigateToRegisterPage()
        loginWithGoogleActions()
    }
    private fun initViewModel(){
        viewModel = ViewModelProvider(this)[LoginFragmentViewModel::class.java]
    }
    private fun loginActions(){
        binding?.buttonLogin?.setOnClickListener {
            val email = binding?.emailLogin?.text.toString()
            val password = binding?.passwordLogin?.text.toString()
            if (email.isNotEmpty() && password.isNotEmpty()){
                auth.signInWithEmailAndPassword(email, password).addOnSuccessListener {
                    findNavController().navigate(R.id.action_loginFragment_to_charactersListFragment)
                }.addOnFailureListener { _exception ->
                    Toast.makeText(requireContext(), _exception.localizedMessage, Toast.LENGTH_SHORT).show()
                }
            }else{
                Toast.makeText(requireContext(), "You entered incomplete information", Toast.LENGTH_SHORT).show()
            }
        }
    }
    private fun loginClick(){
        binding?.buttonLogin?.setOnClickListener {
            viewModel?.loginActions(binding?.emailLogin?.text.toString(),
                binding?.passwordLogin?.text.toString())
        }
    }
    private fun userObserver(){
        viewModel?.updatedUser?.observe(viewLifecycleOwner){ _data ->
            _data?.let {
                navigateWithEmail(_data)
            }
        }
    }
    private fun navigateWithEmail(guncelKullanici : String?){
        guncelKullanici?.let {
            findNavController().navigate(R.id.action_loginFragment_to_charactersListFragment)
        }
    }
    private fun navigateToRegisterPage(){
        binding?.buttonReg?.setOnClickListener {
            findNavController().navigate(R.id.action_loginFragment_to_registerFragment)
        }
    }
    private fun loginWithGoogleActions(){
        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_GAMES_SIGN_IN)
            .requestIdToken("R142546858230-p471u8fic627j6jviml0b8cleca7n6nn.apps.googleusercontent.com")
            .requestEmail()
            .build()
        googleSignInClient = GoogleSignIn.getClient(requireContext(),gso)

        auth = FirebaseAuth.getInstance()

        binding?.loginGoogle?.setOnClickListener {
            signIn()
        }
    }
    private fun signIn(){
        val signInClient = googleSignInClient.signInIntent
        startActivityForResult(signInClient, RC_SIGN_IN)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(
            requestCode,
            resultCode,
            data
        )
        if (requestCode == RC_SIGN_IN) {
            val task = GoogleSignIn.getSignedInAccountFromIntent(data)
            try {
                val account = task.getResult(ApiException::class.java)!!
                Log.d(TAG, "firebaseAuthWithGoogle:" + account.id)
                firebaseAuthWithGoogle(account.idToken!!)
            } catch (e: ApiException) {
                Log.w(TAG, "Google sign in failed", e)
            }
        }
    }
    private fun firebaseAuthWithGoogle(idToken: String) {
        val credential = GoogleAuthProvider.getCredential(idToken, null)
        auth.signInWithCredential(credential)
    }

    private fun updateUI(user: FirebaseUser?) {
        if (user != null) {
            findNavController().navigate(R.id.action_loginFragment_to_charactersListFragment)
        }
    }
}