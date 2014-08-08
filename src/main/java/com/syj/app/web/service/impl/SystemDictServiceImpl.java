package com.syj.app.web.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.syj.app.dao.BaseMapper;
import com.syj.app.service.impl.BaseServiceImpl;
import com.syj.app.web.dao.SystemDictMapper;
import com.syj.app.web.service.SystemDictService;

/**
 * TODO: 增加描述
 * 
 * @author username
 * @date 2014-5-21 下午4:50:29
 * @version 0.1.0 
 * @copyright yougou.com 
 */
@Service
public class SystemDictServiceImpl extends BaseServiceImpl implements SystemDictService {

	@Resource
	private SystemDictMapper systemDictMapper;
	
	@Override
	public BaseMapper init() {
		return systemDictMapper;
	}


}
