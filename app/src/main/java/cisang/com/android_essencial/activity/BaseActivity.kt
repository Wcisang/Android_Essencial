package cisang.com.android_essencial.activity

import android.annotation.SuppressLint
import android.content.Context
import android.support.v7.app.AppCompatActivity

/**
 * Created by willi on 14/02/2018.
 */
@SuppressLint("Registered")
open class BaseActivity : AppCompatActivity() {

    protected val context: Context get() = this
}