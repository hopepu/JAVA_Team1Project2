package com.show.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

import com.show.DTO.MemberDTO;
import com.show.DTO.ReviewDTO;

public class ReviewDAO {

   public void rwriter(Connection connection, ReviewDTO reviewDTO, MemberDTO lSt) {
      // 리뷰 - 작성하기

      try {
         String sql = "insert into review (rno, rtitle, rnickname, starpoint, review , rdate ) values (re_seq.nextval, 'rtitle','rnickname', ?, ?, sysdate)";

         PreparedStatement preparedStatement = connection.prepareStatement(sql);
//         System.out.println(" ???? ");
         preparedStatement.setString(1, reviewDTO.getStarPoint());
         preparedStatement.setString(2, reviewDTO.getReview());

         System.out.println(reviewDTO.getStarPoint());
         System.out.println(reviewDTO.getReview());

         int result = preparedStatement.executeUpdate();
         System.out.println(result);
         if (result > 0) {
            System.out.println("추가 되었습니다.");
         } else {
            System.out.println("삽입 실패.");
            System.out.println("쿼리문 확인 바랍니다.");

         }

         preparedStatement.close();

      } catch (SQLException e) {
         // TODO Auto-generated catch block
         e.printStackTrace();
      }

   }// 리뷰 - 작성하기 종료

   public void rlist(Connection connection, MemberDTO lSt) {
      // 리뷰 - 보기 종료

      try {
         String sql = "select * from review where rnickname=? order by rno desc";
         PreparedStatement preparedStatement = connection.prepareStatement(sql);
         preparedStatement.setString(1, lSt.getNickName());
         ResultSet resultSet = preparedStatement.executeQuery();

         while (resultSet.next()) {

            System.out.println(resultSet.getString(1));
            System.out.println(resultSet.getString(3));
            System.out.println(resultSet.getString(2));
            System.out.println(resultSet.getInt(4));
            System.out.println(resultSet.getString(5));

         }

      } catch (SQLException e) {
         // TODO Auto-generated catch block
         // e.printStackTrace();
      }

   }// 리뷰 - 보기 종료

   public void  rmodify(Connection connection, MemberDTO lSt, Scanner s, Scanner sL, ReviewDTO box) {
      //리뷰 - 수정 
      // 조건 ? 내가 쓴 글을 수정할란다. (닉네임으로 조건)
      
      try {
         String sql = "update review set review=?, starpoint=? where rnickname=? ";
         PreparedStatement preparedStatement = connection.prepareStatement(sql);
         
         preparedStatement.setString(1, box.getReview());
         preparedStatement.setString(2,box.getStarPoint());
         
         
         int result = preparedStatement.executeUpdate();
         
         if(result > 0 ) {
            System.out.println("수정 되었습니다.");
            
         }else {
            System.out.println("수정 실패");
         
         }
      } catch (SQLException e) {
         // TODO Auto-generated catch block
         //e.printStackTrace();
      }
   }
   public void rdelete(Connection connection, MemberDTO lSt) {
      // 리뷰 - 삭제하기

      
      try {
         String sql = "delete from review where rno=? and rnickname=?";
         
         PreparedStatement preparedStatement = connection.prepareStatement(sql);
         preparedStatement.setInt(1, "rno");
         preparedStatement.setString(2, lSt.getId());
         
         
         
         int result = preparedStatement.executeUpdate();
         
         if(result > 0 ) {
            System.out.println("삭제 완료!");
            
         }else {
            System.out.println("삭제 실패");
            
         }
      } catch (SQLException e) {
         // TODO Auto-generated catch block
         //e.printStackTrace();
      }
      
      
      
   }// 리뷰 - 삭제하기 종료
}
