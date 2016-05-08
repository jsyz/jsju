package com.yz.action;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Iterator;
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
import com.yz.model.Usero;
import com.yz.model.Yxarea;
import com.yz.service.IYxareaService;
import com.yz.util.ConvertUtil;
import com.yz.vo.AjaxMsgVO;
import com.yz.vo.AreaVO;

/**
 * @author lq
 * 
 */
@Component("yxareaAction")
@Scope("prototype")
public class YxareaAction extends ActionSupport implements RequestAware,
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

	// 批量删除
	private String checkedIDs;

	// service层对象
	private IYxareaService yxareaService;

	// 单个对象
	private Usero usero;
	private Yxarea yxarea;
	private AreaVO areaVO;

	// list对象
	private List<Yxarea> yxareas;
	private List<AreaVO> areaVOs;
	private List<Project> projects;

	// 总计
	private int allNumberTotal;
	private float allAreaTotal;
	private float allCostTotal;

	/**
	 * 区域管理
	 */
	public String list() throws Exception {
		initAreas();
		for (int i = 0; i < areaVOs.size(); i++) {
			allNumberTotal += areaVOs.get(i).getProjectNumberTotal();
			allAreaTotal += areaVOs.get(i).getBuildingAreaTotal();
			allCostTotal += areaVOs.get(i).getBuildingCostTotal();
		}

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

			// 处理已上传区域项目数量
			projects = new ArrayList<Project>();
			List<Project> pros = yxarea.getProjects();
			if (pros != null && pros.size() > 0) {
				for (Project project : pros) {
					if (project.getIsUpload() != null
							&& project.getIsUpload() == 1) {
						projects.add(project);
					}
				}
			}

			numberTotal = projects.size();
			for (int i = 0; i < projects.size(); i++) {
				areaTotal += projects.get(i).getBuildingArea();
				costTotal += projects.get(i).getBuildingCost();
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
		Usero usero = (Usero) session.get("usero");
		if (usero == null) {
			return "opsessiongo_child";
		}
		yxareaService.add(yxarea);

		arg[0] = "yxareaAction!list";
		arg[1] = "区域管理";
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
		Usero usero = (Usero) session.get("usero");
		if (usero == null) {
			return "opsessiongo";
		}

		yxarea = yxareaService.loadById(id);

		yxareaService.delete(yxarea);

		yxareaService.deleteById(id);
		arg[0] = "yxareaAction!list";
		arg[1] = "区域管理";
		return SUCCESS;
	}

	/**
	 * 删除二(批量删除)
	 * 
	 * @return
	 */
	public String deleteUseros() {

		int[] ids = ConvertUtil.StringtoInt(checkedIDs);
		for (int i = 0; i < ids.length; i++) {
			yxarea = yxareaService.loadById(ids[i]);

			yxareaService.delete(yxarea);
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

		yxarea = yxareaService.loadById(id);
		return "load";
	}

	/**
	 * 修改
	 * 
	 * @return
	 */
	public String update() throws Exception {
		// 判断会话是否失效
		Usero usero = (Usero) session.get("usero");
		if (usero == null) {
			return "opsessiongo_child";
		}

		yxareaService.update(yxarea);
		arg[0] = "yxareaAction!list";
		arg[1] = "区域管理";
		return "success_child";
	}

	/**
	 * 查看信息
	 * 
	 * @return
	 */
	public String view() {
		Usero usero = (Usero) session.get("usero");
		if (usero == null) {
			return "opsessiongo";
		}
		yxarea = yxareaService.loadById(id);
		return "view";
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

	public Usero getUsero() {
		return usero;
	}

	public void setUsero(Usero usero) {
		this.usero = usero;
	}

	public Yxarea getYxarea() {
		return yxarea;
	}

	public void setYxarea(Yxarea yxarea) {
		this.yxarea = yxarea;
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

	public List<Project> getProjects() {
		return projects;
	}

	public void setProjects(List<Project> projects) {
		this.projects = projects;
	}

	public int getAllNumberTotal() {
		return allNumberTotal;
	}

	public void setAllNumberTotal(int allNumberTotal) {
		this.allNumberTotal = allNumberTotal;
	}

	public float getAllAreaTotal() {
		return allAreaTotal;
	}

	public void setAllAreaTotal(float allAreaTotal) {
		this.allAreaTotal = allAreaTotal;
	}

	public float getAllCostTotal() {
		return allCostTotal;
	}

	public void setAllCostTotal(float allCostTotal) {
		this.allCostTotal = allCostTotal;
	}

}
