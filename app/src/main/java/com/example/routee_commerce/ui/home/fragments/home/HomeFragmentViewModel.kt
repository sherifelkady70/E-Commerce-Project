package com.example.routee_commerce.ui.home.fragments.home

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.routee_commerce.ui.base.BaseViewModel
import com.route.domain.common.Resource
import com.route.domain.usecases.CategoryUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.invoke
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeFragmentViewModel @Inject constructor(
    private val categoryUseCase: CategoryUseCase,
) : BaseViewModel() {
     val categoriesList = MutableLiveData<List<com.route.domain.models.Category>?>()
    fun getCategories() {
        viewModelScope.launch(Dispatchers.IO){
            categoryUseCase.invoke().collect{
                when(it){
                    is Resource.Success ->{
                        categoriesList.postValue(it.data)
                    }
                    else ->{
                        handleResource(it)
                    }
                }
            }
        }
    }
}