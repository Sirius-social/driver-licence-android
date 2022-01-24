package com.sirius.driverlicense.ui.activities.main

import android.content.Context
import android.content.Intent
import android.os.Bundle
import com.sirius.driverlicense.R
import com.sirius.driverlicense.base.App
import com.sirius.driverlicense.base.ui.BaseActivity
import com.sirius.driverlicense.databinding.ActivityMainBinding
import com.sirius.driverlicense.ui.chats.AllChatsFragment
import com.sirius.driverlicense.ui.profile.MenuProfileFragment
import com.sirius.driverlicense.ui.scan.MenuScanQrFragment


class MainActivity : BaseActivity<ActivityMainBinding, MainActivityModel>() {

    companion object {
        @JvmStatic
        fun newInstance(context: Context) {
            val intent = Intent(context, MainActivity::class.java)
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            context.startActivity(intent)
        }
    }

    override fun getLayoutRes(): Int {
        return R.layout.activity_main
    }

    override fun initDagger() {
        App.getInstance().appComponent.inject(this)
    }

    override fun getRootFragmentId(): Int {
        return R.id.mainFrame
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        dataBinding.navigationBottom.setOnNavigationItemSelectedListener {
            if (it.itemId == R.id.navigation_profile) {
                showPage(MenuProfileFragment())
            }

            if (it.itemId == R.id.navigation_scan_qr) {
                 showPage(MenuScanQrFragment())
            }
            if (it.itemId == R.id.navigation_chats) {
                showPage(AllChatsFragment())
            }

            return@setOnNavigationItemSelectedListener true
        }
        //  dataBinding.navigationBottom.onBottomNavClickListener = model.getOnBottomNavClickListner()
    }

    override fun subscribe() {
        super.subscribe()

        /*  model.selectedTab.observe(this, Observer {
              dataBinding.navigationBottom.selectedTabLiveData.value = it
          })

          model.invitationStartLiveData.observe(this, Observer {
              // pushPage(ValidatingFragment())
              val item = model.getMessage(it)
              pushPage(ChatsFragment.newInstance(item))
          })

          model.invitationErrorLiveData.observe(this, Observer {
              pushPage(ErrorFragment.newInstance(it.second))
          })

          model.invitationSuccessLiveData.observe(this, Observer {
              val item = model.getMessage(it)
              showPage(MenuFragment())
              pushPage(ChatsFragment.newInstance(item))
          })*/
    }


}