package com.show.SV;

import java.sql.Connection;
import java.util.Scanner;

import com.show.DAO.ShowDAO;
import com.show.DTO.MemberDTO;
public class ShowSV {

   public static ShowDAO show = new ShowDAO(); // ShowDAO 호출용 객체

//   public void showMenu() {
//   합칠 때 메인메서드 대신 사용할 메서드   
//   }

   public static void showMenu(MemberDTO loginState, Scanner s, Scanner sL, Connection connection) {
     // MainExam main = new MainExam();
      
      boolean run = true;

      while (run) {
         System.out.println("______________________________________");
         System.out.println();
         System.out.println("1. 검색하기");
         System.out.println("2. 플랫폼 별 작품 살펴보기");
         System.out.println("3. 장르 별 작품 살펴보기");
         System.out.println("4. 이전으로 ");
         System.out.print(">> ");
         int select = s.nextInt();
         switch (select) {
         case 1:
            System.out.println("----------------------------");
            search(loginState, s, sL, connection);
            break;
         case 2:
            System.out.println("-------- 플랫폼 작품 살펴보기 --------");
            platformMenu(loginState, s, sL, connection);
            break;
         case 3:
            System.out.println("-------- 장르별 작품 살펴보기--------");
            categoryMenu(loginState, s, sL, connection);
            break;
         case 4:
            System.out.println("-------- 메인메뉴로 돌아갑니다--------");
            run = false;
            break;
         case 9191:
            System.out.println("-------- 작품등록 --------");
            adminInsert(loginState, s, sL, connection);
            break;
         default:
            System.out.println("올바른 메뉴를 입력해주세요.");
         }
      }
   }

   public static void platformMenu(MemberDTO loginState, Scanner s, Scanner sL, Connection connection) {
      // 플랫폼, 장르별 볼 수 있는 메뉴
      //MainExam main = new MainExam();
      boolean showRun = true;
//      String platform = "platform";
      String platform1 = "platform";
      while (showRun) {
         System.out.println("1. Netflix \n2. Tiving \n3. 전체보기 \n4. 이전");
         System.out.print(">> ");
         int select = s.nextInt();
         switch (select) {
         case 1:
            String platform2 = "%Netflix%";
            show.select(connection, platform1, platform2, s, sL, loginState);
            
            break;
         case 2:
            String platform3 = "%Tiving%";
            show.select(connection, platform1, platform3, s, sL, loginState);
            
            break;
         case 3:
            show.allSelect(connection, s, sL, loginState);
            break;
         case 4:
            System.out.println("메인메뉴로 돌아갑니다.");
            showRun = false;
         }
      }
   }// 서브메뉴, 종류별 장르별 보기

   public static void search(MemberDTO loginState, Scanner s, Scanner sL, Connection connection) {
     // MainExam main = new MainExam();
      System.out.println("🔎 검색어 입력 >>  ");
      String title = s.next();
      String keyword = "%" + title + "%";
      show.search(connection, keyword);
   }

   public static void categoryMenu(MemberDTO loginState, Scanner s, Scanner sL, Connection connection) {
      // 플랫폼, 장르별 볼 수 있는 메뉴
      //MainExam main = new MainExam();
      boolean showRun = true;
      String category = "category";
      while (showRun) {
         System.out.println("1. 로맨스   2. 코미디    3. 액 션   4. 미스터리");
         System.out.println("5. SF     6. 판타지    7. 예능    8. 이전으로 ");
         System.out.print(">> ");
         int select = s.nextInt();
         switch (select) {
         case 1:
            String category1 = "%로맨스%";
            show.select(connection, category, category1, s, sL, loginState);
            
            break;
         case 2:
            String category2 = "%코미디%";
            show.select(connection, category, category2, s, sL, loginState);
            
            break;
         case 3:
            String category3 = "%액션%";
            show.select(connection, category, category3, s, sL, loginState);
            
            break;
         case 4:
            String category4 = "미스터리%";
            show.select(connection, category, category4, s, sL, loginState);
            
            break;
         case 5:
            String category5 = "%SF%";
            show.select(connection, category, category5, s, sL, loginState);
            
            break;
         case 6:
            String category6 = "%판타지%";
            show.select(connection, category, category6, s, sL, loginState);
            
            break;
         case 7:
            String category7 = "%예능%";
            show.select(connection, category, category7, s, sL, loginState);
            
            break;
         case 8:
            System.out.println("이전으로 돌아갑니다.");
            showRun = false;
            break;
         }
      }

   }// 서브메뉴, 종류별 장르별 보기

   public static void adminInsert(MemberDTO loginState, Scanner s, Scanner sL, Connection connection) {
      //MainExam main = new MainExam();
      System.out.print("플랫폼 >> ");
      String platform = s.next();

      System.out.print("장르 >> ");
      String category = s.next();

      System.out.print("감독 >> ");
      String director = s.next();

      System.out.print("배우 >> ");
      String actor = s.next();

      System.out.print("제목 >> ");
      String title = s.next();

      System.out.print("내용 >> ");
      String content = s.next();

      ShowDAO show = new ShowDAO();
      show.insert(connection, platform, category, director, actor, title, content);

   }
}
