package spring_Board;

import javax.inject.Inject;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.withfirst.crud.dao.BoardDAO;
import com.withfirst.crud.vo.BoardVO;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/**/root-context.xml")
public class BoardDAOTest {
	
	@Inject
	private BoardDAO boardDAO;
	
	private static final Logger logger = LoggerFactory.getLogger(BoardDAOTest.class);
	private int board_no = 1;
	
	private BoardVO createBoard(String title, String description) throws Exception{
		BoardVO vo = new BoardVO();
		vo.setWriter("David");
		vo.setTitle(title);
		vo.setDescription(description);
		
		return vo;
	}
	
	@Before
	public void createTest() throws Exception {
		boardDAO.create(createBoard("Test One", "This is the first test post it"));
	}
	
	@Test
	public void readTest() throws Exception {
		logger.info(boardDAO.read(board_no).toString());
	}
	
	@Test
	public void updateTest() throws Exception {
		BoardVO vo = createBoard("Edit, Test One", "Edit, This is the first test post it");
		vo.setBoard_no(board_no);
		boardDAO.update(vo);
	}
	
	@Test
	public void deleteTest() throws Exception {
		logger.info(boardDAO.allList().toString());
		boardDAO.delete(board_no);
	}
	
	
	
	
	
	
	
}
