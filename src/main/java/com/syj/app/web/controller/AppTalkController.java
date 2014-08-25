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
import com.syj.app.web.model.AppFile;
import com.syj.app.web.model.AppUser;
import com.syj.app.web.model.TalkResponse;
import com.syj.app.web.model.UserTalk;
import com.syj.app.web.service.AppFileService;
import com.syj.app.web.service.AppUserService;
import com.syj.app.web.service.TalkResponseService;
import com.syj.app.web.service.UserTalkService;
import com.syj.app.web.vo.BaseResult;
import com.syj.app.web.vo.TalkQueryVo;

/**
 * APP 说说 评论接口
 * @author she.yj
 * @date 2014-5-24 上午7:14:20
 * @version 0.1.0 
 * @copyright syj.com 
 */
@Controller
@RequestMapping("/")
public class AppTalkController {

	// 日志对象
	private static final Logger logger = Logger.getLogger(AppTalkController.class);

	@Resource
	private UserTalkService userTalkService;

	@Resource
	private TalkResponseService talkResponseService;

	@Resource
	private AppFileService appFileService;

	@Resource
	private AppUserService appUserService;
	
	/**
	 * 用户发布说说
	 * @param appUser
	 * @return
	 */
	@RequestMapping(value = "/userTalk")
	@ResponseBody
	public BaseResult userTalk(UserTalk userTalk) {
		BaseResult baseResult = new BaseResult();
		try {
			
			String imagePath = userTalk.getImagePath();
			AppUser appUser = appUserService.findById(userTalk.getUserId().toString());
			if(null != appUser){
				userTalk.setImagePath(appUser.getUserImage());
				userTalk.setUserName(appUser.getUserName());
				userTalk.setUserAccount(appUser.getUserAccount());
			}
			userTalkService.add(userTalk);
			UserTalk tempUserTalk = userTalkService.getLastUserTalk(userTalk);
			//处理图片路径
			if (StringUtils.isNotBlank(imagePath)) {
				if (null != tempUserTalk) {
					String[] images = imagePath.split(";");
					if (images.length > 0) {
						List<AppFile> imageList = new ArrayList<AppFile>();
						for (String image : images) {
							if (StringUtils.isNotBlank(image)) {
								AppFile appFile = new AppFile();
								appFile.setImageType(PublicConstans.APP_IMAGE_TYPE_2);
								appFile.setOwnerId(tempUserTalk.getId());
								appFile.setImagePath(image);
								appFileService.add(appFile);
								imageList.add(appFile);
							}
						}
						tempUserTalk.setImageList(imageList);
					}
				}
			}
			baseResult.getResultObj().put("userTalk", tempUserTalk);
		} catch (ServiceException e) {
			logger.error("用户发布说说异常！", e);
			baseResult.setSuccess(PublicConstans.OPERTION_FAIL);
			baseResult.setMessage(e.getMessage());
		} catch (Exception e) {
			logger.error("用户发布说说异常！", e);
			baseResult.setSuccess(PublicConstans.OPERTION_FAIL);
			baseResult.setMessage(PublicConstans.OPERTION_FAIL_MESSAGE);
		}
		return baseResult;
	}

