package com.example.routee_commerce.ui.base

import android.app.AlertDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import com.example.routee_commerce.R
import com.google.android.material.snackbar.Snackbar


abstract class BaseFragment<VM:BaseViewModel,DB:ViewDataBinding> : Fragment() {

    lateinit var viewModel : VM
    lateinit var dataBinding : DB
    private var dialog : AlertDialog?=null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewModel = initViewModel()
        dataBinding = DataBindingUtil.inflate(layoutInflater,getLayoutId(),container,false)
        dataBinding.lifecycleOwner=this
        return dataBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        observeLiveData()
    }

    abstract fun getLayoutId(): Int

    abstract fun initViewModel() : VM


     fun observeLiveData(){
        viewModel.isLoading.observe(viewLifecycleOwner){ loading ->
            if(loading){
                showLoading()
            }else{
                hideLoading()
            }
        }

        viewModel.viewMessage.observe(viewLifecycleOwner) { vMessage ->
            showDialog(
                vMessage.title, vMessage.message, vMessage.posBtnTitle, vMessage.onPosBtnClick,
                vMessage.onNegBtnClick,
                vMessage.negBtnTitle
            )
        }
    }

    fun showLoading(){
        val builder = AlertDialog.Builder(activity)
        builder.setView(R.layout.loading)
        dialog = builder.create()
        dialog?.show()
    }
    fun hideLoading(){
        dialog?.dismiss()
    }

    fun showDialog(
        title: String? = null,
        message: String? = null,
        posBtnTitle: String? = null,
        onPosBtnClick: (() -> Unit)? = null,
        onNegBtnClick: (() -> Unit)? = null,
        negBtnTitle: String? = null,


        ) {
        val myDialog = AlertDialog.Builder(activity)
        myDialog.setTitle(title)
        myDialog.setMessage(message)
        posBtnTitle.let {
            myDialog.setPositiveButton(posBtnTitle
            ) { dialog, _ ->
                dialog?.dismiss()
                onPosBtnClick?.invoke()
            }
        }
        negBtnTitle.let {
            myDialog.setNegativeButton(negBtnTitle
            ) { dialog, _ ->
                dialog?.dismiss()
                onNegBtnClick?.invoke()
            }
        }

        myDialog.create().show()

    }


    fun showSnakeBar(message: String) {
        Snackbar.make(requireView(), message, Snackbar.LENGTH_SHORT)
            .setAnimationMode(Snackbar.ANIMATION_MODE_SLIDE)
            .setBackgroundTint(resources.getColor(R.color.black))
            .show()
    }
}