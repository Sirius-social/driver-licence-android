package com.sirius.driverlicense.ui.chats

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import com.sirius.driverlicense.R
import com.sirius.driverlicense.base.App
import com.sirius.driverlicense.base.ui.BaseFragment
import com.sirius.driverlicense.databinding.FragmentAllChatsBinding


class AllChatsFragment : BaseFragment<FragmentAllChatsBinding, AllChatsViewModel>() {

    companion object {
        @JvmStatic
        fun newInstance() = AllChatsFragment()
    }

    override fun getLayoutRes(): Int = R.layout.fragment_all_chats

    override fun setModel() {
        dataBinding!!.viewModel = model
    }

    override fun initDagger() {
        App.getInstance().appComponent.inject(this)
    }

    private var adapter: ChatsAdapter? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        adapter = ChatsAdapter(model::onSelectChat)
        dataBinding.chatsRecyclerView.adapter = adapter
    }

    override fun subscribe() {
     /*   model.emptyStateLiveData.observe(this, Observer {
           dataBinding.emptyStateView.visibility = if (it) View.VISIBLE else View.GONE
        })
        model.chatsLiveData.observe(this, Observer {
            //  adapter?.deleteAllItems(true)
          *//*  if (it.status != Status.LOADING) {
                val items = it.data ?: listOf()
                //model.chatsListLiveData.postValue(items)
                model.originalList = items
                model.emptyStateLiveData.value = items.isEmpty()
                model.filterWith(model.filterLiveData.value)
                model.updateLastActivity()
            }*//*
        })
        model.chatsListLiveData.observe(this, Observer {
            adapter?.setItems(it)
        })*/
      /*  model.chatsSelectLiveData.observe(this, Observer { chat ->
            val messageIntent = Intent(context, MessageActivity::class.java).apply {
                putExtra(StaticFields.BUNDLE_CHAT, chat)
            }
            startActivity(messageIntent)
        })*/

  /*      model.messagesLiveData.observe(this, Observer {
            model.refreshAllChats()
            //model.updateChatWithJid(it.jid)
        })*/

    /*    model.updateOneChatsLiveData.observe(this, Observer {
            if (it is SecretChats) {
                if (it.isHidden) {
                    return@Observer
                }
            }
            adapter?.updateItem(it)
        })*/

    /*    model.lastActivityAllLiveData.observe(this, Observer {
            model.updateLastActivity()
        })

        model.activityStatusLiveData.observe(this, Observer {
    *//*        val statusMap = it?.map { it.uid to it.isOnline }?.toMap().orEmpty()
            adapter?.updateActivityStatus(statusMap)*//*

        })

        model.messagesInDBForceRefreshLiveData.observe(this, Observer {
            model.refreshAllChats()
        })

        model.filterLiveData.observe(this, Observer {
            model.filterWith(it)
        })*/
    }
}