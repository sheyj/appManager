package com.syj.app.web.service;

import com.syj.app.exception.ServiceException;
import com.syj.app.service.BaseService;
import com.syj.app.web.model.UserFriend;

/**
 * 用户好友接口
 * 
 * @author she.yj
 * @date 2014-5-24 上午9:26:13
 * @version 0.1.0 
 * @copyright syj.com 
 */
public interface UserFriendService extends BaseService {

	/**
	 * 增加好友
	 * @param userFriend
	 * @throws ServiceException
	 */
	void addFriend(UserFriend userFriend)throws ServiceException;

	/**
	 * 删除好友
	 * @param userFriend
	 * @throws ServiceException
	 */
	void delFriend(UserFriend userFriend)throws ServiceException;

	/**
	 * 审核新增好友
	 * @param userFriend
	 * @throws ServiceException
	 */
	void addFriendAudit(UserFriend userFriend)throws ServiceException;

}
