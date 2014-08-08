package com.syj.app.web.model;

import java.io.Serializable;
import java.util.Date;

import org.codehaus.jackson.map.annotate.JsonSerialize;

import com.syj.app.util.JsonDateSerializer$19;

public class TalkResponse implements Serializable {
	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 5040218227039692186L;

	private Long id;

	private Long talkId;

	private Long fromUserId;

	private String fromUserName;
	
	private String fromUserAccount;
	
	private String fromImagePath;
	

	private Long toUserId;

	private String toUserName;

	private String responseMsg;
	@JsonSerialize(using = JsonDateSerializer$19.class)
	private Date responseTime;

	private Short status;

	private String statusName;
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getTalkId() {
		return talkId;
	}

	public void setTalkId(Long talkId) {
		this.talkId = talkId;
	}

	public Long getFromUserId() {
		return fromUserId;
	}

	public void setFromUserId(Long fromUserId) {
		this.fromUserId = fromUserId;
	}

	public String getFromUserName() {
		return fromUserName;
	}

	public void setFromUserName(String fromUserName) {
		this.fromUserName = fromUserName == null ? null : fromUserName.trim();
	}

	public Long getToUserId() {
		return toUserId;
	}

	public void setToUserId(Long toUserId) {
		this.toUserId = toUserId;
	}

	public String getToUserName() {
		return toUserName;
	}

	public void setToUserName(String toUserName) {
		this.toUserName = toUserName == null ? null : toUserName.trim();
	}

	public String getResponseMsg() {
		return responseMsg;
	}

	public void setResponseMsg(String responseMsg) {
		this.responseMsg = responseMsg == null ? null : responseMsg.trim();
	}

	public Date getResponseTime() {
		return responseTime;
	}

	public void setResponseTime(Date responseTime) {
		this.responseTime = responseTime;
	}

	public Short getStatus() {
		return status;
	}

	public void setStatus(Short status) {
		this.status = status;
	}

	public String getStatusName() {
		String statusNameTemp = "";
		if(null!=status){
			if(status.intValue()==0){
				statusNameTemp = "不可见";
			}else{
				statusNameTemp = "可见";
			}
		}
		return statusNameTemp;
	}

	public void setStatusName(String statusName) {
		this.statusName = statusName;
	}

	public String getFromUserAccount() {
		return fromUserAccount;
	}

	public void setFromUserAccount(String fromUserAccount) {
		this.fromUserAccount = fromUserAccount;
	}

	public String getFromImagePath() {
		return fromImagePath;
	}

	public void setFromImagePath(String fromImagePath) {
		this.fromImagePath = fromImagePath;
	}
	
	
}