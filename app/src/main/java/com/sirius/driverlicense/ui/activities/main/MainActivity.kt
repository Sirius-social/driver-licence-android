package com.sirius.driverlicense.ui.activities.main

import android.app.AlertDialog
import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.lifecycle.Observer
import com.sirius.driverlicense.R
import com.sirius.driverlicense.base.App
import com.sirius.driverlicense.base.ui.BaseActivity
import com.sirius.driverlicense.databinding.ActivityMainBinding
import com.sirius.driverlicense.ui.chats.AllChatsFragment
import com.sirius.driverlicense.ui.chats.chats.ChatsFragment
import com.sirius.driverlicense.ui.contacts.ContactsFragment
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
        showPage(MenuProfileFragment())
        /* dataBinding.navigationBottom.setOnNavigationItemSelectedListener {
             if (it.itemId == R.id.navigation_profile) {
                 showPage(MenuProfileFragment())
             }

             if (it.itemId == R.id.navigation_scan_qr) {
                  showPage(MenuScanQrFragment())
             }
             if (it.itemId == R.id.navigation_chats) {
                 showPage(ContactsFragment())
             }

             return@setOnNavigationItemSelectedListener true
         }*/
        //  dataBinding.navigationBottom.onBottomNavClickListener = model.getOnBottomNavClickListner()
    }

    var dialog: AlertDialog? = null
    override fun subscribe() {
        super.subscribe()

        /* model.selectedTab.observe(this, Observer {
              dataBinding.navigationBottom.selectedTabLiveData.value = it
          })*/

        model.invitationStartLiveData.observe(this, Observer {
            val builder = AlertDialog.Builder(this)
            builder.setTitle("Connecting...")
            builder.setMessage("Please wait,secure connection is being established")
            builder.setCancelable(false)
            dialog = builder.show()
        })

        model.invitationErrorLiveData.observe(this, Observer {
            dialog?.cancel()
            model.onShowToastLiveData.postValue(it.second)
        })

        model.invitationSuccessLiveData.observe(this, Observer {
            val item = model.getMessage(it)
            dialog?.cancel()
            popPage(ChatsFragment.newInstance(item))
        })
    }


}