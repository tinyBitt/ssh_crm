package cn.baoxin.service;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import cn.baoxin.dao.VisitDao;
import cn.baoxin.entity.Visit;

@Transactional
public class VisitService {
	private VisitDao visitDao;
	public void setVisitDao(VisitDao visitDao) {
		this.visitDao = visitDao;
	}
	
	/**
	 * 新增客户拜访
	 * @param visit
	 */
	public void add(Visit visit) {
		visitDao.add(visit);
	}

	/**
	 * 拜访列表
	 * @return
	 */
	public List<Visit> findAll() {
		return visitDao.findAll();
	}
}
