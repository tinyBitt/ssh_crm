package cn.baoxin.dao;

import java.util.List;
import java.util.Map;

import org.hibernate.criterion.DetachedCriteria;

import cn.baoxin.entity.Customer;
import cn.baoxin.entity.Dictionary;

public interface CustomerDao extends BaseDao<Customer> {

	List<Dictionary> findAllLevel();

	List<Customer> selectByName(String custName);

	List<Customer> multiSel(DetachedCriteria criteria);

	List<Map> levelCount();

}
