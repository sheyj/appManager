package com.syj.app.web.vo;

import java.io.Serializable;
import java.util.Date;

import org.codehaus.jackson.map.annotate.JsonSerialize;

import com.syj.app.util.JsonDateSerializer$19;

/**
 * 群组信息
 * 
 * @author she.yj
 * @date 2014-7-31 上午10:03:12
 * @version 0.1.0 
 * @copyright syj.com 
 */
public class GroupInfo implements Serializable {

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = -4057816647311523745L;

	//群组ID 
	private Long id;

	//群组名称
	private String groupName;
	
	private String groupAccount;
	
	//群组图片
	private String groupImage;
	//群组备注
	private String remark;
	//经度
	private Double longitude;
	//维度
	private Double latitude;
	//距离 单位米
	private Long distance;
	//群主ID
	private Long masterId;
	//创建时间
	@JsonSerialize(using = JsonDateSerializer$19.class)
	private Date createTime;
	
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
		this.groupName = groupName;
	}

	public String getGroupImage() {
		return groupImage;
	}

	public void setGroupImage(String groupImage) {
		this.groupImage = groupImage;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
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

	public String getGroupAccount() {
		return groupAccount;
	}

	public void setGroupAccount(String groupAccount) {
		this.groupAccount = groupAccount;
	}

	
}
