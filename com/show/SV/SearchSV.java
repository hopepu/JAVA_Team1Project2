package com.show.SV;

import java.util.ArrayList;
import java.util.Scanner;

import com.show.DTO.DramaDTO;
import com.show.DTO.MemberDTO;
import com.show.DTO.ReviewDTO;
import com.show.DTO.ShowDTO;
import com.show.DTO.VarietyDTO;

public class SearchSV {

	

	public static void categorySearch(ArrayList<com.show.DTO.ShowDTO> showDTO2, SearchListSV showList,
			ArrayList<ReviewDTO> reviewDTOs, ArrayList<com.show.DTO.ShowDTO> showDTOs, MemberDTO lSt, Scanner s,
			Scanner sL, ArrayList<ShowDTO> showDTOs2, ArrayList<DramaDTO> dramaDTOs, ArrayList<VarietyDTO> varietyDTOs) {
		System.out.println("----- 장르별 작품 보기 -----");
		System.out.println("1.로맨스 \t2.액션");
		System.out.println("3.코미디 \t4.SF");
		System.out.println("5.판타지 \t6.미스터리");
		System.out.println("7.예능 \t8.뒤로가기");
		System.out.print(">> ");
		int no = s.nextInt(); // 메뉴 번호 입력

		switch (no) { // 번호 입력받고 상세정보 제공하는 기능 필요함.
		case 1:
			showList.searchCategory("로맨스", showDTOs, dramaDTOs, varietyDTOs);
			System.out.println("--------------------------");
			reference(sL, showDTOs2, dramaDTOs, varietyDTOs, showList);
			break;
		case 2:
			showList.searchCategory("액션",showDTOs, dramaDTOs, varietyDTOs);
			System.out.println("--------------------------");
			reference(sL, showDTOs2, dramaDTOs, varietyDTOs, showList);
			break;
		case 3:
			showList.searchCategory("코미디",showDTOs, dramaDTOs, varietyDTOs);
			System.out.println("--------------------------");
			reference(sL, showDTOs2, dramaDTOs, varietyDTOs, showList);
			break;
		case 4:
			showList.searchCategory("SF",showDTOs, dramaDTOs, varietyDTOs);
			System.out.println("--------------------------");
			reference(sL, showDTOs2, dramaDTOs, varietyDTOs, showList);
			break;
		case 5:
			showList.searchCategory("판타지",showDTOs, dramaDTOs, varietyDTOs);
			System.out.println("--------------------------");
			reference(sL, showDTOs2, dramaDTOs, varietyDTOs, showList);
			break;
		case 6:
			showList.searchCategory("미스터리",showDTOs, dramaDTOs, varietyDTOs);
			System.out.println("--------------------------");
			reference(sL, showDTOs2, dramaDTOs, varietyDTOs, showList);
			break;
		case 7:
			showList.searchVariety("예능",showDTOs, dramaDTOs, varietyDTOs);
			System.out.println("--------------------------");
			reference(sL, showDTOs2, dramaDTOs, varietyDTOs, showList);
			break;
		case 8:
			System.out.println("-----------뒤로가기-----------");
			break;
		default:
			System.out.println("1 ~ 6 번호만 입력하세요");
		}// 장르 switch

	}

	static void search(ArrayList<ShowDTO> showDTO, ArrayList<ReviewDTO> reviewDTOs, ArrayList<ShowDTO> showDTOs,
			MemberDTO lSt, Scanner s, Scanner sL, ArrayList<DramaDTO> dramaDTOs, ArrayList<VarietyDTO> varietyDTOs,
			SearchListSV showList) {
		boolean run = true;
		while (run) {
			try {
				System.out.println(" 검색 키워드를 입력해 주세요.");
				System.out.print(">>>");
				String kwd1 = sL.nextLine();
				run = showList.search(kwd1, run, showDTOs, dramaDTOs, varietyDTOs, sL);
				
			} catch (Exception e) {
				System.out.println("입력하신 제목을 다시 확인해주세요");
				System.out.println(e);
			}
		}
		
		reference(s, showDTOs, dramaDTOs, varietyDTOs, showList);
		

		

	}// search close

	public static void reference(Scanner s, ArrayList<ShowDTO> showDTOs, ArrayList<DramaDTO> dramaDTOs,
			ArrayList<VarietyDTO> varietyDTOs, SearchListSV showList) {
		System.out.println("몇번 컨텐츠를 조회하시겠습니까?");
		System.out.print(">>>");
		int pNo = s.nextInt();

		showList.searchNo(pNo, showDTOs, dramaDTOs, varietyDTOs);
		
	}

}
