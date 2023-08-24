<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div class = "row my-5">
	<div class = "col">
		<h1 class = "text-center mb-3">강좌정보수정</h1>
			<form name = "frm1" action = "/cou/update" method = "post">
		<div class = "input-group mb-3">
			<span class = "input-group-text">강좌번호</span>
			<input name = "lcode" class = "form-control" value = "${vo.lcode}" readonly>
		</div>
		<div class = "input-group mb-3">
			<span class = "input-group-text">강좌이름</span>
			<input name = "lname" class = "form-control" value = "${vo.lname}">
		</div>
		<div class = "input-group mb-3">
			<span class = "input-group-text">강의실</span>
			<input name = "room" class = "form-control" value = "${vo.room}">
		</div>
		<div class = "input-group mb-3">
			<span class = "input-group-text">강의시수</span>
			<input name = "hours" class = "form-control" value = "${vo.hours}" oninput = "isNumber(this)">
		</div>
		<div class = "input-group mb-3">
			<span class = "input-group-text">최대인원</span>
			<input name = "capacity" class = "form-control" value = "${vo.capacity}" oninput = "isNumber(this)">
		</div>
		<div class = "input-group mb-3">
			<span class = "input-group-text">수강인원</span>
			<input name = "persons" class = "form-control" value = "${vo.persons}" oninput = "isNumber(this)">
		</div>
		<div class = "input-group mb-3">
			<span class = "input-group-text">담당교수</span>
			<select name = "instructor" class = "form-select">
			<c:forEach items="${parray}" var="p">
				<option value = "${p.pcode}"
				<c:out value="${p.pcode==vo.instructor?'selected':''}"/>>
				${p.pcode}:${p.pname}:${p.dept}
				</option>
			</c:forEach>
			</select>
		</div>
		<div class = "text-center">
			<input type = "submit" value = "정보수정" class = "btn btn-primary">
			<input type = "reset" value = "수정취소" class = "btn btn-secondary">
		</div>
	</form>
	</div>
</div>
<script>
$(frm1).on("submit", function(e){
	e.preventDefault();
	if(confirm("강좌 정보를 수정하실래요?")){
		frm1.submit();
	}
});
function isNumber(item){
    item.value=item.value.replace(/[^0-9]/g,'');
}
</script>