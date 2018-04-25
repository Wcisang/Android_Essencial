package cisang.com.android_essencial.activity

import android.graphics.Bitmap
import android.os.Bundle
import android.view.View
import android.webkit.WebView
import android.webkit.WebViewClient
import cisang.com.android_essencial.R
import cisang.com.android_essencial.dialog.AboutDialog
import cisang.com.android_essencial.extensions.setupToolbar
import kotlinx.android.synthetic.main.activity_site_livro.*

class SiteLivroActivity : BaseActivity() {

    private val URL_SOBRE = "https://www.google.com.br"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_site_livro)

        val actionBar = setupToolbar(R.id.toolbar)
        actionBar.setDisplayHomeAsUpEnabled(true)
        setWebViewClient(webview)
        webview.loadUrl(URL_SOBRE)

        swipeToRefresh.setOnRefreshListener {
            webview.reload()
        }
        AboutDialog.showAbout(supportFragmentManager)

    }

    private fun setWebViewClient(webview: WebView?) {
        webview?.webViewClient = object : WebViewClient(){
            override fun onPageStarted(view: WebView?, url: String?, favicon: Bitmap?) {
                super.onPageStarted(view, url, favicon)
                progress.visibility = View.VISIBLE
            }

            override fun onPageFinished(view: WebView?, url: String?) {
                super.onPageFinished(view, url)
                progress.visibility = View.INVISIBLE
                swipeToRefresh.isRefreshing = false
            }

        }
    }
}
