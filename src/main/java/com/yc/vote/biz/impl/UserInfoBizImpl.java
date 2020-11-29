package com.yc.vote.biz.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yc.vote.bean.UserInfo;
import com.yc.vote.biz.IUserInfoBiz;
import com.yc.vote.mapper.IUserInfoMapper;
import com.yc.vote.util.StringUtil;

/**
 * company 源辰信息
 * @author navy
 * @date 2020年9月3日
 * Email haijunzhou@hnit.edu.cn
 */
@Service
public class UserInfoBizImpl implements IUserInfoBiz{
	@Autowired
	private IUserInfoMapper userInfoMapper;
	
	@Override
	public int add(UserInfo uf) {
		if (StringUtil.checkNull(uf.getUname(), uf.getPwd())) {
			return -1;
		}
		return userInfoMapper.add(uf);
	}

	@Override
	public UserInfo login(UserInfo uf) {
		if (StringUtil.checkNull(uf.getUname(), uf.getPwd())) {
			return null;
		}
		return userInfoMapper.login(uf);
	}
}
