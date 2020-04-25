package org.wit.lists.models
/*Author: Paul Shanahan
name: Activity list

 */


import android.content.Context
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.google.gson.reflect.TypeToken
import org.jetbrains.anko.AnkoLogger
import org.wit.lists.helpers.*
import org.wit.lists.helpers.exists
import org.wit.lists.helpers.read
import org.wit.lists.helpers.write
import java.util.*

val JSON_FILE = "lists.json"
val gsonBuilder = GsonBuilder().setPrettyPrinting().create()
val listType = object : TypeToken<java.util.ArrayList<ListModel>>() {}.type

fun generateRandomId(): Long {
    return Random().nextLong()
}

class ListJSONStore : ListStore, AnkoLogger {
//this is to store the files in a json folder in the device data section
    val context: Context
    var lists = mutableListOf<ListModel>()

    constructor (context: Context) {
        this.context = context
        if (exists(context, JSON_FILE)) {
            deserialize()
        }
    }

    override fun findAll(): MutableList<ListModel> {
        return lists
    }
//creates another list option
    override fun create(list: ListModel) {
        list.id = generateRandomId()
        lists.add(list)
        serialize()
    }

//update function alllows you to change and update info
    override fun update(list: ListModel) {
        val listsList = findAll() as ArrayList<ListModel>
        var foundList: ListModel? = listsList.find { p -> p.id == list.id }
        if (foundList != null) {
            foundList.title = list.title
            foundList.description = list.description
            foundList.date = list.date
            foundList.names1 = list.names1
            foundList.image = list.image

        }
        serialize()
    }
//deletes a list item
    override fun delete(placemark: ListModel) {
        lists.remove(placemark)
        serialize()
    }

    private fun serialize() {
        val jsonString = gsonBuilder.toJson(lists, listType)
        write(context, JSON_FILE, jsonString)
    }

    private fun deserialize() {
        val jsonString = read(context, JSON_FILE)
        lists = Gson().fromJson(jsonString, listType)
    }
}
