package com.example.soccercodding.ui.main.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.soccercodding.databinding.ItemSoccerDataBinding
import com.example.soccercodding.extensions.listen
import com.example.soccercodding.model.SoccerData
import com.example.soccercodding.ui.main.adapter.SoccerDataAdapter.SoccerDataViewHolder

class SoccerDataAdapter(
    private val listener: (SoccerData) -> Unit
) : RecyclerView.Adapter<SoccerDataViewHolder>() {

    private val items = mutableListOf<SoccerData>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        ItemSoccerDataBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        ).let {
            SoccerDataViewHolder(it).listen { position -> listener.invoke(items[position]) }
        }

    override fun onBindViewHolder(holder: SoccerDataViewHolder, position: Int) {
        holder.binding.root.loadSoccerData(items[position])
    }

    override fun getItemCount() = items.size

    fun loadData(list: List<SoccerData>) {
        items.clear()
        items.addAll(list)
        notifyDataSetChanged()
    }

    class SoccerDataViewHolder(
        val binding: ItemSoccerDataBinding
    ) : RecyclerView.ViewHolder(binding.root)
}
