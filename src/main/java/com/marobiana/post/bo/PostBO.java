package com.marobiana.post.bo;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.marobiana.common.FileManagerService;
import com.marobiana.post.dao.PostDAO;
import com.marobiana.post.model.Post;
import com.marobiana.timeline.model.Content;

@Service
public class PostBO {
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private PostDAO postDAO;
	
	@Autowired
	private FileManagerService fileManagerService;
	
	public List<Post> getPostList() {
		return postDAO.selectPostList();
	}
	
	public int createPost(int userId, String userName, String content, MultipartFile file) {
		// content 줄바꿈 적용
		content = content.replace("\n", "<br>");
		
		String imagePath = null;
		if (file != null) {
			try {
				imagePath = fileManagerService.saveFile(userId, file); // 컴퓨터에 파일 업로드 후 URL path를 얻어낸다.
			} catch (IOException e) {
				logger.error("[파일업로드 에러] " + e.getMessage());
			} 
		}
		return postDAO.insertPost(userId, userName, content, imagePath);
	}
}
