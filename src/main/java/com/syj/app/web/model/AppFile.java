package com.syj.app.web.model;

import java.io.Serializable;
import java.util.Date;

import org.codehaus.jackson.map.annotate.JsonSerialize;

import com.syj.app.util.JsonDateSerializer$19;

/**
 * APP图片记录表
 * TODO: 增加描述
 * 
 * @author username
 * @date 2014-7-23 上午11:15:27
 * @version 0.1.0 
 * @copyright yougou.com
 */
public class AppFile implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 42424404597545043L;

	private Long id;

	private Long ownerId;

	private Short imageType;

	private String imagePath;

	@JsonSerialize(using = JsonDateSerializer$19.class)
	private Date uploadTime;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getOwnerId() {
		return ownerId;
	}

	public void setOwnerId(Long ownerId) {
		this.ownerId = ownerId;
	}

	public Short getImageType() {
		return imageType;
	}

	public void setImageType(Short imageType) {
		this.imageType = imageType;
	}

	public String getImagePath() {
		return imagePath;
	}

	public void setImagePath(String imagePath) {
		this.imagePath = imagePath == null ? null : imagePath.trim();
	}

	public Date getUploadTime() {
		return uploadTime;
	}

	public void setUploadTime(Date uploadTime) {
		this.uploadTime = uploadTime;
	}
}