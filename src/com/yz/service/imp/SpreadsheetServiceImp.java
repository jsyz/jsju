package com.yz.service.imp;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.yz.dao.ISpreadsheetDao;
import com.yz.model.Spreadsheet;
import com.yz.service.ISpreadsheetService;

@Component("spreadsheetService")
public class SpreadsheetServiceImp implements ISpreadsheetService {
	private ISpreadsheetDao spreadsheetDao;

	public ISpreadsheetDao getSpreadsheetDao() {
		return spreadsheetDao;
	}

	@Resource
	public void setSpreadsheetDao(ISpreadsheetDao spreadsheetDao) {
		this.spreadsheetDao = spreadsheetDao;
	}

	// 添加对象
	/*
	 * (non-Javadoc)
	 * 
	 * @see com.yz.service.imp.ISpreadsheetServiceImp#add(com.yz.model.Spreadsheet)
	 */
	public void add(Spreadsheet spreadsheet) throws Exception {
		spreadsheetDao.save(spreadsheet);
	}

	// 删除对象
	/*
	 * (non-Javadoc)
	 * 
	 * @see com.yz.service.imp.ISpreadsheetServiceImp#delete(com.yz.model.Spreadsheet)
	 */
	public void delete(Spreadsheet spreadsheet) {
		spreadsheetDao.delete(spreadsheet);
	}

	// 删除某个id的对象
	/*
	 * (non-Javadoc)
	 * 
	 * @see com.yz.service.imp.ISpreadsheetServiceImp#deleteById(int)
	 */
	public void deleteById(int id) {
		spreadsheetDao.deleteById(id);
	}

	// 修改对象
	/*
	 * (non-Javadoc)
	 * 
	 * @see com.yz.service.imp.ISpreadsheetServiceImp#update(com.yz.model.Spreadsheet)
	 */
	public void update(Spreadsheet spreadsheet) {
		spreadsheetDao.update(spreadsheet);
	}

	// 获取所有对象
	/*
	 * (non-Javadoc)
	 * 
	 * @see com.yz.service.imp.ISpreadsheetServiceImp#getSpreadsheets()
	 */
	public List<Spreadsheet> getSpreadsheets() {
		return spreadsheetDao.getSpreadsheets();
	}

	// 加载一个id的对象
	/*
	 * (non-Javadoc)
	 * 
	 * @see com.yz.service.imp.ISpreadsheetServiceImp#loadById(int)
	 */
	public Spreadsheet loadById(int id) {
		return spreadsheetDao.loadById(id);
	}

	// 后台管理-页数获取
	/*
	 * (non-Javadoc)
	 * 
	 * @see com.yz.service.imp.ISpreadsheetServiceImp#getPageCount(int,
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
	 * @see com.yz.service.imp.ISpreadsheetServiceImp#getTotalCount(int,
	 *      java.lang.String)
	 */
	public int getTotalCount(int con, String convalue, Spreadsheet spreadsheet) {
		String queryString = "select count(*) from Spreadsheet mo where 1=1 and mo.id!="
				+ spreadsheet.getId();
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
		return spreadsheetDao.getUniqueResult(queryString, p);
	}

	public Spreadsheet getSpreadsheetBySpreadsheetname(String username) {
		String queryString = "from Spreadsheet mo where mo.username=:username";
		String[] paramNames = new String[] { "username" };
		Object[] values = new Object[] { username };
		return spreadsheetDao
				.queryByNamedParam(queryString, paramNames, values);
	}

	// 后台管理-获取符合条件的记录
	/*
	 * (non-Javadoc)
	 * 
	 * @see com.yz.service.imp.ISpreadsheetServiceImp#queryList(int,
	 *      java.lang.String, int, int)
	 */
	public List<Spreadsheet> queryList(int con, String convalue,
			Spreadsheet spreadsheet, int page, int size) {
		String queryString = "from Spreadsheet mo where 1=1 and mo.id!="
				+ spreadsheet.getId();
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
		queryString += " order by mo.id desc";
		return spreadsheetDao.pageList(queryString, p, page, size);
	}

	// 用户登录
	public Spreadsheet spreadsheetlogin(String username, String password) {
		String queryString = "from Spreadsheet mo where mo.username=:username and mo.password=:password";
		String[] paramNames = new String[] { "username", "password" };
		Object[] values = new Object[] { username, password };
		return spreadsheetDao
				.queryByNamedParam(queryString, paramNames, values);
	}

	public int getTotalCount(int con, String convalue, int pid,
			Integer[] sheetTypes) {
		String queryString = "select count(*) from Spreadsheet mo where 1=1 and mo.project.id="
				+ pid;
		Object[] p = null;
		if (con != 0 && convalue != null && !convalue.equals("")) {
			if (con == 1) {
				queryString += " and mo.sheetName like ? ";
			}
			p = new Object[] { '%' + convalue + '%' };
		}
		if (sheetTypes != null && sheetTypes.length > 0) {
			String queryStr = "";
			for (int i = 0; i < sheetTypes.length; i++) {
				queryStr += sheetTypes[i] + ",";
			}
			queryStr = queryStr.substring(0, queryStr.length() - 1);
			queryString += " and mo.sheetType in(" + queryStr + ")";
		}
		System.out.println(queryString);
		return spreadsheetDao.getUniqueResult(queryString, p);
	}

	public List<Spreadsheet> queryList(int con, String convalue, int pid,
			Integer[] sheetTypes, int page, int size) {
		String queryString = "from Spreadsheet mo where 1=1 and mo.project.id="
				+ pid;
		Object[] p = null;
		if (con != 0 && convalue != null && !convalue.equals("")) {
			if (con == 1) {
				queryString += "and mo.sheetName like ? ";
			}
			p = new Object[] { '%' + convalue + '%' };
		}
		if (sheetTypes != null && sheetTypes.length > 0) {
			String queryStr = "";
			for (int i = 0; i < sheetTypes.length; i++) {
				queryStr += sheetTypes[i] + ",";
			}
			queryStr = queryStr.substring(0, queryStr.length() - 1);
			queryString += " and mo.sheetType in(" + queryStr + ")";
		}
		queryString += " order by mo.id desc";
		return spreadsheetDao.pageList(queryString, p, page, size);
	}

	public List<Spreadsheet> queryListByArrayRange(int pid, Integer[] arrayRange) {
		String queryString = "from Spreadsheet mo where 1=1 and mo.project.id="
				+ pid;
		if (arrayRange != null && arrayRange.length > 0) {
			String queryStr = "";
			for (int i = 0; i < arrayRange.length; i++) {
				queryStr += arrayRange[i] + ",";
			}
			queryStr = queryStr.substring(0, queryStr.length() - 1);
			queryString += " and mo.sheetType in(" + queryStr + ")";
		}
		queryString += " order by mo.id desc";
		return spreadsheetDao.queryList(queryString);
	}

	public List<Spreadsheet> queryListByPid(int pid) {
		// TODO Auto-generated method stub
		String queryString = "from Spreadsheet mo where 1=1 and mo.project.id="
				+ pid;
		queryString += " order by mo.id desc";
		return spreadsheetDao.queryList(queryString);
	}

}
