package mw.sense.model;

import java.util.HashMap;
import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;

public class SenseDAO {

	//mybatis�� ����
	private SqlSessionTemplate sqlSession = null; 	
	
	//sqlSession�� �̿��� SQL�� ����
	public SenseDAO(SqlSessionTemplate sqlSession) {
		this.sqlSession = sqlSession;
	}
	
	//���� ����
	public SenseDTO mainVideo() {
		return sqlSession.selectOne("sense.mainVideo"); //
	}
	
	//���� ����Ʈ
	public List<SenseDTO> mainList(){
		return sqlSession.selectList("sense.mainList"); //������������ ����Ʈ�� ���
	}
	
	//���� ī�װ� ���ý� ����Ʈ
	public List<SenseDTO> mainCategorySelect(int num){
		return sqlSession.selectList("sense.categorySelect", num); //���������� ī�װ� ���ý� ����Ʈ ���
	}
	
	//������ ���� ����
	public SenseDTO senseDetailVideo(int num) {
		SenseDTO dto = sqlSession.selectOne("sense.senseDetailVideo", num); //���� ����Ʈ���� ���ý� ���� url ����
		return dto;
	}

	//���̽�ũ�� ������ ������
	public ScrapDTO senseDetail(int num, String id) {
		
		HashMap map = new HashMap();
		map.put("id", id);
		map.put("num", num);
		
		ScrapDTO dto = sqlSession.selectOne("sense.myDetail", map);
		return dto; //������ �������� �ش� ������ ������
		
	}
	
	//��ȸ�� �ø���
	public void count(int num) {
		sqlSession.update("sense.count", num);
	} 
	
	//��ȸ�� ���
	public int readcount(int num) {
		return sqlSession.selectOne("sense.readcount", num);		
	}
	
	//���� �Է� ���� ī�װ� ����Ʈ ����Ʈ
	public List<SenseCategoryDTO> category() {
		return sqlSession.selectList("sense.category"); //ī�װ��� �����ؼ� �ҷ���
	}
	
	//���� ���� �Է��� ���� senseMaxNum�� ���
	public int senseMaxNum() {
		return sqlSession.selectOne("sense.senseMaxNum"); //���� num������ �ֱ� ���� MaxNum�� ���
	}
	
	//���� ���� �Է�
	public void senseInsert(SenseDTO dto) {
		sqlSession.insert("sense.insert", dto); //�������Է�
	}
	
	//���� �Է� Ȯ��
	public int senseInsertCheck(SenseDTO dto) {
		int check = sqlSession.selectOne("sense.insertCheck", dto);
		return check;
	}
	
	// PW confirm
	public int confirmPassword(String id, String password) {
		
		HashMap map = new HashMap();
		map.put("id", id);
		map.put("pw", password);
		int check = sqlSession.selectOne("sense.confirmPassword", map);
		
		return check;
	}
	
	// ���� ���� ������ �ҷ�����
	public SenseDTO senseModifySelect(int num) {
		SenseDTO dto = (SenseDTO)sqlSession.selectOne("sense.select", num);
		return dto; //���� ���� �������� �ش� ������ ������
	}
	
	//���� �Է� Ȯ��
	public int modifyCheck(SenseDTO dto) {
		int check = sqlSession.selectOne("sense.check", dto);
		return check;
	}
	
	// ���� �����ϱ�
	public void senseModify(SenseDTO dto) {
		sqlSession.update("sense.senseModify",dto);
	}
	
	// ���� ����
	public void senseDelete(int num) {
		sqlSession.delete("sense.senseDelete", num);
	}
	
	// ���� ���� Ȯ��
	public int senseDeleteCheck(int num) {
		int deleteCheck = sqlSession.selectOne("sense.senseDeleteCheck", num);
		return deleteCheck;
	}
	
	// �޸�â�� ���� ���
	public SenseDTO senseMemo(int num) {
		return sqlSession.selectOne("sense.senseMemo",num);
	}
	
	// ��ũ�� ����������
	public List<ScrapDTO> myScrap(String id) {
		return sqlSession.selectList("sense.myscrap", id);
	}
	
	// ��ũ�� ����
	public ScrapDTO myVideo(int num, String id) {
		
		HashMap map = new HashMap();
		map.put("id", id);
		map.put("num", num);
		
		ScrapDTO video = (ScrapDTO)sqlSession.selectOne("sense.myVideo", map);
		return video;
	}
	
	//������������
	public SenseDTO senseSearch(int num) {
		SenseDTO dto = sqlSession.selectOne("sense.select", num);
		return dto; //������ �������� �ش� ������ ������
	}
	
	// ��ũ�� ����
	public void scrap(int num, String id, String memo) {
		
		SenseDTO dto = (SenseDTO)senseSearch(num); //��ȣ�� �˻��� ���� ���� �ҷ���
		HashMap map = new HashMap();
		map.put("scrap", dto);
		map.put("id", id);
		map.put("memo", memo);
		
		sqlSession.insert("sense.scrap", map);
	}
	
	// ��ũ�� ī�װ� ���ý� ����Ʈ
	public List<ScrapDTO> myscrapCategory(String id, int num){
		
		HashMap map = new HashMap();
		map.put("id", id);
		map.put("num", num);
		return sqlSession.selectList("sense.myscrapCategory", map); //���������� ī�װ� ���ý� ����Ʈ ���
		
	}
	
	// ��ũ�� ����
	public void scrapDelete(int num, String id) {
		
		HashMap map = new HashMap();
		map.put("id", id);
		map.put("num", num);
		
		sqlSession.delete("sense.scrapDelete", map);
		
	}
	
}
