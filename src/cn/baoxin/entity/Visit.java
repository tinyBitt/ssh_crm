package cn.baoxin.entity;

public class Visit {
	private Integer vtId;
	private String date;
	private String place;
	
	private Customer customer;
	private User user;
	
	public Integer getVtId() {
		return vtId;
	}
	public void setVtId(Integer vtId) {
		this.vtId = vtId;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public String getPlace() {
		return place;
	}
	public void setPlace(String place) {
		this.place = place;
	}
	public Customer getCustomer() {
		return customer;
	}
	public void setCustomer(Customer customer) {
		this.customer = customer;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
}
