package com.marobiana.timeline;

import java.util.List;

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
	public String timeline(Model model) {
		List<Content> contentList = contentBO.getContentList();
		model.addAttribute("contentList", contentList);
		model.addAttribute("viewName", "timeline/timeline");
		return "template/layout";
	}
}
