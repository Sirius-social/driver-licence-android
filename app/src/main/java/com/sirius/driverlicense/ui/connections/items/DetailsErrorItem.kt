package com.sirius.driverlicense.ui.connections.items


class DetailsErrorItem(title: String, value: String) :DetailsBaseItem(title, value) {

    companion object {
        @JvmStatic
        fun map(attributes : FirebaseIndyFields): DetailsErrorItem {
            val item = DetailsErrorItem(attributes.description, attributes.value ?: "")
            return item
        }
    }

}