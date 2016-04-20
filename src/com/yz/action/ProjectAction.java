package com.yz.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.apache.struts2.interceptor.RequestAware;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;
import org.apache.struts2.interceptor.SessionAware;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.opensymphony.xwork2.ActionSupport;
import com.yz.model.Construction;
import com.yz.model.Daymanage;
import com.yz.model.Project;
import com.yz.model.Usero;
import com.yz.model.Yxarea;
import com.yz.service.IConstructionService;
import com.yz.service.IDaymanageService;
import com.yz.service.IProjectService;
import com.yz.service.IYxareaService;
import com.yz.util.ConvertUtil;
import com.yz.vo.AjaxMsgVO;
import com.yz.vo.AreaVO;
import com.yz.vo.ProjectEvaluateSheet;

/**
 * @author lq
 * 
 */
@Component("projectAction")
@Scope("prototype")
public class ProjectAction extends ActionSupport implements RequestAware,
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
	private IProjectService projectService;
	private IYxareaService yxareaService;
	private IDaymanageService daymanageService;
	private IConstructionService constructionService;

	// 单个对象
	private Project project;
	private AreaVO areaVO;
	

	// list对象
	private List<Project> projects;
	private List<Yxarea> yxareas;
	private List<AreaVO> areaVOs;
	


	/**
	 * 项目管理
	 */
	public String list() throws Exception {
		// 判断会话是否失效
		Usero userSession = (Usero) session.get("userSession");
		if (userSession == null) {
			return "opsessiongo";
		}
		if (convalue != null && !convalue.equals("")) {
			convalue = URLDecoder.decode(convalue, "utf-8");
		}
		if (page < 1) {
			page = 1;
		}
		initAreas();

		if (areaIndex > 0 && areaIndex < 10) {
			areaVO = areaVOs.get(areaIndex - 1);
		}
		// 总记录数
		totalCount = projectService.getTotalCount(con, convalue, areaIndex);
		// 总页数
		pageCount = projectService.getPageCount(totalCount, size);
		if (page > pageCount && pageCount != 0) {
			page = pageCount;
		}
		// 所有当前页记录对象
		projects = projectService.queryList(con, convalue, areaIndex, page,
				size);
		return "list";
	}

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
	public String goToAdd() {
		initAreas();
		if (areaIndex > 0 && areaIndex < 10) {
			areaVO = areaVOs.get(areaIndex - 1);
		}
		return "add";
	}

	/**
	 * 添加
	 * 
	 * @return
	 * @throws Exception
	 */
	public String add() throws Exception {
		// 判断回话是否失效
		Usero userSession = (Usero) session.get("userSession");
		if (userSession == null) {
			String loginfail = "登陆失效,信息提交失败.";
			request.put("loginFail", loginfail);
			return "opsessiongo";
		}

		// 新增项目时，同时增加日常监管
		Daymanage daymanage = new Daymanage();
		daymanage.setIsFiveSigned(0);
		daymanage.setIsMassSafeNotify(0);
		daymanage.setIsCompleted(0);
		daymanage.setIsDangerArgument(0);
		daymanage.setIsEducationLaunch(0);
		daymanage.setIsMortarQualified(0);
		daymanage.setIsNameplateInstall(0);
		
		daymanageService.add(daymanage);
		
		//新增项目时，同时增加文明施工
		Construction construction = new Construction();
		construction.setIsWashSet(0);
		construction.setIsWaterClear(0);
		construction.setIsDoorConform(0);
		construction.setIsGuardConform(0);
		construction.setIsAdConform(0);
		construction.setIsRoadHarden(0);
		construction.setIsRoadClear(0);
		construction.setIsLabelComplete(0);
		construction.setIsLayoutRational(0);
		construction.setIsMeetFire(0);
		construction.setIsMeasurePlace(0);
		constructionService.add(construction);
		
		project.setDaymanage(daymanage);
		project.setConstruction(construction);
		projectService.add(project);

		arg[0] = "projectAction!list?areaIndex=" + areaIndex;
		arg[1] = "项目管理";
		return SUCCESS;
	}

	/**
	 * 删除一
	 * 
	 * @return
	 */
	public String delete() {
		// 判断会话是否失效
		Usero userSession = (Usero) session.get("userSession");
		if (userSession == null) {
			String loginfail = "登陆失效,信息提交失败.";
			request.put("loginFail", loginfail);
			return "opsessiongo";
		}

		project = projectService.loadById(id);
		projectService.delete(project);
		arg[0] = "projectAction!list?areaIndex="
				+ project.getYxarea().getAreaIndex();
		arg[1] = "项目管理";
		return SUCCESS;
	}

	/**
	 * 删除二(批量删除)
	 * 
	 * @return
	 */
	public String deleteProjects() {
		int[] ids = ConvertUtil.StringtoInt(checkedIDs);
		for (int i = 0; i < ids.length; i++) {
			project = projectService.loadById(ids[i]);

			projectService.delete(project);
		}
		AjaxMsgVO msgVO = new AjaxMsgVO();
		msgVO.setMessage("批量删除成功.");
		JSONObject jsonObj = JSONObject.fromObject(msgVO);
		PrintWriter out;
		try {
			response.setContentType("text/html;charset=UTF-8");
			out = response.getWriter();
			out.print(jsonObj.toString());
			out.flush();
			out.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 跳转到修改页面
	 * 
	 * @return
	 */
	public String load() {
		initAreas();
		if (areaIndex > 0 && areaIndex < 10) {
			areaVO = areaVOs.get(areaIndex - 1);
		}
		project = projectService.loadById(id);
		return "load";
	}

	/**
	 * 修改
	 * 
	 * @return
	 */
	public String update() throws Exception {
		// 判断会话是否失效
		Usero userSession = (Usero) session.get("userSession");
		if (userSession == null) {
			String loginfail = "登陆失效,信息提交失败.";
			request.put("loginFail", loginfail);
			return "opsessiongo";
		}
		projectService.update(project);
		arg[0] = "projectAction!list?areaIndex=" + areaIndex;
		arg[1] = "项目管理";
		return SUCCESS;
	}


	/**
	 * 查看信息
	 * 
	 * @return
	 */
	public String view() {
		Usero userSession = (Usero) session.get("userSession");
		if (userSession == null) {
			return "opsessiongo";
		}
		project = projectService.loadById(id);
		return "view";
	}

	/**
	 * 项目工作台
	 * 
	 * @return
	 */
	public String bench() {
		Usero userSession = (Usero) session.get("userSession");
		if (userSession == null) {
			return "opsessiongo";
		}
		initAreas();
		if (areaIndex > 0 && areaIndex < 10) {
			areaVO = areaVOs.get(areaIndex - 1);
		}
		
		session.put("areaVO", areaVO);
		
		project = projectService.loadById(id);
		return "bench";
	}
	
	/**
	 * 项目评价
	 */
	public String toProjectEvaluate()
	{
		
		return "evaluate";
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

	public IProjectService getProjectService() {
		return projectService;
	}

	@Resource
	public void setProjectService(IProjectService projectService) {
		this.projectService = projectService;
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

	public IDaymanageService getDaymanageService() {
		return daymanageService;
	}

	@Resource
	public void setDaymanageService(IDaymanageService daymanageService) {
		this.daymanageService = daymanageService;
	}

	public IConstructionService getConstructionService() {
		return constructionService;
	}
	@Resource
	public void setConstructionService(IConstructionService constructionService) {
		this.constructionService = constructionService;
	}

	
}
