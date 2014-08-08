package com.syj.app.web.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.syj.app.common.SimplePage;
import com.syj.app.dao.BaseMapper;
import com.syj.app.web.model.UserTalk;

public interface UserTalkMapper extends BaseMapper {

	int findTalkCount(@Param("params") Map<String, Object> params);

	List<UserTalk> findTalkByPage(@Param("page") SimplePage page, @Param("params") Map<String, Object> params);

	
	UserTalk getLastUserTalk(UserTalk userTalk);

}