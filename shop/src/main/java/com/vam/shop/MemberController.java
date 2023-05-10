package com.vam.shop;

import java.io.File;
import java.util.Random;

import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.shop.model.MemberVO;
import com.shop.service.MemberService;

@Controller
@RequestMapping(value = "/member")
public class MemberController {

	private static final Logger logger = LoggerFactory.getLogger(MemberController.class);

	@Autowired
	private MemberService memberService;
	@Autowired
	private JavaMailSender mailSender;
	@Autowired
	BCryptPasswordEncoder pwEncoder;

	// 회원가입 페이지 이동
	@GetMapping("/join")
	public void joinGET() {

		logger.info("회원가입 페이지 진입");

	}

	// 로그인 페이지 이동
	@GetMapping("/login")
	public void loginGET() {

		logger.info("로그인 페이지 진입");

	}
	//로그인
	@PostMapping("/login.do")
	public String loginPOST(HttpServletRequest request, MemberVO member, RedirectAttributes rttr) throws Exception {
		//System.out.println("login 메서드 진입");
		//System.out.println("전달된 데이터: "+ member);
		HttpSession session = request.getSession();
		String rawPw = "";
		String encodePw = "";
		MemberVO lvo = memberService.memberLogin(member);
		
		if(lvo != null) {	//아이디와 비밀번호 일치(로그인 성공시)
			rawPw = member.getMemberPw(); //사용자가 제출한 비밀번호
			encodePw = lvo.getMemberPw(); //DB에 저장된 인코딩 된 비밀번호
			if(pwEncoder.matches(rawPw, encodePw)) {	//비밀번호 일치시
				lvo.setMemberPw(""); //비밀번호 지움
				session.setAttribute("member", lvo);
				return "redirect:/main";
			} else {	//비밀번호가 일치하지 않을때
				rttr.addFlashAttribute("result", 0);
				return "redirect:/member/login";
			}
		} else {	//일치하는 아이디가 없을때(로그인 실패시)
			rttr.addFlashAttribute("result", 0);
			return "redirect:/member/login";
		}
		
//		if(lvo == null) {
//			int result = 0;
//			rttr.addFlashAttribute("result", result);
//			return "redirect:/member/login";
//		}
//		session.setAttribute("member", lvo);
//		return "redirect:/main";
	}

	@PostMapping("/join")
	public String joinPOST(MemberVO member) throws Exception {
//		logger.info("join 진입");
//		// 회우언 가입 서비스 실행
//		memberService.memberJoin(member);
//		logger.info("회원 가입 성공");
		
		String rawPw = "";		//인코딩 전 비밀번호
		String encodePw = "";	//인코딩 후 비밀번호
		
		rawPw = member.getMemberPw();
		encodePw = pwEncoder.encode(rawPw);	//비밀번호 인코딩
		member.setMemberPw(encodePw);
		//회원가입 쿼리 실행
		memberService.memberJoin(member);
		
		return "redirect:/main";
	}

	// 아이디 중복 검사
	@PostMapping("/memberIdChk")
	@ResponseBody
	public String memberIdChkPOST(String memberId) throws Exception {

		logger.info("memberIdChk() 진입");

		int result = memberService.idCheck(memberId);

		if (result != 0) {
			return "fail"; // 중복 아이디가 존재하는 경우
		} else {
			return "success"; // 중복 아이지가 존재하지 않는 경우
		}

	} // memberIdChkPOST() 종료
	
	//이메일 보내기test
//	@GetMapping("/sendMail")
//	public void sendMailTest() throws Exception {
//		String subject = "test mail";
//		String content = "test mail content" + "<img src=\"https://t1.daumcdn.net/cfile/tistory/214DCD42594CC40625\">";
//		String from = "zzimmee@naver.com";
//		String to = "zzimmee@naver.com";
//
//		try {
//			MimeMessage mail = mailSender.createMimeMessage();
//			MimeMessageHelper mailHelper = new MimeMessageHelper(mail, true, "UTF-8");
//			mailHelper.setFrom(from);
//			mailHelper.setTo(to);
//			mailHelper.setSubject(subject);
//			mailHelper.setText(content, true);
//
//			FileSystemResource file = new FileSystemResource(new File("c:\\temp\\test.txt"));
//			mailHelper.addAttachment("업로드파일 형식", file);
//
//			mailSender.send(mail);
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//
//	}
//이메일 보내기 test2
//	@GetMapping("/sendMail")
//	public void sendMailTest2() throws Exception{
//		String subject = "test mail";
//		String content = "test mail content"+"<img src=\"https://t1.daumcdn.net/cfile/tistory/214DCD42594CC40625\">";
//		String from = "zzimmee@naver.com";
//		String to = "zzimmee@naver.com";
//		
//		final MimeMessagePreparator preparator = new MimeMessagePreparator() {
//			
//			@Override
//			public void prepare(MimeMessage mimeMessage) throws Exception {
//				final MimeMessageHelper mailHelper = new MimeMessageHelper(mimeMessage, true, "UTF-8");
//				
//			}
//		};
//		
//		mailSender.send(preparator);
//	}

	// 이메일 인증
	@GetMapping("/mailCheck")
	@ResponseBody
	public String mailCheckGET(String email) throws Exception {
		logger.info("이메일 데이터 전송 확인");
		logger.info("인증 메일: " + email);

		Random random = new Random();
		int checkNum = random.nextInt(888888) + 111111;
		logger.info("인증번호:" + checkNum);
		// 이메일 보내기
		String setFrom = "zzimmee@naver.com";
		String toMail = email;
		String title = "회원가입 인증 이메일입니다.";
		String content = "홈페이지를 방문해 주셔서 감사합니다. <br><br> 인중번호는 <b>" + checkNum + "</b>입니다.<br> 해당 인증번호를 인증번호 확인란에 기입하여 주세요.";
		
		try {
			MimeMessage mail = mailSender.createMimeMessage();
			MimeMessageHelper mailHelper = new MimeMessageHelper(mail, true, "UTF-8");
			mailHelper.setFrom(setFrom);
			mailHelper.setTo(toMail);
			mailHelper.setSubject(title);
			mailHelper.setText(content, true);

			mailSender.send(mail);
		} catch (Exception e) {
			e.printStackTrace();
		}
		String num = Integer.toString(checkNum);
		
		return num;
	}
	
	@GetMapping("/logout.do")
	public String logoutGET(HttpServletRequest request) throws Exception{
		HttpSession session = request.getSession();
		session.invalidate();
		
		return "redirect:/main";
	}
	
	@PostMapping("/logout.do")
	@ResponseBody
	public void logoutPOST(HttpServletRequest request) throws Exception{
		logger.info("비동기 로그아웃 메소드 진입");
		HttpSession session = request.getSession();
		session.invalidate();
	}

}
