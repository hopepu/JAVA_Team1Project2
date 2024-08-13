package DAO;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;
import java.util.Vector;
import javax.servlet.ServletContext;
import DTO.ReviewDTO;
import Utils.JDBConnect;

public class ReviewDAO extends JDBConnect {

	public ReviewDAO(ServletContext application) {
		super(application);
	}

	public int selectCount(String field, String data) {

		int totalCount = 0 ; //리턴값

		//3단계 : 쿼리문
		String query = "select count(*) from review where "+ field+"=?";


		//4단계 : 쿼리문 실행
		try {
			pstmt = con.prepareStatement(query); // 쿼리문 연결
			pstmt.setString(1,data); // 쿼리문을 실행하여 결과를 표로 받음.
			rs = pstmt.executeQuery();
			rs.next(); 
			totalCount = rs.getInt(1); // 첫번째 컬럼 값을 가져옴.
			System.out.println("totalCount : " + totalCount); // 궁금하면 찍어봐~

		} catch (SQLException e) {
			System.out.println("BoardDAO.selectCount()메서드 오류");
			System.out.println("게시물 개수를 구하는 오류 발생");
			e.printStackTrace();
		}


		return totalCount;
	}
	
	
	
	public int rwriter(ReviewDTO DTO) {// 리뷰 - 작성하기
		int result = 0;

		try {
			String query = "insert into review (rno, rtitle, rnickname, starpoint, review , rdate ) values (re_seq.nextval, ?,?, ?, ?, sysdate)";
			// 인파라미터 값 : 별점, 리뷰

			pstmt = con.prepareStatement(query);
			pstmt.setString(1, DTO.getRtitle());
			pstmt.setString(2, DTO.getRnickName());
			pstmt.setString(3, DTO.getStarPoint());
			pstmt.setString(4, DTO.getReview());

			result = pstmt.executeUpdate();

		} catch (SQLException e) {
			System.out.println("ReviewDAO.rwriter()메서드 오류");
			System.out.println("쿼리문을 확인하세요");

			e.printStackTrace();
		}
		return result;

	}// 리뷰 - 작성하기 메서드 종료

	public List<ReviewDTO> reviewList(String field, String data) {
		List<ReviewDTO> listReviewDTO = new Vector<ReviewDTO>();
		// 리뷰 - 보기 시작 메서드

		String query = "select * from review where "+field+"=? order by rno desc";

		try {
			pstmt = con.prepareStatement(query);
			pstmt.setString(1, data);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				ReviewDTO reviewDTO = new ReviewDTO();
				
				reviewDTO.setRnickName(rs.getString("rnickname"));
				reviewDTO.setRno(rs.getInt("rno"));
				reviewDTO.setRtitle(rs.getString("rtitle"));
				reviewDTO.setReview(rs.getString("review"));
				reviewDTO.setStarPoint(rs.getString("starpoint"));
				reviewDTO.setRdate(rs.getDate("rdate"));
				System.out.println(reviewDTO.toString());

				// 번호, 제목, 내용, 별점
				listReviewDTO.add(reviewDTO);
			}

		} catch (SQLException e) {
			System.out.println("ReviewDAO.reviewList()메서드 오류");
			System.out.println("쿼리문을 확인하세요");
			e.printStackTrace();
		}
		return listReviewDTO;

	}// 리뷰 - 보기 메서드 종료
	public ReviewDTO reviewListD(String rNo) {
		// 리뷰 - 보기 시작 메서드
		
		ReviewDTO reviewDTO = new ReviewDTO();
		String query = "select * from review where rno=? order by rno desc";

		try {
			pstmt = con.prepareStatement(query);
			pstmt.setString(1, rNo);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				
				
				reviewDTO.setRnickName(rs.getString("rnickname"));
				reviewDTO.setRno(rs.getInt("rno"));
				reviewDTO.setRtitle(rs.getString("rtitle"));
				reviewDTO.setReview(rs.getString("review"));
				reviewDTO.setStarPoint(rs.getString("starpoint"));
				reviewDTO.setRdate(rs.getDate("rdate"));
				System.out.println(reviewDTO.toString());

				// 번호, 제목, 내용, 별점
			}

		} catch (SQLException e) {
			System.out.println("ReviewDAO.reviewList()메서드 오류");
			System.out.println("쿼리문을 확인하세요");
			e.printStackTrace();
		}
		return reviewDTO;
		
		

	}// 리뷰 - 보기 메서드 종료
	
	public int editReview(ReviewDTO reviewDTO) {// 리뷰 - 수정하기
		// 조건 ? 내가 쓴 글을 수정할란다. (닉네임으로 조건)
		int result = 0;

		String query = "update review set review=?, starpoint=? where rno=? ";
		// 인파라미터 : 리뷰, 별점/ 쪼껀 = 닉네임

		try {
			pstmt = con.prepareStatement(query);
			pstmt.setString(1, reviewDTO.getReview());
			pstmt.setString(2, reviewDTO.getStarPoint());
			pstmt.setInt(3, reviewDTO.getRno());
			result = pstmt.executeUpdate();

		} catch (SQLException e) {
			System.out.println("ReviewDAO.editReview()메서드 오류");
			System.out.println("쿼리문을 확인하세요");
			e.printStackTrace();
		}
		return result;
	}// 리뷰 - 수정하기 메서드 종료

	public int rdelete(String rNo) {// 리뷰 - 삭제하기
		int result = 0;

		String query = "delete from review where rno=?";
		// 인파라미터 값은 번호와 닉네임
		try {
			pstmt = con.prepareStatement(query);
			pstmt.setString(1, rNo);
			result = pstmt.executeUpdate();

		} catch (SQLException e) {
			System.out.println("ReviewDAO.rdelete()메서드 오류");
			System.out.println("쿼리문을 확인하세요");
			e.printStackTrace();
		}
		return result;
	}// 리뷰 - 삭제하기 종료
	
}