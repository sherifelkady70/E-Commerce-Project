package com.example.routee_commerce.ui.userAuthentication.fragments.login

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.route.domain.usecases.auth.LoginUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlin.math.truncate

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val loginUseCase : LoginUseCase
) :ViewModel(){
    val emailLiveData = MutableLiveData<String>()
    val passLiveData = MutableLiveData<String>()
    val emailErrorLiveData = MutableLiveData<String>()
    val passErrorLiveData = MutableLiveData<String>()

    fun login(){

    }

    fun validationData():Boolean{
        var isValid = true
        if(emailLiveData.value!!.isEmpty()){
            emailErrorLiveData.value = "Please Fill Email"
            isValid = false
        }
        if(passLiveData.value!!.isEmpty()) {
            passErrorLiveData.value = "Please Fill Password"
            isValid=false
        }else if( passLiveData.value!!.length <6){
            passErrorLiveData.value = "Please Enter Valid Password"
            isValid=false
        }
        return isValid
    }
}