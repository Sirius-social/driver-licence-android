package com.sirius.driverlicense.ui.chats.chats.holder

import android.app.AlertDialog
import android.content.res.ColorStateList
import android.view.LayoutInflater
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.RecyclerView
import com.sirius.driverlicense.R
import com.sirius.driverlicense.base.App
import com.sirius.driverlicense.databinding.*
import com.sirius.driverlicense.ui.chats.chats.QuestionAnswerAdapter
import com.sirius.driverlicense.ui.chats.chats.message.*
import com.sirius.driverlicense.ui.connections.ConnectionDetailsAdapter
import com.sirius.driverlicense.ui.credentials.CredentialsDetailAdapter
import com.sirius.driverlicense.utils.DateUtils


class TextMessageViewHolder(itemView: View) : MessageViewHolder(itemView) {
    var binding: ItemMessageTextBinding? =
        DataBindingUtil.bind<ItemMessageTextBinding>(itemView)


    override fun bind(item: BaseItemMessage) {
        binding?.item = item
    }
}
class ProposeCredentialMessageViewHolder(itemView: View) : MessageViewHolder(itemView) {
    var binding: ItemMessageProposeBinding? =
        DataBindingUtil.bind<ItemMessageProposeBinding>(itemView)

    override fun bind(item: BaseItemMessage) {
        val offer = item as ProposeCredentialItemMessage
        binding?.item = offer
        /*val adapter = CredentialsDetailAdapter()
        adapter.setDataList(offer.detailList)
        binding?.attachList?.isNestedScrollingEnabled = false
        binding?.attachList?.adapter = adapter*/
        binding?.cancelBtn?.setOnClickListener {
            item.cancel()
        }

        binding?.yesBtn?.setOnClickListener {
            item.accept()
        }

        binding?.showDetailsBtn?.setOnClickListener {
            val builder = AlertDialog.Builder(it.context)
            val view =   LayoutInflater.from(it.context).inflate(R.layout.dialog_connection_card,null,false)
            val recyclerView  : RecyclerView = view.findViewById(R.id.detailsRecyclerView)
            val nameTextView  : TextView = view.findViewById(R.id.connectionUserTextView)
            val iconView  : ImageView = view.findViewById(R.id.connectionIconView)
            val typeView  : TextView = view.findViewById(R.id.connectionTypeTextView)
            val connectionDescriptionTextView  : TextView = view.findViewById(R.id.connectionDescriptionTextView)

            //  var adapter: ConnectionDetailsAdapter? = null
            val adapter = CredentialsDetailAdapter()
            adapter.setDataList(offer.detailList)
            //   binding?.attachList?.isNestedScrollingEnabled = false
            recyclerView.adapter = adapter

            if (item.name?.contains("driver", true) == true) {
                iconView.setImageResource(R.drawable.ic_driver_license)
                val colorInt: Int = App.getContext().getResources().getColor(R.color.yellowMainText)
                val csl = ColorStateList.valueOf(colorInt)
                iconView.imageTintList = csl
                iconView.setBackgroundResource(R.drawable.bg_accent_rounded_button_yellow_text)
            } else if (item.name?.contains("passport", true) == true) {
                iconView.setImageResource(R.drawable.ic_pass)
                val colorInt: Int = App.getContext().getResources().getColor(R.color.greenMainText)
                val csl = ColorStateList.valueOf(colorInt)
                iconView.imageTintList = csl
                iconView.setBackgroundResource(R.drawable.bg_accent_rounded_button_green_text)
            }  else if(item.name?.contains("vehicle", true) == true) {
                iconView.setImageResource(R.drawable.ic_rent_car)
                val colorInt: Int = App.getContext().getResources().getColor(R.color.redMainText)
                val csl = ColorStateList.valueOf(colorInt)
                iconView.imageTintList = csl
                iconView.setBackgroundResource(R.drawable.bg_accent_rounded_button_red_text)
            }else if(item.name?.contains("diploma", true) == true) {
                iconView.setImageResource(R.drawable.ic_diploma)
                val colorInt: Int = App.getContext().getResources().getColor(R.color.redMainText)
                val csl = ColorStateList.valueOf(colorInt)
                iconView.imageTintList = csl
                iconView.setBackgroundResource(R.drawable.bg_accent_rounded_button_red_text)
            }else {
                iconView.setImageResource(R.drawable.documents)
                val colorInt: Int = App.getContext().getResources().getColor(R.color.redMainText)
                val csl = ColorStateList.valueOf(colorInt)
                iconView.imageTintList = csl
                iconView.setBackgroundResource(R.drawable.bg_accent_rounded_button_red_text)
            }

            nameTextView.text = item.name
            connectionDescriptionTextView.text = item.hint


            builder.setView(view)
            builder.show()
        }

        if(item.isLoading){
            binding?.progressBar?.visibility = View.VISIBLE
            binding?.buttonsLayout?.visibility =  View.GONE
        }else{
            binding?.progressBar?.visibility = View.GONE
            binding?.buttonsLayout?.visibility =  View.VISIBLE
        }
        binding?.statusDate?.text =  DateUtils.getStringFromDate(item.date, DateUtils.PATTERN_ddMMMyyyyBase, false)
    }
}

