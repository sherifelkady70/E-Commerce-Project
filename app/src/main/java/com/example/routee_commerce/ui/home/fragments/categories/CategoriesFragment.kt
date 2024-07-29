package com.example.routee_commerce.ui.home.fragments.categories

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.example.routee_commerce.R
import com.example.routee_commerce.databinding.FragmentCategoriesBinding
import com.example.routee_commerce.ui.base.BaseFragment
import com.example.routee_commerce.ui.home.fragments.categories.adapters.CategoriesAdapter
import com.example.routee_commerce.ui.home.fragments.categories.adapters.SubcategoriesAdapter
import com.example.routee_commerce.ui.home.fragments.home.HomeContract
import com.example.routee_commerce.ui.home.fragments.home.HomeFragmentViewModel
import com.route.domain.models.Subcategory

import com.squareup.picasso.Picasso
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class CategoriesFragment : BaseFragment<CategoriesFragmentViewModel,FragmentCategoriesBinding>()
{
    private val categoriesAdapter = CategoriesAdapter()
    private val subcategoriesAdapter = SubcategoriesAdapter()


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews()
        viewModel.doAction(CategoriesContract.Action.InitPage)
        myObserveLiveData()
        dataBinding.lifecycleOwner = this
        //loadCategories
    }

    override fun getLayoutId(): Int = R.layout.fragment_categories
    private val mViewModel : CategoriesContract.CategoriesViewModel by viewModels<CategoriesFragmentViewModel>()
    override fun initViewModel(): CategoriesFragmentViewModel = mViewModel as CategoriesFragmentViewModel

    private fun initCategoryCard(category: com.route.domain.models.Category?) {
        Picasso.get()
            .load(category?.image)
            .centerCrop()
            .fit()
            .into(dataBinding.cardBgImv)
        dataBinding.selectedCategoryName.text = category?.name

    }


    private fun initViews() {
        dataBinding.categoriesRv.adapter = categoriesAdapter

        dataBinding.subcategoryRv.adapter = subcategoriesAdapter

//        subcategoriesAdapter.bindSubcategories() with subcategories of the first category in categories list

        categoriesAdapter.categoryClicked = { position, category ->
            initCategoryCard(category)
            //LoadSubCategories
        }
    }

    private fun myObserveLiveData(){
        viewModel.event.observe(viewLifecycleOwner,::onEventChange)
       lifecycleScope.launch {
           viewModel.state.collect{
               when(it) {
                  is CategoriesContract.State.Success ->{
                     it.categoriesList?.let { categoriesList ->
                         showSuccessView(categoriesList)
                     }
                      it.subCategoryList.let { subCategoryList ->
                          subcategoriesAdapter.bindSubcategories(subCategoryList)
                      }
                  }
                  else->{
                      showLoading()
                  }
              }
           }
       }
    }
    private fun onEventChange(event : CategoriesContract.Event){
        when(event){
            is CategoriesContract.Event.ShowMessage -> {
                showDialog(event.message.title)
            }
            is CategoriesContract.Event.ShowLoading -> {
                showLoading()
            }
        }
    }
    private fun renderView(state : CategoriesContract.State){
        when(state){
            is CategoriesContract.State.Success -> {
                showSuccessView(state.categoriesList)
                //showSubCategoriesList(state.subCategoryList)
                subcategoriesAdapter.bindSubcategories(state.subCategoryList)
            }
            is CategoriesContract.State.Loading -> {
                showLoadingView()
            }
        }
    }
    private fun showLoadingView() {
        dataBinding.categoriesShimmerViewContainer.isVisible = true
        dataBinding.categoriesShimmerViewContainer.startShimmer()
        dataBinding.errorView.isVisible = false
        dataBinding.successView.isVisible = false
    }


    private fun showSuccessView(categories: List<com.route.domain.models.Category>?) {

        categoriesAdapter.bindCategories(categories)
        dataBinding.successView.isVisible = true
        dataBinding.errorView.isVisible = false
        dataBinding.categoriesShimmerViewContainer.isVisible = false
        dataBinding.categoriesShimmerViewContainer.stopShimmer()
        initCategoryCard(categories?.get(0))
        categories?.get(0)?.let {
            //LoadSubCategories
        }


    }
    private fun showSubCategoriesList(subCategory : List<Subcategory>?){
        subcategoriesAdapter.bindSubcategories(subCategory)
        dataBinding.successView.isVisible = true
        dataBinding.errorView.isVisible = false
        dataBinding.categoriesShimmerViewContainer.isVisible = false
        dataBinding.categoriesShimmerViewContainer.stopShimmer()
        subCategory?.get(0)?.let {
            //LoadSubCategories
        }
    }

    private fun showErrorView(message: String) {
        dataBinding.errorView.isVisible = true
        dataBinding.successView.isVisible = false
        dataBinding.categoriesShimmerViewContainer.isVisible = false
        dataBinding.categoriesShimmerViewContainer.stopShimmer()
        dataBinding.errorMessage.text = message
        dataBinding.tryAgainBtn.setOnClickListener {
            //LoadCategories
        }
    }


    override fun onResume() {
        super.onResume()
        dataBinding.categoriesShimmerViewContainer.startShimmer()
    }

    override fun onPause() {
        dataBinding.categoriesShimmerViewContainer.stopShimmer()
        super.onPause()

    }


}
