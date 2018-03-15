package zxw.cn.com.rrxjava.base;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import zxw.cn.com.rrxjava.R;

/**
 * Created by zengxiaowen on 2018/1/30.
 */

public abstract class BaseRecyclerAdapter<T> extends RecyclerView.Adapter implements IBaseRecylerView {
    private static final int EMPTY_VIEW = -2;
    private List<T> mDateList = new ArrayList<>();
    private int layout = 0;

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if(viewType == EMPTY_VIEW){
            View view = LayoutInflater.from(parent.getContext()).inflate(layout==0? R.layout.layout_empty_view:layout,parent,false);
            view.setLayoutParams(new RecyclerView.LayoutParams(-1,-1));
            return new EmptyViewHolder(view);
        }
        return OnCreateViewHolder(parent,viewType);
    }

    @Override
    public final void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if(!(holder instanceof EmptyViewHolder)){
            OnBindViewHolder(holder,position);
        }
    }


    @Override
    public final int getItemCount() {
        return mDateList.size()>0?mDateList.size():1;
    }

    @Override
    public final int getItemViewType(int position) {
        return mDateList.size()==0?EMPTY_VIEW:GetItemViewType(position);
    }

    protected void OnEmptyView(int layout){
        this.layout =layout;
    }

    public abstract RecyclerView.ViewHolder OnCreateViewHolder(ViewGroup parent, int viewType);
    public abstract void OnBindViewHolder(RecyclerView.ViewHolder holder, int position);

    public int GetItemViewType(int position){
        return super.getItemViewType(position);
    }

    private class EmptyViewHolder<T> extends RecyclerView.ViewHolder{

        public EmptyViewHolder(View itemView) {
            super(itemView);
        }
    }

    @Override
    public void addItemDates(List collection) {
        if (collection!=null&&collection.size()>0){
            mDateList.addAll(collection);
        }
    }

    @Override
    public void clear() {
        mDateList.clear();
    }

    @Override
    public List<T> getAllList() {
        return mDateList;
    }
}
