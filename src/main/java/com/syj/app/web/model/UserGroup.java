package com.syj.app.web.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.codehaus.jackson.map.annotate.JsonSerialize;

import com.syj.app.util.JsonDateSerializer$19;

public class UserGroup implements Serializable {
	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = -8753942313402362078L;

	private Long id;

	private String groupName;

	private String groupAccount;

	private Long masterId;
	@JsonSerialize(using = JsonDateSerializer$19.class)
	private Date createTime;

	private String location;

	//群组位置
	private double[] groupPosition;

	private Short status;

	private Float positionX;

	private Float positionY;

	//群组图片
	private String groupImage;

	private List<GroupUser> groupUserList = new ArrayList<GroupUser>();

	//查询用户数量 默认10
	private int queryNum = 10;

	//查询最大距离
	private int maxDistance;
	//群组备注
	private String remark;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getGroupName() {
		return groupName;
	}

	public void setGroupName(String groupName) {
		this.groupName = groupName == null ? null : groupName.trim();
	}

	public String getGroupAccount() {
		return groupAccount;
	}

	public void setGroupAccount(String groupAccount) {
		this.groupAccount = groupAccount == null ? null : groupAccount.trim();
	}

	public Long getMasterId() {
		return masterId;
	}

	public void setMasterId(Long masterId) {
		this.masterId = masterId;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location == null ? null : location.trim();
	}

	public Short getStatus() {
		return status;
	}

	public void setStatus(Short status) {
		this.status = status;
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

	public List<GroupUser> getGroupUserList() {
		return groupUserList;
	}

	public void setGroupUserList(List<GroupUser> groupUserList) {
		this.groupUserList = groupUserList;
	}

	public double[] getGroupPosition() {
		return groupPosition;
	}

	public void setGroupPosition(double[] groupPosition) {
		this.groupPosition = groupPosition;
	}

	public String getGroupImage() {
		return groupImage;
	}

	public void setGroupImage(String groupImage) {
		this.groupImage = groupImage;
	}

	public int getQueryNum() {
		return queryNum;
	}

	public void setQueryNum(int queryNum) {
		this.queryNum = queryNum;
	}

	public int getMaxDistance() {
		return maxDistance;
	}

	public void setMaxDistance(int maxDistance) {
		this.maxDistance = maxDistance;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

}