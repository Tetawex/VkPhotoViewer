package org.tetawex.vkphotoviewer.base

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import java.util.ArrayList

abstract class BaseRecyclerAdapter<T, VH : RecyclerView.ViewHolder>(protected var context: Context) : RecyclerView.Adapter<VH>() {
    private val inflater: LayoutInflater = LayoutInflater.from(context)
    var data: MutableList<T> = ArrayList<T>()
        private set(data) {
            field = data
        }


    abstract val layoutId: Int

    abstract fun bindSingleItem(viewHolder: VH, item: T)

    abstract fun createVH(view: View): VH

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VH {
        val view = this.inflater.inflate(this.layoutId, parent, false)
        return this.createVH(view)
    }

    override fun onBindViewHolder(viewHolder: VH, position: Int) {
        this.bindSingleItem(viewHolder, this.data[position])
    }

    fun replaceData(data: List<T>) {
        this.data.clear()
        this.data.addAll(data)
    }

    fun appendData(data: List<T>) {
        this.data.addAll(data)
    }

    fun replaceDataWithNotify(data: List<T>) {
        this.replaceData(data)
        this.notifyDataSetChanged()
    }

    fun appendDataWithNotify(data: List<T>) {
        this.appendData(data)
        this.notifyDataSetChanged()
    }

    fun clear() {
        this.data.clear()
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getItemCount(): Int {
        return this.data.size
    }

    init {
        this.data = ArrayList()
    }
}

