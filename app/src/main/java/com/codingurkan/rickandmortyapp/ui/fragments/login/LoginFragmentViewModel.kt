package com.codingurkan.rickandmortyapp.ui.fragments.login

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.codingurkan.rickandmortyapp.utils.showToast
import com.google.firebase.auth.FirebaseAuth

class LoginFragmentViewModel : ViewModel() {

    private lateinit var auth : FirebaseAuth
    var updatedUser = MutableLiveData<String?>().also { it.value = null }
    private var failureMessage = MutableLiveData<String?>().also { it.value = null }
    private var failureNullMessage = MutableLiveData<String?>().also { it.value = null }

    fun loginActions(email : String,password : String){
        auth = FirebaseAuth.getInstance()
        if (email.isNotEmpty() && password.isNotEmpty()){
        auth.signInWithEmailAndPassword(email,password).addOnCompleteListener { _task ->
            if (_task.isSuccessful){
                updatedUser.postValue(auth.currentUser?.email.toString())
            }
        }.addOnFailureListener { _exception ->
            failureMessage.postValue(_exception.localizedMessage)
        }
    }else{
        failureNullMessage.postValue("You entered incomplete information")
        }
    }
}