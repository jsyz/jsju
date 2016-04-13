package com.yz.service.imp;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.yz.dao.IDangerargumentDao;
import com.yz.model.Dangerargument;
import com.yz.service.IDangerargumentService;
@Component("dangerargumentService")
public class DangerargumentServiceImp implements IDangerargumentService {
	private IDangerargumentDao dangerargumentDao;
	public IDangerargumentDao getDangerargumentDao() {
		return dangerargumentDao;
	}
	@Resource
	public void setDangerargumentDao(IDangerargumentDao dangerargumentDao) {
		this.dangerargumentDao = dangerargumentDao;
	}
	//添加对象
	/* (non-Javadoc)
	 * @see com.yz.service.imp.IDangerargumentServiceImp#add(com.yz.model.Dangerargument)
	 */
	public void add(Dangerargument dangerargument) throws Exception {
		dangerargumentDao.save(dangerargument);
	}
	//删除对象
	/* (non-Javadoc)
	 * @see com.yz.service.imp.IDangerargumentServiceImp#delete(com.yz.model.Dangerargument)
	 */
	public void delete(Dangerargument dangerargument) {
		dangerargumentDao.delete(dangerargument);
	}
	//删除某个id的对象
	/* (non-Javadoc)
	 * @see com.yz.service.imp.IDangerargumentServiceImp#deleteById(int)
	 */
	public void deleteById(int id) {
		dangerargumentDao.deleteById(id);
	}
	//修改对象
	/* (non-Javadoc)
	 * @see com.yz.service.imp.IDangerargumentServiceImp#update(com.yz.model.Dangerargument)
	 */
	public void update(Dangerargument dangerargument) {
		dangerargumentDao.update(dangerargument);
	}
	//获取所有对象
	/* (non-Javadoc)
	 * @see com.yz.service.imp.IDangerargumentServiceImp#getDangerarguments()
	 */
	public List<Dangerargument> getDangerarguments() {
		return dangerargumentDao.getDangerarguments();
	}
	//加载一个id的对象
	/* (non-Javadoc)
	 * @see com.yz.service.imp.IDangerargumentServiceImp#loadById(int)
	 */
	public Dangerargument loadById(int id) {
		return dangerargumentDao.loadById(id);
	}
	//后台管理-页数获取
	/* (non-Javadoc)
	 * @see com.yz.service.imp.IDangerargumentServiceImp#getPageCount(int, java.lang.String, int)
	 */
	public int getPageCount(int totalCount,int size) {
		return totalCount%size==0?totalCount/size:(totalCount/size+1);
	}
	//后台管理-获取总记录数
	/* (non-Javadoc)
	 * @see com.yz.service.imp.IDangerargumentServiceImp#getTotalCount(int, java.lang.String)
	 */
	public int getTotalCount(int con, String convalue, Dangerargument dangerargument) {
		String queryString = "select count(*) from Dangerargument mo where 1=1 and mo.id!="+dangerargument.getId();
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
		return dangerargumentDao.getUniqueResult(queryString,p);
	}
	public Dangerargument getDangerargumentByDangerargumentname(String username) {
		String queryString="from Dangerargument mo where mo.username=:username";
		String[] paramNames=new String[]{"username"};
		Object[] values=new Object[]{username};
		return dangerargumentDao.queryByNamedParam(queryString,paramNames,values);
	}
	//后台管理-获取符合条件的记录
	/* (non-Javadoc)
	 * @see com.yz.service.imp.IDangerargumentServiceImp#queryList(int, java.lang.String, int, int)
	 */
	public List<Dangerargument> queryList(int con, String convalue, Dangerargument dangerargument, int page, int size) {
		String queryString = "from Dangerargument mo where 1=1 and mo.id!="+dangerargument.getId();
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
		return dangerargumentDao.pageList(queryString,p,page,size);
	}
	
}
