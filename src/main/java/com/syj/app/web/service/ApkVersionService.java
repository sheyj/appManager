package com.syj.app.web.service;

import com.syj.app.exception.ServiceException;
import com.syj.app.service.BaseService;
import com.syj.app.web.model.ApkVersion;

/**
 * TODO: 增加描述
 * 
 * @author username
 * @date 2014-5-21 下午4:46:48
 * @version 0.1.0 
 * @copyright yougou.com 
 */
public interface ApkVersionService extends BaseService {
    //获取最新版本号
	ApkVersion  getNewVersion() throws ServiceException;
}
