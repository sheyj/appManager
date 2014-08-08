package com.syj.app.web.model;

import java.io.Serializable;
import java.util.Date;

import org.codehaus.jackson.map.annotate.JsonSerialize;

import com.syj.app.util.JsonDateSerializer$10;
import com.syj.app.util.JsonDateSerializer$19;

public class AppUser implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -551258863870987029L;

	private Long id;

	private String userAccount;

	private String userName;

	private String userPassword;

	//性别  0 男 1 女
	private Short userSex;

	private String userMobile;

	@JsonSerialize(using = JsonDateSerializer$10.class)
	private Date userBirthday;

	private String userBirthdayStr;
	
	private String userAddress;

	private String userImage;
   
	//用户头像图标
	//private AppFile appFile;
	
	@JsonSerialize(using = JsonDateSerializer$19.class)
	private Date registerTime;

	private String androidVersion;

	private Short status;

	private String remark;

	@JsonSerialize(using = JsonDateSerializer$19.class)
	private Date lastLoginTime;

	private String location;

	private Float positionX;

	private Float positionY;

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
		this.userAccount = userAccount == null ? null : userAccount.trim();
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName == null ? null : userName.trim();
	}

	public String getUserPassword() {
		return userPassword;
	}

	public void setUserPassword(String userPassword) {
		this.userPassword = userPassword == null ? null : userPassword.trim();
	}

	public String getUserMobile() {
		return userMobile;
	}

	public void setUserMobile(String userMobile) {
		this.userMobile = userMobile == null ? null : userMobile.trim();
	}

	public Date getUserBirthday() {
		return userBirthday;
	}

	public void setUserBirthday(Date userBirthday) {
		this.userBirthday = userBirthday;
	}

	public String getUserAddress() {
		return userAddress;
	}

	public void setUserAddress(String userAddress) {
		this.userAddress = userAddress == null ? null : userAddress.trim();
	}

	public String getUserImage() {
		return userImage;
	}

	public void setUserImage(String userImage) {
		this.userImage = userImage == null ? null : userImage.trim();
	}

	public Date getRegisterTime() {
		return registerTime;
	}

	public void setRegisterTime(Date registerTime) {
		this.registerTime = registerTime;
	}

	public String getAndroidVersion() {
		return androidVersion;
	}

	public void setAndroidVersion(String androidVersion) {
		this.androidVersion = androidVersion == null ? null : androidVersion.trim();
	}

	public Short getStatus() {
		return status;
	}

	public void setStatus(Short status) {
		this.status = status;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark == null ? null : remark.trim();
	}

	public Date getLastLoginTime() {
		return lastLoginTime;
	}

	public void setLastLoginTime(Date lastLoginTime) {
		this.lastLoginTime = lastLoginTime;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location == null ? null : location.trim();
	}

	public Float getPositionX() {
		return positionX;
	}

	public void setPositionX(Float positionX) {
		this.positionX = positionX;
	}

	public Float getPositionY() {
		return positionY;
	}

	public void setPositionY(Float positionY) {
		this.positionY = positionY;
	}

	public Short getUserSex() {
		return userSex;
	}

	public void setUserSex(Short userSex) {
		this.userSex = userSex;
	}

	public String getUserBirthdayStr() {
		return userBirthdayStr;
	}

	public void setUserBirthdayStr(String userBirthdayStr) {
		this.userBirthdayStr = userBirthdayStr;
	}

//	public AppFile getAppFile() {
//		return appFile;
//	}
//
//	public void setAppFile(AppFile appFile) {
//		this.appFile = appFile;
//	}

}