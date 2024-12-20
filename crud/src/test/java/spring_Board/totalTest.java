package spring_Board;

import javax.inject.Inject;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.withfirst.crud.paging.Criteria;
import com.withfirst.crud.service.BoardService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/**/root-context.xml")
public class totalTest {
	
	@Inject
	private BoardService service;
	
	private static Logger logger = LoggerFactory.getLogger(totalTest.class);
	
	@Test
	public void getTotalCountTest() throws Exception {
		Criteria ctr = new Criteria();
		Integer totalCount = service.totalCount(ctr);
		logger.info("totalCount: "+totalCount.toString());
	}
}
