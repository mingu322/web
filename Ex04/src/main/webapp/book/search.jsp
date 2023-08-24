<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <style>
	#div_book img {
		cursor : pointer;
	}
</style>
<div class = "row">
	<div class = "col">
		<h1 class = "text-center mb-5">도서검색</h1>
		<div class = "row">
			<form name = "frm" class = "col-6 col-md-4 col-lg-3">
				<div class = "input-group">
					<input class = "form-control" value = "깜냥" name = "query">
					<button class = "btn btn-primary">검색</button>
				</div>		
			</form>
		</div>
		<hr>
		<div id = "div_book"></div> 
		<div class = "text-center">
			<button class = "btn btn-primary" id = "prev">이전</button>
			<span class = "mx-3" id = "page">1/10</span>
			<button class = "btn btn-primary" id ="next">다음</button>
		</div>
	</div>
</div>
<script id = "temp_book" type = "text/x-handlebars-template">
	<table class = "table">
	<tr>
		<td colspan=5>
			<input type = "checkbox" id = "all">
			<button class = "btn btn-primary btn-sm" id = "btn-save">선택한항목 저장</button>
		</td>
	</tr>
	{{#each documents}}
	<tr>
		<td><input type = "checkbox" class = "chk" book="{{toString @this}}"></td>
		<td><img src = "{{printImage thumbnail}}" width = "50px" index ="{{@index}}"></td>
		<td>{{title}}</td>
		<td>{{fmtPrice price}}</td>
		<td>{{authors}}</td>
	</tr>
	<!-- Modal -->
<div class="modal fade" id="modal{{@index}}" data-bs-backdrop="static" data-bs-keyboard="false" tabindex="-1" aria-labelledby="staticBackdropLabel" aria-hidden="true">
  <div class="modal-dialog modal-lg">
    <div class="modal-content">
      <div class="modal-header">
        <h1 class="modal-title fs-5" id="staticBackdropLabel">{{title}}</h1>
        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
      </div>
      <div class="modal-body">
        <div class = "row">
			<div class = "col-4 card p-3 me-3">
				<img src = "{{printImage thumbnail}}">
			</div>
				<div class = "col">
					<h5>제목: {{title}}</h5>
					<h5>가격: {{fmtPrice price}}</h5>
					<h5>출판사: {{publisher}}</h5>
					<h5>저자: {{authors}}</h5>
					<h5><a href = "{{url}}">사이트이동</a></h5>
				</div>
			</div>
			<hr>
			<div>
				<p class = "card p-3">{{contents}}</p>	
			</div>
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Close</button>
      </div>
    </div>
  </div>
</div>
	{{/each}}
	</table>
</script>
<script>
	Handlebars.registerHelper("fmtPrice", function(price){
		return price.toString().replace(/\B(?=(\d{3})+(?!\d))/g, ",") + "원"
	});
	
	Handlebars.registerHelper("printImage", function(image){
		if(image) return image;
		else return "http://via.placeholder.com/120x174";
	});
	
	Handlebars.registerHelper("toString", function(book){
		return JSON.stringify(book);
	});
</script>
<script>
	let page = 1;
	let query = $(frm.query).val();
	getList();
	
	//선택 저장 버튼을 누른 경우
	$("#div_book").on("click", "#btn-save", function(){
		const chk=$("#div_book .chk:checked").length;
		if(chk==0){
			alert("저장할 항목을 선택하세요.");
		}else{
			if(confirm("선택한 항목을 저장하실래요?")){
				//선택한 항목 저장
				$("#div_book .chk:checked").each(function(){
					const book=JSON.parse($(this).attr("book"));
					console.log(book);
					$.ajax({
						type:"post",
						url:"/book/insert",
						data:book,
						success:function(){}
					});
				});
				alert("저장이 완료되었습니다.")
			}else{
				alert("저장이 취소되었습니다.")
			}
			$("#div_book .chk").prop("checked", false);
			$("#div_book #all").prop("checked", false);
		}
	});
	
	//전체 체크박스를 클릭한 경우
	$("#div_book").on("click", "#all", function(){
		if($(this).is(":checked")){
			$("#div_book .chk").prop("checked", true);
		}else{
			$("#div_book .chk").prop("checked", false);
		}
	});
	
	//각 행의 체크박스를 클릭한 경우
	$("#div_book").on("click", ".chk", function(){
		const all = $("#div_book .chk").length;
		const chk = $("#div_book .chk:checked").length;
		if(all==chk){
			$("#div_book #all").prop("checked", true);
		}else{				 
			$("#div_book #all").prop("checked", false);
		}
	});
	
	//각 행의 이미지를 클릭한 경우
	$("#div_book").on("click", "img", function(){
		const index=$(this).attr("index");
		$("#modal" + index).modal("show");
	});
	
	$(frm).on("submit", function(e){
		e.preventDefault();
		page = 1;
		query = $(frm.query).val();
		getList();
	});
	$("#next").on("click", function(){
		page++;
		getList();
	});
	
	$("#prev").on("click", function(){
		page--;
		getList();
	});
	function getList(){
	$.ajax({
		type:"get",
		url:"https://dapi.kakao.com/v3/search/book?target=title",
		data:{query:query, size:3, page:page},
		headers:{"Authorization":"KakaoAK f516bd0c7aaa3a6e534b343861f7e734"},
		dataType:"json",
		success:function(data){
			console.log(data);
			const temp = Handlebars.compile($("#temp_book").html());
			const html = temp(data);
			$("#div_book").html(html);
			
			let last = Math.ceil(data.meta.pageable_count/3);
			$("#page").html(page + "/" + last);
			
			if(page==1) $("#prev").attr("disabled", true);
			else  $("#prev").attr("disabled", false);
			
			if(data.meta.is_end) $("#next").attr("disabled", true);
			else  $("#next").attr("disabled", false);
		}		
	});
}	
</script>