package com.sirius.driverlicense.ui.auth.auth_third_third

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher

import androidx.lifecycle.Observer
import com.sirius.driverlicense.R
import com.sirius.driverlicense.base.App
import com.sirius.driverlicense.base.ui.BaseFragment

import com.sirius.driverlicense.databinding.FragmentAuthSecondBinding
import com.sirius.driverlicense.databinding.FragmentAuthThirdThirdBinding
import com.sirius.driverlicense.ui.auth.auth_third.AuthThirdFragment


class AuthThirdThirdFragment : BaseFragment<FragmentAuthThirdThirdBinding, AuthThirdThirdViewModel>() {


    override fun setupViews() {
        super.setupViews()


    }

    override fun getLayoutRes(): Int {
        return R.layout.fragment_auth_third_third
    }

    override fun initDagger() {
        App.getInstance().appComponent.inject(this)
    }

    override fun subscribe() {
        model.goToNextScreenLiveData.observe(this, Observer {
            if (it) {
                model.goToNextScreenLiveData.value = false
                baseActivity.pushPage(AuthThirdFragment())
            }
        })
    }

    override fun setModel() {
        dataBinding.viewModel = model
    }


}