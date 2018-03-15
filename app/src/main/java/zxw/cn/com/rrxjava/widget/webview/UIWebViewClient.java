package zxw.cn.com.rrxjava.widget.webview;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.net.http.SslError;
import android.util.Log;
import android.webkit.CookieManager;
import android.webkit.SslErrorHandler;
import android.webkit.WebView;
import android.webkit.WebViewClient;

/**
 * 自定义的浏览器
 *
 * @author 曾晓文
 */
public class UIWebViewClient extends WebViewClient {

    /***跳转上下文**/
    private Context context;

    /***跳转界面对象**/
    private Class target;

    /***是否程序内部打开：true-->当前打开网页，false-->调用别的窗体打开**/
    private Boolean isInnerOpen = true;

    /**
     * 过滤目标字符串
     **/
    private String filter = "";

    private String TAG = UIWebViewClient.class.getSimpleName();

    /**
     * 当前加载网页URL
     ***/
    public static String currentURL = "";


    private UIWebView.IOnReceivedTitle mIOnReceivedTitle;

    /**
     * 默认内部打开链接
     */
    public UIWebViewClient(UIWebView.IOnReceivedTitle mIOnReceivedTitle) {
        this(mIOnReceivedTitle, true);
    }

    /**
     * @param isInnerOpen 是否内部打开
     */
    public UIWebViewClient(UIWebView.IOnReceivedTitle mIOnReceivedTitle, Boolean isInnerOpen) {
        this(mIOnReceivedTitle, null, null, isInnerOpen);
    }

    /**
     * @param context     上下文
     * @param target      处理新开网页URL界面
     * @param isInnerOpen 是否内部打开
     */
    public UIWebViewClient(UIWebView.IOnReceivedTitle mIOnReceivedTitle, Context context, Class target, Boolean isInnerOpen) {
        this(mIOnReceivedTitle, context, target, isInnerOpen, "");
    }

    /**
     * @param context     上下文
     * @param target      处理新开网页URL界面
     * @param isInnerOpen 是否内部打开
     * @param filter      过滤字符串
     */
    public UIWebViewClient(UIWebView.IOnReceivedTitle mIOnReceivedTitle, Context context, Class target, Boolean isInnerOpen, String filter) {
        this.context = context;
        this.target = target;
        this.isInnerOpen = isInnerOpen;
        this.filter = filter;
        this.mIOnReceivedTitle = mIOnReceivedTitle;
    }

    /***
     * 让浏览器支持访问https请求
     */
    @SuppressLint("NewApi")
    public void onReceivedSslError(WebView view, SslErrorHandler handler, SslError error) {
        handler.proceed();
        super.onReceivedSslError(view, handler, error);
    }

    /**
     * 控制网页的链接跳转打开方式（拦截URL）
     */
    public boolean shouldOverrideUrlLoading(final WebView view, String url) {
        currentURL = url;

        return true;
    }


    @Override
    public void onPageStarted(WebView view, String url, Bitmap favicon) {
        super.onPageStarted(view, url, favicon);
    }

    @Override
    public void onPageFinished(WebView view, String url) {
        //加载完毕后，开始加载图片
        //view.getSettings().setBlockNetworkImage(false);
        mIOnReceivedTitle.onReceivedTitle(view.getTitle());
        CookieManager cookieManager = CookieManager.getInstance();
        String newCookie = cookieManager.getCookie(url);

        Log.e(TAG, "onPageFinished--->url=" + url);
        super.onPageFinished(view, url);
    }

    @Override
    public void onReceivedError(WebView view, int errorCode, String description, String failingUrl) {
        super.onReceivedError(view, errorCode, description, failingUrl);
    }
}
