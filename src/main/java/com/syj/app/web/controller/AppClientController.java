package com.syj.app.web.controller;

import java.io.File;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.index.GeospatialIndex;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.mongodb.BasicDBList;
import com.mongodb.BasicDBObject;
import com.mongodb.CommandResult;
import com.syj.app.common.PublicConstans;
import com.syj.app.exception.ServiceException;
import com.syj.app.util.CommonUtil;
import com.syj.app.util.DateUtil;
import com.syj.app.web.model.ApkVersion;
import com.syj.app.web.model.AppActivate;
import com.syj.app.web.model.AppUser;
import com.syj.app.web.model.SystemParam;
import com.syj.app.web.model.UserLocation;
import com.syj.app.web.service.ApkVersionService;
import com.syj.app.web.service.AppActivateService;
import com.syj.app.web.service.AppUserService;
import com.syj.app.web.service.SystemParamService;
import com.syj.app.web.vo.BaseResult;
import com.syj.app.web.vo.UserInfo;

/**
 *APP 基本功能接口
 * 
 * @author she.yj
 * @date 2014-5-23 下午5:12:48
 * @version 0.1.0 
 * @copyright syj.com 
 */
@Controller
@RequestMapping("/")
public class AppClientController {

	// 日志对象
	private static final Logger logger = Logger.getLogger(AppClientController.class);

	@Resource
	private AppUserService appUserService;

	@Resource
	private SystemParamService systemParamService;

	@Resource
	private ApkVersionService apkVersionService;

	@Resource
	private AppActivateService appActivateService;

	@Resource
	private MongoTemplate mongoTemplate;

	//@Resource
	//private AppFileService appFileService;

	/**
	 * APP激活
	 * @param appUser
	 * @return
	 */
	@RequestMapping(value = "/appActivate")
	@ResponseBody
	public BaseResult appActivate(AppActivate appActivate) {
		BaseResult baseResult = new BaseResult();
		try {
			//校验参数是否为空
			if (StringUtils.isEmpty(appActivate.getDeviceId())) {
				baseResult.setSuccess(PublicConstans.OPERTION_FAIL);
				baseResult.setMessage("必填参数为空！");
				return baseResult;
			}

			//校验用户名称和账户是否存在
			Map<String, Object> params = new HashMap<String, Object>();
			params.put("deviceId", appActivate.getDeviceId());
			int count = appActivateService.findCount(params);
			if (count > 0) {
				baseResult.setSuccess(PublicConstans.OPERTION_FAIL);
				baseResult.setMessage("APP已经激活！");
				return baseResult;
			}

			appActivateService.add(appActivate);
		} catch (ServiceException e) {
			logger.error("APP激活异常！", e);
			baseResult.setSuccess(PublicConstans.OPERTION_FAIL);
			baseResult.setMessage(e.getMessage());
		} catch (Exception e) {
			logger.error("APP激活异常！", e);
			baseResult.setSuccess(PublicConstans.OPERTION_FAIL);
			baseResult.setMessage(PublicConstans.OPERTION_FAIL_MESSAGE);
		}
		return baseResult;
	}

