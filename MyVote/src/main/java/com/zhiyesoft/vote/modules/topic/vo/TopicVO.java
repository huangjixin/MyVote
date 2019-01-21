package com.zhiyesoft.vote.modules.topic.vo;

import java.util.List;

import com.zhiyesoft.vote.modules.topic.domain.Topic;

import lombok.Data;

@Data
public class TopicVO extends Topic{
	private static final long serialVersionUID = 1L;
	
	private List<QuestionVO> questions;

	public List<QuestionVO> getQuestions() {
		return questions;
	}

	public void setQuestions(List<QuestionVO> questions) {
		this.questions = questions;
	}
	
}
