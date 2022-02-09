package com.sirius.driverlicense.ui.activities.auth

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import com.sirius.driverlicense.R
import com.sirius.driverlicense.base.App
import com.sirius.driverlicense.base.AppPref
import com.sirius.driverlicense.base.ui.BaseActivity
import com.sirius.driverlicense.databinding.ActivityAuthBinding
import com.sirius.driverlicense.ui.auth.auth_first.AuthFirstFragment
import com.sirius.driverlicense.ui.auth.auth_second_second.AuthSecondSecondFragment
import com.sirius.driverlicense.utils.PermissionHelper


class AuthActivity : BaseActivity<ActivityAuthBinding, AuthActivityModel>() {

    companion object {
        @JvmStatic
        fun newInstance(context: Context, isPolice: Boolean) {
            val intent = Intent(context, AuthActivity::class.java)
            intent.putExtra("isPolice", isPolice)
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

        if (PermissionHelper.checkPermissionsForCamera(this, 1000)) {
            showNext()
        }


    }

    fun showNext() {
        val isPolice = intent?.getBooleanExtra("isPolice", false) ?: false
        if (isPolice) {
            Log.d(
                "mylog2090",
                "AppPref.getInstance().getPoliceUser()?.name.isNullOrEmpty()=" + AppPref.getInstance()
                    .getPoliceUser()?.name
            )
            if (AppPref.getInstance().getPoliceUser()?.name.isNullOrEmpty()) {
                showPage(AuthFirstFragment.newInstance(isPolice))
            } else {
                showPage(AuthSecondSecondFragment.newInstance(isPolice))
            }
        } else {
            if (AppPref.getInstance().getUser()?.name.isNullOrEmpty()) {
                showPage(AuthFirstFragment.newInstance(isPolice))
            } else {
                showPage(AuthSecondSecondFragment.newInstance(isPolice))
            }
        }

    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        PermissionHelper.onRequestPermissionsResult(
            requestCode,
            1000,
            permissions,
            grantResults,
            object : PermissionHelper.OnRequestPermissionListener {
                override fun onRequestSuccess() {
                    showNext()
                }

                override fun onRequestFail() {
                    finish()
                }

            })
    }

    override fun subscribe() {
        super.subscribe()

    }


}