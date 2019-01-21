package com.zhiyesoft.vote.modules.topic.service.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.github.pagehelper.PageInfo;
import com.zhiyesoft.vote.basic.core.service.impl.BaseServiceImpl;
import com.zhiyesoft.vote.modules.topic.domain.Question;
import com.zhiyesoft.vote.modules.topic.mapper.QuestionMapper;
import com.zhiyesoft.vote.modules.topic.service.IQuestionService;
import com.zhiyesoft.vote.modules.topic.vo.QuestionVO;

import tk.mybatis.mapper.common.Mapper;

@Transactional(rollbackFor = Exception.class)
@Service
public class QuestionServiceImpl extends BaseServiceImpl<Question> implements IQuestionService {

////////////////////////////////////////////////////////
///// 变量区域
////////////////////////////////////////////////////////

	private static Logger logger = LoggerFactory.getLogger(QuestionServiceImpl.class);

	private static final String BASE_MESSAGE = "模块类QuestionServiceImpl增删改查";

	@Autowired
	private QuestionMapper questionMapper;

	@Override
	protected Mapper<Question> getBaseMapper() {
		return this.questionMapper;
	}

	@Override
	protected String getBaseMessage() {
		return BASE_MESSAGE;
	}

	@Override
	protected Logger getLogger() {
		return logger;
	}

	public List<QuestionVO> selectAllByTopicId(Integer topicId){
		List<QuestionVO> questions = this.questionMapper.selectAllByTopicId(topicId);
		return questions;
	} 

}
