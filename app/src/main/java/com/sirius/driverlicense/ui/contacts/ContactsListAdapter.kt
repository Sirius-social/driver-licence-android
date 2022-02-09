package com.sirius.driverlicense.ui.contacts

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.sirius.driverlicense.R
import com.sirius.driverlicense.base.ui.SimpleBaseRecyclerViewAdapter
import com.sirius.driverlicense.databinding.ItemChatBinding
import com.sirius.driverlicense.databinding.ViewItemsContactsBinding
import com.sirius.driverlicense.models.ui.ItemContacts


class ContactsListAdapter(private val chatsClickAction:  (ItemContacts) -> Unit,
                          private val detailsClickAction: (ItemContacts) -> Unit ) :
    SimpleBaseRecyclerViewAdapter<ItemContacts, ContactsListAdapter.ContactsViewHolder>() {


    override fun onBind(holder: ContactsViewHolder?, position: Int) {
        val item = getItem(position)
        holder?.bind(item,chatsClickAction,detailsClickAction)
    }


    class ContactsViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var binding: ItemChatBinding? = DataBindingUtil.bind<ItemChatBinding>(itemView)
        fun bind(item: ItemContacts,
                 chatsClickAction: (ItemContacts) -> Unit,
                 detailsClickAction: (ItemContacts) -> Unit) {

            binding?.model = item
            itemView.setOnClickListener {
                chatsClickAction.invoke(item)
            }
         /*   binding?.detailsBtn?.setOnClickListener {
                detailsClickAction .invoke(item)
            }
            */
        }
    }

    override fun getLayoutRes(): Int {
        return R.layout.item_chat
    }

    override fun getViewHolder(
        parent: ViewGroup?,
        layoutRes: Int,
        viewType: Int
    ): ContactsViewHolder {
        val view =
            LayoutInflater.from(parent?.context).inflate(getLayoutRes(), parent, false)
        return ContactsViewHolder(view)
    }


}