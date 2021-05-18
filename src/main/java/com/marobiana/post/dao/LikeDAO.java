package com.marobiana.post.dao;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface LikeDAO {
	public int selectLikeCountByPostIdOrUserId(
			@Param("postId") int postId, 
			@Param("userId") Integer userId);
	
	public void insertLike(
			@Param("postId") int postId, 
			@Param("userId") int userId);
	
	public void deleteLikeByPostIdUserId(
			@Param("postId") int postId, 
			@Param("userId") int userId);
}
