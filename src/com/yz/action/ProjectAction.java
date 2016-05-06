package com.yz.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.apache.struts2.ServletActionContext;
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
import com.yz.util.DateTimeKit;
import com.yz.util.ProjectClassifyExcel;
import com.yz.util.ProjectExcel;
import com.yz.vo.AjaxMsgVO;
import com.yz.vo.AreaVO;
import com.yz.vo.ProjectClassify;

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
	private int engineeringType;// 工程分类(0:土建,1：装饰，2:市政,3:绿化，4：照明亮化)
	private int graphicProgress;// 形象进度(0:基础/20%,1：主体/40%，2:装饰/60%，3：完工待验/80%，4：竣工/100%)
	
	private int graphicProgress1;
	private int graphicProgress2;

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
	private List<ProjectClassify> projectClassifys;

	// 统计
	private int projectNumberTotal;// 项目总数
	private float buildingAreaTotal;// 总面积
	private float buildingCostTotal;// 总造价

	private int excelPageType;

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

		// 设置当前添加项目的用户的uid
		project.setUid(userSession.getId());

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

		// 新增项目时，同时增加文明施工
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
		
		if(project.getEngineeringType()==0)
		{
			project.setGraphicProgress(graphicProgress1);
		}else
		{
			project.setGraphicProgress(graphicProgress2);
		}
		
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
	 * 综合统计
	 * 
	 * @throws UnsupportedEncodingException
	 */
	public String count() throws UnsupportedEncodingException {
		Usero userSession = (Usero) session.get("userSession");
		if (userSession == null) {
			return "opsessiongo";
		}

		if (excelPageType == 1) {
			handleProjectClassifys();
			return "excel1";
		}
		if (convalue != null && !convalue.equals("")) {
			convalue = URLDecoder.decode(convalue, "utf-8");
		}
		if (page < 1) {
			page = 1;
		}

		// 总记录数
		totalCount = projectService.getTotalCount(status, con, convalue,areaIndex,engineeringType,graphicProgress);
		// 总页数
		pageCount = projectService.getPageCount(totalCount, size);
		if (page > pageCount && pageCount != 0) {
			page = pageCount;
		}
		// 所有当前页记录对象
		projects = projectService.queryList(status, con, convalue,areaIndex,engineeringType,graphicProgress, page, size);

		if (projects != null) {
			projectNumberTotal = projects.size();

			for (int i = 0; i < projects.size(); i++) {

				buildingAreaTotal = buildingAreaTotal
						+ projects.get(i).getBuildingArea();
				buildingCostTotal = buildingCostTotal
						+ projects.get(i).getBuildingCost();
			}
		}
		return "excel";
	}

	/***************************************************************************
	 * 导出excel表格
	 * 
	 * @throws UnsupportedEncodingException
	 */
	public String outputExcel() throws UnsupportedEncodingException {
		Usero userSession = (Usero) session.get("userSession");
		if (userSession == null) {
			return "opsessiongo";
		}
		if (convalue != null && !convalue.equals("")) {
			convalue = URLDecoder.decode(convalue, "utf-8");
		}
		// 所有当前页记录对象
		projects = projectService.queryList(status, con, convalue);
		if (projects.size() > 0) {
			// 导出数据-------------------------------------
			String filename = "output\\" + DateTimeKit.getDateRandom()
					+ "_projects.xls";
			String savePath = ServletActionContext.getServletContext()
					.getRealPath("/")
					+ filename;
			System.out.println("[--------------------savePath=" + savePath);
			boolean isexport = ProjectExcel.exportExcel(savePath, projects);
			if (isexport) {
				request.put("errorInfo", "导出数据成功,下载点<a href='" + filename
						+ "'>-这里-</a>");
				return "opexcel";
			} else {
				request.put("errorInfo", "导出数据失败！");
				return "opexcel";
			}
		} else {
			request.put("errorInfo", "查询失败，未导出数据！");
			return "opexcel";
		}
	}

	public String outputExcel1() throws UnsupportedEncodingException {
		Usero userSession = (Usero) session.get("userSession");
		if (userSession == null) {
			return "opsessiongo";
		}
		handleProjectClassifys();
		
		if (projectClassifys.size() > 0) {
			// 导出数据-------------------------------------
			String filename = "output\\" + DateTimeKit.getDateRandom()
					+ "_projectClassifys.xls";
			String savePath = ServletActionContext.getServletContext()
					.getRealPath("/")
					+ filename;
			System.out.println("[--------------------savePath=" + savePath);
			boolean isexport = ProjectClassifyExcel.exportExcel(savePath, projectClassifys);
			if (isexport) {
				request.put("errorInfo", "导出数据成功,下载点<a href='" + filename
						+ "'>-这里-</a>");
				return "opexcel";
			} else {
				request.put("errorInfo", "导出数据失败！");
				return "opexcel";
			}
		} else {
			request.put("errorInfo", "查询失败，未导出数据！");
			return "opexcel";
		}
	}

	//// 项目分类(0:房地产开发,1：安置房，2:政府投资项目,3:重点项目，4：一般项目)
	private List<ProjectClassify> handleProjectClassifys() {
		projects = projectService.getProjects();
		if (projects != null && projects.size() > 0) {
			projectClassifys = new ArrayList<ProjectClassify>();
			for (int i = 0; i < 5; i++) {
				ProjectClassify projectClassify = new ProjectClassify();
				int projectNumber = 0;// 项目总数
				float buildingArea = 0f;// 总面积
				float buildingCost = 0f;// 总造价
				projectClassify.setTotalClassifyName("项目分类");
				switch (i) {
				case 0:
					projectClassify.setClassifyName("房地产开发");
					break;
				case 1:
					projectClassify.setClassifyName("安置房");
					break;
				case 2:
					projectClassify.setClassifyName("政府投资项目");
					break;
				case 3:
					projectClassify.setClassifyName("重点项目");
					break;
				case 4:
					projectClassify.setClassifyName("一般项目");
					break;
				default:
					break;
				}
				projects = projectService.loadByProjectType(i);
				if (projects != null && projects.size() > 0) {
					projectNumber = projects.size();
					for (int j = 0; j < projects.size(); j++) {
						buildingArea = buildingArea
								+ projects.get(j).getBuildingArea();
						buildingCost = buildingCost
								+ projects.get(j).getBuildingCost();
					}
					projectClassify.setProjectNumberTotal(projectNumber);
					projectClassify.setBuildingAreaTotal(buildingArea);
					projectClassify.setBuildingCostTotal(buildingCost);
				}
				projectClassifys.add(projectClassify);
			}

			for (int i = 0; i < 5; i++) {
				ProjectClassify projectClassify = new ProjectClassify();
				int projectNumber = 0;// 项目总数
				float buildingArea = 0f;// 总面积
				float buildingCost = 0f;// 总造价
				projectClassify.setTotalClassifyName("工程分类");
				switch (i) {// engineeringType 0:土建,1：装饰，2:市政,3:绿化，4：照明亮化
				case 0:
					projectClassify.setClassifyName("土建");
					break;
				case 1:
					projectClassify.setClassifyName("装饰");
					break;
				case 2:
					projectClassify.setClassifyName("市政");
					break;
				case 3:
					projectClassify.setClassifyName("绿化");
					break;
				case 4:
					projectClassify.setClassifyName("照明亮化");
					break;
				default:
					break;
				}
				projects = projectService.loadByEngineeringType(i);
				if (projects != null && projects.size() > 0) {
					projectNumber = projects.size();
					for (int j = 0; j < projects.size(); j++) {
						buildingArea = buildingArea
								+ projects.get(j).getBuildingArea();
						buildingCost = buildingCost
								+ projects.get(j).getBuildingCost();
					}
					projectClassify.setProjectNumberTotal(projectNumber);
					projectClassify.setBuildingAreaTotal(buildingArea);
					projectClassify.setBuildingCostTotal(buildingCost);
				}
				projectClassifys.add(projectClassify);
			}

			// private Integer buildingType;// 建筑分类(0:住宅,1：公共建筑，2:工业建筑)
			for (int i = 0; i < 3; i++) {
				ProjectClassify projectClassify = new ProjectClassify();
				int projectNumber = 0;// 项目总数
				float buildingArea = 0f;// 总面积
				float buildingCost = 0f;// 总造价
				projectClassify.setTotalClassifyName("建筑分类");
				switch (i) {
				case 0:
					projectClassify.setClassifyName("住宅");
					break;
				case 1:
					projectClassify.setClassifyName("公共建筑");
					break;
				case 2:
					projectClassify.setClassifyName("工业建筑");
					break;
				default:
					break;
				}
				projects = projectService.loadByBuildingType(i);
				if (projects != null && projects.size() > 0) {
					projectNumber = projects.size();
					for (int j = 0; j < projects.size(); j++) {
						buildingArea = buildingArea
								+ projects.get(j).getBuildingArea();
						buildingCost = buildingCost
								+ projects.get(j).getBuildingCost();
					}
					projectClassify.setProjectNumberTotal(projectNumber);
					projectClassify.setBuildingAreaTotal(buildingArea);
					projectClassify.setBuildingCostTotal(buildingCost);
				}
				projectClassifys.add(projectClassify);
			}

			// 形象进度(0:基础/20%,1：主体/40%，2:装饰/60%，3：完工待验/80%，4：竣工/100%)
			for (int i = 0; i < 5; i++) {
				ProjectClassify projectClassify = new ProjectClassify();
				int projectNumber = 0;// 项目总数
				float buildingArea = 0f;// 总面积
				float buildingCost = 0f;// 总造价
				projectClassify.setTotalClassifyName("形象进度");
				switch (i) {
				case 0:
					projectClassify.setClassifyName("基础");
					break;
				case 1:
					projectClassify.setClassifyName("主体");
					break;
				case 2:
					projectClassify.setClassifyName("装饰");
					break;
				case 3:
					projectClassify.setClassifyName("完工待验");
					break;
				case 4:
					projectClassify.setClassifyName("竣工");
					break;
				default:
					break;
				}
				projects = projectService.loadByGraphicProgress(i);
				if (projects != null && projects.size() > 0) {
					projectNumber = projects.size();
					for (int j = 0; j < projects.size(); j++) {
						buildingArea = buildingArea
								+ projects.get(j).getBuildingArea();
						buildingCost = buildingCost
								+ projects.get(j).getBuildingCost();
					}
					projectClassify.setProjectNumberTotal(projectNumber);
					projectClassify.setBuildingAreaTotal(buildingArea);
					projectClassify.setBuildingCostTotal(buildingCost);
				}
				projectClassifys.add(projectClassify);
			}
		}
		return projectClassifys;
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

	public int getProjectNumberTotal() {
		return projectNumberTotal;
	}

	public void setProjectNumberTotal(int projectNumberTotal) {
		this.projectNumberTotal = projectNumberTotal;
	}

	public float getBuildingAreaTotal() {
		return buildingAreaTotal;
	}

	public void setBuildingAreaTotal(float buildingAreaTotal) {
		this.buildingAreaTotal = buildingAreaTotal;
	}

	public float getBuildingCostTotal() {
		return buildingCostTotal;
	}

	public void setBuildingCostTotal(float buildingCostTotal) {
		this.buildingCostTotal = buildingCostTotal;
	}

	public List<ProjectClassify> getProjectClassifys() {
		return projectClassifys;
	}

	public void setProjectClassifys(List<ProjectClassify> projectClassifys) {
		this.projectClassifys = projectClassifys;
	}

	public int getExcelPageType() {
		return excelPageType;
	}

	public void setExcelPageType(int excelPageType) {
		this.excelPageType = excelPageType;
	}

	public int getEngineeringType() {
		return engineeringType;
	}

	public void setEngineeringType(int engineeringType) {
		this.engineeringType = engineeringType;
	}

	public Integer getGraphicProgress() {
		return graphicProgress;
	}

	public void setGraphicProgress(Integer graphicProgress) {
		this.graphicProgress = graphicProgress;
	}

	public int getGraphicProgress1() {
		return graphicProgress1;
	}

	public void setGraphicProgress1(int graphicProgress1) {
		this.graphicProgress1 = graphicProgress1;
	}

	public int getGraphicProgress2() {
		return graphicProgress2;
	}

	public void setGraphicProgress2(int graphicProgress2) {
		this.graphicProgress2 = graphicProgress2;
	}
	
	
	

}
