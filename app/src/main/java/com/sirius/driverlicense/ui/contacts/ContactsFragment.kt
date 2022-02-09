package com.sirius.driverlicense.ui.contacts

import android.app.AlertDialog
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.view.LayoutInflater
import androidx.databinding.DataBindingUtil

import androidx.lifecycle.Observer
import com.sirius.driverlicense.R
import com.sirius.driverlicense.base.App
import com.sirius.driverlicense.base.ui.BaseFragment
import com.sirius.driverlicense.databinding.FragmentContactsBinding
import com.sirius.driverlicense.databinding.ViewItemsDetailContactsBinding
import com.sirius.driverlicense.models.ui.ItemContacts
import com.sirius.driverlicense.ui.chats.chats.ChatsFragment


class ContactsFragment : BaseFragment<FragmentContactsBinding, ContactsViewModel>() {

    lateinit var adapter: ContactsListAdapter


    override fun setupViews() {
        super.setupViews()
        adapter = ContactsListAdapter(model::onChatsClick, model::onDetailsClick)
        /*   historyAdapter!!.setOnAdapterItemClick {
               it?.let {
                   model.onItemClick(it)
               }
           }*/
        dataBinding.contactsRecyclerView.adapter = adapter
    }

    override fun getLayoutRes(): Int {
        return R.layout.fragment_contacts
    }

    override fun initDagger() {
        App.getInstance().appComponent.inject(this)
    }

    override fun subscribe() {
        model.adapterListLiveData.observe(this, Observer {
            updateAdapter(it)
        })

        model.onChatClickLiveData.observe(this, Observer {
            it?.let {
                model.onChatClickLiveData.value = null
                baseActivity.pushPage(ChatsFragment.newInstance(it))
            }
        })

        model.onAddTagBtnClickLiveData.observe(this, Observer {
            it?.let {
                model.onAddTagBtnClickLiveData.value = null
               // baseActivity.pushPage(ChatsFragment.newInstance(it))
            }
        })

        model.onMoreBtnClickLiveData.observe(this, Observer {
            it?.let {
                model.onMoreBtnClickLiveData.value = null
                // baseActivity.pushPage(ChatsFragment.newInstance(it))
            }
        })

        model.onDetailsClickLiveData.observe(this, Observer {
            it?.let {
                model.onDetailsClickLiveData.value = null
                openDetailAlert(it)
            }
        })
    }

    fun openDetailAlert(item: ItemContacts) {
        val builder = AlertDialog.Builder(baseActivity)
        val view = LayoutInflater.from(baseActivity)
            .inflate(R.layout.view_items_detail_contacts, null, false)
        val detailBinding = DataBindingUtil.bind<ViewItemsDetailContactsBinding>(view)
      //  item.chatsClickAction = model.onChatsClick(item)
        detailBinding?.model = item
        detailBinding?.chatBtn?.setOnClickListener {
            model.onChatsClick(item)
        }
        detailBinding?.addTagBtn?.setOnClickListener {
            model.onAddTagBtnClick(item)
        }
        detailBinding?.moreActionBtn?.setOnClickListener {
            model.onMoreActionBtnClick(item)
        }

        builder.setView(view)
        val alert = builder.create()
        alert.setOnShowListener {
            alert.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT));
        }
        alert.show()
    }

    private fun updateAdapter(data: List<ItemContacts>) {
        adapter.setDataList(data)
        adapter.notifyDataSetChanged()
    }


    override fun setModel() {
        dataBinding.viewModel = model
    }


}