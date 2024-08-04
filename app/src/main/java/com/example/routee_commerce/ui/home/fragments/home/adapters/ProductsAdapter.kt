package com.example.routee_commerce.ui.home.fragments.home.adapters

import android.content.Context
import android.graphics.Paint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView
import com.example.routee_commerce.databinding.ItemProductBinding
import com.route.domain.models.Products


class ProductsAdapter(private var context: Context) :
    RecyclerView.Adapter<ProductsAdapter.ViewHolder>() {
    private var products: List<Products?> = emptyList()
    private var wishList: List<String?> = emptyList()
    private var cartItems: List<String?> = emptyList()
    inner class ViewHolder(val context: Context , val itemProductBinding: ItemProductBinding) :
        RecyclerView.ViewHolder(itemProductBinding.root) {

        fun bind(product: Products?, wishList: List<String?>, cartItem: List<String?>) {
            itemProductBinding.product = product
            for(item in wishList){
                if(product?.id == item){
                    itemProductBinding.isWishList = true
                }else{
                    itemProductBinding.isWishList = false
                }
            }
            for (item in cartItems){
                if(product?.id == item){
                    itemProductBinding.isCarItem = true
                }else{
                    itemProductBinding.isCarItem = false
                }
            }
            itemProductBinding.executePendingBindings()
            if (product?.priceAfterDiscount != null) {
                itemProductBinding.productPrice.text = "EGP ${product?.priceAfterDiscount}"
                itemProductBinding.productOldPrice.isVisible = true
                itemProductBinding.productOldPrice.text = "EGP ${product?.price}"
                itemProductBinding.productOldPrice.paintFlags =
                    itemProductBinding.productOldPrice.paintFlags or Paint.STRIKE_THRU_TEXT_FLAG
            } else {
                itemProductBinding.productPrice.text = "EGP ${product?.price}"
                itemProductBinding.productOldPrice.isVisible = false
            }
            itemProductBinding.reviewValueTv.text = "(${product?.ratingsAverage})"
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            context ,
            ItemProductBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun getItemCount() = products.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val product = products[position]
        holder.bind(product , wishList,cartItems)
        openProductsDetails?.let {
            holder.itemView.setOnClickListener {
                openProductsDetails?.invoke(product!!)
            }
        }
        addProductToWishListClicked?.let {
            holder.itemProductBinding.addToWishlistBtn.setOnClickListener {
                when (holder.itemProductBinding.isWishList) {
                    true -> {
                        removeProductFromWishListClicked?.invoke(product!!)
                    }

                    else -> {
                        addProductToWishListClicked?.invoke(product!!)
                    }
                }
            }
        }
        addProductToCartClicked?.let {
            holder.itemProductBinding.addToCartBtn.setOnClickListener {
                if(holder.itemProductBinding.isCarItem == true) return@setOnClickListener
                addProductToCartClicked?.invoke(product!!)
            }
        }
    }

    fun bindProducts(products: List<Products?>) {
        this.products = products
        notifyDataSetChanged()
    }

    var openProductsDetails : ((product:Products) -> Unit)?=null
    var addProductToWishListClicked: ((product: Products) -> Unit)? = null
    var addProductToCartClicked: ((product: Products) -> Unit)? = null
    var removeProductFromWishListClicked : ((product: Products) -> Unit)? = null

}
