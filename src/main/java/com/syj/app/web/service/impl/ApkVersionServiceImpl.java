package com.syj.app.web.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.syj.app.dao.BaseMapper;
import com.syj.app.exception.ServiceException;
import com.syj.app.service.impl.BaseServiceImpl;
import com.syj.app.web.dao.ApkVersionMapper;
import com.syj.app.web.model.ApkVersion;
import com.syj.app.web.service.ApkVersionService;

/**
 * TODO: 增加描述
 * 
 * @author username
 * @date 2014-5-21 下午4:49:34
 * @version 0.1.0 
 * @copyright yougou.com 
 */
@Service
public class ApkVersionServiceImpl extends BaseServiceImpl implements ApkVersionService {

	@Resource
	private ApkVersionMapper apkVersionMapper;
	
	@Override
	public BaseMapper init() {
		return apkVersionMapper;
	}

	public ApkVersion getNewVersion() throws ServiceException {
		return apkVersionMapper.getNewVersion();
	}

}
