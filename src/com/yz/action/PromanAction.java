package com.yz.action;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.URLDecoder;
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
import com.yz.model.Proman;
import com.yz.model.Yxarea;
import com.yz.service.IPromanService;
import com.yz.service.IYxareaService;
import com.yz.util.ConvertUtil;
import com.yz.util.InitParam;
import com.yz.vo.AjaxMsgVO;

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

	// 登陆
	private String username;
	private String password;

	// 批量删除
	private String checkedIDs;

	// service层对象
	private IPromanService promanService;
	private IYxareaService yxareaService;

	// 单个对象
	private Proman proman;

	// list对象
	private List<Proman> promans;
	private List<Yxarea> areas;

	// 个人资料新旧密码
	private String password1;
	private String password2;

//	/**
//	 * 用户登陆
//	 * 
//	 * @throws Exception
//	 */
//	public String login() throws Exception {
//
//		if (checkDatebase())// 检查数据库
//		{
//			promanService.add(InitParam.getProman());
//			session.put("proman", InitParam.getProman());
//			return "loginSucc";
//		}
//		if (username == null || username.equals("") || password == null
//				|| password.equals("")) {
//			String loginfail = "用户名或密码不能为空";
//			request.put("loginFail", loginfail);
//			return "adminLogin";
//		}
//		Proman promanLogin = promanService.promanLogin(username, password);
//		if (promanLogin == null) {
//			String loginfail = "用户名或密码输入有误";
//			request.put("loginFail", loginfail);
//			return "adminLogin";
//		} else {
//			// 设置登陆时间
//			if (session.get("proman") == null) {
//				//setLoginTime(promanLogin);
//				session.put("proman", promanLogin);
//			}
//			// checkIP();//检查IP地址
//			return "loginSucc";
//		}
//	}
//
//	public String welcome() {
//		// 登陆验证
//		Proman proman = (Proman) session.get("proman");
//		if (proman == null) {
//			return "opsessiongo";
//		}
//		Proman promanWelcome = promanService.loadById(proman.getId());
//		// 欢迎界面
//		return "welcome";
//	}

	// 设置登陆时间
	
	/**
	 * 检查数据库,初始化默认登录及区域
	 * @throws Exception 
	 */
	private boolean checkDatebase() throws Exception {
		// TODO Auto-generated method stub
		areas = yxareaService.getYxareas();
		if(areas==null||areas.size()!=9)
		{
			yxareaService.deleteAllAreas(areas);
			for (int i = 0; i < InitParam.AREAS.length; i++) {
				Yxarea yxarea = new Yxarea();
				yxarea.setAreaname(InitParam.AREAS[0]);
				yxarea.setIndex(i+1);
				yxareaService.add(yxarea);
			}
		}
		
		promans = promanService.getPromans();
		if (promans==null||promans.size() == 0) {
			return true;
		} else {
			return false;
		}
	}

	private void checkIP() {
		// TODO Auto-generated method stub
		// String ip = getIpAddr(req);
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

	/**
	 * 用户注销
	 */
	public String logout() {
		session.clear();
		return "adminLogin";
	}

	/**
	 * 用户管理
	 */
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
		// 总记录数
		totalCount = promanService.getTotalCount(con, convalue, proman);
		// 总页数
		pageCount = promanService.getPageCount(totalCount, size);
		if (page > pageCount && pageCount != 0) {
			page = pageCount;
		}
		// 所有当前页记录对象
		promans = promanService.queryList(con, convalue, proman, page, size);
		return "list";
	}

	/**
	 * 跳转到添加页面
	 * 
	 * @return
	 */
	public String goToAdd() {
		areas = yxareaService.getYxareas();
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
		Proman proman = (Proman) session.get("proman");
		if (proman == null) {
			return "opsessiongo_child";
		}
		promanService.add(proman);

		arg[0] = "promanAction!list";
		arg[1] = "人员管理";
		return "success_child";
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
		Proman proman = (Proman) session.get("proman");
		if (proman == null) {
			return "opsessiongo";
		}

		proman = promanService.loadById(id);

		promanService.delete(proman);

		promanService.deleteById(id);
		arg[0] = "promanAction!list";
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
	 * 跳转到修改页面
	 * 
	 * @return
	 */
	public String load() {

		proman = promanService.loadById(id);
		return "load";
	}

	/**
	 * 修改
	 * 
	 * @return
	 */
	public String update() throws Exception {
		// 判断会话是否失效
		Proman proman = (Proman) session.get("proman");
		if (proman == null) {
			return "opsessiongo_child";
		}

		promanService.update(proman);
		arg[0] = "promanAction!list";
		arg[1] = "人员管理";
		return "success_child";
	}

//	/**
//	 * 跳转到修改秒页面
//	 * 
//	 * @return
//	 */
//	public String loadPassword() throws Exception {
//		Proman proman = (Proman) session.get("proman");
//		if (proman == null) {
//			return "opsessiongo";
//		}
//		password = proman.getPassword();
//		return "password";
//	}
//
//	/**
//	 * 修改密码
//	 * 
//	 * @return
//	 */
//	public String updatePassword() throws Exception {
//		// 判断会话是否失效
//		Proman proman = (Proman) session.get("proman");
//		if (proman == null) {
//			return "opsessiongo";
//		}
//		proman.setPassword(password);
//		promanService.update(proman);
//		arg[0] = "promanAction!list";
//		arg[1] = "用户管理";
//		return SUCCESS;
//	}

	/**
	 * 查看信息
	 * 
	 * @return
	 */
	public String view() {
		Proman proman = (Proman) session.get("proman");
		if (proman == null) {
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

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
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

	public String getPassword1() {
		return password1;
	}

	public void setPassword1(String password1) {
		this.password1 = password1;
	}

	public String getPassword2() {
		return password2;
	}

	public void setPassword2(String password2) {
		this.password2 = password2;
	}

	public IYxareaService getYxareaService() {
		return yxareaService;
	}

	@Resource
	public void setYxareaService(IYxareaService yxareaService) {
		this.yxareaService = yxareaService;
	}

	public List<Yxarea> getAreas() {
		return areas;
	}

	public void setAreas(List<Yxarea> areas) {
		this.areas = areas;
	}
	
	
	
}
