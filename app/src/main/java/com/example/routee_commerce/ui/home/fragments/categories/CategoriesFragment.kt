package com.example.routee_commerce.ui.home.fragments.categories

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.example.routee_commerce.databinding.FragmentCategoriesBinding
import com.example.routee_commerce.model.Category
import com.example.routee_commerce.ui.home.fragments.categories.adapters.CategoriesAdapter
import com.example.routee_commerce.ui.home.fragments.categories.adapters.SubcategoriesAdapter

import com.squareup.picasso.Picasso
import kotlinx.coroutines.launch

class CategoriesFragment : Fragment() {
    private lateinit var binding: FragmentCategoriesBinding
    val categoriesAdapter= CategoriesAdapter()
     val subcategoriesAdapter= SubcategoriesAdapter()
    lateinit var viewModel: CategoriesFragmentViewModel
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        viewModel = ViewModelProvider(this)[CategoriesFragmentViewModel::class.java]
        binding = FragmentCategoriesBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews()
        viewModel.doAction(CategoriesContract.Action.InitPage)
        binding.lifecycleOwner = this
        //loadCategories
    }

    private fun initCategoryCard(category: com.route.domain.models.Category?) {
        Picasso.get()
            .load(category?.image)
            .centerCrop()
            .fit()
            .into(binding.cardBgImv)
        binding.selectedCategoryName.text = category?.name

    }


    private fun initViews() {
        binding.categoriesRv.adapter = categoriesAdapter

        binding.subcategoryRv.adapter = subcategoriesAdapter

//        subcategoriesAdapter.bindSubcategories() with subcategories of the first category in categories list

        categoriesAdapter.categoryClicked = { position, category ->
            initCategoryCard(category)
            //LoadSubCategories
        }
    }

    private fun observeLiveData(){
        viewModel.event.observe(viewLifecycleOwner,::onEventChange)
       lifecycleScope.launch {
           viewModel.state.collect{
               renderView(it)
           }
       }
    }
    private fun onEventChange(event : CategoriesContract.Event){
        when(event){
            is CategoriesContract.Event.ShowMessage -> {

            }
            is CategoriesContract.Event.ShowLoading -> {

            }
        }
    }
    private fun renderView(state : CategoriesContract.State){
        when(state){
            is CategoriesContract.State.Success -> {
                showSuccessView(state.categoriesList)
            }
            is CategoriesContract.State.Loading -> {
                showLoadingView()
            }
        }
    }
    private fun showLoadingView() {
        binding.categoriesShimmerViewContainer.isVisible = true
        binding.categoriesShimmerViewContainer.startShimmer()
        binding.errorView.isVisible = false
        binding.successView.isVisible = false
    }


    private fun showSuccessView(categories: List<com.route.domain.models.Category?>) {

        categoriesAdapter.bindCategories(categories)
        binding.successView.isVisible = true
        binding.errorView.isVisible = false
        binding.categoriesShimmerViewContainer.isVisible = false
        binding.categoriesShimmerViewContainer.stopShimmer()
        initCategoryCard(categories[0])
        categories[0]?.let {
            //LoadSubCategories

        }


    }


    private fun showErrorView(message: String) {
        binding.errorView.isVisible = true
        binding.successView.isVisible = false
        binding.categoriesShimmerViewContainer.isVisible = false
        binding.categoriesShimmerViewContainer.stopShimmer()
        binding.errorMessage.text = message
        binding.tryAgainBtn.setOnClickListener {
            //LoadCategories
        }
    }


    override fun onResume() {
        super.onResume()
        binding.categoriesShimmerViewContainer.startShimmer()
    }

    override fun onPause() {
        binding.categoriesShimmerViewContainer.stopShimmer()
        super.onPause()

    }


}
