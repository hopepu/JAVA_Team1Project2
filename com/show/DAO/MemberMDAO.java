package com.show.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.show.DTO.MemberDTO;

public class MemberMDAO {
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

      public MemberDTO compare(Connection connection, MemberDTO memberDTO) {
    	  try {
			String sql = "select count(*) as count1 from member where id=?";
			// count(*) : 모든 row의 개수를 count1이란 이름으로 개수를 알려줌
			  PreparedStatement preparedStatement = connection.prepareStatement(sql); 
			  //  PreparedStatement타입의 preparedStatement을 preparedStatement에 conn을 적용
			  preparedStatement.setString(1,memberDTO.getId()); // 멤버DTO에서 받은 ID를 ?에 넣음.
			  ResultSet resultSet = preparedStatement.executeQuery(); // 4단계
			  int newcount=0;
			  while(resultSet.next()){
				  // 내가 숫자로 받을 변수 생성
				   newcount = resultSet.getInt("count1");
			  }
			  
			  if(newcount==0) {
				  memberDTO.setUsability(true);
			  }else {
				  memberDTO.setUsability(false);
			  }
			  
			  resultSet.close();
			  preparedStatement.close();
			  
			  
		} catch (SQLException e) {
			System.out.println("sql문을 확인하세요");
			e.printStackTrace();
		}
    	 return memberDTO; 
    	  
      }
      
      public MemberDTO compare2(Connection con, MemberDTO memberDTO) {
    	  try {
			String sql = "select count(*) as count2 from member where nickName=?";
			  PreparedStatement preparedStatement = con.prepareStatement(sql);
			//  PreparedStatement타입의 preparedStatement을 preparedStatement에 con을 적용
			  preparedStatement.setString(1, memberDTO.getNickName());
			  ResultSet resultSet = preparedStatement.executeQuery();
			  int newcount2=0;
			  while(resultSet.next()) {
				  newcount2 = resultSet.getInt("count2");
				  if(newcount2==0) {
					 memberDTO.setUsability(true);
				  }else {
					 memberDTO.setUsability(false);
				  }
			  }
			  
			  resultSet.close();
			  preparedStatement.close();
			  
		} catch (SQLException e) {
			System.out.println("sql문을 확인하세요");
			e.printStackTrace();
		}
    	  return memberDTO;
      }
	 
      
}
