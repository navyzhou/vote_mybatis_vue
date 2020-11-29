package com.yc.vote.biz.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yc.vote.biz.ITopicItemBiz;
import com.yc.vote.mapper.ITopicItemMapper;

/**
 * company 源辰信息
 * @author navy
 * @date 2020年9月8日
 * Email haijunzhou@hnit.edu.cn
 */
@Service
public class TopicItemBizImpl implements ITopicItemBiz {
	@Autowired
	private ITopicItemMapper topicItemMapper;
	
	@Override
	public int update(String[] inos) {
		return topicItemMapper.update(inos);
	}
}
