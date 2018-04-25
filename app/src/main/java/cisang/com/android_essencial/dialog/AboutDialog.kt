package cisang.com.android_essencial.dialog

import android.app.AlertDialog
import android.app.Dialog
import android.content.DialogInterface
import android.content.pm.PackageManager
import android.os.Bundle
import android.support.v4.app.DialogFragment
import android.text.Html
import android.text.SpannableStringBuilder
import cisang.com.android_essencial.R
import android.os.Build
import android.support.v4.app.FragmentManager
import android.text.Spanned
import android.text.method.LinkMovementMethod
import android.view.LayoutInflater
import android.widget.TextView
import kotlinx.android.synthetic.main.nav_drawer_header.view.*


/**
 * Created by WCisang on 25/04/2018.
 */
class AboutDialog : DialogFragment(){

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val aboutBody = SpannableStringBuilder()
        val versionName = getAppVersionName()

        val html = fromHtml(getString(R.string.about_dialog_text, versionName))
        aboutBody.append(html)

        val inflater = LayoutInflater.from(activity)
        val view = inflater.inflate(R.layout.dialog_about, null) as TextView
        view.text = aboutBody
        view.movementMethod = LinkMovementMethod()

        return AlertDialog.Builder(activity)
                .setTitle(R.string.about_dialog_title)
                .setView(view)
                .setPositiveButton(R.string.ok, {
                    dialog, which -> dialog.dismiss()
                }).create()
    }

    private fun getAppVersionName(): String {
        val pm = activity?.packageManager
        val packageName = activity?.packageName
        var versionName : String
        try {
            val info  = pm?.getPackageInfo(packageName, 0)
            versionName = info?.versionName!!
        }catch (ex: PackageManager.NameNotFoundException){
            versionName = "N/A"
        }
        return versionName
    }

    fun fromHtml(html: String): Spanned {
        return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            Html.fromHtml(html, Html.FROM_HTML_MODE_LEGACY)
        } else {
            Html.fromHtml(html)
        }
    }

    companion object {
        fun showAbout(fm: FragmentManager){
            val ft = fm.beginTransaction()
            val prev = fm.findFragmentByTag("about_dialog")
            if (prev != null){
                ft.remove(prev)
            }
            ft.addToBackStack(null)
            AboutDialog().show(ft, "about_dialog")
        }
    }
}