	/**
	 * 用户登录
	 * @param appUser
	 * @return
	 */
	@RequestMapping(value = "/loginUser")
	@ResponseBody
	public BaseResult loginUser(AppUser appUser) {
		BaseResult baseResult = new BaseResult();
		try {
			//校验参数是否为空
			if (StringUtils.isEmpty(appUser.getUserAccount()) || StringUtils.isEmpty(appUser.getUserPassword())) {
				baseResult.setSuccess(PublicConstans.OPERTION_FAIL);
				baseResult.setMessage("用户账号或者用户密码为空！");
				return baseResult;
			}
			Map<String, Object> params = new HashMap<String, Object>();
			params.put("status", "1");
			if (StringUtils.isNotBlank(appUser.getUserAccount())) {
				params.put("userAccount", appUser.getUserAccount());
			}
			if (StringUtils.isNotBlank(appUser.getUserPassword())) {
				params.put("userPassword", CommonUtil.md5(appUser.getUserPassword()));
			}

			List<AppUser> queryAppUserList = appUserService.findByBiz(null, params);
			if (null != queryAppUserList && queryAppUserList.size() > 0) {
				AppUser tempAppUser = queryAppUserList.get(0);
				//修改用户登录时间
				AppUser updateAppUser = new AppUser();
				updateAppUser.setId(tempAppUser.getId());
				updateAppUser.setLastLoginTime(new Date());
				if (StringUtils.isNotBlank(appUser.getLocation())) {
					updateAppUser.setLocation(appUser.getLocation());
				}
				//				AppFile appFile = new AppFile();
				//				Map<String, Object> paramsAppFile = new HashMap<String, Object>();
				//				paramsAppFile.put("ownerId", tempAppUser.getId());
				//				List<AppFile> appFileList = appFileService.findByBiz(appFile, paramsAppFile);
				//
				//				if (null != appFileList && appFileList.size() > 0) {
				//					tempAppUser.setAppFile(appFileList.get(0));
				//				}

				appUserService.modifyById(updateAppUser);
				baseResult.getResultObj().put("appUser", tempAppUser);
			} else {
				baseResult.setSuccess(PublicConstans.OPERTION_FAIL);
				baseResult.setMessage("用户不存在或者密码错误！");
			}

		} catch (ServiceException e) {
			logger.error("用户登录异常！", e);
			baseResult.setSuccess(PublicConstans.OPERTION_FAIL);
			baseResult.setMessage(e.getMessage());
		} catch (Exception e) {
			logger.error("用户登录异常！", e);
			baseResult.setSuccess(PublicConstans.OPERTION_FAIL);
			baseResult.setMessage(PublicConstans.OPERTION_FAIL_MESSAGE);
		}
		return baseResult;
	}

	/**
	 * 新增用户
	 * @param appUser
	 * @return
	 */
	@RequestMapping(value = "/addUser")
	@ResponseBody
	public BaseResult addUser(AppUser appUser) {
		BaseResult baseResult = new BaseResult();
		try {
			//校验参数是否为空
			if (StringUtils.isEmpty(appUser.getUserAccount()) || StringUtils.isEmpty(appUser.getUserName())) {
				baseResult.setSuccess(PublicConstans.OPERTION_FAIL);
				baseResult.setMessage("用户账号或者用户名称为空！");
				return baseResult;
			}

			if (StringUtils.isNotBlank(appUser.getUserBirthdayStr())) {
				appUser.setUserBirthday(DateUtil.getdate(appUser.getUserBirthdayStr()));
			}

			//校验用户名称和账户是否存在
			Map<String, Object> params = new HashMap<String, Object>();
			params.put("userAccount", appUser.getUserAccount());
			int count = appUserService.findCount(params);
			if (count > 0) {
				baseResult.setSuccess(PublicConstans.OPERTION_FAIL);
				baseResult.setMessage("用户账号已经存在！");
				return baseResult;
			}
			params.clear();
			params.put("userName", appUser.getUserName());

			count = appUserService.findCount(params);
			if (count > 0) {
				baseResult.setSuccess(PublicConstans.OPERTION_FAIL);
				baseResult.setMessage("用户名称已经存在！");
				return baseResult;
			}

			appUser.setUserPassword(CommonUtil.md5(appUser.getUserPassword()));
			appUserService.add(appUser);

			//			//保存用户头像
			//			List<AppUser> appUserList = appUserService.findByBiz(appUser, params);
			//			if (null != appUserList && appUserList.size() > 0 && StringUtils.isNotBlank(appUser.getUserImage())) {
			//				AppUser tempAppUser = appUserList.get(0);
			//				String[] images = appUser.getUserImage().split(";");
			//				if (images.length > 0) {
			//					AppFile appFile = new AppFile();
			//					appFile.setImageType(PublicConstans.APP_IMAGE_TYPE_1);
			//					appFile.setOwnerId(tempAppUser.getId());
			//					appFile.setImagePath(images[0]);
			//					appFileService.add(appFile);
			//				}
			//			}

		} catch (ServiceException e) {
			logger.error("新增用户异常！", e);
			baseResult.setSuccess(PublicConstans.OPERTION_FAIL);
			baseResult.setMessage(e.getMessage());
		} catch (Exception e) {
			logger.error("新增用户异常！", e);
			baseResult.setSuccess(PublicConstans.OPERTION_FAIL);
			baseResult.setMessage(PublicConstans.OPERTION_FAIL_MESSAGE);
		}
		return baseResult;
	}

