package cisang.com.android_essencial.activity

import android.graphics.Bitmap
import android.os.Bundle
import android.support.v4.widget.SwipeRefreshLayout
import android.view.View
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.ProgressBar
import cisang.com.android_essencial.R
import cisang.com.android_essencial.extensions.setupToolbar

class SiteLivroActivity : BaseActivity() {

    private val URL_SOBRE = "https://www.google.com.br"
    var webview: WebView? = null
    var progress: ProgressBar? = null
    var swipeToRefresh: SwipeRefreshLayout? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_site_livro)

        val actionBar = setupToolbar(R.id.toolbar)
        actionBar.setDisplayHomeAsUpEnabled(true)
        webview = findViewById<WebView>(R.id.webview)
        progress = findViewById<ProgressBar>(R.id.progress)
        setWebViewClient(webview)
        webview?.loadUrl(URL_SOBRE)

        swipeToRefresh = findViewById<SwipeRefreshLayout>(R.id.swipeToRefresh)
        swipeToRefresh?.setOnRefreshListener {
            webview?.reload()
        }

    }

    private fun setWebViewClient(webview: WebView?) {
        webview?.webViewClient = object : WebViewClient(){
            override fun onPageStarted(view: WebView?, url: String?, favicon: Bitmap?) {
                super.onPageStarted(view, url, favicon)
                progress?.visibility = View.VISIBLE
            }

            override fun onPageFinished(view: WebView?, url: String?) {
                super.onPageFinished(view, url)
                progress?.visibility = View.INVISIBLE
                swipeToRefresh?.isRefreshing = false
            }
        }
    }
}
