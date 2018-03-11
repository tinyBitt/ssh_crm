package cn.baoxin.service;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import cn.baoxin.dao.LinkManDao;
import cn.baoxin.entity.LinkMan;

@Transactional
public class LinkManService {
	private LinkManDao linkManDao;
	public void setLinkManDao(LinkManDao linkManDao) {
		this.linkManDao = linkManDao;
	}
	
	/**
	 * 添加联系人
	 * @param linkMan
	 */
	public void add(LinkMan linkMan) {
		linkManDao.add(linkMan);
	}

	/**
	 * 查询所有联系人
	 * @return
	 */
	public List<LinkMan> findAll() {
		return linkManDao.findAll();
	}

	/**
	 * 根据id查询联系人
	 * @param lkmId
	 * @return
	 */
	public LinkMan get(Integer lkmId) {
		return linkManDao.get(lkmId);
	}

	/**
	 * 修改联系人
	 * @param linkMan
	 */
	public void update(LinkMan linkMan) {
		linkManDao.update(linkMan);
	}

	/**
	 * 根据用户名筛选联系人
	 * @param lkmName
	 * @return
	 */
	public List<LinkMan> selectLikeName(String lkmName) {
		return linkManDao.selectLikeName(lkmName);
	}

	/**
	 * 删除联系人
	 * @param linkMan
	 */
	public void delete(LinkMan linkMan) {
		linkManDao.delete(linkMan);
	}
}
