package com.syj.app.web.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.syj.app.dao.BaseMapper;
import com.syj.app.exception.ServiceException;
import com.syj.app.service.impl.BaseServiceImpl;
import com.syj.app.web.dao.TalkResponseMapper;
import com.syj.app.web.model.TalkResponse;
import com.syj.app.web.service.TalkResponseService;

/**
 * 说说评论接口
 * 
 * @author she.yj
 * @date 2014-5-23 下午2:13:04
 * @version 0.1.0 
 * @copyright syj.com 
 */
@Service
public class TalkResponseServiceImpl extends BaseServiceImpl implements TalkResponseService {

	@Resource
	private TalkResponseMapper talkResponseMapper;

	@Override
	public BaseMapper init() {
		return talkResponseMapper;
	}

	public TalkResponse getLastTalkResponse(TalkResponse talkResponse) throws ServiceException {
		return talkResponseMapper.getLastTalkResponse(talkResponse);
	}

}
