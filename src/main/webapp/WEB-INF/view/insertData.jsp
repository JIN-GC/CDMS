<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ include file="inc/import_i02.jsp"%>
<!DOCTYPE html>
<html>
<head>
	<title id="head_title">INSERT DATA TO </title>
	<jsp:include page="inc/meta_m03.jsp" />
	<link rel="icon" href="data:,">
</head>
<body class="general_template">
	<jsp:include page="inc/header_h01.jsp" />
	<div><h3 id="header_title"></h3></div>
	<%@ include file="inc/login_auth_la02.jsp"%>
	
	<hr id="hr_all"></hr>
	<div>
		<form action="InsertData" method="POST" accept-charset="UTF-8">
			<div id="insert_form"><p>
			<input type="submit" value="登録" />
			<input type="reset" value="リセット" />
			</p></div>
			
			<div id="search_form">
			<label>タイトル:
			<input type="text" name="contents_title" placeholder="タイトル名" size="20" maxlength="200" required />
			※入力必須</label>
			</div>
			
			<div id="search_form">
			<label>種別:
			<input list="contents_type_list" id="contents_type_cd" name="contents_type_cd" placeholder="ALL" size="8" required />
			<datalist id="contents_type_list">
			<!-- <option value="0" selected>ALL</option>  -->
			<option value="1" selected>INFO</option>
			<option value="2">REQUEST</option>
			<option value="3">INCIDENT</option>
			<option value="4">MATTER</option>
			</datalist>
			※入力必須</label>
			
			<label>ステータス:
			<input list="contents_status_list" id="contents_status_cd" name="contents_status_cd" placeholder="ALL" size="8" required />
			<datalist id="contents_status_list">
			<!-- <option value="0" selected>ALL</option>  -->
			<option value="1" selected>OPENED</option>
			<option value="2">CLOSED</option>
			<option value="3">WAITING</option>
			<option value="4">CANCELED</option>
			</datalist>
			※入力必須</label>
			</div>
			
			<div id="search_form">
			<label>コンテンツ:
			<input type="text" name="res_user_id" placeholder="登録内容" size="80" maxlength="1000" required />
			※入力必須</label>
			</div>
			
			<%-- 			
			<div id="search_form">
			<label>カテゴリ１:
			<input list="contents_category1_list" id="contents_category1_cd" name="contents_category1_cd" placeholder="--" size="8" />
			<datalist id="contents_category1_list">
			<!-- <option value="0" selected>ALL</option>  -->
			<option value="1" selected>INFO</option>
			<option value="2">REQUEST</option>
			<option value="3">INCIDENT</option>
			<option value="4">MATTER</option>
			</datalist>
			</label>
			
			<label>カテゴリ２:
			<input id="contents_category2_list" name="contents_category2_cd" placeholder="--" size="8" />
			<datalist id="contents_category2_list">
			<!-- <option value="0" selected>ALL</option>  -->
			<option value="1" selected>category2-1</option>
			<option value="2">category2-2</option>
			<option value="3">category2-3</option>
			</datalist>
			</label>

			<label>カテゴリ３:
			<input id="contents_category2_list" name="contents_category2_cd" placeholder="--" size="8" />
			<datalist id="contents_category2_list">
			<!-- <option value="0" selected>ALL</option>  -->
			<option value="1" selected>category2-1</option>
			<option value="2">category2-2</option>
			<option value="3">category2-3</option>
			</datalist>
			</label>
			</div>
			
			<div id="search_form">
			<label>依頼者 USER ID:
			<input type="number" name="req_user_id" placeholder="数字6桁" size="5" minlength="6" maxlength="6" />
			</label>

			<label>担当者 USER ID:
			<input type="number" name="res_user_id" placeholder="数字6桁" size="5" minlength="6" maxlength="6" />
			</label>
			</div>
			--%>
			<%-- 
			<div id="search_form">
			<label>開始日:
			<input type="date" name="contents_created_at" />
			<fmt:formatDate value="${contents_created_at}" pattern="yyyy/MM/dd HH:mm" />
			</label> ～
			<label>終了日:
			<input type="date" name="contents_closed_at" /><br>
			<fmt:formatDate value="${contents_closed_at}" pattern="yyyy/MM/dd HH:mm" />
			</label>
			</div>
			--%>
			 </div>
			</form>
	</div>
	
	<jsp:include page="inc/footer_f01.jsp" />
</body>
</html>

