package com.zhiyesoft.vote.modules.topic.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.zhiyesoft.vote.basic.core.service.impl.BaseServiceImpl;
import com.zhiyesoft.vote.modules.topic.domain.Customer;
import com.zhiyesoft.vote.modules.topic.mapper.CustomerMapper;
import com.zhiyesoft.vote.modules.topic.service.ICustomerService;

import tk.mybatis.mapper.common.Mapper;

@Transactional(rollbackFor = Exception.class)
@Service
public class CustomerServiceImpl extends BaseServiceImpl<Customer> implements ICustomerService {

////////////////////////////////////////////////////////
///// 变量区域
////////////////////////////////////////////////////////

	private static Logger logger = LoggerFactory.getLogger(CustomerServiceImpl.class);

	private static final String BASE_MESSAGE = "模块类CustomerServiceImpl增删改查";

	@Autowired
	private CustomerMapper userMapper;

	@Override
	protected Mapper<Customer> getBaseMapper() {
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
