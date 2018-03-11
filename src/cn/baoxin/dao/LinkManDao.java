package cn.baoxin.dao;

import java.util.List;

import cn.baoxin.entity.LinkMan;

public interface LinkManDao extends BaseDao<LinkMan> {

	List<LinkMan> selectLikeName(String lkmName);

}
