package com.skyd.easyrailway.bean

class LineBean(
    override var type: String,
    override var actionUrl: String,
    var start: String,
    var destination: String,
    var startTime: String,
    var destinationTime: String,
    var duration: String,
    var cost: String,
    var trainNumber: String
) : BaseBean