package cn.baoxin.entity;

import java.util.LinkedHashSet;
import java.util.Set;

public class Customer {
	private Integer custId;
	private String custName;
	private String custSource;
	private String custPhone;

	private Set<LinkMan> linkManSet = new LinkedHashSet<LinkMan>();
	private Set<Visit> visitSet = new LinkedHashSet<Visit>();
	private Dictionary custLevel;
	
	public Set<Visit> getVisitSet() {
		return visitSet;
	}

	public void setVisitSet(Set<Visit> visitSet) {
		this.visitSet = visitSet;
	}


	public Integer getCustId() {
		return custId;
	}

	public void setCustId(Integer custId) {
		this.custId = custId;
	}

	public String getCustName() {
		return custName;
	}

	public void setCustName(String custName) {
		this.custName = custName;
	}

	public String getCustSource() {
		return custSource;
	}

	public void setCustSource(String custSource) {
		this.custSource = custSource;
	}

	public String getCustPhone() {
		return custPhone;
	}

	public void setCustPhone(String custPhone) {
		this.custPhone = custPhone;
	}

	public Set<LinkMan> getLinkManSet() {
		return linkManSet;
	}

	public void setLinkManSet(Set<LinkMan> linkManSet) {
		this.linkManSet = linkManSet;
	}

	public Dictionary getCustLevel() {
		return custLevel;
	}

	public void setCustLevel(Dictionary custLevel) {
		this.custLevel = custLevel;
	}
}
