package com.syj.app.web.controller;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.syj.app.controller.BaseController;
import com.syj.app.web.model.AppActivate;
import com.syj.app.web.service.AppActivateService;

/**
 * 用户激活
 * @author she.yj
 * @date 2014-5-23 下午2:24:07
 * @version 0.1.0 
 * @copyright syj.com 
 */
@Controller
@RequestMapping("/system/app_activate")
public class AppActivateController extends BaseController<AppActivate> {

	@Resource
	private AppActivateService appActivateService;

	@Override
	protected CrudInfo init() {
		return new CrudInfo("app_activate/", appActivateService);
	}

}
