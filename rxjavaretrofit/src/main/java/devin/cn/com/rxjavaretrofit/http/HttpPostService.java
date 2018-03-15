package devin.cn.com.rxjavaretrofit.http;

import java.util.Map;

import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.QueryMap;
import retrofit2.http.Url;
import rx.Observable;

/**
 * 测试接口service-post相关
 * Created by zengxiaowen on 2018/1/19.
 */

public interface HttpPostService {

//    @POST
//    Observable<String> getAllBy(@Url String url);
    @Headers({
            "Content-Type: application/json;charset=UTF-8"
    })
    @POST
    Observable<String> getAllBy(@Url String url, @QueryMap Map<String ,Object> maps);

}
