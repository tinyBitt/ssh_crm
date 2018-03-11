package cn.baoxin.entity;

import java.util.List;

public class PageBean<T> {
	private int pc;//当前页
	private int tr;//总记录数
	private int ps;//每页记录数
	private List<T> beanList;
	
	public int getTp(){
		return (tr%ps == 0) ? tr/ps : tr/ps+1;
	}
	public int getPc() {
		return pc;
	}
	public void setPc(int pc) {
		this.pc = pc;
	}
	public int getTr() {
		return tr;
	}
	public void setTr(int tr) {
		this.tr = tr;
	}
	public int getPs() {
		return ps;
	}
	public void setPs(int ps) {
		this.ps = ps;
	}
	public List<T> getBeanList() {
		return beanList;
	}
	public void setBeanList(List<T> beanList) {
		this.beanList = beanList;
	}
}
