package devin.cn.com.rxjavaretrofit.downlaod.DownLoadListener;


/**
 * 成功回调处理
 * Created by zengxiaowen on 2018/1/19.
 */
public interface DownloadProgressListener {
    /**
     * 下载进度
     * @param read
     * @param count
     * @param done
     */
    void update(long read, long count, boolean done);
}
