package com.syj.app.web.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.syj.app.dao.BaseMapper;
import com.syj.app.web.model.UserGroup;

public interface UserGroupMapper extends BaseMapper {

	List<UserGroup> findByUserId(@Param("params") Map<String, Object> params);
}