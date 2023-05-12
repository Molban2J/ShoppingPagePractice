package com.shop.mapper;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.shop.model.AuthorVO;
import com.shop.model.Criteria;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/root-context.xml")
public class AuthorMapperTest {
	@Autowired
	private AuthorMapper mapper;
	
//	@Test
//	public void testAuthorEnroll() throws Exception {
//		AuthorVO author = new AuthorVO();
//		author.setAuthorName("test");
//		author.setAuthorIntro("test");
//		author.setNationId("01");
//		
//		mapper.authorEnroll(author);
//	}
	
//	@Test
//	public void testauthorGeList() {
//		Criteria cri = new Criteria(3,10);
//		List<AuthorVO> list = mapper.authorList(cri);
//		cri.setKeyword("홍준");
//		
//		for(int i = 0; i< list.size(); i++) {
//			System.out.println("list"+i+"--------"+list.get(i));
//		}
//		
//	}
	
	@Test
	public void testAuthorCount() {
		Criteria cri = new Criteria(3,10);
		cri.setKeyword("홍준");
		mapper.authorTotal(cri);
		System.out.println(mapper.authorTotal(cri));
		
	}
}
