package com.udacity.shoestore.login

import android.provider.ContactsContract.Directory
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import java.util.Dictionary

class LoginRegisterViewModel: ViewModel() {
    private val _users = mutableMapOf<String, String>("admin@email.com" to "123")
    val users : MutableMap<String, String>
        get() = _users

    private val _currentUser = MutableLiveData<List<String>>()
    val currentUser: LiveData<List<String>>
        get() = _currentUser

    init {
        logout()
    }

    fun registerUser(Email:String, Password:String) {
        _users.put(Email, Password)
        println(_users)
    }

    fun login(Email:String, Password:String) {
        _currentUser.value = listOf(Email, Password)
    }

    fun logout(){
        _currentUser.value = listOf()
    }
}