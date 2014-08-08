package com.syj.app.web.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.syj.app.dao.BaseMapper;
import com.syj.app.service.impl.BaseServiceImpl;
import com.syj.app.web.dao.AppUserMapper;
import com.syj.app.web.service.AppUserService;

/**
 * TODO: 增加描述
 * 
 * @author username
 * @date 2014-5-21 下午4:50:13
 * @version 0.1.0 
 * @copyright yougou.com 
 */
@Service
public class AppUserServiceImpl extends BaseServiceImpl implements AppUserService {

	@Resource
	private AppUserMapper appUserMapper;
	
	@Override
	public BaseMapper init() {
		return appUserMapper;
	}

}
