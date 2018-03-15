package devin.cn.com.rxjavaretrofit.Api;

import com.trello.rxlifecycle.components.support.RxAppCompatActivity;

import devin.cn.com.rxjavaretrofit.http.HttpManager;
import devin.cn.com.rxjavaretrofit.listener.HttpOnNextListener;
import devin.cn.com.rxjavaretrofit.listener.HttpOnNextSubListener;
import retrofit2.Retrofit;
import rx.Observable;

/**
 * 请求数据统一封装类
 * Created by zengxiaowen on 2018/1/19.
 */
public class HttpManagerApi extends BaseApi {
    private HttpManager manager;

    public HttpManagerApi(HttpOnNextListener onNextListener, RxAppCompatActivity appCompatActivity) {
        manager = new HttpManager(onNextListener, appCompatActivity);
    }

    public HttpManagerApi(HttpOnNextSubListener onNextSubListener, RxAppCompatActivity appCompatActivity) {
        manager = new HttpManager(onNextSubListener, appCompatActivity);
    }

    protected Retrofit getRetrofit() {
        return  manager.getReTrofit(getConnectionTime(), getBaseUrl());
    }


    protected void doHttpDeal(Observable observable) {
            manager.httpDeal(observable, this);
    }

    @Override
    public Observable getObservable(Retrofit retrofit) {
        return null;
    }
}
