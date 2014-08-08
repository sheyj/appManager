package com.syj.app.web.vo;

import java.io.Serializable;

/**
 * 用户信息
 * 
 * @author she.yj
 * @date 2014-7-31 上午10:03:12
 * @version 0.1.0 
 * @copyright syj.com 
 */
public class UserInfo implements Serializable {

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = -4057816647311523745L;

	//用户ID 
	private Long id;

	//用户账号
	private String userAccount;
	//用户名称
	private String userName;
	//用户头像
	private String userImage;
	//用户签名
	private String remark;
	//经度
	private Double longitude;
	//维度
	private Double latitude;
	//距离 单位米
	private Long distance;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUserAccount() {
		return userAccount;
	}

	public void setUserAccount(String userAccount) {
		this.userAccount = userAccount;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getUserImage() {
		return userImage;
	}

	public void setUserImage(String userImage) {
		this.userImage = userImage;
	}

	public Double getLongitude() {
		return longitude;
	}

	public void setLongitude(Double longitude) {
		this.longitude = longitude;
	}

	public Double getLatitude() {
		return latitude;
	}

	public void setLatitude(Double latitude) {
		this.latitude = latitude;
	}

	public Long getDistance() {
		return distance;
	}

	public void setDistance(Long distance) {
		this.distance = distance;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	
}
