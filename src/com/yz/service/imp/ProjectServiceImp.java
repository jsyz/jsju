package com.yz.service.imp;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.yz.dao.IProjectDao;
import com.yz.model.Project;
import com.yz.service.IProjectService;
@Component("projectService")
public class ProjectServiceImp implements IProjectService {
	private IProjectDao projectDao;
	public IProjectDao getProjectDao() {
		return projectDao;
	}
	@Resource
	public void setProjectDao(IProjectDao projectDao) {
		this.projectDao = projectDao;
	}
	//添加对象
	/* (non-Javadoc)
	 * @see com.yz.service.imp.IProjectServiceImp#add(com.yz.model.Project)
	 */
	public void add(Project project) throws Exception {
		projectDao.save(project);
	}
	//删除对象
	/* (non-Javadoc)
	 * @see com.yz.service.imp.IProjectServiceImp#delete(com.yz.model.Project)
	 */
	public void delete(Project project) {
		projectDao.delete(project);
	}
	//删除某个id的对象
	/* (non-Javadoc)
	 * @see com.yz.service.imp.IProjectServiceImp#deleteById(int)
	 */
	public void deleteById(int id) {
		projectDao.deleteById(id);
	}
	//修改对象
	/* (non-Javadoc)
	 * @see com.yz.service.imp.IProjectServiceImp#update(com.yz.model.Project)
	 */
	public void update(Project project) {
		projectDao.update(project);
	}
	//获取所有对象
	/* (non-Javadoc)
	 * @see com.yz.service.imp.IProjectServiceImp#getProjects()
	 */
	public List<Project> getProjects() {
		return projectDao.getProjects();
	}
	//加载一个id的对象
	/* (non-Javadoc)
	 * @see com.yz.service.imp.IProjectServiceImp#loadById(int)
	 */
	public Project loadById(int id) {
		return projectDao.loadById(id);
	}
	//后台管理-页数获取
	/* (non-Javadoc)
	 * @see com.yz.service.imp.IProjectServiceImp#getPageCount(int, java.lang.String, int)
	 */
	public int getPageCount(int totalCount,int size) {
		return totalCount%size==0?totalCount/size:(totalCount/size+1);
	}
	//后台管理-获取总记录数
	/* (non-Javadoc)
	 * @see com.yz.service.imp.IProjectServiceImp#getTotalCount(int, java.lang.String)
	 */
	public int getTotalCount(int con, String convalue, int  areaIndex) {
		String queryString = "select count(*) from Project mo where 1=1 and mo.yxarea.areaIndex="+areaIndex;
		Object[] p = null;
		if(con!=0&&convalue!=null&&!convalue.equals("")){
			if(con==1){
				queryString += "and mo.name like ? "; 
			}
			if(con==2){
				queryString += "and mo.supervisor like ? "; 
			}
			if(con==3){
				queryString += "and mo.number like ? "; 
			}
			p = new Object[]{'%'+convalue+'%'};
		}
		return projectDao.getUniqueResult(queryString,p);
	}
	public Project getProjectByProjectname(String username) {
		String queryString="from Project mo where mo.username=:username";
		String[] paramNames=new String[]{"username"};
		Object[] values=new Object[]{username};
		return projectDao.queryByNamedParam(queryString,paramNames,values);
	}
	//后台管理-获取符合条件的记录
	/* (non-Javadoc)
	 * @see com.yz.service.imp.IProjectServiceImp#queryList(int, java.lang.String, int, int)
	 */
	public List<Project> queryList(int con, String convalue, int  areaIndex, int page, int size) {
		String queryString = "from Project mo where 1=1 and mo.yxarea.areaIndex="+areaIndex;
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
		return projectDao.pageList(queryString,p,page,size);
	}
	//用户登录
	public Project projectlogin(String username, String password) {
		String queryString="from Project mo where mo.username=:username and mo.password=:password";
		String[] paramNames=new String[]{"username","password"};
		Object[] values=new Object[]{username,password};
		return projectDao.queryByNamedParam(queryString,paramNames,values);
	} 
	
}
