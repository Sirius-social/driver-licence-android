package com.sirius.driverlicense.ui.main

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View

import androidx.lifecycle.Observer
import com.google.zxing.Result
import com.sirius.driverlicense.R
import com.sirius.driverlicense.base.App
import com.sirius.driverlicense.base.ui.BaseFragment
import com.sirius.driverlicense.databinding.FragmentAuthZeroBinding
import com.sirius.driverlicense.databinding.FragmentMainPoliceBinding
import com.sirius.driverlicense.design.SiriusScannerView

import com.sirius.driverlicense.ui.auth.auth_second.AuthSecondFragment
import com.sirius.driverlicense.utils.PermissionHelper


class MainPoliceFragment : BaseFragment<FragmentMainPoliceBinding, MainPoliceViewModel>(), SiriusScannerView.ResultHandler {



    override fun setupViews() {
        super.setupViews()


    }

    override fun getLayoutRes(): Int {
        return R.layout.fragment_main_police
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


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if (PermissionHelper.checkPermissionsForCamera(activity, 1098)) {
            startCamera()
        }

    }

    override fun onResume() {
        super.onResume()
        dataBinding.mScannerView.resumeCameraPreview(this)
    }

    override fun onPause() {
        super.onPause()
        dataBinding.mScannerView.stopCameraPreview()
    }

    override fun onDestroy() {
        dataBinding.mScannerView?.stopCamera()
        super.onDestroy()

    }

    private fun startCamera() {
        dataBinding.mScannerView?.setResultHandler(this)
        dataBinding. mScannerView?.startCamera()
    }


    override fun handleResult(rawResult: Result?) {
        rawResult?.let { model.onCodeScanned(it.text.orEmpty()) }
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        PermissionHelper.onRequestPermissionsResult(requestCode, 1098, permissions, grantResults, object :
            PermissionHelper.OnRequestPermissionListener {
            override fun onRequestFail() {
                model.onShowToastLiveData.postValue(resources.getString(R.string.camera_qr_scan_permission))
            }

            override fun onRequestSuccess() {
                startCamera()
            }
        })
    }

}