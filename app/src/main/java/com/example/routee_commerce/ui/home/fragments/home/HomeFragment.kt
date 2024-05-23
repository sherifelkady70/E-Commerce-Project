package com.example.routee_commerce.ui.home.fragments.home

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import com.example.routee_commerce.R
import com.example.routee_commerce.databinding.FragmentHomeBinding
import com.example.routee_commerce.model.Category
import com.example.routee_commerce.model.Product
import com.example.routee_commerce.ui.base.BaseFragment
import com.example.routee_commerce.ui.home.fragments.home.adapters.CategoriesAdapter
import com.example.routee_commerce.ui.home.fragments.home.adapters.ProductsAdapter
import com.example.routee_commerce.ui.productDetails.ProductDetailsActivity
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class HomeFragment : BaseFragment<HomeFragmentViewModel,FragmentHomeBinding>() {

     private val categoriesAdapter = CategoriesAdapter()
     private val mostSellingProductsAdapter = ProductsAdapter()
     private val categoryProductsAdapter = ProductsAdapter()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews()
        viewModel.getCategories()
        myObserveLiveData()
        dataBinding.lifecycleOwner = this
    }

    override fun getLayoutId(): Int = R.layout.fragment_home
    private val mViewModel : HomeFragmentViewModel by viewModels()
    override fun initViewModel(): HomeFragmentViewModel = mViewModel

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
        viewModel.categoriesList.observe(viewLifecycleOwner){
            dataBinding.categoriesShimmerViewContainer.hideShimmer()
            if (it != null) {
                categoriesAdapter.bindCategories(it)
            }
        }
    }

}
