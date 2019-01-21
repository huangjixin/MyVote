package com.zhiyesoft.vote.modules.topic.service.impl;

import java.util.List;

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
import tk.mybatis.mapper.entity.Example;

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

	@Override
	public User login(String mobil,String password) {
		logger.info("用户登录开始,传入的电话号码是：" + mobil);
		Example example = new Example(User.class);
		example.createCriteria().andEqualTo("loginName", mobil).andEqualTo("password", password);
//		UserCriteria userCriteria = new UserCriteria();
//		Criteria criteria =  userCriteria.createCriteria();
//		criteria.andCompanyMobilEqualTo(mobil);
//		userCriteria.or(userCriteria.createCriteria().andPersonalMobilEqualTo(mobil));
		List<User> list = this.userMapper.selectByExample(example);
		String res = (list != null && !list.isEmpty()) ? list.get(0).toString() : "查询不到用户";
		logger.info("用户登录结束，结果是：" + res);
		return (list != null && !list.isEmpty()) ? list.get(0) : null;
	}

}
