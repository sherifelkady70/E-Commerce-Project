package com.example.routee_commerce.ui.home.fragments.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.routee_commerce.ui.base.BaseViewModel
import com.example.routee_commerce.utils.SingleLiveEvent
import com.route.domain.common.Resource
import com.route.domain.usecases.CategoryProductsUseCase
import com.route.domain.usecases.CategoryUseCase
import com.route.domain.usecases.MostSellingProductsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeFragmentViewModel @Inject constructor(
    private val categoryUseCase: CategoryUseCase,
    private val mostSellingProductsUseCase: MostSellingProductsUseCase,
    private val categoryProductsUseCase : CategoryProductsUseCase
) : BaseViewModel() , HomeContract.ViewModel {
//     val categoriesList = MutableLiveData<List<com.route.domain.models.Category>?>()


    private val _state = MutableStateFlow<HomeContract.State>(HomeContract.State.Loading)
    private val _event = SingleLiveEvent<HomeContract.Event>()
    override val event: LiveData<HomeContract.Event>
        get() {
            return _event
        }
    override val state: StateFlow<HomeContract.State> = _state
    override fun doAction(action: HomeContract.Action) { //the only fun that can view communicate with VM
        when(action){
            HomeContract.Action.initPage -> {
                initPage()
            }
        }
    }

    private fun initPage() {
        getCategories()
        getMostSellingProducts()
        getCategoryProducts()
    }

    private fun getCategoryProducts() {
        viewModelScope.launch(Dispatchers.IO) {
            categoryProductsUseCase.getCategoryProducts().collect{
                _state.emit(HomeContract.State.Success(it))
            }
        }
    }
    private fun getMostSellingProducts() {
        viewModelScope.launch(Dispatchers.IO) {
            mostSellingProductsUseCase.invoke().collect{ it ->
                when(it) {
                    is Resource.Success ->{
                        _state.emit(HomeContract.State.Success(mostSellingProductsList = it.data))
                    }
                    else ->{
                        extractViewMessage(it)?.let {
                            _event.postValue(HomeContract.Event.ShowMessage(it))
                        }
                    }
                }
            }
        }
    }
    private fun getCategories(){
        viewModelScope.launch(Dispatchers.IO){
            categoryUseCase.invoke().collect{ it ->
                when(it){
                    is Resource.Success ->{
                        _state.emit(HomeContract.State.Success(categoriesList = it.data))
                    }
                    else ->{
                        extractViewMessage(it)?.let {
                            _event.postValue(HomeContract.Event.ShowMessage(it))
                        }
                    }
                }
            }
        }
    }
}
