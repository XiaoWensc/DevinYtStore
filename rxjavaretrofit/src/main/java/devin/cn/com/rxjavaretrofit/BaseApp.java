package devin.cn.com.rxjavaretrofit;

import android.app.Application;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import devin.cn.com.rxjavaretrofit.Api.BaseApi;
import devin.cn.com.rxjavaretrofit.Api.HttpManagerApi;
import retrofit2.Retrofit;
import rx.Observable;

/**
 * 全局app
 * Created by zengxiaowen on 2018/1/19.
 */

public class BaseApp {
    private static Application application;
    private static boolean debug;
    public static List<String> cookies;

    public static void init(Application app){
        cookies = new ArrayList<>();
        setApplication(app);
        setDebug(true);
    }

    public static void init(Application app,boolean debug){
        cookies = new ArrayList<>();
        setApplication(app);
        setDebug(debug);
    }

    public static Application getApplication() {
        return application;
    }

    private static void setApplication(Application application) {
        BaseApp.application = application;
    }

    public static boolean isDebug() {
        return debug;
    }

    public static void setDebug(boolean debug) {
        BaseApp.debug = debug;
    }
}
