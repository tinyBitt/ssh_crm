package cn.baoxin.action;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

import cn.baoxin.entity.Customer;
import cn.baoxin.entity.Dictionary;
import cn.baoxin.entity.PageBean;
import cn.baoxin.service.CustomerService;

public class CustomerAction extends ActionSupport implements ModelDriven<Customer> {
	private CustomerService customerService;
	public void setCustomerService(CustomerService customerService) {
		this.customerService = customerService;
	}
	/*
	 * 模型驱动
	 */
	private Customer customer = new Customer();
	public Customer getModel() {
		return customer;
	}
	
	/**
	 * 客户级别统计
	 * @return
	 * @throws Exception
	 */
	public String levelCount() throws Exception {
		List<Map> mapList = customerService.levelCount();
		ServletActionContext.getRequest().setAttribute("mapList", mapList);
		return "levelCount";
	}
	
	/**
	 * 多条件组合查询
	 * @return
	 * @throws Exception
	 */
	public String multiSel() throws Exception {
		/**
		 * 1、判断用户已填条件
		 * 2、将criteria传到dao
		 * 3、分页的代码review没做
		 */
		//条件：客户名
		DetachedCriteria criteria = DetachedCriteria.forClass(Customer.class);
		if(!"".equals(customer.getCustName().trim())){
			criteria.add(Restrictions.ilike("custName"
					, "%"+customer.getCustName()+"%"));
		}
		//条件：信息来源
		if(!"".equals(customer.getCustSource().trim())){
			criteria.add(Restrictions.ilike("custSource"
					, "%"+customer.getCustSource()+"%"));
		}
		//条件：客户电话
		if(!"".equals(customer.getCustPhone().trim())){
			criteria.add(Restrictions.ilike("custPhone"
					, "%"+customer.getCustPhone()+"%"));
		}
		//条件：客户级别
		if(customer.getCustLevel().getDictId() > 0){
			criteria.add(Restrictions.eq("custLevel.dictId"
					, customer.getCustLevel().getDictId()));
		}
		List<Customer> customerList = customerService.multiSel(criteria);
		ServletActionContext.getRequest().setAttribute("customerList", customerList);
		return "toListPage2";
	}
	
	/**
	 * 到多条件组合查询页面
	 * @return
	 * @throws Exception
	 */
	public String toMultiSelPage() throws Exception {
		List<Dictionary> allLevelList = customerService.findAllLevel();
		ServletActionContext.getRequest().setAttribute("allLevelList", allLevelList);
		return "toMultiSelPage";
	}
	
	/**
	 * 根据用户名筛选客户
	 * @return
	 * @throws Exception
	 */
	public String selectByName() throws Exception {
		/*
		 * 应该单独再写一个list页面，或编写一个按条件分页查询的dao方法（并抽取baseDao）
		 * 这里就不分页了
		 */
		PageBean<Customer> pageBean = new PageBean<Customer>();
		int totalRecod = customerService.totalRecord();//查询数据库获取总记录数
		//条件查询出来的beanList
		List<Customer> customerList = customerService.selectByName(customer.getCustName());
		//封装pageBean
		pageBean.setBeanList(customerList);
		pageBean.setPc(1);
		pageBean.setPs(3);
		pageBean.setTr(totalRecod);
		ServletActionContext.getRequest().setAttribute("pageBean", pageBean);
		return "toListPage";
	}
	
	/**
	 * 删除客户
	 * @return
	 * @throws Exception
	 */
	public String delete() throws Exception {
		HttpServletRequest request = ServletActionContext.getRequest();
		int pc = Integer.parseInt(request.getParameter("pc"));//从页面获取参数pc，用于删除后定位显示位置
		Customer customer2 = customerService.get(customer.getCustId());
		customerService.delete(customer2);
		request.getSession().setAttribute("pc", pc);//用session存储pc
		return "list";
	}
	
	/**
	 * 修改前
	 * @return
	 * @throws Exception
	 */
	public String toEditPage() throws Exception {
		Customer cust = customerService.get(customer.getCustId());//根据客户端传递Id查询客户
		List<Dictionary> allLevelList = customerService.findAllLevel();//从字典表查询出所有客户级别
		
		HttpServletRequest request = ServletActionContext.getRequest();
		request.getSession().setAttribute("pc"
				, Integer.parseInt(request.getParameter("pc")));//用session存储pc
		//将数据存到request域
		request.setAttribute("allLevelList", allLevelList);
		request.setAttribute("customer", cust);
		return "toEditPage";
	}
	
	/**
	 * 修改客户
	 * @return
	 * @throws Exception
	 */
	public String edit() throws Exception {
		customerService.update(customer);
		return "list";
	}
	
	/**
	 * 客户列表
	 * @return
	 * @throws Exception
	 */
	public String list() throws Exception {
		//////////////////////参数ps、pc在action中获得，其它逻辑应该写到service中/////////////////////////////////
		PageBean<Customer> pageBean = new PageBean<Customer>();
		Integer pc = null;
		HttpServletRequest request = ServletActionContext.getRequest();
		String pageCode = request.getParameter("pc");
		if(pageCode != null ){
			pc = Integer.parseInt(pageCode);//从客户端获取当前页
		}else{
			pc = (Integer) request.getSession().getAttribute("pc");
		}
		int totalRecod = customerService.totalRecord();//查询数据库获取总记录数
		int ps = 3;//设置每页记录数（默认值）
		/*
		 * 分页查询
		 */
		List<Customer> customerList = customerService.selectPage(pc, ps);
		if(customerList.size() == 0){
			pc = pc -1;
			customerList = customerService.selectPage(pc, ps);
		}
		/*
		 * 封装pageBean
		 */
		pageBean.setBeanList(customerList);
		pageBean.setPc(pc);
		pageBean.setPs(ps);
		pageBean.setTr(totalRecod);
		request.setAttribute("pageBean", pageBean);
		return "toListPage";
	}
	
	/**
	 * 到添加页面
	 * @return
	 * @throws Exception
	 */
	public String toAddPage() throws Exception{
		List<Dictionary> allLevelList = customerService.findAllLevel();
		ServletActionContext.getRequest().setAttribute("allLevelList", allLevelList);
		return "toAddPage";
	}
	
	/**
	 * 添加客户
	 * @return
	 * @throws Exception
	 */
	public String add() throws Exception {
		customerService.add(customer);
		/**
		 * 1、获取总记录数，用于添加客户后定位添加记录所在位置
		 * 2、用tr和ps计算出pc，保存到session域
		 */
		int totalRecod = customerService.totalRecord();//查询数据库获取总记录数
		int ps = 3;//设置每页记录数（默认值）
		int pc = (totalRecod%ps == 0) ? totalRecod/ps : totalRecod/ps + 1;
		ServletActionContext.getRequest().getSession().setAttribute("pc", pc);
		return "list";
	}
}
