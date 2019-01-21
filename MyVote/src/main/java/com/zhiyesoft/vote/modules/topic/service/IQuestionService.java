package com.zhiyesoft.vote.modules.topic.service;

import java.util.List;

import com.zhiyesoft.vote.basic.core.service.IBaseService;
import com.zhiyesoft.vote.modules.topic.domain.Question;
import com.zhiyesoft.vote.modules.topic.vo.QuestionVO;

public interface IQuestionService extends IBaseService<Question> {
	public List<QuestionVO> selectAllByTopicId(Integer topicId);
}
