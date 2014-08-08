package com.syj.app.web.controller;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.syj.app.controller.BaseController;
import com.syj.app.web.model.UserTalk;
import com.syj.app.web.service.UserTalkService;

/**
 * 用户说说
 * @author she.yj
 * @date 2014-5-23 下午2:24:07
 * @version 0.1.0 
 * @copyright syj.com 
 */
@Controller
@RequestMapping("/system/user_talk")
public class UserTalkController extends BaseController<UserTalk> {

	@Resource
	private UserTalkService userTalkService;

	@Override
	protected CrudInfo init() {
		return new CrudInfo("user_talk/", userTalkService);
	}

}
