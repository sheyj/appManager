package com.syj.app.web.dao;

import com.syj.app.dao.BaseMapper;
import com.syj.app.web.model.TalkResponse;

public interface TalkResponseMapper extends BaseMapper{

	TalkResponse getLastTalkResponse(TalkResponse talkResponse);

}