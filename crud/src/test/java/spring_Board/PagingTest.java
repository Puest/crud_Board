package spring_Board;

import java.util.List;

import javax.inject.Inject;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.withfirst.crud.paging.Criteria;
import com.withfirst.crud.service.BoardService;
import com.withfirst.crud.vo.BoardVO;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/**/root-context.xml")
public class PagingTest {

	@Inject
	private BoardService service;
	private static Logger logger = LoggerFactory.getLogger(PagingTest.class);
	
	@Test
	public void pageListTest() throws Exception {
		Criteria ctr = new Criteria();
		ctr.setCurrentPageNo(1);
		ctr.setRecordsPageNo(10);
		List<BoardVO> boardVO = service.pageList(ctr);
		for(BoardVO vo: boardVO) {
			logger.info(vo.getBoard_no()+":"+vo.getTitle());
		}
	}
}