	/**
	 * 用户修改说说
	 * @param appUser
	 * @return
	 */
	@RequestMapping(value = "/userTalkUpdate")
	@ResponseBody
	public BaseResult userTalkUpdate(UserTalk userTalk) {
		BaseResult baseResult = new BaseResult();
		try {
			
			String imagePath = userTalk.getImagePath();
			//处理图片路径
			AppUser appUser = appUserService.findById(userTalk.getUserId().toString());
			if(null != appUser){
				userTalk.setImagePath(appUser.getUserImage());
				userTalk.setUserName(appUser.getUserName());
				userTalk.setUserAccount(appUser.getUserAccount());
			}
			userTalkService.modifyById(userTalk);

			AppFile tempAppFile = new AppFile();
			Map<String, Object> paramsAppFile = new HashMap<String, Object>();
			paramsAppFile.put("ownerId", userTalk.getId());
			paramsAppFile.put("imageType", PublicConstans.APP_IMAGE_TYPE_2);
			List<AppFile> appFileList = appFileService.findByBiz(tempAppFile, paramsAppFile);

			if (null != appFileList && appFileList.size() > 0) {
				for (AppFile appFileTemp : appFileList) {
					appFileService.deleteById(appFileTemp);
				}
			}

			List<AppFile> fileList = new ArrayList<AppFile>();
			if (StringUtils.isNotBlank(imagePath)) {
				String[] images = imagePath.split(";");
				if (images.length > 0) {
					for (String image : images) {
						if (StringUtils.isNotBlank(image)) {
							AppFile appFile = new AppFile();
							appFile.setImageType(PublicConstans.APP_IMAGE_TYPE_2);
							appFile.setOwnerId(userTalk.getId());
							appFile.setImagePath(image);
							fileList.add(appFile);
							appFileService.add(appFile);
						}
					}
				}
			}
			userTalk.setImageList(fileList);
			baseResult.getResultObj().put("userTalk", userTalk);
		} catch (ServiceException e) {
			logger.error("用户修改说说异常！", e);
			baseResult.setSuccess(PublicConstans.OPERTION_FAIL);
			baseResult.setMessage(e.getMessage());
		} catch (Exception e) {
			logger.error("用户修改说说异常！", e);
			baseResult.setSuccess(PublicConstans.OPERTION_FAIL);
			baseResult.setMessage(PublicConstans.OPERTION_FAIL_MESSAGE);
		}
		return baseResult;
	}

	/**
	 * 用户删除说说
	 * @param appUser
	 * @return
	 */
	@RequestMapping(value = "/userTalkDel")
	@ResponseBody
	public BaseResult userTalkDel(UserTalk userTalk) {
		BaseResult baseResult = new BaseResult();
		try {
			if (userTalk.getId() == null) {
				baseResult.setSuccess(PublicConstans.OPERTION_FAIL);
				baseResult.setMessage("参数为空！");
				return baseResult;
			}
			//处理图片路径
			userTalkService.deleteTalkAndResponse(userTalk);

			AppFile tempAppFile = new AppFile();
			Map<String, Object> paramsAppFile = new HashMap<String, Object>();
			paramsAppFile.put("ownerId", userTalk.getId());
			paramsAppFile.put("imageType", PublicConstans.APP_IMAGE_TYPE_2);
			List<AppFile> appFileList = appFileService.findByBiz(tempAppFile, paramsAppFile);

			if (null != appFileList && appFileList.size() > 0) {
				for (AppFile appFileTemp : appFileList) {
					appFileService.deleteById(appFileTemp);
				}
			}

		} catch (ServiceException e) {
			logger.error("用户删除说说异常！", e);
			baseResult.setSuccess(PublicConstans.OPERTION_FAIL);
			baseResult.setMessage(e.getMessage());
		} catch (Exception e) {
			logger.error("用户删除说说异常！", e);
			baseResult.setSuccess(PublicConstans.OPERTION_FAIL);
			baseResult.setMessage(PublicConstans.OPERTION_FAIL_MESSAGE);
		}
		return baseResult;
	}

	/**
	 * 用户发布说说评论
	 * @param appUser
	 * @return
	 */
	@RequestMapping(value = "/userTalkResponse")
	@ResponseBody
	public BaseResult userTalkResponse(TalkResponse talkResponse) {
		BaseResult baseResult = new BaseResult();
		try {
			talkResponseService.add(talkResponse);
			TalkResponse tempTalkResponse = talkResponseService.getLastTalkResponse(talkResponse);
			baseResult.getResultObj().put("talkResponse", tempTalkResponse);
		} catch (ServiceException e) {
			logger.error("用户发布说说评论异常！", e);
			baseResult.setSuccess(PublicConstans.OPERTION_FAIL);
			baseResult.setMessage(e.getMessage());
		} catch (Exception e) {
			logger.error("用户发布说说评论异常！", e);
			baseResult.setSuccess(PublicConstans.OPERTION_FAIL);
			baseResult.setMessage(PublicConstans.OPERTION_FAIL_MESSAGE);
		}
		return baseResult;
	}

