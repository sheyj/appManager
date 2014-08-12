package com.syj.app.web.controller;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.index.GeospatialIndex;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.druid.util.StringUtils;
import com.mongodb.BasicDBList;
import com.mongodb.BasicDBObject;
import com.mongodb.CommandResult;
import com.syj.app.common.PublicConstans;
import com.syj.app.common.SimplePage;
import com.syj.app.exception.ServiceException;
import com.syj.app.web.model.AppUser;
import com.syj.app.web.model.GroupMsg;
import com.syj.app.web.model.GroupUser;
import com.syj.app.web.model.GroupUserApply;
import com.syj.app.web.model.UserGroup;
import com.syj.app.web.model.UserLocation;
import com.syj.app.web.model.UserTalk;
import com.syj.app.web.service.AppUserService;
import com.syj.app.web.service.UserGroupService;
import com.syj.app.web.vo.BaseResult;
import com.syj.app.web.vo.GroupInfo;
import com.syj.app.web.vo.GroupMsgVo;

/**
 *APP 群组功能接口
 * 
 * @author she.yj
 * @date 2014-5-23 下午5:12:48
 * @version 0.1.0 
 * @copyright syj.com 
 */
@Controller
@RequestMapping("/")
public class AppGroupController {

	// 日志对象
	private static final Logger logger = Logger.getLogger(AppGroupController.class);

	@Resource
	private AppUserService appUserService;

	@Resource
	private UserGroupService userGroupService;

	@Resource
	private MongoTemplate mongoTemplate;

	/**
	 * 创建群组
	 * @param UserGroup
	 * @return
	 */
	@RequestMapping(value = "/createGroup")
	@ResponseBody
	public BaseResult createGroup(UserGroup userGroup) {
		BaseResult baseResult = new BaseResult();
		try {
			//检验群组名称是否存在
			Map<String, Object> params = new HashMap<String, Object>();
			params.put("groupName", userGroup.getGroupName());
			params.put("masterId", userGroup.getMasterId());
			int count = userGroupService.findCount(params);
			if (count > 0) {
				baseResult.setSuccess(PublicConstans.OPERTION_FAIL);
				baseResult.setMessage("群组名称已经存在！");
				return baseResult;
			}

			userGroupService.add(userGroup);
			//查询群组 保存位置信息
			List<UserGroup> list = userGroupService.findByBiz(new UserGroup(), params);
			if(null != list   && list.size()>0){
				UserGroup tempUserGroup = list.get(0);
				if (userGroup.getPositionX() != null && userGroup.getPositionY() != null) {
					double temp[] = { userGroup.getPositionX(), userGroup.getPositionY() };
					tempUserGroup.setGroupPosition(temp);
					mongoTemplate.indexOps(UserGroup.class).ensureIndex(new GeospatialIndex("groupPosition"));
					mongoTemplate.save(tempUserGroup);
				}
				//保存自己到群组成员中
				GroupUser groupUser = new GroupUser();
				
				groupUser.setGroupId(tempUserGroup.getId());
				groupUser.setUserId(tempUserGroup.getMasterId());
				groupUser.setMasterId(tempUserGroup.getMasterId());
				AppUser appUser = appUserService.findById(tempUserGroup.getMasterId().toString());
				if(null != appUser){
					groupUser.setUserAccount(appUser.getUserAccount());
					groupUser.setUserName(appUser.getUserName());
					groupUser.setUserImage(appUser.getUserImage());
				}
				userGroupService.addGroupUser(groupUser);
			}
			baseResult.getResultObj().put("userGroup", userGroup);
		} catch (ServiceException e) {
			logger.error("创建群组异常！", e);
			baseResult.setSuccess(PublicConstans.OPERTION_FAIL);
			baseResult.setMessage(e.getMessage());
		} catch (Exception e) {
			logger.error("创建群组异常！", e);
			baseResult.setSuccess(PublicConstans.OPERTION_FAIL);
			baseResult.setMessage(PublicConstans.OPERTION_FAIL_MESSAGE);
		}
		return baseResult;
	}

	/**
	 * 查询我的群组列表信息
	 * @param UserGroup
	 * @return
	 */
	@RequestMapping(value = "/myGroupList")
	@ResponseBody
	public BaseResult myGroupList(String userId) {
		BaseResult baseResult = new BaseResult();
		try {
			Map<String, Object> params = new HashMap<String, Object>();
			params.put("masterId", userId);
			List<UserGroup> groupList = userGroupService.findByBiz(new UserGroup(), params);
			baseResult.getResultObj().put("groupList", groupList);
		} catch (ServiceException e) {
			logger.error("修改群组异常！", e);
			baseResult.setSuccess(PublicConstans.OPERTION_FAIL);
			baseResult.setMessage(e.getMessage());
		} catch (Exception e) {
			logger.error("修改群组异常！", e);
			baseResult.setSuccess(PublicConstans.OPERTION_FAIL);
			baseResult.setMessage(PublicConstans.OPERTION_FAIL_MESSAGE);
		}
		return baseResult;
	}

	
	
