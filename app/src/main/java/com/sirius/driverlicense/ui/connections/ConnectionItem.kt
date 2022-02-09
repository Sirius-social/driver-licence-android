package com.sirius.driverlicense.ui.connections

import android.os.Parcel
import android.os.Parcelable


class ConnectionItem (
    val id: String,
    val name: String,
    val type: String,
    val attrsCount: String,
    val icon: Int,
    val color: Int
    //val connectionWrapper: ConnectionsWrapper
) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString() ?:"",
        parcel.readString()?:"",
        parcel.readString()?:"",
        parcel.readString()?:"",
        parcel.readInt(),
        parcel.readInt()
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(id)
        parcel.writeString(name)
        parcel.writeString(type)
        parcel.writeString(attrsCount)
        parcel.writeInt(icon)
        parcel.writeInt(color)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<ConnectionItem> {
        override fun createFromParcel(parcel: Parcel): ConnectionItem {
            return ConnectionItem(parcel)
        }

        override fun newArray(size: Int): Array<ConnectionItem?> {
            return arrayOfNulls(size)
        }
    }

}