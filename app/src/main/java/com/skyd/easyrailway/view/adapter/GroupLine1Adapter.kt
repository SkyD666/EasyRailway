package com.skyd.easyrailway.view.adapter

import android.app.Activity
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.skyd.easyrailway.App
import com.skyd.easyrailway.R
import com.skyd.easyrailway.bean.LineBean
import com.skyd.easyrailway.util.Line1ViewHolder
import com.skyd.easyrailway.util.Util.showToast

class GroupLine1Adapter(
    val activity: Activity,
    private val dataList: List<LineBean>
) : BaseRvAdapter(dataList) {

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        super.onBindViewHolder(holder, position)
        val item = dataList[position]

        when (holder) {
            is Line1ViewHolder -> {
                holder.tvLine1Start.text = item.start
                holder.tvLine1StartTime.text = item.startTime
                holder.tvLine1Destination.text = item.destination
                holder.tvLine1DestinationTime.text = item.destinationTime
                holder.tvLine1Duration.text = item.duration
                holder.tvLine1Cost.text = item.cost
                holder.tvLine1TrainNumber.text = item.trainNumber
//                holder.itemView.setOnClickListener {
//                    process(activity, "${item.actionUrl}&${item.link}")
//                }
            }
            else -> {
                holder.itemView.visibility = View.GONE
                (App.context.resources.getString(R.string.unknown_view_holder) + position).showToast()
            }
        }
    }
}