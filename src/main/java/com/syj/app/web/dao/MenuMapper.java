package com.syj.app.web.dao;

import java.util.List;
import java.util.Map;

import com.syj.app.dao.BaseMapper;
import com.syj.app.web.model.Menu;

public interface MenuMapper extends BaseMapper {
	
	List<Menu> selectLoginUserMenu(Map<String, Object> params);
	
}