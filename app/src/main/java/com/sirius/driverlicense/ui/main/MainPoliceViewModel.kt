package com.sirius.driverlicense.ui.main

import android.view.View
import androidx.lifecycle.MutableLiveData
import com.sirius.driverlicense.base.providers.ResourcesProvider
import com.sirius.driverlicense.base.ui.BaseViewModel
import com.sirius.driverlicense.repository.UserRepository
import com.sirius.library.mobile.helpers.ChanelHelper
import com.sirius.library.mobile.helpers.InvitationHelper

import javax.inject.Inject



open class MainPoliceViewModel @Inject constructor(
    val userRepository: UserRepository,
    resourcesProvider: ResourcesProvider
) : BaseViewModel(resourcesProvider) {

    val registerHintLiveData = MutableLiveData<String>("")
    val registerFieldHintLiveData = MutableLiveData<String>("")
    val registerBtnTextLiveData = MutableLiveData<String>("")
    val alreadyExistAccountTextLiveData = MutableLiveData<CharSequence>()
    val goToNextScreenLiveData = MutableLiveData<Boolean>()
    val nextVisibilityLiveData = MutableLiveData<Int>()



    fun onRegisterClick(v: View) {
        goToNextScreenLiveData.postValue(true)
    }

    override fun setupViews() {
        super.setupViews()
        isNextEnabled()
    }


    fun isNextEnabled()  {
        val isNextEnabled = !userRepository.myUser.name.isNullOrEmpty() && !userRepository.myUser.pass.isNullOrEmpty()
        if(isNextEnabled){
            nextVisibilityLiveData.postValue( View.VISIBLE)
        }else{
            nextVisibilityLiveData.postValue(  View.INVISIBLE)
        }

    }

    fun setUserName(name: String) {
        userRepository.myUser.name = name
    }

    fun setUserPass(pass: String) {
        userRepository.myUser.pass = pass
    }

    fun saveUser() {
        userRepository.saveUserToPref()
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


