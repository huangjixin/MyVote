package com.zhiyesoft.vote.modules.topic.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.zhiyesoft.vote.basic.core.service.impl.BaseServiceImpl;
import com.zhiyesoft.vote.modules.topic.domain.Option;
import com.zhiyesoft.vote.modules.topic.mapper.OptionMapper;
import com.zhiyesoft.vote.modules.topic.service.IOptionService;

import tk.mybatis.mapper.common.Mapper;

@Transactional(rollbackFor = Exception.class)
@Service
public class OptionServiceImpl extends BaseServiceImpl<Option> implements IOptionService {

////////////////////////////////////////////////////////
///// 变量区域
////////////////////////////////////////////////////////

	private static Logger logger = LoggerFactory.getLogger(OptionServiceImpl.class);

	private static final String BASE_MESSAGE = "模块类OptionServiceImpl增删改查";

	@Autowired
	private OptionMapper optionMapper;

	@Override
	protected Mapper<Option> getBaseMapper() {
		return this.optionMapper;
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