	/**
	 * 查询群组信息
	 * @param UserGroup
	 * @return
	 */
	@RequestMapping(value = "/queryUserGroupInfo")
	@ResponseBody
	public BaseResult queryUserGroupInfo(String groupId) {
		BaseResult baseResult = new BaseResult();
		try {
		    UserGroup userGroup = userGroupService.findById(groupId);
			baseResult.getResultObj().put("userGroup", userGroup);
		} catch (ServiceException e) {
			logger.error("查询群组信息异常！", e);
			baseResult.setSuccess(PublicConstans.OPERTION_FAIL);
			baseResult.setMessage(e.getMessage());
		} catch (Exception e) {
			logger.error("查询群组信息异常！", e);
			baseResult.setSuccess(PublicConstans.OPERTION_FAIL);
			baseResult.setMessage(PublicConstans.OPERTION_FAIL_MESSAGE);
		}
		return baseResult;
	}
	
	
	/**
	 * 修改群组信息
	 * @param UserGroup
	 * @return
	 */
	@RequestMapping(value = "/updateGroup")
	@ResponseBody
	public BaseResult updateGroup(UserGroup userGroup) {
		BaseResult baseResult = new BaseResult();
		try {
			userGroupService.modifyById(userGroup);
			//修改位置信息
			if (userGroup.getPositionX() != null && userGroup.getPositionY() != null) {
				double temp[] = { userGroup.getPositionX(), userGroup.getPositionY() };
				userGroup.setGroupPosition(temp);
				mongoTemplate.indexOps(UserGroup.class).ensureIndex(new GeospatialIndex("groupPosition"));
				mongoTemplate.save(userGroup);
			}
			
		} catch (ServiceException e) {
			logger.error("修改群组异常！", e);
			baseResult.setSuccess(PublicConstans.OPERTION_FAIL);
			baseResult.setMessage(e.getMessage());
		} catch (Exception e) {
			logger.error("修改群组异常！", e);
			baseResult.setSuccess(PublicConstans.OPERTION_FAIL);
			baseResult.setMessage(PublicConstans.OPERTION_FAIL_MESSAGE);
		}
		return baseResult;
	}

	/**
	 * 删除群组
	 * @param UserGroup
	 * @return
	 */
	@RequestMapping(value = "/delGroup")
	@ResponseBody
	public BaseResult delGroup(UserGroup userGroup) {
		BaseResult baseResult = new BaseResult();
		try {
			Query query = new Query();
			Criteria criteria = Criteria.where("id").is(userGroup.getId());
			query.addCriteria(criteria);
			mongoTemplate.remove(query, UserGroup.class);
			userGroupService.deleteUserGroup(userGroup);
		} catch (ServiceException e) {
			logger.error("删除群组异常！", e);
			baseResult.setSuccess(PublicConstans.OPERTION_FAIL);
			baseResult.setMessage(e.getMessage());
		} catch (Exception e) {
			logger.error("删除群组异常！", e);
			baseResult.setSuccess(PublicConstans.OPERTION_FAIL);
			baseResult.setMessage(PublicConstans.OPERTION_FAIL_MESSAGE);
		}
		return baseResult;
	}

	
	/**
	 * 申请或者邀请加入群组
	 * @param UserGroup
	 * @return
	 */
	@RequestMapping(value = "/applyGroup")
	@ResponseBody
	public BaseResult applyGroup(GroupUserApply groupUserApply) {
		BaseResult baseResult = new BaseResult();
		try {
			
			//查询用户信息
			AppUser appUser = appUserService.findById(groupUserApply.getUserId().toString());
			if(null != appUser){
				groupUserApply.setUserAccount(appUser.getUserAccount());
				groupUserApply.setUserName(appUser.getUserName());
				groupUserApply.setUserImage(appUser.getUserImage());
			}
			userGroupService.addGroupUserApply(groupUserApply);
			
		} catch (ServiceException e) {
			logger.error("申请加入群组异常！", e);
			baseResult.setSuccess(PublicConstans.OPERTION_FAIL);
			baseResult.setMessage(e.getMessage());
		} catch (Exception e) {
			logger.error("申请加入群组异常！", e);
			baseResult.setSuccess(PublicConstans.OPERTION_FAIL);
			baseResult.setMessage(PublicConstans.OPERTION_FAIL_MESSAGE);
		}
		return baseResult;
	}

