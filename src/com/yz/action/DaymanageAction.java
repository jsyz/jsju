package com.yz.action;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.interceptor.RequestAware;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;
import org.apache.struts2.interceptor.SessionAware;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.opensymphony.xwork2.ActionSupport;
import com.yz.model.Daymanage;
import com.yz.model.Project;
import com.yz.model.Usero;
import com.yz.model.Yxarea;
import com.yz.service.IDaymanageService;
import com.yz.service.IProjectService;
import com.yz.service.IYxareaService;
import com.yz.vo.AreaVO;

/**
 * @author lq
 * 
 */
@Component("daymanageAction")
@Scope("prototype")
public class DaymanageAction extends ActionSupport implements RequestAware,
		SessionAware, ServletResponseAware, ServletRequestAware {

	private static final long serialVersionUID = 1L;
	Map<String, Object> request;
	Map<String, Object> session;
	private javax.servlet.http.HttpServletResponse response;
	private javax.servlet.http.HttpServletRequest req;

	// 分页显示
	private String[] arg = new String[2];
	private int page;
	private final int size = 10;
	private int pageCount;
	private int totalCount;

	// 条件
	private int id;
	private int con;
	private String convalue;
	private int status;// 按状态
	private int pid;// 按用户id
	private int areaIndex;// 区域标示

	// 批量删除
	private String checkedIDs;

	// service层对象
	private IDaymanageService daymanageService;
	private IYxareaService yxareaService;
	private IProjectService projectService;

	// 单个对象
	private Daymanage daymanage;
	private Project project;
	private AreaVO areaVO;

	// list对象
	private List<Daymanage> daymanages;
	private List<Project> projects;
	private List<Yxarea> yxareas;
	private List<AreaVO> areaVOs;
	
	private List<String> launchContents;// 页面显示被选中 信息提取情况

	// 区域项目统计
	private void initAreas() {
		areaVOs = new ArrayList<AreaVO>();
		yxareas = yxareaService.getYxareas();
		for (Yxarea yxarea : yxareas) {
			AreaVO areaVO = new AreaVO();
			areaVO.setId(yxarea.getId());
			areaVO.setIndex(yxarea.getAreaIndex());
			areaVO.setAreaName(yxarea.getAreaname());
			int numberTotal = 0;
			float areaTotal = 0f;
			float costTotal = 0f;

			if (yxarea.getProjects() != null && yxarea.getProjects().size() > 0) {
				projects = yxarea.getProjects();
				numberTotal = projects.size();
				for (int i = 0; i < projects.size(); i++) {
					areaTotal += projects.get(i).getBuildingArea();
					costTotal += projects.get(i).getBuildingCost();
				}
			}
			areaVO.setProjectNumberTotal(numberTotal);
			areaVO.setBuildingAreaTotal(areaTotal);
			areaVO.setBuildingCostTotal(costTotal);
			areaVOs.add(areaVO);
		}
	}

	/**
	 * 跳转到添加页面
	 * 
	 * @return
	 */
	public String view() {
		// 判断会话是否失效
		Usero userSession = (Usero) session.get("userSession");
		if (userSession == null) {
			String loginfail = "登陆失效,信息提交失败.";
			request.put("loginFail", loginfail);
			return "opsessiongo";
		}
		initAreas();
		if (areaIndex > 0 && areaIndex < 10) {
			areaVO = areaVOs.get(areaIndex - 1);
		}
		
		project = projectService.loadByPid(pid);
		daymanage = project.getDaymanage();
		if (daymanage != null) {
			handleInfoExtractionMsg(daymanage.getLaunchContent());
		}
		return "view";
	}

	public String updateDaymanage() throws Exception {
		// 判断会话是否失效
		Usero userSession = (Usero) session.get("userSession");
		if (userSession == null) {
			String loginfail = "登陆失效,信息提交失败.";
			request.put("loginFail", loginfail);
			return "opsessiongo";
		}
		//修改项目完成进度 
		if(daymanage.getIsCompleted()==1)
		{
			project = projectService.loadByPid(pid);
			if(project.getGraphicProgress()!=4)
			{
				project.setGraphicProgress(4);
				projectService.update(project);
			}
		}
		daymanageService.update(daymanage);
		arg[0] = "projectAction!bench?id="+pid+"&areaIndex"+areaIndex;
		arg[1] = "项目工作台";
		return SUCCESS;
	}
	
	// 页面显示被选中 三级教育情况 {'纸质','图片','VCR'}显示格式
	private void handleInfoExtractionMsg(String launchContent) {
		// TODO Auto-generated method stub
		launchContents = new ArrayList<String>();
		if (launchContent != null && launchContent.length() > 0
				&& launchContent.contains(",")) {
			String[] launchContentspres = launchContent.split(",");
			for (int i = 0; i < launchContentspres.length; i++) {
				launchContents.add(launchContentspres[i].replace(" ", ""));
			}
		}
	}

	// get、set-------------------------------------------

	// 获得HttpServletResponse对象
	public void setServletResponse(HttpServletResponse response) {
		this.response = response;
	}

	public void setServletRequest(HttpServletRequest req) {
		this.req = req;
	}

	public Map<String, Object> getRequest() {
		return request;
	}

	public void setRequest(Map<String, Object> request) {
		this.request = request;
	}

	public Map<String, Object> getSession() {
		return session;
	}

	public void setSession(Map<String, Object> session) {
		this.session = session;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getPage() {
		return page;
	}

	public void setPage(int page) {
		this.page = page;
	}

	public int getPageCount() {
		return pageCount;
	}

	public void setPageCount(int pageCount) {
		this.pageCount = pageCount;
	}

	public int getTotalCount() {
		return totalCount;
	}

	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
	}

	public int getCon() {
		return con;
	}

	public void setCon(int con) {
		this.con = con;
	}

	public String getConvalue() {
		return convalue;
	}

	public void setConvalue(String convalue) {
		this.convalue = convalue;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public int getPid() {
		return pid;
	}

	public void setPid(int pid) {
		this.pid = pid;
	}

	public String[] getArg() {
		return arg;
	}

	public void setArg(String[] arg) {
		this.arg = arg;
	}

	public IDaymanageService getDaymanageService() {
		return daymanageService;
	}

	@Resource
	public void setDaymanageService(IDaymanageService daymanageService) {
		this.daymanageService = daymanageService;
	}

	public Daymanage getDaymanage() {
		return daymanage;
	}

	public void setDaymanage(Daymanage daymanage) {
		this.daymanage = daymanage;
	}

	public List<Daymanage> getDaymanages() {
		return daymanages;
	}

	public void setDaymanages(List<Daymanage> daymanages) {
		this.daymanages = daymanages;
	}

	public javax.servlet.http.HttpServletResponse getResponse() {
		return response;
	}

	public void setResponse(javax.servlet.http.HttpServletResponse response) {
		this.response = response;
	}

	public javax.servlet.http.HttpServletRequest getReq() {
		return req;
	}

	public void setReq(javax.servlet.http.HttpServletRequest req) {
		this.req = req;
	}

	public String getCheckedIDs() {
		return checkedIDs;
	}

	public void setCheckedIDs(String checkedIDs) {
		this.checkedIDs = checkedIDs;
	}

	public IYxareaService getYxareaService() {
		return yxareaService;
	}

	@Resource
	public void setYxareaService(IYxareaService yxareaService) {
		this.yxareaService = yxareaService;
	}

	public List<Yxarea> getYxareas() {
		return yxareas;
	}

	public void setYxareas(List<Yxarea> yxareas) {
		this.yxareas = yxareas;
	}

	public int getAreaIndex() {
		return areaIndex;
	}

	public void setAreaIndex(int areaIndex) {
		this.areaIndex = areaIndex;
	}

	public AreaVO getAreaVO() {
		return areaVO;
	}

	public void setAreaVO(AreaVO areaVO) {
		this.areaVO = areaVO;
	}

	public List<AreaVO> getAreaVOs() {
		return areaVOs;
	}

	public void setAreaVOs(List<AreaVO> areaVOs) {
		this.areaVOs = areaVOs;
	}

	public Project getProject() {
		return project;
	}

	public void setProject(Project project) {
		this.project = project;
	}

	public List<Project> getProjects() {
		return projects;
	}

	public void setProjects(List<Project> projects) {
		this.projects = projects;
	}

	public IProjectService getProjectService() {
		return projectService;
	}

	@Resource
	public void setProjectService(IProjectService projectService) {
		this.projectService = projectService;
	}

	public List<String> getLaunchContents() {
		return launchContents;
	}

	public void setLaunchContents(List<String> launchContents) {
		this.launchContents = launchContents;
	}

}
