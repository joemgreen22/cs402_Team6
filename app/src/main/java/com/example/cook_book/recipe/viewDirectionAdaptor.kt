package com.example.cook_book.recipe

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.cook_book.R

class viewDirectionAdaptor(var directions: List<String>) : RecyclerView.Adapter<viewDirectionAdaptor.Line>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): viewDirectionAdaptor.Line {
        val view = LayoutInflater.from(parent.getContext()).inflate(R.layout.view_direction_entry, parent, false)
        return Line(view)
    }

    override fun onBindViewHolder(line: viewDirectionAdaptor.Line, position: Int) {
        line.apply {
            count.text = "${position + 1}"
            direction.text = directions[position]
        }
    }

    override fun getItemCount(): Int = directions.size

    inner class Line(val view: View) : RecyclerView.ViewHolder(view) {
        val count = view.findViewById<TextView>(R.id.count)
        val direction = view.findViewById<TextView>(R.id.direction_text)

    }

}