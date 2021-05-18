<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<div class="header bg-info d-flex justify-content-between">
	<%-- logo를 y축으로 .header의 가운데에 위치 --%>
	<div class="logo d-flex align-items-center">
		<h1 class="text-white ml-3"><a href="/timeline/timeline_view" class="text-white">Marondalgram</a></h1>
	</div>
	<div class="login-info d-flex align-items-end mb-3 mr-5">
		<%-- session 정보가 있을 때만 출력 --%>
		<c:if test="${not empty userName}">
			<span class="text-white">${userName}님 안녕하세요</span>
			<a href="/user/sign_out" class="ml-2 text-white font-weight-bold">로그아웃</a>
		</c:if>
		<c:if test="${empty userName}">
			<a href="/user/sign_in_view" class="ml-2 text-white font-weight-bold">로그인</a>
		</c:if>
	</div>
</div>