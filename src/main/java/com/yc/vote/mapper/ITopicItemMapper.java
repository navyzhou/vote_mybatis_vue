package com.yc.vote.mapper;

import java.util.List;
import java.util.Map;

public interface ITopicItemMapper {
	public int update(String[] inos);
	
	public int add(List<Map<String, Object>> list);
}
