package com.example.routee_commerce.ui.home.fragments.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.routee_commerce.ui.base.BaseViewModel
import com.example.routee_commerce.utils.SingleLiveEvent
import com.route.domain.common.Resource
import com.route.domain.models.Category
import com.route.domain.models.Products
import com.route.domain.usecases.CategoryProductsUseCase
import com.route.domain.usecases.CategoryUseCase
import com.route.domain.usecases.MostSellingProductsUseCase
import com.route.domain.usecases.cart.AddProductToCardUseCase
import com.route.domain.usecases.cart.GetLoggedUserCartUseCase
import com.route.domain.usecases.wishlist.AddProductToWishlistUseCase
import com.route.domain.usecases.wishlist.DeleteProductFromWishlistUseCase
import com.route.domain.usecases.wishlist.GetLoggedUserWishlistUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeFragmentViewModel @Inject constructor(
    private val categoryUseCase: CategoryUseCase,
    private val mostSellingProductsUseCase: MostSellingProductsUseCase,
    private val categoryProductsUseCase : CategoryProductsUseCase,
    private val getLoggedUserCartUseCase: GetLoggedUserCartUseCase,
    private val getLoggedUserWishlist: GetLoggedUserWishlistUseCase,
    private val addProductToCardUseCase: AddProductToCardUseCase,
    private val addProductToWishListUseCase : AddProductToWishlistUseCase,
    private val deleteProductFromWishlistUseCase: DeleteProductFromWishlistUseCase
) : BaseViewModel() , HomeContract.ViewModel {
//     val categoriesList = MutableLiveData<List<com.route.domain.models.Category>?>()


    private val _state = MutableStateFlow<HomeContract.State>(HomeContract.State.Loading)
    private val _event = SingleLiveEvent<HomeContract.Event>()
    override val event: LiveData<HomeContract.Event>
        get() {
            return _event
        }
    override val state: StateFlow<HomeContract.State> = _state
    override fun doAction(action: HomeContract.Action) { //the only fun that can view communicate with VM
        when(action){
            is HomeContract.Action.InitPage -> {
                initPage(action.token)
            }
            is HomeContract.Action.AddProductToCart ->{

            }
            is HomeContract.Action.AddProductToWishList ->{

            }
            is HomeContract.Action.RemoveProductFromWishList ->{

            }
        }
    }

    private fun initPage(token: String?) {
        getCategories()
        getMostSellingProducts()
        getCategoryProducts()
    }

    private val categories: List<Category>? = null
    private val mostSellingProducts: List<Products>? = null
    private val categoryProducts: List<Products>? = null

    private fun loadData(token : String){
        viewModelScope.launch {
            combine(

            )
        }
    }
    private fun getCategoryProducts() {
        viewModelScope.launch(Dispatchers.IO) {
            categoryProductsUseCase.getCategoryProducts().collect{ it ->
                when(it) {
                   is Resource.Success -> {
                    _state.emit(HomeContract.State.Success(categoryProductsList = it.data ,
                        categoriesList = categories, mostSellingProductsList = mostSellingProducts))
                }
                    else -> {
                        extractViewMessage(it)?.let {
                            _event.postValue(HomeContract.Event.ShowMessage(it))
                        }
                    }
                }
            }
        }
    }
    private fun getMostSellingProducts() {
        viewModelScope.launch(Dispatchers.IO) {
            mostSellingProductsUseCase.invoke().collect{ it ->
                when(it) {
                    is Resource.Success ->{
                        _state.emit(HomeContract.State.Success(mostSellingProductsList = it.data,
                            categoriesList = categories, categoryProductsList = categoryProducts))
                    }
                    else ->{
                        extractViewMessage(it)?.let {
                            _event.postValue(HomeContract.Event.ShowMessage(it))
                        }
                    }
                }
            }
        }
    }
    private fun getCategories(){
        viewModelScope.launch(Dispatchers.IO){
            categoryUseCase.invoke().collect{ it ->
                when(it){
                    is Resource.Success ->{
                        _state.emit(HomeContract.State.Success(categoriesList = it.data, categoryProductsList = categoryProducts
                        , mostSellingProductsList = mostSellingProducts))
                    }
                    else ->{
                        extractViewMessage(it)?.let {
                            _event.postValue(HomeContract.Event.ShowMessage(it))
                        }
                    }
                }
            }
        }
    }
}
