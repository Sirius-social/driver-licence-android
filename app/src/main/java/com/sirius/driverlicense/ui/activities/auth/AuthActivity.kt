package com.sirius.driverlicense.ui.activities.auth

import android.content.Context
import android.content.Intent
import android.os.Bundle
import com.sirius.driverlicense.R
import com.sirius.driverlicense.base.App
import com.sirius.driverlicense.base.AppPref
import com.sirius.driverlicense.base.ui.BaseActivity
import com.sirius.driverlicense.databinding.ActivityAuthBinding
import com.sirius.driverlicense.ui.auth.auth_first.AuthFirstFragment
import com.sirius.driverlicense.ui.auth.auth_second_second.AuthSecondSecondFragment


class AuthActivity : BaseActivity<ActivityAuthBinding, AuthActivityModel>() {

    companion object {
        @JvmStatic
        fun newInstance(context: Context, isPolice: Boolean) {
            val intent = Intent(context, AuthActivity::class.java)
            intent.putExtra("isPolice",isPolice)
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            context.startActivity(intent)
        }
    }

    override fun isBottomNavigationEnabled(): Boolean {
        return false
    }
    override fun getLayoutRes(): Int {
        return R.layout.activity_auth
    }

    override fun initDagger() {
        App.getInstance().appComponent.inject(this)
    }

    override fun getRootFragmentId(): Int {
        return R.id.mainFrameAuth
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val isPolice =  intent?.getBooleanExtra("isPolice", false) ?: false
        if(isPolice){
            if(AppPref.getInstance().getPoliceUser()?.name.isNullOrEmpty()){
                showPage(AuthFirstFragment.newInstance(isPolice))
            }else{
                showPage(AuthSecondSecondFragment.newInstance(isPolice))
            }
        }else{
            if(AppPref.getInstance().getUser()?.name.isNullOrEmpty()){
                showPage(AuthFirstFragment.newInstance(isPolice))
            }else{
                showPage(AuthSecondSecondFragment.newInstance(isPolice))
            }
        }

    }

    override fun subscribe() {
        super.subscribe()

    }


}