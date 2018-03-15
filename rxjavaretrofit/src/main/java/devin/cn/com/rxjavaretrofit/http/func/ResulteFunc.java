package devin.cn.com.rxjavaretrofit.http.func;

import devin.cn.com.rxjavaretrofit.exception.HttpTimeException;
import rx.functions.Func1;

/**
 * 服务器返回数据判断
 * Created by zengxiaowen on 2018/1/19.
 */

public class ResulteFunc implements Func1<Object,Object>{
    @Override
    public Object call(Object o) {
        if (o == null || "".equals(o.toString())) {
            throw new HttpTimeException("数据错误");
        }
        return o;
    }
}
