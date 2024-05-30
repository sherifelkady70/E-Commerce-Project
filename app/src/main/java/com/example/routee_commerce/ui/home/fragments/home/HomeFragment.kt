package com.example.routee_commerce.ui.home.fragments.home

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.example.routee_commerce.R
import com.example.routee_commerce.databinding.FragmentHomeBinding
import com.example.routee_commerce.model.Category
import com.example.routee_commerce.model.Product
import com.example.routee_commerce.ui.base.BaseFragment
import com.example.routee_commerce.ui.home.fragments.home.adapters.CategoriesAdapter
import com.example.routee_commerce.ui.home.fragments.home.adapters.ProductsAdapter
import com.example.routee_commerce.ui.productDetails.ProductDetailsActivity
import com.route.domain.models.Products
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch


@AndroidEntryPoint
class HomeFragment : BaseFragment<HomeFragmentViewModel,FragmentHomeBinding>() {

     private val categoriesAdapter = CategoriesAdapter()
     private val mostSellingProductsAdapter = ProductsAdapter()
     private val categoryProductsAdapter = ProductsAdapter()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews()
        viewModel.doAction(HomeContract.Action.initPage)
        myObserveLiveData()
        dataBinding.lifecycleOwner = this
    }

    override fun getLayoutId(): Int = R.layout.fragment_home
    private val mViewModel : HomeContract.ViewModel by viewModels<HomeFragmentViewModel>()
    override fun initViewModel(): HomeFragmentViewModel = mViewModel as HomeFragmentViewModel

    private fun initViews() {
        categoriesAdapter.categoryClicked = { position, category ->
//            navigateToCategoriesFragment(category)
        }

        dataBinding.categoriesRv.adapter = categoriesAdapter
        dataBinding.mostSellingProductsRv.adapter = mostSellingProductsAdapter
        dataBinding.categoryProductsRv.adapter = categoryProductsAdapter
        dataBinding.categoryNameTv.text = getString(R.string.electronics)
//        categoryProductsAdapter.bindProducts()
//        mostSellingProductsAdapter.bindProducts()



    }


    private fun navigateToProductDetailsFragment(product: Product) {
        val intent = Intent(context, ProductDetailsActivity::class.java)
        intent.putExtra(Product.PRODUCT, product)
        startActivity(intent)
    }



    private fun navigateToCategoriesFragment(category: Category) {
//        val action = HomeFragmentDirections.actionHomeFragmentToCategoriesFragment(category)
//        findNavController().navigate(action)
    }



    override fun onResume() {
        super.onResume()
//        binding.categoriesShimmerViewContainer.startShimmer()
    }

    override fun onPause() {
//        binding.categoriesShimmerViewContainer.stopShimmer()
        super.onPause()

    }
    override fun onDestroyView() {
        super.onDestroyView()
        dataBinding.unbind()

    }

    private fun myObserveLiveData() {
        viewModel.event.observe(viewLifecycleOwner,::onEventChange)
        lifecycleScope.launch {
            viewModel.state.collect{
                renderView(it)
            }
        }
    }

    private fun renderView(state : HomeContract.State) {
        when(state){
            is HomeContract.State.Success ->{
                showCategories(state.categoriesList)
                showMostSellingProducts(state.mostSellingProductsList)
                showCategoryProducts(state.categoryProductsList)
            }
            is HomeContract.State.Loading ->{
                showLoadingEvent()
            }
        }
    }

    private fun onEventChange(event : HomeContract.Event) {
        when(event) {
            is HomeContract.Event.ShowMessage -> {
                showDialog(event.message.message)
            }

            is HomeContract.Event.ShowLoading -> {
              showLoading()
            }
        }
    }

    private fun showCategories(categories  : List<com.route.domain.models.Category>?) {
        dataBinding.categoriesShimmerViewContainer.hideShimmer()
            if (categories != null) {
                categoriesAdapter.bindCategories(categories)
            }
    }
    private fun showLoadingEvent() {
        dataBinding.categoriesShimmerViewContainer.showShimmer(true)
    }

    private fun showMostSellingProducts(mostSellingProducts : List<Products>?) {
        dataBinding.mostSellingProductsShimmerViewContainer.hideShimmer()
       if(mostSellingProducts != null){
           mostSellingProductsAdapter.bindProducts(mostSellingProducts)
       }
    }

    private fun showCategoryProducts(categoryProductsList : List<Products>?) {
      //  dataBinding.categoryProductsShimmerViewContainer.hideShimmer()
        if(categoryProductsList != null){
            categoryProductsAdapter.bindProducts(categoryProductsList)
        }
    }
}
