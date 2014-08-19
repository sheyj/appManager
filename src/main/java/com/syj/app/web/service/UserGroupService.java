package com.syj.app.web.service;

import java.util.List;
import java.util.Map;

import com.syj.app.common.SimplePage;
import com.syj.app.exception.ServiceException;
import com.syj.app.service.BaseService;
import com.syj.app.web.model.GroupMsg;
import com.syj.app.web.model.GroupUser;
import com.syj.app.web.model.GroupUserApply;
import com.syj.app.web.model.UserGroup;

/**
 * 用户群组
 * 
 * @author she.yj
 * @date 2014-5-24 上午11:01:46
 * @version 0.1.0 
 * @copyright syj.com 
 */
public interface UserGroupService extends BaseService {

	/**
	 * 查询我的群组
	 * @param userId
	 * @return
	 * @throws ServiceException
	 */
	List<UserGroup> findByUserId(String userId)throws ServiceException;

	/**
	 * 删除用户群组
	 *
	 * @param userGroup
	 * @throws ServiceException
	 */
	void deleteUserGroup(UserGroup userGroup)throws ServiceException;

	/**
	 * 审核群组人员申请
	 * @param id
	 * @param status
	 * @throws ServiceException
	 */
	void auditGroup(String id, String status)throws ServiceException;

	/**
	 * 查询群组申请加入待审核列表
	 * @param groupId
	 * @throws ServiceException
	 */
	List<GroupUserApply> queryApplyGroupList(String groupId,String userId,String applyType)throws ServiceException;

	/**
	 * 查询群组用户信息
	 * @param groupId
	 * @return
	 * @throws ServiceException
	 */
	List<GroupUser> queryGroupUserList(String groupId)throws ServiceException;

	/**
	 * 查询群消息数量
	 * @param params
	 * @return
	 * @throws ServiceException
	 */
	int findGroupMsgCount(Map<String, Object> params)throws ServiceException;

	/**
	 * 分页查询群消息
	 * @param page
	 * @param params
	 * @return
	 */
	List<GroupMsg> queryGroupMsg(SimplePage page, Map<String, Object> params)throws ServiceException;

	/**
	 * 增加群聊消息
	 * @param groupMsg
	 * @throws ServiceException
	 */
	void addGroupMsg(GroupMsg groupMsg)throws ServiceException;

	/**
	 * 申请或者邀请加入群组
	 * @param groupUserApply
	 * @throws ServiceException
	 */
	void addGroupUserApply(GroupUserApply groupUserApply)throws ServiceException;

	/**
	 * 增加用户到群组
	 * @param groupUser
	 * @throws ServiceException
	 */
	void addGroupUser(GroupUser groupUser)throws ServiceException;

	int findApplyGroupCount(String userId)throws ServiceException;

	List<GroupUserApply> queryApplyGroupListByPage( String userId,  SimplePage page)throws ServiceException;

	void delGroupUser(String userId, String groupId)throws ServiceException;

}
