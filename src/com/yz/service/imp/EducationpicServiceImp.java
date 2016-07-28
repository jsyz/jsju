package com.yz.service.imp;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.yz.dao.IEducationpicDao;
import com.yz.model.Educationpic;
import com.yz.service.IEducationpicService;
@Component("educationpicService")
public class EducationpicServiceImp implements IEducationpicService {
	private IEducationpicDao educationpicDao;
	public IEducationpicDao getEducationpicDao() {
		return educationpicDao;
	}
	@Resource
	public void setEducationpicDao(IEducationpicDao educationpicDao) {
		this.educationpicDao = educationpicDao;
	}
	//添加对象
	/* (non-Javadoc)
	 * @see com.yz.service.imp.IEducationpicServiceImp#add(com.yz.model.Educationpic)
	 */
	public void add(Educationpic educationpic) throws Exception {
		educationpicDao.save(educationpic);
	}
	//删除对象
	/* (non-Javadoc)
	 * @see com.yz.service.imp.IEducationpicServiceImp#delete(com.yz.model.Educationpic)
	 */
	public void delete(Educationpic educationpic) {
		educationpicDao.delete(educationpic);
	}
	//删除某个id的对象
	/* (non-Javadoc)
	 * @see com.yz.service.imp.IEducationpicServiceImp#deleteById(int)
	 */
	public void deleteById(int id) {
		educationpicDao.deleteById(id);
	}
	//修改对象
	/* (non-Javadoc)
	 * @see com.yz.service.imp.IEducationpicServiceImp#update(com.yz.model.Educationpic)
	 */
	public void update(Educationpic educationpic) {
		educationpicDao.update(educationpic);
	}
	//获取所有对象
	/* (non-Javadoc)
	 * @see com.yz.service.imp.IEducationpicServiceImp#getEducationpics()
	 */
	public List<Educationpic> getEducationpics() {
		return educationpicDao.getEducationpics();
	}
	//加载一个id的对象
	/* (non-Javadoc)
	 * @see com.yz.service.imp.IEducationpicServiceImp#loadById(int)
	 */
	public Educationpic loadById(int id) {
		return educationpicDao.loadById(id);
	}
	//后台管理-页数获取
	/* (non-Javadoc)
	 * @see com.yz.service.imp.IEducationpicServiceImp#getPageCount(int, java.lang.String, int)
	 */
	public int getPageCount(int totalCount,int size) {
		return totalCount%size==0?totalCount/size:(totalCount/size+1);
	}
	//后台管理-获取总记录数
	/* (non-Javadoc)
	 * @see com.yz.service.imp.IEducationpicServiceImp#getTotalCount(int, java.lang.String)
	 */
	public int getTotalCount(int con, String convalue, Educationpic educationpic) {
		String queryString = "select count(*) from Educationpic mo where 1=1 and mo.id!="+educationpic.getId();
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
		return educationpicDao.getUniqueResult(queryString,p);
	}
	public Educationpic getEducationpicByEducationpicname(String username) {
		String queryString="from Educationpic mo where mo.username=:username";
		String[] paramNames=new String[]{"username"};
		Object[] values=new Object[]{username};
		return educationpicDao.queryByNamedParam(queryString,paramNames,values);
	}
	//后台管理-获取符合条件的记录
	/* (non-Javadoc)
	 * @see com.yz.service.imp.IEducationpicServiceImp#queryList(int, java.lang.String, int, int)
	 */
	public List<Educationpic> queryList(int con, String convalue, Educationpic educationpic, int page, int size) {
		String queryString = "from Educationpic mo where 1=1 and mo.id!="+educationpic.getId();
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
		return educationpicDao.pageList(queryString,p,page,size);
	}
	
}
