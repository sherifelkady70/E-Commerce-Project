package com.example.routee_commerce.ui.home.fragments.home

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.routee_commerce.model.HomeData
import com.example.routee_commerce.model.WishListItem
import com.example.routee_commerce.ui.base.BaseViewModel
import com.example.routee_commerce.utils.SingleLiveEvent
import com.route.domain.common.Resource
import com.route.domain.models.CartItem
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
                loadData(action.token)
            }
            is HomeContract.Action.AddProductToCart ->{
                addProductToCart(action.token,action.productId)
            }
            is HomeContract.Action.AddProductToWishList ->{
                addProductToWishList(action.token,action.productId)
            }
            is HomeContract.Action.RemoveProductFromWishList ->{
                deleteProductToWishList(action.token,action.productId)
            }
        }
    }

    private val categories: List<Category>? = null
    private val mostSellingProducts: List<Products>? = null
    private val categoryProducts: List<Products>? = null
    private val wishList: List<WishListItem>? = null
    private val cartItems: List<CartItem<Products>>? = null


    private fun loadData(token : String){
        viewModelScope.launch {
            combine(
                categoryUseCase.invoke(),
                categoryProductsUseCase.getCategoryProducts(),
                mostSellingProductsUseCase.invoke(),
                getLoggedUserWishlist(token),
                getLoggedUserCartUseCase(token)
            ){categories , categoryProducts , mostSellingProducts , wishList , userCartList ->
                var data : HomeData?= null
                if(categories is Resource.Success &&
                    categoryProducts is Resource.Success &&
                    mostSellingProducts is Resource.Success &&
                    wishList is Resource.Success){
                    data = if(userCartList is Resource.Success){
                        HomeData(
                            categoriesList = categories.data,
                            categoryProductsList = categoryProducts.data,
                            mostSellingProductsList = mostSellingProducts.data,
                            wishList = wishList.data,
                            cartList = userCartList.data?.products
                        )
                    }else{
                        HomeData(
                            categoriesList = categories.data,
                            categoryProductsList = categoryProducts.data,
                            mostSellingProductsList = mostSellingProducts.data,
                            wishList = wishList.data,
                            cartList = emptyList()
                        )
                    }
                }
                if(categories is Resource.Fail || categories is Resource.ServerError){
                    extractViewMessage(categories)?.let {
                        _event.postValue(HomeContract.Event.ShowMessage(it))
                    }
                }
                if(categoryProducts is Resource.Fail || categoryProducts is Resource.ServerError){
                    extractViewMessage(categoryProducts)?.let {
                        _event.postValue(HomeContract.Event.ShowMessage(it))
                    }
                }
                if(mostSellingProducts is Resource.Fail || mostSellingProducts is Resource.ServerError){
                    extractViewMessage(mostSellingProducts)?.let {
                        _event.postValue(HomeContract.Event.ShowMessage(it))
                    }
                }
                if(wishList is Resource.Fail || wishList is Resource.ServerError){
                    extractViewMessage(wishList)?.let {
                        _event.postValue(HomeContract.Event.ShowMessage(it))
                    }
                }
                Log.d("TAG","in view model$data")
                data
            }.collect{
                Log.d("TAG","in collect in view model$it")
                it?.let {
                    _state.emit(
                        HomeContract.State.Success(
                            it.categoriesList,it.categoryProductsList,it.mostSellingProductsList
                            ,it.wishList,it.cartList
                        )
                    )
                }
            }
        }
    }
    private fun addProductToCart(token:String,productId:String){
        viewModelScope.launch {
            addProductToCardUseCase(token,productId).collect{
                when(it){
                    is Resource.Success ->{
                        _event.postValue(
                            HomeContract.Event.ProductAddedToCartSuccess(it.data?.products!!)
                        )
                    }
                    else ->{
                        extractViewMessage(it)?.let { message ->
                            _event.postValue(HomeContract.Event.ShowMessage(message))
                        }
                    }
                }
            }
        }
    }
    private fun addProductToWishList(token: String,productId: String){
        viewModelScope.launch {
            addProductToWishListUseCase(token,productId).collect{
                when(it){
                    is Resource.Success ->{
                        _event.postValue(HomeContract.Event.ProductAddedToWishListSuccess(
                            it.data.message!!,it.data.data!!
                        ))
                    }else ->{
                        extractViewMessage(it)?.let {
                            _event.postValue(HomeContract.Event.ShowMessage(it))
                        }
                    }
                }
            }
        }
    }
    private fun deleteProductToWishList(token: String,productId: String){
        viewModelScope.launch {
            deleteProductFromWishlistUseCase(token,productId).collect{
                when(it){
                    is Resource.Success ->{
                        _event.postValue(HomeContract.Event.ProductRemovedFromWishListSuccess(
                            it.data.message!!,it.data.data!!
                        ))
                    }
                    else->{
                        extractViewMessage(it)?.let {
                            _event.postValue(HomeContract.Event.ShowMessage(it))
                        }
                    }
                }
            }
        }
    }
}
