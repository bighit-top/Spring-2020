package mw.member.model;

import org.mybatis.spring.SqlSessionTemplate;
import java.util.HashMap;
import java.util.List;

import mw.member.model.*;

public class MemberDAO {

	private SqlSessionTemplate sqlSession = null;

	public MemberDAO(SqlSessionTemplate sqlSession) {
		this.sqlSession = sqlSession;
	}

	public int loginCheck(MemberDTO dto) { // �α��ν� ������ DB�� �ִ��� üũ�ϴ� �޼���
		int check = (int) sqlSession.selectOne("member.loginCheck", dto);

		return check;
	}

	public void insert(MemberDTO dto) { // ȸ�����Խÿ� ������ �ִ� �޼���
		sqlSession.insert("member.insert", dto);
	}

	public int memberCheck(String id) { // ȸ�����Խÿ� ���̵� �ߺ��Ǵ��� üũ�ϴ� �޼���
		int check = sqlSession.selectOne("member.memberCheck", id);

		return check;
	}

	public MemberDTO modifyCheck(String id) { // ������ �÷����ִ� ���Ǿ��̵��� dto�� �����ϴ¸޼���
		MemberDTO dto = (MemberDTO) sqlSession.selectOne("member.modifyCheck", id);
		return dto; // ������� dto���� ����
	}

	public void updateMember(MemberDTO dto) { // ȸ������ �����Ѱ��� DB�� ������Ʈ �����ִ� �޼���
		sqlSession.update("member.updateMember", dto);
	}

	public int deleteCheck(String id, String pw) { // ȸ�� Ż�������� id�� ����� �´��� Ȯ���ϴ� �޼���
		HashMap map = new HashMap(); // ��ü ����
		map.put("id", id); // map�� ���� id,pw 2���� ����
		map.put("pw", pw);

		int check = (int) sqlSession.selectOne("member.deleteCheck", map);//map�� id,pw�� üũ
		return check; // üũ�Ѱ��� ����
	}

	public MemberDTO deleteSelect(String id) { // id�� ������ �˻��ϴ� �޼��� �� ������ dto1�� ����
		MemberDTO dto1 = (MemberDTO) sqlSession.selectOne("member.deleteSelect", id);
		return dto1;
	}

	public void deleteInsert(DeleteMemListDTO dto2) { // Ż���� ȸ�� �� ������ DB�� �ִ� �޼���
		sqlSession.insert("member.deleteMemList", dto2);
	}

	public void deleteMem(String id) { // ȸ�� id�� ���� ������ ������ �����ϴ� �޼���
		sqlSession.delete("member.deleteMem", id);

	}

	public List selectMemList(MemberDTO dto) { // ������ ȸ�� ����Ʈ
		List list = sqlSession.selectList("member.selectMemList", dto);

		return list;
	}

	public List memSearch(String keyField, String keyWord) { // ȸ���� �˻��ϴ� �޼���

		HashMap map = new HashMap();
		map.put("keyField", keyField);
		map.put("keyWord", keyWord);

		List search = sqlSession.selectList("member.memSearch", map);

		return search;
	}
}
