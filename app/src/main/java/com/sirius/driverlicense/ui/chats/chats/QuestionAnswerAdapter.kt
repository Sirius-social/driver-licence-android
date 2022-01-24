package com.sirius.driverlicense.ui.chats.chats

import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.sirius.driverlicense.R
import com.sirius.driverlicense.base.ui.SimpleBaseRecyclerViewAdapter
import com.sirius.driverlicense.databinding.ViewItemsQuestionAnswerBinding
import com.sirius.driverlicense.models.ui.ItemQuestionAnswer


class QuestionAnswerAdapter :
    SimpleBaseRecyclerViewAdapter<ItemQuestionAnswer, QuestionAnswerAdapter.QuestionAnswerViewHolder>() {



    class QuestionAnswerViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var binding: ViewItemsQuestionAnswerBinding? = DataBindingUtil.bind<ViewItemsQuestionAnswerBinding>(itemView)
        fun bind(item: ItemQuestionAnswer) {
            binding?.item = item
        }
    }

    override fun getLayoutRes(): Int {
        return R.layout.view_items_question_answer
    }

    override fun getViewHolder(
        parent: ViewGroup?,
        layoutRes: Int,
        viewType: Int
    ): QuestionAnswerViewHolder {
        return QuestionAnswerViewHolder(getInflatedView(getLayoutRes(),parent, false))
    }

    override fun onBind(holder: QuestionAnswerViewHolder?, position: Int) {
        val item = getItem(position)
        holder?.bind(item)
        holder?.itemView?.setOnClickListener {
            onAdapterItemClick?.onItemClick(item)
        }
    }

}