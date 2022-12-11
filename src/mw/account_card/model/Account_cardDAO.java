package mw.account_card.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;

public class Account_cardDAO {

	private SqlSessionTemplate sqlSession = null;
	
	public Account_cardDAO(SqlSessionTemplate sqlSession) {
		this.sqlSession = sqlSession;
	}
	
	// ȸ�� ī����
	public void card_insert(Reg_CardDTO cdto) {
		sqlSession.insert("account_card.card_insert",cdto);
	}
	
	// ȸ�� ���µ��
	public void account_insert(Reg_AccountDTO adto) {
		sqlSession.insert("account_card.account_insert",adto);
	}
	
	// ī������ �̹��� ���		
	public void card_img_insert(Card_imgDTO cdto) {
		sqlSession.insert("account_card.card_img_insert",cdto);
	}
	
	// ī������ ��������
	public List card_select() {
		
		List card_list = new ArrayList();
		card_list = sqlSession.selectList("account_card.card_select");
		
		return card_list;
	}
	
	// ī��ȸ��  �� ī���̸� �������� 
	public List card_cn_select(String cardName) {
		
		List card_cn_list = new ArrayList();
		card_cn_list = sqlSession.selectList("account_card.card_cn",cardName);
		
		return card_cn_list;
	}
	
	// ī��� ��������
	public List card_company_select() {
		
		List card_company_list = new ArrayList();
		card_company_list = sqlSession.selectList("account_card.card_company");
		
		return card_company_list;
	}
	
	// ī��� ���� ī������ ��������
	public List card_benefit_select(String cardName) {
		
		List card_benefit_list = new ArrayList();
		card_benefit_list = sqlSession.selectList("account_card.card_benefit",cardName);
		
		return card_benefit_list;
	}
	
	//ī�� �̹���
	public String card_img(String cardName) {
			
		String card_img = sqlSession.selectOne("account_card.card_img",cardName);
		
		return card_img;
	}
	
	// ���� ī�� �� �������� Ȯ��
	public HashMap myCard(String id) {
		
		List mycard = new ArrayList();
		List myaccount = new ArrayList();
		
		mycard = sqlSession.selectList("account_card.mycard",id);
		myaccount = sqlSession.selectList("account_card.myaccount",id);
		
		HashMap acmap = new HashMap();
		acmap.put("mycard",mycard);
		acmap.put("myaccount", myaccount);
		
		return acmap;
	}
	
	// ���� ī�� �̹��� ����Ʈ
	public List myCardList(String id) {
		
		List mycard = new ArrayList();
		mycard = sqlSession.selectList("account_card.mycardList",id);
		
		return mycard;
	}
	
	// ���� ī�����
	public void delMyCard(String id, int num) {
		
		HashMap map = new HashMap();
		map.put("id", id);
		map.put("num",num);
		
		sqlSession.delete("account_card.mycard_del",map);
	}

	// ���� ���»���
	public void delMyAccount(String id, int num) {
		
		HashMap map = new HashMap();
		map.put("id", id);
		map.put("num",num);
		
		sqlSession.delete("account_card.myaccount_del",map);
	}
	// ī�忡 ���� ���� ��������(������ ī�忡 ���� ����)
	public Reg_CardDTO getCard(Reg_CardDTO cdto) {
		
		Reg_CardDTO reg_card = new Reg_CardDTO();
		
		reg_card = (Reg_CardDTO)sqlSession.selectOne("account_card.getCard",cdto);
		
		return reg_card;
	}
	
	// ī�� ����
	public void updateCard(Reg_CardDTO cdto) {
		
		sqlSession.update("account_card.card_update",cdto);
	}
	
	// ��ϵ� ���� �ҷ�����
	public List getAccount(Reg_AccountDTO adto) {
		
		List reg_account = new ArrayList();
		reg_account = sqlSession.selectList("account_card.getAccount");
		
		return reg_account;
	}
	
	// ��ϵ� ����� ���� ���¹�ȣ ��������
	public List getAccount_num(String account_company) {
		
		
		List getAccountNum = new ArrayList();
		getAccountNum = sqlSession.selectList("account_card.getAccount_num",account_company);
		
		return getAccountNum;
	}
	
	// ���� ī�� ���ú��� 
	public List mycard_benefit(String ca_name) {
		
		List myBenefit = new ArrayList();
		myBenefit = sqlSession.selectList("account_card.my_ac_benefit",ca_name);
		
		return myBenefit;
	}
	
	// �����ߺ�Ȯ��
	public int check_account(String id,String account_company, String account_num) {
		
		HashMap amap = new HashMap();
		amap.put("id", id);
		amap.put("account_company",account_company);
		amap.put("account_num", account_num);
	
		int check = sqlSession.selectOne("account_card.check_account",amap);
		return check;
	}
	
	// ī�忡 ���� ����� ���� �ߺ�Ȯ��
	public int check_card(String id, String card_name, String account_num) {
		
		HashMap cmap = new HashMap();
		cmap.put("id",id);
		cmap.put("card_name", card_name);
		cmap.put("account_num",account_num);
		
		int check = sqlSession.selectOne("account_card.check_card",cmap);
		return check;
	}
	
	// ���ɺ� ī�� ��� ����
	public HashMap card_rank(Reg_CardDTO cdto) {
		
		HashMap rmap = new HashMap();
		List rankList_20 = sqlSession.selectList("account_card.20_cardRegisterCount",cdto);
		List rankList_30 = sqlSession.selectList("account_card.30_cardRegisterCount",cdto);
		List rankList_40 = sqlSession.selectList("account_card.40_cardRegisterCount",cdto);
		
		rmap.put("rankList_20", rankList_20);
		rmap.put("rankList_30", rankList_30);
		rmap.put("rankList_40", rankList_40);
		
		
		return rmap;
		
	}
	
}
