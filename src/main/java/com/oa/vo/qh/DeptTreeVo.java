package com.oa.vo.qh;

public class DeptTreeVo {
	private String fid;
	private String id;
	private String text;
	
	
	
	public String getFid() {
		return fid;
	}
	public void setFid(String fid) {
		this.fid = fid;
	}
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
	public DeptTreeVo(String fid, String id, String name) {
		System.out.println("fid"+fid);
		System.out.println("id"+id);
		System.out.println("name"+name);
		this.fid = fid;
		this.id = id;
		this.text = name;
	}
	public DeptTreeVo() {
		super();
		// TODO Auto-generated constructor stub
	}
	@Override
	public String toString() {
		return "DeptTreeVo [fid=" + fid + ", id=" + id + ", name=" + text + "]";
	}
}
