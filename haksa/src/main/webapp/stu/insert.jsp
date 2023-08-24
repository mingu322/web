<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<form name = "frm1" method = "post" action = "/stu/insert">
	<div class = "input-group mb-3">
		<span class = " input-group-text">학생이름</span>
		<input name = "sname" class = "form-control">
	</div>
	<div class = "input-group mb-2">
		<span class = "input-group-text">학생학과</span>
		<select name = "dept" class = "form-select">
			<option value = "전산">전자 계산학과</option>
			<option value = "컴정" selected>컴퓨터 정보공학</option>
			<option value = "전자">전자전기 공학</option>
			<option value = "건축">건축공학과</option>
		</select>
	</div>
	<div class = "input-group mb-3">
		<span class = " input-group-text">생년월일</span>
		<input name = "birthday" class = "form-control" type = "date" value = "2000-01-01">
	</div>
	<div class = "input-group mb-3">
		<span class = " input-group-text">학생학년</span>
		<input name = "year" class = "ms-2" type = "radio" value = "1" checked>
		<span class = "p-2">1학년</span>
		<input name = "year" class = "ms-2" type = "radio" value = "2">
		<span class = "p-2">2학년</span>
		<input name = "year" class = "ms-2" type = "radio" value = "3">
		<span class = "p-2">3학년</span>
		<input name = "year" class = "ms-2" type = "radio" value = "4">
		<span class = "p-2">4학년</span>
	</div>
	<div class = "input-group mb-2">
		<span class = "input-group-text">지도교수</span>
		<select name = "odvisor" class = "form-select">
			<c:forEach items = "${parray}" var="vo">
				<option value = "${vo.pcode}">${vo.pname}:${vo.dept}</option>
			</c:forEach>
		</select>
	</div>
	<div class = "text-center">
		<input type = "submit" value = "학생등록" class = "btn btn-primary">
		<input type = "reset" value = "등록취소" class = "btn btn-secondary">
	</div>
</form>
<script>
	$(frm1).on("submit", function(e){
		e.preventDefault();
		const sname = $(frm1.sname).val();
		if(sname==""){
			alert("학생 이름을 입력하세요.");
			$(frm1.sname).focus();
		}else{
			if(confirm("새로운 학생을 등록하실래요?")){
				frm1.submit();
			}
			
		}
	});
</script>