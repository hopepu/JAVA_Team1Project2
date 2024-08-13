package DAO;

import java.sql.SQLException;

import DTO.MemberDTO;
import Utils.JDBConnect;


public class MemberKDAO extends JDBConnect {

	// 생성자(기본)
	public MemberKDAO() {
		super();
	}

	// 메서드
	/* 아이디찾기 */
	public MemberDTO searchID(MemberDTO memberDTO) {
		String query = "select id from member where name = ? and birth = ? and phone = ?";
		try {
			pstmt = con.prepareStatement(query);
			pstmt.setString(1, memberDTO.getName());
			pstmt.setString(2, memberDTO.getBirth());
			pstmt.setString(3, memberDTO.getpNo());
			
			rs=pstmt.executeQuery();
			
			if(rs.next()) {
				memberDTO.setId(rs.getString("id"));
				memberDTO.setUsability(true);
			}else {
				memberDTO.setUsability(false);
			}
		} catch (SQLException e) {
			System.out.println("MemberKDAO.searchID()메서드 오류");
			e.printStackTrace();
		}

		return memberDTO;
	}//--searchID
	
	/* 패스워드찾기(회원검증) */
	public int searchPW(MemberDTO memberDTO) {
		int result = 0;
		
		String query = "select count(*) as cnt from member where id=? and birth=? and phone=?";
		
		try {
			pstmt = con.prepareStatement(query);
			pstmt.setString(1, memberDTO.getId());
			pstmt.setString(2, memberDTO.getBirth());
			pstmt.setString(3, memberDTO.getpNo());
			
			rs = pstmt.executeQuery();
			rs.next();
			result = rs.getInt("cnt");
			
			
		} catch (SQLException e) {
			System.out.println("MemberKDAO.searchPW()메서드 예외발생");
			e.printStackTrace();
		}
		
		return result;
	}
	
