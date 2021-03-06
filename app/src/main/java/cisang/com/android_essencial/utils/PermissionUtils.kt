package cisang.com.android_essencial.utils

import android.app.Activity
import android.content.pm.PackageManager
import android.support.v4.app.ActivityCompat
import android.support.v4.content.ContextCompat

/**
 * Created by WCisang on 21/05/2018.
 */
object PermissionUtils {

    fun validate(activity:Activity, requestCode: Int, vararg permissions: String): Boolean {
        val list = ArrayList<String>()
        for (permission in permissions) {
            val ok = ContextCompat.checkSelfPermission(activity, permission) == PackageManager.PERMISSION_GRANTED
            if (!ok)
                list.add(permission)
        }

        if (list.isEmpty()){
            return true
        }
        val newPermissions = arrayOfNulls<String>(list.size)
        list.toArray(newPermissions)
        ActivityCompat.requestPermissions(activity, newPermissions, 1)
        return false
    }
}