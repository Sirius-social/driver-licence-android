package com.sirius.driverlicense.ui.connections.items


class DetailsRadioItem(title: String, value: String) :DetailsBaseItem(title, value) {

    companion object {
        @JvmStatic
        fun map(attributes : FirebaseIndyFields): DetailsRadioItem {
            val item = DetailsRadioItem(attributes.description, attributes.value ?:"")
            return item
        }
    }

}