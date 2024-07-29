package com.example.routee_commerce.ui.userAuthentication.fragments.login

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.routee_commerce.R
import com.example.routee_commerce.databinding.FragmentLoginBinding
import com.example.routee_commerce.ui.base.BaseFragment
import com.example.routee_commerce.ui.home.activity.MainActivity
import com.example.routee_commerce.utils.UserDataUtils
import com.example.routee_commerce.utils.UserDataFiled
import com.example.routee_commerce.utils.hideKeyboard
import com.google.android.material.snackbar.Snackbar
import com.route.domain.models.AuthenticationResponse
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch


@AndroidEntryPoint
class LoginFragment : BaseFragment<LoginViewModel,FragmentLoginBinding>() {
     val loginVM : LoginViewModel by viewModels<LoginViewModel>()
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        hideKeyboard()
        initViews()
        observeData()
    }

    override fun getLayoutId(): Int  = R.layout.fragment_login

    override fun initViewModel(): LoginViewModel = loginVM

    private fun initViews() {
        dataBinding.loginVM = loginVM
        dataBinding.lifecycleOwner = this
        dataBinding.loginBtn.setOnClickListener {
          loginVM.doAction(LoginContract.Action.Login)
        }
        dataBinding.donTHaveAnAccountTv.setOnClickListener {
           navigateToRegister()
        }

    }
    private fun observeData() {
        loginVM.event.observe(viewLifecycleOwner){
            when(it){
                is LoginContract.Event.ErrorMessage ->{
                    showErrorView(it.message.message!!)
                }
            }
        }
        lifecycleScope.launch {
            loginVM.state.collect{
                when(it){
                    is LoginContract.State.Pending -> showLoadingView()
                    is LoginContract.State.Logged -> navigateToHome(it.response)
                    is LoginContract.State.Logging -> showSuccessView()
                }
            }
        }
    }

    private fun showSuccessView() {
        dataBinding.icNext.isVisible = true
        dataBinding.progressBar.isVisible = false
    }

    private fun showErrorView(message: String) {
        dataBinding.icNext.isVisible = true
        dataBinding.progressBar.isVisible = false
        Snackbar.make(requireView(), message, Snackbar.LENGTH_SHORT)
            .setAnimationMode(Snackbar.ANIMATION_MODE_SLIDE)
            .setBackgroundTint(resources.getColor(R.color.white))
            .show()
    }

    private fun showLoadingView() {
        dataBinding.icNext.isVisible = false
        dataBinding.progressBar.isVisible = true
    }


    private fun navigateToRegister() {
        findNavController().navigate(R.id.action_loginFragment_to_registerFragment)
    }

    fun intent(activity:AppCompatActivity){
        startActivity(Intent(requireActivity(),activity::class.java))
    }
    private fun navigateToHome(userData :AuthenticationResponse) {
        UserDataUtils().saveUserInfo(requireContext(), UserDataFiled.TOKEN, userData.token)
        UserDataUtils().saveUserInfo(requireContext(), UserDataFiled.ROLE, userData.user?.role)
        UserDataUtils().saveUserInfo(requireContext(),UserDataFiled.NAME,userData.user?.name)
        UserDataUtils().saveUserInfo(requireContext(),UserDataFiled.EMAIL,userData.user?.email)
        UserDataUtils().saveUserInfo(requireContext(),UserDataFiled.CART_ITEM_COUNT,null)
        startActivity(Intent(activity, MainActivity::class.java))
        requireActivity().finish()
    }

    private fun hideKeyboard() {
        view?.hideKeyboard(activity as AppCompatActivity?)
    }

    override fun onDestroy() {
        super.onDestroy()
        dataBinding.unbind()
    }
}
