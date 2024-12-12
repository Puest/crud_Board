package spring_Board;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

import org.junit.Test;

public class ConnectionTest {
	private static final String DRIVER = "com.mysql.cj.jdbc.Driver"; //Connection을 구현한 클래스의 이름
	private static final String URL = "jdbc:mysql://localhost:3306/member?useSSL=false&serverTimezone=GMT"; //mysql 서버 주소
	private static final String USER = "spring"; //계정
	private static final String PW = "spring1234"; // 비밀번호
	
	@Test
	public void testConnection() throws Exception {
		Class.forName(DRIVER);
		
		try(Connection con = DriverManager.getConnection(URL, USER, PW)){
			int num = insert(con, "Luna", "as1234", "luna@google.com");
			System.out.println(num + "개 행 삽입 테스트 완료");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private int insert(Connection con, String username, String password, String email) {
		final String SQL= "INSERT INTO user(username, password, email) VALUES (?,?,?)"; //sql 쿼리
			
		//PreparedStatement에서 해당 SQL을 미리 컴파일함
		try(PreparedStatement pstml = (PreparedStatement) con.prepareStatement(SQL)) {
			pstml.setString(1, username); 
			pstml.setString(2, password); 
			pstml.setString(3, email);
			return pstml.executeUpdate(); //쿼리실행 반환 값 삽입한 행의 개수
		}catch(Exception e){ //예외처리
			e.printStackTrace();
			System.out.println("테이블에 행 삽입 실패");
			return 0;
		}
	}
}
