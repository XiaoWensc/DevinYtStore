package zxw.cn.com.rrxjava.ui.launcher;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;

import java.util.Map;

import devin.cn.com.rxjavaretrofit.exception.ApiException;
import devin.cn.com.rxjavaretrofit.mvp.BasePresenter;
import devin.cn.com.rxjavaretrofit.mvp.IPresenter;
import zxw.cn.com.rrxjava.base.Config;

/**
 * Created by zengxiaowen on 2018/1/29.
 */

public class LauncherPresenter extends BasePresenter<LauncherActivity> implements IPresenter<String[]> {

    private String[] imgUrl = new String[2];

    public LauncherPresenter(LauncherActivity activity) {
        super(activity);
    }

    @Override
    public String[] getData() {
        return imgUrl;
    }

    @Override
    protected void initData(Map<String,Object> map) {
        api.setShowProgress(false);
        // 请求当前定位
//        api.postApi("rest/model/com/yatang/general/GeneralActor/svipShopLocation","svipShopLocation");
        // 查询广告图
        api.postApi("rest/model/com/yatang/appStartAd/APPStartAdActor/queryAPPStartAd","queryAPPStartAd");

    }

    @Override
    protected void onNexts(String resulte, String method) {
        switch (method){
            case "svipShopLocation":
//                ToolGson.fromJson(resulte,Config.UserInfo.class);
                break;
            case "queryAPPStartAd":
                JSONObject object = JSONObject.parseObject(resulte, new TypeReference<JSONObject>() {});
                imgUrl[0] = object.getJSONArray("appStartAd").getJSONObject(0).getString("imgUrl");
                imgUrl[1] = object.getJSONArray("appStartAd").getJSONObject(0).getString("url");
                mView.refresh();
                break;
        }
    }

    @Override
    protected void onErrors(ApiException e, String method) {
        mView.showToast(e.getMessage());
        mView.onEmpty();
    }
}
