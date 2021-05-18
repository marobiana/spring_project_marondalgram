package com.marobiana.timeline;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.marobiana.post.bo.PostBO;
import com.marobiana.post.model.Post;

@Controller
public class TimelineController {
	@Autowired
	private PostBO postBO;
	
	@RequestMapping("/timeline/timeline_view")
	public String timeline(Model model) {
		List<Post> postList = postBO.gettPostList();
		model.addAttribute("postList", postList);
		model.addAttribute("viewName", "timeline/timeline");
		return "template/layout";
	}
}
