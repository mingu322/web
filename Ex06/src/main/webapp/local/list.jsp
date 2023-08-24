<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<div class = "row my-5">
	<div class = "col">
		<h1 class = "text-center">상품목록</h1>
		<div class = "row">
			<form name = "frm" class = "col-6 col-md-4">
				<div class = "input-group">
					<input name = "query" placeholder = "검색" class = "form-control">
					<button class = "btn btn-primary">검색</button>
				</div>
			</form>
			<div class = "col pt-2">
				<span>검색수:</span>
				<span id = "total">100</span>
			</div>
		</div>
	</div>
</div>