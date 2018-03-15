package zxw.cn.com.rrxjava.ui.main;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RadioGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import devin.cn.com.rxjavaretrofit.mvp.BaseActivity;
import devin.cn.com.rxjavaretrofit.mvp.BaseFragment;
import zxw.cn.com.rrxjava.R;
import zxw.cn.com.rrxjava.base.FragmentPagerAdapter;
import zxw.cn.com.rrxjava.ui.account.AccountFragment;
import zxw.cn.com.rrxjava.ui.classify.ClassifyFragment;
import zxw.cn.com.rrxjava.ui.find.FindFragment;
import zxw.cn.com.rrxjava.ui.home.HomeFragment;

public class MainActivity extends BaseActivity<MainPresenter> {

    //
    private HomeFragment f1;
    private ClassifyFragment f2;
    private FindFragment f3;
    private HomeFragment f4;
    private AccountFragment f5;


    @Override
    protected MainPresenter initPresent() {
        return new MainPresenter(this);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    protected void findView() {
        //
        ((RadioGroup)findViewById(R.id.home_radio_button_group)).setOnCheckedChangeListener((radioGroup, i) -> {
            initFragmentTab(getItemView(radioGroup,i));
        });

        initFragment1();
        //检查更新
        basePresenter.initData(null);
    }

    private void initFragmentTab(int i){
        switch (i){
            case 0:
                initFragment1();
                break;
            case 1:
                initFragment2();
                break;
            case 2:
                initFragment3();
                break;
            case 3:
                initFragment4();
                break;
            case 4:
                initFragment5();
                break;
        }
    }

    /**
     * @param viewGroup
     * @param id
     * @return
     */
    private int getItemView(ViewGroup viewGroup,int id){
        for (int i=0;i<viewGroup.getChildCount();i++){
            if (viewGroup.getChildAt(i).getId()==id) return i;
        }
        return 0;
    }


    //显示第一个fragment
    private void initFragment1(){
        //开启事务，fragment的控制是由事务来实现的
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();

        //第一种方式（add），初始化fragment并添加到事务中，如果为null就new一个
        if(f1 == null){
            f1 = new HomeFragment();
            transaction.add(R.id.main_viewPager, f1);
        }
        //隐藏所有fragment
        hideFragment(transaction);
        //显示需要显示的fragment
        transaction.show(f1);

        //第二种方式(replace)，初始化fragment
//        if(f1 == null){
//            f1 = new MyFragment("消息");
//        }
//        transaction.replace(R.id.main_frame_layout, f1);

        //提交事务
        transaction.commit();
    }
    //显示第二个fragment
    private void initFragment2(){
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();

        if(f2 == null){
            f2 = new ClassifyFragment();
            transaction.add(R.id.main_viewPager,f2);
        }
        hideFragment(transaction);
        transaction.show(f2);

//        if(f2 == null) {
//            f2 = new MyFragment("联系人");
//        }
//        transaction.replace(R.id.main_frame_layout, f2);

        transaction.commit();
    }

    //显示第三个fragment
    private void initFragment3(){
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();

        if(f3 == null){
            f3 = new FindFragment();
            transaction.add(R.id.main_viewPager,f3);
        }
        hideFragment(transaction);
        transaction.show(f3);

//        if(f3 == null) {
//            f3 = new MyFragment("动态");
//        }
//        transaction.replace(R.id.main_frame_layout, f3);

        transaction.commit();
    }
    //显示第四个fragment
    private void initFragment4(){
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();

        if(f4 == null){
            f4 = new HomeFragment();
            transaction.add(R.id.main_viewPager,f4);
        }
        hideFragment(transaction);
        transaction.show(f4);

//        if(f3 == null) {
//            f3 = new MyFragment("动态");
//        }
//        transaction.replace(R.id.main_frame_layout, f3);

        transaction.commit();
    }
    //显示第五个fragment
    private void initFragment5(){
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();

        if(f5 == null){
            f5 = new AccountFragment();
            transaction.add(R.id.main_viewPager,f5);
        }
        hideFragment(transaction);
        transaction.show(f5);

//        if(f3 == null) {
//            f3 = new MyFragment("动态");
//        }
//        transaction.replace(R.id.main_frame_layout, f3);

        transaction.commit();
    }


    //隐藏所有的fragment
    private void hideFragment(FragmentTransaction transaction){
        if(f1 != null){
            transaction.hide(f1);
        }
        if(f2 != null){
            transaction.hide(f2);
        }
        if(f3 != null){
            transaction.hide(f3);
        }
        if(f4 != null){
            transaction.hide(f4);
        }
        if(f5 != null){
            transaction.hide(f5);
        }
    }



    @Override
    public void refresh() {
        Log.d("zxw","main");
    }

    public class MainPagerAdapter extends FragmentPagerAdapter {

        private List<MainFragment> mainFragments;

        public MainPagerAdapter(FragmentActivity activity, List<MainFragment> mainFragments) {
            super(activity.getSupportFragmentManager());
            this.mainFragments = mainFragments;
        }

        @Override
        public int getCount() {
            return mainFragments == null ? 0 : mainFragments.size();
        }

        @Override
        public Fragment getItem(int position) {
            return mainFragments.get(position).getFragment();
        }
    }

    public interface MainFragment {
        BaseFragment getFragment();
        void update();
    }
}
