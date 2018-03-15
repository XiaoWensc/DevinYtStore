package zxw.cn.com.rrxjava.utils;

import android.webkit.CookieManager;
import android.webkit.CookieSyncManager;

import zxw.cn.com.rrxjava.MyApplication;

/**
 * Created by zengxiaowen on 2018/1/29.
 */

public class ToolCookie {

    public static void removeSessionCookie() {
        CookieSyncManager.createInstance(MyApplication.gainContext());
        CookieManager cookieManager = CookieManager.getInstance();
        cookieManager.removeAllCookie();
    }
}
