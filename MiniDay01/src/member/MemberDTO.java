package member;

import java.sql.Date;

//		IDX      NOT NULL NUMBER        
//		USERID   NOT NULL VARCHAR2(50)  
//		USERPW   NOT NULL VARCHAR2(50)  
//		USERNAME NOT NULL VARCHAR2(50)  
//		NICKNAME NOT NULL VARCHAR2(100) 
//		EMAIL    NOT NULL VARCHAR2(100) 
//		GENDER   NOT NULL VARCHAR2(20)  
//		BIRTH    NOT NULL VARCHAR2(20)

public class MemberDTO {
	
	private int idx;
	private String userid, userpw;
	private String username, nickname;
	private String email, gender;
	private String birth;
	
	
	public int getIdx() {
		return idx;
	}
	public void setIdx(int idx) {
		this.idx = idx;
	}
	public String getUserid() {
		return userid;
	}
	public void setUserid(String userid) {
		this.userid = userid;
	}
	public String getUserpw() {
		return userpw;
	}
	public void setUserpw(String userpw) {
		this.userpw = userpw;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getNickname() {
		return nickname;
	}
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public String getBirth() {
		return birth;
	}
	public void setBirth(String birth) {
		this.birth = birth;
	}

	
	
	
}
