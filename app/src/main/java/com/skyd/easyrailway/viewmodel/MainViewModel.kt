package com.skyd.easyrailway.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.skyd.easyrailway.bean.LineBean
import com.skyd.easyrailway.bean.PlanBean
import com.skyd.easyrailway.config.Const.ViewHolderTypeString.Companion.GROUP_LINE_1
import com.skyd.easyrailway.config.Const.ViewHolderTypeString.Companion.LINE_1
import com.skyd.easyrailway.net.util.GetDataEnum
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


class MainViewModel : ViewModel() {
    var mainList: MutableList<PlanBean> = ArrayList()
    var mldMainList: MutableLiveData<Pair<GetDataEnum, MutableList<PlanBean>>> = MutableLiveData()

    fun getMainList() {
        viewModelScope.launch(Dispatchers.IO) {
            val result = ArrayList<PlanBean>()
            result.add(
                PlanBean(
                    GROUP_LINE_1, "", "线路1",
                    listOf(
                        LineBean(
                            LINE_1, "", "淄博", "大明湖",
                            "05:22", "06:26",
                            "1小时4分", "16.5", "K286"
                        ), LineBean(
                            LINE_1, "", "济南西", "合肥南",
                            "08:29", "10:55",
                            "2小时26分", "281", "G45"
                        ), LineBean(
                            LINE_1, "", "合肥南", "重庆北",
                            "12:02", "20:50",
                            "8小时48分", "411.5", "D2263"
                        )
                    )
                )
            )
            result.add(
                PlanBean(
                    GROUP_LINE_1, "", "线路2",
                    listOf(
                        LineBean(
                            LINE_1, "", "淄博", "石家庄",
                            "15:06", "18:37",
                            "3小时31分", "150", "D1612"
                        ), LineBean(
                            LINE_1, "", "石家庄", "重庆北",
                            "20:19", "12:13",
                            "15小时54分", "201", "Z3"
                        )
                    )
                )
            )
            mldMainList.postValue(
                Pair(GetDataEnum.REFRESH, result)
            )
        }
    }

    companion object {
        const val TAG = "MainViewModel"
    }
}