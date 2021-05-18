package com.marobiana.comment.bo;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.marobiana.comment.dao.CommentDAO;
import com.marobiana.comment.model.Comment;

@Service
public class CommentBO {
	@Autowired
	private CommentDAO commentDAO;
	
	public List<Comment> getCommentList(int postId) {
		return commentDAO.selectCommentList(postId);
	}
	
	public int createComment(int userId, String userName, int postId, String content) {
		return commentDAO.insertComment(userId, userName, postId, content);
	}
	
	public void deleteComment(int id) {
		commentDAO.deleteComment(id);
	}
}
