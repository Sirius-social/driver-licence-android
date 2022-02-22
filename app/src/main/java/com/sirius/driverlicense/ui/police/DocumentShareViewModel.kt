package com.sirius.driverlicense.ui.police

import android.os.Handler
import android.util.Log
import android.view.View
import androidx.lifecycle.MutableLiveData
import com.sirius.driverlicense.R
import com.sirius.driverlicense.base.providers.ResourcesProvider
import com.sirius.driverlicense.base.ui.BaseViewModel
import com.sirius.driverlicense.models.ui.ItemContacts
import com.sirius.driverlicense.models.ui.ItemCredentials
import com.sirius.driverlicense.models.ui.ItemCredentialsDetails
import com.sirius.driverlicense.repository.UserRepository
import com.sirius.driverlicense.sirius_sdk_impl.SDKUseCase
import com.sirius.driverlicense.ui.connections.ConnectionItem
import com.sirius.library.mobile.helpers.PairwiseHelper
import java.util.*
import javax.inject.Inject

class DocumentShareViewModel @Inject constructor(
    val resourcesProvider: ResourcesProvider,
    val userRepository: UserRepository,
    val sdkUseCase: SDKUseCase
) : BaseViewModel(resourcesProvider) {
    val adapterListLiveData: MutableLiveData<List<ItemCredentials>> = MutableLiveData(listOf())
    var item: ItemContacts? = null
    var policemanTextLiveData =
        MutableLiveData<String>(resourcesProvider.getString(R.string.verify_police))
    var policemanSuccessIconVisibilityLiveData = MutableLiveData<Int>(View.GONE)
    var policemanLoadingIconVisibilityLiveData = MutableLiveData<Int>(View.VISIBLE)
    var policemanLoadingEndLiveData = MutableLiveData<Boolean>()
    var sharingDocumentSuccessLiveData = MutableLiveData<Boolean>()
    var sharingDocumentVisibilityLiveData = MutableLiveData<Int>(View.GONE)


    fun onConnectionClick(item: ItemCredentials) {

    }


    override fun setupViews() {
        super.setupViews()
        startAnim()
        val list = createList()
        adapterListLiveData.postValue(list)
    }
    fun startAnim(){
        loadingPoliceAnim()
    }

    fun loadingPoliceAnim() {
        policemanLoadingIconVisibilityLiveData.postValue(View.VISIBLE)
        policemanSuccessIconVisibilityLiveData.postValue(View.GONE)
        policemanTextLiveData.postValue(resourcesProvider.getString(R.string.verify_police))
        Handler().postDelayed({
            policemanLoadingIconVisibilityLiveData.postValue(View.GONE)
            policemanSuccessIconVisibilityLiveData.postValue(View.VISIBLE)
            policemanTextLiveData.postValue(resourcesProvider.getString(R.string.verify_police_success))
            Handler().postDelayed({
                policemanTextLiveData.postValue("")
                policemanLoadingEndLiveData.postValue(true)
                Handler().postDelayed({
                    sharingDocumentVisibilityLiveData.postValue(View.VISIBLE)
                    Handler().postDelayed({
                        sharingDocumentSuccessLiveData.postValue(true)
                    },5000)

                },600)
            },500)
        }, 3000)
    }


    private fun createList(): List<ItemCredentials> {

        val credentilas = PairwiseHelper.getInstance().getAllCredentials()
        val list = credentilas.map {
            val splittes = it.schema_id?.split(":") ?: listOf("", "", "", "")
            val name = splittes[2]
            val item = ItemCredentials(name, Date(), false, it)
            item.detailList = it.getAttributes().map {
                ItemCredentialsDetails(it.name ?: "", it.value ?: "", it.mimeType ?: "")
            }

            item
        }
        return list

    }

}