package com.yc.vote.biz;

import java.util.List;
import java.util.Map;

import com.yc.vote.bean.Topics;

public interface ITopicsBiz {
	public int add(Topics topics);
	
	public List<Topics> findAll();
	
	public Topics findByTid(String tid);
	
	public int update(Map<String, Object> map);
}
