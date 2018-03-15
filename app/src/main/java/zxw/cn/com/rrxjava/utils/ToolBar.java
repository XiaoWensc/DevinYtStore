package zxw.cn.com.rrxjava.utils;

import android.content.Context;
import android.support.v7.widget.Toolbar;
import devin.cn.com.rxjavaretrofit.mvp.BaseActivity;
import zxw.cn.com.rrxjava.R;

/**
 * Created by zengxiaowen on 2018/1/29.
 */

public class ToolBar {

    public static Toolbar setToolBar(BaseActivity context, String title){
        Toolbar toolbar = context.findViewById(R.id.toolbar);
        toolbar.setTitle(title);
        //设置导航图标要在setSupportActionBar方法之后
        context.setSupportActionBar(toolbar);
        toolbar.setNavigationOnClickListener(view -> context.finish());
        return toolbar;
    }

}
