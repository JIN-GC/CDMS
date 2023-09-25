package model.lm;

import java.sql.SQLException;
import java.sql.Timestamp;

import javax.naming.NamingException;

import model.vm.Users;

public class UserUtilsDAO extends mariaDAO {

	//	users_tb用	ユーザーアカウント検索・照合
	public Users findUser(Users user) {
		try {
			this.connect();
			queryA = ("SELECT user_id, user_name, user_pass, user_fg, user_status_cd, user_roll_cd FROM users_tb WHERE (user_name, user_pass) = (?, ?)");
			ps = con.prepareStatement(queryA);
			ps.setString(1, user.getUser_Name());
			ps.setString(2, user.getUser_Pass());
//			ps.execute();
			rs = ps.executeQuery();
//			System.out.println("[UserUtilsDAO][findUser][user.getUser_Name:" + user.getUser_Name() + ", user.getUser_Pass:" + user.getUser_Pass() + " ]");
//			System.out.println("[UserUtilsDAO][findUser][queryA:" + queryA + " ]");
//			System.out.println("[UserUtilsDAO][findUser][ps.getResultSet():" + ps.getResultSet() + " ]");
			System.out.println("[UserUtilsDAO][findUser][rs:" + rs + " ]");
			
			int user_status_cd;
			if(rs == null ) {
				if (user.getUser_Name() != null) {
					user_status_cd =  user.getUser_Status_Cd();
					// getUser_Name　から　user_status_cd　を取得し、ログイン試行回数を判定
					if ( user_status_cd <= 1) {
						user_status_cd = 101;	//	ログイン失敗１回目
					} else if ( user_status_cd == 101 ) {
						user_status_cd = 102;	//	ログイン失敗2回目
					} else if ( user_status_cd == 101 ) {
						user_status_cd = 103;	//	アカウントロック中
					}
					// DB上の　user_status_cd を更新
					setUserStatus(user);
					// user情報クリア処理（呼び出し元にNULLを返す）
					user = null;
					//	System.out.println("[UserUtilsDAO][checkLoginDTO][user = null]" + " ]");
				}
			} else if(rs.next()) {
				int user_id = rs.getInt("user_id");
				String user_name = rs.getString("user_name");
				String user_pass = rs.getString("user_pass");
				int user_fg = rs.getInt("user_fg");
				int user_roll_cd = rs.getInt("user_roll_cd");
				//	int user_status_cd = rs.getInt("user_status_cd");
				user_status_cd = 11;	//	ログイン成功
				String redirect_page = user.getRedirect_Page();
				user = new Users(user_id, user_name, user_pass, user_fg, user_roll_cd, user_status_cd, redirect_page);

				// DB上の　user_status_cd を更新
				setUserStatus(user);
				System.out.println("[UserUtilsDAO][checkLoginDTO][rs is not null][user_id:" + user_id + " ,user_status_cd: " + user_status_cd + " ,user_fg: " + user_fg + " ,user_roll_cd: " + user_roll_cd + " ,user_status_cd: " + user_status_cd + " ,redirect_page: " + redirect_page + "]");
			}
		} catch (NamingException | SQLException e) {
			e.printStackTrace();
		} finally {
			this.disconnect();
		}
		return user;
	}

	//	users_tb用	ユーザーアカウント登録	（修正予定）
	public void registUser(Users user) {
		try {
			this.connect();
			queryA = ("INSERT INTO users_tb　(user_name, user_pass, user_fg, user_roll_cd, user_status_cd, created_user_at, updated_user_history_at) VALUES (?,?,?,?,?,?,?)");
			ps.executeQuery(queryA);
			ps = con.prepareStatement(queryA);
			ps.setString(1, user.getUser_Name());
			ps.setString(2, user.getUser_Pass());
			ps.setInt(3, user.getUser_Fg());
			ps.setInt(4, user.getUser_Roll_Cd());
			//	ps.setInt(5, user.getUser_Status_Cd());
			ps.setInt(5, 1);	//	新規ユーザー用デフォルト値（Ａｃｔｉｖｅ）
			ps.setTimestamp(6, new Timestamp(System.currentTimeMillis()));
			ps.setTimestamp(7, new Timestamp(System.currentTimeMillis()));
			rs = ps.executeQuery();
		} catch (NamingException | SQLException e) {
			e.printStackTrace();
		} finally {
			this.disconnect();
		}
	}

	//	users_tb用	ユーザーアカウント更新	（修正予定）
	public void updateUser(Users user) {
		try {
			this.connect();
			queryA = ("UPDATE users_tb　(user_name, user_pass, user_fg, user_roll_cd, user_status_cd, updated_user_at, updated_user_history_at) VALUES (?,?,?,?,?,?,? + '|' + updated_user_history_at )");
			ps.executeQuery(queryA);
			ps = con.prepareStatement(queryA);
			ps.setString(1, user.getUser_Name());
			ps.setString(2, user.getUser_Pass());
			ps.setInt(3, user.getUser_Fg());
			ps.setInt(4, user.getUser_Roll_Cd());
			ps.setInt(5, user.getUser_Status_Cd());
			ps.setTimestamp(6, new Timestamp(System.currentTimeMillis()));
			ps.setTimestamp(7, new Timestamp(System.currentTimeMillis()));
			rs = ps.executeQuery();
		} catch (NamingException | SQLException e) {
			e.printStackTrace();
		} finally {
			this.disconnect();
		}
	}

