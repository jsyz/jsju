package com.yz.service.imp;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.yz.dao.IYxareaDao;
import com.yz.model.Usero;
import com.yz.model.Yxarea;
import com.yz.service.IYxareaService;
@Component("yxareaService")
public class YxareaServiceImp implements IYxareaService {
	private IYxareaDao yxareaDao;
	public IYxareaDao getYxareaDao() {
		return yxareaDao;
	}
	@Resource
	public void setYxareaDao(IYxareaDao yxareaDao) {
		this.yxareaDao = yxareaDao;
	}
	//添加对象
	/* (non-Javadoc)
	 * @see com.yz.service.imp.IYxareaServiceImp#add(com.yz.model.Yxarea)
	 */
	public void add(Yxarea yxarea) throws Exception {
		yxareaDao.save(yxarea);
	}
	//删除对象
	/* (non-Javadoc)
	 * @see com.yz.service.imp.IYxareaServiceImp#delete(com.yz.model.Yxarea)
	 */
	public void delete(Yxarea yxarea) {
		yxareaDao.delete(yxarea);
	}
	//删除某个id的对象
	/* (non-Javadoc)
	 * @see com.yz.service.imp.IYxareaServiceImp#deleteById(int)
	 */
	public void deleteById(int id) {
		yxareaDao.deleteById(id);
	}
	//修改对象
	/* (non-Javadoc)
	 * @see com.yz.service.imp.IYxareaServiceImp#update(com.yz.model.Yxarea)
	 */
	public void update(Yxarea yxarea) {
		yxareaDao.update(yxarea);
	}
	//获取所有对象
	/* (non-Javadoc)
	 * @see com.yz.service.imp.IYxareaServiceImp#getYxareas()
	 */
	public List<Yxarea> getYxareas() {
		return yxareaDao.getYxareas();
	}
	//加载一个id的对象
	/* (non-Javadoc)
	 * @see com.yz.service.imp.IYxareaServiceImp#loadById(int)
	 */
	public Yxarea loadById(int id) {
		return yxareaDao.loadById(id);
	}
	//后台管理-页数获取
	/* (non-Javadoc)
	 * @see com.yz.service.imp.IYxareaServiceImp#getPageCount(int, java.lang.String, int)
	 */
	public int getPageCount(int totalCount,int size) {
		return totalCount%size==0?totalCount/size:(totalCount/size+1);
	}
	//后台管理-获取总记录数
	/* (non-Javadoc)
	 * @see com.yz.service.imp.IYxareaServiceImp#getTotalCount(int, java.lang.String)
	 */
	public int getTotalCount(int con, String convalue, Usero usero) {
		String queryString = "select count(*) from Yxarea mo where 1=1 ";
		Object[] p = null;
		if(con!=0&&convalue!=null&&!convalue.equals("")){
			if(con==1){
				queryString += "and mo.areaname like ? "; 
			}
			p = new Object[]{'%'+convalue+'%'};
		}
		return yxareaDao.getUniqueResult(queryString,p);
	}
	public Yxarea getYxareaByYxareaname(String username) {
		String queryString="from Yxarea mo where mo.username=:username";
		String[] paramNames=new String[]{"username"};
		Object[] values=new Object[]{username};
		return yxareaDao.queryByNamedParam(queryString,paramNames,values);
	}
	//后台管理-获取符合条件的记录
	/* (non-Javadoc)
	 * @see com.yz.service.imp.IYxareaServiceImp#queryList(int, java.lang.String, int, int)
	 */
	public List<Yxarea> queryList(int con, String convalue, Usero usero, int page, int size) {
		String queryString = "from Yxarea mo where 1=1 ";
		Object[] p = null;
		if(con!=0&&convalue!=null&&!convalue.equals("")){
			if(con==1){
				queryString += "and mo.areaname like ? "; 
			}
			p = new Object[]{'%'+convalue+'%'};
		}
		return yxareaDao.pageList(queryString,p,page,size);
	}
	//用户登录
	public Yxarea yxarealogin(String username, String password) {
		String queryString="from Yxarea mo where mo.username=:username and mo.password=:password";
		String[] paramNames=new String[]{"username","password"};
		Object[] values=new Object[]{username,password};
		return yxareaDao.queryByNamedParam(queryString,paramNames,values);
	} 
	
}
