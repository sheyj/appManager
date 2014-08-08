package com.syj.app.web.service;

import java.util.List;

import com.syj.app.exception.ServiceException;
import com.syj.app.service.BaseService;
import com.syj.app.web.vo.MenuVo;

public interface SystemUserService extends BaseService {

	/**
	 * 获取登录用户菜单
	 * @param loginName
	 * @return
	 * @throws ServiceException
	 */
	List<MenuVo>  getLoginUserMenu(String loginName) throws ServiceException;
	
}
