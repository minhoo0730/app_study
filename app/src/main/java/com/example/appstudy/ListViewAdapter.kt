package com.example.appstudy

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView

class ListViewAdapter (val List : MutableList<Model>):BaseAdapter(){
    override fun getCount(): Int {
        return List.count()
    }

    override fun getItem(position: Int) {
        List[position]
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        var view = convertView

        if(view == null){
            view = LayoutInflater.from(parent?.context).inflate(R.layout.listview_item, parent, false)
        }
        val title: TextView? = view?.findViewById(R.id.ItemTextId)
        title!!.text = List[position].title
        return view!!
    }
}