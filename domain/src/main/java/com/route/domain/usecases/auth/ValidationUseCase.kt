package com.route.domain.usecases.auth

import javax.inject.Inject

class ValidationUseCase  @Inject constructor(){
    fun isEmailValid(email:String) :Boolean{
        val emailRegex = Regex("[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\\\.[a-zA-Z]{2,}")
            if(email.isEmpty()){
                return false
            }else{
                return emailRegex.matches(email)
            }

    }

    fun isPasswordValid(password:String):Boolean{
        val passwordRegex = Regex("^(?=.*[A-Za-z])(?=.*\\\\d)(?=.*[@\\\$!%*#?&])[A-Za-z\\\\d@\\\$!%*#?&]{8,}\$")
        return if(password.isNullOrEmpty()){
            false
        }else{
            return passwordRegex.matches(password)
        }
    }
}