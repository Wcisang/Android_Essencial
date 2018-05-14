package cisang.com.android_essencial.extensions

import android.widget.TextView

/**
 * Created by WCisang on 13/05/2018.
 */
var TextView.string : String
    get() = text.toString()
    set(value) { text = value}

fun TextView.isEmpty() = text.trim().isEmpty()
