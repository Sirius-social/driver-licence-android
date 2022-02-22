package com.sirius.driverlicense.ui.activities.mainPolice

import android.app.AlertDialog
import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.lifecycle.Observer
import com.sirius.driverlicense.R
import com.sirius.driverlicense.base.App
import com.sirius.driverlicense.base.ui.BaseActivity
import com.sirius.driverlicense.databinding.ActivityMainBinding
import com.sirius.driverlicense.ui.chats.chats.ChatsFragment
import com.sirius.driverlicense.ui.main.MainPoliceFragment
import com.sirius.driverlicense.ui.police.PoliceRequesterFragment
import com.sirius.driverlicense.ui.profile.MenuProfileFragment


class MainActivityPolice : BaseActivity<ActivityMainBinding, MainActivityModelPolice>() {

    companion object {
        @JvmStatic
        fun newInstance(context: Context) {
            val intent = Intent(context, MainActivityPolice::class.java)
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
      //  dataBinding.navigationBottom.onBottomNavClickListener = model.getOnBottomNavClickListner()
    }

    var dialog: AlertDialog? = null

    override fun subscribe() {
        super.subscribe()
        showPage(MainPoliceFragment())


        model.invitationStartLiveData.observe(this, Observer {
            if(it != null){
                model.invitationStartLiveData.value = null
                val builder = AlertDialog.Builder(this)
                builder.setTitle("Connecting...")
                builder.setMessage("Please wait,secure connection is being established")
                builder.setCancelable(false)
                dialog = builder.show()
            }
        })

        model.invitationErrorLiveData.observe(this, Observer {
            if(it!=null){
                model.invitationErrorLiveData.value = null
                dialog?.cancel()
                model.onShowToastLiveData.postValue(it.second)
            }

        })

       model.invitationSuccessLiveData.observe(this, Observer {
           if(it!=null){
               model.invitationPolicemanSuccessLiveData.value = null
               val item = model.getMessage(it)
               dialog?.cancel()
               popPage(PoliceRequesterFragment.newInstance(item))
           }
        })


    }


}