package com.sirius.driverlicense.ui.profile

import android.content.Context
import android.text.TextUtils
import android.util.Log
import android.view.View
import androidx.lifecycle.MutableLiveData
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.google.gson.reflect.TypeToken
import com.sirius.driverlicense.base.AppPref
import com.sirius.driverlicense.base.providers.ResourcesProvider
import com.sirius.driverlicense.base.ui.BaseViewModel
import com.sirius.driverlicense.models.ui.ItemCredentials
import com.sirius.driverlicense.models.ui.ItemCredentialsDetails
import com.sirius.driverlicense.repository.UserRepository
import com.sirius.driverlicense.sirius_sdk_impl.SDKUseCase
import com.sirius.driverlicense.ui.connections.ConnectionItem
import com.sirius.library.agent.aries_rfc.feature_0036_issue_credential.messages.ProposedAttrib
import com.sirius.library.mobile.SiriusSDK
import com.sirius.library.mobile.helpers.PairwiseHelper
import com.sirius.library.utils.JSONArray
import java.util.*

import javax.inject.Inject

class MenuProfileViewModel @Inject constructor(
    resourcesProvider: ResourcesProvider,
    val userRepository: UserRepository,
    val sdkUseCase: SDKUseCase
) : BaseViewModel(resourcesProvider) {

    val connectionsLiveData = MutableLiveData<List<ConnectionItem>>()
    val settingsLiveData = MutableLiveData<Boolean>()
    val nameLiveData = MutableLiveData<String>()
    val phoneLiveData = MutableLiveData<String>()
    val nicknameLiveData = MutableLiveData<String>()

    //  val avatarLiveData = MutableLiveData<RosterUser>()
    val avatarOnClickLiveData = MutableLiveData<Boolean>()
    val connectionClickLiveData = MutableLiveData<ItemCredentials>()
    val scanQrClickLiveData = MutableLiveData<Boolean>()
    val contacsClickLiveData = MutableLiveData<Boolean>()
    val exitClickLiveData = MutableLiveData<Boolean>()
    val avatarLiveData = MutableLiveData<String?>()

    val userNameTextLiveData = MutableLiveData<String>()
    val userNicknameTextLiveData = MutableLiveData<String>()
    val adapterListLiveData: MutableLiveData<List<ItemCredentials>> = MutableLiveData(listOf())
    override fun onViewCreated() {
        super.onViewCreated()
        /*    val myUser = AppPref.getMyselfUser()
    value = myUser
            nameLiveData.value = myUser.contactNameFull
            phoneLiveData.value = myUser.telephony_mob ?: ""
            nicknameLiveData.value = myUser.contactName

            if (IndyWallet.getMyWallet() != null) {
                updateLastConnections()
            }*/


        //userNameTextLiveData.postValue(userRepository.myUser.name)
    }


    fun logout(context: Context) {
        AppPref.getInstance().cleanAll()
        SiriusSDK.getInstance().walletHelper.closeWallet()
        SiriusSDK.cleanInstance()
        sdkUseCase.deleteWallet(context)
    }
    /* override fun onWalletOpen() {
         super.onWalletOpen()
         updateLastConnections()
     }*/


    /*  private fun updateLastConnections() {

          val messagesList  = ArrayList<MessagesNew>();
          try {
              val s = Anoncreds.proverGetCredentials(IndyWallet.getMyWallet(), "{}").get()
              val gson = GsonBuilder().create()
              Utils.logLongText("mylo99","s="+s)
              val credentialsModels: List<CredentialsModel> = gson.fromJson<List<CredentialsModel>>(s, object : TypeToken<List<CredentialsModel?>?>() {}.type)
              for (i in credentialsModels.indices) {
                  val credentialsModel: CredentialsModel = credentialsModels[i]
                  val message = IndyWallet.getCredDefMessage(credentialsModel.getCredDefId())
                  Utils.logLongText("mylo99","message="+message)
                  var messages: MessagesNew? = null
                  if (message != null) {
                      messages = repareFromMessageGson(message)
                  } else {
                      messages = repareFromWallet(credentialsModel)
                  }
                  if (messages != null) {
                      messagesList.add(messages)
                  }
              }


          } catch (e: Exception) {
              e.printStackTrace();
          }

          indyRepository.getConnectionsFromMessages(messagesList).observeOnce(this) {
              val connectionItemsList: MutableList<ConnectionItem> = ArrayList()
              it.forEach { wrapper ->
                  val attributesCount = resourceProvider.getPluralsString(R.plurals.attrbute_plurals, wrapper.attrCount
                          ?: 0, wrapper.attrCount ?: 0)

                  var icon = R.drawable.ic_group_work
                  var color = R.color.cardViolet
                  when {
                      wrapper.type == ConnectionsWrapper.ConnectionType.proofrequest -> {
                          color = R.color.cardConnectionCredentials
                          icon = R.drawable.ic_connection_verification
                      }
                      wrapper.type == ConnectionsWrapper.ConnectionType.credentilas -> {
                          color = R.color.cardOrange
                          icon = R.drawable.ic_connection_credentials2
                      }
                      wrapper.type == ConnectionsWrapper.ConnectionType.workspaces-> {
                          color = R.color.cardConnectionGreen
                          icon = R.drawable.ic_connection_chat
                      }
                      wrapper.status == ConnectionsWrapper.ConnectionStatus.canceled -> {
                          color = R.color.errorColor
                      }
                  }
                  val item = ConnectionItem(wrapper.id, wrapper.userName, wrapper.credName, attributesCount, icon, color, wrapper)
                  connectionItemsList.add(item)
              }
              connectionsLiveData.value = (connectionItemsList)

          }
      }*/

    /*   fun repareFromMessageGson(message: String): MessagesNew? {
           try {
               val gson = Gson()
               val messages: MessagesNew = gson.fromJson(message, MessagesNew::class.java)
               if (messages != null) {
                   if (!TextUtils.isEmpty(messages.getId())) {
                       return messages
                   }
               }
           } catch (e: Exception) {
               e.printStackTrace()
           }
           return null
       }*/

/*
    fun repareFromWallet(credentialsModel: CredentialsModel): MessagesNew? {
        try {
            //   String schema = IndyWallet.getIssuerSchema(credentialsModel.getSchemaId());
            //   String schema1 = IndyWallet.getCredDef(credentialsModel.getCredDefId());

            //   String schema3 = IndyWallet.getCredDefMessage2(credentialsModel.getCredDefId());
            //    Log.d("mylog2080", "schema=" + schema);
            //     Log.d("mylog2080", "schema1=" + schema1);
            //Log.d("mylog2080","message="+message);
            // Log.d("mylog2080","schema3="+schema3);
        } catch (e: Exception) {
            e.printStackTrace()
        }
        return null
    }
*/

    fun onSettingsButtonClick(v: View?) {
        settingsLiveData.value = true
    }

    fun onConnectionClick(item: ItemCredentials) {
        connectionClickLiveData.value = (item)
    }

    fun onAvatarClick(v: View?) {
        avatarOnClickLiveData.value = true
    }

    fun onScanClick(v: View?) {
        scanQrClickLiveData.postValue(true)
    }

    fun onContactsClick(v: View?) {
        contacsClickLiveData.postValue(true)
    }

    fun updateAvatar() {
        var lastname = ""
        val userLastname = userRepository.myUser.lastname.orEmpty().trim()
        if (!userLastname.isEmpty()) {
            lastname = "( " + userLastname + " )"
        }
        userNameTextLiveData.postValue(userRepository.myUser.name.orEmpty() + " " + lastname)
        avatarLiveData.postValue(userRepository.myUser.image)

    }

    fun onExitClick(v: View?) {
        exitClickLiveData.postValue(true)
    }

    private fun createList(): List<ItemCredentials> {

        val credentilas = PairwiseHelper.getInstance().getAllCredentials()
        val list = credentilas.map {
            val splittes = it.schema_id?.split(":") ?: listOf("", "", "", "")
            val name = splittes[2]
            val item = ItemCredentials(name, Date(), false, it)
            var useAsAvatar = false
            if (name.contains("passport", true)) {
                useAsAvatar = true

            }
            item.detailList = it.getAttributes().map {
                ItemCredentialsDetails(it.name ?: "", it.value ?: "", it.mimeType ?: "")
            }

     /*       var offerAttaches = offerMessage?.messageObj?.getJSONArray("~attach")
            if (offerAttaches != null) {
                //  val att = offerAttaches.optJSONObject(0)
                for (attach in offerAttaches) {
                    val att = attach as? com.sirius.library.utils.JSONObject
                    if (att != null) {
                        val type = att.optString("@type") ?: ""
                        for (attach in offerAttaches) {
                            if (type.endsWith("/credential-translation")) {
                                val dataObject = att.optJSONObject("data")
                                val jsonArray = dataObject?.optJSONArray("json") ?: JSONArray()
                                for (jsonObject in jsonArray) {
                                    val att = jsonObject as? com.sirius.library.utils.JSONObject
                                    val name = att?.optString("attrib_name")
                                    val translation = att?.optString("translation")
                                    val attrib = ProposedAttrib(name, translation)
                                    val filtered =   item.detailList?.firstOrNull() {
                                        attrib.name == it.name
                                    }
                                    filtered?.name = translation
                                }
                                //     name = jsonObject?.optString("name")
                            }
                        }
                    }
                }

*/



                Log.d("mylog02", " item.detailList=" + item.detailList)
                if (useAsAvatar) {
                    val itemUser = item.detailList.firstOrNull {
                        it.name?.contains("photo") == true
                    }
                    userRepository.myUser.image = itemUser?.value

                    var lastname = ""
                    val itemUser2 = item.detailList.firstOrNull {
                        it.name?.contains("first_name") == true
                    }
                    lastname = itemUser2?.value ?: ""
                    val itemUser3 = item.detailList.firstOrNull {
                        it.name?.contains("last_name") == true
                    }
                    lastname = lastname + " " + itemUser3?.value
                    userRepository.myUser.lastname = lastname
                    userRepository.saveUserToPref()
                }

                item
            }
            return list

        }


        override fun setupViews() {
            super.setupViews()
            val list = createList()
            updateAvatar()
            adapterListLiveData.postValue(list)
        }

/*    fun uploadIconAndSend(filename: String) {
        uploadRepository.uploadFile(filename, true).observeOnceForDone(this) {
            if (it.status == Status.SUCCESS) {
                val avatarUrl = it.data?.url
                userRepository.updateUser(jid = AppPref.getMyselfUser().jid, avatar = avatarUrl).observeOnce(this) {
                    if (it != null) {
                        AppPref.getMyselfUser().avatar = avatarUrl
                        DaoUtilsRoster.writeRosterUser(AppPref.getMyselfUser())
                    }
                }
            } else if (it.status == Status.ERROR) {
                onShowToastLiveData.postValue(resourceProvider.getString(R.string.error))
            }
        }
    }*/
    }