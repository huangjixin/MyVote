package com.zhiyesoft.vote.modules.topic.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.zhiyesoft.vote.basic.core.service.impl.BaseServiceImpl;
import com.zhiyesoft.vote.modules.topic.domain.Vote;
import com.zhiyesoft.vote.modules.topic.mapper.VoteMapper;
import com.zhiyesoft.vote.modules.topic.service.IVoteService;

import tk.mybatis.mapper.common.Mapper;

@Transactional(rollbackFor = Exception.class)
@Service
public class VoteServiceImpl extends BaseServiceImpl<Vote> implements IVoteService {

////////////////////////////////////////////////////////
///// 变量区域
////////////////////////////////////////////////////////

	private static Logger logger = LoggerFactory.getLogger(VoteServiceImpl.class);

	private static final String BASE_MESSAGE = "系统用户模块类UserServiceImpl增删改查";

	@Autowired
	private VoteMapper voteMapper;

	@Override
	protected Mapper<Vote> getBaseMapper() {
		return this.voteMapper;
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
