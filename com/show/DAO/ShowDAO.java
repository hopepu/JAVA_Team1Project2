package com.show.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

import com.show.DTO.MemberDTO;

public class ShowDAO {
   public void insert(Connection conn, String platform, String catogory, String director, String actor, String title,
         String contents) {
      try {
         String sql = "insert into show_tbl(showNo, platform, category, title, director, actor, contents) values (show_seq.nextval, ?, ?, ?, ?, ?, ?) ";
         PreparedStatement prst = conn.prepareStatement(sql);

         int result = prst.executeUpdate();

         if (result > 0) {
            System.out.println(result + " 행이 추가되었습니다.");
         } else {
            System.out.println("데이터 등록 실패");
         }
      } catch (SQLException e) {
         System.out.println("ShowDAO.insert 쿼리문 확인");
      }
   }// insert

   public void select(Connection conn, String blink, String platform2, Scanner s, Scanner sL, MemberDTO loginState) { // blink에 해당하는 것 관련 출력
      try {

         String sql = "select * from show_tbl where " + blink + " like ?";
         PreparedStatement prst = conn.prepareStatement(sql);
//         prst.setString(1, blink); ? -> '?'
         prst.setString(1, platform2);
         ResultSet rs = prst.executeQuery();
         System.out.println(rs.next());
//         ‹›<¦⁾⁽⁽⁾‹›<¦⁾⁽⁽⁾‹›<¦⁾⁽⁽⁾
         while (rs.next()) {
            System.out.print(rs.getInt(1));
            System.out.println(" ¦ " + "‹" + rs.getString(2) + "›");
            System.out.println("    📺 " + rs.getString(4));
         }
         rs.close();
         prst.close();
      } catch (SQLException e) {
         System.out.println("ShowDAO.selectT 쿼리문 확인");
      }
   } // 종류별 출력

   public void allSelect(Connection conn, Scanner s, Scanner sL, MemberDTO loginState) { // 전체출력
      try {
         String sql = "select showNo, platform, category, title from show_tbl";

         PreparedStatement prst = conn.prepareStatement(sql);
         ResultSet rs = prst.executeQuery();

         while (rs.next()) {
            System.out.print(rs.getInt(1) + " ¦ ");
            System.out.println("‹" + rs.getString(2) + " | " + rs.getString(3) + "›");
            System.out.println("    📺 " + rs.getString(4));
         }
      } catch (SQLException e) {
         System.out.println("SQL 문을 확인하세요.");
      }

   }// 전체 출력 메서드 종료 - allSelect

   public void view(Connection conn) {
      String sql = "select * from show_tbl where showNo = ?";

   }// allselect 종료
   
   public void search(Connection conn, String search) {
      try {
         String sql = "select * from show_tbl where platform like ?  or category like ? or title like ? or director like ? or actor like ?";
         PreparedStatement prst = conn.prepareStatement(sql);
         prst.setString(1, search);
         prst.setString(2, search);
         prst.setString(3, search);
         prst.setString(4, search);
         prst.setString(5, search);
         ResultSet rs = prst.executeQuery();
         while(rs.next()) {
            System.out.println("‹" + rs.getString(2) + " | " + rs.getString(3) + "›");
            System.out.println("📺 " + rs.getString(4));
            System.out.println("🎬 " +rs.getString(5));
            System.out.println("🏆 " +rs.getString(6));
            System.out.println(rs.getString(7).substring(0,30));
            System.out.println(rs.getString(7).substring(30,60));
            System.out.println(rs.getString(7).substring(60,90));
            System.out.println(rs.getString(7).substring(90,120));
            System.out.println(rs.getString(7).substring(120,150));
            
         }
      } catch (Exception e) {
//         System.out.println("SQL문을 확인해주세요.");
        // e.printStackTrace();
      }
      
   }// search 메서드 종료(단어검색)
}
   