	/**
	 * 查询用户信息
	 * @param appUser
	 * @return
	 */
	@RequestMapping(value = "/findUser")
	@ResponseBody
	public BaseResult findUser(AppUser appUser) {
		BaseResult baseResult = new BaseResult();
		try {
			//校验参数是否为空
			if (StringUtils.isEmpty(appUser.getUserAccount()) && appUser.getId() == null) {
				baseResult.setSuccess(PublicConstans.OPERTION_FAIL);
				baseResult.setMessage("参数为空！");
				return baseResult;
			}
			Map<String, Object> params = new HashMap<String, Object>();
			params.put("status", "1");
			if (StringUtils.isNotBlank(appUser.getUserAccount())) {
				params.put("userAccount", appUser.getUserAccount());
			}
			if (appUser.getId() != null) {
				params.put("id", appUser.getId());
			}

			List<AppUser> queryAppUserList = appUserService.findByBiz(null, params);
			if (null != queryAppUserList && queryAppUserList.size() > 0) {
				AppUser tempAppUser = queryAppUserList.get(0);
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
				baseResult.setMessage("查询用户不存在！");
			}
		} catch (ServiceException e) {
			logger.error("查询用户信息异常！", e);
			baseResult.setSuccess(PublicConstans.OPERTION_FAIL);
			baseResult.setMessage(e.getMessage());
		} catch (Exception e) {
			logger.error("查询用户信息异常！", e);
			baseResult.setSuccess(PublicConstans.OPERTION_FAIL);
			baseResult.setMessage(PublicConstans.OPERTION_FAIL_MESSAGE);
		}
		return baseResult;
	}

	/**
	 * 修改用户信息
	 * @param appUser
	 * @return
	 */
	@RequestMapping(value = "/updateUser")
	@ResponseBody
	public BaseResult updateUser(AppUser appUser) {
		BaseResult baseResult = new BaseResult();
		try {
			//校验参数是否为空
			if (appUser.getId() == null && StringUtils.isEmpty(appUser.getUserAccount())) {
				baseResult.setSuccess(PublicConstans.OPERTION_FAIL);
				baseResult.setMessage("用户账号或者用户名称为空！");
				return baseResult;
			}

			if (StringUtils.isNotBlank(appUser.getUserBirthdayStr())) {
				appUser.setUserBirthday(DateUtil.getdate(appUser.getUserBirthdayStr()));
			}
			appUserService.modifyById(appUser);
			//			if (StringUtils.isNotBlank(appUser.getUserImage())) {
			//				String[] images = appUser.getUserImage().split(";");
			//				if (images.length > 0) {
			//					AppFile appFile = new AppFile();
			//					Map<String, Object> paramsAppFile = new HashMap<String, Object>();
			//					paramsAppFile.put("ownerId", appUser.getId());
			//					List<AppFile> appFileList = appFileService.findByBiz(appFile, paramsAppFile);
			//
			//					if (null != appFileList && appFileList.size() > 0) {
			//						for (AppFile appFileTemp : appFileList) {
			//							appFileService.deleteById(appFileTemp);
			//						}
			//					}
			//
			//					appFile.setImageType(PublicConstans.APP_IMAGE_TYPE_1);
			//					appFile.setOwnerId(appUser.getId());
			//
			//					appFile.setImagePath(images[0]);
			//					appFileService.add(appFile);
			//				}
			//			}
		} catch (ServiceException e) {
			logger.error("修改用户异常！", e);
			baseResult.setSuccess(PublicConstans.OPERTION_FAIL);
			baseResult.setMessage(e.getMessage());
		} catch (Exception e) {
			logger.error("修改用户异常！", e);
			baseResult.setSuccess(PublicConstans.OPERTION_FAIL);
			baseResult.setMessage(PublicConstans.OPERTION_FAIL_MESSAGE);
		}
		return baseResult;
	}

	/**
	 * 修改用户密码
	 * @param appUser
	 * @return
	 */
	@RequestMapping(value = "/updateUserPassword")
	@ResponseBody
	public BaseResult updateUserPassword(AppUser appUser) {
		BaseResult baseResult = new BaseResult();
		try {
			//校验参数是否为空
			if (appUser.getId() == null && StringUtils.isEmpty(appUser.getUserAccount())) {
				baseResult.setSuccess(PublicConstans.OPERTION_FAIL);
				baseResult.setMessage("用户账号或者用户名称为空！");
				return baseResult;
			}
			AppUser updateAppUser = new AppUser();
			updateAppUser.setId(appUser.getId());
			updateAppUser.setUserAccount(appUser.getUserAccount());
			//	updateAppUser.setUserName(appUser.getUserName());
			updateAppUser.setUserPassword(CommonUtil.md5(appUser.getUserPassword()));

			appUserService.modifyById(updateAppUser);

		} catch (ServiceException e) {
			logger.error("修改用户密码异常！", e);
			baseResult.setSuccess(PublicConstans.OPERTION_FAIL);
			baseResult.setMessage(e.getMessage());
		} catch (Exception e) {
			logger.error("修改用户密码异常！", e);
			baseResult.setSuccess(PublicConstans.OPERTION_FAIL);
			baseResult.setMessage(PublicConstans.OPERTION_FAIL_MESSAGE);
		}
		return baseResult;
	}

