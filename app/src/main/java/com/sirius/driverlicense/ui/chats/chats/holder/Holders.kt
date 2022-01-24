package com.sirius.driverlicense.ui.chats.chats.holder

import android.view.View
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import com.sirius.driverlicense.R
import com.sirius.driverlicense.base.App
import com.sirius.driverlicense.databinding.*
import com.sirius.driverlicense.ui.chats.chats.QuestionAnswerAdapter
import com.sirius.driverlicense.ui.chats.chats.message.BaseItemMessage
import com.sirius.driverlicense.ui.chats.chats.message.OfferItemMessage
import com.sirius.driverlicense.ui.chats.chats.message.ProverItemMessage
import com.sirius.driverlicense.ui.chats.chats.message.QuestionItemMessage
import com.sirius.driverlicense.utils.DateUtils


class TextMessageViewHolder(itemView: View) : MessageViewHolder(itemView) {
    var binding: ItemMessageTextBinding? =
        DataBindingUtil.bind<ItemMessageTextBinding>(itemView)


    override fun bind(item: BaseItemMessage) {
        binding?.item = item
    }
}

class OfferMessageViewHolder(itemView: View) : MessageViewHolder(itemView) {
    var binding: ItemMessageOfferBinding? =
        DataBindingUtil.bind<ItemMessageOfferBinding>(itemView)

    override fun bind(item: BaseItemMessage) {
      /*  val offer = item as OfferItemMessage
        binding?.item = offer
        val adapter = CredentialsDetailAdapter()
        adapter.setDataList(offer.detailList)
        binding?.attachList?.isNestedScrollingEnabled = false
        binding?.attachList?.adapter = adapter
        binding?.cancelBtn?.setOnClickListener {
            item.cancel()
        }

        binding?.yesBtn?.setOnClickListener {
            item.accept()
        }

        binding?.detailsBtn?.setOnClickListener {
            if( item.detailsVisibilityLiveData.value == View.GONE){
                item.detailsVisibilityLiveData.postValue(View.VISIBLE)
                item.notifyItem()
            }else{
                item.detailsVisibilityLiveData.postValue(View.GONE)
                item.notifyItem()
            }
        }

        if(item.isLoading){
            binding?.progressBar?.visibility = View.VISIBLE
            binding?.buttonsLayout?.visibility =  View.GONE
        }else{
            binding?.progressBar?.visibility = View.GONE
            binding?.buttonsLayout?.visibility =  View.VISIBLE
        }
        binding?.statusDate?.text =  DateUtils.getStringFromDate(item.date, DateUtils.PATTERN_ddMMMyyyyBase, false)*/
    }
}

class OfferAcceptedMessageViewHolder(itemView: View) : MessageViewHolder(itemView) {
     var binding: ItemMessageOfferAcceptedBinding? = DataBindingUtil.bind<ItemMessageOfferAcceptedBinding>(itemView)
    override fun bind(item: BaseItemMessage) {
        if(item.isError){
            binding?.cardView?.setCardBackgroundColor(App.getContext().getColor(R.color.red))
            binding?.text?.text = App.getContext().resources.getString(R.string.message_credentials_declined)
        }else{
            binding?.cardView?.setCardBackgroundColor(App.getContext().getColor(R.color.blue))
            binding?.text?.text = App.getContext().resources.getString(R.string.message_offer_successfully)
        }
        binding?.dateText?.text = DateUtils.getStringFromDate(item.date, DateUtils.PATTERN_DATETIME_DOT, false)
    }
}

class ProverMessageViewHolder(itemView: View) : MessageViewHolder(itemView) {
    var binding: ItemMessageProverBinding? =
        DataBindingUtil.bind<ItemMessageProverBinding>(itemView)

    override fun bind(item: BaseItemMessage) {
     /*   val prover = item as ProverItemMessage
        binding?.item = prover
        val adapter = CredentialsDetailAdapter()
        adapter.setDataList(prover.detailList)
        binding?.attachList?.isNestedScrollingEnabled = false
        binding?.attachList?.adapter = adapter
        binding?.cancelBtn?.setOnClickListener {
            prover.cancel()
        }

        binding?.yesBtn?.setOnClickListener {
            prover.accept()
        }

        binding?.detailsBtn?.setOnClickListener {
            if( item.detailsVisibilityLiveData.value == View.GONE){
                item.detailsVisibilityLiveData.postValue(View.VISIBLE)
                item.notifyItem()
            }else{
                item.detailsVisibilityLiveData.postValue(View.GONE)
                item.notifyItem()
            }
        }
        if(item.isLoading){
            binding?.progressBar?.visibility = View.VISIBLE
            binding?.buttonsLayout?.visibility =  View.GONE
        }else{
            binding?.progressBar?.visibility = View.GONE
            binding?.buttonsLayout?.visibility =  View.VISIBLE
        }
        binding?.statusDate?.text =  DateUtils.getStringFromDate(item.date, DateUtils.PATTERN_ddMMMyyyyBase, false)*/
    }
}

