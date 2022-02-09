package com.sirius.driverlicense.ui.connections.items


class DetailsSelfAttestedItem(title: String, value: String) :DetailsBaseItem(title, value) {

    companion object {
        @JvmStatic
        fun map(attributes : FirebaseIndyFields): DetailsSelfAttestedItem {
            val item = DetailsSelfAttestedItem(attributes.description, attributes.value ?:"")
            return item
        }
    }

}