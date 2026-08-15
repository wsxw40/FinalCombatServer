package com.pde.uweb.admin.controller.cms;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.log4j.Logger;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSONObject;
import com.pde.infor.common.web.AbstractCommonController;
import com.pde.uweb.admin.fckeditor.FckRemoteFileHandler;
import com.pde.uweb.cms.service.CmsService;

/**
 * 控制器--上传文件
 * 
 * @author Sean.Weng
 */
public class CMSContentUploadController extends AbstractCommonController {

	private static final Logger logger = Logger.getLogger(CMSContentUploadController.class);
	private CmsService cmsService;
	
	/** 跳转到信息更新页面 */
	@Override
	protected ModelAndView handleRequestInternal(HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		logger.info("come from address [ " + request.getRemoteAddr() + " ] show news content ");
		JSONObject jsonObj = new JSONObject();
		jsonObj.put("isSuccess", true);
		
		ServletFileUpload upload = new ServletFileUpload(new DiskFileItemFactory());
		upload.setHeaderEncoding("UTF-8");

		try {
			List<FileItem> items = upload.parseRequest(request);
			Iterator<FileItem> itr = items.iterator();
			while (itr.hasNext()) {
				FileItem item = (FileItem) itr.next();
				String fileName = item.getName();
				if (!item.isFormField()) {
					String fileExt = fileName.substring(fileName.lastIndexOf(".") + 1).toLowerCase();
					SimpleDateFormat df = new SimpleDateFormat("yyyyMMddHHmmss");
					String newFileName = df.format(new Date()) + "_" + new Random().nextInt(1000) + "." + fileExt;
					String currentFolder = "/thumbnails/";
					// upload file
					FckRemoteFileHandler handler = new FckRemoteFileHandler();
					handler.uploadFile(currentFolder, newFileName, item.getInputStream());
					// return file's full path
//					response.getWriter().write(handler.getRootPath() + currentFolder + newFileName);
					jsonObj.put("path", handler.getRootPath() + currentFolder + newFileName);
					break;
				}
			}
		} catch (FileUploadException e) {
			jsonObj.put("isSuccess", false);
			e.printStackTrace();
		}
		this.handlerJSCallback(request, response, jsonObj.toString());
		return null;
	}
	
	public void setCmsService(CmsService cmsService) {
		this.cmsService = cmsService;
	}
}