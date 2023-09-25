package model.vm;

import java.text.SimpleDateFormat;
import java.util.Date;

import model.lm.UserUtilsDAO;

public class Users {
	
	private int user_id = 0;
	private String user_name;
	private String user_pass;
	private int user_fg = 0;
	private String user_fg_type;
	private int user_roll_cd = 0;
	private String user_roll;
	private int user_status_cd = 0;
	private String user_status;
	private String redirect_page;
	SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm");	//	("yyyy/MM/dd HH:mm XXX")
	Date customDate = new Date(100, 0, 1, 0, 0); // 2022年1月15日 14:30を表すDateオブジェクト
	private String created_user_at = dateFormat.format(customDate);	//	ユーザーアカウント作成日時
	private String updated_user_at = dateFormat.format(customDate);	//	ユーザーアカウント更新日時
	private String deleted_user_at = dateFormat.format(customDate);	//	ユーザーアカウント削除日時
	private String updated_user_history_at;	//	ユーザーアカウント更新履歴
	
	public Users() {
	}
	
	public Users(String user_name, String user_pass, String redirect_page) {
		this.user_name = user_name;
		this.user_pass = user_pass;
		this.redirect_page = redirect_page;
	}
	
	public Users(int user_id, String user_name, String user_pass, int user_fg, int user_roll_cd, int user_status_cd, String redirect_page) {
		this.user_id = user_id;
		this.user_name = user_name;
		this.user_pass = user_pass;
		this.user_fg = user_fg;
		this.user_roll_cd = user_roll_cd;
		this.user_status_cd = user_status_cd;
		this.redirect_page = redirect_page;
//		this(user_id, user_name, user_pass, user_fg, user_roll_cd, user_status_cd, redirect_page);

	}
	
	public int getUser_Id() {
		return this.user_id;
	}

	public void setUser_Id(int user_id) {
		this.user_id = user_id;
	}

	public String getUser_Name() {
		return this.user_name;
	}

	public void setUser_Name(String user_name) {
		this.user_name = user_name;
	}

	public String getUser_Pass() {
		return this.user_pass;
	}

	public void setUser_Pass(String user_pass) {
		this.user_pass = user_pass;
	}

	public int getUser_Fg() {
		return this.user_fg;
	}

	public void setUser_Fg(int user_fg) {
		this.user_fg = user_fg;
	}

	public String getUser_Fg_Type(int user_fg) {
		switch(user_fg) {
			case 0:
				this.user_fg_type = "New";
				break;
			case 1:
				this.user_fg_type = "General";
				break;
			case 2:
				this.user_fg_type = "Supplier";
				break;
			case 3:
				this.user_fg_type = "Vendor";
				break;
			case 4:
				this.user_fg_type = "3rd Party Vendor";
				break;
			case 5:
				this.user_fg_type = "VIP";
				break;
			default:
				this.user_status = "Unknown";
				break;
		}
		return this.user_fg_type;
	} 
	
	public int getUser_Roll_Cd() {
		return this.user_roll_cd;
	}

	public void setUser_Roll_Cd(int user_roll_cd) {
		this.user_roll_cd = user_roll_cd;
	}

	public String getUser_Roll(int user_roll_cd) {
		switch(user_roll_cd) {
			case 0:
				this.user_status = "New";
				break;
			case 1:
				this.user_status = "General";
				break;
			case 2:
				this.user_status = "Support Lv1";
				break;
			case 3:
				this.user_status = "Support Lv2";
				break;
			case 4:
				this.user_status = "Observer";
				break;
			case 5:
				this.user_status = "Admin";
				break;
			default:
				this.user_status = "Unknown";
				break;
		}
		return this.user_roll;
	} 

	public int getUser_Status_Cd() {
		return this.user_status_cd;
	}

	public void setUser_Status_Cd(int user_status_cd) {
		UserUtilsDAO uud = new UserUtilsDAO();
		this.user_status_cd = user_status_cd;
		uud.setUserStatus(this);
	}
	
	public String getUser_Status(int user_status_cd) {
		switch(user_status_cd) {
			case 0:
				this.user_status = "Deactive";
				break;
			case 1:
				this.user_status = "Active";
				break;
			case 11:
				this.user_status = "Logon";
				break;
			case 101:
				this.user_status = "1st Faild";
				break;
			case 102:
				this.user_status = "2nd Faild ";
				break;
			case 103:
				this.user_status = "Locked";	//	Rockout
				break;
			default:
				this.user_status = "Unknown";
				break;
		}
		return this.user_status;
	}
	
	public String getRedirect_Page() {
		return redirect_page;
	}

	public void setRedirect_Page(String redirect_page) {
		this.redirect_page = redirect_page;
	}

	public boolean isLogin(Users user) {
		System.out.println("[Users][isLogin]" + user.getUser_Status_Cd());
		if(user.getUser_Status_Cd() == 11) {
		//	if( user.getUser_Pass() != null || user.getUser_Status_Cd() == 11 ) {
			return true;
		}
		return false;
	}



}
