package com.show.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.show.DTO.MemberDTO;

public class MemberMDAO {
	/* C-회원가입 */
	public int register(Connection conn, MemberDTO memberDTO) {
		// 조인된 뷰로는 두 테이블을 동시수정불가능
		// sql1 : member 테이블에 삽입
		// sql2 : author 테이블에서 번호에 해당하는 권한명 찾아서 member 테이블에 삽입
		int rst1=0;
		int rst2=0;
		try {
			String sql1 = "insert into member values(mem_seq.nextval,?, ?, ?, ?, TO_DATE(?,'YYYY/MM/DD'),?,?,?)";
			String sql2 = "update member set author=(select authorname from author where alevel=?) where id='?' and nickname='?';";

			PreparedStatement pps1 = conn.prepareStatement(sql1);
			pps1.setString(1, memberDTO.getId());
			pps1.setString(2, memberDTO.getPw());
			pps1.setString(3, memberDTO.getName());
			pps1.setString(4, memberDTO.getNickName());
			pps1.setString(5, memberDTO.getBirth());
			pps1.setString(6, memberDTO.getSex());
			pps1.setString(7, memberDTO.getpNo());
			pps1.setString(8, memberDTO.getMail());
			PreparedStatement pps2 = conn.prepareStatement(sql2);
			pps2.setInt(1, memberDTO.getAuthor());
			pps2.setString(2, memberDTO.getId());
			pps2.setString(3, memberDTO.getNickName());
			
			rst1 = pps1.executeUpdate();
			rst2 = pps2.executeUpdate();
			
			if(rst1>0) {
				System.out.println("회원정보 입력성공");
				if(rst2>0) {
					System.out.println("권한정보 입력성공");
				}else {
					System.out.println("권한정보 입력실패");
				}
			}else {
				System.out.println("회원정보 입력실패");
			}

		} catch (SQLException e) {
			System.out.println("sql문 오류, DB입력 실패");
			e.printStackTrace();
		}
		return rst1+rst2;
	}// --register()

	/* R-동일아이디 검사 */
	public MemberDTO checkID(Connection conn, MemberDTO memberDTO) {

		try {
			String sql = "select count(*) as cnt from member where id=?";
			PreparedStatement pps = conn.prepareStatement(sql);
			pps.setString(1, memberDTO.getId());

			ResultSet rset = pps.executeQuery();
			int count = 0; // 동일 아이디 개수 넣을 변수
			while (rset.next()) {
				count = rset.getInt("cnt");
			}

			boolean use = (count == 0) ? true : false;
			memberDTO.setUsability(use); // 사용가능여부를 동일아이디 개수 결과에 따라 넣음

			rset.close();
			pps.close();
		} catch (SQLException e) {
			System.out.println("검색실패. sql문 오류");
			e.printStackTrace();
		}
		return memberDTO;
	}// --checkID()

	/* R-동일닉네임 검사 */
	public MemberDTO checkNickname(Connection conn, MemberDTO memberDTO) {

		try {
			String sql = "select count(*) as cnt from member where nickName =?";
			PreparedStatement pps = conn.prepareStatement(sql);
			pps.setString(1, memberDTO.getNickName());

			ResultSet rset = pps.executeQuery();
			int count = 0; // 동일 닉네임 개수 넣을 변수
			while (rset.next()) {
				count = rset.getInt("cnt");
			}

			boolean use = (count == 0) ? true : false;
			memberDTO.setUsability(use); // 사용가능여부를 동일아이디 개수 결과에 따라 넣음

			rset.close();
			pps.close();
		} catch (SQLException e) {
			System.out.println("검색실패. sql문 오류");
			e.printStackTrace();
		}
		return memberDTO;
	}// --checkID()

	/* R-로그인 */
	public void login(Connection conn, MemberDTO memberDTO) {

	}// --login()

}
