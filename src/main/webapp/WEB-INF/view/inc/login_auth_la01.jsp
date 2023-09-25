<%@ page contentType="text/html; charset=UTF-8"%>
<!-- ログインステータス	-->
<% if (loginAuth != null) { %>
	<p>[
	<% if (loginAuth.getUser_Fg() <= 1) {%>
	<%= loginAuth.getUser_Fg_Type(loginAuth.getUser_Fg()) %>
	<% } %>
	]
	<%= loginAuth.getUser_Roll(loginAuth.getUser_Roll_Cd()) %>
	.
	<%= loginAuth.getUser_Name() %>
	[ONLINE] <a href="LogoutAuth">LOGOUT</a>
	</p>
<% } else { %>
	<p><span style="color: red;"> ログインエラー</span>
	<a href="index.jsp">ログイン画面へ戻る</a></p>
<% } %>