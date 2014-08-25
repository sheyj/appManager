package com.syj.app.web.service;

import com.syj.app.exception.ServiceException;
import com.syj.app.service.BaseService;
import com.syj.app.web.model.TalkResponse;

/**
 * 评论接口
 * @author username
 * @date 2014-5-23 下午1:51:40
 * @version 0.1.0 
 * @copyright syj.com 
 */
public interface TalkResponseService extends BaseService {

	TalkResponse getLastTalkResponse(TalkResponse talkResponse)throws ServiceException;

}
