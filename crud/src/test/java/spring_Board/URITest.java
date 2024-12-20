package spring_Board;

import static org.junit.Assert.assertEquals;

import javax.inject.Inject;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import com.withfirst.crud.paging.Criteria;
import com.withfirst.crud.service.BoardService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/**/root-context.xml")
public class URITest {
	
	private static Logger logger = LoggerFactory.getLogger(totalTest.class);
	
	@Test
	public void URITest() throws Exception {
		int page = 5;
		int totalPage = 10;
		
		UriComponents uriComponents = UriComponentsBuilder.newInstance()
				.path("/board/pageList")
				.queryParam("page", page)
				.queryParam("totalPage", totalPage)
				.build();
		
		String uri = "/board/pageList?page=" + page + "&totalPage="+ totalPage;
		
		logger.info(uri);
		logger.info(uriComponents.toString());
			
		assertEquals(uri, uriComponents.toString());
	}
}
