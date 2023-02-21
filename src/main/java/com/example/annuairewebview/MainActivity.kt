package com.example.annuairewebview

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.webkit.JavascriptInterface
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.Button
import android.widget.ProgressBar
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import com.android.volley.RequestQueue
import com.example.annuairewebview.databinding.ActivityMainBinding

var loadingFinished = true
class MainActivity : AppCompatActivity() {
    public var btnverifyCaptcha: Button? = null
    var SITE_KEY = "6LeUTakhAAAAAD7qDsrMSySky7YaUf4mKGEa8QcY"
    var SECRET_KEY = "6LeUTakhAAAAALDSUM8RqA3Ownd6Rmih6CGZk2i0"
    var queue: RequestQueue? = null

    lateinit var progressBar: ProgressBar

    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        //setContentView(binding.root)
        setContentView(R.layout.activity_main)

        setSupportActionBar(binding.toolbar)

        /*val navController = findNavController(R.id.nav_host_fragment_content_main)
        appBarConfiguration = AppBarConfiguration(navController.graph)
        setupActionBarWithNavController(navController, appBarConfiguration)*/

        /*Ajout code */
        progressBar = findViewById<ProgressBar>(R.id.progressBar)

        val webView: WebView = findViewById<View>(R.id.webView) as WebView
        val button: Button= findViewById<View>(R.id.button) as Button
        val webSettings = webView.settings
        webSettings.javaScriptEnabled = true

        webView.addJavascriptInterface(myJavaScriptInterface(this), "JSBridge")


        val html = ("<html>  "
                +"<head>"
                +"   <meta charset='utf-8' />"
                +"  <meta name='viewport' content='width=device-width, initial-scale=1' />"
                +"  <script src='https://www.google.com/recaptcha/api.js?onload=onLoad&render=explicit' async defer></script>"
                +"  <title></title>"
                +"  <style>.grecaptcha-badge { visibility: hidden; }</style>"
                +"  <script type='text/javascript'>"
                +"      var onLoad = function() {"
                +"          grecaptcha.render('recaptcha',"
                +"           {"
                +"               sitekey: '6LeUTakhAAAAAD7qDsrMSySky7YaUf4mKGEa8QcY',"
                +"               size: 'invisible'"
                +"           }"
                +"          );"
                +"          grecaptcha.ready(function() {"
                +"              grecaptcha.execute().then(function(token) {"
                +"                  JSBridge.showMessageInNative(token);"
                +"              });"
                +"          });"
                +"      }"
                +"  </script>"
                +"</head>"
                +"<body>"
                +"  <div id='recaptcha'></div>"
                +"</body>"
                + " </html>");

        button.setOnClickListener { view ->
            progressBar.visibility = View.VISIBLE;
            Log.d("TOKEN", "sssss");
            webView.loadDataWithBaseURL("https://www.google.com", html, "text/html", "UTF-8", "https://www.google.com/recaptcha/admin/site/565010968/settings");
        }

        webView.setWebViewClient(object : WebViewClient() {
            override fun onPageFinished(view: WebView, url: String) {
                Log.d("SAM", "C'est pas vraiment fini "+ loadingFinished.toString())
                // do your stuff here
            }
        })
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        return when (item.itemId) {
            R.id.action_settings -> true
            else -> super.onOptionsItemSelected(item)
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.nav_host_fragment_content_main)
        return navController.navigateUp(appBarConfiguration)
                || super.onSupportNavigateUp()
    }

    fun onClick(view: View) {
    }

    fun hideProgressDialog() {
        Log.d("SAM", "hideProgressDialog")
        //progressBar.visibility = View.INVISIBLE

    }

    class myJavaScriptInterface(c: Context) {
        var mContext: Context = c

        @JavascriptInterface
        fun showMessageInNative(message:String){
            //Received message from webview in native, process data
            //progressBar.visibility = View.INVISIBLE
            Log.d("SAM", message)

            (this.mContext as MainActivity).hideProgressDialog()
            (this.mContext as MainActivity).progressBar.visibility = View.INVISIBLE


        }
    }
}