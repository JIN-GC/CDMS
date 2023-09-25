<%@ page import="java.util.List"%>
<%@ page import="model.vm.Contents" %>
<%@ page import="model.vm.Users" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<% String errorMsg = (String)request.getAttribute("errorMsg"); %>
<% Users loginAuth = (Users)session.getAttribute("loginAuth"); %>
<% List<Contents> SerchDataList = (List<Contents>)request.getAttribute("SerchDataList"); %>
<% Contents SerchRequestData = (Contents)request.getAttribute("SerchRequestData"); %>
<% String contents_title = (String)request.getAttribute("contents_title"); %>
<% String contents_data = (String)request.getAttribute("contents_data"); %>
<script src="https://code.jquery.com/jquery-3.7.1.min.js" integrity="sha256-/JqT3SQfawRcv/BIHPThkBvs0OEvtFFmqPF/lYI/Cxo=" crossorigin="anonymous"></script>
<!-- <script src="https://code.jquery.com/jquery-3.4.1.min.js"></script> -->