	/**
	 * 获取系统参数
	 * @param appUser
	 * @return
	 */
	@RequestMapping(value = "/getSystemParam")
	@ResponseBody
	public BaseResult getSystemParam() {
		BaseResult baseResult = new BaseResult();
		try {
			Map<String, Object> params = new HashMap<String, Object>();
			params.put("status", "1");
			params.put("paramType", "1");
			//校验参数是否为空
			List<SystemParam> paramList = systemParamService.findByBiz(null, params);
			baseResult.getResultObj().put("paramList", paramList);
		} catch (ServiceException e) {
			logger.error("获取系统参数异常！", e);
			baseResult.setSuccess(PublicConstans.OPERTION_FAIL);
			baseResult.setMessage(e.getMessage());
		} catch (Exception e) {
			logger.error("获取系统参数异常！", e);
			baseResult.setSuccess(PublicConstans.OPERTION_FAIL);
			baseResult.setMessage(PublicConstans.OPERTION_FAIL_MESSAGE);
		}
		return baseResult;
	}

	/**
	 * 获取最新版本
	 * @param appUser
	 * @return
	 */
	@RequestMapping(value = "/getNewVersion")
	@ResponseBody
	public BaseResult getNewVersion() {
		BaseResult baseResult = new BaseResult();
		try {
			ApkVersion apkVersion = apkVersionService.getNewVersion();
			baseResult.getResultObj().put("apkVersion", apkVersion);
		} catch (ServiceException e) {
			logger.error("版本更新异常！", e);
			baseResult.setSuccess(PublicConstans.OPERTION_FAIL);
			baseResult.setMessage(e.getMessage());
		} catch (Exception e) {
			logger.error("版本更新异常！", e);
			baseResult.setSuccess(PublicConstans.OPERTION_FAIL);
			baseResult.setMessage(PublicConstans.OPERTION_FAIL_MESSAGE);
		}
		return baseResult;
	}

	/**
	 * 更新用户位置信息
	 * @param appUser
	 * @return
	 */
	@RequestMapping(value = "/saveLocation")
	@ResponseBody
	public BaseResult saveLocation(UserLocation userLocation) {
		BaseResult baseResult = new BaseResult();
		try {
			Query query = new Query();
			Criteria criteria = Criteria.where("id").is(userLocation.getId());
			query.addCriteria(criteria);
			userLocation.setCreateTime(new Date());
			UserLocation tempUserLocation = mongoTemplate.findOne(query, UserLocation.class);
			double temp[] = { userLocation.getLongitude(), userLocation.getLatitude() };
			userLocation.setPosition(temp);
			if (null == tempUserLocation) {
				mongoTemplate.indexOps(UserLocation.class).ensureIndex(new GeospatialIndex("position"));
				mongoTemplate.save(userLocation);
			} else {
				Update update = Update.update("latitude", userLocation.getLatitude())
						.set("longitude", userLocation.getLongitude()).set("createTime", userLocation.getCreateTime())
						.set("position", temp);
				mongoTemplate.updateFirst(query, update, UserLocation.class);
			}
			AppUser updateAppUser = new AppUser();
			updateAppUser.setId(userLocation.getId());
			updateAppUser.setPositionX(Float.valueOf(String.valueOf(userLocation.getLongitude())));
			updateAppUser.setPositionY(Float.valueOf(String.valueOf(userLocation.getLatitude())));
			appUserService.modifyById(updateAppUser);

			baseResult.getResultObj().put("userLocation", userLocation);
		} catch (Exception e) {
			logger.error("更新用户位置信息异常！", e);
			baseResult.setSuccess(PublicConstans.OPERTION_FAIL);
			baseResult.setMessage(PublicConstans.OPERTION_FAIL_MESSAGE);
		}
		return baseResult;
	}

