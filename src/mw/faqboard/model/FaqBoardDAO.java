package mw.faqboard.model;

import org.mybatis.spring.SqlSessionTemplate;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import mw.faqboard.model.*;

public class FaqBoardDAO {

	private SqlSessionTemplate sqlSession = null;
	//SQL�� �����ϱ� ���� root �������� ȣ��
	public FaqBoardDAO(SqlSessionTemplate sqlSession) {
		this.sqlSession = sqlSession; 
	}

	public List getArticles(int start, int end) {//�����Խ��� list ��� ������
		HashMap map = new HashMap();
		map.put("start", start);
		map.put("end", end);

		List articleList = sqlSession.selectList("faqboard.getArticles", map);

		return articleList;
	}

	public int getCount(FaqBoardDTO dto) {//�����Խ��� �� ����
		int count = sqlSession.selectOne("faqboard.getCount", dto);

		return count;
	}

	public FaqBoardDTO getContent(int num1) { //��ȸ�� ������ �Բ� �����Խñ� ������ ��ȯ
		sqlSession.update("faqboard.upCount", num1);
		FaqBoardDTO article = sqlSession.selectOne("faqboard.getContent", num1);

		return article;
	}

	public int DeleteCheck(String faq_num, String pw) { //���� �� üũ��ü ��ȯ
		HashMap map = new HashMap();
		map.put("faq_num", faq_num);
		map.put("pw", pw);

		int check = (int) sqlSession.selectOne("faqboard.DeleteCheck", map);

		return check;
	}

	public void DeleteWriting(String faq_num) {//���� �Խñ� ���� ��û
		sqlSession.delete("faqboard.DeleteWriting", faq_num);
	}

	public FaqBoardDTO updateSelect(String faq_num) { //�����Խñ� ���������� ��ȯ 
		FaqBoardDTO dto1 = sqlSession.selectOne("faqboard.updateSelect", faq_num);

		return dto1;
	}

	public int updateCheck(String faq_num, String pw) { //�����Խñ� ���� �� üũ��ü�� ��ȯ
		//���ؾ��� ���� 2�� �̻��̿��� hashmapŬ���� ���
		HashMap map = new HashMap();
		map.put("faq_num", faq_num);
		map.put("pw", pw);
		
		int check = sqlSession.selectOne("faqboard.updateCheck", map);

		return check;
	}

	public void updateContent(FaqBoardDTO dto) { //�����Խñ� ������ ������û
		sqlSession.update("faqboard.updateContent", dto);
	}

	public void insertBoard(FaqBoardDTO dto) { //���� �Խñ� �۾���
		sqlSession.insert("faqboard.insertBoard", dto);
	}

	public List selectMainFaq(FaqMainBoardDTO dto1) {//��� �Խñ� list ������ ��ȯ
		List qList = sqlSession.selectList("faqboard.selectMainFaq", dto1);

		return qList;
	}

	public void insertQwrite(FaqMainBoardDTO dto) { //��� �Խñ� �۾���
		sqlSession.insert("faqboard.insertQwrite", dto);
	}

	public int getQcount(FaqMainBoardDTO dto1) { //��ڰԽñ� �� ����
		int qcount = sqlSession.selectOne("faqboard.getQcount", dto1);

		return qcount;
	}

	public FaqMainBoardDTO getQcontent(int qnum) { //��ȸ�� ������ ��ڰԽñ� �󼼺��� ��ȯ
		sqlSession.update("faqboard.upQcount", qnum);
		FaqMainBoardDTO dto1 = sqlSession.selectOne("faqboard.getQcontent", qnum);

		return dto1;
	}

	public void updateQcontnet(FaqMainBoardDTO dto) { //��ڰԽñ� ������û
		sqlSession.update("faqboard.updateQcontnet", dto);
	}

	public int DeleteQcheck(int qnum, String q_id) { //��ڰԽñ� ������ üũ��ü ��ȯ
		HashMap map = new HashMap();
		map.put("qnum", qnum);
		map.put("q_id", q_id);

		int check = sqlSession.selectOne("faqboard.DeleteQcheck", map);
		return check;
	}
	
	public void DeleteQcontent(int qnum) { //��ڰԽñ� ������û
		sqlSession.delete("faqboard.DeleteQcontent", qnum);
	}

	public List getArticles(int start, int end, String id) { //�������� list ������ ��ȯ
		HashMap map = new HashMap();
		map.put("start", start);
		map.put("end", end);
		map.put("id", id);
		
		List articleList = sqlSession.selectList("faqboard.mygetArticles", map);
		return articleList;
	}

	public int getCountmy(String id) { //�������� ���� ī��Ʈ��ü ��ȯ
		int count = (Integer) sqlSession.selectOne("faqboard.mylistboard", id);
		return count;
	}
	
	public void DeleteAdminfaq(int faq_num) {
		sqlSession.delete("faqboard.DeleteAdminfaq",faq_num);
	}
}
