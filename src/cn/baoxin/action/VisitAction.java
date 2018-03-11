package cn.baoxin.action;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

import cn.baoxin.entity.Customer;
import cn.baoxin.entity.User;
import cn.baoxin.entity.Visit;
import cn.baoxin.service.CustomerService;
import cn.baoxin.service.UserService;
import cn.baoxin.service.VisitService;

public class VisitAction extends ActionSupport implements ModelDriven<Visit> {
	private VisitService visitService;
	@Resource(name="customerService")
	private CustomerService customerService;
	@Resource(name="userService")
	private UserService userService;
	public void setVisitService(VisitService visitService) {
		this.visitService = visitService;
	}
	
	/*
	 * 模型驱动
	 */
	private Visit visit = new Visit();
	public Visit getModel() {
		return visit;
	}
	
	/**
	 * 拜访列表
	 * @return
	 * @throws Exception
	 */
	public String list() throws Exception {
		List<Visit> visitList = visitService.findAll();
		ServletActionContext.getRequest().setAttribute("visitList", visitList);
		return "toListPage";
	}
	
	/**
	 * 新增客户拜访
	 * @return
	 * @throws Exception
	 */
	public String add() throws Exception {
		visitService.add(visit);
		return "list";
	}
	
	/**
	 * 添加前
	 * @return
	 * @throws Exception
	 */
	public String toAddPage() throws Exception {
		/*
		 * 查询所有用户和客户用于下拉显示
		 */
		List<Customer> customerList = customerService.findAll();
		List<User> userList = userService.findAll();
		
		HttpServletRequest request = ServletActionContext.getRequest();
		request.setAttribute("customerList", customerList);
		request.setAttribute("userList", userList);
		return "toAddPage";
	}
	
}
