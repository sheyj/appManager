package com.syj.app.web.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.syj.app.common.PublicConstans;
import com.syj.app.common.SimplePage;
import com.syj.app.exception.ServiceException;
import com.syj.app.interceptor.IgnoredInterceptors;
import com.syj.app.web.model.AppUser;
import com.syj.app.web.model.UserAttention;
import com.syj.app.web.model.UserFriend;
import com.syj.app.web.model.UserGroup;
import com.syj.app.web.service.AppUserService;
import com.syj.app.web.service.UserAttentionService;
import com.syj.app.web.service.UserFriendService;
import com.syj.app.web.service.UserGroupService;
import com.syj.app.web.vo.AppUserQueryVo;
import com.syj.app.web.vo.AttentionQueryVo;
import com.syj.app.web.vo.BaseResult;
import com.syj.app.web.vo.FriendQueryVo;

/**
 * APP好友和关注用户管理接口
 * @author she.yj
 * @date 2014-5-24 上午7:14:20
 * @version 0.1.0 
 * @copyright syj.com 
 */
@Controller
@RequestMapping("/")
public class AppFriendController {

	// 日志对象
	private static final Logger logger = Logger.getLogger(AppFriendController.class);

	@Resource
	private UserAttentionService userAttentionService;

	@Resource
	private UserFriendService userFriendService;

	@Resource
	private AppUserService appUserService;

	@Resource
	private UserGroupService userGroupService;

//	@Resource
//	private AppFileService appFileService;
	
	/**
	 * 查询用户好友列表
	 * @return
	 * @throws ServiceException
	 */
	@IgnoredInterceptors
	@RequestMapping(value = "/friendList")
	@ResponseBody
	public BaseResult friendList(FriendQueryVo friendQueryVo) throws ServiceException {
		BaseResult baseResult = new BaseResult();
		try {
			int pageNo = StringUtils.isEmpty(friendQueryVo.getPageNo()) ? 1 : Integer.parseInt(friendQueryVo
					.getPageNo());
			int pageSize = StringUtils.isEmpty(friendQueryVo.getPageSize()) ? 10 : Integer.parseInt(friendQueryVo
					.getPageSize());

			Map<String, Object> params = new HashMap<String, Object>();
			params.put("userId", friendQueryVo.getUserId());
			params.put("status", friendQueryVo.getStatus());
			int total = this.userFriendService.findCount(params);
			baseResult.getResultObj().put("total", total);
			if (total == 0) {
				baseResult.getResultObj().put("rows", new ArrayList<UserFriend>());
				return baseResult;
			}
			SimplePage page = new SimplePage(pageNo, pageSize, (int) total);
			List<UserFriend> list = this.userFriendService.findByPage(page, null, null, params);
			if(null!= list && list.size()>0){
				for(UserFriend tempUserFriend :list){
					AppUser appUser = appUserService.findById(tempUserFriend.getFriendUserId().toString());
					if(null != appUser){
						tempUserFriend.setFriendsUserAccount(appUser.getUserAccount());
						tempUserFriend.setFriendUserName(appUser.getUserName());
						tempUserFriend.setFriendsUserImage(appUser.getUserImage());
						tempUserFriend.setFriendsUserRemark(appUser.getRemark());
						tempUserFriend.setPositionX(appUser.getPositionX());
						tempUserFriend.setPositionY(appUser.getPositionY());
					}
				}
			}
			baseResult.getResultObj().put("rows", list);
		} catch (ServiceException e) {
			logger.error("查询用户好友列表异常！", e);
			baseResult.setSuccess(PublicConstans.OPERTION_FAIL);
			baseResult.setMessage(e.getMessage());
		} catch (Exception e) {
			logger.error("查询用户好友列表异常！", e);
			baseResult.setSuccess(PublicConstans.OPERTION_FAIL);
			baseResult.setMessage(PublicConstans.OPERTION_FAIL_MESSAGE);
		}
		return baseResult;
	}

