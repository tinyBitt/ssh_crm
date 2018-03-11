package cn.baoxin.dao;

import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.criterion.DetachedCriteria;
import org.springframework.orm.hibernate5.support.HibernateDaoSupport;

@SuppressWarnings("all")
public class BaseDaoImpl<T> extends HibernateDaoSupport implements BaseDao<T> {
	private Class<T> clazz;

	public BaseDaoImpl() {
		ParameterizedType pt = (ParameterizedType) this.getClass().getGenericSuperclass();
		clazz = (Class<T>) pt.getActualTypeArguments()[0];
	}

	/**
	 * 增操作
	 */
	public void add(T t) {
		this.getHibernateTemplate().save(t);
	}

	/**
	 * 改操作
	 */
	public void update(T t) {
		this.getHibernateTemplate().update(t);
	}

	/**
	 * 删操作
	 */
	public void delete(T t) {
		this.getHibernateTemplate().delete(t);
	}

	/**
	 * 根据id查询entity对象
	 */
	public T get(int id) {
		return this.getHibernateTemplate().get(clazz, id);
	}
	
	/**
	 * 查询所有
	 */
	public List<T> findAll() {
		return (List<T>) this.getHibernateTemplate().find("FROM " + clazz.getSimpleName());
	}

	/**
	 * 查询总记录数
	 */
	public int totalRecord() {
		String sql = "SELECT COUNT(*) FROM t_customer";
		Session session = this.getSessionFactory().getCurrentSession();
		SQLQuery query = session.createSQLQuery(sql);
		Number count = (Number) query.uniqueResult();
		return count.intValue();
	}
	
	/**
	 * 分页查询
	 */
	public List<T> selectPage(int pc, int ps) {
		DetachedCriteria criteria = DetachedCriteria.forClass(clazz);
		return (List<T>) this.getHibernateTemplate().findByCriteria(criteria, (pc-1)*ps, ps);
	}
	
	/**
	 * 根据name属性查询entity对象
	 * 
	 * @see 需要有name属性，切属性名称符合*Name/*name规范（或让此方法接收一个map，然后用内省，或如下的反射）
	 */
	public T findByName(String name) {
		/*
		 * 反射得到name属性在T类中的具体属性名---不同于hibernate通过读取hbm.xml映射文件获取属性名的方法
		 */
		String tName = null;
		Field[] fields = clazz.getDeclaredFields();
		for(Field f : fields){
			String fieldName = f.getName();
			if(fieldName.endsWith("name")||fieldName.endsWith("Name")) tName = fieldName;
		}
		if(tName == null) return null;
		//查询数据库
		List<T> list = (List<T>) this.getHibernateTemplate().find("FROM " + clazz.getSimpleName()
		+ " WHERE "+ tName +" = ?", name);
		return list.size()>0 ? list.get(0) : null;
	}
}
