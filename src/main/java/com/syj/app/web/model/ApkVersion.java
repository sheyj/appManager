package com.syj.app.web.model;

import java.io.Serializable;
import java.util.Date;

import org.codehaus.jackson.map.annotate.JsonSerialize;

import com.syj.app.util.JsonDateSerializer$10;
import com.syj.app.util.JsonDateSerializer$19;

public class ApkVersion implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -4674106084651124530L;

	private Integer id;

	private String versionNo;

	private String apkPath;

	private String modifyContent;
	@JsonSerialize(using = JsonDateSerializer$10.class)
	private Date publishDate;

	private Short isForce;
	@JsonSerialize(using = JsonDateSerializer$19.class)
	private Date createTime;

	private String creater;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getVersionNo() {
		return versionNo;
	}

	public void setVersionNo(String versionNo) {
		this.versionNo = versionNo == null ? null : versionNo.trim();
	}

	public String getApkPath() {
		return apkPath;
	}

	public void setApkPath(String apkPath) {
		this.apkPath = apkPath == null ? null : apkPath.trim();
	}

	public String getModifyContent() {
		return modifyContent;
	}

	public void setModifyContent(String modifyContent) {
		this.modifyContent = modifyContent == null ? null : modifyContent.trim();
	}

	public Date getPublishDate() {
		return publishDate;
	}

	public void setPublishDate(Date publishDate) {
		this.publishDate = publishDate;
	}

	public Short getIsForce() {
		return isForce;
	}

	public void setIsForce(Short isForce) {
		this.isForce = isForce;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public String getCreater() {
		return creater;
	}

	public void setCreater(String creater) {
		this.creater = creater == null ? null : creater.trim();
	}
}