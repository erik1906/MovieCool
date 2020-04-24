package com.eagledev.moviecool.ui

import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.eagledev.moviecool.data.repositories.AuthenticationRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

class LogInViewModel @Inject constructor(private val authenticationRepository: AuthenticationRepository) : ViewModel() {


    val authResult = Transformations.map(authenticationRepository.authResponse){it}

    val accessTokenStatus = Transformations.map(authenticationRepository.accessTokenStatus){it}

    fun getAuthentication(){
        viewModelScope.launch(Dispatchers.IO) {
            authenticationRepository.getAuthentication()
        }
    }

    fun getAccessToken(){
        viewModelScope.launch (Dispatchers.IO){
            authenticationRepository.getAccessToken()
        }
    }
}
