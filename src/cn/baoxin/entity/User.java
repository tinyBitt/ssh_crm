package cn.baoxin.entity;

import java.util.LinkedHashSet;
import java.util.Set;

public class User {
	private Integer uid;
	private String username;
	private String password;
	
	private Set<Visit> visitSet = new LinkedHashSet<Visit>();
	
	public Set<Visit> getVisitSet() {
		return visitSet;
	}
	public void setVisitSet(Set<Visit> visitSet) {
		this.visitSet = visitSet;
	}
	public Integer getUid() {
		return uid;
	}
	public void setUid(Integer uid) {
		this.uid = uid;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	@Override
	public String toString() {
		return "User [uid=" + uid + ", username=" + username + ", password=" + password + "]";
	}
}
