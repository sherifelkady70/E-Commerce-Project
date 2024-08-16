package com.example.routee_commerce.ui.home.fragments.home

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.example.routee_commerce.R
import com.example.routee_commerce.databinding.FragmentHomeBinding
import com.example.routee_commerce.model.Product
import com.example.routee_commerce.ui.base.BaseFragment
import com.example.routee_commerce.ui.cart.CartActivity
import com.example.routee_commerce.ui.home.activity.MainActivity
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
     private val mostSellingProductsAdapter by lazy { ProductsAdapter(requireContext()) }
     private val categoryProductsAdapter by lazy {   ProductsAdapter(requireContext()) }


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
            Log.e("TAG", "$token")
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

    private fun initViews(){
        val token = UserDataUtils().getUserData(requireContext(),UserDataFiled.TOKEN)
        Log.d("TAG","token of user $token")
        dataBinding.categoriesRv.adapter = categoriesAdapter
//        categoriesAdapter.categoryClicked ={category ->
//        }
        initCategoryProductsAdapters(token)
        initMostSellingAdapters(token)
    }
    private fun initCategoryProductsAdapters(token : String?) {
        dataBinding.categoryProductsRv.adapter = categoryProductsAdapter
//        categoryProductsAdapter.openProductsDetails={
//        }
        categoryProductsAdapter.addProductToCartClicked = { product ->
            token?.let {
                Log.d("TAG","in add product to cart click it = $it")
                Log.d("TAG","in add product to cart click token = $token")
                viewModel.doAction(HomeContract.Action.AddProductToCart(it,product.id!!))
            }
        }
        categoryProductsAdapter.addProductToWishListClicked ={ product ->
            token?.let {
                viewModel.doAction(HomeContract.Action.AddProductToWishList(token,product.id!!))
                Log.d("TAG","in add product to cart click token = $token")
            }
        }
        categoryProductsAdapter.removeProductFromWishListClicked ={ product ->
            token?.let {
                viewModel.doAction(HomeContract.Action.RemoveProductFromWishList(token,product.id!!))
            }
        }
    }
    private fun initMostSellingAdapters(token : String?) {
        dataBinding.mostSellingProductsRv.adapter = mostSellingProductsAdapter
//        mostSellingProductsAdapter.openProductsDetails ={
//        }
        mostSellingProductsAdapter.addProductToCartClicked = { product ->
            token?.let {
                viewModel.doAction(HomeContract.Action.AddProductToCart(it,product.id!!))
            }
        }
        mostSellingProductsAdapter.addProductToWishListClicked = { product ->
            token?.let {
                viewModel.doAction(HomeContract.Action.AddProductToWishList(it,product.id!!))
            }
        }
        mostSellingProductsAdapter.removeProductFromWishListClicked = { product ->
            token?.let {
                viewModel.doAction(HomeContract.Action.RemoveProductFromWishList(it,product.id!!))
            }
        }
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
//    private fun navigateToCategoriesFragment(category: com.route.domain.models.Category) {
//        val action =  actionHomeFragmentToCategoriesFragment(category)
//        findNavController().navigate(action)
//    }
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
        Log.d("TAG","in observe live data fun ")
        viewModel.event.observe(viewLifecycleOwner,::onEventChange)
        lifecycleScope.launch {
            viewModel.state.collect{
                renderView(it)
            }
        }
    }

    private fun renderView(state : HomeContract.State) {
        Log.d("TAG","in render view fun $state")
        when(state){
            is HomeContract.State.Idle->{}
            is HomeContract.State.Loading ->{
                showLoading()
            }
            is HomeContract.State.Success ->{
                hideLoading()
                //showCategories(state.categoriesList)
                state.categoriesList?.let {
                    Log.d("TAG","category state $it")
                    showCategories(it)
                }
                state.mostSellingProductsList?.let {
                    Log.d("TAG","most selling state $it")
                    showMostSellingProducts(it)
                }
                state.categoryProductsList?.let {
                    Log.d("TAG","products state $it")
                    showCategoryProducts(it)
                }
//                showMostSellingProducts(state.mostSellingProductsList)
//                showCategoryProducts(state.categoryProductsList)
            }
        }
    }

    private fun onEventChange(event : HomeContract.Event) {
        Log.d("TAG","in event fun $event")
        when(event) {
            is HomeContract.Event.ShowMessage -> {
                showSnakeBar(event.message.title ?: "fail in something")
                Log.d("TAG","message in event of snake bar ${event.message.message}")
            }
            is HomeContract.Event.ProductAddedToCartSuccess -> {
                Log.d("TAG","in add to cart ${event.cartItems}")
                val cartIds = event.cartItems?.map { it.product }
                UserDataUtils().saveUserInfo(
                    requireContext(),
                    UserDataFiled.CART_ITEM_COUNT,
                    cartIds?.size.toString()
                )
                categoryProductsAdapter.setCartItems(cartIds ?: emptyList())
                mostSellingProductsAdapter.setCartItems(cartIds ?: emptyList())
                (activity as MainActivity).updateCartCount()
            }
            is HomeContract.Event.ProductRemovedFromWishListSuccess ->{
                Log.d("TAG"," in remove from wishlist ${event.wishList}")
                categoryProductsAdapter.setWishListData(event.wishList)
                mostSellingProductsAdapter.setWishListData(event.wishList)
                showSnakeBar(event.message)
            }

            is HomeContract.Event.ProductAddedToWishListSuccess ->{
                Log.d("TAG","in add to wishlist ${event.wishList}")
                categoryProductsAdapter.setWishListData(event.wishList)
                mostSellingProductsAdapter.setWishListData(event.wishList)
                showSnakeBar(event.message)
            }
            is HomeContract.Event.ShowLoading ->{
                Log.d("TAG","in showLoading event")
                showLoadingEvent()
            }
        }
    }

    private fun showCategories(categories  : List<com.route.domain.models.Category>?) {
        Log.d("TAG","in show categories $categories")
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
