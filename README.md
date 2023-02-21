# RecaptchaV3Android

Description
An Android WebView developed in Kotlin . This project will help to allows you to implement the invisible Recaptcha library from Google.
RecaptchaV3Android supports both recaptcha v2 and recaptcha v3.

"reCAPTCHA is a free CAPTCHA service that protects websites from spam and abuse."

reCAPTCHA: https://www.google.com/recaptcha
Version: 1.0.3
License: MIT, see LICENSE

You can set your URL and SITEKEY from Google Recaptcha at java/com/example/annuairewebview/MainActivity.kt

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
                +"               sitekey: '$SITE_KEY',"
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
            webView.loadDataWithBaseURL("https://www.google.com", html, "text/html", "UTF-8", "https://www.google.com/recaptcha/admin/site/XXXXXXXXX/settings");
        }
