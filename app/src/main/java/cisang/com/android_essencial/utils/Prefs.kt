package cisang.com.android_essencial.utils

import android.content.SharedPreferences
import cisang.com.android_essencial.CarrosApplication

/**
 * Created by WCisang on 14/05/2018.
 */
object Prefs {
    val PREF_ID = "carros"

    private fun prefs(): SharedPreferences {
        val context = CarrosApplication.getInstance().applicationContext
        return context.getSharedPreferences(PREF_ID, 0)
    }

    fun setInt(flag: String, valor: Int){
        val pref = prefs()
        val editor = pref.edit()
        editor.putInt(flag, valor)
        editor.apply()
    }

    fun getInt (flag: String) : Int {
        val pref = prefs()
        val i = pref.getInt(flag, 0)
        return i
    }

    fun setString(flag: String, valor: String){
        val pref = prefs()
        val editor = pref.edit()
        editor.putString(flag, valor)
        editor.apply()
    }

    fun getString(flag: String) : String {
        val pref = prefs()
        val i = pref.getString(flag, "")
        return i
    }

    var tabIdx : Int
        get() = getInt("tabIdx")
        set(value) = setInt("tabIdx", value)
}