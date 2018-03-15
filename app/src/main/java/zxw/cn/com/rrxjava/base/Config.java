package zxw.cn.com.rrxjava.base;

/**
 * Created by zengxiaowen on 2018/1/29.
 */

public class Config {

    public static String _dynSessConf ;

    public static class UserInfo {
        String userName;  //用户名
        public static String mobile;  //手机号
        CityEntity location;  //定位城市
        String shopId;  //定位城市店铺
        public static boolean isLogin ; // 是否登录
        public static boolean isVip ;
        public static String superMemberURL ; //开通超级会员链接
    }

    public class CityEntity{
        private String code ;
        private String displayer;
        private String id;
        private String name;
        private String paretCode;
        private String pingyin;

        public String getCode() {
            return code;
        }

        public void setCode(String code) {
            this.code = code;
        }

        public String getDisplayer() {
            return displayer;
        }

        public void setDisplayer(String displayer) {
            this.displayer = displayer;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getParetCode() {
            return paretCode;
        }

        public void setParetCode(String paretCode) {
            this.paretCode = paretCode;
        }

        public String getPingyin() {
            return pingyin;
        }

        public void setPingyin(String pingyin) {
            this.pingyin = pingyin;
        }
    }
}
