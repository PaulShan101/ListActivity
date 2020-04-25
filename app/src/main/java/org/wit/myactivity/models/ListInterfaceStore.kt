package org.wit.lists.models
/*Author: Paul Shanahan
name: Activity list

 */
//interface section stores all the functions find, create, update and delete
interface ListStore {
    fun findAll(): List<ListModel>
    fun create(list: ListModel)
    fun update(list: ListModel)
    fun delete(list: ListModel)
}