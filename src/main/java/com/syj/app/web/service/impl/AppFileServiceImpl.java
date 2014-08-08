package com.syj.app.web.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.syj.app.dao.BaseMapper;
import com.syj.app.service.impl.BaseServiceImpl;
import com.syj.app.web.dao.AppFileMapper;
import com.syj.app.web.service.AppFileService;

/**
 * 图片接口
 * 
 * @author she.yj
 * @date 2014-5-25 上午7:15:17
 * @version 0.1.0 
 * @copyright syj.com 
 */
@Service
public class AppFileServiceImpl extends BaseServiceImpl implements AppFileService {

	@Resource
	private AppFileMapper appFileMapper;

	@Override
	public BaseMapper init() {
		return appFileMapper;
	}

}
