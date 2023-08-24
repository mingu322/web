<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<div class = "row my-5">
	<div class = "col">
		<h1 class = "text-center">상품목록</h1>
		<table class="table table-striped">
			<c:forEach items="${array}" var="vo">
				<tr>
					<td>${vo.code}</td>
					<td><a href="/pro/read?code=${vo.code}">${vo.name}</a></td>
					<td>${vo.name}</td>
					<td><fmt:formatNumber value="${vo.price}" pattern="#,###원"/> </td>
					<td>${vo.rdate}</td>
				</tr>
			</c:forEach>
		</table>
		<div class = "text-center">
			<button class = "btn btn-primary" id="prev">이전</button>
			<span class = "mx-2" id="page">0</span>
			<button class = "btn btn-primary" id="next">다음</button>
		</div>
	</div>
</div>
<script>
	let page = "${page}";
	$("#page").html(page);
	
	$("#prev").on("click", function(){
		page--;
		location.href="/pro/list?page=" + page;
	});
	$("#next").on("click", function(){
		page++;
		location.href="/pro/list?page=" + page;
	});
</script>