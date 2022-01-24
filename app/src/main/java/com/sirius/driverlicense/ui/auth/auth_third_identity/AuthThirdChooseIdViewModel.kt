package com.sirius.driverlicense.ui.auth.auth_third_identity

import android.view.View
import androidx.lifecycle.MutableLiveData
import com.sirius.driverlicense.base.providers.ResourcesProvider
import com.sirius.driverlicense.base.ui.BaseViewModel
import com.sirius.driverlicense.repository.UserRepository

import javax.inject.Inject



open class AuthThirdChooseIdViewModel @Inject constructor(
    val userRepository: UserRepository,
    resourcesProvider: ResourcesProvider
) : BaseViewModel(resourcesProvider) {


    val registerHintLiveData = MutableLiveData<String>("")
    val registerFieldHintLiveData = MutableLiveData<String>("")
    val registerBtnTextLiveData = MutableLiveData<String>("")
    val alreadyExistAccountTextLiveData = MutableLiveData<CharSequence>()
    val goToTypeInfoScreenLiveData = MutableLiveData<Boolean>()
    val nextVisibilityLiveData = MutableLiveData<Int>()
    val countryCodeLiveData = MutableLiveData<String>("+1")



    fun onChooseIdClick(v: View) {

    }

    fun onCountryCodeClick(v: View){

    }

    override fun setupViews() {
        super.setupViews()
        isNextEnabled()
    }



    fun isNextEnabled()  {
        val isNextEnabled = !userRepository.myUser.phone.isNullOrEmpty() && !userRepository.myUser.email.isNullOrEmpty()
        if(isNextEnabled){
            nextVisibilityLiveData.postValue( View.VISIBLE)
        }else{
            nextVisibilityLiveData.postValue(  View.INVISIBLE)
        }

    }

    fun setUserEmail(emai: String) {
        userRepository.myUser.email = emai
    }

    fun setPhone(phone: String) {
        userRepository.myUser.phone = phone
    }

}


