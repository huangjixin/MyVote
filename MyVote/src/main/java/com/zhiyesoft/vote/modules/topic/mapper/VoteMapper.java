package com.zhiyesoft.vote.modules.topic.mapper;

import java.util.List;

import com.zhiyesoft.vote.modules.topic.domain.Vote;
import tk.mybatis.mapper.common.Mapper;

public interface VoteMapper extends Mapper<Vote> {
	/**
	 * 批量插入数据
	 * @param list
	 * @return
	 */
	public int insertBatch(List<Vote> list);
}