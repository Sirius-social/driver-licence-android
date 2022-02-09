package com.sirius.driverlicense.ui.connections.items


class DetailsCheckboxItem(title: String, value: String) :DetailsBaseItem(title, value) {

    companion object {
        @JvmStatic
        fun map(attributes : FirebaseIndyFields): DetailsCheckboxItem {
            val item = DetailsCheckboxItem(attributes.description, attributes.value ?:"")
            return item
        }
    }

}