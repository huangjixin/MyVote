package com.zhiyesoft.vote.modules.topic.service.impl;

import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.zhiyesoft.vote.basic.core.service.impl.BaseServiceImpl;
import com.zhiyesoft.vote.modules.topic.domain.Vote;
import com.zhiyesoft.vote.modules.topic.mapper.VoteMapper;
import com.zhiyesoft.vote.modules.topic.service.IVoteService;
import com.zhiyesoft.vote.modules.topic.vo.TopicVO;
import com.zhiyesoft.vote.modules.topic.vo.VoteVO;

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
	
	@Override
	public int insert(List<Vote> records) {
		int result = this.voteMapper.insertBatch(records);
		return result;
	}
	
	public int insertBatch(List<Vote> votes) {
		int result = this.voteMapper.insertBatch(votes);
		return result;
	}
	
	public List<TopicVO> selectResultByTopicId(Integer topicId){
		if(logger.isDebugEnabled()) {
			logger.info("查询topic投票结果开始,传入的topicId是：" + topicId);
		}
		List<TopicVO> topics = this.voteMapper.selectResultByTopicId(topicId);
		if(logger.isDebugEnabled()) {
			logger.info("查询topic投票结果结束，投票结果条目数量是：" + topics.size());
		}
		return topics;
	}
	
	public List<TopicVO> selectTopicResultByUserId(String userId,Integer topicId){
		if(logger.isDebugEnabled()) {
			logger.info("查询用户对topic的投票结果开始,传入的topicId是: {}, userId是: {}", topicId, userId);
		}
		List<TopicVO> topics = this.voteMapper.selectResultByTopicId(topicId);
		if(logger.isDebugEnabled()) {
			logger.info("查询用户对topic的投票结果结束，投票结果条目数量是：" + topics.size());
		}
		return topics;
	}
	
	public List<VoteVO> selectVoteByDate(Date startDate, Date endDate, String userId, Integer page, Integer size){
		if(logger.isDebugEnabled()) {
			logger.info("根据日期范围和userId查询投票参与人和时间开始,传入的开始时间是: {}, 结束时间是: {}, userId是: {}", startDate, endDate, userId);
		}
		List<VoteVO> votes = this.voteMapper.selectVoteByDate(startDate, endDate, userId, page, size);
		if(logger.isDebugEnabled()) {
			logger.info("根据日期范围和userId查询投票参与人和时间结束,查询结果条目数量是: {}", votes.size());
		}
		return votes;
	}
	
}
