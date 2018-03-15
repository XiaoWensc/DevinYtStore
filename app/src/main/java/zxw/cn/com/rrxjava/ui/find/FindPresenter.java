package zxw.cn.com.rrxjava.ui.find;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;

import java.util.Map;

import devin.cn.com.rxjavaretrofit.exception.ApiException;
import devin.cn.com.rxjavaretrofit.mvp.BasePresenter;
import devin.cn.com.rxjavaretrofit.mvp.IPresenter;
import zxw.cn.com.rrxjava.entity.BaseResultEntity;

/**
 * Created by zengxiaowen on 2018/2/1.
 */

public class FindPresenter extends BasePresenter<FindFragment> implements IPresenter<FindBean> {

    private FindBean bean;

    public FindPresenter(FindFragment activity) {
        super(activity);
    }

    @Override
    public FindBean getData() {
        return bean;
    }

    @Override
    protected void initData(Map<String,Object> map) {
        api.postApi("rest/model/com/yatang/product/activity/ActivityListActor/queryActivity",map,"queryActivity");
    }

    @Override
    protected void onNexts(String resulte, String method) {
        BaseResultEntity<FindBean> entity = JSONObject.parseObject(resulte,new TypeReference<BaseResultEntity<FindBean>>(){});
        bean = entity.getActivityList();
        mView.refresh();
    }

    @Override
    protected void onErrors(ApiException e, String method) {
        mView.showToast(e.getMessage());
        mView.onEmpty();
    }
}
