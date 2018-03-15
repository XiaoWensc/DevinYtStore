package zxw.cn.com.rrxjava.entity;

/**
 * 测试数据
 * Created by zengxiaowen on 2018/1/19.
 */

public class SubjectResulte {
    private int provinceId;
    private int cityId;
    private int countyId;
    private String county;
    private String province;
    private String city;

    @Override
    public String toString() {
        return "SubjectResulte{" +
                "provinceId=" + provinceId +
                ", cityId=" + cityId +
                ", countyId=" + countyId +
                ", county='" + county + '\'' +
                ", province='" + province + '\'' +
                ", city='" + city + '\'' +
                '}';
    }

    public int getProvinceId() {
        return provinceId;
    }

    public void setProvinceId(int provinceId) {
        this.provinceId = provinceId;
    }

    public int getCityId() {
        return cityId;
    }

    public void setCityId(int cityId) {
        this.cityId = cityId;
    }

    public int getCountyId() {
        return countyId;
    }

    public void setCountyId(int countyId) {
        this.countyId = countyId;
    }

    public String getCounty() {
        return county;
    }

    public void setCounty(String county) {
        this.county = county;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }
}
