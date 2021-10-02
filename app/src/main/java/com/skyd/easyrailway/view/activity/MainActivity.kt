package com.skyd.easyrailway.view.activity

import android.animation.ObjectAnimator
import android.annotation.SuppressLint
import android.app.Activity
import android.app.DatePickerDialog
import android.graphics.Rect
import android.os.Bundle
import android.util.Log
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import com.google.gson.Gson
import com.skyd.easyrailway.bean.LineBean
import com.skyd.easyrailway.bean.PlanBean
import com.skyd.easyrailway.config.Const
import com.skyd.easyrailway.databinding.ActivityMainBinding
import com.skyd.easyrailway.util.GroupLine1ViewHolder
import com.skyd.easyrailway.util.ViewHolderUtil.Companion.getViewHolder
import com.skyd.easyrailway.util.smartNotifyDataSetChanged
import com.skyd.easyrailway.view.adapter.GroupLine1Adapter
import com.skyd.easyrailway.viewmodel.MainViewModel
import java.util.*


class MainActivity : BaseActivity<ActivityMainBinding>() {
    private lateinit var viewModel: MainViewModel
    private lateinit var adapter: Vp2Adapter

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewModel = ViewModelProvider(this).get(MainViewModel::class.java)

        mBinding.apply {
            val c = Calendar.getInstance()
            tvMainActivityDate.text =
                "${c.get(Calendar.YEAR)}年${c.get(Calendar.MONTH) + 1}月${c.get(Calendar.DAY_OF_MONTH)}日"
            tvMainActivityDate.setOnClickListener {
                DatePickerDialog(
                    this@MainActivity, { view, year, month, dayOfMonth ->
                        tvMainActivityDate.text = "${year}年${month + 1}月${dayOfMonth}日"
                    },
                    c.get(Calendar.YEAR),
                    c.get(Calendar.MONTH),
                    c.get(Calendar.DAY_OF_MONTH)
                ).show()
            }

            btnMainActivitySearchRoute.setOnClickListener { viewModel.getMainList() }

            tvMainActivityStartStation.text = "淄博"
            tvMainActivityDestinationStation.text = "重庆北"
        }

        viewModel.mldMainList.observe(this, {
            Log.d("JSON", Gson().toJson(it.second))
            viewModel.mainList.apply {
                clear()
                if (this@MainActivity::adapter.isInitialized)
                    adapter.notifyDataSetChanged()
                addAll(it.second)
                if (this@MainActivity::adapter.isInitialized)
                    adapter.notifyDataSetChanged()
            }
            if (!this::adapter.isInitialized) {
                adapter = Vp2Adapter(this, viewModel.mainList)
                mBinding.vp2MainActivity.setAdapter(adapter)
            }
            val tabLayoutMediator = TabLayoutMediator(
                mBinding.tlMainActivity,
                mBinding.vp2MainActivity.getViewPager()
            ) { tab, position ->
                tab.text = viewModel.mainList[position].title
            }
            tabLayoutMediator.attach()
        })
    }

    override fun getBinding(): ActivityMainBinding = ActivityMainBinding.inflate(layoutInflater)

    class Vp2Adapter(
        private var activity: Activity,
        private var list: List<PlanBean>,
    ) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

        //必须四个参数都不是-1才生效
        var childPadding = Rect(-1, -1, -1, -1)

        override fun getItemViewType(position: Int): Int = Const.ViewHolderTypeInt.GROUP_LINE_1

        override fun onCreateViewHolder(
            parent: ViewGroup,
            viewType: Int
        ): RecyclerView.ViewHolder {
            val viewHolder = getViewHolder(parent, viewType)
            //vp2的item必须是MATCH_PARENT的
            val layoutParams = viewHolder.itemView.layoutParams
            layoutParams.width = ViewGroup.LayoutParams.MATCH_PARENT
            layoutParams.height = ViewGroup.LayoutParams.MATCH_PARENT
            viewHolder.itemView.layoutParams = layoutParams
            return viewHolder
        }

        override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
            val item = list[position]
            when (holder) {
                is GroupLine1ViewHolder -> {
                    val rvLayoutParams = holder.rvGroupLine1.layoutParams
                    rvLayoutParams.height = ViewGroup.LayoutParams.MATCH_PARENT
                    holder.rvGroupLine1.layoutManager = LinearLayoutManager(activity)
                    holder.rvGroupLine1.layoutParams = rvLayoutParams
                    holder.rvGroupLine1.isNestedScrollingEnabled = true
                    holder.rvGroupLine1.adapter = GroupLine1Adapter(activity, item.lines)
                }
            }
        }

        override fun getItemCount(): Int = list.size
    }
}