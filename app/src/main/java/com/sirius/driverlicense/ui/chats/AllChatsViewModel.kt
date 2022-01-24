package com.sirius.driverlicense.ui.chats

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.sirius.driverlicense.base.providers.ResourcesProvider
import com.sirius.driverlicense.base.ui.BaseViewModel
import com.sirius.driverlicense.models.Chats

import javax.inject.Inject

class AllChatsViewModel @Inject constructor(
        resourcesProvider: ResourcesProvider
) : BaseViewModel(resourcesProvider) {

/*    val emptyStateLiveData = MutableLiveData<Boolean>()
    val chatsLiveData = chatsRepository.result
    val chatsListLiveData = MutableLiveData<List<Chats>>(listOf())
    var originalList : List<Chats> = listOf()
    val chatsSelectLiveData = MutableLiveData<Chats>()
    val messagesLiveData = messagesRepository.messagesInDBLiveData
    val updateOneChatsLiveData = MutableLiveData<Chats>()
    val lastActivityAllLiveData = userRepository.statusListLiveData
    val activityStatusLiveData = MutableLiveData<List<RosterStatusResponse>>()
    val messagesInDBForceRefreshLiveData = messagesRepository.messagesInDBForceRefreshLiveData
    val repositoryCreatedLiveData = chatsRepository.oneChatCreatedUpdateLiveData
    val updateOneChatLiveData = chatsRepository.oneChatUpdateLiveData
    val filterLiveData = uiUseCase.searchFilterLiveData*/
    override fun onViewCreated() {

        super.onViewCreated()
      /*  emptyStateLiveData.value = false
        chatsRepository.getAllChats()
        repositoryCreatedLiveData.observeUntilDestroy(this) {
            it?.let {
                chatsRepository.getAllChats(forceRefresh = true)
                repositoryCreatedLiveData.postValue(null)
            }
        }
        updateOneChatLiveData.observeUntilDestroy(this) {
            it?.let {
                updateChatWithJid(it.id)
            }
        }*/

    }

    fun onSelectChat(chat: Chats) {
     //   chatsSelectLiveData.value = chat
    }

    fun updateChatWithJid(jid: String) {
      /*  if (jid == AppPref.getUserJid()) {
            return
        }
        chatsRepository.getChatWithJid(jid).observeOnce(this, action = { chats ->
            if (chats != null) {
                updateOnechat(chats)
                Log.d("mylog20709", "updateChatWithJid chats=" + chats)
                updateOneChatsLiveData.value = chats
            }
        })*/
    }

    fun updateLastActivity() {
/*        val items = lastActivityAllLiveData.value
        activityStatusLiveData.postValue(items)*/
    }

    fun refreshAllChats() {
      //  chatsRepository.getAllChats(false)
    }
/*

    fun filterWith(filter: FilterChipsModel?) {
        val list = originalList?.filter { chats ->
            filter?.let {
                if (filter.id == -2) {
                    return@filter true
                }
                if (filter.id == -1) {
                    if (chats.isRoom) {
                        return@filter chats.workspacePk == 0
                    }

                    if (chats is SecretChats) {
                        return@filter true
                    }

                    Log.d("mylog2099","chats.creatorUser?="+chats.creatorUser?.name +" chats.creatorUser?="+chats.creatorUser?.userGroups)
                    return@filter chats.creatorUser?.isUserAddedByMe ?: false
                  //  val keySet = StringUtils.makeSetFromString(chats.creatorUser?.workspaceName)
                 //   val isDo = keySet.contains(filter.id.toString())
                  //  return@filter false
                }
                if (chats.isRoom) {
                    return@filter chats.workspacePk == filter.id
                } else {
                    val keySet = StringUtils.makeSetFromString(chats.creatorUser?.workspaceName)
                    val isDo = keySet.contains(filter.id.toString())
                    return@filter isDo
                }
            }
            return@filter true
        }
        chatsListLiveData.postValue(list)
    }
*/

    fun updateOnechat(chats: Chats?) {


    }
}