	/**
	 * 查询用户关注列表
	 * @return
	 * @throws ServiceException
	 */
	@RequestMapping(value = "/attentionList")
	@ResponseBody
	public BaseResult friendList(AttentionQueryVo attentionQueryVo) throws ServiceException {
		BaseResult baseResult = new BaseResult();
		try {
			int pageNo = StringUtils.isEmpty(attentionQueryVo.getPageNo()) ? 1 : Integer.parseInt(attentionQueryVo
					.getPageNo());
			int pageSize = StringUtils.isEmpty(attentionQueryVo.getPageSize()) ? 10 : Integer.parseInt(attentionQueryVo
					.getPageSize());

			Map<String, Object> params = new HashMap<String, Object>();
			params.put("userId", attentionQueryVo.getUserId());
			int total = this.userAttentionService.findCount(params);
			baseResult.getResultObj().put("total", total);
			if (total == 0) {
				baseResult.getResultObj().put("rows", new ArrayList<UserAttention>());
				return baseResult;
			}
			SimplePage page = new SimplePage(pageNo, pageSize, (int) total);
			List<UserAttention> list = this.userAttentionService.findByPage(page, null, null, params);
			if(null!= list && list.size()>0){
				for(UserAttention tempUserAttention :list){
					AppUser appUser = appUserService.findById(tempUserAttention.getAttentionUserId().toString());
					if(null != appUser){
						tempUserAttention.setAttentionUserAccount(appUser.getUserAccount());
						tempUserAttention.setAttentionUserName(appUser.getUserName());
						tempUserAttention.setAttentionUserImage(appUser.getUserImage());
						tempUserAttention.setAttentionUserRemark(appUser.getRemark());
						tempUserAttention.setPositionX(appUser.getPositionX());
						tempUserAttention.setPositionY(appUser.getPositionY());
					}
				}
			}
			baseResult.getResultObj().put("rows", list);
		} catch (ServiceException e) {
			logger.error("查询用户关注列表异常！", e);
			baseResult.setSuccess(PublicConstans.OPERTION_FAIL);
			baseResult.setMessage(e.getMessage());
		} catch (Exception e) {
			logger.error("查询用户关注列表异常！", e);
			baseResult.setSuccess(PublicConstans.OPERTION_FAIL);
			baseResult.setMessage(PublicConstans.OPERTION_FAIL_MESSAGE);
		}
		return baseResult;
	}

	/**
	 * 查询用户列表
	 * @return
	 * @throws ServiceException
	 */
	@RequestMapping(value = "/searchUserList")
	@ResponseBody
	public BaseResult searchUserList(AppUserQueryVo appUserQueryVo) throws ServiceException {
		BaseResult baseResult = new BaseResult();
		try {
			int pageNo = StringUtils.isEmpty(appUserQueryVo.getPageNo()) ? 1 : Integer.parseInt(appUserQueryVo
					.getPageNo());
			int pageSize = StringUtils.isEmpty(appUserQueryVo.getPageSize()) ? 10 : Integer.parseInt(appUserQueryVo
					.getPageSize());

			Map<String, Object> params = new HashMap<String, Object>();
			if (StringUtils.isNotBlank(appUserQueryVo.getUserAccount())) {
				params.put("userAccount", appUserQueryVo.getUserAccount());
			}

			if (StringUtils.isNotBlank(appUserQueryVo.getUserName())) {
				params.put("userNameLike", appUserQueryVo.getUserName());
			}

			int total = this.appUserService.findCount(params);
			baseResult.getResultObj().put("total", total);
			if (total == 0) {
				baseResult.getResultObj().put("rows", new ArrayList<UserAttention>());
				return baseResult;
			}
			SimplePage page = new SimplePage(pageNo, pageSize, (int) total);
			List<UserAttention> list = this.appUserService.findByPage(page, null, null, params);
			baseResult.getResultObj().put("rows", list);
		} catch (ServiceException e) {
			logger.error("查询用户列表异常！", e);
			baseResult.setSuccess(PublicConstans.OPERTION_FAIL);
			baseResult.setMessage(e.getMessage());
		} catch (Exception e) {
			logger.error("查询用户列表异常！", e);
			baseResult.setSuccess(PublicConstans.OPERTION_FAIL);
			baseResult.setMessage(PublicConstans.OPERTION_FAIL_MESSAGE);
		}
		return baseResult;
	}

