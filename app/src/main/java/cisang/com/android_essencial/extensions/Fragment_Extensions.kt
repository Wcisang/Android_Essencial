package cisang.com.android_essencial.extensions

import android.support.annotation.StringRes
import android.support.v4.app.Fragment
import android.widget.Toast

/**
 * Created by WCisang on 25/04/2018.
 */
fun Fragment.toast(message: CharSequence, length: Int = Toast.LENGTH_SHORT) = Toast.makeText(activity ,message,length).show()

fun Fragment.toast(@StringRes message: Int, length: Int = Toast.LENGTH_SHORT) = Toast.makeText(activity,message,length).show()