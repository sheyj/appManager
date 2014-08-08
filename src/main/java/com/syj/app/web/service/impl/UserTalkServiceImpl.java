package com.syj.app.web.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.syj.app.common.SimplePage;
import com.syj.app.dao.BaseMapper;
import com.syj.app.exception.ServiceException;
import com.syj.app.service.impl.BaseServiceImpl;
import com.syj.app.web.dao.AppUserMapper;
import com.syj.app.web.dao.TalkResponseMapper;
import com.syj.app.web.dao.UserTalkMapper;
import com.syj.app.web.model.AppUser;
import com.syj.app.web.model.TalkResponse;
import com.syj.app.web.model.UserTalk;
import com.syj.app.web.service.UserTalkService;

/**
 * 用户说说接口
 * 
 * @author she.yj
 * @date 2014-5-23 下午2:12:20
 * @version 0.1.0 
 * @copyright syj.com 
 */
@Service
public class UserTalkServiceImpl extends BaseServiceImpl implements UserTalkService {

	@Resource
	private UserTalkMapper userTalkMapper;

	@Resource
	private TalkResponseMapper talkResponseMapper;

	@Resource
	private AppUserMapper appUserMapper;

	@Override
	public BaseMapper init() {
		return userTalkMapper;
	}

	/**
	 * 查询说说数量
	 */
	public int findTalkCount(Map<String, Object> params) throws ServiceException {
		try {
			return userTalkMapper.findTalkCount(params);
		} catch (Exception e) {
			throw new ServiceException("查询说说数量异常", e);
		}
	}

	/**
	 * 查询说说list
	 */
	public List<UserTalk> findTalkByPage(SimplePage page, Map<String, Object> params) throws ServiceException {
		try {
			List<UserTalk> resultList = userTalkMapper.findTalkByPage(page, params);
			AppUser temAppUser = null;
			if (null != resultList && resultList.size() > 0) {
				UserTalk tempUserTalk = resultList.get(0);
				temAppUser = appUserMapper.selectByPrimaryKey(tempUserTalk.getUserId().toString());
			} else {
				return new ArrayList<UserTalk>();
			}
			//循环查询说说评论
			Map<String, Object> tempMap = new HashMap<String, Object>();
			tempMap.put("status", 1);
			TalkResponse talkResponse = new TalkResponse();
			for (UserTalk userTalk : resultList) {
				tempMap.put("talkId", userTalk.getId());
				if (null != temAppUser) {
					userTalk.setImagePath(temAppUser.getUserImage());
					userTalk.setUserName(temAppUser.getUserName());
					userTalk.setUserAccount(temAppUser.getUserAccount());
				}

				List<TalkResponse> talkResponseList = talkResponseMapper.selectByParams(talkResponse, tempMap);
				if (null != talkResponseList && talkResponseList.size() > 0) {
					for (TalkResponse tempTalkResponse : talkResponseList) {
						AppUser appUser = appUserMapper.selectByPrimaryKey(tempTalkResponse.getFromUserId().toString());
						if (null != appUser) {
							tempTalkResponse.setFromImagePath(appUser.getUserImage());
							tempTalkResponse.setFromUserAccount(appUser.getUserAccount());
							tempTalkResponse.setFromUserName(appUser.getUserName());
						}
					}
					userTalk.setTalkResList(talkResponseList);
				}
			}
			return resultList;
		} catch (Exception e) {
			throw new ServiceException("查询说说异常", e);
		}
	}

	/**
	 * 删除用户说说 和评论
	 */
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = ServiceException.class)
	public void deleteTalkAndResponse(UserTalk userTalk) throws ServiceException {
		try {
			TalkResponse talkResponse = new TalkResponse();
			talkResponse.setTalkId(userTalk.getId());
			talkResponseMapper.deleteByPrimarayKeyForModel(talkResponse);
			userTalkMapper.deleteByPrimarayKeyForModel(userTalk);
		} catch (Exception e) {
			throw new ServiceException("删除用户说说 和评论异常", e);
		}
	}

	public UserTalk getLastUserTalk(UserTalk userTalk) throws ServiceException {
		try {
			return userTalkMapper.getLastUserTalk(userTalk);
		} catch (Exception e) {
			throw new ServiceException("查询用户最新说说异常", e);
		}
	}

}
