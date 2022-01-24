package com.sirius.driverlicense.ui.activities.loader



import android.content.Context
import androidx.lifecycle.MutableLiveData
import com.sirius.driverlicense.base.providers.ResourcesProvider
import com.sirius.driverlicense.base.ui.BaseActivityModel
import com.sirius.driverlicense.sirius_sdk_impl.SDKUseCase
import com.sirius.driverlicense.repository.UserRepository

import javax.inject.Inject

class LoaderActivityModel @Inject constructor(
    resourceProvider: ResourcesProvider,
    private val sdkUseCase: SDKUseCase,
    private val userRepository: UserRepository
) :
    BaseActivityModel(resourceProvider) {

    var isPolice: Boolean = false
    val initStartLiveData = MutableLiveData<Boolean>()
    val initEndLiveData = MutableLiveData<Boolean>()

        fun initSdk(context: Context, isPolice : Boolean){
            var login =  ""
            var pass = ""
            var label = ""
            if(isPolice){
                 login = userRepository.myUserPolice.uid ?: ""
                 pass = userRepository.myUserPolice.pass ?:""
                 label = userRepository.myUserPolice.name ?:""
            }else{
                 login = userRepository.myUser.uid ?: ""
                 pass = userRepository.myUser.pass ?:""
                 label = userRepository.myUser.name ?:""
            }
            sdkUseCase.initSdk(context,login,pass, label,object : SDKUseCase.OnInitListener{
                override fun initStart() {
                    initStartLiveData.postValue(true)
                }

                override fun initEnd() {
                    initEndLiveData.postValue(true)
                }

            })
        }


}