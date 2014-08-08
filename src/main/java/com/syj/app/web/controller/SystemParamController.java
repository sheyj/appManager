package com.syj.app.web.controller;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.syj.app.controller.BaseController;
import com.syj.app.web.model.SystemParam;
import com.syj.app.web.service.SystemParamService;

/**
 * 系统参数管理
 * @author username
 * @date 2014-5-21 下午5:07:40
 * @version 0.1.0 
 * @copyright yougou.com 
 */
@Controller
@RequestMapping("/system/system_param")
public class SystemParamController extends BaseController<SystemParam> {

	@Resource
	private SystemParamService systemParamService;

	public CrudInfo init() {
		return new CrudInfo("system_param/", systemParamService);
	}

}
