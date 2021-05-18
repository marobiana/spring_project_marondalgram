package com.marobiana.timeline.model;

import java.util.List;

import com.marobiana.comment.model.Comment;
import com.marobiana.post.model.Post;

public class Content {
	private Post post;
	private List<Comment> commentList;
	private boolean filledLike;
	private int likeCount;
	
	public Post getPost() {
		return post;
	}
	public void setPost(Post post) {
		this.post = post;
	}
	public List<Comment> getCommentList() {
		return commentList;
	}
	public void setCommentList(List<Comment> commentList) {
		this.commentList = commentList;
	}
	public boolean isFilledLike() {
		return filledLike;
	}
	public void setFilledLike(boolean filledLike) {
		this.filledLike = filledLike;
	}
	public int getLikeCount() {
		return likeCount;
	}
	public void setLikeCount(int likeCount) {
		this.likeCount = likeCount;
	}
}
