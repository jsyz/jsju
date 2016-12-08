package com.yz.action;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.RequestAware;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;
import org.apache.struts2.interceptor.SessionAware;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.opensymphony.xwork2.ActionSupport;
import com.yz.model.Concreterecord;
import com.yz.model.Usero;
import com.yz.service.IConcreterecordService;
import com.yz.service.IFileUploadService;
import com.yz.util.DateTimeKit;
import com.yz.vo.AreaVO;

/**
 * @author Administrator
 * 
 */

@Component("concreterecordAction")
@Scope("prototype")
public class ConcreterecordAction extends ActionSupport implements
		RequestAware, SessionAware, ServletResponseAware, ServletRequestAware {

	/**
	 * 
	 */

	Map<String, Object> request;
	Map<String, Object> session;
	private javax.servlet.http.HttpServletResponse response;
	private javax.servlet.http.HttpServletRequest req;
	private static final long serialVersionUID = 1L;

	private File picture;
	private String pictureContentType;
	private String pictureFileName;

	private File picture1;
	private String picture1ContentType;
	private String picture1FileName;

	private File picture2;
	private String picture2ContentType;
	private String picture2FileName;

	private File picture3;
	private String picture3ContentType;
	private String picture3FileName;

	private File picture4;
	private String picture4ContentType;
	private String picture4FileName;

	private File picture5;
	private String picture5ContentType;
	private String picture5FileName;

	private List<File> myFile = new ArrayList<File>();
	private List<String> myFileContentType = new ArrayList<String>();
	private List<String> myFileFileName = new ArrayList<String>(); // 文件名

	@Resource
	private IFileUploadService fileUploadService;

	// 分页显示
	private String[] arg = new String[2];
	private int page;
	private final int size = 10;
	private int pageCount;
	private int totalCount;

	// 条件
	private int id;
	private int picid;
	private int con;
	private String convalue;
	private int concreterecordType;

	private String concreterecordpicDir;

	private Concreterecord concreterecord;

	private List<Concreterecord> concreterecords;

	private IConcreterecordService concreterecordService;

	public Concreterecord getConcreterecord() {
		return concreterecord;
	}

	public void setConcreterecord(Concreterecord concreterecord) {
		this.concreterecord = concreterecord;
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

	public Map<String, Object> getRequest() {
		return request;
	}

	public Map<String, Object> getSession() {
		return session;
	}

	public javax.servlet.http.HttpServletResponse getResponse() {
		return response;
	}

	public javax.servlet.http.HttpServletRequest getReq() {
		return req;
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

	public void setServletResponse(HttpServletResponse response) {
		this.response = response;
	}

	public void setSession(Map<String, Object> session) {
		this.session = session;
	}

	public String list() throws Exception {

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

		pageCount = concreterecordService.getPageCount(totalCount, size);
		if (page > pageCount && pageCount != 0) {
			page = pageCount;
		}
		// 所有当前页记录对象

		// if(concreterecordType == 0){
		// con = 0;
		// }else if(concreterecordType == 1){
		// con = 1;
		// }

		concreterecords = concreterecordService.queryList(concreterecordType,
				con, convalue, page, size);

		// System.out.println(concreterecords.size());

		return "list";
	}

	public String delete() {
		// 判断会话是否失效
		// Proman proman = (Proman) session.get("proman");
		// if (proman == null) {
		// return "opsessiongo";
		// }

		concreterecord = concreterecordService.loadById(id);

		concreterecordService.delete(concreterecord);

		// promanService.deleteById(id);
		arg[0] = "concreterecordAction!list?concreterecordType="
				+ concreterecordType;
		arg[1] = "备案合同管理";
		return SUCCESS;
	}

	// public String list2() throws Exception {
	//
	// if (convalue != null && !convalue.equals("")) {
	// convalue = URLDecoder.decode(convalue, "utf-8");
	// }
	// if (page < 1) {
	// page = 1;
	// }
	//
	// Usero userSession = (Usero) session.get("userSession");
	// if (userSession == null) {
	// String loginfail = "登陆失效,信息提交失败.";
	// request.put("loginFail", loginfail);
	// return "opsessiongo";
	// }
	//
	// pageCount = concreterecordService.getPageCount(totalCount, size);
	// if (page > pageCount && pageCount != 0) {
	// page = pageCount;
	// }
	// // 所有当前页记录对象
	// concreterecords = concreterecordService.queryList(con, convalue, page,
	// size);
	//
	// return "list2";
	// }

	public String goToAdd() {

		Usero userSession = (Usero) session.get("userSession");
		if (userSession == null) {
			String loginfail = "登陆失效,信息提交失败.";
			request.put("loginFail", loginfail);
			return "opsessiongo";
		}
		return "add";
	}

	public String add() throws Exception {
		System.out.println("the concreterecordType is " + concreterecordType);
		Usero userSession = (Usero) session.get("userSession");
		if (userSession == null) {
			String loginfail = "登陆失效,信息提交失败.";
			request.put("loginFail", loginfail);
			return "opsessiongo";
		}

		// fileUploadService.upload(myFile, myFileFileName, myFileContentType,
		// "concreterecord");
		//		
		// for(int i = 0;i<myFile.size();i++){
		// switch(i){
		// case 0:
		// concreterecord
		// .setContractRecordForm(myFileFileName.get(i));
		// break;
		// case 1:
		// concreterecord
		// .setLiabilityUndertaking(myFileFileName.get(i));
		// break;
		// case 2:
		// concreterecord
		// .setLetterOfAttorney(myFileFileName.get(i));
		// break;
		// case 3:
		// concreterecord
		// .setRepresentativeIdCard(myFileFileName.get(i));
		// break;
		// case 4:
		// concreterecord
		// .setProjectLeaderIdCatd(myFileFileName.get(i));
		// break;
		// case 5:
		// concreterecord
		// .setContract(myFileFileName.get(i));
		// break;
		//				
		//				
		// }
		// }

		if (picture != null && pictureFileName != null
				&& pictureFileName.contains(".")) {
			String imageName = DateTimeKit.getDateRandom()
					+ pictureFileName.substring(pictureFileName.indexOf("."));

			this.upload("concreterecord/contractRecordFormPic/", imageName,
					picture);
			concreterecord
					.setContractRecordForm("concreterecord/contractRecordFormPic/"
							+ imageName);
		}

		if (picture1 != null && picture1FileName != null
				&& picture1FileName.contains(".")) {
			String imageName = DateTimeKit.getDateRandom()
					+ picture1FileName.substring(picture1FileName.indexOf("."));

			this.upload("concreterecord/liabilityUndertakingPic/", imageName,
					picture1);
			concreterecord
					.setLiabilityUndertaking("concreterecord/liabilityUndertakingPic/"
							+ imageName);
		}

		if (picture2 != null && picture2FileName != null
				&& picture2FileName.contains(".")) {
			String imageName = DateTimeKit.getDateRandom()
					+ picture2FileName.substring(picture2FileName.indexOf("."));

			this.upload("concreterecord/letterOfAttorneyPic/", imageName,
					picture2);
			concreterecord
					.setLetterOfAttorney("concreterecord/letterOfAttorneyPic/"
							+ imageName);
		}

		if (picture3 != null && picture3FileName != null
				&& picture3FileName.contains(".")) {
			String imageName = DateTimeKit.getDateRandom()
					+ picture3FileName.substring(picture3FileName.indexOf("."));

			this.upload("concreterecord/representativeIdCardPic/", imageName,
					picture3);
			concreterecord
					.setRepresentativeIdCard("concreterecord/representativeIdCardPic/"
							+ imageName);
		}

		if (picture4 != null && picture4FileName != null
				&& picture4FileName.contains(".")) {
			String imageName = DateTimeKit.getDateRandom()
					+ picture4FileName.substring(picture4FileName.indexOf("."));

			this.upload("concreterecord/projectLeaderIdCatdPic/", imageName,
					picture4);
			concreterecord
					.setProjectLeaderIdCatd("concreterecord/projectLeaderIdCatdPic/"
							+ imageName);
		}

		if (picture5 != null && picture5FileName != null
				&& picture5FileName.contains(".")) {
			String imageName = DateTimeKit.getDateRandom()
					+ picture5FileName.substring(picture5FileName.indexOf("."));

			this.upload("concreterecord/contractPic/", imageName, picture5);
			concreterecord.setContract("concreterecord/contractPic/"
					+ imageName);
		}
		concreterecord.setConcreterecordType(concreterecordType);
		concreterecordService.add(concreterecord);

		arg[0] = "concreterecordAction!list?concreterecordType="
				+ concreterecordType;
		arg[1] = "备案合同管理";
		return "success";

	}

	public String load() throws Exception {
		Usero userSession = (Usero) session.get("userSession");
		if (userSession == null) {
			String loginfail = "登陆失效,信息提交失败.";
			request.put("loginFail", loginfail);
			return "opsessiongo";
		}
		concreterecord = concreterecordService.loadById(id);
		return "load";

	}

	public String update() throws Exception {
		Usero userSession = (Usero) session.get("userSession");
		if (userSession == null) {
			String loginfail = "登陆失效,信息提交失败.";
			request.put("loginFail", loginfail);
			return "opsessiongo";
		}

		if (picture != null && pictureFileName != null
				&& pictureFileName.contains(".")) {
			String imageName = DateTimeKit.getDateRandom()
					+ pictureFileName.substring(pictureFileName.indexOf("."));

			this.upload("concreterecord/contractRecordFormPic/", imageName,
					picture);
			concreterecord
					.setContractRecordForm("concreterecord/contractRecordFormPic/"
							+ imageName);
		}

		if (picture1 != null && picture1FileName != null
				&& picture1FileName.contains(".")) {
			String imageName = DateTimeKit.getDateRandom()
					+ picture1FileName.substring(picture1FileName.indexOf("."));

			this.upload("concreterecord/liabilityUndertakingPic/", imageName,
					picture1);
			concreterecord
					.setLiabilityUndertaking("concreterecord/liabilityUndertakingPic/"
							+ imageName);
		}

		if (picture2 != null && picture2FileName != null
				&& picture2FileName.contains(".")) {
			String imageName = DateTimeKit.getDateRandom()
					+ picture2FileName.substring(picture2FileName.indexOf("."));

			this.upload("concreterecord/letterOfAttorneyPic/", imageName,
					picture2);
			concreterecord
					.setLetterOfAttorney("concreterecord/letterOfAttorneyPic/"
							+ imageName);
		}

		if (picture3 != null && picture3FileName != null
				&& picture3FileName.contains(".")) {
			String imageName = DateTimeKit.getDateRandom()
					+ picture3FileName.substring(picture3FileName.indexOf("."));

			this.upload("concreterecord/representativeIdCardPic/", imageName,
					picture3);
			concreterecord
					.setRepresentativeIdCard("concreterecord/representativeIdCardPic/"
							+ imageName);
		}

		if (picture4 != null && picture4FileName != null
				&& picture4FileName.contains(".")) {
			String imageName = DateTimeKit.getDateRandom()
					+ picture4FileName.substring(picture4FileName.indexOf("."));

			this.upload("concreterecord/projectLeaderIdCatdPic/", imageName,
					picture4);
			concreterecord
					.setProjectLeaderIdCatd("concreterecord/projectLeaderIdCatdPic/"
							+ imageName);
		}

		if (picture5 != null && picture5FileName != null
				&& picture5FileName.contains(".")) {
			String imageName = DateTimeKit.getDateRandom()
					+ picture5FileName.substring(picture5FileName.indexOf("."));

			this.upload("concreterecord/contractPic/", imageName, picture5);
			concreterecord.setContract("concreterecord/contractPic/"
					+ imageName);
		}
		concreterecord.setConcreterecordType(concreterecordType);
		
		concreterecordService.update(concreterecord);
		arg[0] = "concreterecordAction!list?concreterecordType="
				+ concreterecordType;
		arg[1] = "备案合同管理";
		return "success";
	}

	public String loadPic() throws Exception {

		concreterecord = concreterecordService.loadById(id);

		switch (picid) {
		case 1:
			concreterecordpicDir = concreterecord.getContractRecordForm();
			break;
		case 2:
			concreterecordpicDir = concreterecord.getLiabilityUndertaking();
			break;
		case 3:
			concreterecordpicDir = concreterecord.getLetterOfAttorney();
			break;
		case 4:
			concreterecordpicDir = concreterecord.getRepresentativeIdCard();
			break;
		case 5:
			concreterecordpicDir = concreterecord.getProjectLeaderIdCatd();
			break;
		case 6:
			concreterecordpicDir = concreterecord.getContract();
			break;
		}

		return "picview";

	}

	public IConcreterecordService getConcreterecordService() {
		return concreterecordService;
	}

	@Resource
	public void setConcreterecordService(
			IConcreterecordService concreterecordService) {
		this.concreterecordService = concreterecordService;
	}

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

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
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

	public List<Concreterecord> getConcreterecords() {
		return concreterecords;
	}

	public void setConcreterecords(List<Concreterecord> concreterecords) {
		this.concreterecords = concreterecords;
	}

	public String getPicture4FileName() {
		return picture4FileName;
	}

	public void setPicture4FileName(String picture4FileName) {
		this.picture4FileName = picture4FileName;
	}

	public int getConcreterecordType() {
		return concreterecordType;
	}

	public void setConcreterecordType(int concreterecordType) {
		this.concreterecordType = concreterecordType;
	}

	public List<File> getMyFile() {
		return myFile;
	}

	public void setMyFile(List<File> myFile) {
		this.myFile = myFile;
	}

	public List<String> getMyFileContentType() {
		return myFileContentType;
	}

	public void setMyFileContentType(List<String> myFileContentType) {
		this.myFileContentType = myFileContentType;
	}

	public List<String> getMyFileFileName() {
		return myFileFileName;
	}

	public void setMyFileFileName(List<String> myFileFileName) {
		this.myFileFileName = myFileFileName;
	}

	public IFileUploadService getFileUploadService() {
		return fileUploadService;
	}

	public void setFileUploadService(IFileUploadService fileUploadService) {
		this.fileUploadService = fileUploadService;
	}

	public String[] getArg() {
		return arg;
	}

	public void setArg(String[] arg) {
		this.arg = arg;
	}

	public int getPicid() {
		return picid;
	}

	public void setPicid(int picid) {
		this.picid = picid;
	}

	public String getConcreterecordpicDir() {
		return concreterecordpicDir;
	}

	public void setConcreterecordpicDir(String concreterecordpicDir) {
		this.concreterecordpicDir = concreterecordpicDir;
	}
}
