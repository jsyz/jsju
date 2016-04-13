package com.yz.service.imp;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.yz.dao.IPromanDao;
import com.yz.model.Proman;
import com.yz.service.IPromanService;
@Component("promanService")
public class PromanServiceImp implements IPromanService {
	private IPromanDao promanDao;
	public IPromanDao getPromanDao() {
		return promanDao;
	}
	@Resource
	public void setPromanDao(IPromanDao promanDao) {
		this.promanDao = promanDao;
	}
	//添加对象
	/* (non-Javadoc)
	 * @see com.yz.service.imp.IPromanServiceImp#add(com.yz.model.Proman)
	 */
	public void add(Proman proman) throws Exception {
		promanDao.save(proman);
	}
	//删除对象
	/* (non-Javadoc)
	 * @see com.yz.service.imp.IPromanServiceImp#delete(com.yz.model.Proman)
	 */
	public void delete(Proman proman) {
		promanDao.delete(proman);
	}
	//删除某个id的对象
	/* (non-Javadoc)
	 * @see com.yz.service.imp.IPromanServiceImp#deleteById(int)
	 */
	public void deleteById(int id) {
		promanDao.deleteById(id);
	}
	//修改对象
	/* (non-Javadoc)
	 * @see com.yz.service.imp.IPromanServiceImp#update(com.yz.model.Proman)
	 */
	public void update(Proman proman) {
		promanDao.update(proman);
	}
	//获取所有对象
	/* (non-Javadoc)
	 * @see com.yz.service.imp.IPromanServiceImp#getPromans()
	 */
	public List<Proman> getPromans() {
		return promanDao.getPromans();
	}
	//加载一个id的对象
	/* (non-Javadoc)
	 * @see com.yz.service.imp.IPromanServiceImp#loadById(int)
	 */
	public Proman loadById(int id) {
		return promanDao.loadById(id);
	}
	
	public Proman getPromanByPromanname(String username) {
		String queryString="from Proman mo where mo.username=:username";
		String[] paramNames=new String[]{"username"};
		Object[] values=new Object[]{username};
		return promanDao.queryByNamedParam(queryString,paramNames,values);
	}
	//用户登录
	public Proman promanlogin(String username, String password) {
		String queryString="from Proman mo where mo.username=:username and mo.password=:password";
		String[] paramNames=new String[]{"username","password"};
		Object[] values=new Object[]{username,password};
		return promanDao.queryByNamedParam(queryString,paramNames,values);
	}
	
	//后台管理-页数获取
	/* (non-Javadoc)
	 * @see com.yz.service.imp.IPromanServiceImp#getPageCount(int, java.lang.String, int)
	 */
	public int getTotalCount(int con, String convalue, int projectId) {
		String queryString = "select count(*) from Proman mo where 1=1 and mo.project.id="+projectId;
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
		return promanDao.getUniqueResult(queryString,p);
	}
	//后台管理-获取符合条件的记录
	/* (non-Javadoc)
	 * @see com.yz.service.imp.IPromanServiceImp#queryList(int, java.lang.String, int, int)
	 */
	public List<Proman> queryList(int con, String convalue, int projectId,
			int page, int size) {
		String queryString = "from Proman mo where 1=1 and mo.project.id="+projectId;
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
		return promanDao.pageList(queryString,p,page,size);
	}
	public int getPageCount(int totalCount, int size) {
		// TODO Auto-generated method stub
		return totalCount%size==0?totalCount/size:(totalCount/size+1);
	} 
	
}
