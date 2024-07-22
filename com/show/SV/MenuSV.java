package com.show.SV;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.Scanner;

import com.show.DTO.MemberDTO;
import com.show.DTO.ReviewDTO;
import com.show.DTO.ShowDTO;


public class MenuSV { // 모든 메뉴 모음
	/*1. 메인메뉴 */
	
	public static void mainMenu(MemberDTO loginState, Scanner s, Scanner sL, Connection connection) {
		System.out.println("<오잼>(가제 : 오늘은 무엇이 재밌을까?)에 오신것을 환영합니다.");
		
		boolean run = true;
		while (run) {
			System.out.println("아래의 메뉴에서 골라주세요.");
			System.out.println("1. 쇼 정보찾기 | 2. 로그인 | 3.회원정보찾기 | 4.exit");
			System.out.print(">>>");
			int select = s.nextInt();

			switch (select) {
			case 1:
				ShowSV.showMenu(loginState, s, sL, connection);
				break;
			case 2:
				loginMenu(loginState, s, sL, connection);
				break;
			case 3:
				findInfoeMenu(loginState, s, sL, connection);
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
	public static void userMainMenu( MemberDTO loginState, Scanner s, Scanner sL, Connection connection) {
		System.out.println("<오잼>(가제 : 오늘은 무엇이 재밌을까?)에 오신것을 환영합니다.");
		boolean run = true;
		while (run) {
			System.out.println("아래의 메뉴에서 골라주세요.");
			System.out.println("1. 쇼 정보찾기 | 2. 마이페이지 | 3. 로그아웃");
			System.out.print(">>>");
			int select = s.nextInt();

			switch (select) {
			case 1:
				ShowSV showSV;
				//showSV.showMenu(loginState, s, sL, connection);
				break;
			case 2:
				myPageMenu(loginState, s, sL, connection);
				if(!loginState.isLoginStatus()) {//로그아웃값을 리턴받으면
					mainMenu(loginState, s, sL, connection);
					run=false;
				}//메인메뉴로 복귀
				break;
			case 3:
				loginState.setLoginStatus(false);
				mainMenu(loginState, s, sL, connection);			
				run=false;
				break;
			default:
				System.out.println("1~4값 중 골라주세요");
			}//--switch()
		} // --while()
	}// --userMainMenu()
	
	/*2-1. 로그인 메뉴 */
	public static void loginMenu(MemberDTO loginState,Scanner s, Scanner sL,  Connection connection) {
		boolean run = true;
		while(run) {
			System.out.println("1. 로그인 | 2. 회원가입 | 3. exit  ");
			System.out.print(">>>");
			int select = s.nextInt();
			switch(select) {
			case 1:
				loginState = LoginSV.login(loginState, s, sL, connection);
				if(loginState.isLoginStatus()) {//로그인 성공했다면
					userMainMenu(loginState, s, sL, connection);
				}//user 메뉴로 전송
				break;
			case 2:
				LoginSV.register( s, sL, loginState, connection);
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
	public static void myPageMenu(MemberDTO loginState, Scanner sL, Scanner s, Connection connection) {
		boolean run = true;
		while (run) {
			System.out.println("1.회원정보 변경 | 2.회원탈퇴 | 3.로그아웃 | 4.닫기 ");
			System.out.print(">>>");
			int selInt = s.nextInt();
			switch (selInt) {
			case 1:
				MyPageSV.modify(loginState, s, s, connection);
				break;
			case 2:
				try {
					MyPageSV.delete(s, loginState, connection);
				} catch (Exception e) {
					String message = e.getMessage();
					System.out.println(message);
					// e.printStackTrace();
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
	public static void findInfoeMenu(MemberDTO loginState, Scanner s, Scanner sL, Connection connection) {
		boolean run=true;
		while(run) {
			System.out.println("1.아이디찾기 | 2.비밀번호찾기 | 3.닫기");
			FindSV findSV = new FindSV();
			int selInt = s.nextInt();
			switch(selInt) {
			case 1:
				try {
					
					findSV.idFind(loginState, s, sL, connection); 
				} catch (Exception e) {
					String message = e.getMessage();
					System.out.println(message);
					//e.printStackTrace();
				}
				break;
			case 2:
				try {
					System.out.println("비밀번호 찾기 메서드에 진입합니다.");
					//FindSV.pwFind(loginState, s, sL, connection);
				} catch (Exception e) {
					String message = e.getMessage();
					System.out.println(message);
					//e.printStackTrace();
				}
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
