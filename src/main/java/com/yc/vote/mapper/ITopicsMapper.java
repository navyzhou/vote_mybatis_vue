package com.yc.vote.mapper;

import java.util.List;
import java.util.Map;

import com.yc.vote.bean.Topics;

public interface ITopicsMapper {
	public int add(Topics topics);
	
	public List<Topics> findAll();
	
	public Topics findByTid(String tid);
	
	public int update(Map<String, Object> map);
}