class ProposeCredentialAcceptedMessageViewHolder(itemView: View) : MessageViewHolder(itemView) {
    var binding: ItemMessageProposeAcceptedBinding? = DataBindingUtil.bind<ItemMessageProposeAcceptedBinding>(itemView)
    override fun bind(item: BaseItemMessage) {
        if(item.isError){
            binding?.cardView?.setCardBackgroundColor(App.getContext().getColor(R.color.red))
            binding?.text?.text = App.getContext().resources.getString(R.string.message_credentials_declined)
            binding?.iconView?.setImageResource(R.drawable.ic_attention)
        }else{
            binding?.cardView?.setCardBackgroundColor(App.getContext().getColor(R.color.blue))
            binding?.text?.text = App.getContext().resources.getString(R.string.message_offer_successfully)
            binding?.iconView?.setImageResource(R.drawable.ic_check_outline)
        }



        binding?.dateText?.text = DateUtils.getStringFromDate(item.date, DateUtils.PATTERN_DATETIME_DOT, false)
        var hint = ""
        if(item is ProposeCredentialItemMessage ){
            val offer = item as ProposeCredentialItemMessage
            hint = offer.hint ?:""
        }
        binding?.nameCredText?.text =  item.getTitle()
    }
}



class OfferMessageViewHolder(itemView: View) : MessageViewHolder(itemView) {
    var binding: ItemMessageOfferBinding? =
        DataBindingUtil.bind<ItemMessageOfferBinding>(itemView)

    override fun bind(item: BaseItemMessage) {
       val offer = item as OfferItemMessage
        binding?.item = offer
        /*val adapter = CredentialsDetailAdapter()
        adapter.setDataList(offer.detailList)
        binding?.attachList?.isNestedScrollingEnabled = false
        binding?.attachList?.adapter = adapter*/
        binding?.cancelBtn?.setOnClickListener {
            item.cancel()
        }

        binding?.yesBtn?.setOnClickListener {
            item.accept()
        }

        binding?.showDetailsBtn?.setOnClickListener {
            val builder = AlertDialog.Builder(it.context)
            val view =   LayoutInflater.from(it.context).inflate(R.layout.dialog_connection_card,null,false)
            val recyclerView  : RecyclerView = view.findViewById(R.id.detailsRecyclerView)
            val nameTextView  : TextView = view.findViewById(R.id.connectionUserTextView)
            val iconView  : ImageView = view.findViewById(R.id.connectionIconView)
            val typeView  : TextView = view.findViewById(R.id.connectionTypeTextView)
            val connectionDescriptionTextView  : TextView = view.findViewById(R.id.connectionDescriptionTextView)

          //  var adapter: ConnectionDetailsAdapter? = null
            val adapter = CredentialsDetailAdapter()
            adapter.setDataList(offer.detailList)
         //   binding?.attachList?.isNestedScrollingEnabled = false
            recyclerView.adapter = adapter

            if (item.name?.contains("driver", true) == true) {
                iconView.setImageResource(R.drawable.ic_driver_license)
                val colorInt: Int = App.getContext().getResources().getColor(R.color.yellowMainText)
                val csl = ColorStateList.valueOf(colorInt)
                iconView.imageTintList = csl
                iconView.setBackgroundResource(R.drawable.bg_accent_rounded_button_yellow_text)
            } else if (item.name?.contains("passport", true) == true) {
                iconView.setImageResource(R.drawable.ic_pass)
                val colorInt: Int = App.getContext().getResources().getColor(R.color.greenMainText)
                val csl = ColorStateList.valueOf(colorInt)
                iconView.imageTintList = csl
                iconView.setBackgroundResource(R.drawable.bg_accent_rounded_button_green_text)
            }  else if(item.name?.contains("vehicle", true) == true) {
                iconView.setImageResource(R.drawable.ic_rent_car)
                val colorInt: Int = App.getContext().getResources().getColor(R.color.redMainText)
                val csl = ColorStateList.valueOf(colorInt)
                iconView.imageTintList = csl
                iconView.setBackgroundResource(R.drawable.bg_accent_rounded_button_red_text)
            }else if(item.name?.contains("diploma", true) == true) {
                iconView.setImageResource(R.drawable.ic_diploma)
                val colorInt: Int = App.getContext().getResources().getColor(R.color.redMainText)
                val csl = ColorStateList.valueOf(colorInt)
                iconView.imageTintList = csl
                iconView.setBackgroundResource(R.drawable.bg_accent_rounded_button_red_text)
            }else {
                iconView.setImageResource(R.drawable.documents)
                val colorInt: Int = App.getContext().getResources().getColor(R.color.redMainText)
                val csl = ColorStateList.valueOf(colorInt)
                iconView.imageTintList = csl
                iconView.setBackgroundResource(R.drawable.bg_accent_rounded_button_red_text)
            }

            nameTextView.text = item.name
            connectionDescriptionTextView.text = item.hint


            builder.setView(view)
            builder.show()
        }

        if(item.isLoading){
            binding?.progressBar?.visibility = View.VISIBLE
            binding?.buttonsLayout?.visibility =  View.GONE
        }else{
            binding?.progressBar?.visibility = View.GONE
            binding?.buttonsLayout?.visibility =  View.VISIBLE
        }
        binding?.statusDate?.text =  DateUtils.getStringFromDate(item.date, DateUtils.PATTERN_ddMMMyyyyBase, false)
    }
}

