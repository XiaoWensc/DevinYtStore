package zxw.cn.com.rrxjava.ui.classify;

import java.util.List;

/**
 * Created by zengxiaowen on 2018/1/30.
 */

public class ClassifyEntity {
    private String sessionConfirmationNumber;

    private String fastdfsDomain ;

    private List<Brands> categoryGroups;

    public class Brands {
        private String description;
        private String displayName;
        private String promoImageUrl;
        private String mobilePromoImageUrl;// 广告图片地址
        private String mobilePromoUrl;//点击图片跳转地址
        private List<Categories> subCategories;

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public String getDisplayName() {
            return displayName;
        }

        public void setDisplayName(String displayName) {
            this.displayName = displayName;
        }

        public String getPromoImageUrl() {
            return promoImageUrl;
        }

        public void setPromoImageUrl(String promoImageUrl) {
            this.promoImageUrl = promoImageUrl;
        }

        public String getMobilePromoImageUrl() {
            return mobilePromoImageUrl;
        }

        public void setMobilePromoImageUrl(String mobilePromoImageUrl) {
            this.mobilePromoImageUrl = mobilePromoImageUrl;
        }

        public String getMobilePromoUrl() {
            return mobilePromoUrl;
        }

        public void setMobilePromoUrl(String mobilePromoUrl) {
            this.mobilePromoUrl = mobilePromoUrl;
        }

        public List<Categories> getSubCategories() {
            return subCategories;
        }

        public void setSubCategories(List<Categories> subCategories) {
            this.subCategories = subCategories;
        }
    }

    public static class Categories{

        private int ViewType=2; //  //头部 0  ， 标题 1  ，文字图片2

        private String mobilePromoImageUrl;
        private String mobilePromoUrl;

        private String titleName;

        private String categoryId;
        private String categoryName;
        private String dimensionId;
        private String imgUrl;
        private List<Categories> subCategories;
        Categories categories ;
        //是否是空白
        private boolean isBlank;			//是否空白

        @Override
        public String toString() {
            return "Categories{" +
                    "ViewType=" + ViewType +
                    ", mobilePromoImageUrl='" + mobilePromoImageUrl + '\'' +
                    ", mobilePromoUrl='" + mobilePromoUrl + '\'' +
                    ", titleName='" + titleName + '\'' +
                    ", categoryId='" + categoryId + '\'' +
                    ", categoryName='" + categoryName + '\'' +
                    ", dimensionId='" + dimensionId + '\'' +
                    ", imgUrl='" + imgUrl + '\'' +
                    ", subCategories=" + subCategories +
                    ", categories=" + categories +
                    ", isBlank=" + isBlank +
                    '}';
        }

        public String getMobilePromoImageUrl() {
            return mobilePromoImageUrl;
        }

        public void setMobilePromoImageUrl(String mobilePromoImageUrl) {
            this.mobilePromoImageUrl = mobilePromoImageUrl;
        }

        public String getMobilePromoUrl() {
            return mobilePromoUrl;
        }

        public void setMobilePromoUrl(String mobilePromoUrl) {
            this.mobilePromoUrl = mobilePromoUrl;
        }

        public String getTitleName() {
            return titleName;
        }

        public void setTitleName(String titleName) {
            this.titleName = titleName;
        }

        public int getViewType() {
            return ViewType;
        }

        public void setViewType(int viewType) {
            ViewType = viewType;
        }

        public String getCategoryId() {
            return categoryId;
        }

        public void setCategoryId(String categoryId) {
            this.categoryId = categoryId;
        }

        public String getCategoryName() {
            return categoryName;
        }

        public void setCategoryName(String categoryName) {
            this.categoryName = categoryName;
        }

        public String getDimensionId() {
            return dimensionId;
        }

        public void setDimensionId(String dimensionId) {
            this.dimensionId = dimensionId;
        }

        public String getImgUrl() {
            return imgUrl;
        }

        public void setImgUrl(String imgUrl) {
            this.imgUrl = imgUrl;
        }

        public List<Categories> getSubCategories() {
            return subCategories;
        }

        public void setSubCategories(List<Categories> subCategories) {
            this.subCategories = subCategories;
        }

        public Categories getCategories() {
            return categories;
        }

        public void setCategories(Categories categories) {
            this.categories = categories;
        }

        public boolean isBlank() {
            return isBlank;
        }

        public void setBlank(boolean blank) {
            isBlank = blank;
        }
    }

    public String getSessionConfirmationNumber() {
        return sessionConfirmationNumber;
    }

    public void setSessionConfirmationNumber(String sessionConfirmationNumber) {
        this.sessionConfirmationNumber = sessionConfirmationNumber;
    }

    public String getFastdfsDomain() {
        return fastdfsDomain;
    }

    public void setFastdfsDomain(String fastdfsDomain) {
        this.fastdfsDomain = fastdfsDomain;
    }

    public List<Brands> getCategoryGroups() {
        return categoryGroups;
    }

    public void setCategoryGroups(List<Brands> categoryGroups) {
        this.categoryGroups = categoryGroups;
    }
}
