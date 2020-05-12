package org.wit.lists.activities
/*Author: Paul Shanahan
name: Activity list

 */

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import kotlinx.android.synthetic.main.activity_list.*
import kotlinx.android.synthetic.main.activity_list.date1
import kotlinx.android.synthetic.main.activity_list.description
import kotlinx.android.synthetic.main.activity_list.listTitle
import org.jetbrains.anko.AnkoLogger
import org.jetbrains.anko.info
import org.jetbrains.anko.toast

import org.wit.lists.models.ListModel
import org.wit.lists.R
import org.wit.lists.helpers.readImage
import org.wit.lists.helpers.readImageFromPath
import org.wit.lists.helpers.showImagePicker
import org.wit.lists.main.MainApp




class List : AppCompatActivity(), AnkoLogger {

    var list = ListModel()
    lateinit var app : MainApp
    val IMAGE_REQUEST = 1
    var edit = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list)
        app = application as MainApp

        //toolbarAdd.title = title
       //setSupportActionBar(toolbarAdd)

        if (intent.hasExtra("list_edit")) {
            edit = true
            list = intent.run { extras.getParcelable<ListModel>("list_edit") }
            listTitle.setText(list.title)
            description.setText(list.description)
            date1.setText(list.date)
            name1.setText(list.names1)
            pb1.setText(list.pb1)

            placemarkImage.setImageBitmap(readImageFromPath(this, list.image))
            chooseImage.setText(R.string.change_list_image)
            btnAdd.setText(R.string.save_listmark)

        }

//these are the buttons that control or take you too next page they are linked to activity_list
        btnAdd.setOnClickListener() {
            list.title = listTitle.text.toString()
            list.description = description.text.toString()
            list.date = date1.text.toString()
            list.names1 = name1.text.toString()
            list.pb1 = pb1.text.toString()
            //here
            if (list.title.isEmpty()) {
                toast("Enter List Title")
            } else {
                if (edit) {
                    app.lists.update(list.copy())
                } else {
                    app.lists.create(list.copy())
                }
            }
            info("add Button Pressed: $listTitle")
            setResult(AppCompatActivity.RESULT_OK)
            finish()
        }
//chooses the image in your device
        chooseImage.setOnClickListener {
            showImagePicker(this, IMAGE_REQUEST)
        }




    }
//controls the menu
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_placemark, menu)
        if (edit && menu != null) menu.getItem(0).setVisible(true)
        return super.onCreateOptionsMenu(menu)
    }
//these items control the delete list and the cancel options
    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when (item?.itemId) {
            R.id.item_delete -> {
                app.lists.delete(list)
                finish()
            }
            R.id.item_cancel -> {
                finish()
            }
        }
        return super.onOptionsItemSelected(item)
    }


// this controls the image
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        when (requestCode) {
            IMAGE_REQUEST -> if (data != null) {
                list.image = data.run { getData().toString() }
                placemarkImage.setImageBitmap(readImage(this, resultCode, data))
                chooseImage.setText("Change List title")
            }


        }
    }
}