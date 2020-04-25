package org.wit.lists.activities
/*Author: Paul Shanahan
name: Activity list

 */

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.recyclerview.extensions.ListAdapter
import android.support.v7.widget.LinearLayoutManager
import android.view.*
import kotlinx.android.synthetic.main.activity_mark_list.*
import org.jetbrains.anko.intentFor
import org.jetbrains.anko.startActivityForResult
import org.wit.lists.R
import org.wit.lists.main.MainApp
import org.wit.lists.models.ListModel

class ListActivity : AppCompatActivity(), ListListener {

    lateinit var app: MainApp

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_mark_list)
        app = application as MainApp
//this function will load the list function first
        loadList()
        //layout and populate for display
        val layoutManager = LinearLayoutManager(this)
        recyclerView.layoutManager = layoutManager   //recyclerView is a widget in activity_mark_list


        //enable action bar and set title
        //toolbarMain.title = title
        //setSupportActionBar(toolbarMain)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when (item?.itemId) {
            R.id.item_add -> startActivityForResult<List>(0)
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onListClick(list: ListModel) {
        startActivityForResult(intentFor<List>().putExtra("list_edit", list), 0)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        loadList()
        super.onActivityResult(requestCode, resultCode, data)
    }
//this is the load list function for loading the saved lists
    private fun loadList() {
        showLists(app.lists.findAll())
    }
//this function will show the lists from the recycler adapter
    fun showLists (list: kotlin.collections.List<ListModel>) {
        recyclerView.adapter = ListAdapter(list, this)
        recyclerView.adapter?.notifyDataSetChanged()
    }

}