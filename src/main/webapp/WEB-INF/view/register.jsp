<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="inc/import_i01.jsp"%>

<!DOCTYPE html>
<html>

<head>
	<title id="head_title">Verify Account</title>
	<jsp:include page="inc/meta_m02.jsp" />
</head>

<body class="general_template">
	<jsp:include page="inc/header_h01.jsp" />
	<div><h3 id="header_title"></h3></div>
	
	<div id="link_top" ><p>[ <a href="Main">TOP PAGE</a> ]</p></div>
	<div id="info">
	<div><h5></h5></div>

		<form action="RegistUser" method="POST" autocomplete="off">
		<div><strong>ユーザーID登録(画面イメージ)</strong></div>
		<!-- entryBlock -->
		<div class="entryBlock">
		<ul style="list-style: none;">
		
		<li><div><table><tbody><tr><th rowspan="1" colspan="1"><strong>ユーザーネーム：</strong></th><td rowspan="1" colspan="1"><input type="text" style="width: 200px;" placeholder="4文字以上のユーザネーム" value="" name="user_name" autocomplete="off"></td></tr></tbody></div></table></li>
		<li><div><table><tbody><tr><th rowspan="1" colspan="1"><strong>パスワード：</strong></th><td rowspan="1" colspan="1"><input type="text" style="width: 200px;" placeholder="10桁の英数字パスワード" value="" name="user_pass" autocomplete="off"></td></tr></tbody></table></div></li>
		<br />
		<li><div><table border="0" cellpadding="0" cellspacing="0"><tbody><tr><th rowspan="1" colspan="1"><strong>メールアドレス：</strong></th><td rowspan="1" colspan="1"><input type="text" style="width: 160px;" name="mailAddressLocal" value="" autocomplete="off">＠<select name="mailAddressDomain"><option value=""></option><option value="hotmail.com">hotmail.com</option><option value="gmail.com">gmail.com</option><option value="yahoo.ne.jp">yahoo.ne.jp</option><option value="disney.ne.jp">disney.ne.jp</option></select></td></tr></tbody></table></div></li>
		<li><div><table border="0" cellpadding="0" cellspacing="0"><tbody><tr><th rowspan="1" colspan="1"><strong>お名前：</strong></th><td rowspan="1" colspan="1"><input type="text" style="width: 110px;" placeholder="姓" name="lastName" value="" autocomplete="off"><input type="text" style="width: 110px;" placeholder="名" name="firstName" value="" autocomplete="off"></td></tr></tbody></table></div></li>
		<li><div><table border="0" cellpadding="0" cellspacing="0"><tbody><tr><th rowspan="1" colspan="1"><strong>フリガナ：</strong></th><td rowspan="1" colspan="1"><input type="text" style="width: 110px;" placeholder="セイ" name="lastNameKana" value="" autocomplete="off"><input type="text" style="width: 110px;" placeholder="メイ" name="firstNameKana" value="" autocomplete="off"></td></tr></tbody></table></div></li>
		<li><div><table border="0" cellpadding="0" cellspacing="0"><tbody><tr><th rowspan="1" colspan="1"><strong><span></span><span>郵便番号：</span></strong></th><td rowspan="1" colspan="1"><input type="text" style="width: 45px;" maxlength="3" name="zip_code1" value="" autocomplete="off">-<input type="text"style="width: 60px;" maxlength="4" name="zip_code2" value="" autocomplete="off"><a href="javascript:void(0)" shape="rect">住所を検索する</a></td></tr></tbody></table></div></li>
		<li><div><table border="0" cellpadding="0" cellspacing="0"><tbody><tr><th rowspan="1" colspan="1"><strong>都道府県：</strong></th><td rowspan="1" colspan="1"><input type="text" style="width: 250px;" name="prefrcture" value="" autocomplete="off"></td></tr></tbody></table></div></li>
		<li><div><table border="0" cellpadding="0" cellspacing="0"><tbody><tr><th rowspan="1" colspan="1"><strong>市区町村：</strong></th><td rowspan="1" colspan="1"><input type="text" style="width: 250px;" name="city" value="" autocomplete="off"></td></tr></tbody></table></div></li>
		<li><div><table border="0" cellpadding="0" cellspacing="0"><tbody><tr><th rowspan="1" colspan="1"><strong>番地・その他住所：</strong></th><td rowspan="1" colspan="1"><input type="text" style="width: 250px;" name="addressNo" value="" autocomplete="off"></td></tr></tbody></table></div></li>
		<li><div><table border="0" cellpadding="0" cellspacing="0"><tbody><tr><th rowspan="1" colspan="1"><strong>建物名など：</strong></th><td rowspan="1" colspan="1"><input type="text" style="width: 250px;" name="building" value="" autocomplete="off"></td></tr></tbody></table></div></li>
		<li><div><table border="0" cellpadding="0" cellspacing="0"><tbody><tr><th rowspan="1" colspan="1"><strong>電話番号：</strong></th><td rowspan="1" colspan="1"><input type="text" style="width: 60px;" name="tel.tel1" value="" autocomplete="off">-<input type="text" style="width: 60px;" name="tel.tel2" value="" autocomplete="off">-<input type="text" style="width: 60px;" name="tel.tel3" value="" autocomplete="off"></td></tr></tbody></table></div></li>
		<li><div><table border="0" cellpadding="0" cellspacing="0"><tbody><tr><th rowspan="1" colspan="1"><strong>生年月日：</strong></th><td rowspan="1" colspan="1"><input type="text" style="width: 60px;" maxlength="4" placeholder="西暦" name="birthday.year" value="" autocomplete="off"><span>年</span><select name="birthday.month"><option value=" ">&nbsp;</option><option value="1">1</option><option value="2">2</option><option value="3">3</option><option value="4">4</option><option value="5">5</option><option value="6">6</option><option value="7">7</option><option value="8">8</option><option value="9">9</option><option value="10">10</option><option value="11">11</option><option value="12">12</option></select><span>月</span><select name="birthday.day"><option value=" ">&nbsp;</option><option value="1">1</option><option value="2">2</option><option value="3">3</option><option value="4">4</option><option value="5">5</option><option value="6">6</option><option value="7">7</option><option value="8">8</option><option value="9">9</option><option value="10">10</option><option value="11">11</option><option value="12">12</option><option value="13">13</option><option value="14">14</option><option value="15">15</option><option value="16">16</option><option value="17">17</option><option value="18">18</option><option value="19">19</option><option value="20">20</option><option value="21">21</option><option value="22">22</option><option value="23">23</option><option value="24">24</option><option value="25">25</option><option value="26">26</option><option value="27">27</option><option value="28">28</option><option value="29">29</option><option value="30">30</option><option value="31">31</option></select><span>日</span></td></tr></tbody></table></div></li>
		<li><div><table border="0" cellpadding="0" cellspacing="0"><tbody><tr><th rowspan="1" colspan="1"><strong>性別：</strong></th><td rowspan="1" colspan="1"><input type="radio" name="sex" value="1" checked autocomplete="off"><label>男性</label><input type="radio" name="sex" value="2" autocomplete="off"><label>女性</label></td></tr></tbody></table></div></li>
		<br />
		<li><div><input type="submit" value="登録" /></div>
		
		</ul>
		</div>
		<!-- /entryBlock -->
		</form>
	</div>
	
	<jsp:include page="inc/footer_f01.jsp" />
</body>
</html>