	/**
	 * 删除用户发布说说评论
	 * @param appUser
	 * @return
	 */
	@RequestMapping(value = "/userTalkResponseDel")
	@ResponseBody
	public BaseResult userTalkResponseDel(TalkResponse talkResponse) {
		BaseResult baseResult = new BaseResult();
		try {
			if (null == talkResponse.getId()) {
				baseResult.setSuccess(PublicConstans.OPERTION_FAIL);
				baseResult.setMessage("参数为空！");
				return baseResult;
			}
			talkResponseService.deleteById(talkResponse);
		} catch (ServiceException e) {
			logger.error("用户发布说说评论异常！", e);
			baseResult.setSuccess(PublicConstans.OPERTION_FAIL);
			baseResult.setMessage(e.getMessage());
		} catch (Exception e) {
			logger.error("用户发布说说评论异常！", e);
			baseResult.setSuccess(PublicConstans.OPERTION_FAIL);
			baseResult.setMessage(PublicConstans.OPERTION_FAIL_MESSAGE);
		}
		return baseResult;
	}

	/**
	 * 查询用户说说及评论
	 * @param req
	 * @param model
	 * @return
	 * @throws ServiceException
	 */
	@RequestMapping(value = "/talkList")
	@ResponseBody
	public BaseResult talkList(TalkQueryVo talkQueryVo) throws ServiceException {
		BaseResult baseResult = new BaseResult();
		try {
			int pageNo = StringUtils.isEmpty(talkQueryVo.getPageNo()) ? 1 : Integer.parseInt(talkQueryVo.getPageNo());
			int pageSize = StringUtils.isEmpty(talkQueryVo.getPageSize()) ? 10 : Integer.parseInt(talkQueryVo
					.getPageSize());

			Map<String, Object> params = new HashMap<String, Object>();
			params.put("status", 1);
			params.put("userId", talkQueryVo.getUserId());
			params.put("lastQueryTime", talkQueryVo.getLastQueryTime());
			params.put("talkType", talkQueryVo.getTalkType());
			int total = this.userTalkService.findTalkCount(params);
			baseResult.getResultObj().put("total", total);
			if (total == 0) {
				baseResult.getResultObj().put("rows", new ArrayList<UserTalk>());
				return baseResult;
			}
			SimplePage page = new SimplePage(pageNo, pageSize, (int) total);
			List<UserTalk> list = this.userTalkService.findTalkByPage(page, params);
			if (null != list && list.size() > 0) {
				for (UserTalk userTalk : list) {
					AppFile tempAppFile = new AppFile();
					Map<String, Object> paramsAppFile = new HashMap<String, Object>();
					paramsAppFile.put("ownerId", userTalk.getId());
					paramsAppFile.put("imageType", PublicConstans.APP_IMAGE_TYPE_2);
					List<AppFile> imageList = appFileService.findByBiz(tempAppFile, paramsAppFile);
					userTalk.setImageList(imageList);
				}
			}
			baseResult.getResultObj().put("rows", list);
		} catch (ServiceException e) {
			logger.error("查询用户说说及评论异常！", e);
			baseResult.setSuccess(PublicConstans.OPERTION_FAIL);
			baseResult.setMessage(e.getMessage());
		} catch (Exception e) {
			logger.error("查询用户说说及评论异常！", e);
			baseResult.setSuccess(PublicConstans.OPERTION_FAIL);
			baseResult.setMessage(PublicConstans.OPERTION_FAIL_MESSAGE);
		}
		return baseResult;
	}

}
