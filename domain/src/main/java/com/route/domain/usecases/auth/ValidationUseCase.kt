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
}