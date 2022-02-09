package com.sirius.driverlicense.ui.connections.items


class DetailsSuccessItem(title: String, value: String) :DetailsBaseItem(title, value) {

    companion object {
        @JvmStatic
        fun map(attributes : FirebaseIndyFields): DetailsSuccessItem {
            val item = DetailsSuccessItem(attributes.description, attributes.value ?:"")
            return item
        }
    }

}