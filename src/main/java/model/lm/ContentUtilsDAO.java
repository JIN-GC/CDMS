package model.lm;

import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.naming.NamingException;

import model.vm.Contents;

public class ContentUtilsDAO extends mariaDAO {
//	public class ContentUtilsDAO extends mariaDAO Serializable {
	//	contents_tb用	コンテンツ検索検

	public List<Contents> SearchData(int contents_id, int contents_type_cd, int contents_status_cd, String contents_title, String contents_data, String contents_created_at, String contents_updated_at, String contents_cancelled_at, String contents_closed_at, String contents_updated_history, String contents_created_start_at, String contents_created_end_at) {
		StringBuilder queryB = new StringBuilder();
		List<Contents> SerchDataList = new ArrayList<Contents>();
		boolean keyFlg = false;
		int matchFlg = 0;
		int andFlg = 0;

		try {
			this.connect();
			queryB.append("SELECT "
					+ "contents_id, "
					+ "contents_type_cd, "
					+ "contents_status_cd, "
					+ "contents_title, "
					+ "contents_data, "
					+ "contents_created_at, "
					+ "contents_updated_at, "
					+ "contents_cancelled_at, "
					+ "contents_closed_at, "
					+ "contents_updated_history "
					+ "FROM contents_tb");
			
	        //NULLチェック後、SearchContentsDataの各引数に値が存在する場合 queryB変数にappend
			if(!(contents_id == 0 || "".equals(contents_id))) {
			//	if(!"".equals(contents_id)) {
				queryB.append(" WHERE ");
				queryB.append("contents_id LIKE '%" + contents_id + "'");
				keyFlg = true;
				++andFlg;
			}
			
			if(!(contents_type_cd == 0 || "".equals(contents_type_cd))) {
				if (keyFlg) {
					queryB.append(" AND ");
				} else {
					queryB.append(" WHERE ");
					keyFlg = true;
				}
				queryB.append("contents_type_cd = " + contents_type_cd);
				++andFlg;
			}
			
			if(!(contents_status_cd == 0 || "".equals(contents_status_cd))) {
				if (keyFlg) {
					queryB.append(" AND ");
				} else {
					queryB.append(" WHERE ");
					keyFlg = true;
				}
				queryB.append("contents_status_cd = " + contents_status_cd);
				++andFlg;
			}
						
			if(!(contents_created_start_at == null || "".equals(contents_created_start_at) || contents_created_start_at == null || "".equals(contents_created_end_at))) {
				if (keyFlg) {
					queryB.append(" AND ");
				} else {
					queryB.append(" WHERE ");
					keyFlg = true;
				}
				queryB.append("contents_created_at BETWEEN '" + contents_created_start_at + "' AND '" + contents_created_end_at + "'");
				++andFlg;
			}

			//	my.cnfに以下を記述し、MYSQL再起動
			//	[mysqld]
			//	# 全文検索オプション
			//	ft_min_word_len = 2
			//	innodb_ft_min_token_size = 2
			//	innodb_ft_enable_stopword = OFF
			//	全文検索をする対象にFULLTEXT INDEXを指定　※ FULLTEXT INDEXの対象カラムはTEXT型以外も可能
			//	ALTER TABLE contents_tb ADD FULLTEXT(contents_title);
			//	ALTER TABLE contents_tb ADD FULLTEXT(contents_data);
			//	SHOW INDEX FROM contents_tb;
			//	その後にデータ登録する必要あり？
			//	SELECT * FROM テーブル名 WHERE MATCH (FULLTEXTカラム名, FULLTEXTカラム名) AGAINST ('キーワード');
			
			//	https://pgcafe.moo.jp/JAVA/string/main.htm
			//	collate で照合順序をutf8_unicode_ciに指定することで、数字、アルファベット、半角カナ、全角カナ、ひらがな、カタカナもマッチするようにする。
			//	select * from テーブル名 where カラム名 collate utf8_unicode_ci like '%キーワード%';
			/* ERROR 1253: COLLATION 'utf8_unicode_ci' is not valid for CHARACTER SET 'ujis' の場合 */
			//	select * from テーブル名 where convert(カラム using utf8) collate utf8_unicode_ci like '%キーワード%';
			//	タイプ                        	英：大文字小文字 	日：全角半角	日：ひらがなカタカナ	日：濁点
			//	(encode)_general_ci	区別なし  	区別あり       	区別あり    	区別あり
			//	(encode)_unicode_ci	区別なし  	区別なし       	区別なし   	区別なし
			//	改良課題：ソート順の改善、検索評価率の工場、「キーワード%」や「%キーワード」の検索対応、検索エンジンの組み込みなど
			//	Namazu（http://www.namazu.org/）は日本語を検索できる全文検索システム
			//	Apache Lucene（http://lucene.apache.org/java/）はJavaで実装された全文検索システム
			//	Estraier（http://estraier.sourceforge.net/）は個人用途向けの全文検索システム
			//	Senna（http://qwik.jp/senna/）は組み込み型の全文検索システム
			/*
			応用&検証SQL構文
			SELECT *, 
			  (MATCH (contents_title) AGAINST ('ねこ' IN NATURAL LANGUAGE MODE WITH QUERY EXPANSION) +
			   MATCH (contents_data) AGAINST ('犬' IN NATURAL LANGUAGE MODE WITH QUERY EXPANSION)) AS relevance
			FROM contents_tb
			WHERE 
			  (contents_title COLLATE utf8_unicode_ci LIKE '%いぬ%' OR contents_data COLLATE utf8_unicode_ci LIKE '%ねこ%')
			  OR
			  (MATCH (contents_title) AGAINST ('ねこ' IN NATURAL LANGUAGE MODE WITH QUERY EXPANSION) OR MATCH (contents_data) AGAINST ('犬' IN NATURAL LANGUAGE MODE WITH QUERY EXPANSION))
			ORDER BY relevance DESC;
			*/
			/*
			SELECT *,
			    (CASE
			        WHEN contents_title COLLATE utf8_unicode_ci LIKE '%いぬ%' THEN 1
			        WHEN contents_data COLLATE utf8_unicode_ci LIKE '%ねこ%' THEN 2
			        WHEN MATCH (contents_title) AGAINST ('ねこ' IN NATURAL LANGUAGE MODE WITH QUERY EXPANSION) THEN 3
			        WHEN MATCH (contents_data) AGAINST ('犬' IN NATURAL LANGUAGE MODE WITH QUERY EXPANSION) THEN 4
			        ELSE 5
			    END) AS relevance
			FROM contents_tb
			WHERE
			    (contents_title COLLATE utf8_unicode_ci LIKE '%いぬ%' OR contents_data COLLATE utf8_unicode_ci LIKE '%ねこ%')
			    OR
			    (MATCH (contents_title) AGAINST ('ねこ' IN NATURAL LANGUAGE MODE WITH QUERY EXPANSION) AND MATCH (contents_data) AGAINST ('犬' IN NATURAL LANGUAGE MODE WITH QUERY EXPANSION))
			HAVING relevance <= 30
			ORDER BY relevance;
			*/
			/*
			SELECT *, (MATCH(contents_title) AGAINST('ねこ' IN NATURAL LANGUAGE MODE WITH QUERY EXPANSION) + MATCH(contents_data) AGAINST('いぬ' IN NATURAL LANGUAGE MODE WITH QUERY EXPANSION)) AS relevance
			FROM contents_tb
			WHERE
			    (contents_title COLLATE utf8_unicode_ci LIKE '%いぬ%' OR contents_data COLLATE utf8_unicode_ci LIKE '%ねこ%')
			    OR
			    (MATCH(contents_title) AGAINST('ねこ' IN NATURAL LANGUAGE MODE WITH QUERY EXPANSION) + MATCH(contents_data) AGAINST('いぬ' IN NATURAL LANGUAGE MODE WITH QUERY EXPANSION)) >= 0.3
			ORDER BY relevance DESC;
			*/

			if(!(contents_data == null || "".equals(contents_data))) {
				++matchFlg;
			}

			if(!(contents_title == null || "".equals(contents_title))) {
				if (matchFlg == 0) {
					if (keyFlg) {
						queryB.append(" AND ");
					} else {
						queryB.append(" WHERE ");
						keyFlg = true;
					}
					queryB.append("contents_title COLLATE utf8_unicode_ci LIKE '%" + contents_title + "%' "
							+ "UNION SELECT "
							+ "contents_id, "
							+ "contents_type_cd, "
							+ "contents_status_cd, "
							+ "contents_title, "
							+ "contents_data, "
							+ "contents_created_at, "
							+ "contents_updated_at, "
							+ "contents_cancelled_at, "
							+ "contents_closed_at, "
							+ "contents_updated_history "
							+ "FROM contents_tb "
							+ "WHERE MATCH (contents_title) AGAINST ('" + contents_title + "' IN NATURAL LANGUAGE MODE WITH QUERY EXPANSION)");
//					queryB.append("MATCH (contents_title) AGAINST ('" + contents_title + "' IN NATURAL LANGUAGE MODE WITH QUERY EXPANSION)");
//					queryB.append("contents_title COLLATE utf8_unicode_ci LIKE '%" + contents_title + "%'");
//					queryB.append("contents_title LIKE '%" + contents_title + "%'");
				++andFlg;
				++matchFlg;
				}
			}
			
			if(!(contents_data == null || "".equals(contents_data))) {
				if (matchFlg == 0) {
					if (keyFlg) {
						queryB.append(" AND ");
					} else {
						queryB.append(" WHERE ");
//					queryB.append(" MATCH ");
					queryB.append("contents_data COLLATE utf8_unicode_ci LIKE '%" + contents_data + "%' "
							+ "UNION SELECT "
							+ "contents_id, "
							+ "contents_type_cd, "
							+ "contents_status_cd, "
							+ "contents_title, "
							+ "contents_data, "
							+ "contents_created_at, "
							+ "contents_updated_at, "
							+ "contents_cancelled_at, "
							+ "contents_closed_at, "
							+ "contents_updated_history "
							+ "FROM contents_tb "
							+ "WHERE MATCH (contents_data) AGAINST ('" + contents_data + "' IN NATURAL LANGUAGE MODE WITH QUERY EXPANSION)");
//					queryB.append("MATCH (contents_data) AGAINST ('" + contents_data + "' IN NATURAL LANGUAGE MODE WITH QUERY EXPANSION)");
//					queryB.append("contents_data COLLATE utf8_unicode_ci LIKE '%" + contents_data + "%'");
//					queryB.append("contents_data LIKE '%" + contents_data + "%'");
					}
				} else if (matchFlg == 1) {
					if (keyFlg) {
						queryB.append(" AND ");
					} else {
						queryB.append(" WHERE ");
						queryB.append("contents_title COLLATE utf8_unicode_ci LIKE '%" + contents_title + "%' "
							+ "AND"
							+ " contents_data COLLATE utf8_unicode_ci LIKE '%" + contents_data + "%' "
							+ "UNION SELECT "
							+ "contents_id, "
							+ "contents_type_cd, "
							+ "contents_status_cd, "
							+ "contents_title, "
							+ "contents_data, "
							+ "contents_created_at, "
							+ "contents_updated_at, "
							+ "contents_cancelled_at, "
							+ "contents_closed_at, "
							+ "contents_updated_history "
							+ "FROM contents_tb WHERE "
							+ "MATCH (contents_title) AGAINST ('" + contents_title + "' IN NATURAL LANGUAGE MODE WITH QUERY EXPANSION) "
							+ "AND "
							+ "MATCH (contents_data) AGAINST ('" + contents_data + "' IN NATURAL LANGUAGE MODE WITH QUERY EXPANSION)");
					}
				}
//				++andFlg;
//				keyFlg = true;
			}

			System.out.println(queryB);
			queryA = queryB.toString();
			ps = con.prepareStatement(queryA);
			rs = ps.executeQuery();
			System.out.println("[ContentUtilsDAO][SearchContentsDTO][rs:" + rs + " ]");
			
			while (rs.next()) {
				int rs_contents_id = rs.getInt("contents_id");
				int rs_contents_type_cd = rs.getInt("contents_type_cd");
				int rs_contents_status_cd = rs.getInt("contents_status_cd");
				String rs_contents_title = rs.getString("contents_title");
				String rs_contents_data = rs.getString("contents_data");
				SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm XXX");
				Date customDate = new Date(100, 0, 1, 0, 0); // 2022年1月15日 14:30を表すDateオブジェクト
				String rs_contents_created_at = dateFormat.format(customDate);
				String rs_contents_updated_at = dateFormat.format(customDate);
				String rs_contents_cancelled_at = dateFormat.format(customDate);
				String rs_contents_closed_at = dateFormat.format(customDate);
				String rs_contents_updated_history = rs.getString("contents_updated_history");
				
				if(!(rs.getTimestamp("contents_created_at") == null || "".equals(rs.getTimestamp("contents_created_at")))) {
					rs_contents_created_at = dateFormat.format(rs.getTimestamp("contents_created_at"));
				}
				if(!(rs.getTimestamp("contents_updated_at") == null || "".equals(rs.getTimestamp("contents_updated_at")))) {
					rs_contents_updated_at = dateFormat.format(rs.getTimestamp("contents_updated_at"));
				}
				if(!(rs.getTimestamp("contents_cancelled_at") == null || "".equals(rs.getTimestamp("contents_cancelled_at")))) {
					rs_contents_cancelled_at = dateFormat.format(rs.getTimestamp("contents_cancelled_at"));
				}
				if(!(rs.getTimestamp("contents_closed_at") == null || "".equals(rs.getTimestamp("contents_closed_at")))) {
					rs_contents_closed_at = dateFormat.format(rs.getTimestamp("contents_closed_at"));
				}
				Contents content = new Contents(rs_contents_id, rs_contents_type_cd, rs_contents_status_cd, rs_contents_title, rs_contents_data, rs_contents_created_at, rs_contents_updated_at, rs_contents_cancelled_at, rs_contents_closed_at, rs_contents_updated_history );
				SerchDataList.add(content);
				
				System.out.println("[ContentUtilsDAO][SearchContentsDTO][rs_contents_id:: " + rs_contents_id + "]");
				System.out.println("[ContentUtilsDAO][SearchContentsDTO][rs_contents_type_cd:: " + rs_contents_type_cd + "]");
				System.out.println("[ContentUtilsDAO][SearchContentsDTO][rs_contents_status_cd:: " + rs_contents_status_cd + "]");
				System.out.println("[ContentUtilsDAO][SearchContentsDTO][rs_contents_title:: " + rs_contents_title + "]");
				System.out.println("[ContentUtilsDAO][SearchContentsDTO][rs_contents_data:: " + rs_contents_data + "]");
				System.out.println("[ContentUtilsDAO][SearchContentsDTO][rs_contents_created_at:: " + rs_contents_created_at + "]");
				System.out.println("[ContentUtilsDAO][SearchContentsDTO][rs_contents_updated_at:: " + rs_contents_updated_at + "]");
				System.out.println("[ContentUtilsDAO][SearchContentsDTO][rs_contents_cancelled_at:: " + rs_contents_cancelled_at + "]");
				System.out.println("[ContentUtilsDAO][SearchContentsDTO][rs_contents_closed_at:: " + rs_contents_closed_at + "]");
				System.out.println("[ContentUtilsDAO][SearchContentsDTO][rs_contents_updated_history:: " + rs_contents_updated_history + "]");
			}
			/*			
						queryA = ("SELECT FROM contents_tb ( "
								+ "contents_id, "
								+ "contents_type_cd, "
								+ "contents_status_cd, "
								+ "contents_title, "
								+ "contents_data, "
								+ "contents_created_at, "
								+ "contents_closed_at "
								+ ") WHERE ( "
								+ "contents_id, "
								+ "contents_type_cd, "
								+ "contents_status_cd, "
								+ "contents_title, "
								+ "contents_data, "
								+ "contents_created_at, "
								+ "contents_closed_at" 
								+ "= (?,?,?,?,?,?,?");
						ps.setInt(1, contents_id);
						ps.setInt(2, contents_type_cd);
						ps.setInt(3, contents_status_cd);
						ps.setString(4, contents_title);
						ps.setTimestamp(6, new Timestamp(System.currentTimeMillis()));
						ps.executeQuery(queryA);
						
			*/			
		} catch (NamingException | SQLException e) {
			e.printStackTrace();
		} finally {
			this.disconnect();
		}
		return SerchDataList;
	}
	
	//	contents_tb用	コンテンツ登録作成予定
	public void insertData() {
	}
	
	//	contents_tb用	コンテンツ一括登録作成予定
	public void insertBulkData () {
	}

	//	media_tb、contents_status_mtb、contents_type_mtb、contents_category_mtb、media_type_mtb　など作成予定

}
