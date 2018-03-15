package devin.cn.com.rxjavaretrofit.mvp;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;
import com.trello.rxlifecycle.components.support.RxFragment;

import devin.cn.com.rxjavaretrofit.BuildConfig;


/**
 * Created by zengxiaowen on 2018/1/29.
 */

public abstract class BaseFragment<T extends BasePresenter> extends RxFragment implements IActivityView{

    protected T basePresenter;
    /**
     * 共通操作
     */
    private Operation baseOperation = null;

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        baseOperation = new Operation(getActivity());
        basePresenter = initPresent();
        findView(view);
        super.onViewCreated(view, savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {

        return inflater.inflate(getLayoutId(),null);
    }


    public Operation getOperation() {
        return baseOperation;
    }

    protected abstract T initPresent();
    protected abstract int getLayoutId();
    protected abstract void findView(View root);

    @Override
    public void showToast(String msg) {
        Toast.makeText(getActivity(),msg,Toast.LENGTH_LONG).show();
    }

    @Override
    public void onEmpty() {
    }

    @Override
    public void showLog(String msg) {
        if (BuildConfig.DEBUG) Log.d("zxw",msg);
    }

    @Override
    public BaseActivity initActivity() {
        return (BaseActivity) getActivity();
    }
}