	/**
	 * 审核申请加入群组
	 * @param UserGroup
	 * @return
	 */
	@RequestMapping(value = "/auditGroup")
	@ResponseBody
	public BaseResult auditGroup(String id ,String status) {
		BaseResult baseResult = new BaseResult();
		try {
			if(StringUtils.isEmpty(id) || StringUtils.isEmpty(status)){
				baseResult.setSuccess(PublicConstans.OPERTION_FAIL);
				baseResult.setMessage("参数为空！");
			}
			
			userGroupService.auditGroup(id,status);
		} catch (ServiceException e) {
			logger.error("审核申请加入群组异常！", e);
			baseResult.setSuccess(PublicConstans.OPERTION_FAIL);
			baseResult.setMessage(e.getMessage());
		} catch (Exception e) {
			logger.error("审核申请加入群组异常！", e);
			baseResult.setSuccess(PublicConstans.OPERTION_FAIL);
			baseResult.setMessage(PublicConstans.OPERTION_FAIL_MESSAGE);
		}
		return baseResult;
	}

	/**
	 * 查询我的群组待审核申请信息
	 * @param UserGroup
	 * @return
	 */
	@RequestMapping(value = "/applyGroupList")
	@ResponseBody
	public BaseResult applyGroupList(String groupId) {
		BaseResult baseResult = new BaseResult();
		try {
			List<GroupUserApply> applyList = userGroupService.queryApplyGroupList(groupId,"","1"); 
			baseResult.getResultObj().put("applyList", applyList);
		} catch (ServiceException e) {
			logger.error("查询我的群组待审核申请信息异常！", e);
			baseResult.setSuccess(PublicConstans.OPERTION_FAIL);
			baseResult.setMessage(e.getMessage());
		} catch (Exception e) {
			logger.error("查询我的群组待审核申请信息异常！", e);
			baseResult.setSuccess(PublicConstans.OPERTION_FAIL);
			baseResult.setMessage(PublicConstans.OPERTION_FAIL_MESSAGE);
		}
		return baseResult;
	}

	/**
	 * 查询组邀请我加入群组待审核申请信息
	 * @param UserGroup
	 * @return
	 */
	@RequestMapping(value = "/inviteGroupList")
	@ResponseBody
	public BaseResult inviteGroupList(String userId) {
		BaseResult baseResult = new BaseResult();
		try {
			List<GroupUserApply> applyList = userGroupService.queryApplyGroupList("", userId, "0");
			baseResult.getResultObj().put("applyList", applyList);
		} catch (ServiceException e) {
			logger.error("查询组邀请我加入群组待审核申请信息异常！", e);
			baseResult.setSuccess(PublicConstans.OPERTION_FAIL);
			baseResult.setMessage(e.getMessage());
		} catch (Exception e) {
			logger.error("查询组邀请我加入群组待审核申请信息异常！", e);
			baseResult.setSuccess(PublicConstans.OPERTION_FAIL);
			baseResult.setMessage(PublicConstans.OPERTION_FAIL_MESSAGE);
		}
		return baseResult;
	}

	
	/**
	 * 查询群组人员信息
	 * @param UserGroup
	 * @return
	 */
	@RequestMapping(value = "/groupUserList")
	@ResponseBody
	public BaseResult groupUserList(String groupId) {
		BaseResult baseResult = new BaseResult();
		try {
			List<GroupUser> groupUserList = userGroupService.queryGroupUserList(groupId);
			baseResult.getResultObj().put("groupUserList", groupUserList);
		} catch (ServiceException e) {
			logger.error("查询群组人员信息异常！", e);
			baseResult.setSuccess(PublicConstans.OPERTION_FAIL);
			baseResult.setMessage(e.getMessage());
		} catch (Exception e) {
			logger.error("查询群组人员信息异常！", e);
			baseResult.setSuccess(PublicConstans.OPERTION_FAIL);
			baseResult.setMessage(PublicConstans.OPERTION_FAIL_MESSAGE);
		}
		return baseResult;
	}
	
