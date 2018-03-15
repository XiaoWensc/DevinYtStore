package devin.cn.com.rxjavaretrofit.mvp;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import java.io.Serializable;
import java.util.ArrayList;
import devin.cn.com.rxjavaretrofit.R;
import devin.cn.com.rxjavaretrofit.data.DTO;

/**
 * 基本的操作共通抽取
 * 
 * @author 曾晓文
 * @version 1.0
 * 
 */
public class Operation {

	/** 激活Activity组件意图 **/
	private Intent mIntent = new Intent();
	/*** 上下文 **/
	private Activity mContext = null;
	/*** 整个应用Applicaiton **/
	private Context mApplication = null;

	/***Activity之间数据传输数据对象Key**/
	public static final String ACTIVITY_DTO_KEY = "ACTIVITY_DTO_KEY";

	public static final int REQUESTCODE = 101;
	
	public Operation(Activity mContext) {
		this.mContext = mContext;
		mApplication = this.mContext.getApplicationContext();
	}

	public void clones(){
		mIntent.clone();
	}

	/**
	 * 跳转Activity
	 * 
	 * @param activity
	 *            需要跳转至的Activity
	 */
	public void forward(Class<? extends Activity> activity) {
		mIntent.setClass(mContext, activity);
		mContext.startActivity(mIntent);
//		mContext.overridePendingTransition(R.anim.base_slide_right_in, R.anim.base_slide_remain);
	}

	/**
	 * 跳转ActivityForResult
	 * 
	 * @param activity
	 *            需要跳转至的Activity
	 * @param requestCode
	 *            请求Code
	 */
	public void forwardForResult(Class<? extends Activity> activity,
			int requestCode) {
		mIntent.setClass(mContext, activity);
		mContext.startActivityForResult(mIntent, requestCode);
		mContext.overridePendingTransition(R.anim.base_slide_right_in,
				R.anim.base_slide_remain);
	}

	/**
	 * 设置传递参数
	 * 
	 * @param value
	 *            数据传输对象
	 */
	public void addParameter(DTO value) {
		mIntent.putExtra(ACTIVITY_DTO_KEY, value);
	}

	/**
	 * 设置传递参数
	 * 
	 * @param key
	 *            参数key
	 * @param value
	 *            数据传输对象
	 */
	public void addParameter(String key, DTO value) {
		mIntent.putExtra(key, value);
	}
	
	/**
	 * 设置传递参数
	 * @param key
	 * @param value
	 */
	public void addParameter(String key, ArrayList<String> value) {
		mIntent.putExtra(key, value);
	}

	/**
	 * 设置传递参数
	 * 
	 * @param key
	 *            参数key
	 * @param value
	 *            数据传输对象
	 */
	public void addParameter(String key, Parcelable value) {
		mIntent.putExtra(key, value);
	}

	/**
	 * 设置传递参数
	 * 
	 * @param key
	 *            参数key
	 * @param value
	 *            数据传输对象
	 */
	public void addParameter(String key, Bundle value) {
		mIntent.putExtra(key, value);
	}

	/**
	 * 设置传递参数
	 * 
	 * @param key
	 *            参数key
	 * @param value
	 *            数据传输对象
	 */
	public void addParameter(String key, Serializable value) {
		mIntent.putExtra(key, value);
	}
	
	/**
	 * 设置传递参数
	 * 
	 * @param key
	 *            参数key
	 * @param value
	 *            数据传输对象
	 */
	public void addParameter(String key, int value) {
		mIntent.putExtra(key, value);
	}
	
	/**
	 * 设置传递参数
	 * 
	 * @param key
	 *            参数key
	 * @param value
	 *            数据传输对象
	 */
	public void addParameter(String key, Boolean value) {
		mIntent.putExtra(key, value);
	}

	/**
	 * 设置传递参数
	 * 
	 * @param key
	 *            参数key
	 * @param value
	 *            数据传输对象
	 */
	public void addParameter(String key, String value) {
		mIntent.putExtra(key, value);
	}

	/**
	 * 获取跳转时设置的参数
	 * 
	 * @param key
	 * @return
	 */
	public Object getParameters(String key) {
		DTO parms = getParameters();
		if (null != parms) {
			return parms.get(key);
		} else {
			parms = new DTO();
			parms.put(key, mContext.getIntent().getExtras().get(key));
		}
		return parms;
	}

	/**
	 * 获取跳转参数集合
	 * 
	 * @return
	 */
	public DTO getParameters() {
		DTO parms = (DTO) mContext.getIntent().getExtras()
				.getSerializable(ACTIVITY_DTO_KEY);
		return parms;
	}
}