class OfferAcceptedMessageViewHolder(itemView: View) : MessageViewHolder(itemView) {
     var binding: ItemMessageOfferAcceptedBinding? = DataBindingUtil.bind<ItemMessageOfferAcceptedBinding>(itemView)
    override fun bind(item: BaseItemMessage) {
        if(item.isError){
            binding?.cardView?.setCardBackgroundColor(App.getContext().getColor(R.color.red))
            binding?.text?.text = App.getContext().resources.getString(R.string.message_credentials_declined)
            binding?.iconView?.setImageResource(R.drawable.ic_attention)
        }else{
            binding?.cardView?.setCardBackgroundColor(App.getContext().getColor(R.color.blue))
            binding?.text?.text = App.getContext().resources.getString(R.string.message_offer_successfully)
            binding?.iconView?.setImageResource(R.drawable.ic_check_outline)
        }


        if(item.getTitle().contains("driver",true)==true){
            binding?.iconView?.setImageResource(R.drawable.ic_driver_license)
        }else if(item.getTitle()?.contains("passport",true)==true){
            binding?.iconView?.setImageResource(R.drawable.ic_pass)
        }
        else if(item.getTitle().contains("vehicle", true) == true) {
            binding?.iconView?.setImageResource(R.drawable.ic_rent_car)
        }else if(item.getTitle().contains("diploma", true) == true) {
            binding?.iconView?.setImageResource(R.drawable.ic_diploma)
        }else{
            binding?.iconView?.setImageResource(R.drawable.documents)
        }

        val offer = item as OfferItemMessage

        binding?.dateText?.text = DateUtils.getStringFromDate(item.date, DateUtils.PATTERN_DATETIME_DOT, false)
        var hint = ""
        if(item is OfferItemMessage ){
            val offer = item as OfferItemMessage
            hint = offer.hint ?:""
        }

        binding?.nameCredText?.text =  offer.name

        binding?.showDetailsBtn?.setOnClickListener {
            val builder = AlertDialog.Builder(it.context)
            val view =   LayoutInflater.from(it.context).inflate(R.layout.dialog_connection_card,null,false)
            val recyclerView  : RecyclerView = view.findViewById(R.id.detailsRecyclerView)
            val nameTextView  : TextView = view.findViewById(R.id.connectionUserTextView)
            val iconView  : ImageView = view.findViewById(R.id.connectionIconView)
            val typeView  : TextView = view.findViewById(R.id.connectionTypeTextView)
            val connectionDescriptionTextView  : TextView = view.findViewById(R.id.connectionDescriptionTextView)

            //  var adapter: ConnectionDetailsAdapter? = null
            val adapter = CredentialsDetailAdapter()
            adapter.setDataList(offer.detailList)
            //   binding?.attachList?.isNestedScrollingEnabled = false
            recyclerView.adapter = adapter

            if (item.name?.contains("driver", true) == true) {
                iconView.setImageResource(R.drawable.ic_driver_license)
                val colorInt: Int = App.getContext().getResources().getColor(R.color.yellowMainText)
                val csl = ColorStateList.valueOf(colorInt)
                iconView.imageTintList = csl
                iconView.setBackgroundResource(R.drawable.bg_accent_rounded_button_yellow_text)
            } else if (item.name?.contains("passport", true) == true) {
                iconView.setImageResource(R.drawable.ic_pass)
                val colorInt: Int = App.getContext().getResources().getColor(R.color.greenMainText)
                val csl = ColorStateList.valueOf(colorInt)
                iconView.imageTintList = csl
                iconView.setBackgroundResource(R.drawable.bg_accent_rounded_button_green_text)
            }  else if(item.name?.contains("vehicle", true) == true) {
                iconView.setImageResource(R.drawable.ic_rent_car)
                val colorInt: Int = App.getContext().getResources().getColor(R.color.redMainText)
                val csl = ColorStateList.valueOf(colorInt)
                iconView.imageTintList = csl
                iconView.setBackgroundResource(R.drawable.bg_accent_rounded_button_red_text)
            }else if(item.name?.contains("diploma", true) == true) {
                iconView.setImageResource(R.drawable.ic_diploma)
                val colorInt: Int = App.getContext().getResources().getColor(R.color.redMainText)
                val csl = ColorStateList.valueOf(colorInt)
                iconView.imageTintList = csl
                iconView.setBackgroundResource(R.drawable.bg_accent_rounded_button_red_text)
            }else {
                iconView.setImageResource(R.drawable.documents)
                val colorInt: Int = App.getContext().getResources().getColor(R.color.redMainText)
                val csl = ColorStateList.valueOf(colorInt)
                iconView.imageTintList = csl
                iconView.setBackgroundResource(R.drawable.bg_accent_rounded_button_red_text)
            }


            nameTextView.text = item.name
            connectionDescriptionTextView.text = item.hint


            builder.setView(view)
            builder.show()
        }
    }
}

