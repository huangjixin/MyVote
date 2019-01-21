package com.zhiyesoft.vote.modules.topic.vo;

import java.util.List;

import com.zhiyesoft.vote.modules.topic.domain.Question;

import lombok.Data;

@Data
public class QuestionVO extends Question{

	private static final long serialVersionUID = 1L;

	private List<OptionVO> options;

	public List<OptionVO> getOptions() {
		return options;
	}

	public void setOptions(List<OptionVO> options) {
		this.options = options;
	}
}
