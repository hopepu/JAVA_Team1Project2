package com.show.SV;

import java.sql.Connection;
import java.util.Scanner;
import com.show.DTO.MemberDTO;
import com.show.exception.NoExistException;
import com.show.DAO.MemberKDAO;

public class FindSV {

	/* 메소드-아이디찾기 */
	public void idFind(MemberDTO loginState, Scanner s, Scanner sL, Connection conn) {
		// 입력받기(name, pNo, ssn)
		MemberDTO findUser = new MemberDTO();// 찾은 정보 넣을 새 객체 준비

		System.out.println("이름을 입력하세요.");
		System.out.print(">>>");
		findUser.setName(s.next());
		System.out.println("휴대폰번호를 입력하세요.(-제외)");
		System.out.print(">>>");
		findUser.setpNo(s.next());
		System.out.println("주민번호 앞자리를 입력하세요.");
		System.out.print(">>>");
		String fr_ssn = s.next();
		System.out.println("주민번호 뒷자리를 입력하세요.");
		System.out.print(">>>");
		String bk_ssn = s.next();

		LoginSV loginSV = new LoginSV(); // 메서드 부르기 위한 객체 형성(static 아닌 메서드)
		findUser = loginSV.ssnChager(fr_ssn, bk_ssn, findUser);// 주민번호를 birth와 sex로 변환된 객체로 다시 받음
		// System.out.println("test : "+ findUser.getBirth());
		// System.out.println("test : "+ findUser.getSex());
		MemberDTO resultUser = new MemberDTO();// 결과 넣을 객체
		MemberKDAO memberDAO = new MemberKDAO();
		resultUser = memberDAO.searchID(conn, findUser); // db로 정보 보내기

		try {
			if (resultUser.getId() == null) {
				throw new NoExistException("해당하는 계정이 없습니다.");
			} else {
				System.out.println(resultUser.getName() + "님의 아이디 : " + resultUser.getId());
				return;
			}
		} catch (NoExistException e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		}

	}// --idFind()

	/* 메소드-패스워드 찾기 */
	public void pwFind(Scanner s, Connection conn) {
		// 입력받기(id, pNo, ssn)
		MemberDTO findUser = new MemberDTO();// 찾은 정보 넣을 새 객체 준비
		System.out.println("아이디를 입력하세요.");
		System.out.print(">>>");
		findUser.setId(s.next());
		System.out.println("휴대폰번호를 입력하세요.");
		System.out.print(">>>");
		findUser.setpNo(s.next());
		System.out.println("주민번호 앞자리를 입력하세요.");
		System.out.print(">>>");
		String fr_ssn = s.next();
		System.out.println("주민번호 뒷자리를 입력하세요.");
		System.out.print(">>>");
		String bk_ssn = s.next();
		LoginSV loginSV = new LoginSV(); // 메서드 부르기 위한 객체 형성(static 아닌 메서드)
		findUser = loginSV.ssnChager(fr_ssn, bk_ssn, findUser);// 주민번호를 birth와 sex로 변환된 객체로 다시 받음
		//System.out.println("test birth: " + findUser.getBirth());
		//System.out.println("test sex : " + findUser.getSex());
		
		//회원정보 확인
		//MemberDTO resultUser = new MemberDTO();// 회원정보 확인결과 넣을 객체
		MemberKDAO memberDAO = new MemberKDAO();// db보낼 준비
		int result = 0;// 결과 받을 변수
		result = memberDAO.searhPW(conn, findUser); // 입력받은 객체를 db에 보냄-> 동일회원정보의 개수를 받음
		
		String tempPW = null;// 임시비번
		try {
			if (result>0) {// 검색된 값이 있으면(등록된 회원이면)
				tempPW = Integer.toString((int) (Math.random() * 1000000) + 1); // 임의의 6자리 숫자를 임시 pw로 받기
				findUser.setPw(tempPW); // 임시비번을 객체에 저장
				memberDAO.updatePW(conn, findUser); // 복사객체를 DB로 보내 저장(임시비번 정보 저장)
				System.out.println(findUser.getId() + "님의 임시비밀번호 : " + tempPW);
				System.out.println("주의! 로그인 후 반드시 회원정보수정을 통해 비밀번호를 변경해 주세요.");			
			} else {// 등록된 회원이 아닌 경우
				throw new NoExistException("회원정보를 확인해 주세요.");
			}

		} catch (NoExistException e) {
			System.out.println(e.getMessage());
			return;
			// e.printStackTrace();
		}

	}// --pwFind()

}