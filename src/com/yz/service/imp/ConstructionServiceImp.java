package com.yz.service.imp;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.yz.dao.IConstructionDao;
import com.yz.model.Construction;
import com.yz.service.IConstructionService;
@Component("constructionService")
public class ConstructionServiceImp implements IConstructionService {
	private IConstructionDao constructionDao;
	public IConstructionDao getConstructionDao() {
		return constructionDao;
	}
	@Resource
	public void setConstructionDao(IConstructionDao constructionDao) {
		this.constructionDao = constructionDao;
	}
	//添加对象
	/* (non-Javadoc)
	 * @see com.yz.service.imp.IConstructionServiceImp#add(com.yz.model.Construction)
	 */
	public void add(Construction construction) throws Exception {
		constructionDao.save(construction);
	}
	//删除对象
	/* (non-Javadoc)
	 * @see com.yz.service.imp.IConstructionServiceImp#delete(com.yz.model.Construction)
	 */
	public void delete(Construction construction) {
		constructionDao.delete(construction);
	}
	//删除某个id的对象
	/* (non-Javadoc)
	 * @see com.yz.service.imp.IConstructionServiceImp#deleteById(int)
	 */
	public void deleteById(int id) {
		constructionDao.deleteById(id);
	}
	//修改对象
	/* (non-Javadoc)
	 * @see com.yz.service.imp.IConstructionServiceImp#update(com.yz.model.Construction)
	 */
	public void update(Construction construction) {
		constructionDao.update(construction);
	}
	//获取所有对象
	/* (non-Javadoc)
	 * @see com.yz.service.imp.IConstructionServiceImp#getConstructions()
	 */
	public List<Construction> getConstructions() {
		return constructionDao.getConstructions();
	}
	//加载一个id的对象
	/* (non-Javadoc)
	 * @see com.yz.service.imp.IConstructionServiceImp#loadById(int)
	 */
	public Construction loadById(int id) {
		return constructionDao.loadById(id);
	}
	//后台管理-页数获取
	/* (non-Javadoc)
	 * @see com.yz.service.imp.IConstructionServiceImp#getPageCount(int, java.lang.String, int)
	 */
	public int getPageCount(int totalCount,int size) {
		return totalCount%size==0?totalCount/size:(totalCount/size+1);
	}
	//后台管理-获取总记录数
	/* (non-Javadoc)
	 * @see com.yz.service.imp.IConstructionServiceImp#getTotalCount(int, java.lang.String)
	 */
	public int getTotalCount(int con, String convalue, Construction construction) {
		String queryString = "select count(*) from Construction mo where 1=1 and mo.id!="+construction.getId();
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
		return constructionDao.getUniqueResult(queryString,p);
	}
	public Construction getConstructionByConstructionname(String username) {
		String queryString="from Construction mo where mo.username=:username";
		String[] paramNames=new String[]{"username"};
		Object[] values=new Object[]{username};
		return constructionDao.queryByNamedParam(queryString,paramNames,values);
	}
	//后台管理-获取符合条件的记录
	/* (non-Javadoc)
	 * @see com.yz.service.imp.IConstructionServiceImp#queryList(int, java.lang.String, int, int)
	 */
	public List<Construction> queryList(int con, String convalue, Construction construction, int page, int size) {
		String queryString = "from Construction mo where 1=1 and mo.id!="+construction.getId();
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
		queryString += " order by mo.id desc";
		return constructionDao.pageList(queryString,p,page,size);
	}
	//用户登录
	public Construction constructionlogin(String username, String password) {
		String queryString="from Construction mo where mo.username=:username and mo.password=:password";
		String[] paramNames=new String[]{"username","password"};
		Object[] values=new Object[]{username,password};
		return constructionDao.queryByNamedParam(queryString,paramNames,values);
	}
	public Construction loadByCid(int cid) {
		// TODO Auto-generated method stub
		String queryString="from Construction mo where mo.id=:cid";
		String[] paramNames=new String[]{"cid"};
		Object[] values=new Object[]{cid};
		return constructionDao.queryByNamedParam(queryString,paramNames,values);
	} 
	
}
