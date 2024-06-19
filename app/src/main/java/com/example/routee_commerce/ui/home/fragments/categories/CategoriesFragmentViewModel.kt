package com.example.routee_commerce.ui.home.fragments.categories

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.route.domain.usecases.CategoryUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

@HiltViewModel
class CategoriesFragmentViewModel @Inject constructor(
    val categoriesUseCase : CategoryUseCase,
    override val event: LiveData<CategoriesContract.Event>,
    override val state: StateFlow<CategoriesContract.State>
) : ViewModel() , CategoriesContract.CategoriesViewModel {
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

    }
}