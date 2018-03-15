package zxw.cn.com.rrxjava.base;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

/**
 * 防止RecycleView因为数据改变前clear数据而崩溃的问题
 * Created by zengxiaowen on 2018/2/1.
 */

public abstract class SafetyLinearLayoutManager extends LinearLayoutManager {

    public SafetyLinearLayoutManager(Context context) {
        super(context);
    }

    public SafetyLinearLayoutManager(Context context, int orientation, boolean reverseLayout) {
        super(context, orientation, reverseLayout);
    }

    @Override
    public void onLayoutChildren(RecyclerView.Recycler recycler, RecyclerView.State state) {
        try {
            super.onLayoutChildren(recycler, state);
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
