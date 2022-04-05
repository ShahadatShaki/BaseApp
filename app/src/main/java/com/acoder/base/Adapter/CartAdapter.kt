package com.acoder.base.Adapter

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.acoder.base.ModelClass.UserProfile
import com.acoder.base.R
import com.acoder.base.databinding.ListUserBinding
import com.acoder.base.intarface.ClickListener
import java.util.*


/**
 * Created by Shaki
 * 01685558803
 * shahadat.shaki03@gmail.com
 */
class CartAdapter(private val context: Context, private var dataList: ArrayList<UserProfile>, private val clickListener: ClickListener) : RecyclerView.Adapter<CartAdapter.ViewFilesHolder>() {
    private var layoutInflater: LayoutInflater?


    init {
        layoutInflater = context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
    }


    fun setData(list: ArrayList<UserProfile>) {
        this.dataList = list
        notifyDataSetChanged()
    }

    fun getData(): ArrayList<UserProfile> {
        return dataList;
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
