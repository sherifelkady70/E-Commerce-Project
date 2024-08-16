package com.example.routee_commerce.ui.home.activity

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isGone
import androidx.core.view.isVisible
import androidx.databinding.DataBindingUtil
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI
import com.example.routee_commerce.R
import com.example.routee_commerce.databinding.ActivityMainBinding
import com.example.routee_commerce.ui.cart.CartActivity
import com.example.routee_commerce.utils.UserDataFiled
import com.example.routee_commerce.utils.UserDataUtils
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        setContentView(binding.root)
        makeStatusBarTransparentAndIconsClear()
        initViews()
        binding.content.header.cart.setOnClickListener {
            startActivity(Intent(this@MainActivity , CartActivity::class.java))
        }
    }

    fun initViews(){
        val navHost = supportFragmentManager.findFragmentById(R.id.home_host_fragment) as NavHostFragment
        val navController = navHost.navController
        NavigationUI.setupWithNavController(binding.content.bottomNav, navController)
    }
    private fun makeStatusBarTransparentAndIconsClear() {
        window.decorView.systemUiVisibility = (View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                or View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN or View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR)
        window.statusBarColor = Color.TRANSPARENT
    }

    fun updateCartCount(){
        val cartItemCount =
            UserDataUtils().getUserData(this, UserDataFiled.CART_ITEM_COUNT)

        if (cartItemCount == "0" || cartItemCount == null) {
            binding.content.header.counterView.isGone = true
        } else {
            binding.content.header.counterView.isVisible = true
            binding.content.header.cartItemsCounter.text = cartItemCount
        }
    }
}
