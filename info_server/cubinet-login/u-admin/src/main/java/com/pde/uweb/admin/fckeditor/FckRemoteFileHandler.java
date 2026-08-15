package com.pde.uweb.admin.fckeditor;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Vector;

import net.fckeditor.connector.Connector;
import net.fckeditor.handlers.PropertiesLoader;
import net.fckeditor.handlers.ResourceType;

import org.apache.log4j.Logger;

import ch.ethz.ssh2.Connection;
import ch.ethz.ssh2.SCPClient;
import ch.ethz.ssh2.SFTPv3Client;
import ch.ethz.ssh2.SFTPv3DirectoryEntry;

/**
 * 与linux通信的远程文件操作
 * 
 * @author Huanggang
 * 
 */
public class FckRemoteFileHandler {

	private static final Logger logger = Logger.getLogger(FckRemoteFileHandler.class);

	/**
	 * 对性能要求不高，操作并不频繁，使用后立即关闭
	 */
	private Connection connection;

	/**
	 * linux隐藏文件
	 */
	private static final String doublehidenFile = "..";

	/**
	 * linux隐藏文件
	 */
	private static final String dotHidenFile = ".";

	/**
	 * 创建linux 文件夹
	 * 
	 * @param type
	 * @param currentFolder
	 * @param newFolder
	 */
	public void createCommonFolder(ResourceType type, String currentFolder, String newFolder) {

		this.checkSSHConnection();
		String baseDirName = PropertiesLoader.getProperty("connector.remote.default.folder");
		try {
			SFTPv3Client sftPv3Client = new SFTPv3Client(this.connection);
			String dirName = baseDirName + currentFolder + newFolder;
			sftPv3Client.mkdir(dirName, 775);
			
		} catch (IOException e) {
			logger.error("create folder error ", e);
			throw new RuntimeException(e);
		} finally {
			this.connection.close();
		}

	}

	/**
	 * 上传文件,返回文件名称
	 * 
	 * @param type
	 * @param currentFolder
	 * @param fileName
	 * @param inputStream
	 * @return
	 */
	public String uploadFile(ResourceType type, String currentFolder, String fileName, InputStream inputStream) {
		return this.uploadFile(currentFolder, fileName, inputStream);
	}
	
	/**
	 * 上传文件,返回文件名称
	 *
	 * @param currentFolder
	 * @param fileName
	 * @param inputStream
	 * @return
	 */
	public String uploadFile(String currentFolder, String fileName, InputStream inputStream) {
		this.checkSSHConnection();
		SCPClient scpClient = new SCPClient(this.connection);
		try {
			byte[] bs = new byte[inputStream.available()];
			inputStream.read(bs);
			String dirName = PropertiesLoader.getProperty("connector.remote.default.folder");
			scpClient.put(bs, fileName, dirName + currentFolder, "0775");
		} catch (IOException e) {
			logger.error("upload file occur error", e);
			throw new RuntimeException(e);
		} finally {
			this.connection.close();
		}
		return fileName;
	}

	/** 获得图片服务器的ip地址 */
	public String getRootPath() {
		return PropertiesLoader.getProperty("connector.remote.request");
	}

	/**
	 * 获取当前目录下所有文件
	 * 
	 * @param currentFolder
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<Map<String, Object>> getCommonFiles(String currentFolder) {
		this.checkSSHConnection();
		try {
			SFTPv3Client sftPv3Client = new SFTPv3Client(this.connection);
			String dirName = PropertiesLoader.getProperty("connector.remote.default.folder");
			Vector<SFTPv3DirectoryEntry> folders = sftPv3Client.ls(dirName + currentFolder);
			Iterator<SFTPv3DirectoryEntry> iterator = folders.iterator();
			List<Map<String, Object>> lists = new ArrayList<>();
			Map<String, Object> fileMap = null;
			while (iterator.hasNext()) {
				SFTPv3DirectoryEntry eDirectoryEntry = iterator.next();
				if (eDirectoryEntry.attributes.isRegularFile()) {
					fileMap = new HashMap<>(Integer.valueOf(2));
					fileMap.put(Connector.KEY_NAME, eDirectoryEntry.filename);
					fileMap.put(Connector.KEY_SIZE, eDirectoryEntry.attributes.size);
					lists.add(fileMap);
				}
			}
			return lists;
		} catch (IOException e) {
			logger.error("can't open sftp pv3 client,please check", e);
			throw new RuntimeException(e);
		} finally {
			this.connection.close();
		}

	}

	/**
	 * 获取指定目录下的目录列表
	 * 
	 * @param parentFolder
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<String> getFolders(String folder) {
		this.checkSSHConnection();
		try {
			SFTPv3Client sftPv3Client = new SFTPv3Client(this.connection);
			String dirName = PropertiesLoader.getProperty("connector.remote.default.folder");
			Vector<SFTPv3DirectoryEntry> folders = sftPv3Client.ls(dirName + folder);
			if (folders.isEmpty()) {
				return new ArrayList<String>(0);
			}
			Iterator<SFTPv3DirectoryEntry> iterator = folders.iterator();
			List<String> lists = new ArrayList<String>();
			while (iterator.hasNext()) {
				SFTPv3DirectoryEntry eDirectoryEntry = iterator.next();

				if (eDirectoryEntry.attributes.isDirectory()) {
					// 过滤隐藏文件
					if (eDirectoryEntry.filename.startsWith(dotHidenFile) || eDirectoryEntry.filename.startsWith(doublehidenFile)) {
						continue;
					} else {
						lists.add(eDirectoryEntry.filename);
					}
				}
			}
			return lists;

		} catch (IOException e) {
			logger.error("can't open sftp pv3 client,please check", e);
			throw new RuntimeException(e);
		} finally {
			this.connection.close();
		}
	}

	private void checkSSHConnection() {

		if (this.connection == null) {
			String remoteIp = PropertiesLoader.getProperty("connector.remote.ip");
			this.connection = new Connection(remoteIp);
			//this.connection = new Connection("222.73.114.74");
		}
		if (this.connection.isAuthenticationComplete()) {
			return;
		}

		String scpUser = PropertiesLoader.getProperty("connector.remote.user");
		String scpPwd = PropertiesLoader.getProperty("connector.remote.pwd");
//		String scpUser = "huanggang";
//		String scpPwd = "qwewsx";
		
		try {
			this.connection.connect();
			if (!this.connection.authenticateWithPassword(scpUser, scpPwd)) {
				throw new RuntimeException("can't connection remote server with scp");
			}
		} catch (IOException e) {
			e.printStackTrace();
			logger.error("connection remote server with scp error", e);
			throw new RuntimeException(e);
		}

	}

}
