package com.syj.app.web.controller;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.syj.app.controller.BaseController;
import com.syj.app.web.model.AppUser;
import com.syj.app.web.service.AppUserService;

/**
 * app 用户管理
 * @author username
 * @date 2014-5-21 下午5:07:40
 * @version 0.1.0 
 * @copyright yougou.com 
 */
@Controller
@RequestMapping("/system/app_user")
public class AppUserController extends BaseController<AppUser> {

	@Resource
	private AppUserService appUserService;

	public CrudInfo init() {
		return new CrudInfo("app_user/", appUserService);
	}

}
