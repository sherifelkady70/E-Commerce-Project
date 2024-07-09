package com.example.routee_commerce.ui.userAuthentication.fragments.login

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.routee_commerce.ui.base.BaseViewModel
import com.route.domain.usecases.auth.LoginUseCase
import com.route.domain.usecases.auth.ValidationUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val loginUseCase : LoginUseCase,
    private val validationUseCase : ValidationUseCase,
) :BaseViewModel() , LoginContract.LoginViewModel {
    val emailLiveData = MutableLiveData<String>()
    val passLiveData = MutableLiveData<String>()
    val emailErrorLiveData = MutableLiveData<String?>()
    val passErrorLiveData = MutableLiveData<String?>()

    override val event: LiveData<LoginContract.Event>
        get() {
            TODO()
        }
    override val state: StateFlow<LoginContract.State> = TODO()
    override fun doAction(action: LoginContract.Action) {
        TODO("Not yet implemented")
    }

    fun login() {
        Log.d("login", "${isValid()}")
        if (!isValid()) return
        viewModelScope.launch(Dispatchers.IO) {
            loginUseCase.login(emailLiveData.value!!, passLiveData.value!!).collect {

            }
        }
    }

    private fun isValid(): Boolean {
        validationEmailUseCase()
        validationPasswordUseCase()
        if (emailErrorLiveData.value == null && passErrorLiveData.value == null)
            return true
        else
            return false
    }

    private fun validationEmailUseCase() {
        if (validationUseCase.isEmailValid(emailLiveData.value!!))
            emailErrorLiveData.value = null
        else
            emailErrorLiveData.value = "Enter Valid Email"
    }

    private fun validationPasswordUseCase() {
        if (validationUseCase.isPasswordValid(passLiveData.value!!))
            passErrorLiveData.value = null
        else
            passErrorLiveData.value = "Enter Password"
    }

}