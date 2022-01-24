package com.sirius.driverlicense.ui.auth.auth_second

import android.view.View
import androidx.lifecycle.MutableLiveData
import com.sirius.driverlicense.R
import com.sirius.driverlicense.base.providers.ResourcesProvider
import com.sirius.driverlicense.base.ui.BaseViewModel
import com.sirius.driverlicense.repository.UserRepository

import javax.inject.Inject



open class AuthSecondViewModel @Inject constructor(
    val userRepository: UserRepository,
    resourcesProvider: ResourcesProvider
) : BaseViewModel(resourcesProvider) {


    val registerHintLiveData = MutableLiveData<String>("")
    val registerFieldHintLiveData = MutableLiveData<String>("")
    val registerBtnTextLiveData = MutableLiveData<String>("")
    val alreadyExistAccountTextLiveData = MutableLiveData<CharSequence>()
    val goToNextScreenLiveData = MutableLiveData<Boolean>()
    val changeNameScreenLiveData = MutableLiveData<Boolean>()
    val nextVisibilityLiveData = MutableLiveData<Int>()
    val countryCodeLiveData = MutableLiveData<String>("+1")
    val labelTextLiveData = MutableLiveData<String>("")



    fun onRegisterClick(v: View) {
        goToNextScreenLiveData.postValue(true)
    }

    fun onChangeNameClick(v: View){
        changeNameScreenLiveData.postValue(true)
    }

    override fun setupViews() {
        super.setupViews()
        isNextEnabled()
        labelTextLiveData.postValue(resourceProvider.getString(R.string.auth_second_title,userRepository.myUser.name ?: ""))
    }



    fun isNextEnabled()  {
        val isNextEnabled = !userRepository.myUser.pass.isNullOrEmpty()
        if(isNextEnabled){
            nextVisibilityLiveData.postValue( View.VISIBLE)
        }else{
            nextVisibilityLiveData.postValue(  View.INVISIBLE)
        }

    }

    fun setUserPassword(pass: String) {
        userRepository.myUser.pass = pass
    }



}


