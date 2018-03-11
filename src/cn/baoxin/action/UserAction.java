package cn.baoxin.action;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

import cn.baoxin.entity.User;
import cn.baoxin.service.UserException;
import cn.baoxin.service.UserService;

public class UserAction extends ActionSupport implements ModelDriven<User> {
	private UserService userService;
	public void setUserService(UserService userService) {
		this.userService = userService;
	}
	/*
	 * 模型驱动
	 */
	private User user = new User();
	public User getModel() {
		return user;
	}
	
	public String login() throws Exception{
		try {
			User _user = userService.login(user);
			ServletActionContext.getRequest().getSession().setAttribute("user", _user);
			return SUCCESS;
		} catch (UserException e) {
			HttpServletRequest request = ServletActionContext.getRequest();
			request.setAttribute("msg", e.getMessage());
			request.setAttribute("user", user);
			return "fail";
		}
	}
}
