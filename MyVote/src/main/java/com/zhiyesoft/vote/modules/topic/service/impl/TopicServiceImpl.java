package com.zhiyesoft.vote.modules.topic.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.zhiyesoft.vote.basic.core.service.impl.BaseServiceImpl;
import com.zhiyesoft.vote.modules.topic.domain.Topic;
import com.zhiyesoft.vote.modules.topic.mapper.TopicMapper;
import com.zhiyesoft.vote.modules.topic.service.ITopicService;

import tk.mybatis.mapper.common.Mapper;

@Transactional(rollbackFor = Exception.class)
@Service
public class TopicServiceImpl extends BaseServiceImpl<Topic> implements ITopicService {

////////////////////////////////////////////////////////
///// 变量区域
////////////////////////////////////////////////////////

	private static Logger logger = LoggerFactory.getLogger(TopicServiceImpl.class);

	private static final String BASE_MESSAGE = "模块类TopicServiceImpl增删改查";

	@Autowired
	private TopicMapper userMapper;

	@Override
	protected Mapper<Topic> getBaseMapper() {
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
