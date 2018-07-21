package org.tetawex.vkphotoviewer.base

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import java.util.*

abstract class BaseRecyclerAdapter<T, VH : RecyclerView.ViewHolder>(protected var context: Context) : RecyclerView.Adapter<VH>() {
    private val inflater: LayoutInflater = LayoutInflater.from(context)
    var data: MutableList<T> = ArrayList<T>()
        protected set(data) {
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
        replaceData(data)
        notifyDataSetChanged()
    }

    fun appendDataWithNotify(data: List<T>) {
        appendData(data)
        notifyDataSetChanged()
    }

    fun clear() {
        data.clear()
        notifyDataSetChanged()
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getItemCount(): Int {
        return data.size
    }

    init {
        data = ArrayList()
    }
}

