package Utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.ServletContext;

public class JDBConnect {
	// 데이터베이스와 자바를 연결하는 객체 클래스
	// 단점 : url, id, pw가 변경되면 코드 수정  후 컴파일을 다시하고 배포해야 함!!!
	// 1단계 : 드라이버
	// 2단계 : url, id, pw
	// 3단계 : 쿼리생성
	// 4단계 : 쿼리실행 결과 출력
	// 5단계 : close();
	
	// 필드
	public Connection con;			// 연결자
	public Statement stmt;				// 정적쿼리문(모든 리스트 출력)
	public PreparedStatement pstmt;	// 동적쿼리문(?로 인파라미터 처리)
	public ResultSet rs;					// select의 결과를 표로 출력
	
	
	public JDBConnect() {  
		
		try {
			Class.forName("oracle.jdbc.OracleDriver");  // 1단계
			System.out.println("JDBConnect 기본생성자 1단계 성공");
			String url = "jdbc:oracle:thin:@jhj1395.synology.me:21521:xe";
			String id = "PM";
			String pw = "PMPMPM";
			con = DriverManager.getConnection(url, id, pw); // 2단계
			System.out.println("JDBConnect 기본생성자 2단계 성공");
		} catch (ClassNotFoundException | SQLException e) {
			System.out.println(" 1단계, 2단계, 3단계를 확인하세요");
			e.printStackTrace();
		}
	}
	

	
	
	
	// 커스텀 생성자 - application을 활용
	public JDBConnect(ServletContext application) {
		
		String driver = application.getInitParameter("OracleDriver");
		String url = application.getInitParameter("OracleURL");
		String id = application.getInitParameter("Oracle_id");
		String pw = application.getInitParameter("Oracle_pwd");
		
		try {
			Class.forName(driver);
			System.out.println("커스텀 생성자2로 1단계 성공");
			con = DriverManager.getConnection(url, id, pw);
			System.out.println("커스텀 생성자2로 2단계 성공");
		} catch (ClassNotFoundException | SQLException e) {
			System.out.println("커스텀 생성자2로 1,2,3단계 실패!!");
			e.printStackTrace();
		}
		
	}
	
	
	
	
	// 메서드
	public void close() {
		
		try {
			if(rs != null) rs.close();  // resultSet이 null이 아니면 close() 하라
			if(pstmt != null) pstmt.close();
			if(stmt != null) stmt.close();
			if(con != null) con.close();
			System.out.println("JDBConnect 생성자 5단계 성공");
		} catch (SQLException e) {
			System.out.println("정상으로 close() 되지 않습니다.");
			System.exit(0);
			e.printStackTrace();
		}
		
	}
	
	
}