package com.example.routee_commerce.ui.userAuthentication.fragments.register

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.routee_commerce.R
import com.example.routee_commerce.databinding.FragmentRegisterBinding
import com.example.routee_commerce.ui.home.activity.MainActivity
import com.example.routee_commerce.utils.hideKeyboard
import com.google.android.material.snackbar.Snackbar


class RegisterFragment : Fragment() {
    private lateinit var viewBinding: FragmentRegisterBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        viewBinding = FragmentRegisterBinding.inflate(inflater, container, false)
        return viewBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        hideKeyboard()
        initViews()
    }

    private fun initViews() {
        viewBinding.registerBtn.setOnClickListener {
            //register
        }
        viewBinding.loginDoHaveAccountTv.setOnClickListener {
            //go to login
        }
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

    private fun navigateToLogin() {
        findNavController().navigate(R.id.action_registerFragment_to_loginFragment3)
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
