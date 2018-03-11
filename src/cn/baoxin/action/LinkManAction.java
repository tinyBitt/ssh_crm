package cn.baoxin.action;

import java.util.List;

import javax.annotation.Resource;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

import cn.baoxin.entity.Customer;
import cn.baoxin.entity.LinkMan;
import cn.baoxin.service.CustomerService;
import cn.baoxin.service.LinkManService;

public class LinkManAction extends ActionSupport implements ModelDriven<LinkMan> {
	private LinkManService linkManService;
	@Resource(name="customerService")
	private CustomerService customerService;
	public void setLinkManService(LinkManService linkManService) {
		this.linkManService = linkManService;
	}
	
	/*
	 * 模型驱动
	 */
	private LinkMan linkMan = new LinkMan();
	public LinkMan getModel() {
		return linkMan;
	}
	
	/**
	 * 删除联系人
	 * @return
	 * @throws Exception
	 */
	public String delete() throws Exception {
		linkManService.delete(linkMan);
		return "list";
	}
	
	/**
	 * 根据用户名筛选联系人
	 * @return
	 * @throws Exception
	 */
	public String selectLinkMan() throws Exception {
		List<LinkMan> linkManList = linkManService.selectLikeName(linkMan.getLkmName());
		ServletActionContext.getRequest().setAttribute("linkManList", linkManList);
		return "toListPage";
	}
	
	/**
	 * 修改联系人
	 * @return
	 * @throws Exception
	 */
	public String edit() throws Exception {
		linkManService.update(linkMan);
		return "list";
	}
	
	/**
	 * 修改前
	 * @return
	 * @throws Exception
	 */
	public String toEditPage() throws Exception {
		LinkMan lkm = linkManService.get(linkMan.getLkmId());
		List<Customer> customerList = customerService.findAll();
		ServletActionContext.getRequest().setAttribute("linkMan", lkm);
		ServletActionContext.getRequest().setAttribute("customerList", customerList);
		return "toEditPage";
	}
	
	/**
	 * 联系人列表
	 * @return
	 * @throws Exception
	 */
	public String list() throws Exception {
		List<LinkMan> linkManList = linkManService.findAll();
		ServletActionContext.getRequest().setAttribute("linkManList", linkManList);
		return "toListPage";
	}
	
	/**
	 * 添加联系人
	 * @return
	 * @throws Exception
	 */
	public String add() throws Exception {
		linkManService.add(linkMan);
		return "list";
	}
	
	/**
	 * 添加前
	 * @return
	 * @throws Exception
	 */
	public String toAddPage() throws Exception {
		List<Customer> customerList = customerService.findAll();
		ServletActionContext.getRequest().setAttribute("customerList", customerList);
		return "toAddPage";
	}
}
