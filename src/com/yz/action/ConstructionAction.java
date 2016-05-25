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
import com.yz.model.Construction;
import com.yz.model.Project;
import com.yz.model.Usero;
import com.yz.model.Yxarea;
import com.yz.service.IConstructionService;
import com.yz.service.IProjectService;
import com.yz.service.IYxareaService;
import com.yz.util.ConvertUtil;
import com.yz.util.DateTimeKit;
import com.yz.vo.AjaxMsgVO;
import com.yz.vo.AreaVO;

/**
 * @author Administrator
 * 
 */
@Component("constructionAction")
@Scope("prototype")
public class ConstructionAction extends ActionSupport implements RequestAware,
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
	private int cid;
	private int con;
	private String convalue;
	private int status;// 按状态
	private int pid;// 按设备id
	private int areaIndex;
	private int projectId;
	private int pic_row;
	// // 登陆
	// private String username;
	// private String password;

	private String name; // 设备名称
	private String propertyCardNumber;// 产权证号
	private String installTime;// 安装告知时间
	private String checkTime;// 检测时间
	private Integer isDealUsecard;// 是否办理使用登记证
	private String usecardExpireTime;// 登记证到期时间
	private String removeTime;// 拆卸告知日期
	// 批量删除
	private String checkedIDs;

	// service层对象
	private IConstructionService constructionService;
	private IProjectService projectService;
	private IYxareaService yxareaService;
	// 单个对象
	private Construction construction;
	private Project project;
	private AreaVO areaVO;
	// list对象
	private List<Construction> constructions;
	private List<Project> projects;
	private List<Yxarea> yxareas;
	private List<AreaVO> areaVOs;

	// 图片1
	private File picture1;
	private String picture1ContentType;
	private String picture1FileName;

	// 图片2
	private File picture2;
	private String picture2ContentType;
	private String picture2FileName;

	// 图片3
	private File picture3;
	private String picture3ContentType;
	private String picture3FileName;

	// 图片4
	private File picture4;
	private String picture4ContentType;
	private String picture4FileName;

	// 图片5
	private File picture5;
	private String picture5ContentType;
	private String picture5FileName;

	// 图片6
	private File picture6;
	private String picture6ContentType;
	private String picture6FileName;

	// 图片7
	private File picture7;
	private String picture7ContentType;
	private String picture7FileName;

	// 图片8
	private File picture8;
	private String picture8ContentType;
	private String picture8FileName;

	// 图片9
	private File picture9;
	private String picture9ContentType;
	private String picture9FileName;

	// 图片10
	private File picture10;
	private String picture10ContentType;
	private String picture10FileName;

	// 图片11
	private File picture11;
	private String picture11ContentType;
	private String picture11FileName;
	// // 个人资料新旧密码
	// private String password1;
	// private String password2;

	// 上传照片
	private File picture;

	private String pictureContentType;

	private String pictureFileName;

	/**
	 * 用户注销
	 */
	// public String logout() {
	// session.clear();
	// return "adminLogin";
	// }
	/**
	 * 添加设备
	 * 
	 * @return
	 * @throws Exception
	 */

	public String add() throws Exception {
		// 判断回话是否失效
		// Construction construction = (Construction)
		// session.get("construction");
		// if (construction == null) {
		// return "opsessiongo_child";
		// }

		Usero userSession = (Usero) session.get("userSession");
		if (userSession == null) {
			String loginfail = "登陆失效,信息提交失败.";
			request.put("loginFail", loginfail);
			return "opsessiongo";
		}

		project = projectService.loadByPid(pid);

		constructionService.add(construction);

		arg[0] = "constructionAction!list";
		arg[1] = "设备管理";
		return "success_child";
	}

	private void checkIP() {
		// TODO Auto-generated method stub
		// String ip = getIpAddr(req);
	}

	/**
	 * 设备资料
	 */
	public String currentConstruction() {
		Construction construction = (Construction) session.get("construction");
		if (construction == null) {
			return "opsessiongo";
		}
		construction = constructionService.loadById(construction.getId());

		return "currentConstruction";
	}

	/**
	 * 删除一
	 * 
	 * @return
	 */
	public String delete() {
		// 判断会话是否失效
		Construction construction = (Construction) session.get("construction");
		if (construction == null) {
			return "opsessiongo";
		}

		construction = constructionService.loadById(id);

		constructionService.delete(construction);

		constructionService.deleteById(id);
		arg[0] = "constructionAction!list";
		arg[1] = "用户管理";
		return SUCCESS;
	}

	/**
	 * 删除二(批量删除)
	 * 
	 * @return
	 */
	public String deleteConstructions() {

		int[] ids = ConvertUtil.StringtoInt(checkedIDs);
		for (int i = 0; i < ids.length; i++) {
			construction = constructionService.loadById(ids[i]);

			constructionService.delete(construction);
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

	public int getAreaIndex() {
		return areaIndex;
	}

	public AreaVO getAreaVO() {
		return areaVO;
	}

	public List<AreaVO> getAreaVOs() {
		return areaVOs;
	}

	public String[] getArg() {
		return arg;
	}

	public String getCheckedIDs() {
		return checkedIDs;
	}

	public String getCheckTime() {
		return checkTime;
	}

	/**
	 * 跳转到修改秒页面
	 * 
	 * @return
	 */
	// public String loadPassword() throws Exception {
	// Construction construction = (Construction) session.get("construction");
	// if (construction == null) {
	// return "opsessiongo";
	// }
	// password = construction.getPassword();
	// return "password";
	// }
	/**
	 * 修改密码
	 * 
	 * @return
	 */
	// public String updatePassword() throws Exception {
	// // 判断会话是否失效
	// Construction construction = (Construction) session.get("construction");
	// if (construction == null) {
	// return "opsessiongo";
	// }
	// construction.setPassword(password);
	// constructionService.update(construction);
	// arg[0] = "constructionAction!list";
	// arg[1] = "用户管理";
	// return SUCCESS;
	// }
	public int getCon() {
		return con;
	}

	public Construction getConstruction() {
		return construction;
	}

	public List<Construction> getConstructions() {
		return constructions;
	}

	// get、set-------------------------------------------

	public IConstructionService getConstructionService() {
		return constructionService;
	}

	public String getConvalue() {
		return convalue;
	}

	public int getId() {
		return id;
	}

	public String getInstallTime() {
		return installTime;
	}

	/*
	 * 获取IP地址
	 */
	public String getIpAddr(HttpServletRequest request) {
		String ip = request.getHeader("x-forwarded-for");
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("WL-Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("HTTP_CLIENT_IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("HTTP_X_FORWARDED_FOR");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getRemoteAddr();
		}
		return ip;
	}

	public Integer getIsDealUsecard() {
		return isDealUsecard;
	}

	public String getName() {
		return name;
	}

	public int getPage() {
		return page;
	}

	public int getPageCount() {
		return pageCount;
	}

	public File getPicture() {
		return picture;
	}

	public String getPictureContentType() {
		return pictureContentType;
	}

	public String getPictureFileName() {
		return pictureFileName;
	}

	public int getPid() {
		return pid;
	}

	public Project getProject() {
		return project;
	}

	public int getProjectId() {
		return projectId;
	}

	public List<Project> getProjects() {
		return projects;
	}

	public IProjectService getProjectService() {
		return projectService;
	}

	public String getPropertyCardNumber() {
		return propertyCardNumber;
	}

	public String getRemoveTime() {
		return removeTime;
	}

	public javax.servlet.http.HttpServletRequest getReq() {
		return req;
	}

	public Map<String, Object> getRequest() {
		return request;
	}

	public javax.servlet.http.HttpServletResponse getResponse() {
		return response;
	}

	public Map<String, Object> getSession() {
		return session;
	}

	public int getStatus() {
		return status;
	}

	public int getTotalCount() {
		return totalCount;
	}

	public String getUsecardExpireTime() {
		return usecardExpireTime;
	}

	public List<Yxarea> getYxareas() {
		return yxareas;
	}

	public IYxareaService getYxareaService() {
		return yxareaService;
	}

	/**
	 * 跳转到添加页面
	 * 
	 * @return
	 */
	public String goToAdd() {

		return "add";
	}

	/**
	 * // * 用户登陆 // * // *
	 * 
	 * @throws Exception //
	 */
	// public String login() throws Exception {
	//
	// if (checkDatebase())// 检查数据库
	// {
	// Usero useroTest = new Usero();
	// useroTest.setNumber("测试人员");
	// useroTest.setUsername("test");
	// useroTest.setPassword("test");
	// useroTest.setUserLimit(1);
	// useroService.add(useroTest);
	// session.put("usero", useroTest);
	// return "loginSucc";
	// }
	// if (username == null || username.equals("") || password == null
	// || password.equals("")) {
	// String loginfail = "用户名或密码不能为空";
	// request.put("loginFail", loginfail);
	// return "adminLogin";
	// }
	// Usero useroLogin = useroService.useroLogin(username, password);
	// if (useroLogin == null) {
	// String loginfail = "用户名或密码输入有误";
	// request.put("loginFail", loginfail);
	// return "adminLogin";
	// } else {
	// // 设置登陆时间
	// if (session.get("usero") == null) {
	// //setLoginTime(useroLogin);
	// session.put("usero", useroLogin);
	// }
	// // checkIP();//检查IP地址
	// return "loginSucc";
	// }
	// }
	//
	// public String welcome() {
	// // 登陆验证
	// Usero usero = (Usero) session.get("usero");
	// if (usero == null) {
	// return "opsessiongo";
	// }
	// Usero useroWelcome = useroService.loadById(usero.getId());
	// // 欢迎界面
	// return "welcome";
	// }
	//
	// // 设置登陆时间
	/**
	 * 
	 * private void setLoginTime(Usero useroLogin) { // TODO Auto-generated
	 * method stub if (useroLogin.getBeforeLoginTime() == "" ||
	 * useroLogin.getBeforeLoginTime() == null) {
	 * useroLogin.setBeforeLoginTime(DateTimeKit.getLocalTime()); } else {
	 * useroLogin.setBeforeLoginTime(useroLogin .getCurrentLoginTime()); }
	 * useroLogin.setCurrentLoginTime(DateTimeKit.getLocalTime());
	 * useroService.update(useroLogin); }
	 */
	// private boolean checkDatebase() {
	// // TODO Auto-generated method stub
	// useros = useroService.getUseros();
	// if (useros.size() == 0) {
	// return true;
	// } else {
	// return false;
	// }
	// }

	/**
	 * 设备管理
	 */
	public String list() throws Exception {
		// 判断会话是否失效
		Usero userSession = (Usero) session.get("userSession");
		if (userSession == null) {
			String loginfail = "登陆失效,信息提交失败.";
			request.put("loginFail", loginfail);
			return "opsessiongo";
		}
		if (convalue != null && !convalue.equals("")) {
			convalue = URLDecoder.decode(convalue, "utf-8");
		}
		if (page < 1) {
			page = 1;
		}
		// 总记录数
		totalCount = constructionService.getTotalCount(con, convalue,
				construction);
		// 总页数
		pageCount = constructionService.getPageCount(totalCount, size);
		if (page > pageCount && pageCount != 0) {
			page = pageCount;
		}
		// 所有当前页记录对象
		constructions = constructionService.queryList(con, convalue,
				construction, page, size);
		return "list";
	}

	/**
	 * 跳转到修改页面
	 * 
	 * @return
	 */
	public String load() {

		construction = constructionService.loadById(id);
		return "load";
	}

	public void setAreaIndex(int areaIndex) {
		this.areaIndex = areaIndex;
	}

	public void setAreaVO(AreaVO areaVO) {
		this.areaVO = areaVO;
	}

	public void setAreaVOs(List<AreaVO> areaVOs) {
		this.areaVOs = areaVOs;
	}

	public void setArg(String[] arg) {
		this.arg = arg;
	}

	public void setCheckedIDs(String checkedIDs) {
		this.checkedIDs = checkedIDs;
	}

	public void setCheckTime(String checkTime) {
		this.checkTime = checkTime;
	}

	public void setCon(int con) {
		this.con = con;
	}

	public void setConstruction(Construction construction) {
		this.construction = construction;
	}

	public void setConstructions(List<Construction> constructions) {
		this.constructions = constructions;
	}

	@Resource
	public void setConstructionService(IConstructionService constructionService) {
		this.constructionService = constructionService;
	}

	public void setConvalue(String convalue) {
		this.convalue = convalue;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setInstallTime(String installTime) {
		this.installTime = installTime;
	}

	public void setIsDealUsecard(Integer isDealUsecard) {
		this.isDealUsecard = isDealUsecard;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setPage(int page) {
		this.page = page;
	}

	public void setPageCount(int pageCount) {
		this.pageCount = pageCount;
	}

	public void setPicture(File picture) {
		this.picture = picture;
	}

	public void setPictureContentType(String pictureContentType) {
		this.pictureContentType = pictureContentType;
	}

	public void setPictureFileName(String pictureFileName) {
		this.pictureFileName = pictureFileName;
	}

	public void setPid(int pid) {
		this.pid = pid;
	}

	public void setProject(Project project) {
		this.project = project;
	}

	public void setProjectId(int projectId) {
		this.projectId = projectId;
	}

	public void setProjects(List<Project> projects) {
		this.projects = projects;
	}

	@Resource
	public void setProjectService(IProjectService projectService) {
		this.projectService = projectService;
	}

	public void setPropertyCardNumber(String propertyCardNumber) {
		this.propertyCardNumber = propertyCardNumber;
	}

	public void setRemoveTime(String removeTime) {
		this.removeTime = removeTime;
	}

	public void setReq(javax.servlet.http.HttpServletRequest req) {
		this.req = req;
	}

	public void setRequest(Map<String, Object> request) {
		this.request = request;
	}

	public void setResponse(javax.servlet.http.HttpServletResponse response) {
		this.response = response;
	}

	public void setServletRequest(HttpServletRequest req) {
		this.req = req;
	}

	// 获得HttpServletResponse对象
	public void setServletResponse(HttpServletResponse response) {
		this.response = response;
	}

	public void setSession(Map<String, Object> session) {
		this.session = session;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
	}

	public void setUsecardExpireTime(String usecardExpireTime) {
		this.usecardExpireTime = usecardExpireTime;
	}

	public void setYxareas(List<Yxarea> yxareas) {
		this.yxareas = yxareas;
	}

	@Resource
	public void setYxareaService(IYxareaService yxareaService) {
		this.yxareaService = yxareaService;
	}

	/**
	 * 修改
	 * 
	 * @return
	 */
	public String update() throws Exception {
		// 判断会话是否失效
		// Construction construction = (Construction)
		// session.get("construction");
		// if (construction == null) {
		// return "opsessiongo_child";
		// }

		File pictures[] = { picture1, picture2, picture3, picture4, picture5,
				picture6, picture7, picture8, picture9, picture10, picture11 };
		String pictureFileNames[] = { picture1FileName, picture2FileName,
				picture3FileName, picture4FileName, picture5FileName,
				picture6FileName, picture7FileName, picture8FileName,
				picture9FileName, picture10FileName, picture11FileName };

		for (int i = 0; i < 11; i++)
			//this.savePics(pictures[i], pictureFileNames[i], i, construction);

		constructionService.update(construction);
		arg[0] = "constructionAction!view?pid=" + pid + "&areaIndex="
				+ ((AreaVO) session.get("areaVO")).getIndex();
		arg[1] = "文明施工";
		return SUCCESS;
	}

	public String loadPic() throws Exception {

		construction = constructionService.loadById(cid);

		return "picview";

	}
/*
	public String deleteConstructionpic() throws Exception {

		construction = constructionService.loadByCid(cid);

		switch (pic_row) {
		case 1:
			File photofile1 = new File(ServletActionContext.getServletContext()
					.getRealPath("/")
					+ construction.getWashSetImg());
			photofile1.delete();
			construction.setWashSetImg(null);
			break;
		case 2:
			File photofile2 = new File(ServletActionContext.getServletContext()
					.getRealPath("/")
					+ construction.getWashSetImg());
			photofile2.delete();
			construction.setWaterClearImg("");
			break;
		case 3:
			File photofile3 = new File(ServletActionContext.getServletContext()
					.getRealPath("/")
					+ construction.getWashSetImg());
			photofile3.delete();
			construction.setWashSetImg("");
			break;
		case 4:
			File photofile4 = new File(ServletActionContext.getServletContext()
					.getRealPath("/")
					+ construction.getWashSetImg());
			photofile4.delete();
			construction.setWashSetImg("");
			break;
		case 5:
			File photofile5 = new File(ServletActionContext.getServletContext()
					.getRealPath("/")
					+ construction.getWashSetImg());
			photofile5.delete();
			construction.setWashSetImg("");
			break;
		case 6:
			File photofile6 = new File(ServletActionContext.getServletContext()
					.getRealPath("/")
					+ construction.getWashSetImg());
			photofile6.delete();
			construction.setWashSetImg("");
			break;
		case 7:
			File photofile7 = new File(ServletActionContext.getServletContext()
					.getRealPath("/")
					+ construction.getWashSetImg());
			photofile7.delete();
			construction.setWashSetImg("");
			break;
		case 8:
			File photofile8 = new File(ServletActionContext.getServletContext()
					.getRealPath("/")
					+ construction.getWashSetImg());
			photofile8.delete();
			construction.setWashSetImg("");
			break;
		case 9:
			File photofile9 = new File(ServletActionContext.getServletContext()
					.getRealPath("/")
					+ construction.getWashSetImg());
			photofile9.delete();
			construction.setWashSetImg("");
			break;
		case 10:
			File photofile10 = new File(ServletActionContext
					.getServletContext().getRealPath("/")
					+ construction.getWashSetImg());
			photofile10.delete();
			construction.setWashSetImg("");
			break;
		case 11:
			File photofile11 = new File(ServletActionContext
					.getServletContext().getRealPath("/")
					+ construction.getWashSetImg());
			photofile11.delete();
			construction.setWashSetImg("");
			break;
		}
		// File photofile = new File(ServletActionContext.getServletContext()
		// .getRealPath("/")
		// + construction.getWashSetImg());
		// photofile.delete();

		constructionService.update(construction);
		AjaxMsgVO msgVO = new AjaxMsgVO();
		msgVO.setMessage("删除成功.");
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

	public String savePics(File picture, String picturefilename, int pic_row,
			Construction construction) throws Exception {
		if (picture != null) {
			String imageName = DateTimeKit.getDateRandom()
					+ picturefilename.substring(picturefilename.indexOf("."));
			this.upload("/constructionpic/" + pid, imageName, picture);
			switch (pic_row) {
			case 0:
				construction.setWashSetImg("constructionpic/" + pid + "/"
						+ imageName);
				break;
			case 1:
				construction.setWaterClearImg("constructionpic/" + pid + "/"
						+ imageName);
				break;
			case 2:
				construction.setDoorConformImg("constructionpic/" + pid + "/"
						+ imageName);
				break;
			case 3:
				construction.setDoorConformImg("constructionpic/" + pid + "/"
						+ imageName);
				break;
			case 4:
				construction.setGuardConformImg("constructionpic/" + pid + "/"
						+ imageName);
				break;
			case 5:
				construction.setAdConformImg("constructionpic/" + pid + "/"
						+ imageName);
				break;
			case 6:
				construction.setRoadHardenImg("constructionpic/" + pid + "/"
						+ imageName);
				break;
			case 7:
				construction.setRoadClearImg("constructionpic/" + pid + "/"
						+ imageName);
				break;
			case 8:
				construction.setLabelCompleteImg("constructionpic/" + pid + "/"
						+ imageName);
				break;
			case 9:
				construction.setLayoutRationalImg("constructionpic/" + pid
						+ "/" + imageName);
				break;
			case 10:
				construction.setMeetFireImg("constructionpic/" + pid + "/"
						+ imageName);
				break;
			case 11:
				construction.setMeasurePlaceImg("constructionpic/" + pid + "/"
						+ imageName);
				break;
			}

		}

		return null;
	}
*/
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
	 * 查看信息
	 * 
	 * @return
	 */
	public String view() {
		// Construction construction = (Construction)
		// session.get("construction");
		// if (construction == null) {
		// return "opsessiongo";
		// }
		Usero userSession = (Usero) session.get("userSession");
		if (userSession == null) {
			String loginfail = "登陆失效,信息提交失败.";
			request.put("loginFail", loginfail);
			return "opsessiongo";
		}
		project = projectService.loadByPid(pid);

		if (project.getConstruction() != null) {
			construction = project.getConstruction();
			// checkImg(construction.getId());
			return "view";
		} else {
			return "view";
		}

	}
/*
	public void checkImg(int cid) {
		// TODO Auto-generated method stub
		int flag = 0;
		construction = constructionService.loadByCid(cid);

		File photofile1 = new File(ServletActionContext.getServletContext()
				.getRealPath("/")
				+ construction.getWashSetImg());
		System.out.println(ServletActionContext.getServletContext()
				.getRealPath("/")
				+ construction.getWashSetImg());
		if (!photofile1.exists()) {
			construction.setWashSetImg("");
			flag++;
		}
		File photofile2 = new File(ServletActionContext.getServletContext()
				.getRealPath("/")
				+ construction.getWashSetImg());
		if (!photofile2.exists()) {
			construction.setWaterClearImg("");
			flag++;
		}

		File photofile3 = new File(ServletActionContext.getServletContext()
				.getRealPath("/")
				+ construction.getWashSetImg());
		if (!photofile3.exists()) {
			construction.setWashSetImg("");
			flag++;
		}

		File photofile4 = new File(ServletActionContext.getServletContext()
				.getRealPath("/")
				+ construction.getWashSetImg());
		if (!photofile4.exists()) {
			construction.setWashSetImg("");
			flag++;
		}

		File photofile5 = new File(ServletActionContext.getServletContext()
				.getRealPath("/")
				+ construction.getWashSetImg());
		if (!photofile5.exists()) {
			construction.setWashSetImg("");
			flag++;
		}

		File photofile6 = new File(ServletActionContext.getServletContext()
				.getRealPath("/")
				+ construction.getWashSetImg());
		if (!photofile6.exists()) {
			construction.setWashSetImg("");
			flag++;
		}

		File photofile7 = new File(ServletActionContext.getServletContext()
				.getRealPath("/")
				+ construction.getWashSetImg());
		if (!photofile7.exists()) {
			construction.setWashSetImg("");
			flag++;
		}

		File photofile8 = new File(ServletActionContext.getServletContext()
				.getRealPath("/")
				+ construction.getWashSetImg());
		if (!photofile8.exists()) {
			construction.setWashSetImg("");
			flag++;
		}

		File photofile9 = new File(ServletActionContext.getServletContext()
				.getRealPath("/")
				+ construction.getWashSetImg());
		if (!photofile9.exists()) {
			construction.setWashSetImg("");
			flag++;
		}

		File photofile10 = new File(ServletActionContext.getServletContext()
				.getRealPath("/")
				+ construction.getWashSetImg());
		if (!photofile10.exists()) {
			construction.setWashSetImg("");
			flag++;
		}

		File photofile11 = new File(ServletActionContext.getServletContext()
				.getRealPath("/")
				+ construction.getWashSetImg());
		if (!photofile11.exists()) {
			construction.setWashSetImg("");
			flag++;
		}

		if (flag != 0)
			constructionService.update(construction);
	}
*/
	public File get() {
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

	public int getPic_row() {
		return pic_row;
	}

	public void setPic_row(int pic_row) {
		this.pic_row = pic_row;
	}

	public int getCid() {
		return cid;
	}

	public void setCid(int cid) {
		this.cid = cid;
	}

	public File getPicture2() {
		return picture2;
	}

	public void setPicture2(File picture2) {
		this.picture2 = picture2;
	}

	public String getPicture2ContentType() {
		return picture2ContentType;
	}

	public void setPicture2ContentType(String picture2ContentType) {
		this.picture2ContentType = picture2ContentType;
	}

	public String getPicture2FileName() {
		return picture2FileName;
	}

	public void setPicture2FileName(String picture2FileName) {
		this.picture2FileName = picture2FileName;
	}

	public File getPicture3() {
		return picture3;
	}

	public void setPicture3(File picture3) {
		this.picture3 = picture3;
	}

	public String getPicture3ContentType() {
		return picture3ContentType;
	}

	public void setPicture3ContentType(String picture3ContentType) {
		this.picture3ContentType = picture3ContentType;
	}

	public String getPicture3FileName() {
		return picture3FileName;
	}

	public void setPicture3FileName(String picture3FileName) {
		this.picture3FileName = picture3FileName;
	}

	public File getPicture4() {
		return picture4;
	}

	public void setPicture4(File picture4) {
		this.picture4 = picture4;
	}

	public String getPicture4ContentType() {
		return picture4ContentType;
	}

	public void setPicture4ContentType(String picture4ContentType) {
		this.picture4ContentType = picture4ContentType;
	}

	public String getPicture4FileName() {
		return picture4FileName;
	}

	public void setPicture4FileName(String picture4FileName) {
		this.picture4FileName = picture4FileName;
	}

	public File getPicture5() {
		return picture5;
	}

	public void setPicture5(File picture5) {
		this.picture5 = picture5;
	}

	public String getPicture5ContentType() {
		return picture5ContentType;
	}

	public void setPicture5ContentType(String picture5ContentType) {
		this.picture5ContentType = picture5ContentType;
	}

	public String getPicture5FileName() {
		return picture5FileName;
	}

	public void setPicture5FileName(String picture5FileName) {
		this.picture5FileName = picture5FileName;
	}

	public File getPicture6() {
		return picture6;
	}

	public void setPicture6(File picture6) {
		this.picture6 = picture6;
	}

	public String getPicture6ContentType() {
		return picture6ContentType;
	}

	public void setPicture6ContentType(String picture6ContentType) {
		this.picture6ContentType = picture6ContentType;
	}

	public String getPicture6FileName() {
		return picture6FileName;
	}

	public void setPicture6FileName(String picture6FileName) {
		this.picture6FileName = picture6FileName;
	}

	public File getPicture7() {
		return picture7;
	}

	public void setPicture7(File picture7) {
		this.picture7 = picture7;
	}

	public String getPicture7ContentType() {
		return picture7ContentType;
	}

	public void setPicture7ContentType(String picture7ContentType) {
		this.picture7ContentType = picture7ContentType;
	}

	public String getPicture7FileName() {
		return picture7FileName;
	}

	public void setPicture7FileName(String picture7FileName) {
		this.picture7FileName = picture7FileName;
	}

	public File getPicture8() {
		return picture8;
	}

	public void setPicture8(File picture8) {
		this.picture8 = picture8;
	}

	public String getPicture8ContentType() {
		return picture8ContentType;
	}

	public void setPicture8ContentType(String picture8ContentType) {
		this.picture8ContentType = picture8ContentType;
	}

	public String getPicture8FileName() {
		return picture8FileName;
	}

	public void setPicture8FileName(String picture8FileName) {
		this.picture8FileName = picture8FileName;
	}

	public File getPicture9() {
		return picture9;
	}

	public void setPicture9(File picture9) {
		this.picture9 = picture9;
	}

	public String getPicture9ContentType() {
		return picture9ContentType;
	}

	public void setPicture9ContentType(String picture9ContentType) {
		this.picture9ContentType = picture9ContentType;
	}

	public String getPicture9FileName() {
		return picture9FileName;
	}

	public void setPicture9FileName(String picture9FileName) {
		this.picture9FileName = picture9FileName;
	}

	public File getPicture10() {
		return picture10;
	}

	public void setPicture10(File picture10) {
		this.picture10 = picture10;
	}

	public String getPicture10ContentType() {
		return picture10ContentType;
	}

	public void setPicture10ContentType(String picture10ContentType) {
		this.picture10ContentType = picture10ContentType;
	}

	public String getPicture10FileName() {
		return picture10FileName;
	}

	public void setPicture10FileName(String picture10FileName) {
		this.picture10FileName = picture10FileName;
	}

	public File getPicture11() {
		return picture11;
	}

	public void setPicture11(File picture11) {
		this.picture11 = picture11;
	}

	public String getPicture11ContentType() {
		return picture11ContentType;
	}

	public void setPicture11ContentType(String picture11ContentType) {
		this.picture11ContentType = picture11ContentType;
	}

	public String getPicture11FileName() {
		return picture11FileName;
	}

	public void setPicture11FileName(String picture11FileName) {
		this.picture11FileName = picture11FileName;
	}

}
