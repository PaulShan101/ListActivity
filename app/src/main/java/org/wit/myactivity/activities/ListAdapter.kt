package org.wit.lists.activities
/*Author: Paul Shanahan
name: Activity list

 */

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.card_list.view.*
import kotlinx.android.synthetic.main.card_list.view.description
import kotlinx.android.synthetic.main.card_list.view.listTitle

import org.wit.lists.R
import org.wit.lists.helpers.readImageFromPath
import org.wit.lists.models.ListModel
import kotlin.collections.List

interface ListListener {
    fun onListClick(list: ListModel)
}

class ListAdapter constructor(private var lists: List<ListModel>,
                              private val listener: ListListener) : RecyclerView.Adapter<ListAdapter.MainHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainHolder {
        return MainHolder(LayoutInflater.from(parent.context).inflate(R.layout.card_list, parent, false))
    }
//this function holds the lists
    override fun onBindViewHolder(holder: MainHolder, position: Int) {
        val list = lists[holder.adapterPosition]
        holder.bind(list, listener)
    }

    override fun getItemCount(): Int = lists.size
//an Itemview to hold info if i wanted to add another filed i would edit this
    class MainHolder constructor(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bind(list: ListModel, listener : ListListener) {
            itemView.listTitle.text = list.title
            itemView.description.text = list.description
            itemView.date1.text = list.date
            itemView.name1.text = list.names1
            itemView.pb1.text = list.pb1
            itemView.imageIcon.setImageBitmap(readImageFromPath(itemView.context, list.image))
            itemView.setOnClickListener { listener.onListClick(list) }
        }
    }
}