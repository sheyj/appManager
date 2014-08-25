package com.syj.app.web.model;

import java.io.Serializable;
import java.util.Date;

import org.codehaus.jackson.map.annotate.JsonSerialize;

import com.syj.app.util.JsonDateSerializer$19;

public class GroupUserApply implements Serializable{
	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 3550255912955112122L;

	private Long id;

	private Long groupId;

	private Long masterId;

	private String groupName;
	//申请类型 0 群主邀请，1个人申请
	private Short applyType;

	private Long userId;
	private String userName;

	private String userAccount;

	private String userImage;
	@JsonSerialize(using = JsonDateSerializer$19.class)
	private Date createTime;

	private String remark;
	//状态 0申请中1审核通过2审核拒绝
	private Short status = 0;
	@JsonSerialize(using = JsonDateSerializer$19.class)
	private Date updateTime;

	private String groupRemark;
	//群组图片
	private String groupImage;

	//经度
	private Double longitude;
	//维度
	private Double latitude;
	
	private String groupAccount;
	
	
	public String getGroupAccount() {
		return groupAccount;
	}

	public void setGroupAccount(String groupAccount) {
		this.groupAccount = groupAccount;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getGroupId() {
		return groupId;
	}

	public void setGroupId(Long groupId) {
		this.groupId = groupId;
	}

	public String getGroupName() {
		return groupName;
	}

	public void setGroupName(String groupName) {
		this.groupName = groupName == null ? null : groupName.trim();
	}

	public Short getApplyType() {
		return applyType;
	}

	public void setApplyType(Short applyType) {
		this.applyType = applyType;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName == null ? null : userName.trim();
	}

	public String getUserAccount() {
		return userAccount;
	}

	public void setUserAccount(String userAccount) {
		this.userAccount = userAccount == null ? null : userAccount.trim();
	}

	public String getUserImage() {
		return userImage;
	}

	/**
	 * This method was generated by MyBatis Generator.
	 * This method sets the value of the database column group_user_apply.user_image
	 *
	 * @param userImage the value for group_user_apply.user_image
	 *
	 * @mbggenerated
	 */
	public void setUserImage(String userImage) {
		this.userImage = userImage == null ? null : userImage.trim();
	}

	/**
	 * This method was generated by MyBatis Generator.
	 * This method returns the value of the database column group_user_apply.create_time
	 *
	 * @return the value of group_user_apply.create_time
	 *
	 * @mbggenerated
	 */
	public Date getCreateTime() {
		return createTime;
	}

	/**
	 * This method was generated by MyBatis Generator.
	 * This method sets the value of the database column group_user_apply.create_time
	 *
	 * @param createTime the value for group_user_apply.create_time
	 *
	 * @mbggenerated
	 */
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	/**
	 * This method was generated by MyBatis Generator.
	 * This method returns the value of the database column group_user_apply.remark
	 *
	 * @return the value of group_user_apply.remark
	 *
	 * @mbggenerated
	 */
	public String getRemark() {
		return remark;
	}

	/**
	 * This method was generated by MyBatis Generator.
	 * This method sets the value of the database column group_user_apply.remark
	 *
	 * @param remark the value for group_user_apply.remark
	 *
	 * @mbggenerated
	 */
	public void setRemark(String remark) {
		this.remark = remark == null ? null : remark.trim();
	}

	/**
	 * This method was generated by MyBatis Generator.
	 * This method returns the value of the database column group_user_apply.status
	 *
	 * @return the value of group_user_apply.status
	 *
	 * @mbggenerated
	 */
	public Short getStatus() {
		return status;
	}

	/**
	 * This method was generated by MyBatis Generator.
	 * This method sets the value of the database column group_user_apply.status
	 *
	 * @param status the value for group_user_apply.status
	 *
	 * @mbggenerated
	 */
	public void setStatus(Short status) {
		this.status = status;
	}

	/**
	 * This method was generated by MyBatis Generator.
	 * This method returns the value of the database column group_user_apply.update_time
	 *
	 * @return the value of group_user_apply.update_time
	 *
	 * @mbggenerated
	 */
	public Date getUpdateTime() {
		return updateTime;
	}

	/**
	 * This method was generated by MyBatis Generator.
	 * This method sets the value of the database column group_user_apply.update_time
	 *
	 * @param updateTime the value for group_user_apply.update_time
	 *
	 * @mbggenerated
	 */
	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	public Long getMasterId() {
		return masterId;
	}

	public void setMasterId(Long masterId) {
		this.masterId = masterId;
	}

	public String getGroupRemark() {
		return groupRemark;
	}

	public void setGroupRemark(String groupRemark) {
		this.groupRemark = groupRemark;
	}

	public String getGroupImage() {
		return groupImage;
	}

	public void setGroupImage(String groupImage) {
		this.groupImage = groupImage;
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
	
	
	
}