	/**
	 * 发布群聊天消息
	 * @param UserGroup
	 * @return
	 */
	@RequestMapping(value = "/addGroupMsg")
	@ResponseBody
	public BaseResult addGroupMsg(GroupMsg groupMsg) {
		BaseResult baseResult = new BaseResult();
		try {
			//查询用户信息
			AppUser appUser = appUserService.findById(groupMsg.getUserId().toString());
			if(null != appUser){
				groupMsg.setUserAccount(appUser.getUserAccount());
				groupMsg.setUserName(appUser.getUserName());
				groupMsg.setUserImage(appUser.getUserImage());
			}
			userGroupService.addGroupMsg(groupMsg);
			baseResult.getResultObj().put("groupMsg", groupMsg);
		} catch (ServiceException e) {
			logger.error("发布群聊天消息异常！", e);
			baseResult.setSuccess(PublicConstans.OPERTION_FAIL);
			baseResult.setMessage(e.getMessage());
		} catch (Exception e) {
			logger.error("发布群聊天消息异常！", e);
			baseResult.setSuccess(PublicConstans.OPERTION_FAIL);
			baseResult.setMessage(PublicConstans.OPERTION_FAIL_MESSAGE);
		}
		return baseResult;
	}
	
	
	
	/**
	 * 查询群组聊天消息
	 * @param UserGroup
	 * @return
	 */
	@RequestMapping(value = "/queryGroupMsg")
	@ResponseBody
	public BaseResult queryGroupMsg(GroupMsgVo groupMsgVo) {
		BaseResult baseResult = new BaseResult();
		try {
			int pageNo = StringUtils.isEmpty(groupMsgVo.getPageNo()) ? 1 : Integer.parseInt(groupMsgVo.getPageNo());
			int pageSize = StringUtils.isEmpty(groupMsgVo.getPageSize()) ? 10 : Integer.parseInt(groupMsgVo
					.getPageSize());
			Map<String, Object> params = new HashMap<String, Object>();
			params.put("groupId", groupMsgVo.getGroupId());
			params.put("createTime", groupMsgVo.getCreateTime());
			int total = this.userGroupService.findGroupMsgCount(params);
			baseResult.getResultObj().put("total", total);
			if (total == 0) {
				baseResult.getResultObj().put("rows", new ArrayList<UserTalk>());
				return baseResult;
			}
			SimplePage page = new SimplePage(pageNo, pageSize, (int) total);
			List<GroupMsg> list = userGroupService.queryGroupMsg(page, params);
			baseResult.getResultObj().put("rows", list);
		} catch (ServiceException e) {
			logger.error("查询群组聊天消息异常！", e);
			baseResult.setSuccess(PublicConstans.OPERTION_FAIL);
			baseResult.setMessage(e.getMessage());
		} catch (Exception e) {
			logger.error("查询群组聊天消息异常！", e);
			baseResult.setSuccess(PublicConstans.OPERTION_FAIL);
			baseResult.setMessage(PublicConstans.OPERTION_FAIL_MESSAGE);
		}
		return baseResult;
	}

	/**
	 * 更新群组位置信息
	 * @param appUser
	 * @return
	 */
	@SuppressWarnings("unused")
	@RequestMapping(value = "/saveGroupLocation")
	@ResponseBody
	public BaseResult saveGroupLocation(UserGroup userGroup) {
		BaseResult baseResult = new BaseResult();
		try {
			Query query = new Query();
			Criteria criteria = Criteria.where("id").is(userGroup.getId());
			query.addCriteria(criteria);
			UserGroup tempUserGroup = mongoTemplate.findOne(query, UserGroup.class);
			double temp[] = { userGroup.getPositionX(), userGroup.getPositionY() };
			tempUserGroup.setGroupPosition(temp);
			if (null == tempUserGroup) {
				mongoTemplate.indexOps(UserGroup.class).ensureIndex(new GeospatialIndex("groupPosition"));
				mongoTemplate.save(tempUserGroup);
			} else {
				Update update = Update.update("positionX", userGroup.getPositionX())
						.set("positionY", userGroup.getPositionY())
						.set("groupPosition", temp);
				mongoTemplate.updateFirst(query, update, UserLocation.class);
			}
		} catch (Exception e) {
			logger.error("更新用户位置信息异常！", e);
			baseResult.setSuccess(PublicConstans.OPERTION_FAIL);
			baseResult.setMessage(PublicConstans.OPERTION_FAIL_MESSAGE);
		}
		return baseResult;
	}

