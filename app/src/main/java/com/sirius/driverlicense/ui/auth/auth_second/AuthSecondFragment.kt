package com.sirius.driverlicense.ui.auth.auth_second

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher

import androidx.lifecycle.Observer
import com.sirius.driverlicense.R
import com.sirius.driverlicense.base.App
import com.sirius.driverlicense.base.ui.BaseFragment

import com.sirius.driverlicense.databinding.FragmentAuthSecondBinding
import com.sirius.driverlicense.ui.auth.auth_first.AuthFirstFragment
import com.sirius.driverlicense.ui.auth.auth_third.AuthThirdFragment
import com.sirius.driverlicense.ui.auth.auth_third_third.AuthThirdThirdFragment


class AuthSecondFragment : BaseFragment<FragmentAuthSecondBinding, AuthSecondViewModel>() {


    override fun setupViews() {
        super.setupViews()



        dataBinding.passwordText.addTextChangedListener(object: TextWatcher{
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {

            }

            override fun afterTextChanged(s: Editable?) {
                model.setUserPassword(s.toString())
                model.isNextEnabled()
            }

        })
    }

    override fun getLayoutRes(): Int {
        return R.layout.fragment_auth_second
    }

    override fun initDagger() {
        App.getInstance().appComponent.inject(this)
    }

    override fun subscribe() {
        model.goToNextScreenLiveData.observe(this, Observer {
            if (it) {
                model.goToNextScreenLiveData.value = false
                baseActivity.pushPage(AuthThirdThirdFragment())
            }
        })

        model.changeNameScreenLiveData.observe(this, Observer {
            if (it) {
                model.changeNameScreenLiveData.value = false
                baseActivity.showPage(AuthFirstFragment())
            }
        })
    }

    override fun setModel() {
        dataBinding.viewModel = model
    }


}