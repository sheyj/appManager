package com.syj.app.web.vo;

import java.io.Serializable;

/**
 * 用户查询说说及评论VO
 * 
 * @author she.yj
 * @date 2014-5-24 上午7:29:09
 * @version 0.1.0 
 * @copyright syj.com 
 */
public class TalkQueryVo extends BaseAppReq implements Serializable {

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = -2940426206515308200L;

	/**
	 * 查询用户说说及评论类型： 0查询所以说说及评论  1查询自己的说说及评论   2 查询好友说说及评论  3 查询关注用户说说及评论   
	 */
	private String talkType = "0";

	/**
	 * 查询上次更新时间后的说说及评论
	 */
	private String lastQueryTime;

	public String getTalkType() {
		return talkType;
	}

	public void setTalkType(String talkType) {
		this.talkType = talkType;
	}

	public String getLastQueryTime() {
		return lastQueryTime;
	}

	public void setLastQueryTime(String lastQueryTime) {
		this.lastQueryTime = lastQueryTime;
	}

}
