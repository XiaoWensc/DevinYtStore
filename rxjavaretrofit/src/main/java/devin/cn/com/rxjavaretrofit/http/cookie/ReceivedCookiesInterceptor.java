package devin.cn.com.rxjavaretrofit.http.cookie;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import java.io.IOException;
import java.util.HashSet;

import devin.cn.com.rxjavaretrofit.BaseApp;
import okhttp3.Interceptor;
import okhttp3.Response;
import rx.Observable;
import rx.functions.Action1;
import rx.functions.Func1;

/**
 * Created by zengxiaowen on 2018/2/5.
 */

public class ReceivedCookiesInterceptor implements Interceptor {

    @Override
    public Response intercept(Chain chain) throws IOException {
        Response originalResponse = chain.proceed(chain.request());

        if (BaseApp.cookies.isEmpty()) {
            if (!originalResponse.headers("Set-Cookie").isEmpty()) {
                for (String header : originalResponse.headers("Set-Cookie")) {
                        BaseApp.cookies.add(header);
                    Log.v("OkHttp", "Received Header: " + header);
                }
            }
        }
        return originalResponse;
    }
}