	/**
	 * 查看用户详情
	 * @return
	 * @throws ServiceException
	 */
	@RequestMapping(value = "/queryUserDetail")
	@ResponseBody
	public BaseResult queryUserDetail(AppUserQueryVo appUserQueryVo) throws ServiceException {
		BaseResult baseResult = new BaseResult();
		try {

			Map<String, Object> params = new HashMap<String, Object>();
			if (StringUtils.isNotBlank(appUserQueryVo.getUserAccount())) {
				params.put("userAccount", appUserQueryVo.getUserAccount());
			}

			if (StringUtils.isNotBlank(appUserQueryVo.getUserName())) {
				params.put("userName", appUserQueryVo.getUserName());
			}
			if (StringUtils.isNotBlank(appUserQueryVo.getUserId())) {
				params.put("id", appUserQueryVo.getUserId());
			}
			AppUser appUser = new AppUser();
			List<AppUser> list = appUserService.findByBiz(appUser, params);
			if (null != list && list.size() > 0) {
				AppUser tempAppUser = list.get(0);
//				AppFile appFile = new AppFile();
//				Map<String, Object> paramsAppFile = new HashMap<String, Object>();
//				paramsAppFile.put("ownerId", tempAppUser.getId());
//				List<AppFile> appFileList = appFileService.findByBiz(appFile, paramsAppFile);
//
//				if (null != appFileList && appFileList.size() > 0) {
//					tempAppUser.setAppFile(appFileList.get(0));
//				}
				baseResult.getResultObj().put("appUser", tempAppUser);
			} else {
				baseResult.setSuccess(PublicConstans.OPERTION_FAIL);
				baseResult.setMessage("没有查询到用户！");
			}
		} catch (ServiceException e) {
			logger.error("查看用户详情异常！", e);
			baseResult.setSuccess(PublicConstans.OPERTION_FAIL);
			baseResult.setMessage(e.getMessage());
		} catch (Exception e) {
			logger.error("查看用户详情异常！", e);
			baseResult.setSuccess(PublicConstans.OPERTION_FAIL);
			baseResult.setMessage(PublicConstans.OPERTION_FAIL_MESSAGE);
		}
		return baseResult;
	}

	/**
	 * 申请新增好友
	 * @param appUser
	 * @return
	 */
	@RequestMapping(value = "/userFriendAdd")
	@ResponseBody
	public BaseResult userFriendAdd(UserFriend userFriend) {
		BaseResult baseResult = new BaseResult();
		try {
			//校验好友关系是否存在
			Map<String, Object> params = new HashMap<String, Object>();
			params.put("userId", userFriend.getUserId());
			params.put("friendUserId", userFriend.getFriendUserId());
			int count = userFriendService.findCount(params);
			if (count > 0) {
				baseResult.setSuccess(PublicConstans.OPERTION_FAIL);
				baseResult.setMessage("用户好友关系已经存在！");
				return baseResult;
			}
			userFriend.setStatus(Short.valueOf("0"));
			userFriendService.addFriend(userFriend);
		} catch (ServiceException e) {
			logger.error("新增好友异常！", e);
			baseResult.setSuccess(PublicConstans.OPERTION_FAIL);
			baseResult.setMessage(e.getMessage());
		} catch (Exception e) {
			logger.error("新增好友异常！", e);
			baseResult.setSuccess(PublicConstans.OPERTION_FAIL);
			baseResult.setMessage(PublicConstans.OPERTION_FAIL_MESSAGE);
		}
		return baseResult;
	}

	/**
	 * 审核新增好友
	 * @param appUser
	 * @return
	 */
	@RequestMapping(value = "/userFriendAudit")
	@ResponseBody
	public BaseResult userFriendAudit(UserFriend userFriend) {
		BaseResult baseResult = new BaseResult();
		try {
			//校验好友关系是否存在
			Map<String, Object> params = new HashMap<String, Object>();
			params.put("userId", userFriend.getUserId());
			params.put("friendUserId", userFriend.getFriendUserId());
			int count = userFriendService.findCount(params);
			if (count == 0) {
				baseResult.setSuccess(PublicConstans.OPERTION_FAIL);
				baseResult.setMessage("用户好友关系不存在！");
				return baseResult;
			}

			userFriendService.addFriendAudit(userFriend);
		} catch (ServiceException e) {
			logger.error("审核新增好友异常！", e);
			baseResult.setSuccess(PublicConstans.OPERTION_FAIL);
			baseResult.setMessage(e.getMessage());
		} catch (Exception e) {
			logger.error("审核新增好友异常！", e);
			baseResult.setSuccess(PublicConstans.OPERTION_FAIL);
			baseResult.setMessage(PublicConstans.OPERTION_FAIL_MESSAGE);
		}
		return baseResult;
	}

