package com.sirius.driverlicense.ui.activities.splashPolice


import android.os.Bundle
import android.os.Handler
import com.sirius.driverlicense.R
import com.sirius.driverlicense.base.App
import com.sirius.driverlicense.base.AppPref
import com.sirius.driverlicense.base.ui.BaseActivity
import com.sirius.driverlicense.databinding.ActivitySplashBinding
import com.sirius.driverlicense.ui.activities.auth.AuthActivity
import com.sirius.driverlicense.ui.activities.loader.LoaderActivity
import com.sirius.driverlicense.ui.activities.tutorial.TutorialActivity


class SplashPoliceActivity : BaseActivity<ActivitySplashBinding, SplashPoliceActivityModel>() {
    override fun getLayoutRes(): Int {
        return R.layout.activity_splash
    }

    override fun isBottomNavigationEnabled(): Boolean {
        return false
    }

    override fun initDagger() {
        App.getInstance().appComponent.inject(this)
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (AppPref.getInstance().isLoggedIn()) {
            LoaderActivity.newInstance(this,true)
        } else {
          //  if(AppPref.getInstance().isTutorialDone()){
                AuthActivity.newInstance(this, true)
         //   }else{
               // TutorialActivity.newInstance(this)
          //  }
        }
        finish()
    }

}