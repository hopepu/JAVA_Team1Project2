package com.show.SV;

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
	public static void mainMenu(Scanner s, Scanner sL, MemberDTO loginState, ArrayList<MemberDTO> loginDTOs,ArrayList<ReviewDTO> reviewDTOs , ArrayList<ShowDTO> showDTOs , ArrayList<DramaDTO> dramaDTOs, ArrayList<VarietyDTO> varietyDTOs) {
		System.out.println("<오잼>(가제 : 오늘은 무엇이 재밌을까?)에 오신것을 환영합니다.");
		boolean run = true;
		while (run) {
			System.out.println("아래의 메뉴에서 골라주세요.");
			System.out.println("1. 쇼 정보찾기 | 2. 로그인 | 3.회원정보찾기 | 4.exit");
			System.out.print(">>>");
			int select = s.nextInt();

			switch (select) {
			case 1:
				searchMenu(loginState, s, sL, loginDTOs, reviewDTOs, showDTOs, dramaDTOs, varietyDTOs);
				break;
			case 2:
				loginMenu(s, loginState, loginDTOs, reviewDTOs, showDTOs, dramaDTOs, varietyDTOs);
				break;
			case 3:
				findInfoeMenu(s, loginDTOs);
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
	public static void userMainMenu(Scanner s, Scanner sL, MemberDTO loginState, ArrayList<MemberDTO> loginDTOs,ArrayList<ReviewDTO> reviewDTOs , ArrayList<ShowDTO> showDTOs , ArrayList<DramaDTO> dramaDTOs, ArrayList<VarietyDTO> varietyDTOs) {
		System.out.println("<오잼>(가제 : 오늘은 무엇이 재밌을까?)에 오신것을 환영합니다.");
		boolean run = true;
		while (run) {
			System.out.println("아래의 메뉴에서 골라주세요.");
			System.out.println("1. 쇼 정보찾기 | 2. 마이페이지 | 3. 로그아웃");
			System.out.print(">>>");
			int select = s.nextInt();

			switch (select) {
			case 1:
				searchMenu(loginState, s, sL, loginDTOs, reviewDTOs, showDTOs, dramaDTOs, varietyDTOs);
				break;
			case 2:
				myPageMenu(s, loginState, loginDTOs);
				if(!loginState.getLoginStatus()) {//로그아웃값을 리턴받으면
					mainMenu(s, sL, loginState, loginDTOs, reviewDTOs, showDTOs, dramaDTOs, varietyDTOs);
					run=false;
				}//메인메뉴로 복귀
				break;
			case 3:
				loginState.setLoginStatus(false);
				mainMenu(s, sL, loginState, loginDTOs, reviewDTOs, showDTOs, dramaDTOs, varietyDTOs);			
				run=false;
				break;
			default:
				System.out.println("1~4값 중 골라주세요");
			}//--switch()
		} // --while()
	}// --userMainMenu()
	
	/*2-1. 로그인 메뉴 */
	public static void loginMenu(Scanner s,MemberDTO loginState, ArrayList<MemberDTO> loginDTOs,ArrayList<ReviewDTO> reviewDTOs , ArrayList<ShowDTO> showDTOs , ArrayList<DramaDTO> dramaDTOs, ArrayList<VarietyDTO> varietyDTOs) {
		boolean run = true;
		while(run) {
			System.out.println("1. 로그인 | 2. 회원가입 | 3. exit  ");
			System.out.print(">>>");
			int select = s.nextInt();
			switch(select) {
			case 1:
				loginState = LoginSV.login(s, loginState, loginDTOs);
				if(loginState.getLoginStatus()) {//로그인 성공했다면
					userMainMenu(s, s, loginState, loginDTOs, reviewDTOs, showDTOs, dramaDTOs, varietyDTOs);
				}//user 메뉴로 전송
				break;
			case 2:
				LoginSV.register(s, loginState, loginDTOs);
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
	public static void myPageMenu(Scanner s, MemberDTO loginState, ArrayList<MemberDTO> loginDTOs) {
		boolean run = true;
		while (run) {
			System.out.println("1.회원정보 변경 | 2.회원탈퇴 | 3.로그아웃 | 4.닫기 ");
			System.out.print(">>>");
			int selInt = s.nextInt();
			switch (selInt) {
			case 1:
				MyPageSV.modify(s, loginState, loginDTOs);
				break;
			case 2:
				try {
					MyPageSV.delete(s, loginState, loginDTOs);
				} catch (NoExistException e) {
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
	public static void findInfoeMenu(Scanner s, ArrayList<MemberDTO> loginDTOs) {
		boolean run=true;
		while(run) {
			System.out.println("1.아이디찾기 | 2.비밀번호찾기 | 3.닫기");
			int selInt = s.nextInt();
			switch(selInt) {
			case 1:
				try {
					FindSV.idFind(s, loginDTOs); 
				} catch (NoExistException e) {
					String message = e.getMessage();
					System.out.println(message);
					//e.printStackTrace();
				}
				break;
			case 2:
				try {
					FindSV.pwFind(s, loginDTOs);
				} catch (NoExistException e) {
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
	
	public static void searchMenu(MemberDTO lSt, Scanner s, Scanner sL, ArrayList<MemberDTO> loginDTOs,
			ArrayList<ReviewDTO> reviewDTOs, ArrayList<ShowDTO> showDTOs, ArrayList<DramaDTO> dramaDTOs,
			ArrayList<VarietyDTO> varietyDTOs) {

		SearchListSV showList = new SearchListSV();

		// 리스트 가진 클래스 사용할 수 있는 빈 객체 생성

		// 메뉴 만들어서
		boolean run = true;
		while (run) {
			System.out.println("============ 검색 창 ==============");
			System.out.println("1. 검색 ");
			System.out.println("2. 장르별 보기");
			System.out.println("3. OTT 시리즈별 보기");
			System.out.println("4. 메인메뉴로 돌아가기");
			int select = s.nextInt();
			switch (select) {

			case 1:
				System.out.println("검색메서드 진입합니다.");
				SearchSV.search(showDTOs, reviewDTOs, showDTOs, lSt, s, sL, dramaDTOs, varietyDTOs, showList);
				break;

			case 2:

				System.out.println("장르별메서드 진입합니다.");
				SearchSV.categorySearch(showDTOs, showList, reviewDTOs, showDTOs, lSt, s, sL, showDTOs, dramaDTOs, varietyDTOs);
				break;

			case 3:
				System.out.println("OTT 시리즈 보기");
				showList.searchProgram("Netflix", showDTOs, dramaDTOs, varietyDTOs);
				System.out.println("------------------------------");
				showList.searchProgram("Tving", showDTOs, dramaDTOs, varietyDTOs);
				System.out.println("------------------------------");
				showList.searchProgram("Tving, Netflix", showDTOs, dramaDTOs, varietyDTOs);
				break;

			case 4:
				System.out.println("메인메뉴로 돌아갑니다.");
				run = false;
				break;

			default:
				System.out.println(" 잘못 입력 하셨습니다. 1 ~ 4까지의 번호를 입력 해주세요.");

			}// 스위치 종료
		}

	}
	
	
} // --class
