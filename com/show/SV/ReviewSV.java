package com.show.SV;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Scanner;

import com.show.DAO.ReviewDAO;
import com.show.DTO.MemberDTO;
import com.show.DTO.ReviewDTO;

public class ReviewSV {
	
    public static void reviewMenu(MemberDTO loginState, Scanner s, Scanner sL, Connection connection) {
    	
      ReviewSV res = new ReviewSV();

      System.out.println(" 리뷰 ");
      
      System.out.println("1. 리뷰 작성하기");
      
      System.out.println("2. 리뷰 보기");
      
      System.out.println("3. 리뷰 수정하기");
      
      System.out.println("4. 리뷰 삭제하기");
      
      int select = s.nextInt();
      
      switch (select) {
      
      case 1 : 
         System.out.println(" 리뷰 작성 메서드 진입");
         rwriter(s, sL, connection, loginState);
         
         break;
         
      case 2 : 
         System.out.println(" 리뷰 보기 메서드 진입");
         rlist(s, sL, connection, loginState);
         break;
         
      case 3 : 
         System.out.println(" 리뷰 수정 메서드 진입");
         rmodify(s, sL, connection, loginState);
         break;
         
      case 4 : 
         System.out.println(" 리뷰 삭제 메서드 진입");
         rdelete(s, sL, connection, loginState);
         break;   
         
      case 5 : 
         System.out.println("메인화면 돌아가기");
         break;   
         
      default :
         System.out.println("1 ~ 5번 번호만 입력해 주세요");
         
      }
      
      
      
   }
   
   
    public static void rdelete(Scanner sL, Scanner s, Connection connection, MemberDTO loginState) {
      // - 리뷰 삭제하기
      
      
      System.out.println("삭제할 리뷰 번호를 입력 해주세요.");
      System.out.print(">>>");
      int delNo = s.nextInt();
      
      System.out.println("본인 확인을 위해 닉네임을 입력해 주세요");
      System.out.print(">>>");
      String delId = s.next();
      
      // 로그인 스테이트 - 닉네임 동일 확인 후 삭제 기능 추가
      
      
      ReviewDTO rdelete = new ReviewDTO();
      rdelete.setRno(delNo);
      rdelete.setNickName(delId);
      
      ReviewDAO review = new ReviewDAO();
      review.rdelete(connection, loginState, rdelete);
      
      
      
      
      System.out.println(" 정상적으로 삭제가 되었습니다. ");
      
   }


   public static void rmodify(Scanner sL, Scanner s, Connection connection, MemberDTO loginState) {
      // 리뷰 수정 :

         System.out.println(" 리뷰 수정할 내용을 입력 하세요");
         System.out.print(">>>");
         String con = s.next();
         
         System.out.println("수정할 별점을 1~5점까지 입력해주세요.");
         String star = s.next();
       //  별점은 필드를 double로 주는게 어떨까요? 프론트에서 꽉찬별, 반별, 빈별 3종류 할수가 있기 때문에..
         
         ReviewDTO rmodify = new ReviewDTO();
         rmodify.setReview(con);
         rmodify.setStarPoint(star);
         
      // 로그인 스테이트 - 닉네임 동일 확인 후 삭제 기능 추가
         
         ReviewDAO review = new ReviewDAO();
         review.rmodify(connection, loginState, s, sL, rmodify);
         
         
      
      
      
   }



   public static void rlist(Scanner sL, Scanner s, Connection connection, MemberDTO loginState) {
      // 리뷰 보기 방식 : 로그인한 정보랑 제목으로 리뷰 보기
      
      
      System.out.println("1. 내 리뷰 보기");
      System.out.println("2. 작품 리뷰 보기");
      System.out.print(">>> ");
      int select = s.nextInt();
      
      switch (select) {
      
      case 1 :
         System.out.println("내 리뷰 목록 ");
         myReview(sL, s, connection, loginState);
         break;
         
      case 2 :
    	  
         System.out.println("작품 리뷰 목록 ");
         
         break;
         
      default :
         System.out.println("1~2번만 선택해 주세요 ");
         break;
            
         
      }
      
   
   }//리뷰 보기 - 종료





   public static void myReview(Scanner sL, Scanner s, Connection connection, MemberDTO loginState) {
      // 내 리뷰 보기 - 1. 내가 작성한 리뷰보기(닉네임으로 조건)
      
      ReviewDAO review = new ReviewDAO();
      review.rlist(connection, loginState);
      
   
   }// -내 리뷰 보기 종료





   public static void rwriter(Scanner s, Scanner sL, Connection connection, MemberDTO loginState) {
      // -리뷰 작성 메서드
      
      ReviewDAO re = new ReviewDAO();
      
      
      System.out.print("별점(1~5) >> ");
      int star = s.nextInt();
      
      System.out.println("리뷰 작성");
      String review = s.next();
      
    ReviewDTO reviewdto = new ReviewDTO();
    reviewdto.setStarPoint(review);
  
    reviewdto.setReview(review);
    
    re.rwriter(connection, reviewdto, loginState);
    
   }// -리뷰 작성 메서드 종료
   
   
   
   
}