class ProverMessageViewHolder(itemView: View) : MessageViewHolder(itemView) {
    var binding: ItemMessageProverBinding? =
        DataBindingUtil.bind<ItemMessageProverBinding>(itemView)

    override fun bind(item: BaseItemMessage) {
        val prover = item as ProverItemMessage
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


        binding?.showDetailsBtn?.setOnClickListener {
            val builder = AlertDialog.Builder(it.context)
            val view =   LayoutInflater.from(it.context).inflate(R.layout.dialog_connection_card,null,false)
            val recyclerView  : RecyclerView = view.findViewById(R.id.detailsRecyclerView)
            val nameTextView  : TextView = view.findViewById(R.id.connectionUserTextView)
            val iconView  : ImageView = view.findViewById(R.id.connectionIconView)
            val typeView  : TextView = view.findViewById(R.id.connectionTypeTextView)
            val connectionDescriptionTextView  : TextView = view.findViewById(R.id.connectionDescriptionTextView)

            //  var adapter: ConnectionDetailsAdapter? = null
            val adapter = CredentialsDetailAdapter()
            adapter.setDataList(prover.detailList)
            //   binding?.attachList?.isNestedScrollingEnabled = false
            recyclerView.adapter = adapter

            if (item.name?.contains("driver", true) == true) {
                iconView.setImageResource(R.drawable.ic_driver_license)
                val colorInt: Int = App.getContext().getResources().getColor(R.color.yellowMainText)
                val csl = ColorStateList.valueOf(colorInt)
                iconView.imageTintList = csl
                iconView.setBackgroundResource(R.drawable.bg_accent_rounded_button_yellow_text)
            } else if (item.name?.contains("passport", true) == true) {
                iconView.setImageResource(R.drawable.ic_pass)
                val colorInt: Int = App.getContext().getResources().getColor(R.color.greenMainText)
                val csl = ColorStateList.valueOf(colorInt)
                iconView.imageTintList = csl
                iconView.setBackgroundResource(R.drawable.bg_accent_rounded_button_green_text)
            }  else if(item.name?.contains("vehicle", true) == true) {
                iconView.setImageResource(R.drawable.ic_rent_car)
                val colorInt: Int = App.getContext().getResources().getColor(R.color.redMainText)
                val csl = ColorStateList.valueOf(colorInt)
                iconView.imageTintList = csl
                iconView.setBackgroundResource(R.drawable.bg_accent_rounded_button_red_text)
            }else if(item.name?.contains("diploma", true) == true) {
                iconView.setImageResource(R.drawable.ic_diploma)
                val colorInt: Int = App.getContext().getResources().getColor(R.color.redMainText)
                val csl = ColorStateList.valueOf(colorInt)
                iconView.imageTintList = csl
                iconView.setBackgroundResource(R.drawable.bg_accent_rounded_button_red_text)
            }else {
                iconView.setImageResource(R.drawable.documents)
                val colorInt: Int = App.getContext().getResources().getColor(R.color.redMainText)
                val csl = ColorStateList.valueOf(colorInt)
                iconView.imageTintList = csl
                iconView.setBackgroundResource(R.drawable.bg_accent_rounded_button_red_text)
            }

            nameTextView.text = item.name
            connectionDescriptionTextView.text = item.hint


            builder.setView(view)
            builder.show()
        }


     /*   binding?.detailsBtn?.setOnClickListener {
            if( item.detailsVisibilityLiveData.value == View.GONE){
                item.detailsVisibilityLiveData.postValue(View.VISIBLE)
                item.notifyItem()
            }else{
                item.detailsVisibilityLiveData.postValue(View.GONE)
                item.notifyItem()
            }
        }*/
        if(item.isLoading){
            binding?.progressBar?.visibility = View.VISIBLE
            binding?.buttonsLayout?.visibility =  View.GONE
        }else{
            binding?.progressBar?.visibility = View.GONE
            binding?.buttonsLayout?.visibility =  View.VISIBLE
        }
        binding?.statusDate?.text =  DateUtils.getStringFromDate(item.date, DateUtils.PATTERN_ddMMMyyyyBase, false)
    }
}

