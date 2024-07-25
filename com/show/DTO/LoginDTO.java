package com.show.DTO;

public class LoginDTO {
	// 필드
	private String id;
	private String pw;
	private String nickName;
	private String birth;// 주민번호 앞자리 string으로 저장
	private String sex; // 주민번호 뒷자리 첫번째 숫자 x%2이용하여 판별
	private String pNo; // 전화번호 - 없이 저장
	private Boolean loginStatus;

	// 메서드
	public String getId() {
		return id;
	}

	public String getPw() {
		return pw;
	}

	public String getNickName() {
		return nickName;
	}

	public String getBirth() {
		return birth;
	}

	public String getSex() {
		return sex;
	}

	public String getpNo() {
		return pNo;
	}

	public String getMail() {
		return mail;
	}

	public void setId(String id) {
		this.id = id;
	}

	public void setPw(String pw) {
		this.pw = pw;
	}

	public void setNickName(String nickName) {
		this.nickName = nickName;
	}

	public void setBirth(String birth) {
		this.birth = birth;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public void setpNo(String pNo) {
		this.pNo = pNo;
	}

	public void setMail(String mail) {
		this.mail = mail;
	}

	public Boolean getLoginStatus() {
		return loginStatus;
	}

	public void setLoginStatus(Boolean loginStatus) {
		this.loginStatus = loginStatus;
	}

	private String mail;

}
