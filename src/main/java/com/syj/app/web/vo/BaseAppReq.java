package com.syj.app.web.vo;

import java.io.Serializable;

/**
 * APP请求参数基类
 * 
 * @author she.yj
 * @date 2014-5-23 下午4:58:11
 * @version 0.1.0 
 * @copyright syj.com 
 */
public class BaseAppReq implements Serializable {

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = -9215297324609923419L;

	//当前页数
	private String pageNo;
	//每页数量
	private String pageSize;
	//用户ID
	private String userId;
	
    //设备ID
	private String deviceId;
	 
	public String getPageNo() {
		return pageNo;
	}

	public void setPageNo(String pageNo) {
		this.pageNo = pageNo;
	}

	public String getPageSize() {
		return pageSize;
	}

	public void setPageSize(String pageSize) {
		this.pageSize = pageSize;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getDeviceId() {
		return deviceId;
	}

	public void setDeviceId(String deviceId) {
		this.deviceId = deviceId;
	}
	
	
}
