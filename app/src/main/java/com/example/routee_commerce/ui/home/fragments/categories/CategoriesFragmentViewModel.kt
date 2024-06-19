package com.example.routee_commerce.ui.home.fragments.categories

import androidx.lifecycle.ViewModel
import com.route.domain.usecases.CategoryUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class CategoriesFragmentViewModel @Inject constructor(
    val categoriesUseCase : CategoryUseCase
) : ViewModel() {

}