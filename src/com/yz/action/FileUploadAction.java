package com.yz.action;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.apache.struts2.ServletActionContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.opensymphony.xwork2.ActionSupport;
import com.yz.service.IFileUploadService;

/**
 * @author Administrator
 * 
 */
@Component("fileUploadAction")
@Scope("prototype")
public class FileUploadAction extends ActionSupport {

	private static final long serialVersionUID = 572146812454l;

	private List<File> myFile = new ArrayList<File>();
	private List<String> myFileContentType = new ArrayList<String>();
	private List<String> myFileFileName = new ArrayList<String>(); // 文件名

	private IFileUploadService fileUploadService;

	public String upload() throws Exception {

		String fileRoot = "test";


		String imageName = fileUploadService.upload(myFile, myFileFileName,
				myFileContentType, fileRoot);

		System.out.println(imageName);

		return SUCCESS;
	}

	/**
	 * get set
	 * 
	 * @return
	 */

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

	@Resource
	public void setFileUploadService(IFileUploadService fileUploadService) {
		this.fileUploadService = fileUploadService;
	}

}