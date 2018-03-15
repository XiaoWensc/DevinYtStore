package zxw.cn.com.rrxjava.ui.login;

import java.util.Map;

import devin.cn.com.rxjavaretrofit.exception.ApiException;
import devin.cn.com.rxjavaretrofit.mvp.BasePresenter;
import devin.cn.com.rxjavaretrofit.mvp.IPresenter;
import zxw.cn.com.rrxjava.utils.ToolHttpUitl;

/**
 * Created by zengxiaowen on 2018/2/1.
 */

public class LoginPresenter extends BasePresenter<LoginActivity> {
    public LoginPresenter(LoginActivity activity) {
        super(activity);
    }

    @Override
    protected void initData(Map<String, Object> map) {
        ToolHttpUitl.login(mView.initActivity(),map,true);
    }

    @Override
    protected void onNexts(String resulte, String method) {

    }

    @Override
    protected void onErrors(ApiException e, String method) {

    }
}
