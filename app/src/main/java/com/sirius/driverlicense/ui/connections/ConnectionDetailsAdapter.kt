package  com.sirius.driverlicense.ui.connections

import android.graphics.BitmapFactory
import android.text.Editable
import android.text.TextWatcher
import android.util.Base64
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.FragmentManager
import androidx.recyclerview.widget.RecyclerView.*
import com.sirius.driverlicense.R
import com.sirius.driverlicense.ui.connections.items.*


private const val RADIO = 1
private const val CHECKBOX = 2
private const val SUCCESS = 3
private const val ERROR = 4
private const val INFO = 5
private const val SELF_ATTESTED = 6

class ConnectionDetailsAdapter(
    private val onRadioClick: (DetailsRadioItem) -> Unit,
    private val onCheckboxClick: (DetailsCheckboxItem) -> Unit,
    private val onValueListener: (DetailsSelfAttestedItem) -> Unit
) : Adapter<ViewHolder>() {

    lateinit var fragmentManager: FragmentManager

    private val items: MutableList<DetailsBaseItem> = mutableListOf()

    fun setItems(items: List<DetailsBaseItem>) {
        this.items.clear()
        this.items.addAll(items)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return when (viewType) {
            RADIO    -> {
                val view = LayoutInflater.from(parent.context).inflate(R.layout.item_details_radio, parent, false)
                RadioViewHolder(view)
            }
            SELF_ATTESTED    -> {
                val view = LayoutInflater.from(parent.context).inflate(R.layout.item_details_self_attesed, parent, false)
                SelfAttestedViewHolder(view)
            }
            CHECKBOX -> {
                val view = LayoutInflater.from(parent.context).inflate(R.layout.item_details_checkbox, parent, false)
                CheckBoxViewHolder(view)
            }
            SUCCESS  -> {
                val view = LayoutInflater.from(parent.context).inflate(R.layout.item_details_success, parent, false)
                SuccessViewHolder(view)
            }
            ERROR    -> {
                val view = LayoutInflater.from(parent.context).inflate(R.layout.item_details_error, parent, false)
                ErrorViewHolder(view)
            }
            else     -> {
                val view = LayoutInflater.from(parent.context).inflate(R.layout.item_details_data, parent, false)
                InfoViewHolder(view)
            }
        }
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        when (holder) {
            is CheckBoxViewHolder -> holder.bind(items[position] as DetailsCheckboxItem)
            is RadioViewHolder    -> holder.bind(items[position] as DetailsRadioItem)
            is SuccessViewHolder  -> holder.bind(items[position] as DetailsSuccessItem)
            is ErrorViewHolder    -> holder.bind(items[position] as DetailsErrorItem)
            is InfoViewHolder     -> holder.bind(items[position] as DetailsInfoItem)
            is SelfAttestedViewHolder     -> holder.bind(items[position] as DetailsSelfAttestedItem)
        }
    }

    override fun getItemViewType(position: Int): Int {
        var type = INFO
        type  = when (items[position]) {
            is DetailsSuccessItem  -> SUCCESS
            is DetailsErrorItem    -> ERROR
            is DetailsInfoItem     -> INFO
            is DetailsCheckboxItem -> CHECKBOX
            is DetailsRadioItem    -> RADIO
            is DetailsSelfAttestedItem    -> SELF_ATTESTED
            else -> INFO

        }
        return type
    }

    inner class CheckBoxViewHolder(itemView: View) : ViewHolder(itemView) {

         val containerView: View?
            get() = itemView

        fun bind(connectionItem: DetailsCheckboxItem) {
         //   itemView.itemNameCheckboxTextView.text = connectionItem.title
        }
    }

    inner class RadioViewHolder(itemView: View) : ViewHolder(itemView) {

         val containerView: View?
            get() = itemView

        fun bind(connectionItem: DetailsRadioItem) {
           // itemView.itemNameRadioTextView.text = connectionItem.title
        }
    }

    inner class SelfAttestedViewHolder(itemView: View) : ViewHolder(itemView) {

         val containerView: View?
            get() = itemView


        fun bind(connectionItem: DetailsSelfAttestedItem) {
            /*itemView.itemTitleTextView.text = connectionItem.title
            itemView.valueText.text =  connectionItem.value
            if(connectionItem.value.isNullOrEmpty()){
                itemView.valueText.text = App.getContext().resources.getString(R.string.tap_to_fill_the_field)
            }
            itemView.valueField.setOnClickListener {
                EditTextDialogFragment(connectionItem.title, connectionItem.value, object : EditTextDialogFragment.OnOkBtnListener{
                    override fun onOk(value: String) {
                        connectionItem.value = value
                        Log.d("mylog000","afterTextChanged  connectionItem.value="+ connectionItem.value)
                        onValueListener?.invoke(connectionItem)
                    }

                }).show(fragmentManager, SelectorDialogFragment::class.java.simpleName)
            }
*/
        }
    }

    inner class SuccessViewHolder(itemView: View) : ViewHolder(itemView) {

         val containerView: View?
            get() = itemView

        fun bind(connectionItem: DetailsSuccessItem) {
           // itemView.itemNameSuccesTextView.text = connectionItem.title
        }
    }

    inner class ErrorViewHolder(itemView: View) : ViewHolder(itemView) {

         val containerView: View?
            get() = itemView

        fun bind(connectionItem: DetailsErrorItem) {
           // itemView.itemNameTextView.text = connectionItem.title
        }
    }

    inner class InfoViewHolder(itemView: View) : ViewHolder(itemView) {

         val containerView: View?
            get() = itemView

        fun bind(connectionItem: DetailsInfoItem) {
          /*  itemView.itemTitleTextView.text = connectionItem.title
            if(connectionItem.mimeType !=null){
                val encodedByte: ByteArray =
                    Base64.decode(connectionItem.value.toByteArray(charset("UTF-8")), Base64.NO_WRAP)
                if(connectionItem.mimeType.contains("image")){
                    itemView.image.visibility = VISIBLE
                   val bitmap =  BitmapFactory.decodeByteArray(encodedByte,0,encodedByte.size)
                    Glide.with(itemView).load(bitmap).error(R.drawable.ic_send_error).into(itemView.image)
                }else{
                    val decodedString = String(encodedByte)
                    itemView.itemDescTextView.text = decodedString
                }

            }else{
                itemView.itemDescTextView.text = connectionItem.value
            }
*/

        }
    }
}