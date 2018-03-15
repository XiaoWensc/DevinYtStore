package zxw.cn.com.rrxjava.ui.launcher;

import android.os.CountDownTimer;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.drawable.GlideDrawable;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.SimpleTarget;
import com.bumptech.glide.request.target.Target;

import devin.cn.com.rxjavaretrofit.mvp.BaseActivity;
import zxw.cn.com.rrxjava.R;
import zxw.cn.com.rrxjava.ui.main.MainActivity;
import zxw.cn.com.rrxjava.ui.webview.BaseWebViewActivity;

/**
 * 欢迎页
 * Created by zengxiaowen on 2018/1/29.
 */

public class LauncherActivity extends BaseActivity<LauncherPresenter> {

    private ImageView imageView;
    private TextView tvTime;
    private CountDownTimer timer;
    private String[] date;
    private boolean reme = false;

    @Override
    public void refresh() {
        date = basePresenter.getData();
        Glide.with(this).load(date[0]).listener(new RequestListener<String, GlideDrawable>() {
            @Override
            public boolean onException(Exception e, String model, Target<GlideDrawable> target, boolean isFirstResource) {
                timer.onFinish();
                return false;
            }

            @Override
            public boolean onResourceReady(GlideDrawable resource, String model, Target<GlideDrawable> target, boolean isFromMemoryCache, boolean isFirstResource) {
                imageView.setVisibility(View.VISIBLE);
                tvTime.setVisibility(View.VISIBLE);
                timer.start();
                return false;
            }
        }).into(new SimpleTarget<GlideDrawable>() {
            @Override
            public void onResourceReady(GlideDrawable resource, GlideAnimation<? super GlideDrawable> glideAnimation) {
                imageView.setImageDrawable(resource);
            }
        });
    }

    @Override
    protected LauncherPresenter initPresent() {
        return new LauncherPresenter(this);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_launcher;
    }

    @Override
    protected void findView() {
        (imageView = findViewById(R.id.launcher)).setOnClickListener(view -> {
            reme = true;
            timer.cancel();
            getOperation().addParameter("link",date[1]);
            getOperation().forward(BaseWebViewActivity.class);
        });
        //设置透明度
        (tvTime = findViewById(R.id.tvTime)).getBackground().setAlpha(100);
        timer = new CountDownTimer(5*1000,1000) {
            @Override
            public void onTick(long l) {
                tvTime.setText("跳过："+l/1000);
            }

            @Override
            public void onFinish() {
                getOperation().forward(MainActivity.class);
                finish();
            }
        };
        tvTime.setOnClickListener(view -> timer.onFinish());

        basePresenter.initData(null);
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (timer!=null) timer.cancel();
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (reme) {
            getOperation().forward(MainActivity.class);
            finish();
        }
    }
}
