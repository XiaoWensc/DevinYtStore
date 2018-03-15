package zxw.cn.com.rrxjava.base;

import java.util.Collection;
import java.util.List;

/**
 * Created by zengxiaowen on 2018/1/30.
 */

public interface IBaseRecylerView<T> {
    void addItemDates(List<T> collection);
    void clear();
    List<Object> getAllList();
}
