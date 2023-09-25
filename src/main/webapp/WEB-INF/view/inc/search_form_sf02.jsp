<%@ page contentType="text/html; charset=UTF-8" %>
<!-- 検索フォーム表示	-->
<hr id="hr_all"></hr>
<form action="SearchContents" method="POST" accept-charset="UTF-8">

<div id="search_form"><p>
<input type="submit" value="検索" />
<input type="reset" value="リセット" />
</p></div>

<div id="search_form">
<label>チケットID:
<c:if test="${ empty contents_id }">
<input type="number" name="contents_id" placeholder="数字10桁" size="5" minlength="10" maxlength="10" />
</c:if>
<c:if test="${ not empty contents_id }">
<input type="text" name="contents_id" placeholder="${ contents_id }" size="18" maxlength="64" />
</c:if>
</label>

<label>種別:
<input list="contents_type_list" id="contents_type_cd" name="contents_type_cd" placeholder="ALL" size="8" />
<datalist id="contents_type_list">
<!-- <option value="0" selected>ALL</option>  -->
<option value="0">ALL</option>
<option value="1">INFO</option>
<option value="2">REQUEST</option>
<option value="3">INCIDENT</option>
<option value="4">MATTER</option>
</datalist>
</label>

<label>ステータス:
<input list="contents_status_list" id="contents_status_cd" name="contents_status_cd" placeholder="ALL" size="8" />
<datalist id="contents_status_list">
<!-- <option value="0" selected>ALL</option>  -->
<option value="0">ALL</option>
<option value="1">OPENED</option>
<option value="2">CLOSED</option>
<option value="3">WAITING</option>
<option value="4">CANCELED</option>
</datalist>
</label>
</div>

<div id="search_form">
<label>タイトル:
<c:if test="${ empty contents_title }">
<input type="text" name="contents_title" placeholder="検索文字" size="18" maxlength="64" />
</c:if>
<c:if test="${ not empty contents_title }">
<input type="text" name="contents_title" placeholder="${ contents_title }" size="18" maxlength="64" />
</c:if>
</label>

<label>コンテンツ:
<c:if test="${ empty contents_data }">
<input type="text" name="contents_data" placeholder="検索文字" size="24" maxlength="64" />
</c:if>
<c:if test="${ not empty contents_data }">
<input type="text" name="contents_data" placeholder="${ contents_data }" size="18" maxlength="64" />
</c:if>
</label>
</div>

<%-- 
<div id="search_form">
<label>対象開始日:
<input type="date" name="contents_created_start_at" />
<fmt:formatDate value="${contents_created_start_at}" pattern="yyyy/MM/dd HH:mm" />
</label> ～
<label>対象終了日:
<input type="date" name="contents_created_end_at" /><br>
<fmt:formatDate value="${contents_created_end_at}" pattern="yyyy/MM/dd HH:mm" />
</label>
</div>
--%>

 </div>
</form>

<script defer type="text/javascript">
//	DOM（HTML）読み込み完了後の処理
$(document).ready(function() {
//	検索キーワード取得
let searchTerm1 = "${ contents_title }";
let searchTerm2 = "${ contents_data }";
//	検索キーワードの正規表現変換
let regex1 = new RegExp(searchTerm1, "gi"); // "gi"は文字の大・小の判別せずに検索
let regex2 = new RegExp(searchTerm2, "gi"); // "gi"は文字の大・小の判別せずに検索
//	テキストコンテンツを検索し、ハイライトさせる表示タグに置換
$(".result_list_data").find("p").each(function() {
	let text = $(this).text();
	let highlightedText2 = text.replace(regex2, '<span class="highlight">$&</span>');
	$(this).html(highlightedText2);
});

$(".result_list_title").find("p").each(function() {
	let text = $(this).text();
	let highlightedText1 = text.replace(regex1, '<span class="highlight">$&</span>');
	$(this).html(highlightedText1);
});
/*
// マッチした行の横にカラーマーカー表示（要修正）
$(".highlight").each(function() {
$(this).after('<ul class="marker"><li></li></ul>');
});
*/
});

</script>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
<script>
//	テキストマーカーアニメーション
$(window).scroll(function (){
 $(".marker-animation").each(function(){
   var position = $(this).offset().top;	//	ページの一番上から要素までの距離を取得
   var scroll = $(window).scrollTop();	//	スクロールの位置を取得
   var windowHeight = $(window).height();	//	ウインドウの高さを取得
   if (scroll > position - windowHeight){	//	スクロール位置が要素の位置を過ぎたとき
     $(this).addClass('active');	//	クラス「active」を与える
   }
 });
});	
</script>
