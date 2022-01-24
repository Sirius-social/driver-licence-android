package com.sirius.driverlicense.transform

import com.sirius.driverlicense.models.ui.ItemContacts


interface Transformation  {

    fun toItemContacts() : ItemContacts? {
        return null
    }
}