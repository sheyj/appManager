package com.syj.app.web.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.codehaus.jackson.map.annotate.JsonSerialize;

import com.syj.app.util.JsonDateSerializer$19;

public class UserTalk implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 7257363485422219055L;

	private Long id;

	private Long userId;

	private String userName;

	private String userAccount;
	
	
	private String imagePath;
	//图片文件
    private List<AppFile> imageList = new ArrayList<AppFile>();
	
    private String talkContent;

	private Short talkType;

	private String location;

	@JsonSerialize(using = JsonDateSerializer$19.class)
	private Date createTime;

	private Short status;

	private String statusName;

	private List<TalkResponse> talkResList = new ArrayList<TalkResponse>();

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public String getImagePath() {
		return imagePath;
	}

	public void setImagePath(String imagePath) {
		this.imagePath = imagePath == null ? null : imagePath.trim();
	}

	public String getTalkContent() {
		return talkContent;
	}

	public void setTalkContent(String talkContent) {
		this.talkContent = talkContent == null ? null : talkContent.trim();
	}

	public Short getTalkType() {
		return talkType;
	}

	public void setTalkType(Short talkType) {
		this.talkType = talkType;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location == null ? null : location.trim();
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Short getStatus() {
		return status;
	}

	public void setStatus(Short status) {
		this.status = status;
	}

	public List<TalkResponse> getTalkResList() {
		return talkResList;
	}

	public void setTalkResList(List<TalkResponse> talkResList) {
		this.talkResList = talkResList;
	}

	public String getStatusName() {
		String statusNameTemp = "";
		if (null != status) {
			if (status.intValue() == 0) {
				statusNameTemp = "不可见";
			} else {
				statusNameTemp = "可见";
			}
		}
		return statusNameTemp;
	}

	public void setStatusName(String statusName) {
		this.statusName = statusName;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public List<AppFile> getImageList() {
		return imageList;
	}

	public void setImageList(List<AppFile> imageList) {
		this.imageList = imageList;
	}

	public String getUserAccount() {
		return userAccount;
	}

	public void setUserAccount(String userAccount) {
		this.userAccount = userAccount;
	}

	
}