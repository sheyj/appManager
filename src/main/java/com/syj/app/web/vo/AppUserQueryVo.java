package com.syj.app.web.vo;

import java.io.Serializable;

/**
 * 用户查询列表
 * 
 * @author she.yj
 * @date 2014-5-24 上午10:28:45
 * @version 0.1.0 
 * @copyright syj.com 
 */
public class AppUserQueryVo extends BaseAppReq implements Serializable {

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 7010831438924760869L;

	//用户Id
    private String userId;
	//用户账号
	private String userAccount;
	//用户名称  LIKE
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

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

}
