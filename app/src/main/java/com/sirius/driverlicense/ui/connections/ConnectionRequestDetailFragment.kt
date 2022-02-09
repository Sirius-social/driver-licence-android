package com.sirius.driverlicense.ui.connections

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.lifecycle.Observer
import com.sirius.driverlicense.R
import com.sirius.driverlicense.base.App
import com.sirius.driverlicense.base.ui.BaseFragment
import com.sirius.driverlicense.databinding.FragmentConnectionRequestDetailBinding


private const val CONNECTION_ITEM = "CONNECTION_ITEM"

class ConnectionRequestDetailFragment : BaseFragment<FragmentConnectionRequestDetailBinding, ConnectionRequestDetailViewModel>() {

    companion object {
        @JvmStatic
        fun newInstance() = ConnectionRequestDetailFragment().apply {
            arguments = Bundle().apply {
              //  putSerializable(CONNECTION_ITEM, details)
            }
        }
    }

    override fun getLayoutRes(): Int = R.layout.fragment_connection_request_detail

    override fun setModel() {
        dataBinding!!.viewModel = model
    }



    override fun initDagger() {
        App.getInstance().appComponent.inject(this)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    //    val details =  arguments?.getSerializable(CONNECTION_ITEM) as ConnectionDetailsWrapper
   //     model.details = details
        super.onViewCreated(view, savedInstanceState)

    }

    override fun subscribe() {
        model.onBackClickLiveData.observe(this, Observer {
            onBackPressed()
        })

        model.connectionDateLiveData.observe(this, Observer {
            dataBinding.topViewName.text = it
        })

        model.connectionDetailsLiveData.observe(this, Observer {
            dataBinding.connectionDescriptionTextView.text = it
        })

        model.connectionColorLiveData.observe(this, Observer {
            dataBinding.topView.backgroundTintList = it
            dataBinding.connectionIconView.backgroundTintList = it
        })

        model.connectionImageLiveData.observe(this, Observer {
            dataBinding.connectionIconView.setImageDrawable(it)
        })
    }
}