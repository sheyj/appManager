package com.syj.app.web.model;

import java.io.Serializable;
import java.util.Date;

import org.codehaus.jackson.map.annotate.JsonSerialize;

import com.syj.app.util.JsonDateSerializer$19;

public class UserFriend implements Serializable {
	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 1635260617988494886L;

	private Long id;

	private Long userId;

	private String userName;

	private Long friendUserId;
	@JsonSerialize(using = JsonDateSerializer$19.class)
	private Date createTime;

	private String friendUserName;

	private Short status;

	private String remark;
	
	
	private String friendsUserAccount;
	private String friendsUserImage;
	private String friendsUserRemark;
	private Float positionX;
	private Float positionY;
	
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

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

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

	public Long getFriendUserId() {
		return friendUserId;
	}

	public void setFriendUserId(Long friendUserId) {
		this.friendUserId = friendUserId;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public String getFriendUserName() {
		return friendUserName;
	}

	public void setFriendUserName(String friendUserName) {
		this.friendUserName = friendUserName == null ? null : friendUserName.trim();
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

	public String getFriendsUserAccount() {
		return friendsUserAccount;
	}

	public void setFriendsUserAccount(String friendsUserAccount) {
		this.friendsUserAccount = friendsUserAccount;
	}

	public String getFriendsUserImage() {
		return friendsUserImage;
	}

	public void setFriendsUserImage(String friendsUserImage) {
		this.friendsUserImage = friendsUserImage;
	}

	public String getFriendsUserRemark() {
		return friendsUserRemark;
	}

	public void setFriendsUserRemark(String friendsUserRemark) {
		this.friendsUserRemark = friendsUserRemark;
	}
	
	
}