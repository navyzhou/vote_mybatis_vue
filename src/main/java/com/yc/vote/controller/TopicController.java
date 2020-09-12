package com.yc.vote.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.yc.vote.bean.Topics;
import com.yc.vote.biz.ITopicsBiz;
import com.yc.vote.biz.impl.TopicsBizImpl;
import com.yc.vote.util.RequestParamUtil;

@WebServlet("/topic")
public class TopicController extends BasicController{
	private static final long serialVersionUID = 5965931172616878381L;

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String op = request.getParameter("op");
		
		switch (op) {
		case "add": add(request, response); break;
		case "findAll": findAll(request, response); break;
		default: error(request, response); break;
		}
	}

	private void findAll(HttpServletRequest request, HttpServletResponse response) throws IOException {
		ITopicsBiz topicBiz = new TopicsBizImpl();
		this.send(response, 200, topicBiz.findAll());
	}

	private void add(HttpServletRequest request, HttpServletResponse response) throws IOException {
		Topics topic = RequestParamUtil.getParams(Topics.class, request);

		ITopicsBiz topicBiz = new TopicsBizImpl();
		if (topicBiz.add(topic) > 0) {
			this.send(response, 200, null);
			return;
		}
		this.send(response, 500, null);
	}
}
