/**
 * 
 */
package com.pde.uweb.admin.fckeditor;

import java.io.InputStream;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletContext;

import net.fckeditor.connector.Connector;
import net.fckeditor.connector.exception.FolderAlreadyExistsException;
import net.fckeditor.connector.exception.InvalidCurrentFolderException;
import net.fckeditor.connector.exception.InvalidNewFolderNameException;
import net.fckeditor.connector.exception.WriteException;
import net.fckeditor.handlers.ResourceType;

/**
 * @author Huanggang
 * 
 */
public class FckConnector implements Connector {

	/**
	 * 远程文件操作
	 */
	private FckRemoteFileHandler fckRemoteFileHandler;

	public FckConnector() {
		this.fckRemoteFileHandler = new FckRemoteFileHandler();
	}

	@Override
	public String fileUpload(ResourceType type, String currentFolder, String fileName, InputStream inputStream)
			throws InvalidCurrentFolderException, WriteException {
		return this.fckRemoteFileHandler.uploadFile(type, currentFolder, fileName, inputStream);
	}

	@Override
	public List<Map<String, Object>> getFiles(ResourceType type, String currentFolder) throws InvalidCurrentFolderException {
		return this.fckRemoteFileHandler.getCommonFiles(currentFolder);

	}

	@Override
	public List<String> getFolders(ResourceType type, String currentFolder) throws InvalidCurrentFolderException {
		return this.fckRemoteFileHandler.getFolders(currentFolder);
	}

	@Override
	public void init(ServletContext servletContext) throws Exception {

	}

	@Override
	public void createFolder(ResourceType type, String currentFolder, String newFolder) throws InvalidCurrentFolderException,
			InvalidNewFolderNameException, FolderAlreadyExistsException, WriteException {
		this.fckRemoteFileHandler.createCommonFolder(type, currentFolder, newFolder);
	}
	
}
