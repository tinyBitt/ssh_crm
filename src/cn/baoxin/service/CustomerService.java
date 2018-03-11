package cn.baoxin.service;

import java.util.List;
import java.util.Map;

import org.hibernate.criterion.DetachedCriteria;
import org.springframework.transaction.annotation.Transactional;

import cn.baoxin.dao.CustomerDao;
import cn.baoxin.entity.Customer;
import cn.baoxin.entity.Dictionary;

@Transactional
public class CustomerService {
	private CustomerDao customerDao;
	public void setCustomerDao(CustomerDao customerDao) {
		this.customerDao = customerDao;
	}
	
	/**
	 * 查询所有客户等级
	 * @return
	 */
	public List<Dictionary> findAllLevel() {
		return customerDao.findAllLevel();
	}
	/**
	 * 添加客户
	 * @param customer
	 */
	public void add(Customer customer) {
		customerDao.add(customer);
	}
	
	/**
	 * 查询所有客户
	 * @return
	 */
	public List<Customer> findAll() {
		return customerDao.findAll();
	}

	/**
	 * 查询总记录数
	 * @return
	 */
	public int totalRecord() {
		return customerDao.totalRecord();
	}
	
	/**
	 * 分页查询
	 * @param pc
	 * @param ps
	 * @return
	 */
	public List<Customer> selectPage(int pc, int ps) {
		return customerDao.selectPage(pc, ps);
	}
	
	/**
	 * 根据id查询客户
	 * @param custId
	 * @return
	 */
	public Customer get(Integer custId) {
		return customerDao.get(custId);
	}
	
	/**
	 * 修改客户
	 * @param customer
	 */
	public void update(Customer customer) {
		customerDao.update(customer);
	}
	
	/**
	 * 删除客户
	 * @param customer
	 */
	public void delete(Customer customer) {
		customerDao.delete(customer);
	}
	
	/**
	 * 根据用户名筛选客户
	 * @return
	 */
	public List<Customer> selectByName(String custName) {
		/*
		 * 1、如果用户什么都不输入，或只输入空格，则为查询所有。
		 * 2、否则按条件查询
		 */
		if("".equals(custName.trim())){
			return (List<Customer>) customerDao.findAll();
		}else{
			return customerDao.selectByName(custName);
		}
	}

	/**
	 * 多条件组合查询
	 * @param criteria
	 * @return
	 */
	public List<Customer> multiSel(DetachedCriteria criteria) {
		return customerDao.multiSel(criteria);
	}
	
	/**
	 * 客户级别统计
	 * @return
	 */
	public List<Map> levelCount() {
		return customerDao.levelCount();
	}
}
