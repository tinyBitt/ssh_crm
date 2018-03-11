package cn.baoxin.dao;

import java.util.List;

import cn.baoxin.entity.LinkMan;

public class LinkManDaoImpl extends BaseDaoImpl<LinkMan> implements LinkManDao {

	/**
	 * 根据用户名筛选联系人
	 */
	@SuppressWarnings("unchecked")
	public List<LinkMan> selectLikeName(String lkmName) {
		return (List<LinkMan>) this.getHibernateTemplate().find("from LinkMan where lkmName like ?", "%" + lkmName + "%");
	}

}
