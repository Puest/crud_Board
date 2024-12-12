package spring_Board;

import java.sql.Connection;

import javax.inject.Inject;
import javax.sql.DataSource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/**/root-context.xml")
public class DataSourceTest {
	@Inject
	private DataSource data;
	
	@Test
	public void testConnection() throws Exception {
		try(Connection con = data.getConnection()) {
				System.out.println(con);
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
}
