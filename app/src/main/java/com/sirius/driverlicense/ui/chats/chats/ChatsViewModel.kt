package com.sirius.driverlicense.ui.chats.chats

import android.view.View
import androidx.lifecycle.MutableLiveData
import com.sirius.driverlicense.base.providers.ResourcesProvider
import com.sirius.driverlicense.base.ui.BaseViewModel
import com.sirius.driverlicense.models.ui.ItemContacts
import com.sirius.driverlicense.repository.EventRepository
import com.sirius.driverlicense.repository.MessageRepository
import com.sirius.driverlicense.repository.UserRepository
import com.sirius.driverlicense.sirius_sdk_impl.SDKUseCase
import com.sirius.driverlicense.transform.LocalMessageTransform
import com.sirius.driverlicense.ui.chats.chats.message.BaseItemMessage
import com.sirius.driverlicense.utils.extensions.observeOnce

import java.util.*

import javax.inject.Inject


open class ChatsViewModel @Inject constructor(
    val userRepository: UserRepository,
    resourcesProvider: ResourcesProvider,
    val eventRepository: EventRepository,
    val messageRepository: MessageRepository,
    val sdkUseCase: SDKUseCase
) : BaseViewModel(resourcesProvider) {


    val adapterListLiveData: MutableLiveData<List<BaseItemMessage>> = MutableLiveData(listOf())
    val enableSendIconLiveData: MutableLiveData<Boolean> = MutableLiveData(false)
    val visibilityChatLiveData: MutableLiveData<Int> = MutableLiveData(View.GONE)
    val clearTextLiveData: MutableLiveData<Boolean> = MutableLiveData()
    val eventStoreLiveData = messageRepository.eventStoreLiveData

    var item: ItemContacts? = null
    var messageText: String? = null


    fun enableSendIcon() {
        enableSendIconLiveData.postValue(!messageText.isNullOrEmpty())
    }

    private fun createList() {
        messageRepository.getMessagesForPairwiseDid(item?.id ?: "").observeOnce(this) {
            var list = it.map {
                LocalMessageTransform.toBaseItemMessage(it)
            }.toMutableList()

            if (list.isEmpty()) {
                visibilityChatLiveData.postValue(View.GONE)
            } else {
                visibilityChatLiveData.postValue(View.VISIBLE)
            }

            if (list.isEmpty()) {
                LocalMessageTransform.toLocalMessage(item, messageRepository)
                    .observeOnce(this) {
                        val message = LocalMessageTransform.toBaseItemMessage(it)
                        list.add(message)
                        Collections.sort(
                            list,
                            kotlin.Comparator { o1, o2 ->
                                o1.date?.compareTo(o2.date ?: Date(0)) ?: -1
                            })
                        adapterListLiveData.postValue(list)
                    }
            }else{
                Collections.sort(
                    list,
                    kotlin.Comparator { o1, o2 ->
                        o1.date?.compareTo(o2.date ?: Date(0)) ?: -1
                    })
                adapterListLiveData.postValue(list)
            }
        }
    }


    fun onChooseIdClick(v: View) {

    }

    fun onFilterClick(v: View) {

    }

    fun onSendClick(v: View) {
        if (messageText == "question test") {
            val message = sdkUseCase.sendTestQuestion(item?.id ?: "")
            message?.let {
                messageRepository.createOrUpdateItem(it)
                clearTextLiveData.postValue(true)
            }
            return
        }
        if (messageText == "тест") {
            val message = sdkUseCase.sendRequestToPairwise(item?.id ?: "")
            message?.let {
                messageRepository.createOrUpdateItem(it)
                clearTextLiveData.postValue(true)
            }
            return
        }
        val message = sdkUseCase.sendTextMessageForPairwise(item?.id ?: "", messageText)
        message?.let {
            messageRepository.createOrUpdateItem(it)
            // eventRepository.storeEvent(message.message()?.id ?: "", message, "text")
            clearTextLiveData.postValue(true)
        }
    }


    fun updateList() {
        createList()
    }

    override fun setupViews() {
        super.setupViews()
        updateList()
        setTitle(item?.title)
        enableSendIcon()

    }


}


