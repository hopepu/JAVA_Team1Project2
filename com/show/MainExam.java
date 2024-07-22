package com.show;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Scanner;

import com.show.DTO.MemberDTO;
import com.show.SV.MenuSV;

public class MainExam {

	
	public static Scanner s = new Scanner(System.in);// 단어 입력
	public static Scanner sL = new Scanner(System.in);// 긴 문장 입력
	public static MemberDTO loginState ; //로그인 정보저장용 세션(변수)
	public static Connection connection = null;
	
	public MainExam() {
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			connection = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl", "member", "member1919");
		} catch (ClassNotFoundException e) {
			System.out.println("드라이버명 또는 ojdbc6.jar를 확인해주세요");
		} catch (SQLException e) {
			System.out.println("url, id, pw나 쿼리문이 잘못됨");
			System.exit(0);
		}

	}

	public static void main(String[] args) {
		MainExam mainExam = new MainExam();		
		loginState = new MemberDTO(); //guest 용 객체로 loginState 생성
		MenuSV.mainMenu(loginState, s, s, connection);

	}// main close

}// class close
