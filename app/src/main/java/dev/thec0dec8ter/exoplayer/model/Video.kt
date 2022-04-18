package dev.thec0dec8ter.exoplayer.model

import android.net.Uri
import android.os.Parcel
import android.os.Parcelable

class Video(var name: String, var uri :Uri) : Parcelable{

    constructor(parcel: Parcel) :this(parcel.readString()!!, parcel.readParcelable(Uri::class.java.classLoader)!!) {

    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(name)
        parcel.writeParcelable(uri, flags)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Video> {
        override fun createFromParcel(parcel: Parcel): Video {
            return Video(parcel)
        }

        override fun newArray(size: Int): Array<Video?> {
            return arrayOfNulls(size)
        }

    }

}