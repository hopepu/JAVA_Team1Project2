package Utils;

import java.util.ArrayList;
import java.util.List;

import DTO.MemberDTO;


public class Automatic { // 자동변환 등의 공통 메서드 모음

	/*
	 * 메서드-주민번호 추출 : 주민번호 앞-뒤 자리를 받아 생년월일-성별로 변환 public static MemberDTO
	 * ssnChager(String fr_ssn, String bk_ssn, MemberDTO member) {// 주민번호
	 * 앞자리(fr_ssn)과 뒷자리(bk_ssn)을 // 받아 // 생년월일과 성별을 추출한다. // 주민번호 앞자리 YY-MM-DD 형태로
	 * 추출하기 int calyear = Integer.parseInt(fr_ssn.substring(0, 2)); // 앞 2자리 int
	 * calmonth = Integer.parseInt(fr_ssn.substring(2, 4)); // 중간 2자리 int calday =
	 * Integer.parseInt(fr_ssn.substring(4)); // 끝 2자리
	 * 
	 * int sexnum = Integer.parseInt(bk_ssn); // 뒷번호 첫 글자 (한글자만 받음-> string으로 받아
	 * int로)
	 * 
	 * String year = (sexnum == 1 || sexnum == 2) ? "19" : "20"; // 주민번호 뒷자리 첫 글자가 1
	 * 또는 2면 앞에 19년을, 아니면 20년을 적용 String birth = year + String.format("%02d",
	 * calyear) + "/" + String.format("%02d", calmonth) + "/" +
	 * String.format("%02d", calday); // YYYY/MM/DD 형태로 birth 저장 String sex =
	 * (sexnum % 2 == 0) ? "여" : "남"; // 주민번호 뒷자리 첫 글자가 짝수면 여, 홀수면 남
	 * member.setBirth(birth); member.setSex(sex);
	 * 
	 * return member; }
	 */

	/* 생년월일 합치기 */
	public static String birthMaker(String birth_year, String birth_month, String birth_day) {
		String user_birth = birth_year + "/" + birth_month + "/" + birth_day;
		return user_birth;
	}

	/* 메서드-휴대폰 번호 합치기 */
	public static String phoneJoin(String pno_frn, String pno_mid, String pno_last) {
		String phoneNum = pno_frn + "-" + pno_mid + "-" + pno_last;
		return phoneNum;
	}
	
	/* 메서드-휴대폰 번호 분리 */
	public static String[] phoneDiv(MemberDTO memberDTO) {
		
		String phoneNumStr = memberDTO.getpNo(); 
		String[] phoneNums =  phoneNumStr.split("-", 3); //"-"기준으로 3개로 나누어 배열에 넣음
		
		return phoneNums;
	}

	/* 메서드-메일주소 합치기 */
	public static String mailJoin(String mail_frn, String mail_last) {
		String mailAddr = mail_frn + mail_last;
		return mailAddr;
	}
	
	/* 메서드-메일주소 분리 */
	public static String[] mailDiv(MemberDTO memberDTO) {
		String mailAddr = memberDTO.getMail();
		String[] mails = mailAddr.split("@", 2);
		return mails;
	}

	/* 달력리스트 만들기 - 시작, 끝 값 받아서 리스트 만들기(내림차순+2자리수 만들기) */
	public List<String> makeListASC(int start, int end) {
		List<String> lists = new ArrayList<String>();

		for (int i = start; i <= end; i++) {
			String str = Integer.toString(i);
			if (str.length() < 2)
				str = "0" + str;
			lists.add(str);
		}
		return lists;
	}// --makeListASC()

	/* 달력리스트 만들기 - 시작, 끝 값 받아서 리스트 만들기(오름차순) */
	public List<String> makeListDESC(int start, int end) {
		List<String> lists = new ArrayList<String>();

		for (int i = end; i >= start; i--) {
			String str = Integer.toString(i);
			lists.add(str);
		}
		return lists;
	}// --makeListDESC()

	/* 달력배열 만들기 - 시작, 끝 값 받아서 리스트 만들기(내림차순+2자리수 만들기) */
	public String[] makeSeqASC(int start, int end) {
		int len = end - (start - 1);
		String[] lists = new String[len];

		for (int i = 0; i < len; i++) {
			String str = Integer.toString(start);
			if (str.length() < 2)
				str = "0" + str;
			lists[i] = str;
			start++;
		}
		return lists;
	}// --makeSeqASC()

	/* 달력배열 만들기 - 시작, 끝 값 받아서 리스트 만들기(오름차순) **/
	public String[] makeSeqDESC(int start, int end) {
		int len = end - (start - 1);
		String[] lists = new String[len];

		for (int i = 0; i < len; i++) {
			String str = Integer.toString(end);
			lists[i] = str;
			end--;
		}
		return lists;
	}// --makeSeqASC()

	/* 달력 말일 판단(윤년계산 포함) */
	public int choiceEndDay(String year, String month) {
		int endDay = 0;
		int calYear = Integer.parseInt(year);
		int calMonth = Integer.parseInt(month);

		// 윤년판단
		boolean leapyear = (calYear % 4 == 0 || calYear % 100 == 0 || calYear % 400 == 0) ? true : false;
		
		switch (calMonth) {
		case 1, 3, 5, 7, 8, 10, 12:
			endDay = 31;
			break;
		case 4, 6, 9, 11:
			endDay = 30;
			break;
		case 2:
			if (leapyear) {// 윤년이면
				endDay = 29;
			} else {
				endDay = 28;
			}
			break;
		default:
			endDay = 31;
		}

		return endDay;
	}
	
	/* 생년월일변환(month 내장객체 이용)-db입력용 */
	public static String birthChange(String birth) {
		String newBirht= birth.replace("-", "/");
		return newBirht;
	}
	
	/* 생년월일변환(month 내장객체 이용)-불러오기 */
	public static String birthReChange(String birth) {
		String newBirht= birth.replace("/", "-");
		return newBirht;
	}

}
