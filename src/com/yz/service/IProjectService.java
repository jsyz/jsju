package com.yz.service;

import java.util.List;

import com.yz.model.Project;
import com.yz.model.Usero;

public interface IProjectService {

	// 添加对象
	public abstract void add(Project project) throws Exception;

	// 删除对象
	public abstract void delete(Project project);

	// 删除某个id的对象
	public abstract void deleteById(int id);

	// 修改对象
	public abstract void update(Project project);

	// 获取所有对象
	public abstract List<Project> getProjects();

	// 加载一个id的对象
	public abstract Project loadById(int id);

	// 后台管理-页数获取
	public abstract int getPageCount(int totalCount, int size);

	// 后台管理-获取总记录数
	public abstract int getTotalCount(int con, String convalue, int areaIndex);

	// 后台管理-获取符合条件的记录
	public abstract List<Project> queryList(int con, String convalue,
			int areaIndex, int page, int size);

	public abstract Project getProjectByProjectname(String projectname);

	public abstract int addAndReturn(Project project);

	public abstract Project loadByPid(int pid);

	public abstract int getTotalCount(int status, int con, String convalue);

	public abstract List<Project> queryList(int status, int con,
			String convalue, int page, int size);

	public abstract List<Project> queryList(int status, int con, String convalue);

	public abstract List<Project> loadByProjectType(int i);

	public List<Project> loadByEngineeringType(int i);

	public List<Project> loadByBuildingType(int i);

	public List<Project> loadByGraphicProgress(int i);

	public abstract int getTotalCount(int status, int con, String convalue,
			int areaIndex, int engineeringType, int graphicProgress,String starttime,String endtime);

	public abstract List<Project> queryList(int status, int con,
			String convalue, int areaIndex, int engineeringType,
			int graphicProgress, int page, int size,String starttime,String endtime);

	public abstract int getTotalCount(int con, String convalue, int areaIndex,
			Usero userSession);

	public abstract List<Project> queryList(int con, String convalue,
			int areaIndex, int page, int size, Usero userSession);

	public abstract List<Project> queryList(int status, int con,
			String convalue, int areaIndex, int engineeringType,
			int graphicProgress);

}