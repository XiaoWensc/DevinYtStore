package devin.cn.com.rxjavaretrofit.listener.upload;

/**
 * 上传进度回调类
 * Created by zengxiaowen on 2018/1/19.
 */

public interface UploadProgressListener {
    /**
     * 上传进度
     * 手动回调到主线程中
     * @param currentBytesCount
     * @param totalBytesCount
     */
    void onProgress(long currentBytesCount, long totalBytesCount);
}