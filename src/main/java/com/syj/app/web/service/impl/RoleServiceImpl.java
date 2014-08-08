package com.syj.app.web.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.syj.app.dao.BaseMapper;
import com.syj.app.service.impl.BaseServiceImpl;
import com.syj.app.web.dao.RoleMapper;

/**
 * TODO: 增加描述
 * 
 * @author username
 * @date 2014-5-20 上午10:59:17
 * @version 0.1.0 
 * @copyright yougou.com 
 */
@Service
public class RoleServiceImpl extends BaseServiceImpl {

	@Resource
	private RoleMapper roleMapper;
	
	@Override
	public BaseMapper init() {
		return roleMapper;
	}

}
