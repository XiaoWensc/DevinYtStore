package zxw.cn.com.rrxjava.widget.webview;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.res.TypedArray;
import android.net.Uri;
import android.os.Build;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.View;
import android.webkit.CookieManager;
import android.webkit.CookieSyncManager;
import android.webkit.JavascriptInterface;
import android.webkit.WebSettings;
import android.webkit.WebSettings.RenderPriority;
import android.webkit.WebView;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.util.List;

import devin.cn.com.rxjavaretrofit.Api.BaseApi;
import devin.cn.com.rxjavaretrofit.Api.HttpManagerApi;
import devin.cn.com.rxjavaretrofit.utils.AppUtil;
import okhttp3.Cookie;
import zxw.cn.com.rrxjava.R;


/**
 * 自定义浏览器
 * @author 曾晓文
 * @version 1.0
 */
public class UIWebView extends WebView {

    private OnScrollChangedCallback mOnScrollChangedCallback;
	private ProgressBar progressbar;
	
	private boolean isShowProgress = true;

    /***是否程序内部打开：true-->当前打开网页，false-->调用别的窗体打开**/
    private Boolean isInnerOpen = true;

	private Toolbar textView;
	
	private IOnReceivedTitle mIOnReceivedTitle = new IOnReceivedTitle() {
        @Override
        public void onReceivedTitle(String title) {
            if (textView!=null) textView.setTitle(title);
        }
    };
	
    /**
     * Construct a new WebView with a Context object.
     * @param context A Context object used to access application assets.
     */
    public UIWebView(Context context) {
        this(context, null);
    }

    /**
     * Construct a new WebView with layout parameters.
     * @param context A Context object used to access application assets.
     * @param attrs An AttributeSet passed to our parent.
     */
    public UIWebView(Context context, AttributeSet attrs) {
        this(context, attrs, android.R.attr.webViewStyle,true);
    }

    /**
     * Construct a new WebView with layout parameters and a default style.
     * @param context A Context object used to access application assets.
     * @param attrs An AttributeSet passed to our parent.
     * @param defStyle The default style resource ID.
     */
    public UIWebView(Context context, AttributeSet attrs, int defStyle, boolean isShowProgress) {
        super(context, attrs, defStyle);

        //获取自定义属性和默认值
        TypedArray mTypedArray = context.obtainStyledAttributes(attrs, R.styleable.UIWebView);
        isInnerOpen = mTypedArray.getBoolean(R.styleable.UIWebView_innerOpen,true);
        mTypedArray.recycle();
        //添加加载进度条
        if(isShowProgress){
        	 progressbar = new ProgressBar(context, null, android.R.attr.progressBarStyleHorizontal);
             progressbar.setLayoutParams(new LayoutParams(LayoutParams.FILL_PARENT, 5, 0, 0));
             addView(progressbar);
        }
        syncCookie(context, Uri.parse(BaseApi.getBaseUrl()).getHost());
        setWebChromeClient(new UIWebChromeClient(mIOnReceivedTitle,progressbar));
        setWebViewClient(new UIWebViewClient(mIOnReceivedTitle,isInnerOpen));
        //配置webView设置
        settingWebView();
        //设置滚动条样式
        setScrollBarStyle(View.SCROLLBARS_INSIDE_OVERLAY);
        addJavascriptInterface(this,"itbt");
    }

    /**
     * Sync Cookie
     */
    private void syncCookie(Context context, String url){
        try{
            CookieSyncManager.createInstance(context);
            CookieManager cookieManager = CookieManager.getInstance();
            cookieManager.setAcceptCookie(true);
            cookieManager.removeSessionCookie();// 移除
            cookieManager.removeAllCookie();
//            getCookieText(url,cookieManager);
            if (Build.VERSION.SDK_INT < 21) {
                CookieSyncManager.getInstance().sync();
            } else {
                CookieManager.getInstance().flush();
            }
        }catch(Exception e){
        }
    }


    /**
     * 获取标准 Cookie
     */
//    private void getCookieText(String url, CookieManager cookieManager) {
//        PersistentCookieStore myCookieStore = new PersistentCookieStore(MApplication.gainContext());
//        List<Cookie> cookies = myCookieStore.getCookies();
//        Utils.setCookies(cookies);
//        for (Cookie cookie : cookies) {
//            ToolLog.i(cookie.getName() + " = " + cookie.toString());
//            StringBuffer sb = new StringBuffer();
//            String cookieName = cookie.getName();
//            String cookieValue = cookie.getValue();
//            if (!TextUtils.isEmpty(cookieName)&& !TextUtils.isEmpty(cookieValue)) {
//                sb.append(String.format("%s=%s", cookieName,cookieValue));
//                sb.append(String.format(";domain=%s", cookie.getDomain()));
//                sb.append(String.format(";path=%s",cookie.getPath()));
//            }
//            cookieManager.setCookie(url,sb.toString());
//        }
//    }


