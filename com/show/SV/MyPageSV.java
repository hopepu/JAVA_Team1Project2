package com.show.SV;

import java.util.ArrayList;
import java.util.Scanner;

import com.show.DTO.MemberDTO;
import com.show.NoExistException;

public class MyPageSV {

	/* 메서드-회원정보보기 */
	public static void myInfo(MemberDTO loginState) {
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
	public static void modify(Scanner s, MemberDTO loginState, ArrayList<MemberDTO> loginDTOs) {
		myInfo(loginState);
		boolean run = true;
		MemberDTO modAccount = new MemberDTO();// 수정정보 저장용 빈객체 생성
		modAccount = loginState;// 받은 현재의 로그인 정보를 넣어 수정되지 않을 정보 맞춰줌
		while (run) {
			System.out.println("수정할 항목의 번호를 입력하세요.");
			System.out.println("1.닉네임 | 2.이메일 | 3.휴대폰번호 | 4.저장하기 | 5.취소  ");
			int selInt = s.nextInt();
			switch (selInt) {
			case 1:
				System.out.println("수정할 닉네임을 입력하세요.");
				System.out.print(">>>");
				String nickName = s.next();
				modAccount.setNickName(nickName);
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
				try {
					int i = FindSV.findIDIndex(loginState.getId(), loginDTOs);
					loginDTOs.add(i, modAccount);
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
				System.out.println("1~3값만 입력하세요.");
			}// --switch

		} // --while()
	}// --modify()

	/* 메서드-회원탈퇴 */
	public static void delete(Scanner s, MemberDTO loginState, ArrayList<MemberDTO> loginDTOs) throws NoExistException {
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
				// MemberDTO delMember = new MemberDTO(); //삭제비교용 객체 생성
				// 회원정보 재확인
				System.out.println("회원정보를 재확인합니다.");
				System.out.println("아이디를 입력하세요.");
				System.out.print(">>>");
				String id = s.next();
				System.out.println("패스워드를 입력하세요.");
				System.out.print(">>>");
				String pw = s.next();
				for (MemberDTO delMember : loginDTOs) {
					if (loginState.getId().equals(id)) {// 재입력내용이 맞으면
						if (loginState.getPw().equals(pw)) {
							int i = FindSV.findIDIndex(id, loginDTOs); // id로 해당 인덱스 번호 받아오기
							delMember = loginDTOs.get(i);// 해당 인덱스 내용 delMember에 넣기(**추후 삭제인원 관리 시 이 객체 이동)
							loginDTOs.remove(i); // 찾은 인덱스 지우기
							System.out.println(delMember.getName() + "님의 회원탈퇴가 완료되었습니다. 안녕히 가세요.");
							run = false;
							break;
						}
					} else {
						throw new NoExistException("회원정보가 확인되지 않습니다.");// ();
					} // --if()
				}
				//System.out.println("회원정보가 확인되지 않습니다.");
				break;
			default:
				System.out.println("1~2값만 입력하세요.");
			}// --switch()

		} // --while()

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