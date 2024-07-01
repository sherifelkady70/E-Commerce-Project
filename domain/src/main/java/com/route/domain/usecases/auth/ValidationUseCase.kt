package com.route.domain.usecases.auth

import javax.inject.Inject

class ValidationUseCase  @Inject constructor(){
    fun isEmailValid(email:String):Boolean{
        val emailRegex = Regex("")
        if(email.isEmpty()){
           return false
        }else{
            return if (emailRegex.matches(email))
                true
            else
                false
        }
    }

    fun isPasswordValid(password:String):Boolean{
        val passwordRegex = Regex("")
        if(password.isEmpty()){
            return false
        }else{
            return if (passwordRegex.matches(password))
                true
            else
                false
        }
    }
}