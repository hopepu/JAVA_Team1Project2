package DAO;

import java.sql.SQLException;

import javax.servlet.ServletContext;

import DTO.MemberDTO;
import Utils.JDBConnect;

public class MemberDAO extends JDBConnect {
	// 기본생성자
	public MemberDAO() {
		super();//1,2단계(dbconnection pool 연결)
	}

	
	// 메서드
	public MemberDTO loginUser(MemberDTO memberDTO) {
		MemberDTO loginMem = new MemberDTO();// 빈객체 생성
		int result = 0; // count 결과 받을 정수 변수
		// 1차 회원가입여부 확인
		try {
			String sql1 = "select count(*) as cnt from member where id=? and pw=?";
			pstmt = con.prepareStatement(sql1);
			pstmt.setString(1, memberDTO.getId());
			pstmt.setString(2, memberDTO.getPw());
			rs = pstmt.executeQuery();
			rs.next();
			result = rs.getInt("cnt");
			//System.out.println("test : id - " + memberDTO.getId());
			//System.out.println("test : pw - " + memberDTO.getPw());
			//System.out.println("test : result - " + result);
			//System.out.println("test : cnt - " + rset1.getInt("cnt"));

			int select = (result != 0) ? 1 : 2;
			//System.out.println("test : select - " + select);
			switch (select) {
			case 1: // 회원정보가 있는 경우(검증완료) 정보를 가져온다.
				String sql2 = "select * from member where id=? and pw=?";
				pstmt = con.prepareStatement(sql2);
				pstmt.setString(1, memberDTO.getId());
				pstmt.setString(2, memberDTO.getPw());
				rs = pstmt.executeQuery();
				while (rs.next()) {
					loginMem.setId(rs.getString("id"));
					loginMem.setName(rs.getString("name"));
					//loginMem.setRegdate(rset2.getString("regidate"));
					loginMem.setLoginStatus(true);
				} // --while()
				break;
			case 2:
				loginMem = null;
			default:
				loginMem = null;
				throw new SQLException();
			}// --select()

		} catch (SQLException e) {
			System.out.println("loginUser() 3, 4단계 오류");
			e.printStackTrace();
		}
		return loginMem;
	}
	
	public MemberDAO(ServletContext application) {  
		super(application);  // 부모 생성자에게 전달 하여 1,2단계
	}  
	
	// 매서드
	public MemberDTO memLogin(String id, String pw) {
		MemberDTO memberDTO = new MemberDTO();  // 빈객체 생성
		String sql = "select * from member where id=? and pw=?";  // 3단계  쿼리문 생성
		
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, id);
			pstmt.setString(2, pw);
			rs = pstmt.executeQuery();
			
			if(rs.next()) { // resultSet 표의 값이 있니?!!
				memberDTO.setId(rs.getString("id"));
				memberDTO.setPw(rs.getString("pw"));
				memberDTO.setName(rs.getString("name"));
				memberDTO.setNickName(rs.getString("nickName"));
				memberDTO.setBirth(rs.getString("birth"));
				memberDTO.setSex(rs.getString("sex"));
				memberDTO.setpNo(rs.getString("phone"));
				memberDTO.setMail(rs.getString("mail"));
			}
		} catch (SQLException e) {
			System.out.println("MemberDAO.memLogin메서드 오류발생");
			e.printStackTrace();
		}
		
		
		
		
		
		return memberDTO;
	}

	public MemberDTO searchMember(String id, String nickname) {
		MemberDTO memberDTO = new MemberDTO();  // 빈객체 생성
		String sql = "select * from member where id=? and nickName=?";  // 3단계  쿼리문 생성
		
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, id);
			pstmt.setString(2, nickname);
			rs = pstmt.executeQuery();
			
			if(rs.next()) { // resultSet 표의 값이 있니?!!
				memberDTO.setId(rs.getString("id"));
				memberDTO.setPw(rs.getString("pw"));
				memberDTO.setName(rs.getString("name"));
				memberDTO.setNickName(rs.getString("nickName"));
				memberDTO.setBirth(rs.getString("birth"));
				memberDTO.setSex(rs.getString("sex"));
				memberDTO.setpNo(rs.getString("phone"));
				memberDTO.setMail(rs.getString("mail"));
			}
		} catch (SQLException e) {
			System.out.println("MemberDAO.memLogin메서드 오류발생");
			e.printStackTrace();
		}
		
		
		
		
		
		return memberDTO;
	}
	
	public int modifyMember(MemberDTO dto) {
		int result = 0;
		System.out.println(dto.toString());

		String sql="update member set mail=?, pw=?, phone=?, name=?, nickName=? where id=?";
		
		try {
			pstmt=con.prepareStatement(sql);
			pstmt.setString(1, dto.getMail());
			pstmt.setString(2, dto.getPw());
			pstmt.setString(3, dto.getpNo());
			pstmt.setString(4, dto.getName());
			pstmt.setString(5, dto.getNickName());
			pstmt.setString(6, dto.getId());
			
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			System.out.println("MemberDAO.modifyMember메서드 오류발생");
			e.printStackTrace();
		}
		return result;
	}
	
	public int delete(MemberDTO deleteDTO) {
		int result = 0;
		String sql = "delete from member where id = ?";
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, deleteDTO.getId());
			
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			System.out.println("MemberDAO.delete()메서드 오류(3단계)");
			e.printStackTrace();
		}
		
		return result;
	}
	
	public int register(MemberDTO dto) {
		int result = 0;
		String sql="insert into member (mno, id, pw, name, nickName, birth, sex, phone, mail, ath) VALUES (member_seq.nextval, ?, ?, ?, ?, TO_DATE(?,'YYYY/MM/DD'),?,?,?,1)";
		
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, dto.getId());
			pstmt.setString(2, dto.getPw());
			pstmt.setString(3, dto.getName());
			pstmt.setString(4, dto.getNickName());
			pstmt.setString(5, dto.getBirth());
			pstmt.setString(6, dto.getSex());
			pstmt.setString(7, dto.getpNo());
			pstmt.setString(8, dto.getMail());
			
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			System.out.println("MemberDAO.register()메서드 오류(3단계)");
			e.printStackTrace();
		}
		
		return result;
	}

}
