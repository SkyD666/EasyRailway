package com.skyd.easyrailway.view.adapter

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.skyd.easyrailway.bean.BaseBean
import com.skyd.easyrailway.config.Const
import com.skyd.easyrailway.util.ViewHolderUtil.Companion.getViewHolder
import com.skyd.easyrailway.util.ViewHolderUtil.Companion.getItemViewType

abstract class BaseRvAdapter(
    private val dataList: List<BaseBean>
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return getViewHolder(parent, viewType)
    }

    override fun getItemViewType(position: Int): Int {
        return if (position < dataList.size) getItemViewType(dataList[position])
        else Const.ViewHolderTypeInt.UNKNOWN
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
    }

    override fun getItemCount(): Int = dataList.size
}