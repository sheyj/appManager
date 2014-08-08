package com.syj.app.web.service;

import java.util.List;
import java.util.Map;

import com.syj.app.common.SimplePage;
import com.syj.app.exception.ServiceException;
import com.syj.app.service.BaseService;
import com.syj.app.web.model.UserTalk;

/**
 * 说说接口
 * @author username
 * @date 2014-5-23 下午1:51:00
 * @version 0.1.0 
 * @copyright yougou.com 
 */
public interface UserTalkService extends BaseService {

	/**
	 * 查询用户说说数量
	 * @param params
	 * @return
	 * @throws ServiceException
	 */
	int findTalkCount(Map<String, Object> params)throws ServiceException;

	/**
	 * 查询用户说说LIST
	 * @param page
	 * @param params
	 * @return
	 * @throws ServiceException
	 */
	List<UserTalk> findTalkByPage(SimplePage page, Map<String, Object> params)throws ServiceException;

	/**
	 * 删除用户说说和评论
	 * @param userTalk
	 * @throws ServiceException
	 */
	void deleteTalkAndResponse(UserTalk userTalk)throws ServiceException;
	
	/**
	 * 获取用户最后发表的说说
	 * @param userTalk
	 * @return
	 * @throws ServiceException
	 */
	UserTalk getLastUserTalk(UserTalk userTalk)throws ServiceException;

}
