package com.marobiana.post.bo;

import java.io.IOException;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.marobiana.comment.bo.CommentBO;
import com.marobiana.common.FileManagerService;
import com.marobiana.post.dao.PostDAO;
import com.marobiana.post.model.Post;

@Service
public class PostBO {
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private PostDAO postDAO;
	
	@Autowired
	private LikeBO likeBO;

	@Autowired
	private CommentBO commentBO;
	
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
	
	public void deletePost(int postId) {
		
		Post post = postDAO.selectPost(postId);
		if (post == null) {
			logger.warn("[글 삭제] 이미 삭제되었습니다.");
			return;
		}
		
		// post 삭제
		postDAO.deletePost(postId);
		
		// image 삭제
		String imagePath = post.getImagePath();
		if (imagePath != null) {
			try {
				fileManagerService.deleteFile(imagePath);
			} catch (IOException e) {
				logger.warn("[글 삭제] 이미지 삭제 중 실패: " + imagePath);
			}
		}
		
		// like 삭제 - 글에 대한 모든 좋아요를 삭제 
		likeBO.deleteLikeByPostId(postId);
		
		// 댓글 목록 삭제 - 글에 대한 모든 댓글을 삭제
		commentBO.deleteCommentByPostId(postId);
	}
}
