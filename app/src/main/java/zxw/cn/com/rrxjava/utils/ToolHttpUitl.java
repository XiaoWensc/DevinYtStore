package zxw.cn.com.rrxjava.utils;

import android.content.Context;
import android.support.annotation.Nullable;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import com.allenliu.versionchecklib.v2.AllenVersionChecker;
import com.allenliu.versionchecklib.v2.builder.DownloadBuilder;
import com.allenliu.versionchecklib.v2.builder.UIData;
import com.allenliu.versionchecklib.v2.callback.RequestVersionListener;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import devin.cn.com.rxjavaretrofit.Api.BaseApi;
import devin.cn.com.rxjavaretrofit.Api.CombinApi;
import devin.cn.com.rxjavaretrofit.exception.ApiException;
import devin.cn.com.rxjavaretrofit.listener.HttpOnNextListener;
import devin.cn.com.rxjavaretrofit.mvp.BaseActivity;
import zxw.cn.com.rrxjava.base.Config;
import zxw.cn.com.rrxjava.ui.webview.BaseWebViewActivity;

/**
 * Created by zengxiaowen on 2018/1/29.
 */

public class ToolHttpUitl {

    /**
     * 获取SessionConfirmationNumber
     * @param activity
     */
    public static void getSessionConf(BaseActivity activity){
        CombinApi api = new CombinApi(new HttpOnNextListener() {
            @Override
            public void onNext(String resulte, String method) {
                 BaseApi._dynSessConf = JSONObject.parseObject(resulte).getString("sessionConfirmationNumber");
            }

            @Override
            public void onError(ApiException e, String method) {

            }
        },activity);
        api.setShowProgress(false);
        api.postApi("rest/model/atg/rest/SessionConfirmationActor/getSessionConfirmationNumber");
    }

    /**
     * 登录
     * @param activity
     * @param map
     * @param isShow
     */
    public static void login(BaseActivity activity,Map<String, Object> map,boolean isShow){
        CombinApi api = new CombinApi(new HttpOnNextListener() {
            @Override
            public void onNext(String resulte, String method) {
                Config.UserInfo.isLogin = true;
            }

            @Override
            public void onError(ApiException e, String method) {

            }
        },activity);
        api.setShowProgress(isShow);
        api.postApi("rest/model/atg/userprofiling/ProfileActor/login",map);
    }

    /**
     * 版本更新
     * @param activity
     */
    public static void getAndroidVersion(BaseActivity activity){
        AllenVersionChecker
                .getInstance()
                .requestVersion()
                .setRequestUrl(BaseApi.getBaseUrl()+"rest/model/com/yatang/general/GeneralActor/androidVersion")
                .request(new RequestVersionListener() {
                    @Nullable
                    @Override
                    public UIData onRequestVersionSuccess(String result) {
                        //拿到服务器返回的数据，解析，拿到downloadUrl和一些其他的UI数据
                        JSONObject object = JSONObject.parseObject(result);
                        JSONObject versionInfo = object.getJSONObject("versionInfo");
                        String url = versionInfo.getString("DownloadUrl");
                        String appName = versionInfo.getString("APPname");
                        String description = versionInfo.getString("description");
                        return UIData.create().setDownloadUrl(url).setTitle(appName).setContent(description);
                    }

                    @Override
                    public void onRequestVersionFailure(String message) {
                        activity.showToast(message);
                    }
                }).excuteMission(activity);
    }

    /**
     * 获取开通会员界面Url
     * @param context
     */
    public static void postSupVip(final BaseActivity context) {
        Map<String,Object> parm = new HashMap<>();
        parm.put("source", "1"); //1、app,2、wap
        new CombinApi(new HttpOnNextListener() {
            @Override
            public void onNext(String resulte, String method) {
                JSONObject object = JSONObject.parseObject(resulte);
                Map<String,Object> strMap = new HashMap<>();
                strMap.put("userName", Config.UserInfo.mobile);  //用户名
                strMap.put("nonceStr", object.getString("nonceStr"));  //随机数 数字字母随便（最多32位）
                strMap.put("superMember", object.getString("superMember")); //是否是超级会员  0不是，1是
                strMap.put("source", "1"); //1、app,2、wap
                strMap.put("token", object.getString("token"));   // 加入token
                String postKey = getSignContentMapValueString(strMap);  // 组装post参数
                context.getOperation().addParameter("link", Config.UserInfo.superMemberURL);
                context.getOperation().addParameter("postDate", postKey);
                context.getOperation().forward(BaseWebViewActivity.class);
            }

            @Override
            public void onError(ApiException e, String method) {
                context.showToast(e.getDisplayMessage());
            }
        },context).postApi("rest/model/atg/userprofiling/ProfileActor/getLoginToSVipWapParams",parm);
    }

    private static String getSignContentMapValueString(Map<String, Object> sortedParams) {
        StringBuffer content = new StringBuffer();
        List<String> keys = new ArrayList<>(sortedParams.keySet());
        Collections.sort(keys);
        int index = 0;
        for (int i = 0; i < keys.size(); i++) {
            String key = keys.get(i);
            String value = sortedParams.get(key).toString();
            if (value == null)
                continue;
            if (!value.isEmpty()) {
                content.append((index == 0 ? "" : "&") + key + "=" + value);
                index++;
            }
        }
        return content.toString();
    }

    public interface OnHttpNext{
        void onSucc();
    }
}
