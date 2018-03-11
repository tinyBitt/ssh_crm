package cn.baoxin.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.transform.AliasToBeanResultTransformer;

import cn.baoxin.entity.Customer;
import cn.baoxin.entity.Dictionary;

public class CustomerDaoImpl extends BaseDaoImpl<Customer> implements CustomerDao {

	/**
	 * 查询所有客户等级
	 */
	@SuppressWarnings("all")
	public List<Dictionary> findAllLevel() {
		return (List<Dictionary>) this.getHibernateTemplate().find("FROM Dictionary");
	}

	/**
	 * 根据用户名筛选客户
	 */
	@SuppressWarnings("all")
	public List<Customer> selectByName(String custName) {
		return (List<Customer>) this.getHibernateTemplate()
				.find("from Customer where custName like ?", "%" + custName + "%");
	}

	/**
	 * 多条件组合查询
	 */
	@SuppressWarnings("unchecked")
	public List<Customer> multiSel(DetachedCriteria criteria) {
		return (List<Customer>) this.getHibernateTemplate().findByCriteria(criteria);
	}

	/**
	 * 根据客户级别统计
	 */
	public List<Map> levelCount() {
		Session session = this.getSessionFactory().getCurrentSession();
		String sql = "SELECT dictName '客户级别',COUNT(*) '人数' FROM t_customer cus,t_dictionary dict WHERE cus.custLevel = dict.dictId GROUP BY dictName";
		SQLQuery sqlQuery = session.createSQLQuery(sql);
		sqlQuery.setResultTransformer(new AliasToBeanResultTransformer(HashMap.class));
		return sqlQuery.list();
	}

}
