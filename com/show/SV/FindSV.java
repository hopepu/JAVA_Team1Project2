//package com.show.SV;
//
//import java.util.ArrayList;
//import java.util.Scanner;
//import com.show.DTO.MemberDTO;
//import com.show.NoExistException;
//
//public class FindSV {
//		
//	/* 메소드-아이디찾기 */
//	public static void idFind(Scanner s, ArrayList<MemberDTO> loginDTOs) throws NoExistException {
//		// 입력받기(name, pNo, ssn)
//		System.out.println("이름을 입력하세요.");
//		System.out.print(">>>");
//		String name = s.next();
//		System.out.println("휴대폰번호를 입력하세요.");
//		System.out.print(">>>");
//		String pno = s.next();
//		System.out.println("주민번호를 입력하세요.(-제외)");
//		System.out.print(">>>");
//		String ssn = s.next();
//		String birth = ssn.substring(0, 6);
//
//		MemberDTO findUser = new MemberDTO();// 찾은 정보 넣을 새 객체 준비
//		// 배열에서 휴대폰번호를 검색하여 findUser 에 인덱스 정보를 넣고
//		// 이름, 주민번호를 다시 검증하여 아이디 출력
//		int i = findIndex(pno, loginDTOs);
//		findUser = loginDTOs.get(i); // 찾은 인덱스의 객체를 준비한 객체에 복사
//		if (findUser.getName().equals(name) && findUser.getBirth().equals(birth)) {
//			System.out.println(findUser.getName() + "님의 아이디 : " + findUser.getId());
//			} else {// 이름, 주민번호가 다른 경우
//				throw new NoExistException("회원정보를 확인해 주세요.");
//			}
//		
//
//	}// --idFind()
//
//	/* 메소드-패스워드 찾기 */
//	public static void pwFind(Scanner s, ArrayList<MemberDTO> loginDTOs) throws NoExistException{
//		// 입력받기(id, pNo, ssn)
//		System.out.println("아이디를 입력하세요.");
//		System.out.print(">>>");
//		String id = s.next();
//		System.out.println("휴대폰번호를 입력하세요.");
//		System.out.print(">>>");
//		String pno = s.next();
//		System.out.println("주민번호를 입력하세요.(-제외)");
//		System.out.print(">>>");
//		String ssn = s.next();
//		String birth = ssn.substring(0, 6);
//		
//		MemberDTO findUser = new MemberDTO();// 찾은 정보 넣을 새 객체 준비
//		int i = findIndex(pno, loginDTOs);
//		findUser = loginDTOs.get(i); // 찾은 인덱스의 객체를 준비한 객체에 복사
//		
//		if (findUser.getId().equals(id) && findUser.getBirth().equals(birth)) {
//			String tempPW = Integer.toString((int)(Math.random()*1000000)+1); //임의의 6자리 숫자를 임시 pw로 받기
//			findUser.setPw(tempPW); //임시비번을 복사객체에 넣고
//			loginDTOs.add(i, findUser); //복사객체를 리스트에 넣음(임시비번 정보 저장)  
//			System.out.println(findUser.getName() + "님의 임시비밀번호 : " + tempPW);
//			System.out.println("주의! 로그인 후 반드시 회원정보수정을 통해 비밀번호를 변경해 주세요.");
//			} else {// 이름, 주민번호가 다른 경우
////				throw new NoExistException("회원정보를 확인해 주세요.");
//			}
//	
//	}// --pwFind()
//
//	/* 메소드(공통)-리스트에서 pNo 검색하여 인덱스 리턴 */
//	public static int findIndex(String pno, ArrayList<MemberDTO> loginDTOs) throws NoExistException {
//		int findIndex = 0; // 찾은 인덱스 번호 넣을 변수
//		for (MemberDTO findUser : loginDTOs) {
//			if (findUser.getpNo().equals(pno)) {// 휴대폰 번호가 같으면
//				findIndex = loginDTOs.indexOf(findUser); // 인덱스 번호 추출
//				break;
//			} else {// 휴대폰정보가 없으면
////				throw new NoExistException("가입 정보를 찾을 수 없습니다.");
//			}
//		} // --for()
//
//		return findIndex;
//	}
//	
//	/* 메소드(공통)-리스트에서 id 검색하여 인덱스 리턴 */
//	public static int findIDIndex(String id, ArrayList<MemberDTO> loginDTOs) throws NoExistException {
//		int findIndex = 0; // 찾은 인덱스 번호 넣을 변수
//		for (MemberDTO findUser : loginDTOs) {
//			if (findUser.getId().equals(id)) {
//				System.out.println("아이디확인");// 휴대폰 번호가 같으면
//				findIndex = loginDTOs.indexOf(findUser); // 인덱스 번호 추출
//				break;
//			} else {// 휴대폰정보가 없으면
//				//throw new NoExistException("가입 정보를 찾을 수 없습니다.");
//				
//			}
//		} // --for()
//
//		return findIndex;
//	}
//
//}
//

