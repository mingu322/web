<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div class = "row my-5">
	<div class = "col">
		<h1 class ="text-center mb-5">수강신청</h1>
		<div class = "card p-3">
			<div class = "row">
			<div class = "col">학생번호: ${vo.scode}</div>
			<div class = "col">학생이름: ${vo.sname}</div>
			<div class = "col">학생학과: ${vo.dept}</div>
			<div class = "col">지도교수: ${vo.pname} (${vo.advisor})</div>
			</div>
		</div>
		<div class = "card p-3 my-3">
		<div class = "row">
			<div class = "col" id = "div_cou">
		 		<select class = "form-select">
		 		 <c:forEach items = "${carray}" var = "c">
		 		 	<option value = "${c.lcode}">
		 		 		${c.lname}-(${c.pname}) ${persons}/${capacity}
		 		 	</option>
		 		 </c:forEach>	 		
		 		</select>
		 	</div>
		 	<div class = "col">
		 		<button class = "btn btn-primary" id = "btn-enroll">수강신청</button>
		 	</div>
		 </div>
		</div>
		<hr>
		<div id = "div_enroll"></div>
	</div>
</div>
<!-- 강좌목록 템플릿 -->
<script id="temp_cou" type= "text/x-handlebars-template">
	<select class = "form-select" id = "lcode">
		{{#each .}}
			<option value = "{{lcode}}" {{dis persons capacity}}>
				{{lname}} {{persons}}/{{capacity}}
			</option>
		{{/each}}
	</select>
</script>
<script>
	Handlebars.registerHelper("dis", function(persons, capacity){
		if(persons>=capacity) return 
	});
</script>
<!-- 수강신청목록 템플릿 -->
<script id="temp_enroll" type= "text/x-handlebars-template">
	<table class="table table striped">
		<tr>
			<th>강좌번호</th>
			<th>강좌이름</th>
			<th>교수</th>
			<th>성적</th>
			<th>강의실</th>
			<th>강의시간</th>
			<th>총원/수강인원</th>
			<th>수강신청일</th>
			<th>취소</th>
		</tr>
		{{#each .}}
		<tr>
			<td>{{lcode}}</td>
			<td>{{lname}}</td>
			<td>{{pname}}</td>
			<td>{{grade}}</td>
			<td>{{room}}</td>
			<td>{{hours}}</td>
			<td>{{capacity}}/{{person}}</td>
			<td>{{edate}}</td>
			<td><button class = "btn btn-danger">취소</button></td>
		</tr>
		{{/each}}
	</table>	
</script>
<script>
	const scode = "${vo.scode}";
	getList();
	getCou();
	
	$("#div_enroll").on("click", "btn-danger", function(){
		const lcode = $(this).attr("lcode");
		if(confirm(lcode + "강좌를 수강취소하실래요?")){
			$.ajax({
				type : "post",
				url : "/enroll/delete",
				data : {scode:scode, lcode:lcode},
				success:function(){
					alert("수강이 취소되었습니다.");
					getList();
					getCou();
				}
			});	
		}
	});
	function getCou(){
		$.ajax({
			type : "get",
			url : "/cou/all.json",
			dataType : "json",
			 
			success:function(data){
				const temp = Handlebars.compile($("#temp_cou").html());
				const html = temp(data);
				$("#div_cou").html(html);
			}
		});
	}
	$("#btn-enroll").on("click", function(){
		const lcode = $("#lcode").val();
		if(confirm(lcode + "강좌를 수강신청 하실래요?")){
			$.ajax({
				type : "get",
				url : "/enroll/insert",
				data :{scode:scode, lcode:lcode},
				success:function(data){
					if(data==0){
						alert("수강신청이 완료되었습니다.")
						getList();
						getCou();
					}else{
						alert("이미 신청한 과목입니다.")
					}
				}
			});
		}
	});
	function getList(){
		$.ajax({
			type:"get",
			url:"/stu/enroll.json",
			data:{scode:scode},
			dataType:"json",
			success:function(data){
				const temp = Handlebars.compile($("#temp_enroll").html());
				const html = temp(data);
				$("#div_enroll").html(html);
			}
		});
	}
</script>
