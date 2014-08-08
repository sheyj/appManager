package com.syj.app.web.model;

import java.io.Serializable;
import java.util.Date;

import org.codehaus.jackson.map.annotate.JsonSerialize;

import com.syj.app.util.JsonDateSerializer$19;

/**
 * 用户位置信息
 * 
 * @author she.yj
 * @date 2014-7-29 下午5:11:49
 * @version 0.1.0 
 * @copyright syj.com 
 */
public class UserLocation implements Serializable {

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 2540497973002739110L;
	//用户ID
	private Long id;

	//经度
	private double longitude;
	//维度
	private double latitude;

	private double[] position;

	//创建时间
	@JsonSerialize(using = JsonDateSerializer$19.class)
	private Date createTime;
	//查询用户数量 默认10
	private int queryNum = 10;
	
	//查询最大距离
	private int maxDistance;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public double getLongitude() {
		return longitude;
	}

	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}

	public double getLatitude() {
		return latitude;
	}

	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public double[] getPosition() {
		return position;
	}

	public void setPosition(double[] position) {
		this.position = position;
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

	
}
