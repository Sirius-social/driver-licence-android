package com.sirius.driverlicense.ui.activities.loader


import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import androidx.lifecycle.Observer
import com.sirius.driverlicense.R
import com.sirius.driverlicense.base.App
import com.sirius.driverlicense.base.AppPref
import com.sirius.driverlicense.base.ui.BaseActivity
import com.sirius.driverlicense.databinding.ActivityLoaderBinding
import com.sirius.driverlicense.databinding.ActivitySplashBinding
import com.sirius.driverlicense.ui.activities.auth.AuthActivity
import com.sirius.driverlicense.ui.activities.main.MainActivity
import com.sirius.driverlicense.ui.activities.mainPolice.MainActivityPolice


class LoaderActivity : BaseActivity<ActivityLoaderBinding, LoaderActivityModel>() {


    companion object {
        @JvmStatic
        fun newInstance(context: Context, isPolice: Boolean) {
            val intent = Intent(context, LoaderActivity::class.java)
            intent.putExtra("isPolice", isPolice)
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            context.startActivity(intent)
        }
    }


    override fun getLayoutRes(): Int {
        return R.layout.activity_loader
    }

    override fun isBottomNavigationEnabled(): Boolean {
        return false
    }
    override fun initDagger() {
        App.getInstance().appComponent.inject(this)
    }


    override fun subscribe() {
        super.subscribe()
        model.initEndLiveData.observe(this, Observer {
            finishAffinity()
            if(model.isPolice){
                MainActivityPolice.newInstance(this)
            }else{
                MainActivity.newInstance(this)
            }
        })
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val isPolice = intent?.getBooleanExtra("isPolice", false) ?: false
        model.isPolice = isPolice
        model.initSdk(this,isPolice)
    }


}