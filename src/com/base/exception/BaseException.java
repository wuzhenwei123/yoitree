package com.base.exception;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sys.manageException.model.ManageException;
import com.sys.manageException.service.ManageExceptionService;
@Service
public class BaseException {
	@Autowired
	private ManageExceptionService manageexceptionService = null;
	protected String exception(Exception ex) {
		ex.printStackTrace();
		ManageException manageException = getManageException(ex);
		saveException(manageException);
		return manageException.getContent();
	}
	private void saveException(ManageException manageException ){
		try {
			manageexceptionService.insertManageException(manageException);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	private ManageException getManageException(Exception ex){
		ManageException manageException = new ManageException();
		StringBuffer exceptionBuff = new StringBuffer();
		exceptionBuff.append("<h3><strong>" + ex + "</strong></h3><br>");
		StackTraceElement[] element = ex.getStackTrace();
		for (int i = 0 ;i< element.length;i++) {
			StackTraceElement stackTraceElement = element[i];
			String className = stackTraceElement.getClassName();
			String fileName = stackTraceElement.getFileName();
			int lineNumber = stackTraceElement.getLineNumber();
			String methodName = stackTraceElement.getMethodName();
			String s = "<code>" + className + "." + methodName + "(" + fileName
					+ ":" + lineNumber + ")<br></code>";
			if(i == 0){
				manageException.setBrief(s);
			}
			exceptionBuff.append(s);
		}
		manageException.setException(ex.toString());
		manageException.setCreateTime(new Date());
		manageException.setContent(exceptionBuff.toString());
		return manageException;
	}
}
