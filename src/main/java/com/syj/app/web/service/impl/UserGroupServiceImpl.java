package com.syj.app.web.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.syj.app.common.SimplePage;
import com.syj.app.dao.BaseMapper;
import com.syj.app.exception.ServiceException;
import com.syj.app.service.impl.BaseServiceImpl;
import com.syj.app.web.dao.GroupMsgMapper;
import com.syj.app.web.dao.GroupUserApplyMapper;
import com.syj.app.web.dao.GroupUserMapper;
import com.syj.app.web.dao.UserGroupMapper;
import com.syj.app.web.model.GroupMsg;
import com.syj.app.web.model.GroupUser;
import com.syj.app.web.model.GroupUserApply;
import com.syj.app.web.model.UserGroup;
import com.syj.app.web.service.UserGroupService;

/**
 * 用户群组
 * @author she.yj
 * @date 2014-5-24 上午11:02:24
 * @version 0.1.0 
 * @copyright syj.com 
 */
@Service
public class UserGroupServiceImpl extends BaseServiceImpl implements UserGroupService {

	@Resource
	private UserGroupMapper userGroupMapper;

	@Resource
	private GroupUserMapper groupUserMapper;

	@Resource
	private GroupUserApplyMapper groupUserApplyMapper;

	@Resource
	private GroupMsgMapper groupMsgMapper;

	@Override
	public BaseMapper init() {
		return userGroupMapper;
	}

	public List<UserGroup> findByUserId(String userId) throws ServiceException {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("userId", Integer.valueOf(userId));
		List<UserGroup> list = userGroupMapper.findByUserId(params);
		if (null != list && list.size() > 0) {
			Map<String, Object> temparams = new HashMap<String, Object>();
			for (UserGroup userGroup : list) {
				GroupUser groupUser = new GroupUser();
				temparams.put("groupId", userGroup.getId());
				List<GroupUser> guList = groupUserMapper.selectByParams(groupUser, temparams);
				userGroup.setGroupUserList(guList);
			}
		}
		return list;
	}

	@Transactional
	public void deleteUserGroup(UserGroup userGroup) throws ServiceException {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("groupId", userGroup.getId());
		GroupMsg groupMsg = new GroupMsg();
		groupMsg.setGroupId(userGroup.getId());
		groupMsgMapper.deleteByPrimarayKeyForModel(groupMsg);
		GroupUser groupUser = new GroupUser();
		groupUser.setGroupId(userGroup.getId());
		groupUserMapper.deleteByPrimarayKeyForModel(groupUser);
		GroupUserApply groupUserApply = new GroupUserApply();
		groupUserApply.setGroupId(userGroup.getId());
		groupUserApplyMapper.deleteByPrimarayKeyForModel(groupUserApply);
		userGroupMapper.deleteByPrimaryKey(userGroup.getId().toString());

	}

	@Transactional
	public void auditGroup(String id, String status) throws ServiceException {
		if ("1".equals(status)) {
			//审核通过 插入到用群组
			GroupUser groupUser = new GroupUser();
			GroupUserApply groupUserApply = groupUserApplyMapper.selectByPrimaryKey(id);
			groupUser.setGroupId(groupUserApply.getGroupId());
			groupUser.setUserId(groupUserApply.getUserId());
			groupUserMapper.deleteByPrimarayKeyForModel(groupUser);
			
			UserGroup userGroup =userGroupMapper.selectByPrimaryKey(groupUserApply.getGroupId().toString());
			if(null!= userGroup){
				groupUser.setMasterId(userGroup.getMasterId());
			}
			groupUser.setUserAccount(groupUserApply.getUserAccount());
			groupUser.setUserName(groupUserApply.getUserName());
			groupUser.setUserImage(groupUserApply.getUserImage());
			groupUserMapper.insertSelective(groupUser);
		}
		//审核通过和审核拒绝都是直接删除
		groupUserApplyMapper.deleteByPrimaryKey(id);
	}

	public List<GroupUserApply> queryApplyGroupList(String groupId,String userId,String applyType) throws ServiceException {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("groupId", groupId);
		params.put("userId", userId);
		params.put("applyType", applyType);
		List<GroupUserApply> applyList = groupUserApplyMapper.selectByParams(new GroupUserApply(), params);
		return applyList;

	}

	public List<GroupUser> queryGroupUserList(String groupId) throws ServiceException {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("groupId", groupId);
		return groupUserMapper.selectByParams(new GroupUser(), params);
	}

	public int findGroupMsgCount(Map<String, Object> params) throws ServiceException {
		return groupMsgMapper.selectCount(params);
	}

	public List<GroupMsg> queryGroupMsg(SimplePage page, Map<String, Object> params) throws ServiceException {
		return groupMsgMapper.selectByPage(page, "", "", params);
	}

	public void addGroupMsg(GroupMsg groupMsg) throws ServiceException {
		groupMsgMapper.insertSelective(groupMsg);
	}

	@Transactional
	public void addGroupUserApply(GroupUserApply groupUserApply) throws ServiceException {
		groupUserApplyMapper.deleteByPrimarayKeyForModel(groupUserApply);
		groupUserApplyMapper.insertSelective(groupUserApply);
	}

	public void addGroupUser(GroupUser groupUser) throws ServiceException {
		groupUserMapper.insertSelective(groupUser);
	}

}
