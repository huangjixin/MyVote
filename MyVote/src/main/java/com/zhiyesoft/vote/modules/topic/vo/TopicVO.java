package com.zhiyesoft.vote.modules.topic.vo;

import java.io.Serializable;
import java.util.List;

import com.zhiyesoft.vote.modules.topic.domain.Topic;

import cn.afterturn.easypoi.excel.annotation.Excel;
import lombok.Data;

public class TopicVO implements Serializable {
	
	private static final long serialVersionUID = 1L;

	@Excel(name = "序号", orderNum = "0")
	private String optionId;
	@Excel(name = "选项", orderNum = "2")
	private String optionName;
	@Excel(name = "票数", orderNum = "3")
	private Integer voteCount;
	private String questionId;
	@Excel(name = "问题", orderNum = "1")
	private String questionName;
	private Integer topicId;
	@Excel(name = "话题", orderNum = "4")
	private String topicName;
	public String getOptionId() {
		return optionId;
	}
	public void setOptionId(String optionId) {
		this.optionId = optionId;
	}
	public String getOptionName() {
		return optionName;
	}
	public void setOptionName(String optionName) {
		this.optionName = optionName;
	}
	public Integer getVoteCount() {
		return voteCount;
	}
	public void setVoteCount(Integer voteCount) {
		this.voteCount = voteCount;
	}
	public String getQuestionId() {
		return questionId;
	}
	public void setQuestionId(String questionId) {
		this.questionId = questionId;
	}
	public String getQuestionName() {
		return questionName;
	}
	public void setQuestionName(String questionName) {
		this.questionName = questionName;
	}
	public Integer getTopicId() {
		return topicId;
	}
	public void setTopicId(Integer topicId) {
		this.topicId = topicId;
	}
	public String getTopicName() {
		return topicName;
	}
	public void setTopicName(String topicName) {
		this.topicName = topicName;
	}
	
}
