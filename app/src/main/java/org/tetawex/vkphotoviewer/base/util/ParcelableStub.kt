package org.tetawex.vkphotoviewer.base.util

import android.os.Parcel
import android.os.Parcelable

class ParcelableStub() :Parcelable {
    constructor(parcel: Parcel) : this() {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {

    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<ParcelableStub> {
        override fun createFromParcel(parcel: Parcel): ParcelableStub {
            return ParcelableStub(parcel)
        }

        override fun newArray(size: Int): Array<ParcelableStub?> {
            return arrayOfNulls(size)
        }
    }
}