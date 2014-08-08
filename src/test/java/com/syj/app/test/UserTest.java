package com.syj.app.test;

import static org.junit.Assert.assertNotNull;

import java.util.List;

import javax.annotation.Resource;

import org.junit.Before;
import org.junit.Test;

import com.syj.app.exception.ServiceException;
import com.syj.app.web.service.SystemUserService;
import com.syj.app.web.vo.MenuVo;

public class UserTest extends BaseTest {
	@Resource
	private SystemUserService userService;

	@Before
	public void init() throws Exception {

	}

	@Test
	public void testGetSerialNoByReqNo() throws ServiceException {

		List<MenuVo> list = userService.getLoginUserMenu("admin");
		assertNotNull(list);
	}
}
