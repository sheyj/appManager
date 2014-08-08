package com.syj.app.test;

import static org.junit.Assert.assertNotNull;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.junit.Before;
import org.junit.Test;

import com.syj.app.common.SimplePage;
import com.syj.app.exception.ServiceException;
import com.syj.app.web.model.UserTalk;
import com.syj.app.web.service.UserTalkService;

public class UserTalkTest extends BaseTest {
	@Resource
	private UserTalkService userTalkService;

	@Before
	public void init() throws Exception {

	}

	@Test
	public void testFindTalkCount() throws ServiceException {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("userId", 1);
		params.put("lastQueryTime", "2014-04-10 00:00:00");
		params.put("talkType", 3);
		int  count = userTalkService.findTalkCount(params);
		
		System.out.println(count);
	}
	
	@Test
	public void findTalkByPage() throws ServiceException {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("userId", 1);
		params.put("lastQueryTime", "2014-04-10 00:00:00");
		params.put("talkType", 3);
		SimplePage page = new SimplePage(1, 10, 11);
		List<UserTalk> list = userTalkService.findTalkByPage(page, params);
		assertNotNull(list);
	}
}
