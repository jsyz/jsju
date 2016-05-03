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

	// 添加对象
	/*
	 * (non-Javadoc)
	 * 
	 * @see com.yz.service.imp.IProjectServiceImp#add(com.yz.model.Project)
	 */
	public void add(Project project) throws Exception {
		projectDao.save(project);
	}

	// 删除对象
	/*
	 * (non-Javadoc)
	 * 
	 * @see com.yz.service.imp.IProjectServiceImp#delete(com.yz.model.Project)
	 */
	public void delete(Project project) {
		projectDao.delete(project);
	}

	// 删除某个id的对象
	/*
	 * (non-Javadoc)
	 * 
	 * @see com.yz.service.imp.IProjectServiceImp#deleteById(int)
	 */
	public void deleteById(int id) {
		projectDao.deleteById(id);
	}

	// 修改对象
	/*
	 * (non-Javadoc)
	 * 
	 * @see com.yz.service.imp.IProjectServiceImp#update(com.yz.model.Project)
	 */
	public void update(Project project) {
		projectDao.update(project);
	}

	// 获取所有对象
	/*
	 * (non-Javadoc)
	 * 
	 * @see com.yz.service.imp.IProjectServiceImp#getProjects()
	 */
	public List<Project> getProjects() {
		return projectDao.getProjects();
	}

	// 加载一个id的对象
	/*
	 * (non-Javadoc)
	 * 
	 * @see com.yz.service.imp.IProjectServiceImp#loadById(int)
	 */
	public Project loadById(int id) {
		return projectDao.loadById(id);
	}

	// 后台管理-页数获取
	/*
	 * (non-Javadoc)
	 * 
	 * @see com.yz.service.imp.IProjectServiceImp#getPageCount(int,
	 *      java.lang.String, int)
	 */
	public int getPageCount(int totalCount, int size) {
		return totalCount % size == 0 ? totalCount / size
				: (totalCount / size + 1);
	}

	// 后台管理-获取总记录数
	/*
	 * (non-Javadoc)
	 * 
	 * @see com.yz.service.imp.IProjectServiceImp#getTotalCount(int,
	 *      java.lang.String)
	 */
	public int getTotalCount(int con, String convalue, int areaIndex) {
		String queryString = "select count(*) from Project mo where 1=1 and mo.yxarea.areaIndex="
				+ areaIndex;
		Object[] p = null;
		if (con != 0 && convalue != null && !convalue.equals("")) {
			if (con == 1) {
				queryString += "and mo.name like ? ";
			}
			if (con == 2) {
				queryString += "and mo.supervisor like ? ";
			}
			p = new Object[] { '%' + convalue + '%' };
		}
		return projectDao.getUniqueResult(queryString, p);
	}

	public Project getProjectByProjectname(String username) {
		String queryString = "from Project mo where mo.username=:username";
		String[] paramNames = new String[] { "username" };
		Object[] values = new Object[] { username };
		return projectDao.queryByNamedParam(queryString, paramNames, values);
	}

	// 后台管理-获取符合条件的记录
	/*
	 * (non-Javadoc)
	 * 
	 * @see com.yz.service.imp.IProjectServiceImp#queryList(int,
	 *      java.lang.String, int, int)
	 */
	public List<Project> queryList(int con, String convalue, int areaIndex,
			int page, int size) {
		String queryString = "from Project mo where 1=1 and mo.yxarea.areaIndex="
				+ areaIndex;
		Object[] p = null;
		if (con != 0 && convalue != null && !convalue.equals("")) {
			if (con == 1) {
				queryString += "and mo.name like ? ";
			}
			if (con == 2) {
				queryString += "and mo.supervisor like ? ";
			}
			p = new Object[] { '%' + convalue + '%' };
		}
		return projectDao.pageList(queryString, p, page, size);
	}

	public int addAndReturn(Project project) {
		// TODO Auto-generated method stub
		return projectDao.savereturn(project);
	}

	public Project loadByPid(int pid) {
		// TODO Auto-generated method stub
		String queryString = "from Project mo where mo.id=:pid";
		String[] paramNames = new String[] { "pid" };
		Object[] values = new Object[] { pid };
		return projectDao.queryByNamedParam(queryString, paramNames, values);
	}

	public int getTotalCount(int status, int con, String convalue) {
		String queryString = "select count(*) from Project mo where 1=1";
		Object[] p = null;
		if (con != 0 && convalue != null && !convalue.equals("")) {
			if (con == 1) {
				queryString += "and mo.name like ? ";
			}
			if (con == 2) {
				queryString += "and mo.supervisor like ? ";
			}
			p = new Object[] { '%' + convalue + '%' };
		}
		if (status == 1) {
			queryString += "and mo.graphicProgress != 4";
		}
		if (status == 2) {
			queryString += "and mo.graphicProgress = 4";
		}
		return projectDao.getUniqueResult(queryString, p);
	}

	public List<Project> queryList(int status, int con, String convalue,
			int page, int size) {
		String queryString = "from Project mo where 1=1 ";
		Object[] p = null;
		if (con != 0 && convalue != null && !convalue.equals("")) {
			if (con == 1) {
				queryString += "and mo.name like ? ";
			}
			if (con == 2) {
				queryString += "and mo.supervisor like ? ";
			}
			p = new Object[] { '%' + convalue + '%' };
		}
		if (status == 1) {
			queryString += "and mo.graphicProgress != 4";
		}
		if (status == 2) {
			queryString += "and mo.graphicProgress = 4";
		}
		return projectDao.pageList(queryString, p, page, size);
	}

	public List<Project> queryList(int status, int con, String convalue) {
		// TODO Auto-generated method stub
		String queryString = "from Project mo where 1=1";
		Object[] p = null;
		if (con != 0 && convalue != null && !convalue.equals("")) {
			if (con == 1) {
				queryString += "and mo.name like ? ";
			}
			if (con == 2) {
				queryString += "and mo.supervisor like ? ";
			}
			p = new Object[] { '%' + convalue + '%' };
		}
		if (status == 1) {
			queryString += "and mo.graphicProgress != 4";
		}
		if (status == 2) {
			queryString += "and mo.graphicProgress = 4";
		}
		return projectDao.getObjectsByCondition(queryString, p);
	}

	public List<Project> loadByProjectType(int i) {
		// TODO Auto-generated method stub
		String queryString = "from Project mo where 1=1 and mo.projectType="+i;
		return projectDao.queryList(queryString);
	}
	
	public List<Project> loadByEngineeringType(int i) {
		// TODO Auto-generated method stub
		String queryString = "from Project mo where 1=1 and mo.engineeringType="+i;
		return projectDao.queryList(queryString);
	}
	
	public List<Project> loadByBuildingType(int i) {
		// TODO Auto-generated method stub
		String queryString = "from Project mo where 1=1 and mo.buildingType="+i;
		return projectDao.queryList(queryString);
	}
	
	public List<Project> loadByGraphicProgress(int i) {
		// TODO Auto-generated method stub
		String queryString = "from Project mo where 1=1 and mo.graphicProgress="+i;
		return projectDao.queryList(queryString);
	}

	public int getTotalCount(int status, int con, String convalue,
			int areaIndex, int engineeringType, int graphicProgress) {
		String queryString = "select count(*) from Project mo where 1=1";
		Object[] p = null;
		if (con != 0 && convalue != null && !convalue.equals("")) {
			if (con == 1) {
				queryString += "and mo.name like ? ";
			}
			if (con == 2) {
				queryString += "and mo.supervisor like ? ";
			}
			p = new Object[] { '%' + convalue + '%' };
		}
		if (status == 1) {
			queryString += "and mo.graphicProgress != 4 ";
		}
		if (status == 2) {
			queryString += "and mo.graphicProgress = 4 ";
		}
		if(areaIndex!=0)
		{
			queryString += " and mo.yxarea.areaIndex = "+areaIndex;
		}
		if(engineeringType!=0)
		{
			queryString += " and mo.engineeringType = "+(engineeringType-1);
		}
		if(graphicProgress!=0)
		{
			queryString += " and mo.graphicProgress = "+(graphicProgress-1);
		}
		return projectDao.getUniqueResult(queryString, p);
	}

	public List<Project> queryList(int status, int con, String convalue,
			int areaIndex, int engineeringType, int graphicProgress, int page,
			int size) {
		String queryString = "from Project mo where 1=1";
		Object[] p = null;
		if (con != 0 && convalue != null && !convalue.equals("")) {
			if (con == 1) {
				queryString += "and mo.name like ? ";
			}
			if (con == 2) {
				queryString += "and mo.supervisor like ? ";
			}
			p = new Object[] { '%' + convalue + '%' };
		}
		if (status == 1) {
			queryString += "and mo.graphicProgress != 4";
		}
		if (status == 2) {
			queryString += "and mo.graphicProgress = 4";
		}
		if(areaIndex!=0)
		{
			queryString += " and mo.yxarea.areaIndex = "+areaIndex;
		}
		if(engineeringType!=0)
		{
			queryString += " and mo.engineeringType = "+(engineeringType-1);
		}
		if(graphicProgress!=0)
		{
			queryString += " and mo.graphicProgress = "+(graphicProgress-1);
		}
		return projectDao.getObjectsByCondition(queryString, p);
	}

}
