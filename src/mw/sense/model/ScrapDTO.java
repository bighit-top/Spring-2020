package mw.sense.model;

public class ScrapDTO {
	private int scrap_num; //��ũ����ȣ - ������
	private int num; //��ȣ - sense��ȣ
	private String id; //id
	private String sense_category; //ī�װ�
	private String sense_title; //Ÿ��Ʋ
	private String sense_url; //url
	private String memo; //�޸�
	
	
	//getter
	public int getScrap_num() {
		return scrap_num;
	}
	public int getNum() {
		return num;
	}
	public String getId() {
		return id;
	}
	public String getSense_category() {
		return sense_category;
	}
	public String getSense_title() {
		return sense_title;
	}
	public String getSense_url() {
		return sense_url;
	}
	public String getMemo() {
		return memo;
	}
	
	
	//setter
	public void setScrap_num(int scrap_num) {
		this.scrap_num = scrap_num;
	}
	public void setNum(int num) {
		this.num = num;
	}
	public void setId(String id) {
		this.id = id;
	}
	public void setSense_category(String sense_category) {
		this.sense_category = sense_category;
	}
	public void setSense_title(String sense_title) {
		this.sense_title = sense_title;
	}
	public void setSense_url(String sense_url) {
		this.sense_url = sense_url;
	}
	public void setMemo(String memo) {
		this.memo = memo;
	}
	
}
