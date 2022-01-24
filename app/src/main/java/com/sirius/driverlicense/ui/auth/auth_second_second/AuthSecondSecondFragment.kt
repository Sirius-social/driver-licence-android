package com.sirius.driverlicense.ui.auth.auth_second_second

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher

import androidx.lifecycle.Observer
import com.sirius.driverlicense.R
import com.sirius.driverlicense.base.App
import com.sirius.driverlicense.base.ui.BaseFragment

import com.sirius.driverlicense.databinding.FragmentAuthSecondBinding
import com.sirius.driverlicense.databinding.FragmentAuthSecondSecondBinding
import com.sirius.driverlicense.ui.activities.loader.LoaderActivity

import com.sirius.driverlicense.ui.auth.auth_first.AuthFirstFragment



class AuthSecondSecondFragment : BaseFragment<FragmentAuthSecondSecondBinding, AuthSecondSecondViewModel>() {



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
        model.isPolice = isPolice
        if(isPolice){
            dataBinding.icon.setImageResource(R.drawable.ic_police)
        }else{
            dataBinding.icon.setImageResource(R.drawable.ic_license)
        }

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

        dataBinding.nameEditText.addTextChangedListener(object: TextWatcher{
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {

            }

            override fun afterTextChanged(s: Editable?) {
                model.setUserUid(s.toString())
                model.isNextEnabled()
            }

        })
    }

    override fun getLayoutRes(): Int {
        return R.layout.fragment_auth_second_second
    }

    override fun initDagger() {
        App.getInstance().appComponent.inject(this)
    }

    override fun subscribe() {
        model.goToNextScreenLiveData.observe(this, Observer {
            if (it) {
                model.goToNextScreenLiveData.value = false
                model.saveUser()
                LoaderActivity.newInstance(baseActivity, model.isPolice)
              //  baseActivity.pushPage(AuthThirdThirdFragment())
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