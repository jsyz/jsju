package com.yz.service.imp;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.yz.dao.IConcreterecordDao;
import com.yz.model.Concreterecord;
import com.yz.service.IConcreterecordService;

@Component("ConcreterecordService")
public class ConcreterecordServiceImp implements IConcreterecordService{

	
	private IConcreterecordDao concreterecordDao;
	
	
	
	public IConcreterecordDao getConcreterecordDao() {
		return concreterecordDao;
	}
	@Resource
	public void setConcreterecordDao(IConcreterecordDao concreterecordDao) {
		this.concreterecordDao = concreterecordDao;
	}

	public void add(Concreterecord concreterecord) throws Exception {
		// TODO Auto-generated method stub
		concreterecordDao.save(concreterecord);
	}

	public void delete(Concreterecord concreterecord) {
		// TODO Auto-generated method stub
		concreterecordDao.delete(concreterecord);
		
	}

	public void deleteById(int id) {
		// TODO Auto-generated method stub
		
	}

	public Concreterecord getConstructionpicByConstructionpicname(
			String constructionpicname) {
		// TODO Auto-generated method stub
		return null;
	}

	public List<Concreterecord> getConstructionpics() {
		// TODO Auto-generated method stub
		return null;
	}

	public int getPageCount(int totalCount, int size) {
		// TODO Auto-generated method stub
		return 0;
	}

	public int getTotalCount(int con, String convalue,
			Concreterecord concreterecord) {
		// TODO Auto-generated method stub
		return 0;
	}

	public Concreterecord loadByConidAndRow(int conid, int rowid) {
		// TODO Auto-generated method stub
		return null;
	}

	public Concreterecord loadById(int id) {
		// TODO Auto-generated method stub
		return concreterecordDao.loadById(id);
	}

	public List<Concreterecord> queryList(int con, String convalue,
			Concreterecord concreterecord, int page, int size) {
		// TODO Auto-generated method stub
		return null;
	}

	public void update(Concreterecord concreterecord) {
		// TODO Auto-generated method stub
		concreterecordDao.update(concreterecord);
	}

	public List<Concreterecord> queryList(int concreterecordType,int con, String convalue, int page,
			int size) {
		// TODO Auto-generated method stub
		String queryString = "from Concreterecord mo where 1=1 and mo.concreterecordType="+concreterecordType;
		Object[] p = null;
		if (con != 0 && convalue != null && !convalue.equals("")) {
			if (con == 0) {
				queryString += "and mo.constructionUnit = ?"; 
			}
			if (con == 1) {
				queryString += "and mo.name like ? "; 
			}
			if (con == 3) {
				queryString += "and mo.engineeringDirector like ? "; 
			}
			if (con == 4) {
				queryString += "and mo.projectManager like ? "; 
			}
			p = new Object[] {convalue};
		}
//		
//		
//		queryString += " order by mo.id desc";
		return concreterecordDao.pageList(queryString, p, page, size);
	}

}
