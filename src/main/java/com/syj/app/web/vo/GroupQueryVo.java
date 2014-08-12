package com.syj.app.web.vo;

import java.io.Serializable;

/**
 * 群分页查询
 * 
 * @author she.yj
 * @date 2014-8-12 下午2:01:44
 * @version 0.1.0 
 * @copyright syj.com 
 */
public class GroupQueryVo extends BaseAppReq implements Serializable{

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 5123960640460110159L;

	//群组名称
	private String groupName;
	
	//群组账号
	private String groupAccount;

	public String getGroupName() {
		return groupName;
	}

	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}

	public String getGroupAccount() {
		return groupAccount;
	}

	public void setGroupAccount(String groupAccount) {
		this.groupAccount = groupAccount;
	}
	
	
	
}
