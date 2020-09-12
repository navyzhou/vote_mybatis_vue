package com.yc.vote.biz.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import com.yc.vote.bean.Topics;
import com.yc.vote.biz.ITopicsBiz;
import com.yc.vote.dao.IBaseDao;
import com.yc.vote.dao.impl.BaseDaoImpl;
import com.yc.vote.util.StringUtil;

/**
 * company 源辰信息
 * @author navy
 * @date 2020年9月8日
 * Email haijunzhou@hnit.edu.cn
 */
public class TopicsBizImpl implements ITopicsBiz{

	@Override
	public int add(Topics topics) {
		if (StringUtil.checkNull(topics.getSdate(), topics.getEdate(), topics.getTname())) {
			return -1;
		}
		IBaseDao baseDao = new BaseDaoImpl();
		
		
		String tid = UUID.randomUUID().toString().replace("-", "");
		topics.setTid(tid);
		
		String[] inames = topics.getUsids().split(",");
		topics.setUsids("");
		List<String> sqlIds = Arrays.asList("Topics.add", "TopicItem.add");
		List<Object> params = new ArrayList<Object>();
		params.add(topics);
		
		List<Object> param = new ArrayList<Object>();
		Map<String, Object> map = null;
		for (String iname : inames) {
			map = new HashMap<String, Object>();
			map.put("tid", tid);
			map.put("iname", iname);
			param.add(map);
		}
		
		params.add(param);
		
		return baseDao.update(sqlIds, params);
	}

	@Override
	public List<Topics> findAll() {
		IBaseDao baseDao = new BaseDaoImpl();
		return baseDao.finds("Topics.findAll");
	}

	@Override
	public Topics findByTid(String tid) {
		IBaseDao baseDao = new BaseDaoImpl();
		return baseDao.find("Topics.findByTid", tid);
	}

	@Override
	public int update(Map<String, Object> map) {
		IBaseDao baseDao = new BaseDaoImpl();
		return baseDao.update("Topics.update", map);
	}

}
