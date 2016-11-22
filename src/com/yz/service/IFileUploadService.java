package com.yz.service;

import java.io.File;
import java.util.List;

public interface IFileUploadService {
	
	public String upload(List<File> myFile, List<String> myFileFileName,
			List<String> myFileContentType, String fileRoot) throws Exception;

}
