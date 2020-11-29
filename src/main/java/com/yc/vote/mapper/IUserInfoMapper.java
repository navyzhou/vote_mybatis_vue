package com.yc.vote.mapper;

import com.yc.vote.bean.UserInfo;

public interface IUserInfoMapper {
	public int add(UserInfo uf);

	public UserInfo login(UserInfo uf);
}
