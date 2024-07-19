package com.show;

import java.util.ArrayList;
import java.util.Scanner;

import com.show.DTO.DramaDTO;
import com.show.DTO.MemberDTO;
import com.show.DTO.ReviewDTO;
import com.show.DTO.ShowDTO;
import com.show.DTO.VarietyDTO;
import com.show.SV.FindSV;
import com.show.SV.LoginSV;
import com.show.SV.MenuSV;
import com.show.SV.MyPageSV;
import com.show.SV.SearchListSV;
import com.show.SV.SearchSV;

public class MainExam {

	
	public static Scanner s = new Scanner(System.in);// 단어 입력
	public static Scanner sL = new Scanner(System.in);// 긴 문장 입력
	public static ArrayList<MemberDTO> loginDTOs = new ArrayList<MemberDTO>();
	public static MemberDTO loginState ; //로그인 정보저장용 세션(변수)
	public static ArrayList<ReviewDTO> reviewDTOs = new ArrayList<ReviewDTO>();
	public static ArrayList<ShowDTO> showDTOs = new ArrayList<ShowDTO>();
	public static ArrayList<DramaDTO> dramaDTOs = new ArrayList<DramaDTO>();
	public static ArrayList<VarietyDTO> varietyDTOs = new ArrayList<VarietyDTO>();

	static {
		MemberDTO user1 = new MemberDTO("aaa", "aaa", "kim", "2012122000000", "tester", "0101234567", "aaa@aaa.com");
		loginDTOs.add(user1);
		MemberDTO user2 = new MemberDTO("bbb", "bbb", "lee", "9901011000000", "tester2", "0109876543", "bbb@bbb.com");
		loginDTOs.add(user2);

		dramaDTOs.add(new DramaDTO("Tving", "로맨스", "선재업고튀어", "윤종호, 이시은", "변우석, 김혜윤, 송건희, 이승협, 정영주, 성병숙 등",
				"만약, 당신의 최애를 구할 수 있는 기회가 된다면? \n삶의 의지를 놓아버린 순간, 자신을 살게 해줬던 유명아티스트 류선재 \n그의 죽음으로 절망했던 열성팬 임솔이 최애를 살리기 위해 시간을 거슬러 2008년으로 돌아간다!\n다시 살게 된 열아홉, 목표는 최애 류선재를 지키는 것!"));

		dramaDTOs.add(1, new DramaDTO("Netflix", "로맨스", "이번 생도 잘 부탁해", "이나정, 최영림, 한아름", "신혜선, 안보현, 하윤경, 안동구",
				"반지음에겐 전생을 기억할 수 있는 신비한 능력이 있다. \n18회차 인생이 이른 나이에 끝난 후 다시 태어난 그녀.\n이번 생은 어느덧 어른이 된 어린 시절의 사랑을 찾는데 올인한다."));

		dramaDTOs.add(2, new DramaDTO("Tving, Nexflix", "로맨스", "낮과 밤이 다른 그녀", "이형민, 박지하", "이정은, 정은지, 최진혁",
				"어느날 갑자기 노년타임에 갇혀버린 취준생과 낮과 밤 올 타임 \n그녀에게 휘말린 능력캐 검사의 기상천외한 인턴쉽과 앙큼달콤 로맨틱 코미디를 그리는 드라마"));

		dramaDTOs.add(3, new DramaDTO("Tving, Netflix", "판타지", "미씽:그들이있었다", "민연홍, 반기리", "고수, 허준호, 안소희, 하준, 서은수, 송건희",
				"실종된 사망자들이 모인 영혼 마을을 배경으로, 사라진 시체를 찾고 \n사건배후의 진실을 쫓는 미스터리 추적 판타지"));

		dramaDTOs.add(4, new DramaDTO("Tving, Netflix", "판타지", "경이로운 소문", "유선동, 김새봄", "조병규, 유준상, 김세정, 엄혜란 등",
				"악귀 사냥꾼 '카운터'들이 국수집 직원으로 위장해, 지상의 악귀들을 물리치는 \n통쾌하고 땀내나는 악귀타파 히어로물"));

		dramaDTOs.add(5, new DramaDTO("Tving", "액션", "스틸러", "최준배, 신경일", "주원, 이주우, 조한철, 김재원, 최화정, 이덕화 등",
				"베일에 싸인 문화재 도둑 스컹크와 비공식 문화재 환수팀 '카르마'가 뭉쳐, \n법이 심판하지 못하는 자들을 상대로 펼치는 케이퍼 코믹액션"));

		dramaDTOs.add(new DramaDTO("Tving", "판타지", "이재, 곧 죽습니다", "하병훈", "서인국, 박소담, 김지훈, 이재욱, 이도현, 고윤정 등",
				"지옥으로 떨어지기 직전의 이재가 12번의 죽음과 삶을 경험하게 되는 인생 환승 드라마"));

		dramaDTOs.add(new DramaDTO("Netflix", "액션", "마이네임", "김바다, 김진민", "한소희, 박희순, 안보현, 김상호, 이학주, 장률",
				"겉으로는 마약 범죄를 소탕하는 경찰. \n하지만 실제로 그녀가 충성을 바치는 인물은 따로 있다. \n그것은 바로, 그녀에게 새로운 삶을 주고 복수의 길을 열어준 범죄 조직의 보스"));

		dramaDTOs.add(new DramaDTO("Tving", "스릴러", "운수 오진날", "필감성, 김민성, 송한나", "이성민, 유연석, 이정은",
				"평범한 택시기사 오택이 고액을 제시하는 지방행 손님을 태우고 가다 \n그가 연쇄살인마임을 깨닫게 되면서 공포의 주행을 시작하게 되는 이야기"));

		varietyDTOs.add(new VarietyDTO("Netflix", "예능", "지구마불 세계여행", "김태호", "노홍철, 주우재, 주현영, 빠니보틀, 원지, 곽준빈",
				"주사위를 던져 그 결과에 따라 다음 목적지가 정해지는 일생일대의 모험을 시작한다.\n우승자에게는 우주여행이 기다리고 있다!!"));

	}

	public static void main(String[] args) {
		
		loginState = new MemberDTO(); //guest 용 객체로 loginState 생성
		MenuSV.mainMenu(s, sL, loginState, loginDTOs, reviewDTOs, showDTOs, dramaDTOs, varietyDTOs);

	}// main close

}// class close
