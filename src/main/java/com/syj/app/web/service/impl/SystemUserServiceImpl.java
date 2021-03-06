package com.syj.app.web.service.impl;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import com.syj.app.dao.BaseMapper;
import com.syj.app.exception.ServiceException;
import com.syj.app.service.impl.BaseServiceImpl;
import com.syj.app.web.dao.MenuMapper;
import com.syj.app.web.dao.SystemUserMapper;
import com.syj.app.web.model.Menu;
import com.syj.app.web.service.SystemUserService;
import com.syj.app.web.vo.MenuVo;

@Service
public class SystemUserServiceImpl extends BaseServiceImpl implements SystemUserService {

	private static Logger logger = Logger.getLogger(SystemUserServiceImpl.class);
	
	@Resource
	private SystemUserMapper systemUserMapper;
	
	@Resource
	private MenuMapper menuMapper;
	
	@Override
	public BaseMapper init() {
		return systemUserMapper;
	}
	
	/**
	 * 查询登录用户菜单
	 * @param loginName
	 * @return
	 * @throws ServiceException
	 */
	public List<MenuVo>  getLoginUserMenu(String loginName) throws ServiceException {
		List<MenuVo> resultList = new LinkedList<MenuVo>();
		try{
			Map<String ,Object>  params = new HashMap<String,Object>();
			params.put("loginName", loginName);
			List<Menu>  queryList = menuMapper.selectLoginUserMenu(params);
			if(null != queryList && queryList.size()>0){
				
				Map<String,List<MenuVo>> tempMap = new HashMap<String,List<MenuVo>>();
				for(Menu menu : queryList){
					MenuVo menuVo = new MenuVo();
					menuVo.setId(menu.getMenuId());
					menuVo.setText(menu.getMenuName());
					menuVo.setParentId(menu.getParentId());
					if (null != menu.getMenuType()) {
						if(0 == menu.getMenuType().intValue()){
							menuVo.setIsLeaf("true");
							resultList.add(menuVo);
						}else{
							menuVo.setIsLeaf("false");
							menuVo.getAttributes().put("url", menu.getMenuUrl());
						}
					}
					List<MenuVo> tempList = null;
					if(tempMap.containsKey(menu.getParentId())){
						tempList = tempMap.get(menu.getParentId());
						
					}else{
						tempList = new LinkedList<MenuVo>();
					}
					tempList.add(menuVo);
					tempMap.put(menu.getParentId(), tempList);
				}
				
				for(MenuVo menuVo : resultList){
					List<MenuVo> listVo=tempMap.get(menuVo.getId());
					if(null!=listVo && listVo.size()>0){
						menuVo.setMenuVoList(listVo);
					}
				}
				
			}
			return resultList;
		}catch(Exception e){
			logger.error("查询菜单异常！",e);
			throw new ServiceException(e);
		}
	}
	
}
