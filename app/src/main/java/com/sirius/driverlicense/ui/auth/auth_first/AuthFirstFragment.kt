package com.sirius.driverlicense.ui.auth.auth_first

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher

import androidx.lifecycle.Observer
import com.sirius.driverlicense.R
import com.sirius.driverlicense.base.App
import com.sirius.driverlicense.base.ui.BaseFragment
import com.sirius.driverlicense.databinding.FragmentAuthFirstBinding
import com.sirius.driverlicense.ui.activities.auth.AuthActivity
import com.sirius.driverlicense.ui.activities.loader.LoaderActivity


import com.sirius.driverlicense.ui.auth.auth_second.AuthSecondFragment
import com.sirius.driverlicense.ui.auth.auth_second_second.AuthSecondSecondFragment


class AuthFirstFragment : BaseFragment<FragmentAuthFirstBinding, AuthFirstViewModel>() {




    companion object {
        @JvmStatic
        fun newInstance(isPolice: Boolean): AuthFirstFragment{
            val args = Bundle()
            args.putBoolean("isPolice",isPolice)
            val fragment = AuthFirstFragment()
            fragment.arguments = args
            return fragment
        }

    }


    override fun setupViews() {
        super.setupViews()
        val isPolice = arguments?.getBoolean("isPolice") ?: false
        model.isPolice  = isPolice
        if(isPolice){
            dataBinding.icon.setImageResource(R.drawable.ic_police)
        }else{
            dataBinding.icon.setImageResource(R.drawable.ic_license)
        }
        //dataBinding.indicatorView.selectPage(1)
        dataBinding.nameEditText.addTextChangedListener(object: TextWatcher{
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {

            }
            override fun afterTextChanged(s: Editable?) {
                model.setUserName(s.toString())
                model.isNextEnabled()
            }
        })
        dataBinding.nameEditText.setText(model.getUserName())
    }

    override fun getLayoutRes(): Int {
        return R.layout.fragment_auth_first
    }

    override fun initDagger() {
        App.getInstance().appComponent.inject(this)
    }

    override fun subscribe() {

        model.goToNextScreenLiveData.observe(this, Observer {
            if (it) {
                model.goToNextScreenLiveData.value = false
                model.setUserUid("test_user")
                model.setUserPassword("test_password")
                model.saveUser()
                LoaderActivity.newInstance(baseActivity, model.isPolice)
            }
        })
    }

    override fun setModel() {
        dataBinding.viewModel = model
    }


}