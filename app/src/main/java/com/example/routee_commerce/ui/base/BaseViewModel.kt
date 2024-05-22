package com.example.routee_commerce.ui.base

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel


open class BaseViewModel : ViewModel() {

    val isLoading = MutableLiveData<Boolean>()
    val viewMessage = MutableLiveData<ViewMessage>()
}