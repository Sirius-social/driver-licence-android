package com.sirius.driverlicense.ui.chats.chats

import android.os.Bundle
import androidx.core.widget.addTextChangedListener

import androidx.lifecycle.Observer
import com.sirius.driverlicense.R
import com.sirius.driverlicense.base.App
import com.sirius.driverlicense.base.ui.BaseFragment
import com.sirius.driverlicense.databinding.FragmentChatsBinding
import com.sirius.driverlicense.models.ui.ItemContacts
import com.sirius.driverlicense.ui.chats.chats.message.BaseItemMessage


class ChatsFragment : BaseFragment<FragmentChatsBinding, ChatsViewModel>() {


    companion object {
        fun newInstance(item: ItemContacts): ChatsFragment {
            val args = Bundle()
            args.putSerializable("item", item)
            val fragment = ChatsFragment()
            fragment.arguments = args
            return fragment
        }
    }


    val adapter = MessagesListAdapter()

    override fun setupViews() {
        model.item = arguments?.getSerializable("item") as? ItemContacts
        super.setupViews()
        adapter.lifecycle = this
        dataBinding.messagesRecyclerView.adapter = adapter
        dataBinding.messageText.addTextChangedListener {
            model.messageText = it.toString()
            model.enableSendIcon()
        }

    }

    override fun getLayoutRes(): Int {
        return R.layout.fragment_chats
    }

    override fun initDagger() {
        App.getInstance().appComponent.inject(this)
    }

    override fun subscribe() {
        model.adapterListLiveData.observe(this, Observer {
            updateAdapter(it)
            dataBinding.messagesRecyclerView.postDelayed({
                dataBinding.messagesRecyclerView.scrollToPosition(adapter.itemCount-1)
            }, 200)
        })
        model.enableSendIconLiveData.observe(this, Observer {
            dataBinding.sendIcon.isEnabled = it
            if (it) {
                dataBinding.sendIcon.setColorFilter(App.getContext().getColor(R.color.blue))
            } else {
                dataBinding.sendIcon.setColorFilter(App.getContext().getColor(R.color.grey))
            }
        })

        model.clearTextLiveData.observe(this, Observer {
            if (it) {
                model.clearTextLiveData.value = false
                dataBinding.messageText.setText("")
            }
        })

        model.eventStoreLiveData.observe(this, Observer {
            model.updateList()

        })
    }

    private fun updateAdapter(data: List<BaseItemMessage>) {
        adapter.setDataList(data)
        adapter.notifyDataSetChanged()
    }


    override fun setModel() {
        dataBinding.viewModel = model
    }


}