	/* 회원정보변경-정보가져오기 */
	public MemberDTO searchMem(MemberDTO memberDTO) {
		MemberDTO findMem = new MemberDTO();
		
		String query = "select * from member where id =? and nickName=?";
		
		try {
			pstmt= con.prepareStatement(query);
			pstmt.setString(1, memberDTO.getId());
			pstmt.setString(2, memberDTO.getNickName());
			
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				findMem.setId(rs.getString("id"));
				findMem.setPw(rs.getString("pw"));
				findMem.setName(rs.getString("name"));
				findMem.setNickName(rs.getString("nickName"));
				findMem.setBirth(rs.getString("birth"));
				findMem.setSex(rs.getString("sex"));
				findMem.setpNo(rs.getString("phone"));
				findMem.setMail(rs.getString("mail"));				
			}
			
		} catch (SQLException e) {
			System.out.println("MemberKDAO.searchMem()메서드 예외발생");
			//e.printStackTrace();
		}		
		return findMem;
	}
	
	/* 회원정보변경 - 변경정보 업데이트*/
	public int updateMem(MemberDTO memberDTO) {
		int result = 0;
		String query = "update member set pw=?, nickName=?, phone=?, mail=? where id=? and name=?";
		
		try {
			pstmt = con.prepareStatement(query);
			pstmt.setString(1, memberDTO.getPw());
			pstmt.setString(2, memberDTO.getNickName());
			pstmt.setString(3, memberDTO.getpNo());
			pstmt.setString(4, memberDTO.getMail());
			pstmt.setString(5, memberDTO.getId());
			pstmt.setString(6, memberDTO.getName());
			
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			System.out.println("MemberKDAO.updateMem()메서드 예외발생");
			e.printStackTrace();
		}
			return result;
	}
	
	/* 패스워드찾기(임시비번 업데이트) */
	public void inserttempPW(MemberDTO memberDTO) {
		int result = 0;
		
		String query = "update member set pw = ? where id=? and and phone=?";
		try {
			pstmt = con.prepareStatement(query);
			pstmt.setString(1, memberDTO.getPw());
			pstmt.setString(2, memberDTO.getId());
			pstmt.setString(3, memberDTO.getpNo());

			result = pstmt.executeUpdate();
			
			if (result > 0) {
				System.out.println(result + "건의 패스워드 변경 완료");
			} else {
				throw new SQLException();
			}
			
		} catch (SQLException e) {
			System.out.println("MemberKDAO.inserttempPW()메서드 예외발생");
			e.printStackTrace();
		}
		
	}
	
	/* id 중복검사 */
 	public int checkID(String user_id) {
		int result = 0;
		
		String sql ="select count(id) from member where id=?";
		
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, user_id);
			
			rs = pstmt.executeQuery();
			rs.next();
			result = rs.getInt(1);
		} catch (SQLException e) {
			System.out.println("MemberKDAO.checkID()메서드 예외발생");
			e.printStackTrace();
		}
		
		return result;
	}//--checkID()
	
	/* 닉네임 중복검사 */
	public int checkNick(String user_nick) {
		int result = 0;
		
		String sql ="select count(nickName) from member where nickName=?";
		
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, user_nick);
			
			rs = pstmt.executeQuery();
			rs.next();
			result = rs.getInt(1);
		} catch (SQLException e) {
			System.out.println("MemberKDAO.checkNick()메서드 예외발생");
			e.printStackTrace();
		}
		
		return result;
	}//--checkNick()

	/* C - 회원가입 */
	public int register(MemberDTO memberDTO) {
		int result = 0;
		
		String query = "insert into member (mno, id, pw, name, nickName, birth, sex, phone, mail, ath) values (member_seq.nextval, ?, ?, ?, ?, TO_DATE(?,'YYYY/MM/DD'), ?, ?, ?, ?)";
		System.out.println("test 오류지점1" );
		try {
			pstmt = con.prepareStatement(query);
			pstmt.setString(1, memberDTO.getId());
			pstmt.setString(2, memberDTO.getPw());
			pstmt.setString(3, memberDTO.getName());
			pstmt.setString(4, memberDTO.getNickName());
			pstmt.setString(5, memberDTO.getBirth());
			pstmt.setString(6, memberDTO.getSex());
			pstmt.setString(7, memberDTO.getpNo());
			pstmt.setString(8, memberDTO.getMail());
			pstmt.setInt(9, memberDTO.getAuthor());
			
			result = pstmt.executeUpdate();
			System.out.println("test Author: "+memberDTO.getAuthor());
			System.out.println("test result: "+result);
			
		} catch (SQLException e) {
			System.out.println("MemberKDAO.register() 메서드 예외발생");
			System.out.println("쿼리문 확인");
			e.printStackTrace();
		}
		
		return result;
	}
	
	/* 로그인-회원검증 */
	public int loginVal(MemberDTO memberDTO) {
		int result = 0;
		String query = "select count(*) as cnt from member where id =? and pw=?";
		try {
			pstmt = con.prepareStatement(query);
			pstmt.setString(1, memberDTO.getId());
			pstmt.setString(2, memberDTO.getPw());
			
			rs = pstmt.executeQuery();
			rs.next();
			
			result = rs.getInt("cnt");
		} catch (SQLException e) {
			System.out.println("MemberKDAO.loginVal() 메서드 예외발생");
			//e.printStackTrace();
		}
		
		return result;
	}
	
	/* 로그인-정보가져오기 */
	public MemberDTO login(MemberDTO memberDTO) {
		MemberDTO loginMem = new MemberDTO();
		
		String query = "select * from member where id =? and pw = ?";
		
		try {
			pstmt = con.prepareStatement(query);
			pstmt.setString(1, memberDTO.getId());
			pstmt.setString(2, memberDTO.getPw());
			
			rs = pstmt.executeQuery();
			while(rs.next()) {
				loginMem.setId(rs.getString("id"));
				loginMem.setPw(rs.getString("pw"));
				loginMem.setName(rs.getString("name"));
				loginMem.setNickName(rs.getString("nickName"));
				loginMem.setBirth(rs.getString("birth"));
				loginMem.setSex(rs.getString("sex"));
				loginMem.setpNo(rs.getString("phone"));
				loginMem.setMail(rs.getString("mail"));
				loginMem.setLoginStatus(true);
			}
			
		} catch (SQLException e) {
			System.out.println("MemberKDAO.login() 메서드 예외발생");
			e.printStackTrace();
		}
		
		return loginMem;
	}
	
	/* D-회원탈퇴 */
	public void delete(MemberDTO memberDTO) {
		int result = 0;
		try {
			String query = "delete from member where id=? and pw=?";
			pstmt = con.prepareStatement(query);
			pstmt.setString(1, memberDTO.getId());
			pstmt.setString(2, memberDTO.getPw());

			result = pstmt.executeUpdate();

			if (result > 0) {
				System.out.println(result + "건의 회원정보 삭제 완료");
			} else {
				throw new SQLException();
			}

			pstmt.close();
		} catch (SQLException e) {
			System.out.println("회원정보 검색 실패.관리자에게 문의하세요.");
			// e.printStackTrace();
		}

	}// --delete()
}
