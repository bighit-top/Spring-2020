package mw.calendar.model;

public class MwScheduleDTO {
	private int num; //��ȣ
	private String id; //ȸ��ID
	private String title; //��������
	private String memo; //�����޸�
	private String start_time; //���۽ð�
	private String end_time; //����ð�
	private String place; //���
	private String sc_color; //����
	
	public void setNum(int num) {
		this.num = num;
	}
	public void setId(String id) { 
		this.id = id;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public void setMemo(String memo) {
		this.memo = memo;
	}
	public void setStart_time(String start_time) {
		this.start_time = start_time;
	}
	public void setEnd_time(String end_time) {
		this.end_time = end_time;
	}
	public void setPlace(String place) {
		this.place = place;
	}
	public void setSc_color(String sc_color) {
		this.sc_color = sc_color;
	}
	
	public int getNum() {
		return num;
	}
	public String getId() {
		return id;
	}
	public String getTitle() {
		return title;
	}
	public String getMemo() {
		return memo;
	}
	public String getStart_time() {
		return start_time;
	}
	public String getEnd_time() {
		return end_time;
	}
	public String getPlace() {
		return place;
	}
	public String getSc_color() {
		return sc_color;
	}

	
}
