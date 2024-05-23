package com.example.routee_commerce.ui.base

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.route.domain.common.InternetConnection
import com.route.domain.common.Resource
import kotlin.math.truncate


open class BaseViewModel : ViewModel() {

    val isLoading = MutableLiveData<Boolean>()
    val viewMessage = MutableLiveData<ViewMessage>()

    fun <T>handleResource(resource : Resource<T>){
        when(resource) {
            is Resource.Loading -> {
                isLoading.postValue(true)
            }
            is Resource.Fail -> {
                when(resource.error){
                    is InternetConnection -> {
                        viewMessage.postValue(ViewMessage(
                            resource.error.message
                        ))
                    }
                    else ->{
                        viewMessage.postValue(ViewMessage(
                            resource.error.localizedMessage
                        ))
                    }
                }
            }
            is Resource.ServerError -> {
                viewMessage.postValue(ViewMessage(
                    resource.message.serverError
                ))
            }
            else -> {
            }
        }
    }
}