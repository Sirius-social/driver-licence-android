package com.sirius.driverlicense.ui.main

import android.text.Editable
import android.text.TextWatcher

import androidx.lifecycle.Observer
import com.sirius.driverlicense.R
import com.sirius.driverlicense.base.App
import com.sirius.driverlicense.base.ui.BaseFragment
import com.sirius.driverlicense.databinding.FragmentAuthZeroBinding
import com.sirius.driverlicense.databinding.FragmentMainDriverBinding
import com.sirius.driverlicense.databinding.FragmentMainPoliceBinding

import com.sirius.driverlicense.ui.auth.auth_second.AuthSecondFragment


class MainDriverFragment : BaseFragment<FragmentMainDriverBinding, MainDriverViewModel>() {



    override fun setupViews() {
        super.setupViews()


        dataBinding.lastnameEditText.addTextChangedListener(object: TextWatcher{
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {

            }

            override fun afterTextChanged(s: Editable?) {
                model.setUserPass(s.toString())
                model.isNextEnabled()
            }

        })

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
    }

    override fun getLayoutRes(): Int {
        return R.layout.fragment_main_driver
    }

    override fun initDagger() {
        App.getInstance().appComponent.inject(this)
    }

    override fun subscribe() {
        model.goToNextScreenLiveData.observe(this, Observer {
            if (it) {
                model.goToNextScreenLiveData.value = false
                model.saveUser()
                //LoaderActivity.newInstance(baseActivity)
            }
        })
    }

    override fun setModel() {
        dataBinding.viewModel = model
    }



}