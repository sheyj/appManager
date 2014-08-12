package com.syj.app.web.vo;

import java.io.Serializable;

/**
 * 用户分页查询
 * 
 * @author she.yj
 * @date 2014-8-12 下午2:01:44
 * @version 0.1.0 
 * @copyright syj.com 
 */
public class UserQueryVo extends BaseAppReq implements Serializable{

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = -4410550844051065023L;

	private String userAccount;

	private String userName;

	public String getUserAccount() {
		return userAccount;
	}

	public void setUserAccount(String userAccount) {
		this.userAccount = userAccount;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}
	
	
}
