package spring_Board;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/**/root-context.xml")
public class MyBatisTest {

	@Inject
	private SqlSessionFactory sqlSessionFactory;

	@Test
	public void sqlTest() {
		System.out.println("sqlSessionFactory: " + sqlSessionFactory);
	}

	@Test
	public void sessionTest() throws Exception {
		try (SqlSession sqlSession = sqlSessionFactory.openSession()) {
			System.out.println(sqlSession);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