	/**
	 * 删除好友
	 * @param appUser
	 * @return
	 */
	@RequestMapping(value = "/userFriendDel")
	@ResponseBody
	public BaseResult userFriendDel(UserFriend userFriend) {
		BaseResult baseResult = new BaseResult();
		try {
			userFriendService.delFriend(userFriend);
		} catch (ServiceException e) {
			logger.error("删除好友异常！", e);
			baseResult.setSuccess(PublicConstans.OPERTION_FAIL);
			baseResult.setMessage(e.getMessage());
		} catch (Exception e) {
			logger.error("删除好友异常！", e);
			baseResult.setSuccess(PublicConstans.OPERTION_FAIL);
			baseResult.setMessage(PublicConstans.OPERTION_FAIL_MESSAGE);
		}
		return baseResult;
	}

	/**
	 * 新增关注用户
	 * @param appUser
	 * @return
	 */
	@RequestMapping(value = "/userAttentionAdd")
	@ResponseBody
	public BaseResult userAttentionAdd(UserAttention userAttention) {
		BaseResult baseResult = new BaseResult();
		try {
			//校验好友关系是否存在
			Map<String, Object> params = new HashMap<String, Object>();
			params.put("userId", userAttention.getUserId());
			params.put("attentionUserId", userAttention.getAttentionUserId());
			int count = userAttentionService.findCount(params);
			if (count > 0) {
				baseResult.setSuccess(PublicConstans.OPERTION_FAIL);
				baseResult.setMessage("用户关注已经存在！");
				return baseResult;
			}

			userAttentionService.add(userAttention);
		} catch (ServiceException e) {
			logger.error("新增关注用户异常！", e);
			baseResult.setSuccess(PublicConstans.OPERTION_FAIL);
			baseResult.setMessage(e.getMessage());
		} catch (Exception e) {
			logger.error("新增关注用户异常！", e);
			baseResult.setSuccess(PublicConstans.OPERTION_FAIL);
			baseResult.setMessage(PublicConstans.OPERTION_FAIL_MESSAGE);
		}
		return baseResult;
	}

	/**
	 * 删除关注用户
	 * @param appUser
	 * @return
	 */
	@RequestMapping(value = "/userAttentionDel")
	@ResponseBody
	public BaseResult userAttentionDel(UserAttention userAttention) {
		BaseResult baseResult = new BaseResult();
		try {
			userAttentionService.deleteById(userAttention);
		} catch (ServiceException e) {
			logger.error("删除关注用户异常！", e);
			baseResult.setSuccess(PublicConstans.OPERTION_FAIL);
			baseResult.setMessage(e.getMessage());
		} catch (Exception e) {
			logger.error("删除关注用户异常！", e);
			baseResult.setSuccess(PublicConstans.OPERTION_FAIL);
			baseResult.setMessage(PublicConstans.OPERTION_FAIL_MESSAGE);
		}
		return baseResult;
	}

	/**
	 *查询我的用户群组
	 * @param appUser
	 * @return
	 */
	@RequestMapping(value = "/myUserGroup")
	@ResponseBody
	public BaseResult myUserGroup(String userId) {
		BaseResult baseResult = new BaseResult();
		try {
			List<UserGroup> userGroupList = userGroupService.findByUserId(userId);
			if (null != userGroupList && userGroupList.size() > 0) {
				baseResult.getResultObj().put("rows", userGroupList);
			}
		} catch (ServiceException e) {
			logger.error("查询我的用户群组异常！", e);
			baseResult.setSuccess(PublicConstans.OPERTION_FAIL);
			baseResult.setMessage(e.getMessage());
		} catch (Exception e) {
			logger.error("查询我的用户群组异常！", e);
			baseResult.setSuccess(PublicConstans.OPERTION_FAIL);
			baseResult.setMessage(PublicConstans.OPERTION_FAIL_MESSAGE);
		}
		return baseResult;
	}

}
