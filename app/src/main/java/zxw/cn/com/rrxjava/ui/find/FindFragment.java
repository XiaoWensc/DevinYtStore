package zxw.cn.com.rrxjava.ui.find;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.aspsine.swipetoloadlayout.SwipeToLoadLayout;
import com.bumptech.glide.Glide;
import java.util.HashMap;
import java.util.Map;
import devin.cn.com.rxjavaretrofit.mvp.BaseFragment;
import zxw.cn.com.rrxjava.R;
import zxw.cn.com.rrxjava.base.BaseRecyclerAdapter;
import zxw.cn.com.rrxjava.base.SafetyLinearLayoutManager;
import zxw.cn.com.rrxjava.utils.ToolString;

/**
 * 发现界面
 * Created by zengxiaowen on 2018/2/1.
 */

public class FindFragment extends BaseFragment<FindPresenter> {

    private SwipeToLoadLayout refreshLayout;
    private FindAdapter adapter;

    @Override
    public void refresh() {
        if (refreshLayout.isRefreshing()) refreshLayout.setRefreshing(false);
        if (refreshLayout.isLoadingMore()) refreshLayout.setLoadingMore(false);
        if (basePresenter.getData().getRows().size()<10) refreshLayout.setLoadMoreEnabled(false);
        else refreshLayout.setLoadMoreEnabled(true);
        adapter.addItemDates(basePresenter.getData().getRows());
        adapter.notifyDataSetChanged();
    }

    @Override
    protected FindPresenter initPresent() {
        return new FindPresenter(this);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_find;
    }

    @Override
    protected void findView(View root) {
        refreshLayout = root.findViewById(R.id.my_list_swipe);
        RecyclerView swipe_target = root.findViewById(R.id.swipe_target);
        swipe_target.setLayoutManager(new SafetyLinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false) {
            @Override
            public RecyclerView.LayoutParams generateDefaultLayoutParams() {
                return new RecyclerView.LayoutParams(-1, -2);
            }
        });
        adapter = new FindAdapter();
        swipe_target.setAdapter(adapter);
        refreshLayout.setOnRefreshListener(() -> {
            adapter.clear();
            apiPost(1);
        });
        refreshLayout.setOnLoadMoreListener(() -> apiPost(basePresenter.getData().getPageNum()+1));
        apiPost(1);
    }

    private void apiPost(int pageNumber){
        Map<String, Object> parmsMap = new HashMap<>();
        parmsMap.put("pageNumber", pageNumber);
        parmsMap.put("pageSize", 10);
        basePresenter.initData(parmsMap);
    }

    private class FindAdapter extends BaseRecyclerAdapter<FindBean.FindInfo> {

        @Override
        public RecyclerView.ViewHolder OnCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_find_item,null);
            return new ViewHolder(view);
        }

        @Override
        public void OnBindViewHolder(RecyclerView.ViewHolder holder, int position) {
            FindBean.FindInfo info = getAllList().get(position);
            ViewHolder viewHolder = (ViewHolder) holder;
            viewHolder.tv_title.setText(info.getActivityName());
            String startYear = info.getStartTime().substring(0, 4);
            String startMonth = info.getStartTime().substring(5, 7);
            String startDay = info.getStartTime().substring(8, 10);
            String endYear = info.getEndTime().substring(0, 4);
            String endMonth = info.getEndTime().substring(5, 7);
            String endDay = info.getEndTime().substring(8, 10);
            viewHolder.tv_time.setText("活动时间：" + startYear + "年" + startMonth + "月"+ startDay + "-" + endYear + "年" + endMonth + "月" + endDay+ "日");
            Glide.with(viewHolder.itemView.getContext()).load(ToolString.utfToGbk(info.getImageUrl())).placeholder(R.color.ECECEC).error(R.mipmap.yt_default).into(viewHolder.iv_url);
            viewHolder.tv_description.setText(info.getActivityDesc());
        }
    }

    private class ViewHolder extends RecyclerView.ViewHolder {
        ImageView iv_url;
        TextView tv_title;
        TextView tv_time;
        TextView tv_description;
        public ViewHolder(View itemView) {
            super(itemView);
            iv_url = itemView.findViewById(R.id.iv_url);
            tv_title = itemView.findViewById(R.id.tv_title);
            tv_description = itemView.findViewById(R.id.tv_description);
            tv_time = itemView.findViewById(R.id.tv_time);
        }
    }
}
