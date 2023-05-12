package com.vam.shop;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.shop.model.AuthorVO;
import com.shop.service.AuthorService;

@Controller
@RequestMapping("/admin")
public class Admincontroller {
	private static final Logger logger = LoggerFactory.getLogger(Admincontroller.class);
	@Autowired
	AuthorService authorService;
	
	@GetMapping("/main")
	public void adminGET() throws Exception{
		logger.info("관리자 페이지 이동");
	}
	
	 /* 상품 등록 페이지 접속 */
    @GetMapping("/goodsManage")
    public void goodsManageGET() throws Exception{
        logger.info("상품 등록 페이지 접속");
    }
    
    /* 상품 등록 페이지 접속 */
    @GetMapping("/goodsEnroll")
    public void goodsEnrollGET() throws Exception{
        logger.info("상품 등록 페이지 접속");
    }
    
    /* 작가 등록 페이지 접속 */
    @GetMapping("/authorEnroll")
    public void authorEnrollGET() throws Exception{
        logger.info("작가 등록 페이지 접속");
        
    }
    
    @PostMapping("authorEnroll.do")
    public String authorEnrollPOST(AuthorVO vo, RedirectAttributes rttr) throws Exception{
    	//작가 등록 쿼리 실행
        authorService.authorEnroll(vo);
        rttr.addFlashAttribute("enroll_result", vo.getAuthorName());
    	return "redirect:/admin/authorManage";
    }
    
    /* 작가 관리 페이지 접속 */
    @GetMapping("/authorManage")
    public void authorManageGET() throws Exception{
        logger.info("작가 관리 페이지 접속");
        
        
    }    
}
