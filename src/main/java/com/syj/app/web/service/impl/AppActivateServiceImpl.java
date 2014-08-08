package com.syj.app.web.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.syj.app.dao.BaseMapper;
import com.syj.app.service.impl.BaseServiceImpl;
import com.syj.app.web.dao.AppActivateMapper;
import com.syj.app.web.service.AppActivateService;

/**
 * 用户激活
 * 
 * @author she.yj
 * @date 2014-5-25 上午7:15:17
 * @version 0.1.0 
 * @copyright syj.com 
 */
@Service
public class AppActivateServiceImpl extends BaseServiceImpl implements AppActivateService {

	@Resource
	private AppActivateMapper appActivateMapper;

	@Override
	public BaseMapper init() {
		return appActivateMapper;
	}

}