	/**
	 * 分页查询附近的人
	 * @param appUser
	 * @return
	 */
	@RequestMapping(value = "/searchNear")
	@ResponseBody
	public BaseResult searchNear(UserLocation userLocation) {
		BaseResult baseResult = new BaseResult();
		try {
			mongoTemplate.indexOps(UserLocation.class).ensureIndex(new GeospatialIndex("position"));

			String command = "{geoNear:\"userLocation\",near:[" + userLocation.getLongitude() + ","
					+ userLocation.getLatitude() + "],num:" + userLocation.getQueryNum()
					+ ",spherical:true";
			if (userLocation.getMaxDistance() > 0) {
				
				 BigDecimal b1 = new BigDecimal(Double.toString(userLocation.getMaxDistance()));

			        BigDecimal b2 = new BigDecimal(Double.toString(6378137));

				String maxDistance =b1.divide(b2, 10, BigDecimal.ROUND_HALF_UP).toPlainString();
				
				command = command + ",maxDistance:" + maxDistance+",distanceMultiplier:6378137";
			}
			command = command + "})";
			
			if(logger.isInfoEnabled()){
				logger.info("查询附近的人:"+command);
			}
			CommandResult commandResult = mongoTemplate.executeCommand(command);
			if (null != commandResult) {
				List<BasicDBObject> list = (List<BasicDBObject>) commandResult.get("results");
				if (null != list && list.size() > 0) {
					List<UserInfo> userInfoList = new ArrayList<UserInfo>();
					for (BasicDBObject basicDBObject : list) {
						UserInfo userInfo = new UserInfo();
						BasicDBObject obj = (BasicDBObject) basicDBObject.get("obj");
						Long userId = obj.getLong("_id");
						if (null != userLocation.getId() && userLocation.getId().longValue() == userId.longValue()) {
							continue;
						}
						userInfo.setId(userId);

						double temp = basicDBObject.getDouble("dis");
						if (temp > 0) {
							BigDecimal bg = new BigDecimal(temp);
							double dis = bg.setScale(0, BigDecimal.ROUND_HALF_UP).doubleValue();
							BigDecimal bd = new BigDecimal(dis );
							String disChar = bd.toPlainString();
							//disChar = disChar.substring(0, disChar.length() - 2);
							userInfo.setDistance(Long.valueOf(disChar));
						} else {
							userInfo.setDistance(0L);
						}

						BasicDBList dbList = (BasicDBList) obj.get("position");
						userInfo.setLongitude((Double) dbList.get(0));
						userInfo.setLatitude((Double) dbList.get(1));
						userInfoList.add(userInfo);
					}

					for (UserInfo userInfo : userInfoList) {
						AppUser tempAppUser = appUserService.findById(userInfo.getId().toString());
						if (null != tempAppUser) {
							userInfo.setUserAccount(tempAppUser.getUserAccount());
							userInfo.setUserImage(tempAppUser.getUserImage());
							userInfo.setUserName(tempAppUser.getUserName());
							userInfo.setRemark(tempAppUser.getRemark());
						}
					}

					baseResult.getResultObj().put("userInfoList", userInfoList);
				}
			}
		} catch (Exception e) {
			logger.error("查询附近的人异常！", e);
			baseResult.setSuccess(PublicConstans.OPERTION_FAIL);
			baseResult.setMessage(PublicConstans.OPERTION_FAIL_MESSAGE);
		}
		return baseResult;
	}

	
	/**
	 * 删除上传文件
	 * @param appUser
	 * @return
	 */
	@RequestMapping(value = "/delFile")
	@ResponseBody
	public BaseResult delFile(String filePath,HttpServletRequest  request) {
		BaseResult baseResult = new BaseResult();
		try {
			if(StringUtils.isNotBlank(filePath)){
				
				String userDir =  request.getSession().getServletContext().getRealPath("/");
				String[] temp =  filePath.split(";");
				for(String tempFilePath :temp){
					if(StringUtils.isEmpty(tempFilePath)){
						continue;
					}
					 File file = new File(userDir+File.separator+tempFilePath);  
					 // 路径为文件且不为空则进行删除  
				    if (file.isFile() && file.exists()) {  
				        file.delete();  
				    }  
				}
			}
			
		} catch (Exception e) {
			logger.error(" 删除上传文件异常！", e);
			baseResult.setSuccess(PublicConstans.OPERTION_FAIL);
			baseResult.setMessage(PublicConstans.OPERTION_FAIL_MESSAGE);
		}
		return baseResult;
	}
	
	
}
