package devin.cn.com.rxjavaretrofit.http.cookie;

import android.util.Log;

import java.io.IOException;

import devin.cn.com.rxjavaretrofit.Api.BaseApi;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;
import rx.Observable;
import rx.functions.Action1;
import rx.functions.Func1;

/**
 * Created by zengxiaowen on 2018/2/5.
 */

public class SaveCookieInterceptor implements Interceptor {

    @Override
    public Response intercept(Chain chain) throws IOException {
        Request request = chain.request();
        Response response = chain.proceed(request);
        Log.d("httpss","response.code=" + response.code());

        if (!isTokenExpired(response)) {//根据和服务端的约定判断token过期
            Log.d("httpss","静默自动刷新Token,然后重新请求数据");
            //同步请求方式，获取最新的Token
//            String newSession = getNewToken();
            //使用新的Token，创建新的请求
            Request newRequest = chain.request()
                    .newBuilder()
                    .header("Cookie", "JSESSIONID=" + BaseApi._dynSessConf)
                    .build();
            //重新请求
            return chain.proceed(newRequest);
        }
        return response;
    }

    /**
     * 根据Response，判断Token是否失效
     *
     * @param response
     * @return
     */
    private boolean isTokenExpired(Response response) {
        if (response.code() == 404) {
            return true;
        }
        return false;
    }

}
