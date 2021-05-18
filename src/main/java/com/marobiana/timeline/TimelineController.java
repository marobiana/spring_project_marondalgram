package com.marobiana.timeline;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.marobiana.timeline.bo.ContentBO;
import com.marobiana.timeline.model.Content;

@Controller
public class TimelineController {
	@Autowired
	private ContentBO contentBO;
	
	@RequestMapping("/timeline/timeline_view")
	public String timeline(Model model, HttpServletRequest request) {
		// 로그인 된 사용자의 좋아요 상태를 표시하기 위해 userId 파라미터를 추가한다. 
		HttpSession session = request.getSession();
		Integer userId = (Integer) session.getAttribute("userId");
		
		List<Content> contentList = contentBO.getContentList(userId);
		model.addAttribute("contentList", contentList);
		model.addAttribute("viewName", "timeline/timeline");
		return "template/layout";
	}
}
