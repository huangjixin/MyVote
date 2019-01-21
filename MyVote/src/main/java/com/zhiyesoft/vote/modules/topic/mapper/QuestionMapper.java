package com.zhiyesoft.vote.modules.topic.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.zhiyesoft.vote.modules.topic.domain.Question;
import com.zhiyesoft.vote.modules.topic.vo.QuestionVO;

import tk.mybatis.mapper.common.Mapper;

public interface QuestionMapper extends Mapper<Question> {
	
	List<QuestionVO> selectAllByTopicId(@Param(value="topicId") Integer topicId);
}