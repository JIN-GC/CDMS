<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="inc/import_i01.jsp"%>

<!DOCTYPE html>
<html>
<head>
	<title id="head_title">Verify Account</title>
	<jsp:include page="inc/meta_m01.jsp" />
</head>
<body class="general_template">
	<jsp:include page="inc/header_h01.jsp" />
	<div><h3 id="header_title"></h3></div>
	
	<div id="link_top" ><p>[ <a href="Main">TOP PAGE</a> ]</p></div>
	<div id="info">
		<form action="VerifyUser" method="POST" autocomplete="off">
		<div><h5>パスワード再設定(暫定表示画面)</h5></div>
		<ul id="list_base">
		<li><div>パスワード再発行・アカウントロック解除の為、メールアドレスを入力してください。</div></li>
		<br />
		<li><div><table border="0" cellpadding="0" cellspacing="0"><tbody><tr><th rowspan="1" colspan="1"><strong>　仮パスワード送信先メールアドレス：</strong></th><td rowspan="1" colspan="1"><input type="text" style="width: 160px;" name="mailAddressLocal" value="" autocomplete="off">＠<select name="mailAddressDomain"><option value=""></option><option value="hotmail.com">hotmail.com</option><option value="gmail.com">gmail.com</option><option value="yahoo.ne.jp">yahoo.ne.jp</option><option value="disney.ne.jp">disney.ne.jp</option></select></td></tr></tbody></table></div></li>
		<br />
		<li><div><input type="submit" value="発行" /></div>
		</ul>
		</form>
	</div>
	
	<jsp:include page="inc/footer_f01.jsp" />
</body>
</html>