package com.syj.app.web.controller;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.syj.app.controller.BaseController;
import com.syj.app.web.model.ApkVersion;
import com.syj.app.web.service.ApkVersionService;

/**
 * app 版本管理
 * @author username
 * @date 2014-5-21 下午5:07:40
 * @version 0.1.0 
 * @copyright yougou.com 
 */
@Controller
@RequestMapping("/system/app_version")
public class ApkVersionController extends BaseController<ApkVersion> {

	@Resource
	private ApkVersionService apkVersionService;

	public CrudInfo init() {
		return new CrudInfo("app_version/", apkVersionService);
	}

}
