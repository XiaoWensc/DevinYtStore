package devin.cn.com.rxjavaretrofit.utils;

import android.app.Activity;
import android.os.Build;

import java.lang.ref.WeakReference;
import java.util.Stack;

/**
 * Activity栈管理
 * Created by zengxiaowen on 2018/1/19.
 */

public class ActivityManager {

    private static final Stack<WeakReference<Activity>> activitys = new Stack<>();

    public static void in(Activity activity){
        activitys.add(new WeakReference<>(activity));
    }
    public static Activity current(){
        if(activitys.firstElement() == null) return null;
        return  activitys.firstElement().get();
    }

    public static Activity activityTop(){
        if (activitys.lastElement() == null) return null;
        return activitys.lastElement().get();
    }

    public static void out(Activity activity){
        int i = 0;
        for(WeakReference<Activity> weakReference:activitys){
            if(weakReference == null){
                i ++;
                continue;
            }
            Activity act = weakReference.get();
            if(act == null) {
                i ++;
                continue;
            }
            if(act.equals(activity)){
                break;
            }
            i ++;
        }
        if(activitys.size() > i) {
            activitys.removeElementAt(i);
        }
    }

    public static void finishAll(){
        while (activitys.size() > 0){
            WeakReference<Activity> aw = activitys.pop();
            if(aw == null) continue;
            Activity activity = aw.get();
            if(activity == null || activity.isFinishing() ) continue;
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
                if (activity.isDestroyed()) continue;
            }
            activity.finish();
        }
    }

    public static Stack<WeakReference<Activity>> getActivitys() {
        return activitys;
    }
}
