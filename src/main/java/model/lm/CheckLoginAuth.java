package model.lm;

import model.vm.Users;

public class CheckLoginAuth {
	private int user_id;
	private String user_name;
	private String user_pass;
	private int user_fg;
	private int user_status_cd;

	public int getUser_id() { return user_id; }
	public void setUser_id(int user_id) { this.user_id = user_id; }
	public String getUser_name() { return user_name; }
	public void setUser_name(String user_name) {this.user_name = user_name; }
	public String getUser_pass() { return user_pass; }
	public void setUser_pass(String user_pass) { this.user_pass = user_pass; }
	public int getUser_fg() { return user_fg; }
	public void setUser_fg(int user_fg) { this.user_fg = user_fg; }
	public int getUser_status_cd() { return user_status_cd; }
	public void setUser_status_cd(int user_status_cd) { this.user_status_cd = user_status_cd; }

	public CheckLoginAuth(String user_name, String user_pass) {
		this.user_name = user_name;
		this.user_pass = user_pass;
	}
	
	public boolean isLogin(Users user) {
		if( user.getUser_Pass() != null || user.getUser_Status_Cd() == 11 ) {
			return true;
		}
		return false;
	}
}

