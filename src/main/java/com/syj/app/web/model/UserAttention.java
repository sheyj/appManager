package com.syj.app.web.model;

import java.io.Serializable;

public class UserAttention implements Serializable {
	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = -3404231458662465038L;

	private Long id;

	private Long userId;

	private Long attentionUserId;

	private String attentionUserName;

	private String attentionUserAccount;
	private String attentionUserImage;
	private String attentionUserRemark;
	private Float positionX;
	private Float positionY;

	public String getAttentionUserAccount() {
		return attentionUserAccount;
	}

	public void setAttentionUserAccount(String attentionUserAccount) {
		this.attentionUserAccount = attentionUserAccount;
	}

	public String getAttentionUserImage() {
		return attentionUserImage;
	}

	public void setAttentionUserImage(String attentionUserImage) {
		this.attentionUserImage = attentionUserImage;
	}

	public String getAttentionUserRemark() {
		return attentionUserRemark;
	}

	public void setAttentionUserRemark(String attentionUserRemark) {
		this.attentionUserRemark = attentionUserRemark;
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

	public Long getAttentionUserId() {
		return attentionUserId;
	}

	public void setAttentionUserId(Long attentionUserId) {
		this.attentionUserId = attentionUserId;
	}

	public String getAttentionUserName() {
		return attentionUserName;
	}

	public void setAttentionUserName(String attentionUserName) {
		this.attentionUserName = attentionUserName == null ? null : attentionUserName.trim();
	}
}