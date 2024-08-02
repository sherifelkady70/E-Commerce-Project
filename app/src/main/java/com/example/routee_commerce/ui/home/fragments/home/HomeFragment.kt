package com.example.routee_commerce.ui.home.fragments.home

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.routee_commerce.R
import com.example.routee_commerce.databinding.FragmentHomeBinding
import com.example.routee_commerce.model.Category
import com.example.routee_commerce.model.Product
import com.example.routee_commerce.ui.base.BaseFragment
import com.example.routee_commerce.ui.cart.CartActivity
import com.example.routee_commerce.ui.home.fragments.home.adapters.CategoriesAdapter
import com.example.routee_commerce.ui.home.fragments.home.adapters.ProductsAdapter
import com.example.routee_commerce.ui.productDetails.ProductDetailsActivity
import com.example.routee_commerce.ui.userAuthentication.activity.UserAuthenticationActivity
import com.example.routee_commerce.utils.Constants
import com.example.routee_commerce.utils.UserDataFiled
import com.example.routee_commerce.utils.UserDataUtils
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
        loadData()
        myObserveLiveData()
        dataBinding.lifecycleOwner = this
    }

    override fun getLayoutId(): Int = R.layout.fragment_home
    private val mViewModel : HomeContract.ViewModel by viewModels<HomeFragmentViewModel>()
    override fun initViewModel(): HomeFragmentViewModel = mViewModel as HomeFragmentViewModel

    private fun loadData(){
        Log.e("TAG", "onLoadPage")
        val token = UserDataUtils().getUserData(requireContext(), UserDataFiled.TOKEN)
        if (token != null) {
            viewModel.doAction(HomeContract.Action.InitPage(token))
        } else {
            showDialog(
                message = "Login again",
                posBtnTitle = "",
                onPosBtnClick = {
                    startActivity(
                        Intent(requireActivity(), UserAuthenticationActivity::class.java)
                    )
                    requireActivity().finish()
                },
            )
        }
    }
    private fun initViews() {
        dataBinding
        categoryProductsAdapter.addProductToCartClicked = { product ->
            //navigateToCartActivity(product)
        }
        mostSellingProductsAdapter.addProductToCartClicked = { product ->
           // navigateToCartActivity(product)
        }
        categoriesAdapter.categoryClicked = { position, category ->
//            navigateToCategoriesFragment(category)
        }
        categoryProductsAdapter.addProductToWishListClicked = { product ->
        }
        dataBinding.categoriesRv.adapter = categoriesAdapter
        dataBinding.mostSellingProductsRv.adapter = mostSellingProductsAdapter
        dataBinding.categoryProductsRv.adapter = categoryProductsAdapter
        //dataBinding.categoryNameTv.text = getString(R.string.electronics)
//        categoryProductsAdapter.bindProducts()
//        mostSellingProductsAdapter.bindProducts()

    }
    private fun navigateToCartActivity(product: Products){
        val intent = Intent(context , CartActivity::class.java)
        intent.putExtra(Constants.CART_KEY , product)
        startActivity(intent)
    }
    private fun navigateToProductDetailsFragment(product: Product) {
        val intent = Intent(context, ProductDetailsActivity::class.java)
        intent.putExtra(Product.PRODUCT, product)
        startActivity(intent)
    }
    private fun navigateToCategoriesFragment(category: com.route.domain.models.Category) {

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
        dataBinding.categoryProductsShimmerViewContainer.hideShimmer()
        if(categoryProductsList != null){
            categoryProductsAdapter.bindProducts(categoryProductsList)
        }
    }
}
