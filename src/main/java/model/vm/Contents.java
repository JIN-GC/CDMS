package model.vm;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Contents {

	private int contents_id;	//	コンテンツ#
	private int contents_status_cd;	//	ステータスコード
	private int req_user_id;	//	依頼ユーザー#
	private int res_user_id;	//	対応ユーザー#
	private String res_user_ids;	//	対応ユーザー#履歴
	private int contents_type_cd;	//	チケット種別コード
	private String contents_title; //	コンテンツタイトル
	private String contents_data; //	コンテンツ内容
	private int contents_category1_cd;	//	チケットカテゴリコード1
	private int contents_category2_cd;	//	チケットカテゴリコード2
	private int contents_category3_cd;	//	チケットカテゴリコード3
	private String parents_contents_ids;	//	参照元チケット#
	private String children_contents_ids;	//	参照先チケット#
	private String ref_contents_ids;	//	参照チケット#
	private int media_id;	//	データファイル#
	SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm");	//	("yyyy/MM/dd HH:mm XXX")
	Date customDate = new Date(100, 0, 1, 0, 0); // 2022年1月15日 14:30を表すDateオブジェクト
	private String contents_created_at = dateFormat.format(customDate);	//	チケット作成日時
	private String contents_created_start_at = dateFormat.format(customDate);	//	チケット作成日時
	private String contents_created_end_at = dateFormat.format(customDate);	//	チケット作成日時
	private String contents_updated_at = dateFormat.format(customDate);	//	チケット更新日時
	private String contents_cancelled_at = dateFormat.format(customDate);	//	チケットキャンセル日時
	private String contents_closed_at = dateFormat.format(customDate);	//	チケット終了日時
	private String contents_updated_history;	//	チケット更新履歴
	private String search_key;	//	検索キー
	private int select_page;	//	検索参照ページ
	private int searched_pages;	//	検索ページ数

	public Contents() {
	}
	//	暫定保存用
	public Contents(String contents_title, String contents_data) {
		this.contents_title = contents_title;
		this.contents_data = contents_data;
	}
	//	JSPへのrequest.setAttribute用
	public Contents(String search_key, int select_page, int searched_pages, int contents_id, int contents_type_cd, int contents_status_cd, String contents_title,
			String contents_data, String contents_created_start_at, String contents_created_end_at ) {
		this.search_key = search_key;
		this.select_page = select_page;
		this.searched_pages = searched_pages;
		
		this.contents_id = contents_id;
		this.contents_type_cd = contents_type_cd;
		this.contents_status_cd = contents_status_cd;
		this.contents_title = contents_title;
		this.contents_data = contents_data;
		this.contents_created_start_at = contents_created_start_at;
		this.contents_created_end_at = contents_created_end_at;
	}
	//	DAO　Response用
	public Contents(int contents_id, int contents_type_cd, int contents_status_cd, String contents_title,
			String contents_data, String contents_created_at, String contents_updated_at, String contents_cancelled_at, String contents_closed_at, String contents_updated_history) {
		this.contents_id = contents_id;
		this.contents_type_cd = contents_type_cd;
		this.contents_status_cd = contents_status_cd;
		this.contents_title = contents_title;
		this.contents_data = contents_data;
		this.contents_created_at = contents_created_at;
		this.contents_updated_at = contents_updated_at;
		this.contents_cancelled_at = contents_cancelled_at;
		this.contents_closed_at = contents_closed_at;
		this.contents_updated_history = contents_updated_history;
	}
	
	public int getContents_id() {
		return contents_id;
	}
	public void setContents_id(int contents_id) {
		this.contents_id = contents_id;
	}
	public int getContents_status_cd() {
		return contents_status_cd;
	}
	public void setContents_status_cd(int contents_status_cd) {
		this.contents_status_cd = contents_status_cd;
	}
	public int getReq_user_id() {
		return req_user_id;
	}
	public void setReq_user_id(int req_user_id) {
		this.req_user_id = req_user_id;
	}
	public int getRes_user_id() {
		return res_user_id;
	}
	public void setRes_user_id(int res_user_id) {
		this.res_user_id = res_user_id;
	}
	public String getRes_user_ids() {
		return res_user_ids;
	}
	public void setRes_user_ids(String res_user_ids) {
		this.res_user_ids = res_user_ids;
	}
	public int getContents_type_cd() {
		return contents_type_cd;
	}
	public void setContents_type_cd(int contents_type_cd) {
		this.contents_type_cd = contents_type_cd;
	}
	public String getContents_title() {
		return contents_title;
	}
	public void setContents_title(String contents_title) {
		this.contents_title = contents_title;
	}
	public String getContents_data() {
		return contents_data;
	}
	public void setContents_data(String contents_data) {
		this.contents_data = contents_data;
	}
	public int getContents_category1_cd() {
		return contents_category1_cd;
	}
	public void setContents_category1_cd(int contents_category1_cd) {
		this.contents_category1_cd = contents_category1_cd;
	}
	public int getContents_category2_cd() {
		return contents_category2_cd;
	}
	public void setContents_category2_cd(int contents_category2_cd) {
		this.contents_category2_cd = contents_category2_cd;
	}
	public int getContents_category3_cd() {
		return contents_category3_cd;
	}
	public void setContents_category3_cd(int contents_category3_cd) {
		this.contents_category3_cd = contents_category3_cd;
	}
	public String getParents_contents_ids() {
		return parents_contents_ids;
	}
	public void setParents_contents_ids(String parents_contents_ids) {
		this.parents_contents_ids = parents_contents_ids;
	}
	public String getChildren_contents_ids() {
		return children_contents_ids;
	}
	public void setChildren_contents_ids(String children_contents_ids) {
		this.children_contents_ids = children_contents_ids;
	}
	public String getRef_contents_ids() {
		return ref_contents_ids;
	}
	public void setRef_contents_ids(String ref_contents_ids) {
		this.ref_contents_ids = ref_contents_ids;
	}
	public int getMedia_id() {
		return media_id;
	}
	public void setMedia_id(int media_id) {
		this.media_id = media_id;
	}
	public String getContents_created_at() {
		return contents_created_at;
	}
	public void setContents_created_at(String contents_created_at) {
		this.contents_created_at = contents_created_at;
	}
	public String getContents_updated_at() {
		return contents_updated_at;
	}
	public void setContents_updated_at(String contents_updated_at) {
		this.contents_updated_at = contents_updated_at;
	}
	public String getContents_cancelled_at() {
		return contents_cancelled_at;
	}
	public void setContents_cancelled_at(String contents_cancelled_at) {
		this.contents_cancelled_at = contents_cancelled_at;
	}
	public String getContents_closed_at() {
		return contents_closed_at;
	}
	public void setContents_closed_at(String contents_closed_at) {
		this.contents_closed_at = contents_closed_at;
	}
	public String getContents_updated_history() {
		return contents_updated_history;
	}
	public void setContents_updated_history(String contents_updated_history) {
		this.contents_updated_history = contents_updated_history;
	}
	public String getSearch_Key() {
		return search_key;
	}
	public void setSearch_Key(String search_key) {
		this.search_key = search_key;
	}
	public int getSelect_Page() {
		return select_page;
	}
	public void setSelect_Page(int select_page) {
		this.select_page = select_page;
	}
	public int getSearched_Pages() {
		return searched_pages;
	}
	public void setSearched_Pages(int searched_pages) {
		this.searched_pages = searched_pages;
	}
}
