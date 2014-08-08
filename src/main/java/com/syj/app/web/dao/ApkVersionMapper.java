package com.syj.app.web.dao;

import com.syj.app.dao.BaseMapper;
import com.syj.app.web.model.ApkVersion;

public interface ApkVersionMapper extends BaseMapper {
	
	//获取最新版本
	ApkVersion  getNewVersion();
}