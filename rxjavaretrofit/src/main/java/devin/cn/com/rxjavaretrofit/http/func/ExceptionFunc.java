package devin.cn.com.rxjavaretrofit.http.func;

import android.util.Log;

import devin.cn.com.rxjavaretrofit.exception.FactoryException;
import rx.Observable;
import rx.functions.Func1;

/**
 * 异常处理
 * Created by zengxiaowen on 2018/1/19.
 */

public class ExceptionFunc implements Func1<Throwable, Observable> {
    @Override
    public Observable call(Throwable throwable) {
        Log.e("Tag","-------->"+throwable.getMessage());
        return Observable.error(FactoryException.analysisExcetpion(throwable));
    }
}
