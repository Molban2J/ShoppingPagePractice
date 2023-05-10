package com.vam.shop;

import java.io.File;

import javax.mail.internet.MimeMessage;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/root-context.xml")
public class MailTestController {
	@Autowired
	JavaMailSenderImpl mailSender;
	
	@Test
	public void testMailSend() throws Exception {
		String subject = "test mail";
		String content = "test mail content"+"<img src=\"https://t1.daumcdn.net/cfile/tistory/214DCD42594CC40625\">";
		String from = "zzimmee@naver.com";
		String to = "zzimmee@naver.com";
		
		try {
			MimeMessage mail = mailSender.createMimeMessage();
			MimeMessageHelper mailHelper = new MimeMessageHelper(mail, true, "UTF-8");
			mailHelper.setFrom(from);
			mailHelper.setTo(to);
			mailHelper.setSubject(subject);
			mailHelper.setText(content,true);
			
			FileSystemResource file = new FileSystemResource(new File("c:\\temp\\test.txt"));
			mailHelper.addAttachment("업로드파일 형식", file);
			
			mailSender.send(mail);
		}catch(Exception e) {
			e.printStackTrace();
		}
		
	}
}
