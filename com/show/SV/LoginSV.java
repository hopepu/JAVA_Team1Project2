package com.show.SV;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.Scanner;

import com.show.DAO.MemberMDAO;
import com.show.DTO.MemberDTO;

public class LoginSV {

<<<<<<< HEAD
   public static void register(Scanner s, Scanner sL, MemberDTO loginState, Connection connection) {

      MemberDTO join = new MemberDTO();

      System.out.println("회원 가입을 시작합니다.");

      
      System.out.println("원하시는 ID를 입력하세요");
      System.out.print(">>>");
      join.setId(s.next());

      System.out.println("원하시는 PW를 입력하세요");
      System.out.print(">>>");
      join.setPw(s.next());

      System.out.println("--------------------");
      System.out.println("이름을 입력하세요");
      System.out.print(">>>");
      join.setName(s.next());

      System.out.println("--------------------");
      System.out.println("주민번호 앞 6자리를 입력하세요");
      System.out.print(">>>");
      String joinBirth = s.next();
      
      System.out.println("--------------------");
      System.out.println("주민번호 뒤 7자리를 입력하세요");
      System.out.print(">>>");
      String joinSex = s.next();
      
      join = ssnChager(joinBirth,joinSex, join);

      System.out.println("원하시는 닉네임을 입력하세요");
      System.out.print(">>>");
      join.setNickName(s.next());

      System.out.println("--------------------");
      System.out.println("e-mail을 입력하세요");
      System.out.print(">>>");
      join.setMail(s.next());

      System.out.println("--------------------");
      System.out.println("전화번호를 입력하세요");
      System.out.print(">>>");
      join.setpNo(s.next());
      
      MemberMDAO memberMDAO = new MemberMDAO();
      memberMDAO.join(connection, join);

      

   }

   public static MemberDTO login(MemberDTO loginState, Scanner s, Scanner sL, Connection connection) {
      System.out.println("로그인 메서드로 진입");
      System.out.print("ID : ");
      String id = s.next();
      System.out.print("PW : ");
      String pw = s.next();
      MemberDTO loginDTO = new MemberDTO(id, pw); // 입력받은 ID,PW를 넣을 객체 생성
      System.out.println();
      MemberMDAO memberDAO = new MemberMDAO();  // 객체가 생성/  connection을 여기에 쓰면 생성되면서 1단,2단계 정보가 들어감.
      return memberDAO.login(connection, loginState, loginDTO);  // connection : 호출할때 정보를 밀어넣음
   }// login method close

