package org.wit.lists.models
/*Author: Paul Shanahan
name: Activity list

 */
import android.os.Parcelable
import kotlinx.android.parcel.Parcelize
//this is the parcelable section if i had a new field i would add it to these
@Parcelize
data class ListModel(var id: Long = 0,
                     var title: String = "",
                     var description: String = "",
                     var date: String = "",
                     var names1: String = "",
                     var image: String = "") : Parcelable





