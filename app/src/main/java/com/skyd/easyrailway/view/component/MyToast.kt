package com.skyd.easyrailway.view.component

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.widget.TextView
import android.widget.Toast
import com.skyd.easyrailway.R

object MyToast {
    fun makeText(
        context: Context,
        text: CharSequence, duration: Int = Toast.LENGTH_SHORT
    ): Toast {
        val toast = Toast(context)
        val view: View =
            (context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater).inflate(
                R.layout.toast_1,
                null
            )
        view.findViewById<TextView>(R.id.tv_toast_1).apply {
            this.text = text
        }
        toast.view = view
        toast.duration = duration
        return toast
    }
}