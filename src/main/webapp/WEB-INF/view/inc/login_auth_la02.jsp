<%@ page contentType="text/html; charset=UTF-8" %>
<!-- ログインステータス	-->
<c:if test="${ not empty loginAuth }">
	<c:if test="${ user_fg <= 1 }">
		<div class="login_status"><p>
			[ <a href="Main">TOP PAGE</a>
			 ] [ <span><c:out value="${ loginAuth.getUser_Fg_Type(user_fg) } " /></span>
	</c:if>
	<c:out value="${ loginAuth.getUser_Roll(user_roll_cd) } " />
			  : <span><c:out value="${ user_name }" /></span>
			 ][ <span>ONLINE</span> ] [ <a href="LogoutAuth">LOGOUT</a> ]
	</p></div>
</c:if>
<c:if test="${ empty loginAuth }">
	<div class="login_status"><p>[ <span style="color: red;">LOGIN ERROR</span> ] [ <a href="Main">TRY AGAIN to LOGIN</a> ]</p></div>
</c:if>