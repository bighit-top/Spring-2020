package mw.sense.model;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class SenseBean {
	
	@Autowired
	private SenseDAO dao = null;
	
	//R - �����ڸ��� 
	@RequestMapping("senseAdmin.mw")
	public String senseAdminMain(Model model) {
	
		SenseDTO video = dao.mainVideo(); //������ ����
		List<SenseCategoryDTO> category = dao.category(); //ī�װ� ����Ʈ
		List<SenseDTO> mainlist = dao.mainList(); //���� ����Ʈ
				
		model.addAttribute("video", video); //������ ���� url ������
		model.addAttribute("category", category); //ī�װ� ����Ʈ
		model.addAttribute("list", mainlist); //���� ����Ʈ			

		return "/sense/senseAdmin";
		
	}
	
	//R - �����ڸ��� / ī�װ� ���� �� ����Ʈ
	@RequestMapping("senseAdminList.mw")
	public String senseAdminCategorySelect(Model model, int num) {
		
		List<SenseDTO> category_select_list = dao.mainCategorySelect(num); //����Ʈ ���		
		model.addAttribute("list", category_select_list);
		return "/sense/senseAdminList";		
		
	}
	
	//R - ���� 
	@RequestMapping("sense.mw")
	public String senseMain(Model model) {
	
		SenseDTO video = dao.mainVideo(); //������ ����
		List<SenseCategoryDTO> category = dao.category(); //ī�װ� ����Ʈ
		List<SenseDTO> mainlist = dao.mainList(); //���� ����Ʈ
				
		model.addAttribute("video", video); //������ ���� url ������
		model.addAttribute("category", category); //ī�װ� ����Ʈ
		model.addAttribute("list", mainlist); //���� ����Ʈ			

		return "/sense/senseMain";
		
	}
	
	//R - ���� / ī�װ� ���� �� ����Ʈ
	@RequestMapping("senseSelect.mw")
	public String senseCategorySelect(Model model, int num) {
		
		List<SenseDTO> category_select_list = dao.mainCategorySelect(num); //����Ʈ ���		
		model.addAttribute("list", category_select_list);
		return "/sense/mainList";	
		
	}
	
	//R - ���̽�ũ�� ������ ������
	@RequestMapping("senseDetail.mw")
	public String senseDetail(HttpSession session, int num, Model model) { //������ ��ȣ�� �Ű������� ����
		
		String id = (String)session.getAttribute("memId");
		
		ScrapDTO dto = dao.senseDetail(num, id);
		model.addAttribute("detail", dto); //������ �������� ������ ������
		return "/sense/detail";
		
	}
	
	//R - ������ main video url ����
	@RequestMapping("senseDetailVideo.mw")
	public String senseDetailVideo(HttpSession session, int num, Model model) {
		
		String id = (String)session.getAttribute("memId");
		if( id != "admin") {
			dao.count(num); //��ȸ���� �ø�
		}
		SenseDTO dto = dao.senseDetailVideo(num);		
		model.addAttribute("dto", dto);
		return "/sense/video_url";
		
	}
	
	//R - ������ ��ȸ�� ����
	@RequestMapping("senseReadcount.mw")
	public String senseReadcount(int num, Model model) {
		
		int readcount = dao.readcount(num);
		model.addAttribute("count", readcount);		
		return "/sense/readcount";
		
	}	
	
	//C - ���� ���� �Է� form - ī�װ� selectbox
	@RequestMapping("senseWriteForm.mw")
	public String categorySelect(Model model) {
		
		List<SenseCategoryDTO> list = dao.category(); //ī�װ� ����Ʈ
		model.addAttribute("list", list);		
		return "/sense/senseWriteForm";
		
	}
	
	//C - ���� ���� �Է� pro������ 
	@RequestMapping("senseWritePro.mw")
	public String senseWritePro(SenseDTO dto, Model model) {
		
		int maxNum = dao.senseMaxNum() + 1; //mwsense���̺��� num �ִ��� ����� +1 ����
		dto.setNum(maxNum); //dto�� maxNum�� �־���
		
		int check = dao.senseInsertCheck(dto); //url or thumbnail �ߺ� Ȯ��		
		if (check == 0) {
			dao.senseInsert(dto); //�Է�					
		}
		
		model.addAttribute("check", check); //check ��ȯ
		return "/sense/senseWritePro";
	}
	
	//U - ���� ���� form������
	@RequestMapping("senseModify.mw")
	public String senseModifySelect(int num, Model model) {
		
		List<SenseCategoryDTO> category = dao.category(); //ī�װ� ����Ʈ ��������
		SenseDTO dto = dao.senseModifySelect(num); //������ ������ �Խñ� ���� ȣ��
		
		model.addAttribute("category", category); //ī�װ� ����Ʈ
		model.addAttribute("modify", dto); //�Խñ� ����
		return "/sense/senseModify";
		
	}
	
	//U - ���� ���� pro������
	@RequestMapping("senseModifyPro.mw")
	public String senseModify(SenseDTO dto, Model model) {
		
		int check = dao.modifyCheck(dto); //���� ��ȿ�� Ȯ��		
		
		if (check == 1) { //1�� ��� DB���� ����
			dao.senseModify(dto);				
			check = 2;
		}
		
		model.addAttribute("check", check); // ���� Ȯ�� check ��ȯ
		return "/sense/senseModifyPro";
	}
	
	
	//D - ���� ������ ���� PWȮ��
	@RequestMapping("senseDelete.mw")
	public String confirmPassword(HttpSession session, String password, String number, Model model) {
		
		int num = Integer.parseInt(number);
		int check; //��ȯ ������ Ȯ���ϱ� ���� ��
		String id = (String)session.getAttribute("memId");

		if ("admin".equals(id)) { //�������� ��츸 ����
			
			check = dao.confirmPassword(id, password); //id�� pw�� Ȯ����

			if (check == 1) { // id/pw�� ���� ��

				dao.senseDelete(num); // ���� �Խñ� ����			
				int deleteCheck = dao.senseDeleteCheck(num); // �Խñ� ���� Ȯ�� 

				if(deleteCheck == 0) { // ����� ���� �Ǿ��� ��

					model.addAttribute("check",check); // 1�� ��ȯ

				}else {
					check = -1; //����� �������� �ʾ��� ��

					model.addAttribute("check", check); // -1�� ��ȯ
				}
			}else {	// id/pw�� Ʋ�� ��

				model.addAttribute("check",check); // 0�� ��ȯ
			}
		}else {

			check = 2; //���� ������ ���� ���
			model.addAttribute("check", check); //2�� ��ȯ
		}
		List<SenseDTO> mainlist = dao.mainList(); //���� ����Ʈ	
		model.addAttribute("list", mainlist);
		return "/sense/senseDelete";
	}
	
	
	
	//R - ��ũ�� ����������
	@RequestMapping("myscrap.mw")
	public String myScrap(HttpSession session, Model model) {
	
		String id = (String)session.getAttribute("memId");
		List<ScrapDTO> dto = dao.myScrap(id); //��ũ�� ����Ʈ
		List<SenseCategoryDTO> category = dao.category(); //ī�װ� ����Ʈ

		model.addAttribute("myscrap", dto); //���� ��ũ������Ʈ
		model.addAttribute("category", category); //ī�װ� ����Ʈ
		return "/sense/myScrap"; 
		
	}
	@RequestMapping("myscrap_sub.mw")
	public String myScrap_sub(HttpSession session, Model model) {
		
		String id = (String)session.getAttribute("memId");
		List<ScrapDTO> dto = dao.myScrap(id); //��ũ�� ����Ʈ
		List<SenseCategoryDTO> category = dao.category(); //ī�װ� ����Ʈ

		model.addAttribute("myscrap", dto); //���� ��ũ������Ʈ
		model.addAttribute("category", category); //ī�װ� ����Ʈ
		return "/sense/myScrap_sub"; 
		
	}
	
	//R - memoâ
	@RequestMapping("memo.mw")
	public String memo(int num, Model model) {
		
		SenseDTO dto = (SenseDTO)dao.senseMemo(num); //������ ������ ������
		model.addAttribute("dto", dto);
		return "/sense/memo";
		
	}
	
	
	//R - ��ũ�� ��������
	@RequestMapping("myVideo.mw")
	public String myVideo(HttpSession session, int num, Model model) {
	
		String id = (String)session.getAttribute("memId");
		ScrapDTO dto = (ScrapDTO)dao.myVideo(num, id);
		model.addAttribute("dto",dto);
		return "/sense/myVideo";
		
	}
	
	
	//C - ��ũ�� ����
	@RequestMapping("scrap.mw")
	public String scrapInsert(HttpSession session, int num, String memo) {
		
		String id = (String)session.getAttribute("memId");
		dao.scrap(num, id, memo);		
		return "/sense/senseMain";
		
	}
	
	//R - ��ũ�� ī�װ� ����
	@RequestMapping("myscrapCategory.mw")
	public String myscrapCategory(HttpSession session, int num, Model model) {
		
		String id = (String)session.getAttribute("memId");
		List<ScrapDTO> myscrapCategory = dao.myscrapCategory(id, num); //����Ʈ ���		
		model.addAttribute("myscraplist", myscrapCategory);
		return "/sense/myScrapList";	
		
	}
	
	
	//D - ��ũ�� ����
	@RequestMapping("scrapDelete.mw")
	public String scrapDelete(HttpSession session, int num, Model model) {
		
		String id = (String)session.getAttribute("memId");
		dao.scrapDelete(num, id); 
		List<ScrapDTO> dto = dao.myScrap(id); //���� ��ũ�� ����Ʈ�� �ٽ� ��� 
		model.addAttribute("myscraplist", dto);		
		return "/sense/myScrapList";
		
	}
	
}
