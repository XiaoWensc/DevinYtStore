package devin.cn.com.rxjavaretrofit.listener;

import rx.Observable;

/**
 * 回调ober对象
 * Created by zengxiaowen on 2018/1/19.
 */

public interface HttpOnNextSubListener {

    /**
     * ober成功回调
     * @param observable
     * @param method
     */
    void onNext(Observable observable, String method);
}
