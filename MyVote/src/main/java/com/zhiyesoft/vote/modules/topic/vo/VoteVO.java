package com.zhiyesoft.vote.modules.topic.vo;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import com.alibaba.fastjson.annotation.JSONField;
import com.fasterxml.jackson.annotation.JsonFormat;

import cn.afterturn.easypoi.excel.annotation.Excel;

public class VoteVO {

	@Excel(name="序号",orderNum="0")
	private String voteId;
	@Excel(name="投票日期",orderNum="1")
	@JSONField(format = "yyyy-MM-dd HH:mm:ss") //FastJson包使用注解 
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8") //Jackson包使用注解 
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss") //格式化前台日期参数注解 
	private Date createDate;
	private String userId;
	@Excel(name="投票人",orderNum="2")
	private String userName;

	public String getVoteId() {
		return voteId;
	}

	public void setVoteId(String voteId) {
		this.voteId = voteId;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}
}