   /* 메서드-주민번호 추출 */
   public static MemberDTO ssnChager(String fr_ssn, String bk_ssn, MemberDTO member) {// 주민번호 앞자리(fr_ssn)과 뒷자리(bk_ssn)을 받아
                                                            // 생년월일과 성별을 추출한다.
      // 주민번호 앞자리 YY-MM-DD 형태로 추출하기
      int calyear = Integer.parseInt(fr_ssn.substring(0, 2)); // 앞 2자리
      int calmonth = Integer.parseInt(fr_ssn.substring(2, 4)); // 중간 2자리
      int calday = Integer.parseInt(fr_ssn.substring(4)); // 끝 2자리

      int sexnum = Integer.parseInt(bk_ssn.substring(0, 1)); // 뒷번호 첫 글자 추출

      String year = (sexnum == 1 || sexnum == 2) ? "19" : "20"; // 주민번호 뒷자리 첫 글자가 1 또는 2면 앞에 19년을, 아니면 20년을 적용
      String birth = year + String.format("%02d", calyear) + "/" + String.format("%02d", calmonth) + "/"
            + String.format("%02d", calday); // YYYY/MM/DD 형태로 birth 저장
      String sex = (sexnum % 2 == 0) ? "여" : "남"; // 주민번호 뒷자리 첫 글자가 짝수면 여, 홀수면 남
      member.setBirth(birth);
      member.setSex(sex);

      return member;
   }
=======
	public static void register(Scanner s, Scanner sL, MemberDTO loginState, Connection connection) {

		MemberDTO join = new MemberDTO();
		MemberMDAO memberMDAO = new MemberMDAO();

		System.out.println("회원 가입을 시작합니다.");
		// 동일아이디 검사하여 사용가능한 아이디만 입력받는다.
		boolean idcheck = true;
		while (idcheck) {
			System.out.println("원하시는 ID를 입력하세요");
			System.out.print(">>>");
			join.setId(s.next());
			join = memberMDAO.checkID(connection, join); // 사용가능여부 db로 보내서 확인
			if (join.isUsability()) {
				System.out.println(join.getId() + "는 사용가능한 ID입니다.");
				idcheck = false;
				break;
			} else {
				System.out.println("사용중인 ID입니다.\n다른 ID를 입력하세요.");
			}
		} // --while()

		System.out.println("원하시는 PW를 입력하세요");
		System.out.print(">>>");
		join.setPw(s.next());

		System.out.println("--------------------");
		System.out.println("이름을 입력하세요");
		System.out.print(">>>");
		join.setName(s.next());

		System.out.println("--------------------");
		System.out.println("주민번호 앞 6자리를 입력하세요");
		System.out.print(">>>");
		String joinBirth = s.next();

		System.out.println("--------------------");
		System.out.println("주민번호 뒤 7자리를 입력하세요");
		System.out.print(">>>");
		String joinSex = s.next();

		join = ssnChager(joinBirth, joinSex, join);

		boolean nickcheck = true;
		while (nickcheck) {
			System.out.println("원하시는 닉네임을 입력하세요");
			System.out.print(">>>");
			join.setNickName(s.next());
			join = memberMDAO.checkNickname(connection, join); // 사용가능여부 db로 보내서 확인
			if (join.isUsability()) {
				System.out.println(join.getId() + "는 사용가능한 닉네임입니다.");
				idcheck = false;
				break;
			} else {
				System.out.println("사용중인 ID입니다.\n다른 닉네임을 입력하세요.");
			}
		} // --while()

		System.out.println("--------------------");
		System.out.println("e-mail을 입력하세요");
		System.out.print(">>>");
		join.setMail(s.next());

		System.out.println("--------------------");
		System.out.println("전화번호를 입력하세요");
		System.out.print(">>>");
		join.setpNo(s.next());

		System.out.println("--------------------");
		System.out.println("1.가입하기 | 2.취소");
		int save = s.nextInt();
		switch (save) {
		case 1:
			join.setAuthor(1);
			int result = memberMDAO.register(connection, join);
			if(result==2) {				
				System.out.println(join.getNickName()+" 님 환영합니다.");
				System.out.println("가입하신 정보로 로그인해 주세요.");
			}else {
				System.out.println("프로그램 오류입니다. 관리자에게 문의하세요.");
			}

			break;
		case 2:
			System.out.println("회원가입을 취소합니다.");
			return;
		default:
			System.out.println("1~2값 중 골라주세요");

		}// --switch()

	}

	public static MemberDTO login(MemberDTO loginState, Scanner s, Scanner sL, Connection connection) {
		System.out.println("로그인 메서드로 진입");
		System.out.print("ID : ");
		String id = s.next();
		System.out.print("PW : ");
		String pw = s.next();
		MemberDTO loginDTO = new MemberDTO(id, pw); // 입력받은 ID,PW를 넣을 객체 생성
		System.out.println();
		MemberMDAO memberDAO = new MemberMDAO(); // 객체가 생성/ connection을 여기에 쓰면 생성되면서 1단,2단계 정보가 들어감.
		return memberDAO.login(connection, loginDTO); // connection : 호출할때 정보를 밀어넣음
	}// login method close
>>>>>>> origin/kjs

	/* 메서드-주민번호 추출 */
	public static MemberDTO ssnChager(String fr_ssn, String bk_ssn, MemberDTO member) {// 주민번호 앞자리(fr_ssn)과 뒷자리(bk_ssn)을
																						// 받아
		// 생년월일과 성별을 추출한다.
		// 주민번호 앞자리 YY-MM-DD 형태로 추출하기
		int calyear = Integer.parseInt(fr_ssn.substring(0, 2)); // 앞 2자리
		int calmonth = Integer.parseInt(fr_ssn.substring(2, 4)); // 중간 2자리
		int calday = Integer.parseInt(fr_ssn.substring(4)); // 끝 2자리

		int sexnum = Integer.parseInt(bk_ssn.substring(0, 1)); // 뒷번호 첫 글자 추출

		String year = (sexnum == 1 || sexnum == 2) ? "19" : "20"; // 주민번호 뒷자리 첫 글자가 1 또는 2면 앞에 19년을, 아니면 20년을 적용
		String birth = year + String.format("%02d", calyear) + "/" + String.format("%02d", calmonth) + "/"
				+ String.format("%02d", calday); // YYYY/MM/DD 형태로 birth 저장
		String sex = (sexnum % 2 == 0) ? "여" : "남"; // 주민번호 뒷자리 첫 글자가 짝수면 여, 홀수면 남
		member.setBirth(birth);
		member.setSex(sex);

		return member;
	}

}
