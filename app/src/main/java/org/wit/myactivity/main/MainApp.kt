package org.wit.lists.main
/*Author: Paul Shanahan
name: Activity list

 */
import android.app.Application
import org.jetbrains.anko.AnkoLogger
import org.jetbrains.anko.info
import org.wit.lists.models.ListJSONStore

import org.wit.lists.models.ListStore

class MainApp : Application(), AnkoLogger {
//this is the main section
    lateinit var lists: ListStore

    override fun onCreate() {
        super.onCreate()
        lists = ListJSONStore(applicationContext)
        info("List started")
    }
}