package com.example.routee_commerce.ui.home.fragments.categories

import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.routee_commerce.ui.base.BaseViewModel
import com.example.routee_commerce.utils.SingleLiveEvent
import com.route.domain.common.Resource
import com.route.domain.models.Category
import com.route.domain.models.Subcategory
import com.route.domain.usecases.CategoryUseCase
import com.route.domain.usecases.SubCategoryUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CategoriesFragmentViewModel @Inject constructor(
    private val categoriesUseCase : CategoryUseCase,
    private val subCategoryUseCase : SubCategoryUseCase
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

    private val categoryList : List<Category>?=null
    private val subCategoryList : List<Subcategory>?=null
    private fun initPage() {
        getCategories()
        getSubCategory()
    }
    private fun getCategories() {
        viewModelScope.launch(Dispatchers.IO) {
            categoriesUseCase.invoke().collect{ list ->
                when(list) {
                   is Resource.Success ->{
                        _state.emit(CategoriesContract.State.Success(categoriesList = list.data,
                            subCategoryList = subCategoryList))
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
    private fun getSubCategory(){
        viewModelScope.launch {
            subCategoryUseCase.getSubCategory().collect{ list ->
                when(list) {
                    is Resource.Success -> {
                        _state.emit(CategoriesContract.State.Success(subCategoryList = list.data,
                            categoriesList = categoryList))
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