	//	users_tb用	ユーザーアカウント削除	（修正予定）
	public void deleteUser(Users user) {
		try {
			this.connect();
			queryA = ("UPDATE users_tb　(user_status_cd, deleted_user_at, updated_user_history_at) VALUES (?,?,? + '|' + updated_user_history_at)");
			ps.executeQuery(queryA);
			ps = con.prepareStatement(queryA);
			//	ps.setInt(1, user.getUser_Status_Cd());
			ps.setInt(1, 0);	//	ユーザーアカウント無効化値（Deaｃｔｉｖｅ）
			ps.setTimestamp(2, new Timestamp(System.currentTimeMillis()));
			ps.setTimestamp(3, new Timestamp(System.currentTimeMillis()));
			rs = ps.executeQuery();
		} catch (NamingException | SQLException e) {
			e.printStackTrace();
		} finally {
			this.disconnect();
		}	
	}
	
	//	users_tb用	ユーザーアカウント情報確認	（修正予定）
	//	追記予定変数
	//	created_user_at	//	ユーザーアカウント作成日時
	//	updated_user_at	//	ユーザーアカウント更新日時
	//	deleted_user_at	//	ユーザーアカウント削除日時
	//	updated_user_history_at	//	ユーザーアカウント更新履歴
	public Users checkUser() {
		Users user = new Users();
		try {
			this.connect();
			queryA = ("SELECT user_id, user_name, user_fg, user_status_cd, user_roll_cd FROM users_tb WHERE (?, ?)");
			rs = ps.executeQuery();
			
			int user_id = rs.getInt("user_id");
			String user_name = rs.getString("user_name");
			String user_pass = rs.getString("user_pass");
			int user_fg = rs.getInt("user_fg");
			int user_status_cd = rs.getInt("user_status_cd");
			int user_roll_cd = rs.getInt("user_roll_cd");
			String redirect_page = rs.getString("redirect_page");	//	引数調整用
			
			user = new Users(user_id, user_name, user_pass, user_fg, user_status_cd, user_roll_cd, redirect_page);
			
		} catch (NamingException | SQLException e) {
			e.printStackTrace();
		} finally {
			this.disconnect();
		}
		return user;
	}
	
	//	users_tb用	user_status　のみ更新
	public Users setUserStatus(Users user) {
		try {
			this.connect();
			queryA = ("update users_tb user_status = ? WHERE user_name = ?");
			ps = con.prepareStatement(queryA);
			ps.setInt(1, user.getUser_Status_Cd());
			ps.setString(2, user.getUser_Name());
			rs = ps.executeQuery();
		} catch (NamingException | SQLException e) {
			e.printStackTrace();
		} finally {
			this.disconnect();
		}
		return user;
	}


	//	user_status_mtb用	ユーザーステータス情報取得
	//	user_status_mtb用DAOに転記予定 ＆＆　UsersのBeansで暫定処置中）
	public String getUserStatusInfo(int user_status_cd) {
		String user_status = null;
		try {
			this.connect();
			queryA = ("SELECT user_status FROM user_status_mtb WHERE user_status_cd = ?");
			ps = con.prepareStatement(queryA);
			ps.setInt(1, user_status_cd);
			rs = ps.executeQuery();
			user_status = rs.getString("user_status");
		} catch (NamingException | SQLException e) {
			e.printStackTrace();
		} finally {
			this.disconnect();
		}
		return user_status;
	}
	
	//	user_fg_mtb用	ユーザーフラグ（階級）情報取得
	//	user_ｆｇ_mtb用DAOに転記予定 ＆＆　UsersのBeansで暫定処置中）
	public String getUserFgTypeInfo(int user_fg) {
		String user_fg_type = null;
		try {
			this.connect();
			queryA = ("SELECT user_fg_type FROM user_fg_mtb WHERE user_fg = ?");
			ps = con.prepareStatement(queryA);
			ps.setInt(1, user_fg);
			rs = ps.executeQuery();
			user_fg_type = rs.getString("user_fg_type");
		} catch (NamingException | SQLException e) {
			e.printStackTrace();
		} finally {
			this.disconnect();
		}
		return user_fg_type;
	}

	//	user_roll_mtb用	ユーザーロール（役職種別）情報取得
	//	user_roll_mtb用DAOに転記予定 ＆＆　UsersのBeansで暫定処置中）
	public String getUserRollInfo(int user_roll_cd) {
		String user_roll = null;
		try {
			this.connect();
			queryA = ("SELECT user_roll FROM user_roll_mtb WHERE user_roll_cd = ?");
			ps = con.prepareStatement(queryA);
			ps.setInt(1, user_roll_cd);
			rs = ps.executeQuery();
			user_roll = rs.getString("user_roll");
		} catch (NamingException | SQLException e) {
			e.printStackTrace();
		} finally {
			this.disconnect();
		}
		return user_roll;
	}
	
}
