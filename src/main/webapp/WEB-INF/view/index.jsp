<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ include file="inc/import_i01.jsp"%>
<head>
	<title id="head_title">INDEX OF</title>
<jsp:include page="inc/meta_m02.jsp" />
</head>
<body class="general_template">
	<jsp:include page="inc/header_h01.jsp" />
	<div><h3 id="header_title"></h3></div>
	
	<div id="login_form">
		<div>
		<form action="LoginAuth" method="POST">
			<div>ユーザー名: <input type="text" name="user_name" size="25" autocomplete="username" /></div>
			<div>パスワード: <input type="password" name="user_pass" size="25" minlength="4" /></div>
			<!-- ※ autocomplete 属性は、入力欄にどの種類の情報が期待されているかをブラウザーに示唆することができるのに加え、ユーザーエージェントがフォーム入力欄の値を埋めるための自動支援を提供する必要があることを指定することができます。-->
			<div><input type="submit" value="ログイン" />
			<input type="checkbox" name="autologin" checked="checked" value="True" />
			<span>次回から自動的にログイン</span></div>
		</form>
		</div>
		
		<div>
		<form id="form_redirect_01" action="Redirect" method="POST">
			<a href="javascript:void(0);" onclick="click_redirect_01(); return false;" >ユーザーIDおよびパスワードを忘れた場合</a>
			<input type="hidden" name="redirect_page" value="/WEB-INF/view/verify.jsp" >
			<input style="display:none" type="submit" />
		</form>
		</div>
		<div>
		<form id="form_redirect_02" action="Redirect" method="POST">
			<a href="javascript:void(0);" onclick="click_redirect_02(); return false;" >アカウントを作成</a>
			<input type="hidden" name="redirect_page" value="/WEB-INF/view/register.jsp" >
			<input style="display:none" type="submit" />
		</form>
		</div>
	</div>
	
	<jsp:include page="inc/footer_f01.jsp" />
</body>
</html>

<script>
	function click_redirect_01() { document.getElementById("form_redirect_01").submit(); }
	function click_redirect_02() { document.getElementById("form_redirect_02").submit(); }
</script>