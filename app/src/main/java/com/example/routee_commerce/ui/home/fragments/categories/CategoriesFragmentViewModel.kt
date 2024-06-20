package com.example.routee_commerce.ui.home.fragments.categories

import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.routee_commerce.ui.base.BaseViewModel
import com.example.routee_commerce.utils.SingleLiveEvent
import com.route.domain.common.Resource
import com.route.domain.usecases.CategoryUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CategoriesFragmentViewModel @Inject constructor(
    private val categoriesUseCase : CategoryUseCase,
) : BaseViewModel() , CategoriesContract.CategoriesViewModel {


    private val _state = MutableStateFlow<CategoriesContract.State>(CategoriesContract.State.Loading)
    private val _event = SingleLiveEvent<CategoriesContract.Event>()

    override val event: LiveData<CategoriesContract.Event>
        get() {
            return _event
        }
    override val state: StateFlow<CategoriesContract.State> = _state
    override fun doAction(action: CategoriesContract.Action) {
        when (action){
            CategoriesContract.Action.InitPage -> {
                initPage()
            }
        }
    }


    private fun initPage() {
        getCategories()
    }
    private fun getCategories() {
        viewModelScope.launch(Dispatchers.IO) {
            categoriesUseCase.invoke().collect{ list ->
                when(list) {
                   is Resource.Success ->{
                        _state.emit(CategoriesContract.State.Success(list.data))
                    }
                    else -> {
                        extractViewMessage(list)?.let {
                            _event.postValue(CategoriesContract.Event.ShowMessage(it))
                        }
                    }
                }
            }
        }
    }
}