package com.sirius.driverlicense.ui.scan

import android.net.Uri
import android.os.Handler
import android.text.TextUtils
import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.google.gson.Gson
import com.sirius.driverlicense.R
import com.sirius.driverlicense.base.providers.ResourcesProvider
import com.sirius.driverlicense.base.ui.BaseViewModel
import com.sirius.driverlicense.models.Chats
import com.sirius.library.mobile.helpers.ChanelHelper
import com.sirius.library.mobile.helpers.InvitationHelper

import org.json.JSONArray
import org.json.JSONException
import org.json.JSONObject
import javax.inject.Inject

open class MenuScanQrViewModel @Inject constructor(
    open var resourcesProvider: ResourcesProvider

) : BaseViewModel(resourcesProvider) {

    val goToNewSecretChatLiveData = MutableLiveData<Chats?>()

    override fun onViewCreated() {
        super.onViewCreated()
    }

    open fun onCodeScanned(result: String) : Boolean {
        val message = InvitationHelper.getInstance().parseInvitationLink(result)
        if (message != null) {
            ChanelHelper.getInstance().parseMessage(message)
            return true
        } else {
            val textError: String ="The scanned QR code is not an invitation, please scan another QR code."
            onShowToastLiveData.postValue(textError)
            return false
        }

    }





}