	/**
	 * 分页查询附近的群组
	 * @param appUser
	 * @return
	 */
	@RequestMapping(value = "/searchNearGroup")
	@ResponseBody
	public BaseResult searchNearGroup(UserGroup userGroup) {
		BaseResult baseResult = new BaseResult();
		try {
			mongoTemplate.indexOps(UserGroup.class).ensureIndex(new GeospatialIndex("groupPosition"));

			String command = "{geoNear:\"userGroup\",near:[" + userGroup.getPositionX() + ","
					+ userGroup.getPositionY() + "],num:" + userGroup.getQueryNum() + ",spherical:true";
			if (userGroup.getMaxDistance() > 0) {

				BigDecimal b1 = new BigDecimal(Double.toString(userGroup.getMaxDistance()));

				BigDecimal b2 = new BigDecimal(Double.toString(6378137));

				String maxDistance = b1.divide(b2, 10, BigDecimal.ROUND_HALF_UP).toPlainString();

				command = command + ",maxDistance:" + maxDistance + ",distanceMultiplier:6378137";
			}
			command = command + "})";

			if (logger.isInfoEnabled()) {
				logger.info("查询附近的群:" + command);
			}
			CommandResult commandResult = mongoTemplate.executeCommand(command);
			if (null != commandResult) {
				List<BasicDBObject> list = (List<BasicDBObject>) commandResult.get("results");
				if (null != list && list.size() > 0) {
					List<GroupInfo> groupInfoList = new ArrayList<GroupInfo>();
					for (BasicDBObject basicDBObject : list) {
						GroupInfo groupInfo = new GroupInfo();
						BasicDBObject obj = (BasicDBObject) basicDBObject.get("obj");
						Long groupId = obj.getLong("_id");
						if (null != userGroup.getId() && userGroup.getId().longValue() == groupId.longValue()) {
							continue;
						}
						groupInfo.setId(groupId);

						double temp = basicDBObject.getDouble("dis");
						if (temp > 0) {
							BigDecimal bg = new BigDecimal(temp);
							double dis = bg.setScale(0, BigDecimal.ROUND_HALF_UP).doubleValue();
							BigDecimal bd = new BigDecimal(dis);
							String disChar = bd.toPlainString();
							//disChar = disChar.substring(0, disChar.length() - 2);
							groupInfo.setDistance(Long.valueOf(disChar));
						} else {
							groupInfo.setDistance(0L);
						}

						BasicDBList dbList = (BasicDBList) obj.get("groupPosition");
						groupInfo.setLongitude((Double) dbList.get(0));
						groupInfo.setLatitude((Double) dbList.get(1));
						groupInfoList.add(groupInfo);
					}

					for (GroupInfo groupInfo : groupInfoList) {
						UserGroup tempUserGroup = userGroupService.findById(groupInfo.getId().toString());
						if (null != tempUserGroup) {
							groupInfo.setGroupImage(tempUserGroup.getGroupImage());
							groupInfo.setGroupName(tempUserGroup.getGroupName());
							groupInfo.setRemark(tempUserGroup.getRemark());
							groupInfo.setMasterId(tempUserGroup.getMasterId());
							groupInfo.setCreateTime(tempUserGroup.getCreateTime());
						}
					}

					baseResult.getResultObj().put("groupInfoList", groupInfoList);
				}
			}
		} catch (Exception e) {
			logger.error("查询附近的群异常！", e);
			baseResult.setSuccess(PublicConstans.OPERTION_FAIL);
			baseResult.setMessage(PublicConstans.OPERTION_FAIL_MESSAGE);
		}
		return baseResult;
	}

	
	
	/**
	 * 搜索群组信息
	 * @param UserGroup
	 * @return
	 */
	@RequestMapping(value = "/searchUserGroup")
	@ResponseBody
	public BaseResult searchUserGroup(String groupName,String groupAccount) {
		BaseResult baseResult = new BaseResult();
		try {
			if(StringUtils.isEmpty(groupName) && StringUtils.isEmpty(groupAccount)){
				baseResult.setSuccess(PublicConstans.OPERTION_FAIL);
				baseResult.setMessage("搜索参数为空！");
				return baseResult;
			}
			Map<String, Object> params = new HashMap<String, Object>();
			params.put("groupName", groupName);
			params.put("groupAccount", groupAccount);
			
		    List<UserGroup> userGroupList = userGroupService.findByBiz(null, params);
		    if(null != userGroupList  &&  userGroupList.size()>0){
		    	baseResult.getResultObj().put("userGroup", userGroupList.get(0));
		    }
		} catch (ServiceException e) {
			logger.error("搜索群组异常！", e);
			baseResult.setSuccess(PublicConstans.OPERTION_FAIL);
			baseResult.setMessage(e.getMessage());
		} catch (Exception e) {
			logger.error("搜索群组异常！", e);
			baseResult.setSuccess(PublicConstans.OPERTION_FAIL);
			baseResult.setMessage(PublicConstans.OPERTION_FAIL_MESSAGE);
		}
		return baseResult;
	}
	
	
	
	
}
