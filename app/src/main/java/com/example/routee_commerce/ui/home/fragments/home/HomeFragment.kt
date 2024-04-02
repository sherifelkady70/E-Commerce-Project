package com.example.routee_commerce.ui.home.fragments.home

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.routee_commerce.R
import com.example.routee_commerce.databinding.FragmentHomeBinding
import com.example.routee_commerce.model.Category
import com.example.routee_commerce.model.Product
import com.example.routee_commerce.ui.home.fragments.home.adapters.CategoriesAdapter
import com.example.routee_commerce.ui.home.fragments.home.adapters.ProductsAdapter
import com.example.routee_commerce.ui.productDetails.ProductDetailsActivity


class HomeFragment : Fragment() {
    private lateinit var binding: FragmentHomeBinding
     private val categoriesAdapter = CategoriesAdapter()
     private val mostSellingProductsAdapter = ProductsAdapter()
     private val categoryProductsAdapter = ProductsAdapter()


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews()

    }

    private fun initViews() {
        categoriesAdapter.categoryClicked = { position, category ->
//            navigateToCategoriesFragment(category)
        }

        binding.categoriesRv.adapter = categoriesAdapter
        binding.mostSellingProductsRv.adapter = mostSellingProductsAdapter
        binding.categoryProductsRv.adapter = categoryProductsAdapter
        binding.categoryNameTv.text = getString(R.string.electronics)
//        categoryProductsAdapter.bindProducts()
//        mostSellingProductsAdapter.bindProducts()
//        categoriesAdapter.bindCategories()

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
//        binding.categoriesShimmerViewContainer.startShimmerAnimation()
    }

    override fun onPause() {
//        binding.categoriesShimmerViewContainer.stopShimmerAnimation()
        super.onPause()

    }
    override fun onDestroyView() {
        super.onDestroyView()
        binding.unbind()

    }


}
