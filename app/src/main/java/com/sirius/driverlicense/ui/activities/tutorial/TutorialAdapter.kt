package com.sirius.driverlicense.ui.activities.tutorial

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.sirius.driverlicense.R
import com.sirius.driverlicense.base.ui.SimpleBaseRecyclerViewAdapter

import com.sirius.driverlicense.models.ui.ItemTags
import com.sirius.driverlicense.models.ui.ItemTutorial


class TutorialAdapter :
    SimpleBaseRecyclerViewAdapter<ItemTutorial, TutorialAdapter.TutorialViewHolder>() {


    override fun onBind(holder: TutorialViewHolder?, position: Int) {
        val item = getItem(position)
       // holder?.bind(item)
    }


    override fun getLayoutRes(): Int {
        return 0
       // return R.layout.view_items_tutorial
    }

    override fun getViewHolder(
        parent: ViewGroup?,
        layoutRes: Int,
        viewType: Int
    ): TutorialViewHolder {
        val view =
            LayoutInflater.from(parent?.context).inflate(getLayoutRes(), parent, false)
        return TutorialViewHolder(view)
    }

    class TutorialViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
       /* var binding: ViewItemsTutorialBinding? = DataBindingUtil.bind<ViewItemsTutorialBinding>(itemView)
        fun bind(item: ItemTutorial) {
            binding?.model = item
            binding?.tutorialImage?.setImageResource(item.drawableRes ?: R.drawable.tutorial_1)
        }*/
    }
}