class ProverAcceptedMessageViewHolder(itemView: View) : MessageViewHolder(itemView) {
     var binding: ItemMessageProverAcceptedBinding? = DataBindingUtil.bind<ItemMessageProverAcceptedBinding>(itemView)
    override fun bind(item: BaseItemMessage) {
        if(item.isError){
            binding?.cardView?.setCardBackgroundColor(App.getContext().getColor(R.color.red))
            binding?.text?.text = App.getContext().resources.getString(R.string.message_credentials_request_declined)
        }else{
            binding?.cardView?.setCardBackgroundColor(App.getContext().getColor(R.color.blue))
            binding?.text?.text = App.getContext().resources.getString(R.string.message_credentials_request_succes)
        }

        binding?.dateText?.text = DateUtils.getStringFromDate(item.date, DateUtils.PATTERN_DATETIME_DOT, false)
    }
}



class ConnectMessageViewHolder(itemView: View) : MessageViewHolder(itemView) {
    var binding: ItemMessageConnectBinding? =
        DataBindingUtil.bind<ItemMessageConnectBinding>(itemView)

    override fun bind(item: BaseItemMessage) {
        binding?.cancelBtn?.setOnClickListener {
            item.cancel()
        }

        binding?.yesBtn?.setOnClickListener {
            item.accept()
        }
        if(item.isLoading){
            binding?.progressBar?.visibility = View.VISIBLE
            binding?.buttonsLayout?.visibility =  View.GONE
        }else{
            binding?.progressBar?.visibility = View.GONE
            binding?.buttonsLayout?.visibility =  View.VISIBLE
        }
    }
}

class ConnectedMessageViewHolder(itemView: View) : MessageViewHolder(itemView) {
     var binding: ItemMessageConnectAcceptedBinding? = DataBindingUtil.bind<ItemMessageConnectAcceptedBinding>(itemView)
    override fun bind(item: BaseItemMessage) {
        if(item.isError){
            binding?.cardView?.setCardBackgroundColor(App.getContext().getColor(R.color.red))
            binding?.text?.text = App.getContext().resources.getString(R.string.message_connected_error)
           // item.errorString
        }else{
            binding?.cardView?.setCardBackgroundColor(App.getContext().getColor(R.color.blue))
            binding?.text?.text = App.getContext().resources.getString(R.string.message_connected_successfully)

        }
        binding?.dateText?.text = DateUtils.getStringFromDate(item.date, DateUtils.PATTERN_DATETIME_DOT, false)
    }
}


class QuestionMessageViewHolder(itemView: View) : MessageViewHolder(itemView) {
    var binding: ItemMessageQuestionBinding? =
        DataBindingUtil.bind<ItemMessageQuestionBinding>(itemView)

    override fun bind(item: BaseItemMessage) {
        val question = item as QuestionItemMessage
        binding?.item = question
        val adapter = QuestionAnswerAdapter()
        adapter.setOnAdapterItemClick {
            item.accept(it.title)
        }
        adapter.setDataList(question.answerList)
        binding?.answerList?.isNestedScrollingEnabled = false
        binding?.answerList?.adapter = adapter
    }
}

class QuestionAcceptedMessageViewHolder(itemView: View) : MessageViewHolder(itemView) {
    // var binding: ItemMessageTextBinding? = DataBindingUtil.bind<ItemMessageTextBinding>(itemView)
    override fun bind(item: BaseItemMessage) {
        //      binding?.item = item

    }
}

class QuestionTimeoutMessageViewHolder(itemView: View) : MessageViewHolder(itemView) {
    // var binding: ItemMessageTextBinding? = DataBindingUtil.bind<ItemMessageTextBinding>(itemView)
    override fun bind(item: BaseItemMessage) {
        //      binding?.item = item

    }
}
