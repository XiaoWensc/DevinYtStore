package zxw.cn.com.rrxjava.ui.webview;

import android.support.v7.widget.Toolbar;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.ViewGroup;
import devin.cn.com.rxjavaretrofit.mvp.BaseActivity;
import devin.cn.com.rxjavaretrofit.mvp.BasePresenter;
import zxw.cn.com.rrxjava.R;
import zxw.cn.com.rrxjava.utils.ToolCookie;
import zxw.cn.com.rrxjava.widget.webview.UIWebView;

/**
 * Created by zengxiaowen on 2018/1/29.
 */

public class BaseWebViewActivity extends BaseActivity {
    private UIWebView uiWebView;
    @Override
    public void refresh() {
    }

    @Override
    protected BasePresenter initPresent() {
        return null;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_base_webview;
    }

    @Override
    protected void findView() {
        Toolbar toolbar = findViewById(R.id.toolbar);
        uiWebView =  findViewById(R.id.uiWebView);
        //设置导航图标要在setSupportActionBar方法之后
        setSupportActionBar(toolbar);
        toolbar.setNavigationOnClickListener(view -> {
            if (uiWebView.canGoBack()) {
                uiWebView.goBack(); // goBack()表示返回WebView的上一页面
            } else {
                finish();
            }
        });
        toolbar.setOnMenuItemClickListener(item -> {
            finish();
            return true;
        });
        uiWebView.setCurrentTitle(toolbar);

        String link = getIntent().getStringExtra("link");
//        syncCookie(MApplication.gainContext(),link);
        String postDate = getIntent().getStringExtra("postDate");
        uiWebView.isShowProgress(true);
        if (postDate==null||postDate.isEmpty()){
            uiWebView.loadUrl(link);
        }else {
            uiWebView.postUrl(link,postDate.getBytes());
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_webview, menu);
        return true;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (uiWebView != null) {
            uiWebView.loadDataWithBaseURL(null, "", "text/html", "utf-8", null);
            uiWebView.clearHistory();
            ((ViewGroup) uiWebView.getParent()).removeView(uiWebView);
            uiWebView.destroy();
            uiWebView = null;
        }
        ToolCookie.removeSessionCookie();
    }

    // 设置回退
    // 覆盖Activity类的onKeyDown(int keyCoder,KeyEvent event)方法
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if ((keyCode == KeyEvent.KEYCODE_BACK) && uiWebView.canGoBack()) {
            uiWebView.goBack(); // goBack()表示返回WebView的上一页面
        } else {
            this.finish();
        }
        return false;
    }
}
