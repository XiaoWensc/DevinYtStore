package zxw.cn.com.rrxjava.ui.home;

import android.graphics.Color;
import android.os.Build;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

import devin.cn.com.rxjavaretrofit.mvp.BaseFragment;
import devin.cn.com.rxjavaretrofit.mvp.BasePresenter;
import devin.cn.com.rxjavaretrofit.utils.AppUtil;
import zxw.cn.com.rrxjava.R;
import zxw.cn.com.rrxjava.ui.main.MainActivity;
import zxw.cn.com.rrxjava.utils.StatusBarUtil;
import zxw.cn.com.rrxjava.widget.webview.UIWebView;

/**
 * Created by zengxiaowen on 2018/1/29.
 */

public class HomeFragment extends BaseFragment implements MainActivity.MainFragment{

    private UIWebView uiWebView;
    private ImageView iv_sao_sao;
    private TextView search_layout;
    private TextView index_myLocation;
    private View container_view;

    @Override
    public void refresh() {

    }

    @Override
    protected BasePresenter initPresent() {
        return null;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_home;
    }

    @Override
    protected void findView(View root) {
        container_view = root.findViewById(R.id.container_view);
        container_view.getBackground().setAlpha(0);
        uiWebView = root.findViewById(R.id.uiWebView);
        iv_sao_sao = root.findViewById(R.id.iv_sao_sao);
        search_layout = root.findViewById(R.id.search_layout);
        search_layout.getBackground().setAlpha(200);
        index_myLocation = root.findViewById(R.id.index_myLocation);
        uiWebView.setOnScrollChangedCallback((dx, dy) -> {
            //这里我们根据dx和dy参数做自己想做的事情
            container_view.getBackground().setAlpha(dy / 2 > 255 ? 255 : dy / 2);
            if (dy > 200) {
                iv_sao_sao.setImageResource(R.mipmap.ic_saoma_b);
                search_layout.getBackground().setAlpha(255);
                index_myLocation.setTextColor(Color.parseColor("#000000"));
            } else {
                iv_sao_sao.setImageResource(R.mipmap.ic_saoma);
                index_myLocation.setTextColor(getResources().getColor(R.color.white));
                search_layout.getBackground().setAlpha(200);
            }
        });

        uiWebView.loadUrl(AppUtil.getBaseUrl()+"ym/home/");
    }


    @Override
    public BaseFragment getFragment() {
        return this;
    }

    @Override
    public void update() {

    }
}
