package com.oa.vo.qh;

public class DeptVo {
	private String id ; 
	private String text ;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	public DeptVo(String id, String text) {
		System.out.println(id);
		System.out.println(text);
		this.id = id;
		this.text = text;
	}
	public DeptVo() {
		// TODO Auto-generated constructor stub
	}
}
