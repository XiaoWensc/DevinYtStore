package devin.cn.com.rxjavaretrofit.mvp;

import java.util.HashMap;
import java.util.Map;

import devin.cn.com.rxjavaretrofit.Api.CombinApi;
import devin.cn.com.rxjavaretrofit.exception.ApiException;
import devin.cn.com.rxjavaretrofit.listener.HttpOnNextListener;

/**
 * Created by zengxiaowen on 2018/1/22.
 */

public abstract class BasePresenter<T extends IActivityView>{

    protected IActivityView mView;
    public CombinApi api ;

    public BasePresenter(T activity) {
        this.mView = activity;
        if (api==null) {
            api = new CombinApi(new HttpOnNextListener() {
                @Override
                public void onNext(String resulte, String method) {
                    onNexts(resulte, method);
                }

                @Override
                public void onError(ApiException e, String method) {
                    onErrors(e, method);
                }
            }, activity.initActivity());
        }
    }


    protected abstract void initData(Map<String,Object> map);
    protected abstract void onNexts(String resulte, String method);
    protected abstract void onErrors(ApiException e, String method);

}
