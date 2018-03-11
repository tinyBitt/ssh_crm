package cn.baoxin.service;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import cn.baoxin.dao.UserDao;
import cn.baoxin.entity.User;

@Transactional
public class UserService {
	private UserDao userDao;

	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}

	/**
	 * 登录业务
	 * 
	 * @param user
	 * @throws UserException
	 */
	public User login(User user) throws UserException {
		/**
		 * 1、只要表单登录， user就不会为null, 如果为null，提示未知错误
		 * 2、根据user的id查询数据库，查询不到数据，则登录失败
		 * 3、密码不同则登录失败
		 * 4、否则登录成功,返回_user
		 */
		if(user == null) throw new UserException("发生未知错误，请稍后重试！");
		User _user = userDao.findByName(user.getUsername());
		if(_user == null) throw new UserException("用户名或密码错误！");
		if (!user.getPassword().equals(_user.getPassword()))
			throw new UserException("用户名或密码错误");
		return _user;
	}

	/**
	 * 查询所有用户
	 * @return
	 */
	public List<User> findAll() {
		return userDao.findAll();
	}

}
