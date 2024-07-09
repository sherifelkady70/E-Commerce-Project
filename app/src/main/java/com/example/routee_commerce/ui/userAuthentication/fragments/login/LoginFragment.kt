package com.example.routee_commerce.ui.userAuthentication.fragments.login

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.routee_commerce.R
import com.example.routee_commerce.databinding.FragmentLoginBinding
import com.example.routee_commerce.ui.base.BaseFragment
import com.example.routee_commerce.ui.home.activity.MainActivity
import com.example.routee_commerce.utils.hideKeyboard
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class LoginFragment : BaseFragment<LoginViewModel,FragmentLoginBinding>() {
    private lateinit var viewBinding: FragmentLoginBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewBinding = FragmentLoginBinding.inflate(inflater, container, false)
        return viewBinding.root
    }
    private val loginVM : LoginViewModel by viewModels<LoginViewModel>()
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewBinding.loginVM = loginVM
        hideKeyboard()
        initViews()
    }

    override fun getLayoutId(): Int  = R.layout.fragment_login

    override fun initViewModel(): LoginViewModel = loginVM

    private fun initViews() {
        viewBinding.loginBtn.setOnClickListener {
           // intent(MainActivity())
        }
        viewBinding.donTHaveAnAccountTv.setOnClickListener {
           navigateToRegister()
        }

    }


    private fun showSuccessView() {
        viewBinding.icNext.isVisible = true
        viewBinding.progressBar.isVisible = false
    }

    private fun showErrorView(message: String) {
        viewBinding.icNext.isVisible = true
        viewBinding.progressBar.isVisible = false
        Snackbar.make(requireView(), message, Snackbar.LENGTH_SHORT)
            .setAnimationMode(Snackbar.ANIMATION_MODE_SLIDE)
            .setBackgroundTint(resources.getColor(R.color.white))
            .show()
    }

    private fun showLoadingView() {
        viewBinding.icNext.isVisible = false
        viewBinding.progressBar.isVisible = true
    }


    private fun navigateToRegister() {
        findNavController().navigate(R.id.action_loginFragment_to_registerFragment)
    }

    fun intent(activity:AppCompatActivity){
        startActivity(Intent(requireActivity(),activity::class.java))
    }
    private fun navigateToHome() {
        startActivity(Intent(activity, MainActivity::class.java))
        requireActivity().finish()
    }

    private fun hideKeyboard() {
        view?.hideKeyboard(activity as AppCompatActivity?)
    }

    override fun onDestroy() {
        super.onDestroy()
        viewBinding.unbind()
    }
}
