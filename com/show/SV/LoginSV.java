package com.show.SV;

import java.util.ArrayList;
//import java.util.Iterator;
import java.util.Scanner;
import com.show.DTO.MemberDTO;

public class LoginSV {


	public static void register(Scanner s, MemberDTO loginState, ArrayList<MemberDTO> loginDTOs) {
		MemberDTO join = new MemberDTO();

		System.out.println("회원 가입을 시작합니다.");
		boolean run = true;
		String joinID = "";
		while (run) {
			System.out.println("원하시는 ID를 입력하세요");
			System.out.print(">>>");
			joinID = s.next();
			boolean pass = true;
			pass = join.setId(joinID, loginDTOs, pass);
			
			if (pass) {
				join.setUsability(true);
				if(join.isUsability()) {
					System.out.println("사용 가능한 ID입니다.");
					System.out.println("--------------------");
					run=false;
				}
			}
		}
				System.out.println("원하시는 PW를 입력하세요");
				System.out.print(">>>");
				String joinPW = s.next();
				
				System.out.println("--------------------");
				System.out.println("이름을 입력하세요");
				System.out.print(">>>");
				String joinName = s.next();
				
				System.out.println("--------------------");
				System.out.println("생년월일을 입력하세요");
				System.out.print(">>>");		
				String joinBirth = s.next();
				
				System.out.println("--------------------");
				System.out.println("닉네임을 입력하세요");
				System.out.print(">>>");
				String joinNickName = s.next();
				
				System.out.println("--------------------");
				System.out.println("e-mail을 입력하세요");
				System.out.print(">>>");
				String joinEmail = s.next();
				
				System.out.println("--------------------");
				System.out.println("전화번호를 입력하세요");
				System.out.print(">>>");
				String joinpNo = s.next();
				
				MemberDTO join1 = new MemberDTO(joinID, joinPW, joinName, joinBirth, joinNickName, joinpNo, joinEmail);
				loginDTOs.add(join1);
		
		
	}

	public static MemberDTO login(Scanner s, MemberDTO loginState, ArrayList<MemberDTO> loginDTOs) {
		System.out.print("ID : ");
		String id = s.next();
		System.out.print("PW : ");
		String pw = s.next();
		MemberDTO login = new MemberDTO(id, pw); // 입력받은 ID,PW를 넣을 객체 생성

		try {
			for (MemberDTO find : loginDTOs) {// memberDTOs 배열을 돌려
				int i = 0;
				if (find.getId().equals(id)) {
					if (find.getPw().equals(pw)) {
						System.out.println("로그인 성공!!");
						i = loginDTOs.indexOf(find);
						login = loginDTOs.get(i);
						login.setLoginStatus(true);
						loginState = login;
						break;
					} else {
//						System.out.println("id, pw를 확인해주세요.");
					} // if close
				} else {
//					System.out.println("id, pw를 확인해주세요.");
				}  // if close

			} // for close
			if(login.getLoginStatus()==false) {
				System.out.println("id, pw를 확인해주세요.");
			}

		} catch (Exception e) {
			System.out.println("숫자를 입력해주세요");
		}//try catch close

		return loginState;
	}// login method close

}
