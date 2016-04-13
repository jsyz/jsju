package com.yz.service.imp;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.yz.dao.IDaymanageDao;
import com.yz.model.Daymanage;
import com.yz.service.IDaymanageService;

@Component("daymanageService")
public class DaymanageServiceImp implements IDaymanageService {
	private IDaymanageDao daymanageDao;

	public IDaymanageDao getDaymanageDao() {
		return daymanageDao;
	}

	@Resource
	public void setDaymanageDao(IDaymanageDao daymanageDao) {
		this.daymanageDao = daymanageDao;
	}

	// 添加对象
	/*
	 * (non-Javadoc)
	 * 
	 * @see com.yz.service.imp.IDaymanageServiceImp#add(com.yz.model.Daymanage)
	 */
	public void add(Daymanage daymanage) throws Exception {
		daymanageDao.save(daymanage);
	}

	// 删除对象
	/*
	 * (non-Javadoc)
	 * 
	 * @see com.yz.service.imp.IDaymanageServiceImp#delete(com.yz.model.Daymanage)
	 */
	public void delete(Daymanage daymanage) {
		daymanageDao.delete(daymanage);
	}

	// 删除某个id的对象
	/*
	 * (non-Javadoc)
	 * 
	 * @see com.yz.service.imp.IDaymanageServiceImp#deleteById(int)
	 */
	public void deleteById(int id) {
		daymanageDao.deleteById(id);
	}

	// 修改对象
	/*
	 * (non-Javadoc)
	 * 
	 * @see com.yz.service.imp.IDaymanageServiceImp#update(com.yz.model.Daymanage)
	 */
	public void update(Daymanage daymanage) {
		daymanageDao.update(daymanage);
	}

	// 获取所有对象
	/*
	 * (non-Javadoc)
	 * 
	 * @see com.yz.service.imp.IDaymanageServiceImp#getDaymanages()
	 */
	public List<Daymanage> getDaymanages() {
		return daymanageDao.getDaymanages();
	}

	// 加载一个id的对象
	/*
	 * (non-Javadoc)
	 * 
	 * @see com.yz.service.imp.IDaymanageServiceImp#loadById(int)
	 */
	public Daymanage loadById(int id) {
		return daymanageDao.loadById(id);
	}

	// 后台管理-页数获取
	/*
	 * (non-Javadoc)
	 * 
	 * @see com.yz.service.imp.IDaymanageServiceImp#getPageCount(int,
	 *      java.lang.String, int)
	 */
	public int getPageCount(int totalCount, int size) {
		return totalCount % size == 0 ? totalCount / size
				: (totalCount / size + 1);
	}

	// 后台管理-获取总记录数
	/*
	 * (non-Javadoc)
	 * 
	 * @see com.yz.service.imp.IDaymanageServiceImp#getTotalCount(int,
	 *      java.lang.String)
	 */
	public int getTotalCount(int con, String convalue, Daymanage daymanage) {
		String queryString = "select count(*) from Daymanage mo where 1=1 and mo.id!="
				+ daymanage.getId();
		Object[] p = null;
		if (con != 0 && convalue != null && !convalue.equals("")) {
			if (con == 1) {
				queryString += "and mo.unit.name like ? ";
			}
			if (con == 2) {
				queryString += "and mo.realname like ? ";

			}
			if (con == 3) {
				queryString += "and mo.number like ? ";
			}
			p = new Object[] { '%' + convalue + '%' };
		}
		return daymanageDao.getUniqueResult(queryString, p);
	}

	public Daymanage getDaymanageByDaymanagename(String username) {
		String queryString = "from Daymanage mo where mo.username=:username";
		String[] paramNames = new String[] { "username" };
		Object[] values = new Object[] { username };
		return daymanageDao.queryByNamedParam(queryString, paramNames, values);
	}

	// 后台管理-获取符合条件的记录
	/*
	 * (non-Javadoc)
	 * 
	 * @see com.yz.service.imp.IDaymanageServiceImp#queryList(int,
	 *      java.lang.String, int, int)
	 */
	public List<Daymanage> queryList(int con, String convalue,
			Daymanage daymanage, int page, int size) {
		String queryString = "from Daymanage mo where 1=1 and mo.id!="
				+ daymanage.getId();
		Object[] p = null;
		if (con != 0 && convalue != null && !convalue.equals("")) {
			if (con == 1) {
				queryString += "and mo.unit.name like ? ";
			}
			if (con == 2) {
				queryString += "and mo.realname like ? ";

			}
			if (con == 3) {
				queryString += "and mo.number like ? ";
			}
			p = new Object[] { '%' + convalue + '%' };
		}
		return daymanageDao.pageList(queryString, p, page, size);
	}

	public int addAndReturn(Daymanage daymanage) {
		// TODO Auto-generated method stub
		return daymanageDao.savereturn(daymanage);
	}


}
