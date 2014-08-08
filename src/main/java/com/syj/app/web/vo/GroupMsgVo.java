package com.syj.app.web.vo;

import java.io.Serializable;

/**
 * 群组消息查询VO
 * 
 * @author she.yj
 * @date 2014-8-6 下午12:12:31
 * @version 0.1.0 
 * @copyright syj.com 
 */
public class GroupMsgVo extends BaseAppReq implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5422569727668260358L;

	
	private String groupId;
	
	private String createTime;

	public String getGroupId() {
		return groupId;
	}

	public void setGroupId(String groupId) {
		this.groupId = groupId;
	}

	public String getCreateTime() {
		return createTime;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}
}
