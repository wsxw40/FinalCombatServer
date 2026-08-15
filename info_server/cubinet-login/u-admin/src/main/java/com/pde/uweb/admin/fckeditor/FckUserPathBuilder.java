/**
 * 
 */
package com.pde.uweb.admin.fckeditor;

import javax.servlet.http.HttpServletRequest;

import net.fckeditor.handlers.PropertiesLoader;
import net.fckeditor.requestcycle.UserPathBuilder;

/**
 * @author Huanggang
 *
 */
public class FckUserPathBuilder implements UserPathBuilder {

 
	@Override
	public String getUserFilesAbsolutePath(HttpServletRequest request) {
	
		return PropertiesLoader.getProperty("connector.remote.request");
	}

	 
	@Override
	public String getUserFilesPath(HttpServletRequest request) {
		
		return PropertiesLoader.getProperty("connector.remote.request");
	}

}
