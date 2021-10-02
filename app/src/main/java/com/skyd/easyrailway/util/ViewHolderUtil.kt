package com.skyd.easyrailway.util

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.skyd.easyrailway.R
import com.skyd.easyrailway.bean.BaseBean
import com.skyd.easyrailway.config.Const.ViewHolderTypeInt
import com.skyd.easyrailway.config.Const.ViewHolderTypeString

class ViewHolderUtil {
    companion object {

        fun getItemViewType(item: BaseBean): Int = when (item.type) {
            ViewHolderTypeString.LINE_1 -> ViewHolderTypeInt.LINE_1
            ViewHolderTypeString.GROUP_LINE_1 -> ViewHolderTypeInt.GROUP_LINE_1
            else -> ViewHolderTypeInt.UNKNOWN
        }

        fun getViewHolder(parent: ViewGroup, viewType: Int) = when (viewType) {
            ViewHolderTypeInt.LINE_1 -> Line1ViewHolder(
                LayoutInflater.from(parent.context)
                    .inflate(R.layout.item_line_1, parent, false)
            )
            ViewHolderTypeInt.GROUP_LINE_1 -> GroupLine1ViewHolder(
                LayoutInflater.from(parent.context)
                    .inflate(R.layout.item_group_line_1, parent, false)
            )
            else -> EmptyViewHolder(View(parent.context))
        }
    }
}

class EmptyViewHolder(view: View) : RecyclerView.ViewHolder(view)

class Line1ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    val tvLine1Start: TextView = view.findViewById(R.id.tv_line_1_start)
    val tvLine1Destination: TextView = view.findViewById(R.id.tv_line_1_destination)
    val tvLine1StartTime: TextView = view.findViewById(R.id.tv_line_1_start_time)
    val tvLine1DestinationTime: TextView = view.findViewById(R.id.tv_line_1_destination_time)
    val tvLine1Duration: TextView = view.findViewById(R.id.tv_line_1_duration)
    val tvLine1TrainNumber: TextView = view.findViewById(R.id.tv_line_1_train_number)
    val tvLine1Cost: TextView = view.findViewById(R.id.tv_line_1_cost)
}

class GroupLine1ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    val rvGroupLine1: RecyclerView = view.findViewById(R.id.rv_group_line_1)
}