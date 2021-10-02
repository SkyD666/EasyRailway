package com.skyd.easyrailway.bean

class PlanBean(
    override var type: String,
    override var actionUrl: String,
    var title: String,
    var lines: List<LineBean>
) : BaseBean