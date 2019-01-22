package com.zhiyesoft.vote.modules.topic.service;

import java.util.List;

import com.zhiyesoft.vote.basic.core.service.IBaseService;
import com.zhiyesoft.vote.modules.topic.domain.Vote;

public interface IVoteService extends IBaseService<Vote> {

	/**
	 * 插进去对象
	 * 
	 * @param record
	 * @return
	 */
	public int insert(List<Vote> records);
}
