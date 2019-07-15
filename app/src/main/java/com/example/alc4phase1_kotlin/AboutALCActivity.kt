package com.example.alc4phase1_kotlin

import android.content.Context
import android.content.DialogInterface
import android.graphics.Bitmap
import android.net.ConnectivityManager
import android.net.http.SslError
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.app.AlertDialog
import android.view.View
import android.view.WindowManager
import android.webkit.*
import android.widget.ProgressBar
import android.widget.Toast

class AboutALCActivity : AppCompatActivity() {
    private lateinit var mWebView: WebView
    private lateinit var mLoadingProcess: ProgressBar
    private val url = "https://andela.com/alc/"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN)
        setContentView(R.layout.activity_about_alc)

            mWebView = findViewById(R.id.abt_alc_webview)
            mLoadingProcess = findViewById(R.id.pb_Loading)
            mWebView.setWebViewClient(object : WebViewClient() {

                override fun onPageStarted(view: WebView?, url: String?, favicon: Bitmap?) {
                    mLoadingProcess.setVisibility(View.VISIBLE)
                }

                override fun shouldOverrideUrlLoading(view: WebView, url: String): Boolean {
                    view.loadUrl(url)
                    return false
                }

                override fun onReceivedSslError(view: WebView, handler: SslErrorHandler, error: SslError) {
                    // for SSLErrorHandler
                    val builder = AlertDialog.Builder(this@AboutALCActivity)
                    builder.setMessage(R.string.notification_error_ssl_cert_invalid)
                    builder.setPositiveButton(
                        "continue"
                    ) { dialog, which -> handler.proceed() }
                    builder.setNegativeButton("cancel") { dialog, which ->
                        handler.cancel()
                        finish()
                    }
                    val dialog = builder.create()
                    dialog.show()
                }

                override fun onReceivedError(view: WebView, request: WebResourceRequest, error: WebResourceError) {
                    Toast.makeText(this@AboutALCActivity, "Sorry :(", Toast.LENGTH_SHORT).show()
                }

                override fun onPageFinished(view: WebView, url: String) {
                    mLoadingProcess.setVisibility(View.INVISIBLE)
                    Toast.makeText(this@AboutALCActivity,"Finished loading :)",Toast.LENGTH_LONG).show()
                }
            })

            mWebView.getSettings().javaScriptEnabled = true
            mWebView.setScrollBarStyle(View.SCROLLBARS_INSIDE_OVERLAY)
            mWebView.getSettings().builtInZoomControls = false

            if (!isNetworkConnected()) {
                mWebView.loadData(
                    "<html><body style='text-align:center;'><h1>Connection Error ...</h1><h2>Check Your Connection ... </h2></body></html>",
                    "text/html",
                    null
                )
            } else {
                Toast.makeText(this@AboutALCActivity, "Loading...please wait", Toast.LENGTH_SHORT).show()
                mWebView.loadUrl(url)
            }
    }

    private fun isNetworkConnected(): Boolean {
        val cm = getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val ni = cm.activeNetworkInfo
        return if (ni == null) false else true
    }

    override fun onBackPressed() {
        if (mWebView.canGoBack()) {
            mWebView.goBack()
        } else {
            super.onBackPressed()
        }
    }
}
