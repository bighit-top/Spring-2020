package mw.email.model;

import java.util.Random;

import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import mw.email.model.EmailDTO;
import mw.email.model.EmailDAO;

@Controller
public class EmailBean {
	@Autowired
	private EmailDAO dao = null;

	@Autowired
	private JavaMailSender mailSender;
	
	@RequestMapping("idFindForm.mw")
	public String idFindForm() {
		return "/member/idFindForm";
	}
	
	@RequestMapping("idFindPro.mw")
	public String idFindPro(EmailDTO dto, Model model, HttpServletRequest request) {
		
		String name= request.getParameter("name") ;
		int check = (int)dao.idFind(dto);

		String id="";
		
		if(check==1) {
			id = dao.idWhat(dto);
			model.addAttribute("id", id);
		}
		model.addAttribute("name", name);
		model.addAttribute("check", check);
		
		return "/member/idFindPro";
	}
	
	@RequestMapping("pwFindForm.mw")
	public String pwFindForm() {
		return "/member/pwFindForm";
	}
	
	// mailSending �ڵ�
	@RequestMapping("pwFindPro.mw")
	public String pwFindPro(EmailDTO dto, Model model, HttpServletRequest request) {
		
		String name = request.getParameter("name");
		int pw_check = (int)dao.pwFind(dto);
		System.out.println("pw_check"+pw_check);
		
		//���� ��ȣ ������
        StringBuffer temp = new StringBuffer();
        Random rnd = new Random();
        for(int i=0;i<10;i++)
        {
            int rIndex = rnd.nextInt(3);
            switch (rIndex) {
            case 0:
                // a-z
                temp.append((char) ((int) (rnd.nextInt(26)) + 97));
                break;
            case 1:
                // A-Z
                temp.append((char) ((int) (rnd.nextInt(26)) + 65));
                break;
            case 2:
                // 0-9
                temp.append((rnd.nextInt(10)));
                break;
            }
        }
        String AuthenticationKey = temp.toString();
        
		
		if(pw_check == 1) {
			String setfrom = "ADMIN";
			String tomail1 = request.getParameter("email1"); // �޴� ��� �̸���
			String tomail2 = request.getParameter("email2"); // �޴� ��� �̸���
			String title = "[moneyWatch �ӽ� ��й�ȣ ����] "; // ����
			String content = name+"���� �ӽ� ��й�ȣ�� "+AuthenticationKey+" �Դϴ�."; // ����
			String content2 = "���� ���� ��ȣ�� ���� ��й�ȣ�� �������ּ���";
			
			//dto.setPw(AuthenticationKey);
			dao.pwUpdate(dto,AuthenticationKey);
			
			try {
				
				MimeMessage message = mailSender.createMimeMessage();
				MimeMessageHelper messageHelper = new MimeMessageHelper(message, true, "UTF-8");
				
				System.out.println(content);
				messageHelper.setFrom(setfrom); // �����»�� �����ϸ� �����۵��� ����
				messageHelper.setTo(tomail1+"@"+tomail2); // �޴»�� �̸���
				messageHelper.setSubject(title); // ���������� ������ �����ϴ�
				messageHelper.setText(content+"\n"+content2); // ���� ����
	
	
				mailSender.send(message);	
				
			} catch (Exception e) {
				System.out.println(e);
			}
		}
		model.addAttribute("pw_check", pw_check);
		return "/member/pwFindPro";
	}
	
}
