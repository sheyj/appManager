package com.syj.app.web.controller;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.syj.app.controller.BaseController;
import com.syj.app.web.model.TalkResponse;
import com.syj.app.web.service.TalkResponseService;

/**
 *说说评论
 * 
 * @author she.yj
 * @date 2014-5-23 下午2:25:03
 * @version 0.1.0 
 * @copyright syj.com 
 */
@Controller
@RequestMapping("/system/talk_response")
public class TalkResponseController extends BaseController<TalkResponse> {

	@Resource
	private TalkResponseService talkResponseService;

	@Override
	protected CrudInfo init() {
		return new CrudInfo("talk_response/", talkResponseService);
	}

}
