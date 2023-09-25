<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ include file="inc/import_i01.jsp"%>
<!DOCTYPE html>
<html>
<head>
<title id="head_title">LOGIN FAILED ON</title>
<jsp:include page="inc/meta_m02.jsp" />
</head>
<body class="general_template">
	<jsp:include page="inc/header_h01.jsp" />
	<div><h3 id="header_title"></h3></div>
	<div id="info">
		<span style="color:red">ログインできませんでした。 username および passsword をご確認ください。</span>
		<p><a href="Main">TOP PAGE へ戻る</a></p>
	</div>
	<jsp:include page="inc/footer_f01.jsp" />
</body>
</html>