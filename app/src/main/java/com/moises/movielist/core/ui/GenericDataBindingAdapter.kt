package com.moises.movielist.core.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView


class GenericDataBindingAdapter<Model>(
    private val variableModelItem: Int,
    private val layoutResId: Int
) : RecyclerView.Adapter<DataBindingViewHolder<Model>>() {

    private var items = mutableListOf<Model>()
    private var itemVariables: List<DataBindingVariables> = mutableListOf()

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): DataBindingViewHolder<Model> {
        val binding: ViewDataBinding = DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            layoutResId,
            parent,
            false
        )
        return DataBindingViewHolder(
            variableModelItem,
            binding
        )
    }

    override fun getItemCount(): Int = items.size


    fun getItems(): MutableList<Model> = items

    fun setItems(items: MutableList<Model>) {
        this.items.clear()
        this.items.addAll(items)
        this.notifyDataSetChanged()
    }

    override fun onBindViewHolder(holder: DataBindingViewHolder<Model>, position: Int) {
        val item = items[position]
        holder.bindItem(item, itemVariables)
    }
}