package com.example.soccercodding.extensions

import android.view.View
import androidx.recyclerview.widget.RecyclerView

// extension function for showing view, default its true
fun View.show(show: Boolean = true) {
    // checking if view is visible, otherwise remove visibility
    visibility = if (show) View.VISIBLE else View.GONE
}

// extension function for any type of viewHolder for a RecyclerView to implement onClickListener interface for each element on the adapter
fun <T : RecyclerView.ViewHolder> T.listen(event: (position: Int) -> Unit) = this.also {
    itemView.setOnClickListener {
        event.invoke(adapterPosition)
    }
}