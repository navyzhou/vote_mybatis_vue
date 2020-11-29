package com.yc.vote.biz.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.yc.vote.bean.Topics;
import com.yc.vote.biz.ITopicsBiz;
import com.yc.vote.mapper.ITopicItemMapper;
import com.yc.vote.mapper.ITopicsMapper;
import com.yc.vote.util.StringUtil;

/**
 * company 源辰信息
 * @author navy
 * @date 2020年9月8日
 * Email haijunzhou@hnit.edu.cn
 */
@Service
public class TopicsBizImpl implements ITopicsBiz{
	@Autowired
	private ITopicItemMapper topicItemMapper;
	
	@Autowired
	private ITopicsMapper topicsMapper;
	
	@Override
	@Transactional
	public int add(Topics topics) {
		if (StringUtil.checkNull(topics.getSdate(), topics.getEdate(), topics.getTname())) {
			return -1;
		}
		int result = - 1;
		String tid = UUID.randomUUID().toString().replace("-", "");
		topics.setTid(tid);
		
		String[] inames = topics.getUsids().split(",");
		topics.setUsids("");
		result = topicsMapper.add(topics);
		
		List<Map<String, Object>> params = new ArrayList<Map<String, Object>>();
		Map<String, Object> map = null;
		for (String iname : inames) {
			map = new HashMap<String, Object>();
			map.put("tid", tid);
			map.put("iname", iname);
			params.add(map);
		}
		result = topicItemMapper.add(params);
		return result;
	}

	@Override
	public List<Topics> findAll() {
		return topicsMapper.findAll();
	}

	@Override
	public Topics findByTid(String tid) {
		return topicsMapper.findByTid(tid);
	}

	@Override
	@Transactional
	public int update(String tid, int usid, String inos) {
		if (StringUtil.checkNull(tid, inos)) {
			return -1;
		}
		int result = -1;
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("usid", usid);
		map.put("tid", tid);
		
		result = topicsMapper.update(map);
		result = topicItemMapper.update(inos.split(","));
		return result;
	}

}
