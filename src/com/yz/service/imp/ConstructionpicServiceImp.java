package com.yz.service.imp;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.yz.dao.IConstructionpicDao;
import com.yz.model.Constructionpic;
import com.yz.service.IConstructionpicService;
@Component("constructionpicService")
public class ConstructionpicServiceImp implements IConstructionpicService {
	private IConstructionpicDao constructionpicDao;
	public IConstructionpicDao getConstructionpicDao() {
		return constructionpicDao;
	}
	@Resource
	public void setConstructionpicDao(IConstructionpicDao constructionpicDao) {
		this.constructionpicDao = constructionpicDao;
	}
	//添加对象
	/* (non-Javadoc)
	 * @see com.yz.service.imp.IConstructionpicServiceImp#add(com.yz.model.Constructionpic)
	 */
	public void add(Constructionpic constructionpic) throws Exception {
		constructionpicDao.save(constructionpic);
	}
	//删除对象
	/* (non-Javadoc)
	 * @see com.yz.service.imp.IConstructionpicServiceImp#delete(com.yz.model.Constructionpic)
	 */
	public void delete(Constructionpic constructionpic) {
		constructionpicDao.delete(constructionpic);
	}
	//删除某个id的对象
	/* (non-Javadoc)
	 * @see com.yz.service.imp.IConstructionpicServiceImp#deleteById(int)
	 */
	public void deleteById(int id) {
		constructionpicDao.deleteById(id);
	}
	//修改对象
	/* (non-Javadoc)
	 * @see com.yz.service.imp.IConstructionpicServiceImp#update(com.yz.model.Constructionpic)
	 */
	public void update(Constructionpic constructionpic) {
		constructionpicDao.update(constructionpic);
	}
	//获取所有对象
	/* (non-Javadoc)
	 * @see com.yz.service.imp.IConstructionpicServiceImp#getConstructionpics()
	 */
	public List<Constructionpic> getConstructionpics() {
		return constructionpicDao.getConstructionpics();
	}
	//加载一个id的对象
	/* (non-Javadoc)
	 * @see com.yz.service.imp.IConstructionpicServiceImp#loadById(int)
	 */
	public Constructionpic loadById(int id) {
		return constructionpicDao.loadById(id);
	}
	//后台管理-页数获取
	/* (non-Javadoc)
	 * @see com.yz.service.imp.IConstructionpicServiceImp#getPageCount(int, java.lang.String, int)
	 */
	public int getPageCount(int totalCount,int size) {
		return totalCount%size==0?totalCount/size:(totalCount/size+1);
	}
	//后台管理-获取总记录数
	/* (non-Javadoc)
	 * @see com.yz.service.imp.IConstructionpicServiceImp#getTotalCount(int, java.lang.String)
	 */
	public int getTotalCount(int con, String convalue, Constructionpic constructionpic) {
		String queryString = "select count(*) from Constructionpic mo where 1=1 and mo.id!="+constructionpic.getId();
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
		return constructionpicDao.getUniqueResult(queryString,p);
	}
	public Constructionpic getConstructionpicByConstructionpicname(String username) {
		String queryString="from Constructionpic mo where mo.username=:username";
		String[] paramNames=new String[]{"username"};
		Object[] values=new Object[]{username};
		return constructionpicDao.queryByNamedParam(queryString,paramNames,values);
	}
	//后台管理-获取符合条件的记录
	/* (non-Javadoc)
	 * @see com.yz.service.imp.IConstructionpicServiceImp#queryList(int, java.lang.String, int, int)
	 */
	public List<Constructionpic> queryList(int con, String convalue, Constructionpic constructionpic, int page, int size) {
		String queryString = "from Constructionpic mo where 1=1 and mo.id!="+constructionpic.getId();
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
		return constructionpicDao.pageList(queryString,p,page,size);
	}
	public Constructionpic loadByConidAndRow(int conid, int rowid) {
		String queryString="from Constructionpic mo where mo.construction.id=:conid and mo.rowid=:rowid";
		String[] paramNames=new String[]{"conid","rowid"};
		Object[] values=new Object[]{conid,rowid};
		return constructionpicDao.queryByNamedParam(queryString,paramNames,values);
	}
	
}
