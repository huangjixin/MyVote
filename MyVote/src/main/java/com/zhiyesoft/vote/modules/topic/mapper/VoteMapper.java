package com.zhiyesoft.vote.modules.topic.mapper;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.zhiyesoft.vote.modules.topic.domain.Vote;
import com.zhiyesoft.vote.modules.topic.vo.TopicVO;
import com.zhiyesoft.vote.modules.topic.vo.VoteVO;

import tk.mybatis.mapper.common.Mapper;

public interface VoteMapper extends Mapper<Vote> {
	public List<Vote> checkVoted(@Param(value = "userId") String userId, @Param(value = "topicId") Integer topicId);

	public List<TopicVO> selectResultByTopicId(@Param(value = "topicId") Integer topicId);

	public List<TopicVO> selectTopicResultByUserId(@Param(value = "topicId") Integer topicId,
			@Param(value = "userId") String userId);

	public int insertBatch(List<Vote> list);

	public List<VoteVO> selectVoteByDate(@Param(value = "startDate") Date startDate, @Param(value = "endDate") Date endDate,
			@Param(value = "userId") String userId, @Param(value = "page") Integer page,
			@Param(value = "size") Integer size);
}