package com.sirius.driverlicense.ui.chats.chats

import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.RecyclerView
import com.sirius.driverlicense.base.ui.BaseMultiRecyclerViewAdapter
import com.sirius.driverlicense.ui.chats.chats.holder.MessageViewHolder
import com.sirius.driverlicense.ui.chats.chats.message.BaseItemMessage


class MessagesListAdapter :
    BaseMultiRecyclerViewAdapter<BaseItemMessage>() {

    override fun getItemCount(): Int {
        return super.getItemCount()
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val layoutRes = MessageViewHolder.getLayoutResFromType(viewType)
        val view =
            LayoutInflater.from(parent.context).inflate(layoutRes, parent, false)
        return MessageViewHolder.getHolderFromType(viewType, view)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val item = getItem(position)
        val handler = Handler(Looper.getMainLooper())
        item.notifyDataListener = object : BaseItemMessage.NotifyDataListener{
            override fun notifyData() {
                handler.post{
                    notifyDataSetChanged()
                }

            }

            override fun notifyItem(item: BaseItemMessage) {
                handler.post{
                    val index = getDataList().indexOf(item)
                    notifyItemChanged(index)
                }
            }

        }
        (holder as? MessageViewHolder)?.bind(item)



    }


    override fun getItemViewType(position: Int): Int {
        return getItem(position).getType().ordinal
    }

}