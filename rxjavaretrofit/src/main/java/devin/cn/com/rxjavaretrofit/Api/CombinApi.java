package devin.cn.com.rxjavaretrofit.Api;

import com.trello.rxlifecycle.components.support.RxAppCompatActivity;
import java.util.HashMap;
import java.util.Map;
import devin.cn.com.rxjavaretrofit.listener.HttpOnNextListener;
import devin.cn.com.rxjavaretrofit.listener.HttpOnNextSubListener;
import devin.cn.com.rxjavaretrofit.http.HttpPostService;

/**
 * 请求数据统一封装类
 * Created by zengxiaowen on 2018/1/19.
 */

public class CombinApi extends HttpManagerApi{

    public CombinApi(HttpOnNextListener onNextListener, RxAppCompatActivity appCompatActivity) {
        super(onNextListener, appCompatActivity);
        /*统一设置*/
        setCache(true);
    }

    public CombinApi(HttpOnNextSubListener onNextSubListener, RxAppCompatActivity appCompatActivity) {
        super(onNextSubListener, appCompatActivity);
        /*统一设置*/
        setCache(true);
    }


    /**
     * post请求演示
     * api-1
     * @param url api
     * @param maps 参数
     */
    public void postApi(String url, Map<String,Object> maps) {
        postApi(url,maps,"AppFiftyToneGraph/videoLink");
    }
    /**
     * post请求演示
     * api-1
     * @param url api
     */
    public void postApi(String url,String method) {
        postApi(url,null,method);
    }
    /**
     * post请求演示
     * api-1
     * @param url api
     */
    public void postApi(String url) {
        postApi(url,null,"AppFiftyToneGraph/videoLink");
    }

    /**
     * post请求演示
     * api-1
     * @param url api
     * @param maps 参数
     */
    public void postApi(String url, Map<String,Object> maps,String method) {
        if (maps==null) maps = new HashMap<>();
        maps.put("_dynSessConf",BaseApi._dynSessConf);
        /*也可单独设置请求，会覆盖统一设置*/
        setCache(false);
        setMethod(method);
        HttpPostService httpService = getRetrofit().create(HttpPostService.class);
        doHttpDeal(httpService.getAllBy(url,maps));
    }

    /**
     * post请求演示
     * api-2
     * @param url api
     * @param maps 参数
     */
    public void postApiOther(String url, Map<String,Object> maps) {
        if (maps==null) maps = new HashMap<>();
        maps.put("_dynSessConf",BaseApi._dynSessConf);
        setCache(true);
        setMethod("AppFiftyToneGraph/videoLink");
        HttpPostService httpService = getRetrofit().create(HttpPostService.class);
        doHttpDeal(httpService.getAllBy(url,maps));
    }
}
