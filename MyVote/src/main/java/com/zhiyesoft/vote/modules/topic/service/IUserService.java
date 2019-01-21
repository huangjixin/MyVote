package com.zhiyesoft.vote.modules.topic.service;

import com.zhiyesoft.vote.basic.core.service.IBaseService;
import com.zhiyesoft.vote.modules.topic.domain.User;

public interface IUserService extends IBaseService<User> {
	/**
	 * 根据电话（包括公司电话和私人电话）查询用户。
	 * @param mobil
	 * @return
	 */
	User login(String mobil,String password);
}
