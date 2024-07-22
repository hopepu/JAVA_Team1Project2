package com.show.SV;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.Scanner;

import com.show.DTO.MemberDTO;
import com.show.exception.NoExistException;
import com.show.DAO.MemberKDAO;

public class MyPageSV {

	/* 메서드-회원정보보기 */
	public void myInfo(MemberDTO loginState) {
		// 회원정보 출력
		System.out.println("-----------------------------");
		System.out.println("성명 : " + loginState.getName());
		System.out.println("닉네임 : " + loginState.getNickName());
		System.out.println("아이디 : " + loginState.getId());
		String star = printStar(loginState);
		System.out.println("패스워드 : " + star); // 패스워드 글자수 만큼 별개수 출력
		System.out.println("이메일 : " + loginState.getNickName());
		System.out.println("-----------------------------");

	}// --myInfo()

	/* 메서드-회원정보수정 */
	public static void modify(MemberDTO loginState, Scanner s, Scanner sL, Connection conn) {
		MyPageSV myPageSV = new MyPageSV();
		myPageSV.myInfo(loginState);
		boolean run = true;
		MemberDTO modAccount = new MemberDTO();// 수정정보 저장용 빈객체 생성
		modAccount = loginState;// 받은 현재의 로그인 정보를 넣어 수정되지 않을 정보 맞춰줌
		MemberKDAO memberKDAO = new MemberKDAO();//db보낼 객체 생성		
		while (run) {
			System.out.println("수정할 항목의 번호를 입력하세요.");
			System.out.println("1.닉네임 | 2.이메일 | 3.휴대폰번호 | 4.저장하기 | 5.취소  ");
			int selInt = s.nextInt();
			switch (selInt) {
			case 1:
				boolean chrun=true;
				while (chrun) {
					System.out.println("수정할 닉네임을 입력하세요.");
					System.out.print(">>>");
					modAccount.setNickName(s.next());
					modAccount=memberKDAO.checkNickName(conn, modAccount); //UNIQUE 제약조건 검사를 위해 db에 보내고 결과를 다시 받음
					if(modAccount.isUsability()) {//동일닉네임이 없다면
						System.out.println("사용가능한 닉네임입니다.");
						chrun=false;
					}else {
						System.out.println("다른 사용자가 사용중인 닉네임입니다.\n다른 닉네임을 입력해 주세요.");
					}
				}//--while()
				break;
			case 2:
				System.out.println("수정할 이메일을 입력하세요.");
				System.out.print(">>>");
				String email = s.next();
				modAccount.setMail(email);
				break;
			case 3:
				System.out.println("수정할 휴대폰번호를 입력하세요.");
				System.out.print(">>>");
				String pno = s.next();
				modAccount.setpNo(pno);
				break;
			case 4:				
				//변경 정보를 담은 객체를 db로 update 작업하도록 보낸다.						
				try {
					int result = memberKDAO.updateUser(conn, modAccount);
					if(result==0) {
						throw new NoExistException("회원정보가 확인되지 않습니다.");
					}else {
						System.out.println(modAccount.getNickName());
					}
				} catch (NoExistException e) {
					String message = e.getMessage();
					System.out.println(message);
					// e.printStackTrace();
				}
				break;
			case 5:
				run = false;
				break;
			default:
				System.out.println("1~5값만 입력하세요.");
			}// --switch
		} // --while()
	}// --modify()

	/* 메서드-회원탈퇴 */
	public static MemberDTO delete(Scanner s, MemberDTO loginState, Connection conn) {
		boolean run = true;
		while (run) {
			System.out.println("회원을 탈퇴하시겠습니까? \n모든 서비스에 대한 권리를 상실하실 수 있습니다.");
			System.out.print("No:1 / Yes:2 >>>");
			int selInt = s.nextInt();
			switch (selInt) {
			case 1:
				System.out.println("감사합니다. 서비스를 계속 즐겨보세요.");
				run = false;
				break;
			case 2: // 회원정보 재확인 후 탈퇴 진행
				MemberDTO delMember = new MemberDTO(); //삭제정보 전송용 객체 생성
				MemberKDAO memberKDAO = new MemberKDAO(); //db 전송용 객체 생성
				// 회원정보 재확인
				System.out.println("회원정보를 재확인합니다.");
				System.out.println("아이디를 입력하세요.");
				System.out.print(">>>");
				delMember.setId(s.next());
				System.out.println("패스워드를 입력하세요.");
				System.out.print(">>>");
				delMember.setPw(s.next());
				try {
					if (loginState.getId().equals(delMember.getId()) && loginState.getPw().equals(delMember.getPw())) {// 로그인 정보와 재입력내용이 맞으면
						int result = 0; //db작업 결과 담을 변수
						//db로 탈퇴 계정을 보낸다.
						result = memberKDAO.delete(conn, delMember);
						if(result==1) {
							System.out.println(delMember.getName() + "님의 회원탈퇴가 완료되었습니다. 안녕히 가세요.");
							MemberDTO guest = new MemberDTO();//게스트용 객체 생성
							loginState = guest;
							run = false;
							break;}
					}else {
						throw new NoExistException("회원정보를 확인해 주세요.");
					}
				} catch (Exception e) {
					System.out.println(e.getMessage());
					//e.printStackTrace();
				}
				break;
			default:
				System.out.println("1~2값만 입력하세요.");
			}// --switch()

		} // --while()
		return loginState;

	}// --delete()

	/* 공통메서드-패스워드 글자수만큼 별찍기 */
	public static String printStar(MemberDTO loginState) {
		String star = "*"; // 별 넣을 빈 문자열 변수
		for (int i = 0; i < loginState.getPw().length() - 1; i++) {// 글자수 만큼 돌면서
			star += "*"; // star에 별 누적
		}
		return star;
	}// --printStar()

}// --class()