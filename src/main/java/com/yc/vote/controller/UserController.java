package com.yc.vote.controller;

import java.io.IOException;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yc.vote.bean.UserInfo;
import com.yc.vote.biz.IUserInfoBiz;
import com.yc.vote.vo.ResultVO;

@Controller
@RequestMapping("/user")
public class UserController {
	@Autowired
	private IUserInfoBiz userInfoBizImpl;

	@GetMapping("/check") // 说明这个方法只能通过GET方式访问
	@ResponseBody // 说明以json格式返回
	public ResultVO check(HttpSession session) {
		Object obj = session.getAttribute("currentLoginUser");
		if (obj == null) {
			return new ResultVO(500);
		}
		return new ResultVO(200, obj);
	}

	@PostMapping("/login")
	@ResponseBody
	public ResultVO login(UserInfo uf, HttpSession session) {
		UserInfo userInfo = userInfoBizImpl.login(uf);
		if (userInfo == null) {
			return new ResultVO(500);
		}
		
		session.setAttribute("currentLoginUser", userInfo);
		return new ResultVO(200);
	}

	@PostMapping("/reg")
	@ResponseBody
	public ResultVO reg(UserInfo uf) throws IOException {
		if (userInfoBizImpl.add(uf) > 0) {
			return new ResultVO(200);
		}
		return new ResultVO(500);
	}
}
