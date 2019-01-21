package com.zhiyesoft.vote.modules.topic.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.zhiyesoft.vote.basic.core.service.impl.BaseServiceImpl;
import com.zhiyesoft.vote.modules.topic.domain.User;
import com.zhiyesoft.vote.modules.topic.mapper.UserMapper;
import com.zhiyesoft.vote.modules.topic.service.IUserService;

import tk.mybatis.mapper.common.Mapper;

@Transactional(rollbackFor = Exception.class)
@Service
public class UserServiceImpl extends BaseServiceImpl<User> implements IUserService {

////////////////////////////////////////////////////////
///// 变量区域
////////////////////////////////////////////////////////

	private static Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

	private static final String BASE_MESSAGE = "模块类UserServiceImpl增删改查";

	@Autowired
	private UserMapper userMapper;

	@Override
	protected Mapper<User> getBaseMapper() {
		return this.userMapper;
	}

	@Override
	protected String getBaseMessage() {
		return BASE_MESSAGE;
	}

	@Override
	protected Logger getLogger() {
		return logger;
	}

}
