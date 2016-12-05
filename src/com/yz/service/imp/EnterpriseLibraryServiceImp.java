package com.yz.service.imp;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.yz.dao.IEnterpriseLibraryDao;
import com.yz.model.EnterpriseLibrary;
import com.yz.service.IEnterpriseLibraryService;

@Component("enterpriseLibraryService")
public class EnterpriseLibraryServiceImp implements IEnterpriseLibraryService {
	private IEnterpriseLibraryDao enterpriseLibraryDao;

	public IEnterpriseLibraryDao getEnterpriseLibraryDao() {
		return enterpriseLibraryDao;
	}

	@Resource
	public void setEnterpriseLibraryDao(IEnterpriseLibraryDao enterpriseLibraryDao) {
		this.enterpriseLibraryDao = enterpriseLibraryDao;
	}
	//添加对象
	/* (non-Javadoc)
	 * @see com.yz.service.imp.IDeviceServiceImp#add(com.yz.model.Device)
	 */
	public void add(EnterpriseLibrary enterpriseLibrary) throws Exception {
		enterpriseLibraryDao.save(enterpriseLibrary);
	}
	//删除对象
	/* (non-Javadoc)
	 * @see com.yz.service.imp.IEnterpriseLibraryServiceImp#delete(com.yz.model.EnterpriseLibrary)
	 */
	public void delete(EnterpriseLibrary enterpriseLibrary) {
		enterpriseLibraryDao.delete(enterpriseLibrary);
	}
	//删除某个id的对象
	/* (non-Javadoc)
	 * @see com.yz.service.imp.IEnterpriseLibraryServiceImp#deleteById(int)
	 */
	public void deleteById(int id) {
		enterpriseLibraryDao.deleteById(id);
	}
	//修改对象
	/* (non-Javadoc)
	 * @see com.yz.service.imp.IEnterpriseLibraryServiceImp#update(com.yz.model.EnterpriseLibrary)
	 */
	public void update(EnterpriseLibrary enterpriseLibrary) {
		enterpriseLibraryDao.update(enterpriseLibrary);
	}
	//获取所有对象
	/* (non-Javadoc)
	 * @see com.yz.service.imp.IEnterpriseLibraryServiceImp#getEnterpriseLibrarys()
	 */
	public List<EnterpriseLibrary> getEnterpriseLibrarys() {
		return enterpriseLibraryDao.getEnterpriseLibrarys();
	}
	//加载一个id的对象
	/* (non-Javadoc)
	 * @see com.yz.service.imp.IEnterpriseLibraryServiceImp#loadById(int)
	 */
	public EnterpriseLibrary loadById(int id) {
		return enterpriseLibraryDao.loadById(id);
	}
	//后台管理-页数获取
	/* (non-Javadoc)
	 * @see com.yz.service.imp.IEnterpriseLibraryServiceImp#getPageCount(int, java.lang.String, int)
	 */
	public int getPageCount(int totalCount,int size) {
		return totalCount%size==0?totalCount/size:(totalCount/size+1);
	}
	//后台管理-获取总记录数
	/* (non-Javadoc)
	 * @see com.yz.service.imp.IEnterpriseLibraryServiceImp#getTotalCount(int, java.lang.String)
	 */
	public int getTotalCount(int con, String convalue) {
		String queryString = "select count(*) from EnterpriseLibrary mo where 1=1";
		Object[] p = null;
		if(con!=0&&convalue!=null&&!convalue.equals("")){
			if(con==1){
				queryString += "and mo.name like ? "; 
			}
			if(con==2){
				queryString += "and mo.propertyCardNumber like ? "; 
				
			}
			if(con==3){
				queryString += "and mo.number like ? "; 
			}
			p = new Object[]{'%'+convalue+'%'};
		}
		return enterpriseLibraryDao.getUniqueResult(queryString,p);
	}
	
	public int getTotalCount(int con, String convalue,
			int projectId) {
		String queryString = "select count(*) from EnterpriseLibrary mo where 1=1 and mo.project.id="+projectId;
		Object[] p = null;
		if(con!=0&&convalue!=null&&!convalue.equals("")){
			if(con==1){
				queryString += "and mo.name like ? "; 
			}
			if(con==2){
				queryString += "and mo.propertyCardNumber like ? "; 
				
			}
			if(con==3){
				queryString += "and mo.number like ? "; 
			}
			p = new Object[]{'%'+convalue+'%'};
		}
		return enterpriseLibraryDao.getUniqueResult(queryString,p);
	} 
	
	public EnterpriseLibrary getEnterpriseLibraryByEnterpriseLibraryname(String username) {
		String queryString="from EnterpriseLibrary mo where mo.username=:username";
		String[] paramNames=new String[]{"username"};
		Object[] values=new Object[]{username};
		return enterpriseLibraryDao.queryByNamedParam(queryString,paramNames,values);
	}
	//后台管理-获取符合条件的记录
	/* (non-Javadoc)
	 * @see com.yz.service.imp.IEnterpriseLibraryServiceImp#queryList(int, java.lang.String, int, int)
	 */
//	public List<EnterpriseLibrary> queryList(int con, String convalue, EnterpriseLibrary enterpriseLibrary, int page, int size) {
//		String queryString = "from EnterpriseLibrary mo where 1=1 and mo.id!="+enterpriseLibrary.getId();
//		Object[] p = null;
//		if(con!=0&&convalue!=null&&!convalue.equals("")){
//			if(con==1){
//				queryString += "and mo.unit.name like ? "; 
//			}
//			if(con==2){
//				queryString += "and mo.realname like ? "; 
//				
//			}
//			if(con==3){
//				queryString += "and mo.number like ? "; 
//			}
//			p = new Object[]{'%'+convalue+'%'};
//		}
//		return enterpriseLibraryDao.pageList(queryString,p,page,size);
//	}
	//用户登录
	public EnterpriseLibrary enterpriseLibrarylogin(String username, String password) {
		String queryString="from EnterpriseLibrary mo where mo.username=:username and mo.password=:password";
		String[] paramNames=new String[]{"username","password"};
		Object[] values=new Object[]{username,password};
		return enterpriseLibraryDao.queryByNamedParam(queryString,paramNames,values);
	}
	public List<EnterpriseLibrary> queryList(int con, String convalue,
			int page, int size) {
		String queryString = "from EnterpriseLibrary mo where 1=1 ";
		Object[] p = null;
		if(con!=0&&convalue!=null&&!convalue.equals("")){
			if(con==1){
				queryString += "and mo.name like ? "; 
			}
			if(con==2){
				queryString += "and mo.propertyCardNumber like ? "; 
				
			}
			if(con==3){
				queryString += "and mo.number like ? "; 
			}
			p = new Object[]{'%'+convalue+'%'};
		}
//		queryString += " order by mo.id desc";
		return enterpriseLibraryDao.pageList(queryString,p,page,size);
	}
}
