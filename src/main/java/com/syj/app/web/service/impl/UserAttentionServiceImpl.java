package com.syj.app.web.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.syj.app.dao.BaseMapper;
import com.syj.app.service.impl.BaseServiceImpl;
import com.syj.app.web.dao.UserAttentionMapper;
import com.syj.app.web.service.UserAttentionService;

/**
 * 用户关注接口
 * 
 * @author she.yj
 * @date 2014-5-24 上午9:44:02
 * @version 0.1.0 
 * @copyright syj.com 
 */
@Service
public class UserAttentionServiceImpl extends BaseServiceImpl implements UserAttentionService {

	@Resource
	private UserAttentionMapper userAttentionMapper;

	@Override
	public BaseMapper init() {
		return userAttentionMapper;
	}

}
