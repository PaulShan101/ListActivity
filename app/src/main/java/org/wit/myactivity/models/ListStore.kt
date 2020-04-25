package org.wit.lists.models
/*Author: Paul Shanahan
name: Activity list

 */

import org.jetbrains.anko.AnkoLogger
import org.jetbrains.anko.info

var lastId = 0L

internal fun getId(): Long {
    return lastId++
}

class ListMemStore : ListStore, AnkoLogger {

    val lists = ArrayList<ListModel>()

    override fun findAll(): List<ListModel> {
        return lists
    }

    override fun create(list: ListModel) {
        list.id = getId()
        lists.add(list)
        logAll()
    }
//if i was adding a new field i would add another field here
    override fun update(list: ListModel) {
        val foundList: ListModel? = lists.find { p -> p.id == list.id }
        if (foundList != null) {
            foundList.title = list.title
            foundList.description = list.description
            foundList.date = list.date
            foundList.names1 = list.names1
            foundList.image = list.image

            logAll()
        }
    }

    override fun delete(list: ListModel) {
        lists.remove(list)
    }
//logging all new info
    fun logAll(){
        lists.forEach {info("${it}")}
    }

}