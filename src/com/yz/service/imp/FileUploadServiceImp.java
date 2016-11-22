package com.yz.service.imp;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

import org.apache.struts2.ServletActionContext;
import org.springframework.stereotype.Component;

import com.yz.service.IFileUploadService;
import com.yz.util.DateTimeKit;

@Component("fileUploadService")
public class FileUploadServiceImp implements IFileUploadService {

	private static final int BUFFER_SIZE = 16 * 1024;

	private List<String> realFileName = null;

	public String upload(List<File> myFile, List<String> myFileFileName,
			List<String> myFileContentType, String fileRoot) throws Exception {

		if (myFile != null) {
			realFileName = new ArrayList<String>();
			for (int i = 0; i < myFile.size(); i++) {
				realFileName.add(DateTimeKit.getDateRandom()
						+ getExtention(myFileFileName.get(i)));
				File imageFile = new File(ServletActionContext.getServletContext()
						.getRealPath("/"+fileRoot), realFileName.get(i));
				copy(myFile.get(i), imageFile); // 把图片写入到上面设置的路径里

			}
		}

		return listToString(fileRoot,realFileName);

	}

	private String getExtention(String fileName) {
		int pos = fileName.lastIndexOf(".");
		return fileName.substring(pos);
	}

	public String listToString(String fileRoot,List<String> stringList) {
		if (stringList == null||stringList.size()<1) {
			return "";
		}
		StringBuilder result = new StringBuilder();
		boolean flag = false;
		for (String string : stringList) {
			if (flag) {
				result.append(",");
			} else {
				flag = true;
			}
			result.append(fileRoot+"/"+string);
		}
		return result.toString();
	}

	private void copy(File src, File dst) {
		try {
			InputStream in = null;
			OutputStream out = null;
			try {
				in = new BufferedInputStream(new FileInputStream(src),
						BUFFER_SIZE);
				out = new BufferedOutputStream(new FileOutputStream(dst),
						BUFFER_SIZE);
				byte[] buffer = new byte[BUFFER_SIZE];
				while (in.read(buffer) > 0) {
					out.write(buffer);
				}
			} finally {
				if (null != in) {
					in.close();
				}
				if (null != out) {
					out.close();
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public List<String> getRealFileName() {
		return realFileName;
	}

	public void setRealFileName(List<String> realFileName) {
		this.realFileName = realFileName;
	}

}
