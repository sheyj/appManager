package com.syj.app.web.vo;

import java.io.Serializable;

/**
 * 用户好友查询VO
 * 
 * @author she.yj
 * @date 2014-5-24 上午10:28:28
 * @version 0.1.0 
 * @copyright syj.com 
 */
public class FriendQueryVo extends BaseAppReq implements Serializable {

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = -4123176601004016734L;
	
	private Short status;

	public Short getStatus() {
		return status;
	}

	public void setStatus(Short status) {
		this.status = status;
	}
	
	
}
