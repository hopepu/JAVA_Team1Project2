package DAO;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;
import java.util.Vector;

import javax.servlet.ServletContext;

import DTO.ShowDTO;
import Utils.JDBConnect;

public class ShowDAO extends JDBConnect{
	
	public ShowDAO(ServletContext application) {
		super(application);
	}
	
	
   public void insert() { // 관리쟈용
      try {
         String sql = "insert into show_tbl values (show_seq.nextval, ?, ?, ?, ?, ?, ?) ";
         pstmt = con.prepareStatement(sql);

         int result = pstmt.executeUpdate();

         if (result > 0) {
            System.out.println(result + " 행이 추가되었습니다.");
         } else {
            System.out.println("데이터 등록 실패");
         }
      } catch (SQLException e) {
         System.out.println("ShowDAO.insert 쿼리문 확인");
      }
   }// insert
   
   
   public int selectCount(Map<String, Object> map) { // 출력 리스트 총 개수
		int totalCount = 0; // 리턴 값
		
		// 3단계 쿼리문 작성
		String query = "select count(*) from show_tbl ";
		if(map.get("searchWord")!= null) {
			// 검색어 O
			query += "where " + map.get("searchField") + " like '%"+map.get("searchWord")+"%'";
			// searchField : 제목, 내용, 작성자(Object) - 검색조건
			// searchWord : input="text" 로 넘어온 글자. - 검색어
			// select count(*) from board where 제목 like '%딸기%';
		} // 검색어 조건 추가
		
		// 4단계 : 실행
		try {
			stmt = con.createStatement(); // 쿼리문 연결
			
			rs = stmt.executeQuery(query); // 쿼리문 실행 후 결과 표로 받음
			rs.next(); // 
			totalCount=rs.getInt(1); // 첫번째 컬럼 값 totalCount 삽입
			System.out.println(totalCount);
		} catch (SQLException e) {
			System.out.println("ShowDAO.selectCount 메서드 오류");
//			e.printStackTrace();
		}
		
		
		return totalCount;
	}

   public List<ShowDTO> selectList(Map<String, Object>map) { 
	   
	   List<ShowDTO> lists = new Vector<ShowDTO>();
	   String query = "select * from ( select s.*, rownum rNum from ( select * from show_tbl ";
		// 조건 추가
		if (map.get("searchWord") != null) {
			// 검색어가 있으면
			query += "where " + map.get("searchField") + " like '%" + map.get("searchWord") + "%'";
			// searchField : 제목,내용,작성자 
			// searchWord : input text로 넘어온 글자
			// select count(*) from board where 제목 like '%딸기%' ;
		} // 검색어가 있으면 조건이 추가 된다.

		query += "order by showNo asc ) s ) where rNum between ? and ? "; // 정렬 기준 추가
		// 3단계 쿼리문 완성
		// 4단계 : 실행
		System.out.println(map.get("start").toString() + " " + map.get("end").toString());
		try {
			pstmt = con.prepareStatement(query); // 쿼리문 연결
			pstmt.setString(1, map.get("start").toString()); // 시작번호
			pstmt.setString(2, map.get("end").toString());	 // 끝번호
			rs = pstmt.executeQuery(); // 쿼리문 실행 후 결과 표로 받음(4단계)
			
			while(rs.next()) {
				ShowDTO dto = new ShowDTO();
				dto.setShowNo(rs.getString("showNo"));
				dto.setTitle(rs.getString("title"));
				dto.setCategory(rs.getString("category"));
				dto.setPlatform(rs.getString("platform"));
				dto.setDirector(rs.getString("director"));
				dto.setActor(rs.getString("actor"));
				dto.setContents(rs.getString("contents"));
				dto.setMain_jpg(rs.getString("main_jpg"));
				dto.setInfo_jpg(rs.getString("info_jpg"));
				dto.setInfo_day(rs.getString("info_day"));
				// name 필드 null
				
				lists.add(dto); // boardDTO 객체 list 객체에 추가
			} // while 종료
			
			
			
		} catch (SQLException e) {
			System.out.println("BoardDAO.selectList 메서드 오류");
			System.out.println("board 테이블 모든 리스트 출력 오류");
//			e.printStackTrace();
		}
			
		
		
		return lists;
	}
	// 게시글 등록용 메서드
   
   public ShowDTO selectView(String title){
	   ShowDTO dto = new ShowDTO();
	   String sql = "select * from show_tbl where title=?";
	   
	   try {
		pstmt=con.prepareStatement(sql);
		   pstmt.setString(1, title);
		   rs=pstmt.executeQuery();
		   
		   if(rs.next()) {
			   dto.setShowNo(rs.getString("showNo"));
			   dto.setTitle(rs.getString("title"));
			   dto.setCategory(rs.getString("category"));
			   dto.setPlatform(rs.getString("platform"));
			   dto.setDirector(rs.getString("director"));
			   dto.setActor(rs.getString("actor"));
			   dto.setContents(rs.getString("contents"));
			   dto.setMain_jpg(rs.getString("main_jpg"));
			   dto.setInfo_jpg(rs.getString("info_jpg"));
			   dto.setInfo_day(rs.getString("info_day"));
			   
			  
		   }
	} catch (SQLException e) {
		System.out.println("ShowDAO.selectView 메서드 에러 발생");
		e.printStackTrace();
	}
	return dto;
   }
   
