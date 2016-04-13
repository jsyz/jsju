package com.yz.service.imp;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.yz.dao.ISubunitDao;
import com.yz.model.Subunit;
import com.yz.service.ISubunitService;
@Component("subunitService")
public class SubunitServiceImp implements ISubunitService {
	private ISubunitDao subunitDao;
	public ISubunitDao getSubunitDao() {
		return subunitDao;
	}
	@Resource
	public void setSubunitDao(ISubunitDao subunitDao) {
		this.subunitDao = subunitDao;
	}
	//添加对象
	/* (non-Javadoc)
	 * @see com.yz.service.imp.ISubunitServiceImp#add(com.yz.model.Subunit)
	 */
	public void add(Subunit subunit) throws Exception {
		subunitDao.save(subunit);
	}
	//删除对象
	/* (non-Javadoc)
	 * @see com.yz.service.imp.ISubunitServiceImp#delete(com.yz.model.Subunit)
	 */
	public void delete(Subunit subunit) {
		subunitDao.delete(subunit);
	}
	//删除某个id的对象
	/* (non-Javadoc)
	 * @see com.yz.service.imp.ISubunitServiceImp#deleteById(int)
	 */
	public void deleteById(int id) {
		subunitDao.deleteById(id);
	}
	//修改对象
	/* (non-Javadoc)
	 * @see com.yz.service.imp.ISubunitServiceImp#update(com.yz.model.Subunit)
	 */
	public void update(Subunit subunit) {
		subunitDao.update(subunit);
	}
	//获取所有对象
	/* (non-Javadoc)
	 * @see com.yz.service.imp.ISubunitServiceImp#getSubunits()
	 */
	public List<Subunit> getSubunits() {
		return subunitDao.getSubunits();
	}
	//加载一个id的对象
	/* (non-Javadoc)
	 * @see com.yz.service.imp.ISubunitServiceImp#loadById(int)
	 */
	public Subunit loadById(int id) {
		return subunitDao.loadById(id);
	}
	//后台管理-页数获取
	/* (non-Javadoc)
	 * @see com.yz.service.imp.ISubunitServiceImp#getPageCount(int, java.lang.String, int)
	 */
	public int getPageCount(int totalCount,int size) {
		return totalCount%size==0?totalCount/size:(totalCount/size+1);
	}
	//后台管理-获取总记录数
	/* (non-Javadoc)
	 * @see com.yz.service.imp.ISubunitServiceImp#getTotalCount(int, java.lang.String)
	 */
	public int getTotalCount(int con, String convalue, Subunit subunit) {
		String queryString = "select count(*) from Subunit mo where 1=1 and mo.id!="+subunit.getId();
		Object[] p = null;
		if(con!=0&&convalue!=null&&!convalue.equals("")){
			if(con==1){
				queryString += "and mo.unit.name like ? "; 
			}
			if(con==2){
				queryString += "and mo.realname like ? "; 
				
			}
			if(con==3){
				queryString += "and mo.number like ? "; 
			}
			p = new Object[]{'%'+convalue+'%'};
		}
		return subunitDao.getUniqueResult(queryString,p);
	}
	public Subunit getSubunitBySubunitname(String username) {
		String queryString="from Subunit mo where mo.username=:username";
		String[] paramNames=new String[]{"username"};
		Object[] values=new Object[]{username};
		return subunitDao.queryByNamedParam(queryString,paramNames,values);
	}
	//后台管理-获取符合条件的记录
	/* (non-Javadoc)
	 * @see com.yz.service.imp.ISubunitServiceImp#queryList(int, java.lang.String, int, int)
	 */
	public List<Subunit> queryList(int con, String convalue, Subunit subunit, int page, int size) {
		String queryString = "from Subunit mo where 1=1 and mo.id!="+subunit.getId();
		Object[] p = null;
		if(con!=0&&convalue!=null&&!convalue.equals("")){
			if(con==1){
				queryString += "and mo.unit.name like ? "; 
			}
			if(con==2){
				queryString += "and mo.realname like ? "; 
				
			}
			if(con==3){
				queryString += "and mo.number like ? "; 
			}
			p = new Object[]{'%'+convalue+'%'};
		}
		return subunitDao.pageList(queryString,p,page,size);
	}
	
}
