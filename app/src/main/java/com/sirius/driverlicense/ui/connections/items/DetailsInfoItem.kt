package com.sirius.driverlicense.ui.connections.items


class DetailsInfoItem(title: String, value: String,  val mimeType : String? = null) :DetailsBaseItem(title, value) {

    companion object {
        @JvmStatic
        fun map(): DetailsInfoItem {
            val item = DetailsInfoItem(",","")
           // val item = DetailsInfoItem(attributes.name.orEmpty(), attributes.value.orEmpty(), attributes.mimeType)
            return item
        }
    }

}