   public ShowDTO selectCate(String category){
	   ShowDTO dto = new ShowDTO();
	   String sql = "select * from show_tbl where title=?";
	   
	   try {
		pstmt=con.prepareStatement(sql);
		   pstmt.setString(1, category);
		   rs=pstmt.executeQuery();
		   
		   if(rs.next()) {
			   dto.setShowNo(rs.getString("showNo"));
			   dto.setTitle(rs.getString("title"));
			   dto.setCategory(rs.getString("category"));
			   dto.setPlatform(rs.getString("platform"));
			   dto.setDirector(rs.getString("director"));
			   dto.setActor(rs.getString("actor"));
			   dto.setContents(rs.getString("contents"));
			   dto.setMain_jpg(rs.getString("main_jpg"));
			   dto.setInfo_jpg(rs.getString("info_jpg"));
			   dto.setInfo_day(rs.getString("info_day"));
			   
			  
		   }
	} catch (SQLException e) {
		System.out.println("ShowDAO.selectView 메서드 에러 발생");
		e.printStackTrace();
	}
	return dto;
   }
   
   public int selectCategory(Map<String, Object> map) { // 출력 리스트 총 개수
		int totalCount = 0; // 리턴 값
		
		// 3단계 쿼리문 작성
		String query = "select count(*) from show_tbl ";
		if(map.get("category")!= null) {
			// 검색어 O
			query += "where category like '%"+map.get("category")+"%'";
			// searchField : 제목, 내용, 작성자(Object) - 검색조건
			// searchWord : input="text" 로 넘어온 글자. - 검색어
			// select count(*) from board where 제목 like '%딸기%';
		} // 검색어 조건 추가
		
		if(map.get("platform")!= null) {
			// 검색어 O
			query += "where platform like '%"+map.get("platform")+"%'";
			// searchField : 제목, 내용, 작성자(Object) - 검색조건
			// searchWord : input="text" 로 넘어온 글자. - 검색어
			// select count(*) from board where 제목 like '%딸기%';
		} // 검색어 조건 추가
		
		
		// 4단계 : 실행
		try {
			stmt = con.createStatement(); // 쿼리문 연결
			
			rs = stmt.executeQuery(query); // 쿼리문 실행 후 결과 표로 받음
			rs.next(); // 
			totalCount=rs.getInt(1); // 첫번째 컬럼 값 totalCount 삽입
			System.out.println(totalCount);
		} catch (SQLException e) {
			System.out.println("ShowDAO.selectCount 메서드 오류");
//			e.printStackTrace();
		}
		
		
		return totalCount;
	}
   	   public List<ShowDTO> lists(Map<String, Object>map) { 
	   
	   List<ShowDTO> lists = new Vector<ShowDTO>();
	   String query = "select * from ( select s.*, rownum rNum from ( select * from show_tbl ";
		// 조건 추가
		if (map.get("category") != null) {
			// 검색어가 있으면
			query += "where category like '%" + map.get("category") + "%'";
			// searchField : 제목,내용,작성자 
			// searchWord : input text로 넘어온 글자
			// select count(*) from board where 제목 like '%딸기%' ;
		} // 검색어가 있으면 조건이 추가 된다.
		if(map.get("platform") != null) {
			query += "where platform like '%" + map.get("platform") + "%'";
		}

		query += "order by showNo desc ) s ) where rNum between ? and ? "; // 정렬 기준 추가
		// 3단계 쿼리문 완성
		// 4단계 : 실행
		System.out.println(map.get("start").toString() + " " + map.get("end").toString());
		try {
			pstmt = con.prepareStatement(query); // 쿼리문 연결
			pstmt.setString(1, map.get("start").toString()); // 시작번호
			pstmt.setString(2, map.get("end").toString());	 // 끝번호
			rs = pstmt.executeQuery(); // 쿼리문 실행 후 결과 표로 받음(4단계)
			
			while(rs.next()) {
				ShowDTO dto = new ShowDTO();
				dto.setShowNo(rs.getString("showNo"));
				dto.setTitle(rs.getString("title"));
				dto.setCategory(rs.getString("category"));
				dto.setPlatform(rs.getString("platform"));
				dto.setDirector(rs.getString("director"));
				dto.setActor(rs.getString("actor"));
				dto.setContents(rs.getString("contents"));
				dto.setMain_jpg(rs.getString("main_jpg"));
				dto.setInfo_jpg(rs.getString("info_jpg"));
				dto.setInfo_day(rs.getString("info_day"));
				// name 필드 null
				
				lists.add(dto); // boardDTO 객체 list 객체에 추가
			} // while 종료
			
			
			
		} catch (SQLException e) {
			System.out.println("BoardDAO.selectList 메서드 오류");
			System.out.println("board 테이블 모든 리스트 출력 오류");
//			e.printStackTrace();
		}
			
		
		
		return lists;
	}

}
   