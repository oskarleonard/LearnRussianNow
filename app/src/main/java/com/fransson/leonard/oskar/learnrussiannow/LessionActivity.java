package com.fransson.leonard.oskar.learnrussiannow;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.Window;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.fransson.leonard.oskar.learnrussiannow.MyClasses.Lession;
import com.fransson.leonard.oskar.learnrussiannow.util.MyDebugger;


public class LessionActivity extends AppCompatActivity {

    private WebView wvLession;
    private int textZoom = 120;
    private Lession curentLession;

    public static final String BUNDLE_LESSION = "bundeLession";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lession);

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            if (MyDebugger.ON) {
                Log.i("LessionActivity", "Trying to get Parcable");
            }

            curentLession = extras.getParcelable(BUNDLE_LESSION);
        }


        getSupportActionBar().setDisplayShowTitleEnabled(false);
        setUpWebView();
    }

    private void setUpWebView() {
        wvLession = (WebView) findViewById(R.id.wvLession);
        wvLession.loadUrl(curentLession.getHtmlPath());
        wvLession.getSettings().setBuiltInZoomControls(true);
        wvLession.getSettings().setDisplayZoomControls(false);

        wvLession.getSettings().setJavaScriptEnabled(true);

        //Seems that .xml need padding othervise work stange
        wvLession.getSettings().setLoadWithOverviewMode(true);
        wvLession.getSettings().setUseWideViewPort(true);

        wvLession.getSettings().setTextZoom(textZoom);

        //Keep text from edge to edge, wihtout - text will add a \n to early (Nessessary on older devices)
        //wvLession.getSettings().setLayoutAlgorithm(WebSettings.LayoutAlgorithm.NORMAL);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if(wvLession != null) {
            wvLession.removeAllViews();
            wvLession.destroy();
        }
        wvLession = null;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_lession, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        switch (id){
            case R.id.zoomIn:
                textZoom+=15;
                wvLession.getSettings().setTextZoom(textZoom);
                return true;
            case R.id.zoomOut:
                textZoom = (textZoom > 50) ? textZoom-15 :textZoom;
                wvLession.getSettings().setTextZoom(textZoom);
                return true;
            case R.id.testActivity:
                Intent intent = new Intent(this, LessionTest.class);
                Bundle mBundle = new Bundle();
                mBundle.putParcelable(BUNDLE_LESSION, curentLession);
                intent.putExtras(mBundle);
                startActivity(intent);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
