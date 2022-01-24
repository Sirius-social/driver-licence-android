package com.sirius.driverlicense.ui.auth.auth_third_identity

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher

import androidx.lifecycle.Observer
import com.sirius.driverlicense.R
import com.sirius.driverlicense.base.App
import com.sirius.driverlicense.base.ui.BaseFragment

import com.sirius.driverlicense.databinding.FragmentAuthSecondBinding
import com.sirius.driverlicense.databinding.FragmentAuthThirdBinding
import com.sirius.driverlicense.databinding.FragmentAuthThirdChooseBinding
import com.sirius.driverlicense.databinding.FragmentAuthThirdIdentityBinding


class AuthThirdIChooseIdFragment : BaseFragment<FragmentAuthThirdChooseBinding, AuthThirdChooseIdViewModel>() {

    override fun setupViews() {
        super.setupViews()



    }

    override fun getLayoutRes(): Int {
        return R.layout.fragment_auth_third_choose
    }

    override fun initDagger() {
        App.getInstance().appComponent.inject(this)
    }

    override fun subscribe() {


        model.goToTypeInfoScreenLiveData.observe(this, Observer {
            if (it) {
                model.goToTypeInfoScreenLiveData.value = false
             //   baseActivity.pushPage(RegisterTypeInfoFragment())
            }
        })
    }

    override fun setModel() {
        dataBinding.viewModel = model
    }


}