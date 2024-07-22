package com.show.SV;

import java.util.ArrayList;
import java.util.Scanner;

import com.show.DTO.DramaDTO;
import com.show.DTO.ShowDTO;
import com.show.DTO.VarietyDTO;

public class SearchListSV {

	// 드라마, 예능 등 프로그램 정보 담고 있는 리스트 관리 클래스

//	public void searchTitle(String title) { // 제목으로 검색
//		for (DramaDTO dramaTitle : dramas) {
//			if (dramaTitle.getTitle().equalsIgnoreCase(title)) {
//				dramaTitle.showList(Integer.toString(i));
//			}
//		}
//	}

	public void 
	searchCategory(String category, ArrayList<ShowDTO> showDTOs, ArrayList<DramaDTO> dramaDTOs, ArrayList<VarietyDTO> varietyDTOs) { // 카테고리별 검색
		reset(dramaDTOs);
		int i = 1;
		for (DramaDTO dramaCategory : dramaDTOs) {
			if (dramaCategory.getCategory().equalsIgnoreCase(category)) {
				dramaCategory.showList(Integer.toString(i));
				// System.out.println(drama2.no);
				i++; // 번호 생성해주는 변수

			}
		}
	}// searchCategory 메서드

	public void searchProgram(String program, ArrayList<ShowDTO> showDTOs, ArrayList<DramaDTO> dramaDTOs, ArrayList<VarietyDTO> varietyDTOs) {
		// program 종류에 따라 리스트 출력하는 메서드
		int i = 1;
		for (DramaDTO ott : dramaDTOs) {
			if (ott.getProgram().equalsIgnoreCase(program)) {
				ott.showList(Integer.toString(i));
				i++;
			}
		}
	}// searchProgram 메서드

	public void searchVariety(String category, ArrayList<ShowDTO> showDTOs, ArrayList<DramaDTO> dramaDTOs, ArrayList<VarietyDTO> varietyDTOs) {
		// 예능 category 출력 메서드
		int i = 1;
		for (VarietyDTO variety : varietyDTOs) {
			if (variety.getCategory().equalsIgnoreCase(category)) {
				System.out.println(variety.getNo());
				i++;
			}	
		}
	}// searchProgram 메서드

//	public boolean searchTitle(String kwd1, boolean run) {
//		// program 종류에 따라 리스트 출력하는 메서드
//		reset();
//		int i = 1;
//		for (DramaDTO ott : dramas) {
//			if (ott.getTitle().equalsIgnoreCase(kwd1)) {
//				ott.showList(Integer.toString(i));
//				i++;
//			}
//		}
//		return false;
//	}// searchProgram 메서드

	private void reset(ArrayList<DramaDTO> dramaDTOs) {
		for (DramaDTO ott : dramaDTOs) {
			ott.setNo("0");
		}

	}

	public void searchNo(int pNo, ArrayList<ShowDTO> showDTOs, ArrayList<DramaDTO> dramaDTOs, ArrayList<VarietyDTO> varietyDTOs) {
		// 번호에 따라 출력하는 메서드
		for (DramaDTO ott : dramaDTOs) {
			try {
				if (ott.getNo().equalsIgnoreCase(Integer.toString(pNo))) {
					System.out.println(ott.toString());
				}
			} catch (Exception e) {
			}
		}
	}// searchProgram 메서드

		public boolean search(String kwd1, boolean run, ArrayList<ShowDTO> showDTOs, ArrayList<DramaDTO> dramaDTOs, ArrayList<VarietyDTO> varietyDTOs, Scanner sL2) { // 검색 기능 가능한 메서드 (보류)
		reset(dramaDTOs);
		int i = 1;
		showDTOs = new ArrayList<ShowDTO>();
		showDTOs.addAll(dramaDTOs);
		showDTOs.addAll(varietyDTOs);
		// 부모객체 ArrayList 에 자식객체 리스트 합침.
		for (ShowDTO allLists : showDTOs) { // 향상된 for 문 사용
			// 출력값 문제있음(보류..)
			if (allLists.getActor().contains(kwd1) || allLists.getCategory().contains(kwd1)
					|| allLists.getDirector().contains(kwd1) || allLists.getTitle().contains(kwd1)
					|| allLists.getProgram().contains(kwd1)) {
				allLists.showList(Integer.toString(i));
				i++;
				run = false;
			} 

		} // for
		return run;
	}

}
