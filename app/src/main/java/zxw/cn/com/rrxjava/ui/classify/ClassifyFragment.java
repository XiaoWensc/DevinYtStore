package zxw.cn.com.rrxjava.ui.classify;

import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

import java.util.ArrayList;
import java.util.List;

import devin.cn.com.rxjavaretrofit.mvp.BaseFragment;
import q.rorbin.verticaltablayout.VerticalTabLayout;
import q.rorbin.verticaltablayout.adapter.TabAdapter;
import q.rorbin.verticaltablayout.widget.ITabView;
import q.rorbin.verticaltablayout.widget.TabView;
import zxw.cn.com.rrxjava.R;
import zxw.cn.com.rrxjava.base.BaseRecyclerAdapter;

/**
 * 分类
 * Created by zengxiaowen on 2018/1/30.
 */

public class ClassifyFragment extends BaseFragment<ClassifyPresenter> {

    private VerticalTabLayout tabLayout;
    private RecyclerView recyclerView;
    private ClassifyAdapter classifyAdapter;
    private SwipeRefreshLayout refreshLayout;

    @Override
    public void refresh() {
        if (refreshLayout.isRefreshing()) {
            refreshLayout.setRefreshing(false);
        }
        List<String> titles = new ArrayList<>();
        for (String key : basePresenter.getData().keySet()) {
            titles.add(key);
        }
        tabLayout.setTabAdapter(new LeftTabAdapter(titles));
        classifyAdapter.clear();
        classifyAdapter.addItemDates(basePresenter.getData().get(titles.get(0)));
        classifyAdapter.notifyDataSetChanged();
    }

    @Override
    protected ClassifyPresenter initPresent() {
        return new ClassifyPresenter(this);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_classify;
    }

    @Override
    protected void findView(View root) {
        Toolbar toolbar = root.findViewById(R.id.toolbar);
        toolbar.inflateMenu(R.menu.menu_classify);
        toolbar.setOnMenuItemClickListener(item -> {
            showToast("点击了扫码");
            return false;
        });

        refreshLayout = root.findViewById(R.id.refreshLayout);
        tabLayout = root.findViewById(R.id.tabLayout);
        recyclerView = root.findViewById(R.id.recyclerView);
        GridLayoutManager manager = new GridLayoutManager(getContext(), 3);
        manager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
            @Override
            public int getSpanSize(int position) {//头部 0  ， 标题 1  ，图片2  ，文字图片， 3
                switch (recyclerView.getAdapter().getItemViewType(position)) {
                    case 0:
                        return 3;
                    case 1://
                        return 3;
                    case 2://
                        return 1;
                    default://1
                        return 3;
                }
            }
        });
        recyclerView.setLayoutManager(manager);

        classifyAdapter = new ClassifyAdapter();
        recyclerView.setAdapter(classifyAdapter);
        tabLayout.addOnTabSelectedListener(new VerticalTabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabView tab, int position) {
                classifyAdapter.clear();
                classifyAdapter.addItemDates(basePresenter.getData().get(tab.getTitleView().getText().toString()));
                classifyAdapter.notifyDataSetChanged();
            }

            @Override
            public void onTabReselected(TabView tab, int position) {
            }
        });

        refreshLayout.setOnRefreshListener(() -> basePresenter.initData(null));

        basePresenter.initData(null);
    }

    private class ClassifyAdapter extends BaseRecyclerAdapter {

        @Override
        public RecyclerView.ViewHolder OnCreateViewHolder(ViewGroup parent, int viewType) {
            switch (viewType) {
                case 0:
                    View hreadView = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_classify_hread, parent, false);
                    return new HreadViewHolder(hreadView);
                case 1:
                    View titleView = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_classify_title, parent, false);
                    return new TitleViewHolder(titleView);
                case 2:
                    View txtView = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_classify_txt, parent, false);
                    return new TxtViewHolder(txtView);
            }
            return null;
        }

        @Override
        public void OnBindViewHolder(RecyclerView.ViewHolder holder, int position) {
            ClassifyEntity.Categories cat = (ClassifyEntity.Categories) getAllList().get(position);
            switch (getItemViewType(position)) {
                case 0:
                    Glide.with(holder.itemView.getContext()).load(cat.getMobilePromoImageUrl()).into(((HreadViewHolder) holder).imgTopView);
                    break;
                case 1:
                    ((TitleViewHolder) holder).title.setText(cat.getTitleName());
                    break;
                case 2:
                    ((TxtViewHolder)holder).name.setText(cat.getCategoryName());
                    Glide.with(holder.itemView.getContext()).load(cat.getImgUrl()).skipMemoryCache(true).diskCacheStrategy(DiskCacheStrategy.SOURCE).placeholder(R.color.ECECEC).error(R.mipmap.yt_default).into(((TxtViewHolder) holder).imgView);
                    break;
            }
        }

        @Override
        public int GetItemViewType(int position) {
            return ((ClassifyEntity.Categories) (getAllList().get(position))).getViewType();
        }
    }

    private class HreadViewHolder extends RecyclerView.ViewHolder {
        ImageView imgTopView;
        public HreadViewHolder(View itemView) {
            super(itemView);
            imgTopView = itemView.findViewById(R.id.imgTopView);
        }
    }

    private class TitleViewHolder extends RecyclerView.ViewHolder {
        TextView title;
        public TitleViewHolder(View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.title);
        }
    }

    private class TxtViewHolder extends RecyclerView.ViewHolder {
        TextView name;
        ImageView imgView;
        public TxtViewHolder(View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.name);
            imgView = itemView.findViewById(R.id.imgView);
        }
    }

    /**
     * 侧边栏适配器
     */
    private class LeftTabAdapter implements TabAdapter {

        private List<String> titles;

        public LeftTabAdapter(List<String> titles) {
            this.titles = titles;
        }

        @Override
        public int getCount() {
            return titles == null ? 0 : titles.size();
        }

        @Override
        public ITabView.TabBadge getBadge(int position) {
            return null;
        }

        @Override
        public ITabView.TabIcon getIcon(int position) {
            return null;
        }

        @Override
        public ITabView.TabTitle getTitle(int position) {
            return new TabView.TabTitle.Builder().setContent(titles.get(position)).setTextSize(12)
                    .setTextColor(0xFF36BC9B, 0xFF757575).build();
        }

        @Override
        public int getBackground(int position) {
            return -1;
        }
    }
}
