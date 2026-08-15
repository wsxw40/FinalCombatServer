package com.pearl.o2o.service;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import com.google.gson.JsonObject;
import com.pearl.o2o.service.DataOhteServlet.DataFactory;
import com.pearl.o2o.servlet.gm.BaseGMServlet;
import com.pearl.o2o.utils.ServiceLocator;

@SuppressWarnings("unchecked")
public class UploadProcessorServlet extends BaseGMServlet {
	private static final long serialVersionUID = 1L;
	// 保存文件的目录
	private static String PATH_FOLDER = File.separator;
	private static String PATH_FOLDER_REAL = File.separator;
	// 存放临时文件的目录
	private static String TEMP_FOLDER = File.separator;
	
	
	@Override
	public void init(ServletConfig config) throws ServletException {
		ServletContext servletCtx = config.getServletContext();
		// 初始化路径
		// 保存文件的目录
		PATH_FOLDER = servletCtx.getRealPath(File.separator+"upload");
		// 存放临时文件的目录,存放xxx.tmp文件的目录
		TEMP_FOLDER = servletCtx.getRealPath(File.separator+"uploadTemp");
		PATH_FOLDER_REAL = servletCtx.getRealPath(File.separator);
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	
	public void service(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8"); // 设置编码
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=UTF-8");
		if(!"userLoginSuccess".equals(request.getSession().getAttribute("namePwd"))){
			response.getWriter().write("503");
			return;
		}
		PrintWriter writer = response.getWriter();
		// 获得磁盘文件条目工厂
		DiskFileItemFactory factory = new DiskFileItemFactory();
		factory.setRepository(new File(TEMP_FOLDER));
		// 设置 缓存的大小，当上传文件的容量超过该缓存时，直接放到 暂时存储室
		factory.setSizeThreshold(1024 * 1024);
		// 高水平的API文件上传处理
		ServletFileUpload upload = new ServletFileUpload(factory);
		try {
			long time = new Date().getTime();
			ServiceLocator.fileLog.info("\r\n-----------------action file upload-----------------");
			List<FileItem> list = upload.parseRequest(request);
			// 获取上传的文件
			FileItem item = getUploadFileItem(list);
			// 获取文件名
			String filename = getUploadFileName(item);
			String fileTop = filename.substring(0,filename.lastIndexOf("."));
			String fileBot = filename.substring(filename.lastIndexOf("."));
			String fileRename = fileTop+"_UPLOAD"+new java.util.Date().getTime()+fileBot;
			ServiceLocator.fileLog.info("\tSave folder:" + PATH_FOLDER);
			ServiceLocator.fileLog.info("\tFile name:" + fileRename);
			File file = new File(PATH_FOLDER, fileRename);
			// 写到磁盘
			item.write(file); // 第三方提供的
			ServiceLocator.fileLog.info("\tfile upload end... "+(new Date().getTime()-time)+"ms");
			ServiceLocator.fileLog.info("action file to db...\t");
			
			DataFactory data = new DataOhteServlet().new DataFactory(); 
			String objName = data.apObName(filename.substring(0,filename.lastIndexOf(".")));
			
			int flag = 0;
			Object o=null;
			while(flag<2 && o==null){
				try {
					o = Class.forName("com.pearl.o2o.pojo."+objName).newInstance();
				} catch (ClassNotFoundException e) {
					objName = objName.replaceAll("\\d", "");
					if(flag==1){
						JsonObject jo = new JsonObject();
						jo.addProperty("errorFileName","file name exists error edit here ："+filename); 
						ServiceLocator.fileLog.error(e.getMessage());
						writer.write(jo.toString());
						return;
					}
				}finally{
					flag++;
				}
			}
			
			StringBuffer errorFileName = new StringBuffer();
//			System.out.println(data.excelToObject(file, o.getClass(),PATH_FOLDER_REAL, response,errorFileName).size());
			data.excelToObject(file, o.getClass(),PATH_FOLDER_REAL, response,errorFileName);
			ServiceLocator.fileLog.info("upload error name:"+errorFileName.toString());
			JsonObject jo = new JsonObject();
			jo.addProperty("msg", "文件"+filename+"/("+(item.getSize()/1024)+"kb),用时 "+((new Date().getTime()-time)/1000)+"s");
			jo.addProperty("errorFileName",errorFileName.toString()); 
			ServiceLocator.fileLog.info("\r\n----------------"+filename+" file to db end... totalTime:"+(new Date().getTime()-time)+"ms------------------");
			
			writer.write(jo.toString());
			writer.close();
		} catch (FileUploadException e) {
//			PrintWriter writer = response.getWriter();
			JsonObject jo = new JsonObject();
			jo.addProperty("error",e.getMessage()); 
			writer.write(jo.toString());
			e.printStackTrace();
		} catch (Exception e) {
//			PrintWriter writer = response.getWriter();
			JsonObject jo = new JsonObject();
			jo.addProperty("error",e.getMessage()); 
			writer.write(jo.toString());
			e.printStackTrace();
		}

	}
	
	private FileItem getUploadFileItem(List<FileItem> list) {
		for (FileItem fileItem : list) {
			if(!fileItem.isFormField()) {
				return fileItem;
			}
		}
		return null;
	}
	
	private String getUploadFileName(FileItem item) {
		// 获取路径名
		String value = item.getName();
		// 索引到最后一个反斜杠
		int start = value.lastIndexOf("/")<=-1?value.lastIndexOf("\\"):value.lastIndexOf("/");
		// 截取 上传文件的 字符串名字，加1是 去掉反斜杠，
		String filename = value.substring(start + 1);
		
		return filename;
	}
	
}

