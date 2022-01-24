package com.sirius.driverlicense.ui.scan

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.lifecycle.Observer
import com.google.zxing.Result
import com.sirius.driverlicense.R
import com.sirius.driverlicense.base.App
import com.sirius.driverlicense.base.ui.BaseFragment
import com.sirius.driverlicense.databinding.FragmentMenuScanQrBinding
import com.sirius.driverlicense.design.SiriusScannerView
import com.sirius.driverlicense.utils.PermissionHelper


class MenuScanQrFragment : BaseFragment<FragmentMenuScanQrBinding, MenuScanQrViewModel>(), SiriusScannerView.ResultHandler {

    companion object {
        @JvmStatic
        fun newInstance() = MenuScanQrFragment()
    }

    override fun getLayoutRes(): Int = R.layout.fragment_menu_scan_qr

    override fun setModel() {
        dataBinding!!.viewModel = model
    }

    override fun initDagger() {
        App.getInstance().appComponent.inject(this)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if (PermissionHelper.checkPermissionsOnlyForCamera(activity, 1098)) {
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

    override fun subscribe() {
        model.goToNewSecretChatLiveData.observe(this, Observer {
            it?.let {
                model.goToNewSecretChatLiveData.value = null
                Log.d("mylog2080", "goToNewSecretChatLiveData=" + it);
              /*  (baseActivity as? MainActivity)?.showChats()
                val messageIntent = Intent(context, MessageActivity::class.java).apply {
                    putExtra(StaticFields.BUNDLE_CHAT, it)
                }
                startActivity(messageIntent)*/

            }


        })


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