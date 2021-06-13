package com.marobiana.post;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.marobiana.post.bo.LikeBO;
import com.marobiana.post.bo.PostBO;

@RequestMapping("/post")
@RestController
public class PostRestController {
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private PostBO postBO;
	
	@Autowired
	private LikeBO likeBO;
	
	/**
	 * 글쓰기 및 이미지 업로드
	 * @param content
	 * @param file
	 * @return
	 */
	@RequestMapping("/create")
	public Map<String, Object> create(
			@RequestParam("content") String content,
			@RequestParam(value = "file", required = false) MultipartFile file,
			HttpServletRequest request) {
		
		Map<String, Object> result = new HashMap<>();
		HttpSession session = request.getSession();
		Integer userId = (Integer) session.getAttribute("userId");
		String userName = (String) session.getAttribute("userName");
		if (userId == null || userName == null) {
			result.put("result", "error");
			logger.error("[글쓰기] 로그인 세션이 없습니다.");
			return result;
		}
		
		int row = postBO.createPost(userId, userName, content, file);
		if (row > 0) {
			result.put("result", "success");
		} else {
			result.put("result", "error");
			logger.error("[글쓰기] 글쓰기를 완료하지 못했습니다.");
		}
		
		return result;
	}
	
	/**
	 * 좋아요 / 해제
	 * @param postId
	 * @param request
	 * @return
	 */
	@RequestMapping("/like")
	public Map<String, Object> like(
			@RequestParam("postId") int postId,
			HttpServletRequest request) {
		
		Map<String, Object> result = new HashMap<>();
		HttpSession session = request.getSession();
		Integer userId = (Integer) session.getAttribute("userId");
		if (userId == null) {
			result.put("result", "error");
			logger.error("[좋아요] 로그인 세션이 없습니다.");
			return result;
		}
		
		likeBO.like(postId, userId);
		result.put("result", "success");
		return result;
	}
	
	@RequestMapping("/delete")
	public Map<String, Object> lideleteke(
			@RequestParam("postId") int postId,
			HttpServletRequest request) {
		
		Map<String, Object> result = new HashMap<>();
		HttpSession session = request.getSession();
		Integer userId = (Integer) session.getAttribute("userId");
		if (userId == null) {
			result.put("result", "error");
			logger.error("[글 삭제] 로그인 세션이 없습니다.");
			return result;
		}
		
		postBO.deletePost(postId);
		result.put("result", "success");
		return result;
	}
}
