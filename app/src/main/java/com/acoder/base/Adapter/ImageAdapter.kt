package com.acoder.base.Adapter

import android.annotation.SuppressLint
import android.content.Context
import android.net.Uri
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.acoder.base.R
import com.acoder.base.databinding.ListUserBinding
import com.acoder.base.intarface.ClickListener
import java.util.*


/**
 * Created by Shaki
 * 01685558803
 * shahadat.shaki03@gmail.com
 */
class ImageAdapter(private val context: Context, private var dataList: ArrayList<Uri>, private val clickListener: ClickListener) : RecyclerView.Adapter<ImageAdapter.ViewFilesHolder>() {
    private var layoutInflater: LayoutInflater?


    init {
        layoutInflater = context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
    }


    fun setData(list: ArrayList<Uri>) {
        this.dataList = list
        notifyDataSetChanged()
    }

    fun getData(): ArrayList<Uri> {
        return  dataList
    }


    override fun onCreateViewHolder(parent: ViewGroup, i: Int): ViewFilesHolder {

        if (layoutInflater == null) {
            layoutInflater = LayoutInflater.from(parent.context)
        }
        val binding: ListUserBinding = DataBindingUtil.inflate(layoutInflater!!, R.layout.list_user, parent, false)
        return ViewFilesHolder(binding)
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(viewHolder: ViewFilesHolder, i: Int) {

        val b = viewHolder.binding
        val item = dataList[i]

//        Constants.loadImage(b.imageView, item)

        b.imageView.setImageURI(item)


    }

    override fun getItemViewType(position: Int): Int {
        return position
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }


    override fun getItemCount(): Int {
        return dataList.size
    }


    inner class ViewFilesHolder(val binding: ListUserBinding) : RecyclerView.ViewHolder(binding.root)


}
