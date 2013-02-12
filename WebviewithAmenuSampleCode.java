package com.example.baynaught;

import android.os.Bundle;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.view.Menu;
import android.view.MenuItem;
import android.view.Window;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

public class MainActivity extends Activity {

    
	private WebView webView;
	private ProgressDialog mProgress;
    
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // no need to use title bar
  requestWindowFeature(Window.FEATURE_NO_TITLE);

        setContentView(R.layout.activity_main);
        webView = (WebView) findViewById(R.id.webView1);
        webView.setWebViewClient(new WebViewClient()); //to handle redirections
        WebSettings webSettings = webView.getSettings();
        webSettings.setJavaScriptEnabled(true); // set Javascript
        webSettings.setBuiltInZoomControls(true); //To use zoom
		
     // the init state of progress dialog
                mProgress = ProgressDialog.show(this, "Buckle Up!!", "It's just about time...");

     //add a webviewClient for WEbView ,which handles loading data from web
                
                webView.setWebViewClient(new WebViewClient()
                {
                	//load url
                	public boolean shouldOverrideUrlLoading(WebView view,String url)
                	{
                		view.loadUrl(url);
                		return true;
                	}
               
//on error while loading
                	public void onReceivedError(WebView view, int errorCode,
                            String description, String failingUrl) {
                        Toast.makeText(getApplicationContext(), "Failed to Connect \n" + description , Toast.LENGTH_LONG).show();
                    }
                	
                	//when finish loading page
                	
                	public void onPageFinished(WebView view,String url)
                	{
                		if(mProgress.isShowing())
                		{
                			mProgress.dismiss();
                		}
                	}
                });
                
                webView.loadUrl("http://59.163.204.32:82");//set url for webView to load 
        
        
        
       
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
        switch (item.getItemId()) {
        case R.id.menu_settings:
        	Context context = getApplicationContext();
        	CharSequence text = "Not Available At This Time!" ;
        	int duration = Toast.LENGTH_SHORT;

        	Toast toast = Toast.makeText(context, text, duration);
        	toast.show();
            return true;
                default:
            return super.onOptionsItemSelected(item);
        }
    }
    
}
