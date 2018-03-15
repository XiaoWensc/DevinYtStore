package zxw.cn.com.rrxjava.ui.find;

import java.util.List;

/**
 * Created by zengxiaowen on 2018/2/1.
 */

public class FindBean {

    private int pageNum;
    private int pageSize;
    private int totalPage;
    private String total;
    private List<FindInfo> rows;

    public int getPageNum() {
        return pageNum;
    }

    public void setPageNum(int pageNum) {
        this.pageNum = pageNum;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public int getTotalPage() {
        return totalPage;
    }

    public void setTotalPage(int totalPage) {
        this.totalPage = totalPage;
    }

    public String getTotal() {
        return total;
    }

    public void setTotal(String total) {
        this.total = total;
    }

    public List<FindInfo> getRows() {
        return rows;
    }

    public void setRows(List<FindInfo> rows) {
        this.rows = rows;
    }

    public class FindInfo {
        private String activityDesc;//活动描述
        private String activityName;//活动名称
        private int activityStatus;
        private String endTime;//活动结束时间
        //private String id;//活动ID
        private String imageUrl;
        private String linkUrl;//活动链接地址
        private String orderNum;//推荐信息（大于0的都是推荐活动值越大排在越前面）
        private String startTime;//活动开始时间

        public String getActivityDesc() {
            return activityDesc;
        }

        public void setActivityDesc(String activityDesc) {
            this.activityDesc = activityDesc;
        }

        public String getActivityName() {
            return activityName;
        }

        public void setActivityName(String activityName) {
            this.activityName = activityName;
        }

        public int getActivityStatus() {
            return activityStatus;
        }

        public void setActivityStatus(int activityStatus) {
            this.activityStatus = activityStatus;
        }

        public String getEndTime() {
            return endTime;
        }

        public void setEndTime(String endTime) {
            this.endTime = endTime;
        }

        public String getImageUrl() {
            return imageUrl;
        }

        public void setImageUrl(String imageUrl) {
            this.imageUrl = imageUrl;
        }

        public String getLinkUrl() {
            return linkUrl;
        }

        public void setLinkUrl(String linkUrl) {
            this.linkUrl = linkUrl;
        }

        public String getOrderNum() {
            return orderNum;
        }

        public void setOrderNum(String orderNum) {
            this.orderNum = orderNum;
        }

        public String getStartTime() {
            return startTime;
        }

        public void setStartTime(String startTime) {
            this.startTime = startTime;
        }
    }
}
