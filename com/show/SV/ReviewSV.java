package com.show.SV;
import java.util.ArrayList;
import java.util.Scanner;

import com.show.DTO.MemberDTO;
import com.show.DTO.ReviewDTO;

public class ReviewSV {
   // 리뷰 서비스 메뉴

   public void reviewMenu() {
   }
   
   
   public static void writeReview(Scanner s, Scanner sL, ArrayList<ReviewDTO>reviewDTOs, MemberDTO lst) {
      
      ReviewDTO reviewAdd = new ReviewDTO();
      
       System.out.println(lst.getNickName()); 
       System.out.print("제목 >> ");
       reviewAdd.setTitle(sL.nextLine());
       System.out.print("별점(1~5점) >>  ");
       reviewAdd.setStarPoint(s.next());
       System.out.print("리뷰 >> ");
       reviewAdd.setReview(sL.nextLine());
       
       reviewDTOs.add(new ReviewDTO (reviewAdd.getTitle(),lst.getNickName(), reviewAdd.getStarPoint(), reviewAdd.getReview()));
       
       System.out.println(lst.getNickName() + "   "+reviewAdd.getStarPoint());
       System.out.println(reviewAdd.getReview());
   }

}