class ProverAcceptedMessageViewHolder(itemView: View) : MessageViewHolder(itemView) {
     var binding: ItemMessageProverAcceptedBinding? = DataBindingUtil.bind<ItemMessageProverAcceptedBinding>(itemView)
    override fun bind(item: BaseItemMessage) {
        if(item.isError){
            binding?.cardView?.setCardBackgroundColor(App.getContext().getColor(R.color.red))
            binding?.text?.text = App.getContext().resources.getString(R.string.message_credentials_request_declined)
            binding?.iconView?.setImageResource(R.drawable.ic_attention)
        }else{
            binding?.cardView?.setCardBackgroundColor(App.getContext().getColor(R.color.blue))
            binding?.text?.text = App.getContext().resources.getString(R.string.message_offer_provided)
            binding?.iconView?.setImageResource(R.drawable.ic_check_outline)
        }
        val prover = item as ProverItemMessage
        binding?.nameCredText?.text = prover.name
        if(prover.name?.contains("driver",true)==true){
            binding?.iconView?.setImageResource(R.drawable.ic_driver_license)
        }else if(prover.name?.contains("passport",true)==true){
            binding?.iconView?.setImageResource(R.drawable.ic_pass)
        }else if(item.getTitle().contains("vehicle", true) == true) {
            binding?.iconView?.setImageResource(R.drawable.ic_rent_car)
        }else if(item.getTitle().contains("diploma", true) == true) {
            binding?.iconView?.setImageResource(R.drawable.ic_diploma)
        }else{
            binding?.iconView?.setImageResource(R.drawable.documents)
        }
        binding?.dateText?.text = DateUtils.getStringFromDate(item.date, DateUtils.PATTERN_DATETIME_DOT, false)
        binding?.showDetailsBtn?.setOnClickListener {
            val builder = AlertDialog.Builder(it.context)
            val view =   LayoutInflater.from(it.context).inflate(R.layout.dialog_connection_card,null,false)
            val recyclerView  : RecyclerView = view.findViewById(R.id.detailsRecyclerView)
            val nameTextView  : TextView = view.findViewById(R.id.connectionUserTextView)
            val iconView  : ImageView = view.findViewById(R.id.connectionIconView)
            val typeView  : TextView = view.findViewById(R.id.connectionTypeTextView)
            val connectionDescriptionTextView  : TextView = view.findViewById(R.id.connectionDescriptionTextView)

            //  var adapter: ConnectionDetailsAdapter? = null
            val adapter = CredentialsDetailAdapter()
            adapter.setDataList(prover.detailList)
            //   binding?.attachList?.isNestedScrollingEnabled = false
            recyclerView.adapter = adapter

            if (item.name?.contains("driver", true) == true) {
                iconView.setImageResource(R.drawable.ic_driver_license)
                val colorInt: Int = App.getContext().getResources().getColor(R.color.yellowMainText)
                val csl = ColorStateList.valueOf(colorInt)
                iconView.imageTintList = csl
                iconView.setBackgroundResource(R.drawable.bg_accent_rounded_button_yellow_text)
            } else if (item.name?.contains("passport", true) == true) {
                iconView.setImageResource(R.drawable.ic_pass)
                val colorInt: Int = App.getContext().getResources().getColor(R.color.greenMainText)
                val csl = ColorStateList.valueOf(colorInt)
                iconView.imageTintList = csl
                iconView.setBackgroundResource(R.drawable.bg_accent_rounded_button_green_text)
            } else if(item.name?.contains("vehicle", true) == true) {
                iconView.setImageResource(R.drawable.ic_rent_car)
                val colorInt: Int = App.getContext().getResources().getColor(R.color.redMainText)
                val csl = ColorStateList.valueOf(colorInt)
                iconView.imageTintList = csl
                iconView.setBackgroundResource(R.drawable.bg_accent_rounded_button_red_text)
            }else if(item.name?.contains("diploma", true) == true) {
                iconView.setImageResource(R.drawable.ic_diploma)
                val colorInt: Int = App.getContext().getResources().getColor(R.color.redMainText)
                val csl = ColorStateList.valueOf(colorInt)
                iconView.imageTintList = csl
                iconView.setBackgroundResource(R.drawable.bg_accent_rounded_button_red_text)
            } else {
                iconView.setImageResource(R.drawable.documents)
                val colorInt: Int = App.getContext().getResources().getColor(R.color.redMainText)
                val csl = ColorStateList.valueOf(colorInt)
                iconView.imageTintList = csl
                iconView.setBackgroundResource(R.drawable.bg_accent_rounded_button_red_text)

        }

            nameTextView.text = item.name
            connectionDescriptionTextView.text = item.hint


            builder.setView(view)
            builder.show()
        }

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

        binding?.nameTextView?.text = item.getTitle()
        if(item.isLoading){
            binding?.progressBar?.visibility = View.VISIBLE
            binding?.buttonsLayout?.visibility =  View.GONE
        }else{
            binding?.progressBar?.visibility = View.GONE
            binding?.buttonsLayout?.visibility =  View.VISIBLE
        }
        val connectItem = item as ConnectItemMessage
        binding?.nameTextView?.text = connectItem.name
       // binding?.nameCredText?.text = connectItem?.name
    }
}

class ConnectedMessageViewHolder(itemView: View) : MessageViewHolder(itemView) {
     var binding: ItemMessageConnectAcceptedBinding? = DataBindingUtil.bind<ItemMessageConnectAcceptedBinding>(itemView)
    override fun bind(item: BaseItemMessage) {
        val connectItem = item as ConnectItemMessage
        if(item.isError){
            binding?.cardView?.setCardBackgroundColor(App.getContext().getColor(R.color.red))
            binding?.text?.text = App.getContext().resources.getString(R.string.message_connected_error)
            binding?.iconView?.setImageResource(R.drawable.ic_attention)
           // item.errorString
        }else{
            binding?.cardView?.setCardBackgroundColor(App.getContext().getColor(R.color.blue))
            binding?.text?.text = App.getContext().resources.getString(R.string.message_connected_successfully)
            binding?.iconView?.setImageResource(R.drawable.ic_check_outline)

        }
        binding?.dateText?.text = DateUtils.getStringFromDate(item.date, DateUtils.PATTERN_DATETIME_DOT, false)
        binding?.nameCredText?.text = connectItem?.name
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
