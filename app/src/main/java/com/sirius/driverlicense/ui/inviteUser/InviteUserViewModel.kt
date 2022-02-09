package com.sirius.driverlicense.ui.inviteUser

import android.util.Log
import com.sirius.driverlicense.base.ui.BaseViewModel


import android.view.View
import androidx.lifecycle.MutableLiveData
import com.google.gson.Gson
import com.sirius.driverlicense.base.providers.ResourcesProvider
import com.sirius.driverlicense.sirius_sdk_impl.SDKUseCase

import javax.inject.Inject

class InviteUserViewModel @Inject constructor(
        resourcesProvider: ResourcesProvider,
        val sdkUseCase: SDKUseCase
) : BaseViewModel(resourcesProvider ) {
    val qrCodeLiveData = MutableLiveData<String>()
    val shareButtonAction = MutableLiveData<String>()

    override fun onViewCreated() {
        super.onViewCreated()
        val inviteLink = sdkUseCase.generateInvitation() ?:""
        Log.d("mylog200","inviteLink="+inviteLink)
        qrCodeLiveData.value = inviteLink
    }

    fun onShareButtonClick(v: View?) {
        shareButtonAction.value = qrCodeLiveData.value
    }
}