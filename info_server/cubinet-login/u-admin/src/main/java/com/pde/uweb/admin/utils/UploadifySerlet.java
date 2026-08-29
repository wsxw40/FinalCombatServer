package com.pde.uweb.admin.utils;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import com.pde.uweb.admin.fckeditor.FckRemoteFileHandler;

public class UploadifySerlet extends HttpServlet {

	private static final long serialVersionUID = 6169138499343081366L;

	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		this.doPost(request, response);
	}

	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		
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
					response.getWriter().write(handler.getRootPath() + currentFolder + newFileName);
					break;
				}
			}
		} catch (FileUploadException e) {
			response.getWriter().write("error="+e.getMessage());
			e.printStackTrace();
		}
		response.getWriter().flush();
		response.getWriter().close();
	}
}
