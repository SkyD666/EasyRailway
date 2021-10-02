package com.skyd.easyrailway.bean

import java.io.Serializable

interface BaseBean : Serializable {
    var type: String
    var actionUrl: String
}