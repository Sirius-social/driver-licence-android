package com.sirius.driverlicense.ui.loading

import com.sirius.driverlicense.base.App
import com.sirius.driverlicense.base.ui.BaseFragment
import com.sirius.driverlicense.databinding.FragmentMenuProfileBinding
import com.sirius.driverlicense.ui.profile.MenuProfileViewModel

class LoadingFragment : BaseFragment<FragmentMenuProfileBinding, LoadingViewModel>(){
    override fun getLayoutRes(): Int {
       return 0
    }

    override fun subscribe() {
        TODO("Not yet implemented")
    }

    override fun setModel() {
        TODO("Not yet implemented")
    }

    override fun initDagger() {
        //App.getInstance().appComponent.inject(this)
    }
}