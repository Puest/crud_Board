package spring_Board;

import javax.inject.Inject;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.withfirst.crud.dao.MemberDAO;
import com.withfirst.crud.vo.MemberVO;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/**/root-context.xml")
public class MemberDAOTest {

	@Inject
	private MemberDAO memberDAO;

	@Before
	public void insertMemberTest() throws Exception {
		MemberVO memberVO = new MemberVO();
		memberVO.setUsername("Kamo");
		memberVO.setPassword("Kamo1234");
		memberVO.setEmail("Kamo@withfirst.com");

		memberDAO.insertMember(memberVO);
	}

	@Test
	public void selectMembertTest() throws Exception {
		MemberVO memberVO = memberDAO.selectMember("Kamo");
		System.out.println(memberVO.getSeq() + " / " + memberVO.getUsername() + " / " + memberVO.getPassword() + " / "
				+ memberVO.getEmail());
	}

}
