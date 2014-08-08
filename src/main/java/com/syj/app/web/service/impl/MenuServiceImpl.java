package com.syj.app.web.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.syj.app.dao.BaseMapper;
import com.syj.app.service.impl.BaseServiceImpl;
import com.syj.app.web.dao.MenuMapper;

/**
 * 菜单维护
 * 
 * @author username
 * @date 2014-5-20 上午10:58:54
 * @version 0.1.0 
 * @copyright yougou.com 
 */
@Service
public class MenuServiceImpl extends BaseServiceImpl {

	@Resource
	private MenuMapper menuMapper;
	
	@Override
	public BaseMapper init() {
		return menuMapper;
	}

}
