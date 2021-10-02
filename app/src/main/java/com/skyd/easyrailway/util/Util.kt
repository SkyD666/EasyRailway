package com.skyd.easyrailway.util

import android.app.Activity
import android.content.*
import android.content.res.Resources
import android.util.TypedValue
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import android.widget.Toast
import androidx.annotation.ColorRes
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.skyd.easyrailway.App
import com.skyd.easyrailway.config.Const
import com.skyd.easyrailway.view.component.MyToast
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import java.io.*
import java.net.URLDecoder
import java.util.*


object Util {

    /**
     * 通过id获取颜色
     */
    fun Context.getResColor(@ColorRes id: Int) = ContextCompat.getColor(this, id)

    fun EditText.showKeyboard() {
        isFocusable = true
        isFocusableInTouchMode = true
        requestFocus()
        val inputManager =
            App.context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        inputManager.showSoftInput(this, 0)
    }

    fun EditText.hideKeyboard() {
        val inputManager =
            App.context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        inputManager.hideSoftInputFromWindow(this.windowToken, 0)
    }

    val Float.dp: Float
        get() = TypedValue.applyDimension(
            TypedValue.COMPLEX_UNIT_DIP,
            this,
            Resources.getSystem().displayMetrics
        )

    val Int.dp: Int
        get() = TypedValue.applyDimension(
            TypedValue.COMPLEX_UNIT_DIP,
            this.toFloat(),
            Resources.getSystem().displayMetrics
        ).toInt()

    fun CharSequence.showToast(duration: Int = Toast.LENGTH_SHORT) {
        MyToast.makeText(App.context, this, duration).show()
    }

    fun CharSequence.showToastOnIOThread(duration: Int = Toast.LENGTH_SHORT) {
        GlobalScope.launch(Dispatchers.Main) {
            this@showToastOnIOThread.showToast(duration)
        }
    }

    fun process(fragment: Fragment, actionUrl: String?) {
        val activity = fragment.activity
        if (activity != null)
            process(activity, actionUrl)
    }

    fun process(activity: Activity, actionUrl: String?) {
        actionUrl ?: return
        val decodeUrl = URLDecoder.decode(actionUrl, "UTF-8")
        when {
//            decodeUrl.startsWith(Const.ActionUrl.ARTICLE_READ) -> {
//                val link = decodeUrl.replace(Const.ActionUrl.ARTICLE_READ + "&", "")
//                activity.startActivity(
//                    Intent(activity, ArticleActivity::class.java)
//                        .putExtra("url", link)
//                )
//            }
            else -> {
                "未知跳转".showToast()
            }
        }
    }
}