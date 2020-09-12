package com.yc.vote.biz.impl;

import com.yc.vote.biz.ITopicItemBiz;
import com.yc.vote.dao.IBaseDao;
import com.yc.vote.dao.impl.BaseDaoImpl;

/**
 * company 源辰信息
 * @author navy
 * @date 2020年9月8日
 * Email haijunzhou@hnit.edu.cn
 */
public class TopicItemBizImpl implements ITopicItemBiz {
	@Override
	public int update(String[] inos) {
		IBaseDao baseDao = new BaseDaoImpl();
		return baseDao.update("Topics.update", inos);
	}
}
