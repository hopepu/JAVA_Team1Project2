package com.show.SV;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.Scanner;

import com.show.NoExistException;
import com.show.DTO.DramaDTO;
import com.show.DTO.MemberDTO;
import com.show.DTO.ReviewDTO;
import com.show.DTO.ShowDTO;
import com.show.DTO.VarietyDTO;

public class MenuSV { // 모든 메뉴 모음
	/*1. 메인메뉴 */
	public static void mainMenu(Scanner s, Scanner sL, MemberDTO loginState, Connection connection) {
		System.out.println("<오잼>(가제 : 오늘은 무엇이 재밌을까?)에 오신것을 환영합니다.");
		boolean run = true;
		while (run) {
			System.out.println("아래의 메뉴에서 골라주세요.");
			System.out.println("1. 쇼 정보찾기 | 2. 로그인 | 3.회원정보찾기 | 4.exit");
			System.out.print(">>>");
			int select = s.nextInt();

			switch (select) {
			case 1:
				//searchMenu(loginState, s, sL, connection);
				break;
			case 2:
				loginMenu(s, sL, loginState, connection);
				break;
			case 3:
				findInfoMenu(s, loginState, connection); //sun2024.07.20 ->sL삭제, ..infoe->..info
				break;
			case 4:
				run=false;
				break;
			default:
				System.out.println("1~4값 중 골라주세요");
			}//--switch()
		} // --while()
	}// --mainMenu()
	
	/*1-2.로그인 시 메인메뉴 */
	public static void userMainMenu(Scanner s, Scanner sL, MemberDTO loginState, Connection connection) {
		System.out.println("<오잼>(가제 : 오늘은 무엇이 재밌을까?)에 오신것을 환영합니다.");
		boolean run = true;
		while (run) {
			System.out.println("아래의 메뉴에서 골라주세요.");
			System.out.println("1. 쇼 정보찾기 | 2. 마이페이지 | 3. 로그아웃");
			System.out.print(">>>");
			int select = s.nextInt();

			switch (select) {
			case 1:
				//searchMenu(loginState, s, sL, connection);
				break;
			case 2:
				myPageMenu(s, sL, loginState, connection);
				if(loginState.isLoginStatus()==false) {//로그아웃값을 리턴받으면
					mainMenu(s, sL, loginState, connection);
					run=false;
				}//메인메뉴로 복귀
				break;
			case 3:
				loginState.setLoginStatus(false);
				mainMenu(s, sL, loginState, connection);		
				run=false;
				break;
			default:
				System.out.println("1~4값 중 골라주세요");
			}//--switch()
		} // --while()
	}// --userMainMenu()
	
	/*2-1. 로그인 메뉴 */
	public static void loginMenu(Scanner s, Scanner sL, MemberDTO loginState, Connection connection) {
		boolean run = true;
		while(run) {
			System.out.println("1. 로그인 | 2. 회원가입 | 3. exit  ");
			System.out.print(">>>");
			int select = s.nextInt();
			switch(select) {
			case 1:
				loginState = LoginSV.login(loginState, s, sL, connection);
				if(loginState.isLoginStatus()) {//로그인 성공했다면
					userMainMenu(s, sL, loginState, connection);
				}//user 메뉴로 전송
				break;
			case 2:
				LoginSV.register(s, sL, loginState, connection);
				break;
			case 3:
				run=false;
				break;
			default:
				System.out.println("1~3값 중 골라주세요");			
			}//--switch()
		}// --while()		
	}// --loginMenu()
	
	/*2-2. 마이페이지 메뉴 */
	public static void myPageMenu(Scanner s, Scanner sL, MemberDTO loginState, Connection connection) { 
		MyPageSV myPageSV = new MyPageSV(); //sun2024.07.20 -메소드 static 제거하여 추가된 항목 
		boolean run = true;
		while (run) {
			System.out.println("1.회원정보 변경 | 2.회원탈퇴 | 3.로그아웃 | 4.닫기 ");
			System.out.print(">>>");
			int selInt = s.nextInt();
			switch (selInt) {
			case 1:
				loginState = myPageSV.modify(s, loginState, connection); //sun2024.07.23
				break;
			case 2:
				loginState = myPageSV.delete(s, loginState, connection); //sun2024.07.20
				if(loginState.getAuthor()==0) {//회원탈퇴로 게스트상태로 돌아왔다면
					run = false;
					return;
				}
				break;
			case 3:
				loginState.setLoginStatus(false);
				run = false;
				break;
			case 4:
				run = false;
				break;
			default:
				System.out.println("1~4값만 입력하세요.");
			}// --switch()
		} // --while()
	}// --myPageMenu()

	/* 3-1. 회원정보찾기 메누*/
	public static void findInfoMenu(Scanner s,  MemberDTO loginState, Connection connection) { //sun2024.07.20 -sL삭제 
		FindSV findSV = new FindSV(); //sun2024.07.20 -메소드 static 제거하여 추가된 항목 
		boolean run=true;
		while(run) {
			System.out.println("1.아이디찾기 | 2.비밀번호찾기 | 3.닫기");
			int selInt = s.nextInt();
			switch(selInt) {
			case 1:
				findSV.idFind(s, connection); //sun2024.07.20-try-catch를 메소드 안에서 처리 하도록 수정 
				break;
			case 2:
				findSV.pwFind(s, connection); //sun2024.07.20-try-catch를 메소드 안에서 처리 하도록 수정 
				break;
			case 3:
				run=false;
				break;
			default:
				System.out.println("1~3값만 입력하세요.");
			}//--switch()
		}//--while()
	}//find info menu close

	
	
} // --class
