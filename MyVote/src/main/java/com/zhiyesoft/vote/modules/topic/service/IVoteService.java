package com.zhiyesoft.vote.modules.topic.service;

import java.util.Date;
import java.util.List;

import com.zhiyesoft.vote.basic.core.service.IBaseService;
import com.zhiyesoft.vote.modules.topic.domain.Vote;
import com.zhiyesoft.vote.modules.topic.vo.TopicVO;
import com.zhiyesoft.vote.modules.topic.vo.VoteVO;

public interface IVoteService extends IBaseService<Vote> {

	public List<TopicVO> selectTopicResultByUserId(String userId, Integer topicId);

	public List<TopicVO> selectResultByTopicId(Integer topicId);

	public int insertBatch(List<Vote> votes);

	public List<VoteVO> selectVoteByDate(Date startDate, Date endDate, String userId, Integer page, Integer size);

	public int insert(List<Vote> records);
}
