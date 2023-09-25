package model.lm;

import java.sql.Connection;
//	import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public abstract class mariaDAO {
	//	DB接続用フィールド変数
	Connection con = null;
	PreparedStatement ps = null;
	ResultSet rs = null;
	String queryA = null;
	//	StringBuilder queryB = new StringBuilder();
	
	//	*Statement: SQL文を受け取り解析を行いすぐに実行
	//	*PreparedStatement: Statementと同様にSQL文をデータベースへ送信するための準備を行う機能
	//	実行するSQL文を先に解析し、処理速度の向上を補う機能。
	
	//	MariaDBドライバ未使用時
	//	final String DRIVER_PATH = "jdbc:oracle:thin:@127.0.0.1:1521:cm_db";	//	接続パス
	//	final String DRIVER_NAME = "oracle.jdbc.driver.OracleDriver";
	//	final String ID = "root";	//	MYSQL ログインID
	//	final String PW = "root";	//	MYSQL ログインパスワード
	
	//	DB接続
	protected void connect() throws NamingException, SQLException {
		Context context = new InitialContext();
		DataSource ds = (DataSource)context.lookup("java:comp/env/mariadb");
		this.con = ds.getConnection();
		//	念のために文字コード指定
//		queryA = "SET NAMES utf8"; 
//		ps = con.prepareStatement(queryA);
//		rs = ps.executeQuery();
//		System.out.println("[mariaDAO][connect][CONNECTED][rs:" + rs + " ]");
		System.out.println("[mariaDAO][connect][CONNECTED]");
	}
	
	//	DB切断
	protected void disconnect() {
		try {
			if( rs != null ) {
				rs.close();
			}
			if( ps != null ) {
				ps.close();
			}
			if( con != null) {
				con.close();
			}
			System.out.println("[mariaDAO][connect][DISCONNECTED]");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	//	DB接続チェック
	protected void checkConnection() {
		try {
			this.connect();
			System.out.println("[mariaDAO][connect][DB CONNECTION SUCCESSFUL]");
		} catch (NamingException | SQLException e) {
			e.printStackTrace();
		} this.disconnect();
	}
	
	//	DB接続（MariaDBドライバ未使用）
	/*	
	protected void connect_() throws NamingException, SQLException {
		try{
			//	JDBCドライバ読込
			Class.forName(DRIVER_NAME);
			//	DBコネクション作成
			this.con = DriverManager.getConnection(DRIVER_PATH, ID, PW);
			System.out.println("[CONNECTED]");
		//		//	SQL文定義例
		//		String sql = "SELECT * FROM users_tb WHERE no >= ?";
		//		//	実行SQL文のパラメータ設定
		//		ps = con.prepareStatement(sql);
		//		ps.setString(1, "2");
		//		//SELECTを実行する
		//		rs = ps.executeQuery();
		}catch(ClassNotFoundException e){
			System.out.println("[CANNOT FIND JDBC DRIVER");
			e.printStackTrace();
		//	}catch(SQLException e) {
		//		System.out.println("[CONNECTION FAILD]");
		//		e.printStackTrace();
		}
	}
	 */
}
