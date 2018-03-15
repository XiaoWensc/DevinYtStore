package zxw.cn.com.rrxjava.ui.account;

import devin.cn.com.rxjavaretrofit.mvp.BaseActivity;

/**
 * 我的账户
 * Created by zengxiaowen on 2018/2/1.
 */

public class AccountModel {

    private int ViewType ;

    private HreadView hreadView;
    private OrderView orderView;
    private ItemView itemView;

    public int getViewType() {
        return ViewType;
    }

    public void setViewType(int viewType) {
        ViewType = viewType;
    }

    public HreadView getHreadView() {
        return hreadView;
    }

    public void setHreadView(HreadView hreadView) {
        this.hreadView = hreadView;
    }

    public OrderView getOrderView() {
        return orderView;
    }

    public void setOrderView(OrderView orderView) {
        this.orderView = orderView;
    }

    public ItemView getItemView() {
        return itemView;
    }

    public void setItemView(ItemView itemView) {
        this.itemView = itemView;
    }

    public static class HreadView{
        private String mobile; //手机号
        private String nickName; // 昵称
        private boolean isVip ;  //是否是超级会员

        public HreadView(String mobile, String nickName, boolean isVip) {
            this.mobile = mobile;
            this.nickName = nickName;
            this.isVip = isVip;
        }

        public boolean isVip() {
            return isVip;
        }

        public void setVip(boolean vip) {
            isVip = vip;
        }

        public String getMobile() {
            return mobile;
        }

        public void setMobile(String mobile) {
            this.mobile = mobile;
        }

        public String getNickName() {
            return nickName;
        }

        public void setNickName(String nickName) {
            this.nickName = nickName;
        }
    }

    public static class OrderView {
        private String noEvaluate; //待评价
        private String noReceive; //待收货
        private String noPayment;  //待付款
        private String noRepair; //退换/返修

        public OrderView(String noEvaluate, String noReceive, String noPayment, String noRepair) {
            this.noEvaluate = noEvaluate;
            this.noReceive = noReceive;
            this.noPayment = noPayment;
            this.noRepair = noRepair;
        }

        public String getNoEvaluate() {
            return noEvaluate;
        }

        public void setNoEvaluate(String noEvaluate) {
            this.noEvaluate = noEvaluate;
        }

        public String getNoReceive() {
            return noReceive;
        }

        public void setNoReceive(String noReceive) {
            this.noReceive = noReceive;
        }

        public String getNoPayment() {
            return noPayment;
        }

        public void setNoPayment(String noPayment) {
            this.noPayment = noPayment;
        }

        public String getNoRepair() {
            return noRepair;
        }

        public void setNoRepair(String noRepair) {
            this.noRepair = noRepair;
        }
    }

    public static class ItemView {
        private int drawable;
        private String title;

        public ItemView(int drawable, String title) {
            this.drawable = drawable;
            this.title = title;
        }

        public int getDrawable() {
            return drawable;
        }

        public void setDrawable(int drawable) {
            this.drawable = drawable;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

    }
}
