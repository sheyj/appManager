package com.syj.app.web.controller;

import javax.annotation.Resource;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.syj.app.controller.BaseController;
import com.syj.app.exception.ServiceException;
import com.syj.app.util.CommonUtil;
import com.syj.app.util.UUIDUtil;
import com.syj.app.web.model.SystemUser;
import com.syj.app.web.service.SystemUserService;

/**
 * 系统用户维护
 * 
 * @author username
 * @date 2014-5-20 上午11:02:14
 * @version 0.1.0 
 * @copyright yougou.com 
 */
@Controller
@RequestMapping("/system/system_user")
public class SystemUserController extends BaseController<SystemUser> {

	@Resource
	private SystemUserService systemUserService;

	public CrudInfo init() {
		return new CrudInfo("system_user/", systemUserService);
	}

	@RequestMapping(value = "/add")
	public ResponseEntity<SystemUser> add(SystemUser systemUser) throws ServiceException {
		if (!systemUser.getLoginPassword().equals(systemUser.getReLoginPassword())) {
			throw new ServiceException("输入密码不一致！");
		}
		//md5加密
		systemUser.setLoginPassword(CommonUtil.md5(systemUser.getLoginPassword()));
		systemUser.setUserid(UUIDUtil.getUuid());
		systemUserService.add(systemUser);
		return new ResponseEntity<SystemUser>(systemUser, HttpStatus.OK);
	}

}
