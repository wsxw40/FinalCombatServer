package com.pde.uweb.admin.fckeditor;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.Enumeration;
import java.util.Properties;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.fckeditor.connector.ConnectorServlet;
import net.fckeditor.handlers.PropertiesLoader;
import net.fckeditor.requestcycle.ThreadLocalData;
import net.fckeditor.response.GetResponse;
import net.fckeditor.response.UploadResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * fck servlet处理
 * 
 * @author Huanggang
 * 
 */
public class FckConnectorServlet extends HttpServlet {
	private static final long serialVersionUID = -5742008970929377161L;
	private final Logger logger = LoggerFactory.getLogger(ConnectorServlet.class);
	private transient FckDispatcher fckDispatcher;

	/**
	 * Initializes this servlet. It initializes the dispatcher internally.
	 * 
	 * @throws ServletException
	 *             if an exception occurs that interrupts the servlet's normal
	 *             operation
	 */
	@Override
	public void init() throws ServletException {
		try {
			// 加载外部全局配置参数
			InputStream inputStream = new FileInputStream(new File("/opt/config/UWEBGlobal.properties"));
			Properties properties = new Properties();
			properties.load(inputStream);
			Enumeration<Object> enumeration = properties.keys();
			while (enumeration.hasMoreElements()) {
				String key = (String) enumeration.nextElement();
				PropertiesLoader.setProperty(key, properties.getProperty(key));
			}
			this.fckDispatcher = new FckDispatcher(this.getServletContext());
		} catch (Exception e) {
			logger.error("Dispatcher could not be initialized", e);
			throw new ServletException(e);
		}
	}

	@Override
	protected void doGet(final HttpServletRequest request, final HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		response.setContentType("application/xml");
		response.setHeader("Cache-Control", "no-cache");
		PrintWriter out = response.getWriter();
		GetResponse getResponse = null;

		try {
			ThreadLocalData.beginRequest(request);
			getResponse = this.fckDispatcher.doGet(request);
		} catch (Exception e) {
			throw new ServletException(e);
		} finally {
			ThreadLocalData.endRequest();
		}

		out.print(getResponse);
		out.flush();
		out.close();
	}

	@Override
	protected void doPost(final HttpServletRequest request, final HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html");
		response.setHeader("Cache-Control", "no-cache");
		PrintWriter out = response.getWriter();
		UploadResponse uploadResponse = null;

		try {
			ThreadLocalData.beginRequest(request);
			uploadResponse = this.fckDispatcher.doPost(request);
		} catch (Exception e) {
			throw new ServletException(e);
		} finally {

			ThreadLocalData.endRequest();
		}

		out.print(uploadResponse);
		out.flush();
		out.close();
	}

}
