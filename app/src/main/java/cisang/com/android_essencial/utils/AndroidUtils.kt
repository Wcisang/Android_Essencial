package cisang.com.android_essencial.utils

import android.annotation.TargetApi
import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkInfo
import android.os.Build

/**
 * Created by WCisang on 10/05/2018.
 */
object AndroidUtils {


    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    fun isNetworkAvailable(context: Context): Boolean {
        val connectivity = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val networks = connectivity.allNetworks
        return  networks
                .map { connectivity.getNetworkInfo(it) }
                .any {it.state == NetworkInfo.State.CONNECTED}
    }
}