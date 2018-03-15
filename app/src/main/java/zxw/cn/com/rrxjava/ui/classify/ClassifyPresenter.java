package zxw.cn.com.rrxjava.ui.classify;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import devin.cn.com.rxjavaretrofit.exception.ApiException;
import devin.cn.com.rxjavaretrofit.mvp.BasePresenter;
import devin.cn.com.rxjavaretrofit.mvp.IPresenter;

/**
 * Created by zengxiaowen on 2018/1/30.
 */

public class ClassifyPresenter extends BasePresenter<ClassifyFragment> implements IPresenter<TreeMap<String,List<ClassifyEntity.Categories>>>{

    private ClassifyEntity entity;

    public ClassifyPresenter(ClassifyFragment activity) {
        super(activity);
    }

    @Override
    protected void initData(Map<String,Object> map) {
        api.postApi("rest/model/com/yatang/search/CategorySearchActor/categoryGroupActor","categoryGroupActor");
    }

    @Override
    protected void onNexts(String resulte, String method) {
        entity = JSONObject.parseObject(resulte,new TypeReference<ClassifyEntity>(){});
        mView.refresh();
    }

    @Override
    protected void onErrors(ApiException e, String method) {
        mView.showToast(e.getMessage());
        mView.onEmpty();
    }

    @Override
    public TreeMap<String,List<ClassifyEntity.Categories>> getData() {
        return getDataList(entity);
    }

    private TreeMap<String,List<ClassifyEntity.Categories>> getDataList(ClassifyEntity entity){
        TreeMap<String,List<ClassifyEntity.Categories>> map = new TreeMap<>();
        for (int i=0;i<entity.getCategoryGroups().size();i++){
            ClassifyEntity.Brands brands = entity.getCategoryGroups().get(i);
            map.put(brands.getDisplayName(),getList(brands,entity.getFastdfsDomain()));
        }
        return map;
    }

    private List<ClassifyEntity.Categories> getList(ClassifyEntity.Brands brands,String fastdfsDomain){
        List<ClassifyEntity.Categories> list = new ArrayList<>();
        ClassifyEntity.Categories categories = new ClassifyEntity.Categories();
        categories.setMobilePromoImageUrl(fastdfsDomain+brands.getMobilePromoImageUrl());
        categories.setMobilePromoUrl(brands.getMobilePromoUrl());
        categories.setViewType(0);
        if (brands.getMobilePromoImageUrl()!=null) list.add(categories);
        for (int i=0;i<brands.getSubCategories().size();i++){
            categories = new ClassifyEntity.Categories();
            categories.setViewType(1);
            categories.setTitleName(brands.getSubCategories().get(i).getCategoryName());
            list.add(categories);
            list.addAll(brands.getSubCategories().get(i).getSubCategories());
        }
        return list;
    }
}
