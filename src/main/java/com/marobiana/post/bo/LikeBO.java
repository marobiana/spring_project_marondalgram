package com.marobiana.post.bo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.marobiana.post.dao.LikeDAO;

@Service
public class LikeBO {
	@Autowired
	private LikeDAO likeDAO;
	
	public void like(int postId, int userId) {
		boolean existLike = existLike(postId, userId);
		if (existLike) {
			likeDAO.deleteLikeByPostIdUserId(postId, userId);
		} else {
			likeDAO.insertLike(postId, userId);
		}
	}
	
	public boolean existLike(int postId, Integer userId) {
		// 비로그인 상태면 세팅된 좋아요는 없다.
		if (userId == null) {
			return false;
		}
		int count = likeDAO.selectLikeCountByPostIdOrUserId(postId, userId);
		return count > 0? true : false;
	}
	
	public int getLikeCountByPostId(int postId) {
		return likeDAO.selectLikeCountByPostIdOrUserId(postId, null);
	}
	
	public void deleteLikeByPostIdUserId(int postId, int userId) {
		likeDAO.deleteLikeByPostIdUserId(postId, userId);
	}

	public void deleteLikeByPostId(int postId) {
		likeDAO.deleteLikeByPostId(postId);
	}
}
