package zxw.cn.com.rrxjava.ui.account;

import com.alibaba.fastjson.JSONObject;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import devin.cn.com.rxjavaretrofit.exception.ApiException;
import devin.cn.com.rxjavaretrofit.mvp.BasePresenter;
import devin.cn.com.rxjavaretrofit.mvp.IPresenter;
import zxw.cn.com.rrxjava.R;
import zxw.cn.com.rrxjava.base.Config;

/**
 * Created by zengxiaowen on 2018/2/1.
 */

public class AccountPresenter extends BasePresenter<AccountFragment> implements IPresenter<List<AccountModel>> {

    List<AccountModel> list;

    public AccountPresenter(AccountFragment activity) {
        super(activity);
        list = new ArrayList<>();
    }

    @Override
    public List<AccountModel> getData() {
        return list;
    }

    @Override
    protected void initData(Map<String, Object> map) {
        list.clear();
        AccountModel model = new AccountModel();
        model.setViewType(0);
        if (Config.UserInfo.isLogin) {
            model.setHreadView(new AccountModel.HreadView(Config.UserInfo.mobile, Config.UserInfo.mobile, false));
            list.add(model);
            api.postApi("rest/model/atg/commerce/order/OrderLookupActor/myOrderCounts",map);
        }else{
            list.add(model);
            model = new AccountModel();
            model.setViewType(1);
            list.add(model);
            addItemView();
            mView.refresh();
        }
    }

    @Override
    protected void onNexts(String resulte, String method) {
        JSONObject object = JSONObject.parseObject(resulte).getJSONObject("orderCounts");
        AccountModel model = new AccountModel();
        model.setViewType(1);
        model.setOrderView(new AccountModel.OrderView(object.getString("PENDING_EVALUATE"),object.getString("PENDING_RECEIVING"),object.getString("PENDING_PAYMENT"),object.getString("PENDING_PAYMENT")));
        list.add(model);
        addItemView();
        mView.refresh();
    }

    @Override
    protected void onErrors(ApiException e, String method) {
        mView.showToast(e.getMessage());
        mView.onEmpty();
    }

    private void addItemView(){
        AccountModel model = new AccountModel();
        model.setViewType(2);
        model.setItemView(new AccountModel.ItemView(R.mipmap.ic_supvip,"超级会员购物专区"));
        list.add(model);
        model = new AccountModel();
        model.setViewType(2);
        model.setItemView(new AccountModel.ItemView(R.mipmap.img_my_collection,"我的收藏"));
        list.add(model);
        model = new AccountModel();
        model.setViewType(2);
        model.setItemView(new AccountModel.ItemView(R.mipmap.img_my_footprint,"我的足迹"));
        list.add(model);
        model = new AccountModel();
        model.setViewType(2);
        model.setItemView(new AccountModel.ItemView(R.mipmap.img_my_coupon,"我的电商劵"));
        list.add(model);
        model = new AccountModel();
        model.setViewType(2);
        model.setItemView(new AccountModel.ItemView(R.mipmap.xc_icon,"我的小超劵"));
        list.add(model);
    }
}
