package zxw.cn.com.rrxjava.entity;

import java.util.Map;

import devin.cn.com.rxjavaretrofit.Api.BaseApi;
import devin.cn.com.rxjavaretrofit.http.HttpPostService;
import retrofit2.Retrofit;
import rx.Observable;

/**
 * Created by zengxiaowen on 2018/2/2.
 */

public class PostApi extends BaseApi {

    private String url ;
    private Map<String,Object> map;

    public PostApi() {
        setCache(false);
        setMethod("Devin/post");
    }

    @Override
    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Map<String, Object> getMap() {
        return map;
    }

    public void setMap(Map<String, Object> map) {
        this.map = map;
    }

    @Override
    public Observable getObservable(Retrofit retrofit) {
        HttpPostService httpPostService = retrofit.create(HttpPostService.class);
        return httpPostService.getAllBy(getUrl(),getMap());
    }
}
