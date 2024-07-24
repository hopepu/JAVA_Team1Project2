package com.show.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.show.DTO.MemberDTO;

public class MemberMDAO {
<<<<<<< HEAD
   // 회원 DB에 대한 C()회원가입), R(로그인), U(회원정보수정), D(회원탈퇴)
   
      public MemberMDAO() {
          
      } // 기본 생성자
      
      
//      public MemberDAO(Connection connection) {
//         // 커스텀 생성자
//      }

      public void join(Connection connection, MemberDTO join) { // 회원가입 처리
         
         
         try {
            String sql = "insert into member (mno, id, pw, name, nickname, birth, sex, phone, mail)" +
                     "VALUES (mem_seq.nextval, ?, ?, ?, ?, TO_DATE(?, 'yyyy/MM/dd'), ?, ?, ?)";
            MemberDTO loginState1 = new MemberDTO(); // 회원가입실패시 돌려줄 빈객체
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, join.getId()); // id 입력받음
            preparedStatement.setString(2, join.getPw());
            preparedStatement.setString(3, join.getName());
            preparedStatement.setString(4, join.getNickName());
            preparedStatement.setString(5, join.getBirth());
            preparedStatement.setString(6, join.getSex());
            preparedStatement.setString(7, join.getpNo());
            preparedStatement.setString(8, join.getMail());
            
            int result = preparedStatement.executeUpdate();
            if(result>0) {
            	System.out.println("회원가입에 성공하셨습니다.");
            } else {
            	System.out.println("중복된 정보가 있습니다.");
            }
            
            
//            ResultSet resultSet = preparedStatement.executeQuery();
//            while(resultSet.next()) {
//               loginState1.setMno(resultSet.getInt("mno"));
//               loginState1.setId(resultSet.getString("id"));
//               loginState1.setPw(resultSet.getString("pw"));
//               loginState1.setNickName(resultSet.getString("nickname"));
//               loginState1.setBirth(resultSet.getString("birth"));
//               loginState1.setSex(resultSet.getString("sex"));
//               loginState1.setpNo(resultSet.getString("phono"));
//               loginState1.setMail(resultSet.getString("mail"));
//               System.out.println(loginState1.getId()+"님 가입을 환영합니다.");
//               
//               // resultSEt 표에 있는 정보를 MemberDTO 객체에 넣음
//               
//            } //while문 종료
//            
//            resultSet.close();
            preparedStatement.close();
            
         } catch (SQLException e) {
            System.out.println("동일한 id, 닉네임, 전화번호, 이메일이 존재합니다.");
            e.printStackTrace();
         }
         
         //return loginState;
      
      }
      public MemberDTO login(Connection connection, MemberDTO loginState, MemberDTO loginDTO) { // 로그인 처리
         
         
         try {
            String sql = "select * from member where id=? and pw=?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, loginDTO.getId());
            //  service에서 받은 id가 첫번째 ? 에 적용
            preparedStatement.setString(2, loginDTO.getPw());
            //  service에서 받은 pw가 두번째 ? 에 적용
            ResultSet resultSet = preparedStatement.executeQuery();
            // 위에서 만든 쿼리문을 실행하고 결과를 resultSet표로 받는다.
            
            while(resultSet.next()) {
               loginState.setMno(resultSet.getInt("mno"));
               loginState.setId(resultSet.getString("id"));
               loginState.setPw(resultSet.getString("pw"));
               // resultSEt 표에 있는 정보를 MemberDTO 객체에 넣음
               System.out.println(loginState.getId()+"님 환영합니다!");
               
            } //while문 종료
            
            resultSet.close();
            preparedStatement.close();
                              
            
         } catch (SQLException e) {
            System.out.println("찾는 id와 pw가 없습니다.");
            System.out.println("관리자 : sql문을 확인하세요");
            System.out.println("회원 : id와 pw를 확인하세요");
            e.printStackTrace();
         }
         
         return loginState;  // 로그인 완성용 객체
      }
            
      public void update() { // 회원 정보 수정
         
      }
      
      public void delete() { // 회원 탈퇴
         
      }


	 
      
=======
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

>>>>>>> origin/kjs
}
