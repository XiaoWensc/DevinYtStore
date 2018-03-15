package zxw.cn.com.rrxjava.ui.main;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;

import java.util.Map;

import devin.cn.com.rxjavaretrofit.exception.ApiException;
import devin.cn.com.rxjavaretrofit.listener.HttpOnNextListener;
import devin.cn.com.rxjavaretrofit.mvp.BasePresenter;
import devin.cn.com.rxjavaretrofit.mvp.IPresenter;
import devin.cn.com.rxjavaretrofit.Api.CombinApi;
import zxw.cn.com.rrxjava.entity.BaseResultEntity;
import zxw.cn.com.rrxjava.entity.SubjectResulte;
import zxw.cn.com.rrxjava.utils.ToolHttpUitl;

/**
 * Created by zengxiaowen on 2018/1/22.
 */

public class MainPresenter extends BasePresenter<MainActivity> implements IPresenter<SubjectResulte>{

    public MainPresenter(MainActivity activity) {
        super(activity);
    }

    @Override
    protected void initData(Map<String,Object> map) {
        ToolHttpUitl.getAndroidVersion(mView.initActivity());
    }

    @Override
    protected void onNexts(String resulte, String method) {

    }

    @Override
    protected void onErrors(ApiException e, String method) {

    }


    @Override
    public SubjectResulte getData() {
        return null;
    }
}
