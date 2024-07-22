package com.show.DTO;

import java.sql.Date;

public class MemberDTO {
	// 필드 --DB와 공유되는 필드
	private int mno; //멤버번호 : db에서 시퀀스로 생성
	private String id;
	private String pw;
	private String name;
	private String nickName;
	private String birth;// 주민번호 앞자리 string으로 저장
	private String sex; // 주민번호 뒷자리 첫번째 숫자 x%2이용하여 판별
	private String pNo; // 전화번호 - 없이 저장
	private String mail;
	private Author author;
	//필드 --java에서 체크용으로 사용되는 필드
	private boolean usability;
	private boolean loginStatus;

	// 생성자
	public MemberDTO() { //기본생성자 - 게스트용 정보 자동 저장
	      this.id = "guest";
	      this.nickName = "guest";
	      this.author = Author.GUEST;
	      this.loginStatus=false;
	}

	public MemberDTO(String id, String pw) {// 로그인시 생성자
		this.id = id;
		this.pw = pw;
		this.loginStatus = false; // 로그인 검증후 성공시 변경
	}

	// 메서드
	//-----getter
	public int getMno() {
		return mno;
	}

	public String getId() {
		return id;
	}

	public String getPw() {
		return pw;
	}

	public String getName() {
		return name;
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

	public Author getAuthor() {
		return author;
	}

	public boolean isUsability() {
		return usability;
	}

	public boolean isLoginStatus() {
		return loginStatus;
	}
	
	//-----setter

	public void setMno(int mno) {
		this.mno = mno;
	}

	public void setId(String id) {
		this.id = id;
	}

	public void setPw(String pw) {
		this.pw = pw;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setNickName(String nickName) {
		this.nickName = nickName;
	}

	public void setBirth(String birth) {//주민번호를 받아 생년월일부분을 추출하여 YYYY/MM/DD형의 문자열로 자동변환		
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

	public void setAuthor(Author author) {
		this.author = author;
	}

	public void setUsability(boolean usability) {
		this.usability = usability;
	}

	public void setLoginStatus(boolean loginStatus) {
		this.loginStatus = loginStatus;
	}
	

}