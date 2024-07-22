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

      public MemberDTO register(Connection connection, MemberDTO loginState) { // 회원가입 처리
         
         
         try {
            String sql = "insert into member (mno, id, pw, name, nickname, birth, sex, phone, mail, author)" +
                     "VALUES (mem_seq.nextval, id=?, pw=?, name=?, nickname=?, birth=?, sex=?,phone=?',mail=?)";
            MemberDTO loginState1 = new MemberDTO(); // 회원가입의 정보 입력용 빈객체
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, loginState1.getId()); // id 입력받음
            preparedStatement.setString(2, loginState1.getPw());
            preparedStatement.setString(3, loginState1.getName());
            preparedStatement.setString(4, loginState1.getNickName());
            preparedStatement.setString(5, loginState1.getBirth());
            preparedStatement.setString(6, loginState1.getSex());
            preparedStatement.setString(7, loginState1.getpNo());
            preparedStatement.setString(8, loginState1.getMail());
            
            ResultSet resultSet = preparedStatement.executeQuery();
            while(resultSet.next()) {
               loginState1.setMno(resultSet.getInt("mno"));
               loginState1.setId(resultSet.getString("id"));
               loginState1.setPw(resultSet.getString("pw"));
               loginState1.setNickName(resultSet.getString("nickname"));
               loginState1.setBirth(resultSet.getString("birth"));
               loginState1.setSex(resultSet.getString("sex"));
               loginState1.setpNo(resultSet.getString("pNo"));
               loginState1.setMail(resultSet.getString("mail"));
               
               // resultSEt 표에 있는 정보를 MemberDTO 객체에 넣음
               
            } //while문 종료
            
            resultSet.close();
            preparedStatement.close();
            
         } catch (SQLException e) {
            System.out.println("동일한 id, 닉네임, 전화번호, 이메일이 존재합니다.");
            e.printStackTrace();
         }
         
         return loginState;
      
      }
      public MemberDTO login(Connection connection, MemberDTO loginState) { // 로그인 처리
         
         
         MemberDTO loginDTO = new MemberDTO();  // 리턴용 빈객체
         try {
            String sql = "select mno, mid, mpw, mdate from member where mid=? and mpw=?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, loginDTO.getId());
            //  service에서 받은 id가 첫번째 ? 에 적용
            preparedStatement.setString(2, loginDTO.getPw());
            //  service에서 받은 pw가 두번째 ? 에 적용
            ResultSet resultSet = preparedStatement.executeQuery();
            // 위에서 만든 쿼리문을 실행하고 결과를 resultSet표로 받는다.
            
            while(resultSet.next()) {
               loginDTO.setMno(resultSet.getInt("mno"));
               loginDTO.setId(resultSet.getString("id"));
               loginDTO.setPw(resultSet.getString("pw"));
               // resultSEt 표에 있는 정보를 MemberDTO 객체에 넣음
               
            } //while문 종료
            
            resultSet.close();
            preparedStatement.close();
                              
            
         } catch (SQLException e) {
            System.out.println("찾는 id와 pw가 없습니다.");
            System.out.println("관리자 : sql문을 확인하세요");
            System.out.println("회원 : id와 pw를 확인하세요");
            e.printStackTrace();
         }
         
         return loginDTO;  // 로그인 완성용 객체
      }
            
      public void update() { // 회원 정보 수정
         
      }
      
      public void delete() { // 회원 탈퇴
         
      }
      
}
