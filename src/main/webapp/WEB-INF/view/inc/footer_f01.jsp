<%@ page contentType="text/html; charset=UTF-8"%>
<!-- フッター表示 -->

	<hr id="hr_all"></hr>
	<section id="ac_gf_footer">
	<div id="ac_gf_footer_legal">
		<div id="ac_gf_footer_legal_copyright">Copyright © 2023 JIN's Product. All rights reserved.</div>
		<ul id="ac_gf_footer_legal_links" role="list">
			<li id="ac_gf_footer_legal_links_item" role="listitem">
			<a id="ac_gf_footer_legal_link" href="javascript:void(0);" onclick="click_redirect1(); return false;" >プライバシーポリシー</a></li>
			<li id="ac_gf_footer_legal_links_item" role="listitem">
			<a id="ac_gf_footer_legal_link" href="javascript:void(0);" onclick="click_redirect2(); return false;" >利用規約</a></li>
			<li id="ac_gf_footer_legal_links_item" role="listitem">
			<a id="ac_gf_footer_legal_link" href="javascript:void(0);" onclick="click_redirect3(); return false;" >About</a></li>
 		</ul>
	</div>
	</section>
		<form id="form_redirect_f01" action="Redirect" method="POST">
			<input type="hidden" name="redirect_page" value="/WEB-INF/view/legal.jsp" >
			<input style="display:none" type="submit" />
		</form>
		<form id="form_redirect_f02" action="Redirect" method="POST">
			<input type="hidden" name="redirect_page" value="/WEB-INF/view/privacy.jsp" >
			<input style="display:none" type="submit" />
		</form>
		<form id="form_redirect_f03" action="Redirect" method="POST">
			<input type="hidden" name="redirect_page" value="/WEB-INF/view/profile.jsp" >
			<input style="display:none" type="submit" />
		</form>
	<hr id="hr_all"></hr>
<script>
function click_redirect1() { document.getElementById("form_redirect_f01").submit(); }
function click_redirect2() { document.getElementById("form_redirect_f02").submit(); }
function click_redirect3() { document.getElementById("form_redirect_f03").submit(); }
/* 
	function submitForm(action, param1, value1, param2, value2) {
	// フォームの要素を設定
	document.getElementById('formAction').value = action;
	document.getElementById('formParam1').value = param1;
	document.getElementById('formParam2').value = param2;
	// フォームをサブミット
	document.getElementById('footerForm').submit();
	}
 */
</script>
<!--
	<li id="ac_gf_footer_legal_links_item" role="listitem">
	<a id="ac_gf_footer_legal_link" href="#" onclick="submitForm('Redirect', 'redirect_page', 'legal.jsp', 'user_name', '${user_name}')" >プライバシーポリシー</a></li>
	<li id="ac_gf_footer_legal_links_item" role="listitem">
	<a id="ac_gf_footer_legal_link" href="#" onclick="submitForm('Redirect', 'redirect_page', 'privacy.jsp', 'user_name', '${user_name}')" >利用規約</a></li>
	<li id="ac_gf_footer_legal_links_item" role="listitem">
	<a id="ac_gf_footer_legal_link" href="#" onclick="submitForm('Redirect', 'redirect_page', 'profile.jsp', 'user_name', '${user_name}')" >About</a></li>
-->
<!-- JQuery 参照	https://whitewood-hp.com/web-tips/archives/3958 -->    
<!-- JQuery 参照	https://memo.ag2works.tokyo/post-2005/ -->    