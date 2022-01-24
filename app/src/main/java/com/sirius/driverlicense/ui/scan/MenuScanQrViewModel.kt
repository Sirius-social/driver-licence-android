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

    open fun onCodeScanned(code: String) : Boolean {
        if (validateInvitationUrl(code)) {
            parseInvitationLink(code)
            return true
        } else {
            val textError: String = resourcesProvider.getString(R.string.invite_qr_scan_hint_error)
            onShowToastLiveData.postValue(textError)
            return false
        }
    }




    private fun parseInvitationLink(rawValue: String)  {

    }


    private fun validateInvitationUrl(rawValue: String): Boolean {
            return false
    }


}