package com.show.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.show.DTO.MemberDTO;

public class MemberKDAO { // DB - member table 데이터 처리
	/* C-회원가입(insert into) -MDAO */

	/* R-동일 닉네임 검색(회원정보 변경시) */
	public MemberDTO checkNickName(Connection conn, MemberDTO memberDTO) {
		int cnt =0;
		try {
			String sql = "select count(*) as cnt from member where nickName = ?";
			PreparedStatement pps = conn.prepareStatement(sql);
			pps.setString(1, memberDTO.getNickName());

			ResultSet rst = pps.executeQuery();
			while(rst.next()) {
				cnt = rst.getInt("cnt");
			}
			
			boolean use = (cnt == 0) ? true : false;
			memberDTO.setUsability(use);

			rst.close();
			pps.close();
		} catch (SQLException e) {
			System.out.println("sql문오류");
			// e.printStackTrace();
		}
		return memberDTO;
	}// --checkNickName()

	/* R-회원정보 검색(ID찾기) */
	public MemberDTO searchID(Connection conn, MemberDTO memberDTO) {
		try {
			String sql = "select id from member where name = ? and birth = ? and sex = ?";
			PreparedStatement pps = conn.prepareStatement(sql);
			pps.setString(1, memberDTO.getName());
			pps.setString(2, memberDTO.getBirth());
			pps.setString(3, memberDTO.getSex());

			ResultSet rset = pps.executeQuery();

			while (rset.next()) {
				memberDTO.setId(rset.getString("id"));
			}

			rset.close();
			pps.close();
		} catch (SQLException e) {
			System.out.println("해당하는 계정이 없습니다.");
			// e.printStackTrace();
		}

		return memberDTO;

	}// --searchID

	/* R-회원정보 검색(패스워드 찾기 시) */
	public int searhPW(Connection conn, MemberDTO memberDTO) {
		int result = 0;
		try {
			String sql = "select count(*) as cnt from member where id=? and birth=? and phone=?";

			PreparedStatement pps = conn.prepareStatement(sql);
			pps.setString(1, memberDTO.getId());
			pps.setString(2, memberDTO.getBirth());
			pps.setString(3, memberDTO.getpNo());

			ResultSet rset = pps.executeQuery();
			//System.out.println("test id : " + rset.getString("id"));
			while(rset.next()) {
				result = rset.getInt("cnt");
			}
						
			if(result==0) {
				throw new SQLException();
			}else {
				System.out.println("회원정보 검색완료 :" +result);
			}
			
			rset.close();
			pps.close();

//			
		} catch (SQLException e) {
			System.out.println("해당하는 계정이 없습니다.");
			//e.printStackTrace();
			
		}
		return result;
	}// --searhPW()

	/* R-로그인-MDAO */

	/* U-회원정보 변경 */
	public int updateUser(Connection conn, MemberDTO memberDTO) {
		int result = 0;
		try {
			String sql = "update member set nickName = ? , phone=?, mail=? where id=?";
			PreparedStatement pps = conn.prepareStatement(sql);
			pps.setString(1, memberDTO.getNickName());
			pps.setString(2, memberDTO.getpNo());
			pps.setString(3, memberDTO.getMail());

			result = pps.executeUpdate();
			if (result > 0) {
				System.out.println(result + "건의 변경이 완료됐습니다.");
			} else {
				throw new SQLException();
			}
			pps.close();

		} catch (SQLException e) {
			System.out.println("sql문 오류.");
			// e.printStackTrace();
		}
		return result;
	}// --updateUser()

	/* U-패스워드 변경(패스워드 찾기 시) */
	public int updatePW(Connection conn, MemberDTO memberDTO) {
		int result = 0;
		try {
			String sql = "update member set pw=? where id=? and phone=? ";
			PreparedStatement pps = conn.prepareStatement(sql);
			pps.setString(1, memberDTO.getPw());
			pps.setString(2, memberDTO.getId());
			pps.setString(3, memberDTO.getpNo());

			result = pps.executeUpdate();

			if (result > 0) {
				System.out.println(result + "건의 패스워드 변경 완료");
			} else {
				throw new SQLException();
			}

			pps.close();
		} catch (SQLException e) {
			System.out.println("패스워드 업데이트 실패. 관리자에게 문의하세요.");
			 e.printStackTrace();
		}
		return result;
	}

	/* D-회원탈퇴 */
	public int delete(Connection conn, MemberDTO memberDTO) {
		int result = 0;
		try {
			String sql = "delete from member where id=? and pw=?";
			PreparedStatement pps = conn.prepareStatement(sql);
			pps.setString(1, memberDTO.getId());
			pps.setString(2, memberDTO.getPw());

			result = pps.executeUpdate();

			if (result > 0) {
				System.out.println(result + "건의 회원정보 삭제 완료");
			} else {
				throw new SQLException();
			}

			pps.close();
		} catch (SQLException e) {
			System.out.println("회원정보 검색 실패.관리자에게 문의하세요.");
			// e.printStackTrace();
		}

		return result;

	}// --delete()

}