package devin.cn.com.rxjavaretrofit.mvp;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Toast;

import com.trello.rxlifecycle.components.support.RxAppCompatActivity;

import devin.cn.com.rxjavaretrofit.BuildConfig;
import devin.cn.com.rxjavaretrofit.utils.ActivityManager;

/**
 * Created by zengxiaowen on 2018/1/22.
 */

public abstract class BaseActivity<T extends BasePresenter> extends RxAppCompatActivity implements IActivityView{

    protected T basePresenter;
    protected View root;
    /**
     * 共通操作
     */
    private Operation baseOperation = null;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        ActivityManager.in(this);
        super.onCreate(savedInstanceState);
        root = LayoutInflater.from(this).inflate(getLayoutId(),null);
        setContentView(root);
        baseOperation = new Operation(this);
        basePresenter = initPresent();
        findView();
    }

    public Operation getOperation() {
        return baseOperation;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ActivityManager.out(this);
    }

    protected abstract T initPresent();
    protected abstract int getLayoutId();
    protected abstract void findView();

    @Override
    public void showToast(String msg) {
        Toast.makeText(this,msg,Toast.LENGTH_LONG).show();
    }

    @Override
    public void onEmpty() {
    }

    @Override
    public BaseActivity initActivity() {
        return this;
    }

    @Override
    public void showLog(String msg) {
        if (BuildConfig.DEBUG) Log.d("zxw",msg);
    }
}
