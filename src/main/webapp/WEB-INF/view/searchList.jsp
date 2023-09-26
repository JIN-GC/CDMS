<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ include file="inc/import_i02.jsp"%>
<!DOCTYPE html>
<html>
<head>
	<title id="head_title">SEARCH LIST OF</title>
	<jsp:include page="inc/meta_m03.jsp" />
	<link rel="icon" href="data:,">
</head>
<body class="general_template">
	<jsp:include page="inc/header_h01.jsp" />
	<div><h3 id="header_title"></h3></div>
	<%@ include file="inc/login_auth_la02.jsp"%>
	<%@ include file="inc/search_form_sf02.jsp"%>
	
	<hr id="hr_all"></hr>
	<div>
		<table>
			<%-- <% for (Contents result : SearchResultsList) { %>
			<p><%= result.getContents_id() %> :: <%= result.getContents_title() %> :: <%= result.getContents_created_at() %> :: <%= result.getContents_closed_at() %></p>
			<p><%= result.getContents_data() %></p>
			<% } %>　--%>
			<c:forEach var="result" items="${SerchDataList}">
				<div class="result_list_title"><strong><p>
					<c:out value="${result.contents_id}" />
					:
					<c:out value="${result.contents_title}" />
					:
					<c:out value="${result.contents_created_at}" />
					:
					<c:out value="${result.contents_closed_at}" />
					:
				</div></p></strong>
				<div class="result_list_data" ><p id="result" class="marker-animation ma-blue">
					<c:out value="${result.contents_data}" />
				</div></p>
			</c:forEach>
		</table>
	</div>
	<jsp:include page="inc/footer_f01.jsp" />
</body>
</html>