    @JavascriptInterface
    public void linkUrl(String url) {
        Uri uri1 = Uri.parse(url);
        Intent intent = new Intent(Intent.ACTION_VIEW, uri1);
        getContext().startActivity(intent);
    }

    @Override
    protected void onScrollChanged(int l, int t, int oldl, int oldt) {
    	if(null != progressbar)
    	{
            LayoutParams lp = (LayoutParams) progressbar.getLayoutParams();
            lp.x = l;
            lp.y = t;
            progressbar.setLayoutParams(lp);
    	}
        super.onScrollChanged(l, t, oldl, oldt);

        if (mOnScrollChangedCallback != null) {
            mOnScrollChangedCallback.onScroll(l, t);
        }
    }
    

    @SuppressLint({ "NewApi", "SetJavaScriptEnabled" })
	private void settingWebView(){
    	WebSettings settings = getSettings();
    	//设置是否支持Javascript
    	settings.setJavaScriptEnabled(true);
    	//是否支持缩放
    	settings.setSupportZoom(true);
    	//settings.setBuiltInZoomControls(true);
//        if (Build.VERSION.SDK_INT >= 3.0)
//        	settings.setDisplayZoomControls(false);
    	//是否显示缩放按钮
    	//settings.setDisplayZoomControls(false);
    	//提高渲染优先级
    	settings.setRenderPriority(RenderPriority.HIGH);
    	//设置页面自适应手机屏幕
    	settings.setUseWideViewPort(true);
    	//WebView自适应屏幕大小
    	settings.setLoadWithOverviewMode(true);
        // 设置可以访问文件
        settings.setAllowFileAccess(true);
//        settings.setUserAgentString("android");
        settings.setAppCacheEnabled(true);
        settings.setDomStorageEnabled(true);
        settings.setDatabaseEnabled(true);
        if (AppUtil.isNetworkAvailable(getContext())) { //判断是否联网
            settings.setCacheMode(WebSettings.LOAD_DEFAULT); //默认的缓存使用模式
        } else {
            settings.setCacheMode(WebSettings.LOAD_CACHE_ONLY); //不从网络加载数据，只从缓存加载数据。
        }
    	//加载url前，设置不加载图片WebViewClient-->onPageFinished加载图片
    	//settings.setBlockNetworkImage(true);
    	//设置网页编码
    	settings.setDefaultTextEncodingName("UTF-8");
        /**
         * MIXED_CONTENT_ALWAYS_ALLOW：允许从任何来源加载内容，即使起源是不安全的；
         * MIXED_CONTENT_NEVER_ALLOW：不允许Https加载Http的内容，即不允许从安全的起源去加载一个不安全的资源；
         * MIXED_CONTENT_COMPATIBILITY_MODE：当涉及到混合式内容时，WebView 会尝试去兼容最新Web浏览器的风格。
         **/
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            settings.setMixedContentMode(WebSettings.MIXED_CONTENT_ALWAYS_ALLOW);
        }
    }
    
    /**
     * 设置当前网页Title
     * @param title 网页Title
     */
    public void setCurrentTitle(Toolbar title){
    	textView = title;
    }
    
    /**
     * 获取当前加载网页的标题
     * @return 网页<title>网页标题</title>
     */
    public static String getCurrentTitle(){
    	return "";
    }
    
    /**
     * 获取当前加载网页的URL
     * @return 网页URL
     */
    public static String getCurrentURL(){
    	return UIWebViewClient.currentURL;
    }
    
    /**
     * 获取网页标题
     *
     */
    public interface IOnReceivedTitle{
    	public void onReceivedTitle(String title);
    }
    
    
    /**
     * 设置是否显示进度条
     * @param isShow 是否显示
     */
    public void isShowProgress(boolean isShow){
    	this.isShowProgress = isShow;
    }
    
    /**
     * 获取网页创建的progressbar
     * @return
     */
    public ProgressBar getProgressBar(){
    	return this.progressbar;
    }


    public void setOnScrollChangedCallback(
            final OnScrollChangedCallback onScrollChangedCallback) {
        mOnScrollChangedCallback = onScrollChangedCallback;
    }

    /**
     * Impliment in the activity/fragment/view that you want to listen to the webview
     */
    public static interface OnScrollChangedCallback {
        public void onScroll(int dx, int dy);
    }
}
