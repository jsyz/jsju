package com.yz.action;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
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
import com.yz.model.Project;
import com.yz.model.Spreadsheet;
import com.yz.model.Usero;
import com.yz.model.Yxarea;
import com.yz.service.IProjectService;
import com.yz.service.ISpreadsheetService;
import com.yz.service.IYxareaService;
import com.yz.util.ConvertUtil;
import com.yz.util.DateTimeKit;
import com.yz.vo.AjaxMsgVO;
import com.yz.vo.AreaVO;
import com.yz.vo.SheetVO;

/**
 * @author lq 表格
 * 
 */
@Component("spreadsheetAction")
@Scope("prototype")
public class SpreadsheetAction extends ActionSupport implements RequestAware,
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
	private int pid;// 按项目id
	private int areaIndex;// 区域标示
	private String sheetTypeStr;// 表格类型可能为多个
	private Integer[] sheetTypes;
	private String pageName;// 跳转到页面名称
	private Integer sheetType;
	private String sheetName;// 表格名称

	// 批量删除
	private String checkedIDs;

	// service层对象
	private ISpreadsheetService spreadsheetService;
	private IYxareaService yxareaService;
	private IProjectService projectService;

	// 单个对象
	private Spreadsheet spreadsheet;
	private Project project;
	private AreaVO areaVO;
	private SheetVO sheetVO;

	// list对象
	private List<Spreadsheet> spreadsheets;
	private List<Project> projects;
	private List<Yxarea> yxareas;
	private List<AreaVO> areaVOs;
	private List<SheetVO> sheetVOs;

	/**
	 * 表格管理
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

		handleSheetTypes(sheetTypeStr);

		if (sheetTypes.length > 0) {
			handleSheetVOs(sheetTypes);
			System.out.println("size:" + sheetVOs.size());
		}
		// 获得当前项目
		project = projectService.loadById(pid);
		// 总记录数
		totalCount = spreadsheetService.getTotalCount(con, convalue, pid,
				sheetTypes);
		// 总页数
		pageCount = spreadsheetService.getPageCount(totalCount, size);
		if (page > pageCount && pageCount != 0) {
			page = pageCount;
		}
		// 所有当前页记录对象
		spreadsheets = spreadsheetService.queryList(con, convalue, pid,
				sheetTypes, page, size);

		return "list";
	}

	private void handleSheetTypes(String sheetTypeString) {
		// TODO Auto-generated method stub
		System.out.println(sheetTypeString);
		List<Integer> types = new ArrayList<Integer>();
		if (sheetTypeString != null) {
			String strs[] = sheetTypeString.split(",");
			for (int i = 0; i < strs.length; i++) {
				types.add(Integer.parseInt(strs[i]));
			}
		}
		sheetTypes = (Integer[]) types.toArray(new Integer[types.size()]);
	}

	private void handleSheetVOs(Integer[] types) {
		// TODO Auto-generated method stub
		sheetVOs = new ArrayList<SheetVO>();
		for (int i = 0; i < types.length; i++) {
			SheetVO sheet = new SheetVO();
			switch (types[i]) {
			// 1,2同一个页面
			case 1:
				sheet.setSheetType(types[i]);
				sheet.setSheetName("工程质量行为资料监督抽查记录");
				pageName = "日常监管-行为监督抽查";
				break;
			case 2:
				sheet.setSheetType(types[i]);
				sheet.setSheetName("施工单位安全生产行为监督检查表");
				break;
			//3,4同一页面
			case 3:
				sheet.setSheetType(types[i]);
				sheet.setSheetName("工程质量监督抽查（巡查）记录");
				pageName = "日常监管-日常巡查";
				break;
			case 4:
				sheet.setSheetType(types[i]);
				sheet.setSheetName("建设工程安全生产监督抽查记录表");
				break;
			case 5:
				sheet.setSheetType(types[i]);
				break;
			case 6:
				sheet.setSheetType(types[i]);
				break;
			case 7:
				sheet.setSheetType(types[i]);
				break;
			case 8:
				sheet.setSheetType(types[i]);
				break;
			case 9:
				sheet.setSheetType(types[i]);
				break;
			case 10:
				sheet.setSheetType(types[i]);
				break;
			case 11:
				sheet.setSheetType(types[i]);
				break;
			case 12:
				sheet.setSheetType(types[i]);
				break;
			case 13:
				sheet.setSheetType(types[i]);
				break;
			case 14:
				sheet.setSheetType(types[i]);
				break;
			default:
				break;
			}
			sheetVOs.add(sheet);

		}
	}

	/**
	 * 跳转到添加页面
	 * 
	 * @return
	 */
	public String goToAdd() {

		// 获得当前项目
		project = projectService.loadById(pid);
		System.out.println("sheetTypeStr:" + sheetTypeStr);
		switch (sheetType) {
		case 0:
			break;
		case 1:
			pageName = "日常监管-行为监督抽查-工程质量行为资料监督抽查记录";
			sheetName = "工程质量行为资料监督抽查记录";
		case 2:
			pageName = "日常监管-行为监督抽查-施工单位安全生产行为监督检查表";
			sheetName = "施工单位安全生产行为监督检查表";
			break;
		case 3:
			pageName = "日常监管-日常巡查-工程质量监督抽查（巡查）记录";
			sheetName = "工程质量监督抽查（巡查）记录";
			break;
		case 4:
			pageName = "日常监管-日常巡查-施工单位安全生产行为监督检查表";
			sheetName = "建设工程安全生产监督抽查记录表";
			break;
		case 5:
			
			break;
		case 6:

			break;
		case 7:

			break;
		case 8:

			break;
		case 9:

			break;
		case 10:

			break;
		case 11:

			break;
		case 12:

			break;
		case 13:

			break;
		case 14:

			break;

		default:
			break;
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
		if (picture1 != null && picture1FileName != null
				&& !picture1FileName.replace(" ", "").equals("")) {
			String imageName = DateTimeKit.getDateRandom()
					+ picture1FileName.substring(picture1FileName.indexOf("."));
			this.upload("/sheet" + spreadsheet.getSheetType(), imageName,
					picture1);
			spreadsheet.setSheetImg("/sheet" + spreadsheet.getSheetType() + "/"
					+ imageName);
		}
		spreadsheetService.add(spreadsheet);
		System.out.println("sheetTypes:" + sheetTypes);
		arg[0] = "spreadsheetAction!list?pid=" + pid + "&sheetTypeStr="
				+ sheetTypeStr;
		arg[1] = "表格管理";
		return SUCCESS;
	}

	// 图片
	private File picture1;
	private String picture1ContentType;
	private String picture1FileName;

	// 文件上传
	public void upload(String fileName, String imageName, File picture)
			throws Exception {
		File saved = new File(ServletActionContext.getServletContext()
				.getRealPath(fileName), imageName);
		InputStream ins = null;
		OutputStream ous = null;
		try {
			saved.getParentFile().mkdirs();
			ins = new FileInputStream(picture);
			ous = new FileOutputStream(saved);
			byte[] b = new byte[1024];
			int len = 0;
			while ((len = ins.read(b)) != -1) {
				ous.write(b, 0, len);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (ous != null)
				ous.close();
			if (ins != null)
				ins.close();
		}
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

		spreadsheet = spreadsheetService.loadById(id);
		if (spreadsheet.getSheetImg() != null
				&& !spreadsheet.getSheetImg().replace(" ", "").equals("")) {
			File photofile = new File(ServletActionContext.getServletContext()
					.getRealPath("/")
					+ spreadsheet.getSheetImg());
			photofile.delete();
		}
		spreadsheetService.delete(spreadsheet);
		arg[0] = "spreadsheetAction!list?pid=" + pid + "&sheetTypeStr="
				+ sheetTypeStr;
		arg[1] = "表格管理";
		return SUCCESS;
	}

	/**
	 * 删除二(批量删除)
	 * 
	 * @return
	 */
	public String deleteSpreadsheets() {
		int[] ids = ConvertUtil.StringtoInt(checkedIDs);
		for (int i = 0; i < ids.length; i++) {
			spreadsheet = spreadsheetService.loadById(ids[i]);
			if (spreadsheet.getSheetImg() != null
					&& !spreadsheet.getSheetImg().replace(" ", "").equals("")) {
				File photofile = new File(ServletActionContext
						.getServletContext().getRealPath("/")
						+ spreadsheet.getSheetImg());
				photofile.delete();
			}
			spreadsheetService.delete(spreadsheet);
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
		spreadsheet = spreadsheetService.loadById(id);
		return "load";
	}

	/**
	 * 跳转到展示页面
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
		spreadsheet = spreadsheetService.loadById(id);
		return "view";
	}

	public String update() throws Exception {
		// 判断会话是否失效
		Usero userSession = (Usero) session.get("userSession");
		if (userSession == null) {
			String loginfail = "登陆失效,信息提交失败.";
			request.put("loginFail", loginfail);
			return "opsessiongo";
		}
		spreadsheetService.update(spreadsheet);
		arg[0] = "spreadsheetAction!list?pid=" + pid + "&sheetTypes="
				+ sheetTypes;
		arg[1] = "表格管理";
		return SUCCESS;
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

	public ISpreadsheetService getSpreadsheetService() {
		return spreadsheetService;
	}

	@Resource
	public void setSpreadsheetService(ISpreadsheetService spreadsheetService) {
		this.spreadsheetService = spreadsheetService;
	}

	public Spreadsheet getSpreadsheet() {
		return spreadsheet;
	}

	public void setSpreadsheet(Spreadsheet spreadsheet) {
		this.spreadsheet = spreadsheet;
	}

	public List<Spreadsheet> getSpreadsheets() {
		return spreadsheets;
	}

	public void setSpreadsheets(List<Spreadsheet> spreadsheets) {
		this.spreadsheets = spreadsheets;
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

	public String getSheetTypeStr() {
		return sheetTypeStr;
	}

	public void setSheetTypeStr(String sheetTypeStr) {
		this.sheetTypeStr = sheetTypeStr;
	}

	public Integer[] getSheetTypes() {
		return sheetTypes;
	}

	public void setSheetTypes(Integer[] sheetTypes) {
		this.sheetTypes = sheetTypes;
	}

	public String getPageName() {
		return pageName;
	}

	public void setPageName(String pageName) {
		this.pageName = pageName;
	}

	public SheetVO getSheetVO() {
		return sheetVO;
	}

	public void setSheetVO(SheetVO sheetVO) {
		this.sheetVO = sheetVO;
	}

	public List<SheetVO> getSheetVOs() {
		return sheetVOs;
	}

	public void setSheetVOs(List<SheetVO> sheetVOs) {
		this.sheetVOs = sheetVOs;
	}

	public Integer getSheetType() {
		return sheetType;
	}

	public void setSheetType(Integer sheetType) {
		this.sheetType = sheetType;
	}

	public String getSheetName() {
		return sheetName;
	}

	public void setSheetName(String sheetName) {
		this.sheetName = sheetName;
	}

	public File getPicture1() {
		return picture1;
	}

	public void setPicture1(File picture1) {
		this.picture1 = picture1;
	}

	public String getPicture1ContentType() {
		return picture1ContentType;
	}

	public void setPicture1ContentType(String picture1ContentType) {
		this.picture1ContentType = picture1ContentType;
	}

	public String getPicture1FileName() {
		return picture1FileName;
	}

	public void setPicture1FileName(String picture1FileName) {
		this.picture1FileName = picture1FileName;
	}

}
