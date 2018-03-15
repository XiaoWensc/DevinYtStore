package zxw.cn.com.rrxjava.ui.account;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.aspsine.swipetoloadlayout.OnRefreshListener;
import com.aspsine.swipetoloadlayout.SwipeToLoadLayout;

import devin.cn.com.rxjavaretrofit.mvp.BaseActivity;
import devin.cn.com.rxjavaretrofit.mvp.BaseFragment;
import zxw.cn.com.rrxjava.R;
import zxw.cn.com.rrxjava.base.BaseRecyclerAdapter;
import zxw.cn.com.rrxjava.base.Config;
import zxw.cn.com.rrxjava.base.SafetyLinearLayoutManager;
import zxw.cn.com.rrxjava.ui.login.LoginActivity;
import zxw.cn.com.rrxjava.utils.ToolHttpUitl;
import zxw.cn.com.rrxjava.widget.RoundImageView;

/**
 * Created by zengxiaowen on 2018/2/1.
 */

public class AccountFragment extends BaseFragment<AccountPresenter> {

    private SwipeToLoadLayout refreshLayout;
    private AccountAdapter adapter;

    @Override
    public void refresh() {
        if (refreshLayout.isRefreshing()) refreshLayout.setRefreshing(false);
        adapter.clear();
        adapter.addItemDates(basePresenter.getData());
        adapter.notifyDataSetChanged();
    }

    @Override
    protected AccountPresenter initPresent() {
        return new AccountPresenter(this);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_account;
    }

    @Override
    protected void findView(View root) {
        refreshLayout = root.findViewById(R.id.my_list_swipe);
        refreshLayout.setLoadMoreEnabled(false);
        refreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh() {
                basePresenter.initData(null);
            }
        });
        RecyclerView swipe_target = root.findViewById(R.id.swipe_target);
        swipe_target.setLayoutManager(new SafetyLinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false) {
            @Override
            public RecyclerView.LayoutParams generateDefaultLayoutParams() {
                return new RecyclerView.LayoutParams(-1, -2);
            }
        });
        adapter = new AccountAdapter();
        swipe_target.setAdapter(adapter);

        basePresenter.initData(null);
    }

    private class AccountAdapter extends BaseRecyclerAdapter<AccountModel> {

        @Override
        public RecyclerView.ViewHolder OnCreateViewHolder(ViewGroup parent, int viewType) {
            switch (viewType) {
                case 0:
                    return new HreadView(LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_account_hread, null));
                case 1:
                    return new OrderView(LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_account_order, null));
                default:
                    return new ItemView(LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_account_item, null));
            }
        }

        @Override
        public void OnBindViewHolder(RecyclerView.ViewHolder holder, int position) {
            AccountModel model = getAllList().get(position);
            switch (getItemViewType(position)) {
                case 0:
                    if (Config.UserInfo.isLogin){
                        ((HreadView)holder).tv_login_state.setText(Config.UserInfo.mobile);
                        ((HreadView)holder).imgSupVip.setVisibility(View.VISIBLE);
                        if (Config.UserInfo.isVip){
                            ((HreadView)holder).imgSupVip.setImageResource(R.mipmap.ic_login_account_vip);
                            ((HreadView)holder).tvSupVip.setVisibility(View.VISIBLE);
                            ((HreadView)holder).tvVip.setVisibility(View.GONE);
                        }else{
                            ((HreadView)holder).imgSupVip.setImageResource(R.mipmap.ic_login_account);
                            ((HreadView)holder).tvSupVip.setVisibility(View.GONE);
                            ((HreadView)holder).tvVip.setVisibility(View.VISIBLE);
                        }
                    }else{
                        ((HreadView)holder).tv_login_state.setText("登录/注册");
                        ((HreadView)holder).imgSupVip.setVisibility(View.GONE);
                        ((HreadView)holder).tvSupVip.setVisibility(View.GONE);
                        ((HreadView)holder).tvVip.setVisibility(View.GONE);
                    }
                    break;
                case 1:

                    break;
                default:
                    break;
            }
        }

        @Override
        public int GetItemViewType(int position) {
            AccountModel model = getAllList().get(position);
            return model.getViewType();
        }
    }

    private class HreadView extends RecyclerView.ViewHolder {
        RoundImageView roundImageView;
        ImageView imgSupVip;
        TextView tv_login_state, tvSupVip, tvVip;
        public HreadView(View itemView) {
            super(itemView);
            roundImageView = itemView.findViewById(R.id.roundImageView);
            imgSupVip = itemView.findViewById(R.id.imgSupVip);
            tv_login_state = itemView.findViewById(R.id.tv_login_state);
            tvSupVip = itemView.findViewById(R.id.tvSupVip);
            tvVip = itemView.findViewById(R.id.tvVip);
            tvSupVip.setOnClickListener(view -> ToolHttpUitl.postSupVip((BaseActivity) itemView.getContext()));
            tvVip.setOnClickListener(view -> ToolHttpUitl.postSupVip((BaseActivity) itemView.getContext()));
            itemView.setOnClickListener(view -> getOperation().forward(LoginActivity.class));
        }
    }

    private class OrderView extends RecyclerView.ViewHolder {

        public OrderView(View itemView) {
            super(itemView);
        }
    }

    private class ItemView extends RecyclerView.ViewHolder {

        public ItemView(View itemView) {
            super(itemView);
        }
    }

}
