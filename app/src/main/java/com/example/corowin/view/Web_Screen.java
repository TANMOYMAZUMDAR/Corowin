package com.example.corowin.view;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.os.Build;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.Window;
import android.view.WindowManager;
import android.webkit.WebResourceRequest;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.example.corowin.R;
import com.example.corowin.databinding.ActivityWebScreenBinding;

public class Web_Screen extends AppCompatActivity {

    ActivityWebScreenBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityWebScreenBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        getSupportActionBar().hide();

        CusstomWebViewClient client = new CusstomWebViewClient(this);
        binding.webview.setWebViewClient(client);
        binding.webview.getSettings().setJavaScriptEnabled(true);
        binding.webview.getSettings().setDomStorageEnabled(true);


        binding.webview.getSettings().setJavaScriptCanOpenWindowsAutomatically(true);
        binding.webview.setWebViewClient(new WebViewClient());

        binding.webview.getSettings().setSaveFormData(true);
        binding.webview.getSettings().setSupportZoom(false);
        binding.webview.getSettings().setCacheMode(WebSettings.LOAD_NO_CACHE);
        binding.webview.getSettings().setPluginState(WebSettings.PluginState.ON);


        binding.webview.loadUrl("https://www.cowin.gov.in/");

        changeStatusBarColor();


    }

    @Override
    public void onBackPressed() {
        if (binding.webview.canGoBack()) {
            binding.webview.goBack();
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent keyEvent) {
        if (keyCode == keyEvent.KEYCODE_BACK && binding.webview.canGoBack()) {
            binding.webview.goBack();
            return true;
        }
        return super.onKeyDown(keyCode, keyEvent);
    }

    private void changeStatusBarColor() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
//            window.setStatusBarColor(Color.TRANSPARENT);
            window.setStatusBarColor(getResources().getColor(R.color.purple_200));
        }
    }
}


class CusstomWebViewClient extends WebViewClient {
    private Activity activity;
    public CusstomWebViewClient(Activity activity)
    {
        this.activity=activity;
    }

    //API Level less than 24
    @Override
    public boolean shouldOverrideUrlLoading(WebView webView, String url)
    {
        return false;  //all the links would be opened in our app and not external website.
    }

    //Api level greater than 24
    @Override
    public boolean  shouldOverrideUrlLoading(WebView webView, WebResourceRequest request)
    {
        return  false;
    }

}
