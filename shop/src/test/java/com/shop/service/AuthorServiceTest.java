package com.shop.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.shop.model.AuthorVO;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/root-context.xml")
public class AuthorServiceTest {
	@Autowired
	private AuthorService service;
	
	@Test
	public void testAuthorService() throws Exception{
		AuthorVO vo = new AuthorVO();
		vo.setNationId("02");
		vo.setAuthorName("serviceTest");
		vo.setAuthorIntro("serviceTest");
		
		service.authorEnroll(vo);
	}
}
