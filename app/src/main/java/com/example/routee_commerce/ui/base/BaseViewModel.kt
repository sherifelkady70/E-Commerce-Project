package com.example.routee_commerce.ui.base

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.route.domain.common.InternetConnection
import com.route.domain.common.Resource
import kotlin.math.truncate


open class BaseViewModel : ViewModel() {

    val isLoading = MutableLiveData<Boolean>()
    val viewMessage = MutableLiveData<ViewMessage>()

    fun <T>extractViewMessage(resource : Resource<T>) : ViewMessage? {
       return when(resource) {
            is Resource.Fail -> {
                when(resource.error){
                    is InternetConnection -> {
                       ViewMessage(
                            resource.error.message
                        )
                    }
                    else ->{
                        ViewMessage(
                            resource.error.localizedMessage
                        )
                    }
                }
            }
            is Resource.ServerError -> {
                ViewMessage(
                    resource.message.serverError
                )
            }
            else -> null
        }
    }
}