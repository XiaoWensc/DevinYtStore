package devin.cn.com.rxjavaretrofit.mvp;

/**
 * Created by zengxiaowen on 2018/1/22.
 */

public interface IActivityView {
    void showToast(String msg);
    void refresh();
    void onEmpty();
    void showLog(String msg);
    BaseActivity initActivity();
}
