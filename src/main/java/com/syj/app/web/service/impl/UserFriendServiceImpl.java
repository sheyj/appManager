package com.syj.app.web.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.syj.app.dao.BaseMapper;
import com.syj.app.exception.ServiceException;
import com.syj.app.service.impl.BaseServiceImpl;
import com.syj.app.web.dao.UserFriendMapper;
import com.syj.app.web.model.UserFriend;
import com.syj.app.web.service.UserFriendService;

/**
 * 用户好友接口
 * 
 * @author she.yj
 * @date 2014-5-24 上午9:43:38
 * @version 0.1.0 
 * @copyright syj.com 
 */
@Service
public class UserFriendServiceImpl extends BaseServiceImpl implements UserFriendService {

	@Resource
	private UserFriendMapper userFriendMapper;

	@Override
	public BaseMapper init() {
		return userFriendMapper;
	}

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = ServiceException.class)
	public void addFriend(UserFriend userFriend) throws ServiceException {
		//防止数据重复
		//先删除自己的好友关系
		UserFriend delUserFriend = new UserFriend();
		delUserFriend.setUserId(userFriend.getUserId());
		delUserFriend.setFriendUserId(userFriend.getFriendUserId());
		userFriendMapper.deleteByPrimarayKeyForModel(delUserFriend);
		//再删除对方的好友
		delUserFriend.setUserId(userFriend.getFriendUserId());
		delUserFriend.setFriendUserId(userFriend.getUserId());
		userFriendMapper.deleteByPrimarayKeyForModel(delUserFriend);
		//插入自己
		userFriendMapper.insert(userFriend);
		
		UserFriend tempUserFriend  = new UserFriend();
		tempUserFriend.setUserId(userFriend.getFriendUserId());
		tempUserFriend.setFriendUserId(userFriend.getUserId());
		tempUserFriend.setStatus(Short.valueOf("1"));
		tempUserFriend.setRemark(userFriend.getRemark());
		userFriendMapper.insert(tempUserFriend);
	}

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = ServiceException.class)
	public void delFriend(UserFriend userFriend) throws ServiceException {
		//先删除自己的好友关系
		UserFriend delUserFriend = new UserFriend();
		delUserFriend.setUserId(userFriend.getUserId());
		delUserFriend.setFriendUserId(userFriend.getFriendUserId());
		userFriendMapper.deleteByPrimarayKeyForModel(delUserFriend);
		//再删除对方的好友
		delUserFriend.setUserId(userFriend.getFriendUserId());
		delUserFriend.setFriendUserId(userFriend.getUserId());
		userFriendMapper.deleteByPrimarayKeyForModel(delUserFriend);
	}

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = ServiceException.class)
	public void addFriendAudit(UserFriend userFriend) throws ServiceException {
		//状态0待审核  1申请中 2申请通过 3 申请拒绝
		int status = userFriend.getStatus().intValue();
		//申请通过  修改双方好友状态
		if (status!=1) {
			UserFriend tempUserFriend = new UserFriend();
			tempUserFriend.setUserId(userFriend.getUserId());
			tempUserFriend.setFriendUserId(userFriend.getFriendUserId());	
			tempUserFriend.setStatus(userFriend.getStatus());
			tempUserFriend.setRemark(userFriend.getRemark());
			userFriendMapper.updateStatus(tempUserFriend);
			
			tempUserFriend.setUserId(userFriend.getFriendUserId());
			tempUserFriend.setFriendUserId(userFriend.getUserId());
			userFriendMapper.updateStatus(tempUserFriend);
		}
	}

}
