package com.devdroid.projectday9fin

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class MultiSelectAdapter(
    private var dataset: MutableList<DataSet>,
    private val showMenuDelete:(Boolean) -> Unit
): RecyclerView.Adapter<MultiSelectAdapter.MultiselectViewHolder>(){
    private var isEnable = false
    private val itemSelectedList = mutableListOf<Int>()
    class MultiselectViewHolder(private val view: View):RecyclerView.ViewHolder(view){
        val textView: TextView = view.findViewById(R.id.TextView)
        val imageView: ImageView = view.findViewById(R.id.ImageView)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MultiselectViewHolder{
        val adapterLayout = LayoutInflater.from(parent.context).inflate(R.layout.list_items,parent,false)
        return MultiselectViewHolder(adapterLayout)
    }

    override fun onBindViewHolder(holder: MultiselectViewHolder, position: Int) {
        val item = dataset[position]
        holder.textView.text = item.number.toString()
        holder.imageView.visibility = View.GONE

        holder.textView.setOnLongClickListener{
            selectItem(holder,item,position)
            true
        }
        holder.textView.setOnClickListener{
            if(itemSelectedList.contains(position)){
                itemSelectedList.removeAt(position)
                holder.imageView.visibility = View.GONE
                item.selected = false
                if(itemSelectedList.isEmpty()){
                    showMenuDelete(false)
                    isEnable = false
                }

            }else if(isEnable){
                selectItem(holder,item,position)

            }
        }
    }
    private fun selectItem(holder:MultiSelectAdapter.MultiselectViewHolder,item: DataSet,position: Int){
        isEnable = true
        itemSelectedList.add(position)
        item.selected = true
        holder.imageView.visibility = View.VISIBLE
        showMenuDelete(true)
    }

    override fun getItemCount(): Int {
        return dataset.size
    }
    fun deleteSelectedItem(){
        if(itemSelectedList.isNotEmpty()){
            dataset.removeAll{item -> item.selected}
            isEnable = false
            itemSelectedList.clear()
        }
        notifyDataSetChanged()
    }
}


