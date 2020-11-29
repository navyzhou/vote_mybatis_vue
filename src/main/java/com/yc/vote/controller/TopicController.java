package com.yc.vote.controller;

import java.io.IOException;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.yc.vote.bean.Topics;
import com.yc.vote.bean.UserInfo;
import com.yc.vote.biz.ITopicsBiz;
import com.yc.vote.vo.ResultVO;

@RestController // 相当于 @Controller + @ResponseBody  说明这个类中的所有方法都是以json格式返回数据
@RequestMapping("/topic")
public class TopicController{
	@Autowired
	private ITopicsBiz topicBizImpl;

	@PostMapping("/vote")
	public ResultVO vote(HttpSession session, String tid, String inos) throws IOException {
		Object obj = session.getAttribute("currentLoginUser");
		if (obj == null) {
			return new ResultVO(500);
		}
		int usid = ((UserInfo)obj).getUsid();	
		if (topicBizImpl.update(tid, usid, inos) > 0) {
			return new ResultVO(200);
		}
		return new ResultVO(500);
	}

	@PostMapping("/findByTid")
	public ResultVO findByTid(String tid)  {
		Topics topic = topicBizImpl.findByTid(tid);
		if (topic != null) {
			return new ResultVO(200, topic);
		}
		return new ResultVO(500);
	}

	@GetMapping("/findAll")
	public ResultVO findAll()  {
		return new ResultVO(200, topicBizImpl.findAll());
	}

	public ResultVO add(Topics topic) throws IOException {
		if (topicBizImpl.add(topic) > 0) {
			return new ResultVO(200);
		}
		return new ResultVO(500);
	}
}
