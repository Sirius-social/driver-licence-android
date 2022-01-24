package com.sirius.driverlicense.ui.auth.auth_fourth

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher

import androidx.lifecycle.Observer
import com.sirius.driverlicense.R
import com.sirius.driverlicense.base.App
import com.sirius.driverlicense.base.ui.BaseFragment
import com.sirius.driverlicense.databinding.FragmentAuthFourthBinding

import com.sirius.driverlicense.databinding.FragmentAuthSecondBinding
import com.sirius.driverlicense.databinding.FragmentAuthThirdBinding
import com.sirius.driverlicense.ui.activities.main.MainActivity


class AuthFourthFragment : BaseFragment<FragmentAuthFourthBinding, AuthFourthViewModel>() {

    override fun setupViews() {
        super.setupViews()


        dataBinding.phoneEditText.addTextChangedListener(object: TextWatcher{
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {

            }

            override fun afterTextChanged(s: Editable?) {
                model.setPhone(s.toString())
                model.isNextEnabled()
            }

        })

        dataBinding.emailEditText.addTextChangedListener(object: TextWatcher{
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {

            }

            override fun afterTextChanged(s: Editable?) {
                model.setUserEmail(s.toString())
                model.isNextEnabled()
            }

        })
    }

    override fun getLayoutRes(): Int {
        return R.layout.fragment_auth_fourth
    }

    override fun initDagger() {
        App.getInstance().appComponent.inject(this)
    }

    override fun subscribe() {


        model.goToTypeInfoScreenLiveData.observe(this, Observer {
            if (it) {
                model.goToTypeInfoScreenLiveData.value = false
               baseActivity.finishAffinity()
               MainActivity.newInstance(baseActivity)
             //   baseActivity.pushPage(RegisterTypeInfoFragment())
            }
        })
    }

    override fun setModel() {
        dataBinding.viewModel = model
    }


}