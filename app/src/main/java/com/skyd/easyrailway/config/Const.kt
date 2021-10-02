package com.skyd.easyrailway.config

interface Const {

    interface ActionUrl {
        companion object {
//            const val ARTICLE_READ = "/app/read"
        }
    }

    interface ViewHolderTypeInt {
        companion object {
            const val UNKNOWN = -1
            const val LINE_1 = 1
            const val GROUP_LINE_1 = 2
        }
    }

    interface ViewHolderTypeString {
        companion object {
            const val EMPTY_STRING = ""
            const val UNKNOWN = "unknown"
            const val LINE_1 = "line1"
            const val GROUP_LINE_1 = "groupLine1"
        }
    }

}