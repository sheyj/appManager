package com.syj.app.web.dao;

import com.syj.app.dao.BaseMapper;
import com.syj.app.web.model.UserFriend;

public interface UserFriendMapper extends BaseMapper {

  int updateStatus(UserFriend userFriend);

}