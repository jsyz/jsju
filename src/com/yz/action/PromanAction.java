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
import com.yz.model.Proman;
import com.yz.model.Usero;
import com.yz.model.Yxarea;
import com.yz.service.IProjectService;
import com.yz.service.IPromanService;
import com.yz.service.IYxareaService;
import com.yz.util.ConvertUtil;
import com.yz.util.DateTimeKit;
import com.yz.util.InitParam;
import com.yz.vo.AjaxMsgVO;
import com.yz.vo.AreaVO;

/**
 * @author lq
 * 
 */
@Component("promanAction")
@Scope("prototype")
public class PromanAction extends ActionSupport implements RequestAware,
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
	private int areaIndex;

	// 批量删除
	private String checkedIDs;

	// service层对象
	private IPromanService promanService;
	private IYxareaService yxareaService;
	private IProjectService projectService;

	// 单个对象
	private Proman proman;
	private Project project;
	private AreaVO areaVO;
	// list对象
	private List<Proman> promans;
	private List<Project> projects;
	private List<Yxarea> yxareas;
	private List<AreaVO> areaVOs;
	
	public String list() throws Exception {
		// 判断会话是否失效
		/*Proman proman = (Proman) session.get("proman");
		if (proman == null) {
			return "opsessiongo";
		}*/
		if (convalue != null && !convalue.equals("")) {
			convalue = URLDecoder.decode(convalue, "utf-8");
		}
		if (page < 1) {
			page = 1;
		}
		
		Usero userSession = (Usero) session.get("userSession");
		if (userSession == null) {
			String loginfail = "登陆失效,信息提交失败.";
			request.put("loginFail", loginfail);
			return "opsessiongo";
		}
		project = projectService.loadById(pid);
		
		// 总记录数
		totalCount = promanService.getTotalCount(con, convalue, pid);
		//// 总页数
		pageCount = promanService.getPageCount(totalCount, size);
		if (page > pageCount && pageCount != 0) {
			page = pageCount;
		}
		// 所有当前页记录对象
		promans = promanService.queryList(con, convalue, pid, page, size);
		//promans = promanService.getPromans();
		return "list";
	}

	/**
	 * 跳转到添加页面
	 * 
	 * @return
	 */
	public String goToAdd() {
		Usero userSession = (Usero) session.get("userSession");
		if (userSession == null) {
			String loginfail = "登陆失效,信息提交失败.";
			request.put("loginFail", loginfail);
			return "opsessiongo";
		}
		project = projectService.loadById(pid);
		return "add";
	}

	/**
	 * 添加
	 * 
	 * @return
	 * @throws Exception
	 */

	public String add() throws Exception {
		Usero userSession = (Usero) session.get("userSession");
		if (userSession == null) {
			String loginfail = "登陆失效,信息提交失败.";
			request.put("loginFail", loginfail);
			return "opsessiongo";
		}
		
		String imageName = DateTimeKit.getDateRandom()
		+ pictureFileName.substring(pictureFileName
				.indexOf("."));
		
		this.upload("/promanpic/"+pid, imageName, picture);
		
//		System.out.println("the certificate is"+"promanpic/"+pid+ "/"+ imageName);
		
		proman.setCertificate("promanpic/"+pid+ "/"+ imageName);
		
		promanService.add(proman);

		arg[0] = "promanAction!list?pid="+pid+"&areaIndex="
		+ ((AreaVO) session.get("areaVO")).getIndex();
		arg[1] = "人员管理";
		return "success";
	}
	
	// 上传照片
	private File picture;
	private String pictureContentType;
	private String pictureFileName;

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
//		Proman proman = (Proman) session.get("proman");
//		if (proman == null) {
//			return "opsessiongo";
//		}

		proman = promanService.loadById(id);

		promanService.delete(proman);

		//promanService.deleteById(id);
		arg[0] = "promanAction!list?pid="+pid+"&areaIndex="
		+ ((AreaVO) session.get("areaVO")).getIndex();
		arg[1] = "人员管理";
		return SUCCESS;
	}

	/**
	 * 删除二(批量删除)
	 * 
	 * @return
	 */
	public String deletePromans() {

		int[] ids = ConvertUtil.StringtoInt(checkedIDs);
		for (int i = 0; i < ids.length; i++) {
			proman = promanService.loadById(ids[i]);

			promanService.delete(proman);
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
	 * 修改
	 * 
	 * @return
	 */
	public String load() {
		Usero userSession = (Usero) session.get("userSession");
		if (userSession == null) {
			String loginfail = "登陆失效,信息提交失败.";
			request.put("loginFail", loginfail);
			return "opsessiongo";
		}
		project = projectService.loadById(pid);
		proman = promanService.loadById(id);
		return "load";
	}

	
	public String loadPic() throws Exception {
		
		proman = promanService.loadById(id);
		
		return "picview";
		
	}
	public String update() throws Exception {
		Usero userSession = (Usero) session.get("userSession");
		if (userSession == null) {
			String loginfail = "登陆失效,信息提交失败.";
			request.put("loginFail", loginfail);
			return "opsessiongo";
		}

		promanService.update(proman);
		arg[0] = "promanAction!list?pid="+pid+"&areaIndex="
		+ ((AreaVO) session.get("areaVO")).getIndex();
		arg[1] = "人员管理";
		return "success";
	}


	/**
	 * 查看信息
	 * 
	 * @return
	 */
	public String view() {
		Usero userSession = (Usero) session.get("userSession");
		if (userSession == null) {
			String loginfail = "登陆失效,信息提交失败.";
			request.put("loginFail", loginfail);
			return "opsessiongo";
		}
		proman = promanService.loadById(id);
		return "view";
	}

	/**
	 * 个人资料
	 */
	public String currentProman() {
		Proman proman = (Proman) session.get("proman");
		if (proman == null) {
			return "opsessiongo";
		}
		proman = promanService.loadById(proman.getId());
		;
		return "currentProman";
	}

	public String updateCurrentProman() throws Exception {
		Proman proman = (Proman) session.get("proman");
		if (proman == null) {
			return "opsessiongo";
		}
//		if (password1 != null && !password1.replace(" ", "").equals("")
//				&& password2 != null && !password2.replace(" ", "").equals("")) {
//			proman.setPassword(password1);
//		}
		promanService.update(proman);
		arg[0] = "promanAction!currentProman";
		arg[1] = "个人资料";
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

	public IPromanService getPromanService() {
		return promanService;
	}

	@Resource
	public void setPromanService(IPromanService promanService) {
		this.promanService = promanService;
	}

	public Proman getProman() {
		return proman;
	}

	public void setProman(Proman proman) {
		this.proman = proman;
	}

	public List<Proman> getPromans() {
		return promans;
	}

	public void setPromans(List<Proman> promans) {
		this.promans = promans;
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

	public File getPicture() {
		return picture;
	}

	public void setPicture(File picture) {
		this.picture = picture;
	}

	public String getPictureContentType() {
		return pictureContentType;
	}

	public void setPictureContentType(String pictureContentType) {
		this.pictureContentType = pictureContentType;
	}

	public String getPictureFileName() {
		return pictureFileName;
	}

	public void setPictureFileName(String pictureFileName) {
		this.pictureFileName = pictureFileName;
	}


	public IYxareaService getYxareaService() {
		return yxareaService;
	}

	@Resource
	public void setYxareaService(IYxareaService yxareaService) {
		this.yxareaService = yxareaService;
	}

	public int getAreaIndex() {
		return areaIndex;
	}

	public void setAreaIndex(int areaIndex) {
		this.areaIndex = areaIndex;
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

	public AreaVO getAreaVO() {
		return areaVO;
	}

	public void setAreaVO(AreaVO areaVO) {
		this.areaVO = areaVO;
	}

	public List<Project> getProjects() {
		return projects;
	}

	public void setProjects(List<Project> projects) {
		this.projects = projects;
	}

	public List<Yxarea> getYxareas() {
		return yxareas;
	}

	public void setYxareas(List<Yxarea> yxareas) {
		this.yxareas = yxareas;
	}

	public List<AreaVO> getAreaVOs() {
		return areaVOs;
	}

	public void setAreaVOs(List<AreaVO> areaVOs) {
		this.areaVOs = areaVOs;
	}


	
	
	
}
