package com.sirius.driverlicense.ui.connections.items



open class DetailsBaseItem(val title : String,
                           var value : String){

    companion object {
        fun mapTo(attr : FirebaseIndyFields) : DetailsBaseItem{
            if(attr.isSelfAttested){
                return DetailsSelfAttestedItem.map(attr)
            }
            if (attr.value.isNullOrEmpty()) {
                return DetailsErrorItem.map(attr)
            } else {
                return DetailsSuccessItem.map(attr)
            }
        }
    }
}