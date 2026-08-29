package com.pearl.o2o.service;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.Reader;
import java.io.Writer;
import java.lang.reflect.Field;
import java.sql.BatchUpdateException;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.ConvertUtils;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFRichTextString;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Row;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.ibatis.common.jdbc.exception.NestedSQLException;
import com.ibatis.sqlmap.client.SqlMapClient;
import com.pearl.o2o.dao.impl.nonjoin.SysMissionDao;
import com.pearl.o2o.pojo.BaseMappingBean;
import com.pearl.o2o.pojo.SysItem;
import com.pearl.o2o.servlet.gm.BaseGMServlet;
import com.pearl.o2o.utils.DaoCacheUtil;
import com.pearl.o2o.utils.ServiceLocator;
import com.pearl.o2o.utils.StringUtil;

@SuppressWarnings("unchecked")
public class DataOhteServlet extends BaseGMServlet {
	static final long serialVersionUID=1L;
	// 保存文件的目录
	private static String PATH_FOLDER = File.separator;
	
	
	private static Map<String,Field> map = new HashMap<String, Field>();
	@Override
	public void init(ServletConfig config) throws ServletException {
		ServletContext servletCtx = config.getServletContext();
		PATH_FOLDER = servletCtx.getRealPath(File.separator+"upload");
		ServiceLocator.fileLog.info("Init Path:"+PATH_FOLDER);
		if(!new File(PATH_FOLDER).exists()){
			new File(PATH_FOLDER).mkdir();
			new File(servletCtx.getRealPath(File.separator+"uploadTemp")).mkdir();
		}
	}
	
	@Override
	public void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		String tableName = request.getParameter("tableName");
		String downFile = request.getParameter("downFile");
		String showExl = request.getParameter("showExl");
		String delExlFile = request.getParameter("delExlFile");
		String lessUploadOrDownFileList = request.getParameter("lessUploadOrDownFileList");
//		String isComp = request.getParameter("isComp");
//		DataFactory j = (new DataOhte()).new DataFactory();
		
		if(!"userLoginSuccess".equals(request.getSession().getAttribute("namePwd"))){
			response.getWriter().write("503");
			return;
		}
		
		if(!StringUtil.isEmptyString(delExlFile)){			//Delete
			Writer out = response.getWriter();
			out.write(DataOhteServlet.delExlFile(delExlFile));
			return;
		}else if(!StringUtil.isEmptyString(lessUploadOrDownFileList)){
			Writer out = response.getWriter();
			out.write(DataOhteServlet.lessUploadOrDownFileList());
			return;
		}else if(!StringUtil.isEmptyString(downFile))		//Down
		{
			try {
				String tName = new DataFactory<Object>().dataToExcel(tableName);
				downFile(request,response,tName!=null?tName:tableName,PATH_FOLDER);
			} catch (Exception e) {
				e.printStackTrace();
			} 
			return;
		}else if(!StringUtil.isEmptyString(showExl)){		//Show singe table
		}else{												//Show all tableName
			JsonObject jo = new JsonObject();
			LinkedHashMap<String, String> tables = getDBTables();
			for(String key : tables.keySet()){
				if(key.matches("^SYS.*")){
					jo.addProperty(key, tables.get(key));
				}
			}
			Writer out = response.getWriter();
			out.write(jo.toString());
		}
		
	} 
	/***************************************************************************
	 * down file is excel format
	 */
	private void downFile(HttpServletRequest request, HttpServletResponse response,String fileName,String pathFolder)throws Exception{
		ServiceLocator.fileLog.info("file path:"+PATH_FOLDER);
		File file = new File(pathFolder+File.separator+fileName);
		FileInputStream fis = new FileInputStream(file);
		byte[] b = new byte[fis.available()];
//		fis.read(b, 0, fis.available());
		response.addHeader("Content-Type","application/ms-excel");//octet-stream
//		response.addHeader("Content-Disposition", "attachment;filename="+fileName);//attachment;inline
		response.addHeader("Content-Disposition", "inline;filename="+fileName); 
		response.setContentLength(fis.available()); 
		BufferedOutputStream bos = new BufferedOutputStream(response.getOutputStream());
		BufferedInputStream bis = new BufferedInputStream(fis);
		long k = 0 ;
		try {
			while(k<file.length()){
				int i = bis.read(b,0,fis.available());
				k+=i;
				bos.write(b,0,i);
			}
			bos.flush();
		}catch (Exception e) {
			ServiceLocator.fileLog.info("用户取消了下载操作~");
		}finally{  
			bis.close();
			bos.close();
			fis.close(); 
		}
		
	}
	/**
	 * 数据更新操作
	 * 
	 * **/
	class DataFactory<T>{
		public String conver(List<T> list)throws Exception{
			JsonArray ja = new JsonArray();
			for(T clazz : list){
				JsonObject jo = new JsonObject();
				for(Field f : clazz.getClass().getDeclaredFields()){
					f.setAccessible(true);
					if(f.getName().matches("serialVersionUID")
							|| f.getType().getName().matches(".*List.*")
							|| f.getType().getName().matches(".*Set.*")
							|| f.getType().getName().matches(".*Map.*")){
						jo.addProperty("ID", String.valueOf(clazz.getClass().getMethod("getId").invoke(clazz)));
					}else if(f.get(clazz)!=null&&!f.get(clazz).toString().matches("com.pearl")){
						jo.addProperty(f.getName(), new String(String.valueOf(f.get(clazz))));
					}
				}
				ja.add(jo);	
			}
			return ja.toString();
		}
		
		/**
		 * */
		public Field findSuperId(String name,Class<?> clazz){
			try {
				Field[] fis = clazz.getDeclaredFields();
				for(Field f : fis){
					if(name.equals(f.getName()))
					{
						return f;
					}
				}
				return  clazz.getDeclaredField(name);
			} catch (SecurityException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (NoSuchFieldException e) {
				return findSuperId(name,clazz.getSuperclass());
			} catch (NullPointerException e) {
				// TODO: handle exception
			}
			return null;
		}
		public Field findName(String pathFolderReal,Class<T> clazz,String cName){
			String key = cName+"_"+clazz.getSimpleName();
			if(map.containsKey(key)){return map.get(key);}
			Field result = null;
			String columnName=findXmlObjName(pathFolderReal,clazz.getSimpleName(),cName);
			if(!StringUtil.isEmptyString(columnName))
				result = findSuperId(columnName, clazz);
			map.put(key, result);
			return result;
		}
		/**
		 * excel conver obj
		 * */
		//List<BaseMappingBean<T>>
		@SuppressWarnings("static-access")
		public void excelToObject(File exlFile,Class<T> clazz,String pathFolderReal,HttpServletResponse response,StringBuffer errorFileName) throws Exception{
			long time = new Date().getTime();
			ServiceLocator.fileLog.info("action exlcel file to reader...  ");
			LinkedHashMap<Integer, StringBuffer> cellRowNum = new LinkedHashMap<Integer, StringBuffer>();
//			List<BaseMappingBean<T>> list = new ArrayList<BaseMappingBean<T>>();
//			List<T> list=ServiceLocator.baseMappingDao.getSqlMapClientTemplate().queryForList(clazz.getSimpleName()+".select");
			POIFSFileSystem fs = new POIFSFileSystem(new FileInputStream(exlFile));
			@SuppressWarnings("resource")
			HSSFWorkbook wb = new HSSFWorkbook(fs);
			HSSFSheet sheet = wb.getSheetAt(0);
			Iterator<org.apache.poi.ss.usermodel.Row> rows=sheet.rowIterator();
			Row row = null;
			
			String tableName = exlFile.getName().substring(0,exlFile.getName().lastIndexOf("_UPLOAD"));
			List<String> ids = getDBContents(tableName,"ID");;
			
			SqlMapClient sc = ServiceLocator.baseMappingDao.getSqlMapClient();	//insert batch
			sc.startTransaction();
			sc.startBatch();
			 
			int rowIndex = 1;//0
			while(rows.hasNext())
			{
				if(row==null){row = rows.next();}
				Row row2 = rows.next();
				Iterator<org.apache.poi.ss.usermodel.Cell> cells=row.cellIterator();
				BaseMappingBean<T> claz = (BaseMappingBean<T>)clazz.newInstance();
				Cell cell = null;	//top
				Cell cell2 = null;	//cont
				Field ff = null;
				int cellIndex = -1;//0
					while(cells.hasNext())
					{
						try {
							cell = cells.next(); 
							cell2 = row2.getCell(++cellIndex); 
							ff=findName(pathFolderReal,clazz,cell.toString());
							if(ff==null)continue;
							ff.setAccessible(true); 
							if(cell2==null || "null".equalsIgnoreCase(cell2.toString())){	
								if(ff.getType().getSimpleName().equalsIgnoreCase("Integer")||ff.getType().getSimpleName().equalsIgnoreCase("int"))
								{
									ff.set(claz, 0);
								}else if(ff.getType().getSimpleName().equalsIgnoreCase("Float")){
									ff.set(claz, 0F);
								}
								else{
									ff.set(claz, null);
								}
								continue;
							}
							
							String value = null; 
							if(cell2.getCellType()==HSSFCell.CELL_TYPE_NUMERIC){
								if(ff.getType().getSimpleName().equalsIgnoreCase("integer")){
									value=new java.text.DecimalFormat().format(Math.floor(cell2.getNumericCellValue())).replace(",", "");
								}else if(ff.getType().getSimpleName().equals("Date")){
									ff.set(claz, cell2.getDateCellValue());
								}else{
									value=new java.text.DecimalFormat().format(cell2.getNumericCellValue()).replace(",", "");
								}
							}else if(cell2.getCellType()==HSSFCell.CELL_TYPE_BOOLEAN){
								value = Boolean.valueOf(cell2.toString())?"1":"0";
							}else if(cell2.getCellType()==HSSFCell.CELL_TYPE_STRING){ 
								if(ff.getType().getSimpleName().equals("Date")){ 
									ff.set(claz, new java.text.SimpleDateFormat("yyyy-MM-dd hh:mm:ss").parse(cell2.toString()));	continue;
								}else if(ff.getType().getSimpleName().equals("Integer") && cell2.toString().lastIndexOf(":")>-1){
										ff.set(claz, Integer.parseInt(String.valueOf(new java.text.SimpleDateFormat("yyyy-MM-dd hh:mm:ss").parse(cell2.toString()).getTime()/1000)));	continue;
								}else if(ff.getType().getSimpleName().equalsIgnoreCase("integer")){
									value=cell2.getStringCellValue().trim();
									if(!value.matches("^-?\\d*$") && value.length()>0){
										if(!cellRowNum.containsKey(rowIndex)){
											cellRowNum.put(rowIndex,new StringBuffer(cellIndex+""));
										}else{
											StringBuffer columnIndexs = cellRowNum.get(rowIndex);
											columnIndexs.append(";");
											columnIndexs.append(cellIndex);
											cellRowNum.put(rowIndex, columnIndexs);
										} 
										continue;
									}
								}else{
									value=cell2.getStringCellValue(); 
								}
							}
							
							ff.set(claz, ConvertUtils.convert(value, ff.getType()));
							
						} catch (Exception e) {   
							if(!cellRowNum.containsKey(rowIndex)){
								cellRowNum.put(rowIndex,new StringBuffer(cellIndex+""));
							}else{
								StringBuffer columnIndexs = cellRowNum.get(rowIndex);
								columnIndexs.append(";");
								columnIndexs.append(cellIndex);
								cellRowNum.put(rowIndex, columnIndexs);
							}
							ServiceLocator.fileLog.info("\r\n");
							ServiceLocator.fileLog.info("Content:"+cell!=null?cell.getStringCellValue():""+" : "+String.valueOf(cell2));
							ServiceLocator.fileLog.info("row:"+rowIndex+":"+"column:"+cellIndex);
							ServiceLocator.fileLog.info("\r\n");
							ServiceLocator.fileLog.error(e.getMessage());
							//e.printStackTrace();
						}
					}
					try {
						// update or insert 
						if(claz.getId()>0 && ids.contains(String.valueOf(claz.getId()))){
							sc.update(claz.getClass().getSimpleName()+".update",claz);
							ids.remove(String.valueOf(claz.getId()));
						}else{
							claz.setId((Integer)sc.insert(claz.getClass().getSimpleName()+".insert",claz));
						}
					} catch (NestedSQLException e) {
						// TODO Auto-generated catch block 
//						e.printStackTrace();
						String errorStack = e.getCause().getMessage();
						if(errorStack.matches(".*FOREIGN.KEY.*REFERENCES.*")){
							String errorReferenceName = errorStack.substring(errorStack.indexOf("FOREIGN KEY")+11,errorStack.indexOf("REFERENCES")).replaceAll("\\(|\\)|\\`|", "").trim();
							 
							//由于抛出的错误，列不明确，故需要判定
							Iterator<org.apache.poi.ss.usermodel.Cell> allCells=row.cellIterator();
							boolean flag = false;
							for(;allCells.hasNext();){
								if(allCells.next().toString().equals(errorReferenceName)){
									if(!cellRowNum.containsKey(rowIndex)){
										cellRowNum.put(rowIndex,new StringBuffer(cellIndex+""));
									}else{
										StringBuffer columnIndexs = cellRowNum.get(rowIndex);
										columnIndexs.append(";");
										columnIndexs.append(cellIndex);
										cellRowNum.put(rowIndex, columnIndexs);
									}
									flag=true;
								} 
							}
							
							if(flag){ 
								ServiceLocator.fileLog.info(errorStack);
								continue;
							}else{
								sc.endTransaction();
								ServiceLocator.fileLog.error(e.getMessage());
//								e.printStackTrace();
							}
						}
					}
					
					rowIndex++;
			}
			
			ServiceLocator.fileLog.info("excel file reader end... "+(new Date().getTime()-time)+"ms");
			
			if(cellRowNum.size()>0){
				sc.endTransaction(); 
				sc.flushDataCache();
				uploadErrorWrite(cellRowNum, exlFile,response,errorFileName);
			}else{
				long time3 = new Date().getTime();
				
				if(ids.size()>0)		//delting
				{
					for(String id : ids){
						BaseMappingBean<T> b=(BaseMappingBean<T>)clazz.newInstance();
						b.setId(Integer.parseInt(id));
						sc.delete(clazz.getSimpleName()+".delete",b);
					}
				}
				
				int count=0;
				ServiceLocator.fileLog.info("data to db...  ");
				try {
					ServiceLocator.fileLog.info("success save db count:"+(count=sc.executeBatch()));
				} catch (BatchUpdateException e) {
					// TODO Auto-generated catch block
//					e.printStackTrace();
					ServiceLocator.fileLog.error(e.getMessage());
				}finally{
					if(count>0)
						sc.commitTransaction();
					sc.endTransaction();
					sc.flushDataCache();
				}
				
				ServiceLocator.fileLog.info("data to db end..."+(new Date().getTime()-time3)+"ms");
				
				ServiceLocator.fileLog.info("sysitem reset .."); 
				//sysitem reset 
				Map<Integer, SysItem> allSysitem=new HashMap<Integer, SysItem>();
				ServiceLocator.getService.getSysItemDao().setAllSysitem(allSysitem);
				SysMissionDao.joinMissionGift();//重置任务奖品
				ServiceLocator.mcc.flushAll();
				ServiceLocator.fileLog.info("sysitem reset over ..");
			}
		}
		
		/**
		 * database content conver excel
		 * @return fileName
		 * */
		public String dataToExcel(String tableName) throws Exception{
			if(tableName.matches(".*\\.xls$"))
			{return null;}
			long time = new Date().getTime();
			ServiceLocator.fileLog.info("action exlcel download...");
			LinkedHashMap<String,String> columnNameAndType = getDBColumnsAndType(tableName);
			
			if(columnNameAndType.size()<=0)
				return null;
			
			File file = new File(PATH_FOLDER+File.separator+tableName+"_DOWN"+new java.util.Date().getTime()+".xls");
			if(file.exists())if(file.delete())file.createNewFile();else;else file.createNewFile();
			OutputStream os = new FileOutputStream(file);
			@SuppressWarnings("resource")
			HSSFWorkbook wb = new HSSFWorkbook();
			
			// style list 
			CellStyle longStyle = wb.createCellStyle();
			longStyle.setDataFormat(wb.createDataFormat().getFormat("0.00"));
			
			
			HSSFSheet sheet = wb.createSheet(tableName);
			
			List<?> columnContent = getDBContents(tableName,"*");
			
//			Map<String, Field> fields =new LinkedHashMap<String, Field>();
			
			int j = 0;									//Content column index.
			int k = 1;									//Content row index.
			int i = 0;
			
			HSSFRow rowTop=sheet.createRow(0);		//Top 
			HSSFCell cellTop=null;
			for(String columnName : columnNameAndType.keySet()){
				cellTop=rowTop.createCell(i++);
				cellTop.setCellValue(new HSSFRichTextString(String.valueOf(columnName)));
			}
			 
			HSSFRow rowCon=null;						//Content
			for(Object cell : columnContent){
				if(j==0 || j == columnNameAndType.size()){
					rowCon = sheet.createRow(k++); 
					j=0;
				}
				String cellTypeAndLen = columnNameAndType.get(rowTop.getCell(j).toString());
				String cellType = cellTypeAndLen.substring(0,cellTypeAndLen.indexOf("."));
				String cellLen = cellTypeAndLen.substring(cellTypeAndLen.indexOf(".")+1);
				int type = 0;
				
				
				try {
					HSSFCell cellCon = rowCon.createCell(j);
					if(cell == null){
						type = HSSFCell.CELL_TYPE_BLANK; 
						cellCon.setCellType(type);
						cellCon.setCellValue("");
					}else if (cellType.toUpperCase().indexOf("TINYINT")>-1 && Integer.parseInt(cellLen)==1) {
						type = HSSFCell.CELL_TYPE_BOOLEAN;
						cellCon.setCellType(type);
						cellCon.setCellValue(Boolean.valueOf(cell.toString()));
					}else if (cellType.toUpperCase().indexOf("INT")>-1) {
						type = HSSFCell.CELL_TYPE_NUMERIC;
						cellCon.setCellType(type);
						cellCon.setCellValue(Double.valueOf(cell.toString().toUpperCase().equals("NULL")?"0":cell.toString()));
					}else if (cellType.toUpperCase().equals("LONG") || cellType.toUpperCase().equals("DOUBLE") || cellType.toUpperCase().equals("FLOAT")) {
						type = HSSFCell.CELL_TYPE_NUMERIC; 
						cellCon.setCellStyle(longStyle);
						cellCon.setCellType(type); 
						cellCon.setCellValue(Double.valueOf(cell.toString()));
					}else if (cellType.toUpperCase().equals("BOOL") || cellType.toUpperCase().equals("BOOLEAN")) {
						type = HSSFCell.CELL_TYPE_BOOLEAN;
						cellCon.setCellType(type);
						cellCon.setCellValue(Boolean.valueOf(cell.toString()));
					}else{
						type = HSSFCell.CELL_TYPE_STRING;
						cellCon.setCellType(type);
						cellCon.setCellValue(String.valueOf(cell));
					}
					 
				} catch (NumberFormatException e) {
					// TODO Auto-generated catch block
					ServiceLocator.fileLog.info(cellType);
					ServiceLocator.fileLog.info(cellLen);
					ServiceLocator.fileLog.info(rowTop.getCell(j).getStringCellValue());
					ServiceLocator.fileLog.info(cell.toString());
					e.printStackTrace();
				}
				j++;
			}
			
			wb.write(os);
			os.flush();
			os.close();
			ServiceLocator.fileLog.info("excel download save end"+(new Date().getTime()-time)+"ms");
			return file.getName();
		}
		/**
		 * 	error excel content or format for message 
		 * */
		private void uploadErrorWrite(Map<Integer,StringBuffer> cellRowNum,File exlFile,HttpServletResponse response,StringBuffer errorFileName) throws Exception{
			long time2 = new Date().getTime();
			ServiceLocator.fileLog.info("dispost data constans error...");
			File file = new File(exlFile.getPath().substring(0,exlFile.getPath().lastIndexOf("."))+"-Error.xls");
			if(file.exists())if(file.delete())file.createNewFile();else;else file.createNewFile();
			OutputStream os = new FileOutputStream(file);
			POIFSFileSystem fs = new POIFSFileSystem(new FileInputStream(exlFile));
			@SuppressWarnings("resource")
			HSSFWorkbook wb = new HSSFWorkbook(fs);
			HSSFSheet sheet = wb.getSheetAt(0);
			for(Map.Entry<Integer,StringBuffer> cellRowIndex : cellRowNum.entrySet()){
				CellStyle cellStyle = wb.createCellStyle();
				cellStyle.setWrapText(true);	
				cellStyle.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
				cellStyle.setFillForegroundColor(HSSFColor.RED.index);
				String[] columns = new String[1];
				if(cellRowIndex.toString().indexOf(";")>-1)
				{
					columns = cellRowIndex.getValue().toString().split(";");
				}else{
					columns[0] = cellRowIndex.getValue().toString();
				}
				for(String column : columns){ 
					HSSFCell cell = sheet.getRow(cellRowIndex.getKey()).getCell(Integer.parseInt(column));
					cell.setCellStyle(cellStyle);
//					cell.setCellValue(cell+"[error format!]");
					cell.setCellValue(cell+"");
				}
				
			}  
			wb.write(os); 
			os.flush();
			os.close();
			ServiceLocator.fileLog.info("dispost data constans error end..."+(new Date().getTime()-time2)+"ms");
			errorFileName.append(file.getName());
//			downFile(null, response, file.getName(), file.getPath().substring(0,file.getPath().lastIndexOf(File.separator)+1));
		}
		
		public String apTbName(String name) {
			String[] tbName = { name, name.toUpperCase(), "" };
			for (int i = 0; i < tbName[0].length(); i++) {
				if (tbName[0].substring(i, i + 1).equals(
						tbName[1].substring(i, i + 1))) {
					tbName[2] += "_" + tbName[1].substring(i, i + 1);
				} else {
					tbName[2] += tbName[1].substring(i, i + 1);
				}
			}
			if (tbName[2].indexOf("_") == 0)
				tbName[2] = tbName[2].substring(1);
			return tbName[2];
		}
		
		public String apObName(String name) {
			String result = "";
			
			if(name.indexOf("_")>-1)
			{
				result = name.intern().toLowerCase();
				for(int i = 0 ; i < result.length();i++){
					if(result.substring(i , i + 1).equals("_")){
						result = result.substring(0,i) + String.valueOf(result.charAt(i+1)).toUpperCase() + result.substring(i+2); 
					}
				}
			}else{
				result = name.toLowerCase();
			}
			
			result=result.substring(0,1).toUpperCase() + result.substring(1);
			if(result.equalsIgnoreCase("SysOnlineAward")){
				result = "OnlineAward";
			}else if(result.equalsIgnoreCase("SysLevel")){
				result = "LevelInfo";
			}
			
			return result;
		}
		
		/**
		 * for xml column name convert object name , not find return null~
		 * 
		 * @return ap class name to object name 
		 * 
		 * @param xml path
		 * @param class name
		 * @param class for field name
		 * */
		public String findXmlObjName(String pathFolderReal,String clazzName,String columnName){
//			if(map.containsKey(columnName+"_"+clazzName)){return map.get(columnName+"_"+clazzName);}
			String pathTop = pathFolderReal + "WEB-INF/classes/";
			InputStream is = null;
			Reader re = null;
			BufferedReader br = null;
			
			//find ibatis xml path~~
			String line=null;
			try {
				is = new FileInputStream(new File(pathTop+"SqlMapConfig.xml"));
				re = new InputStreamReader(is);
				br = new BufferedReader(re);
				while((line=br.readLine())!=null)
				{
					if(line.matches("^.*sqlMap.*resource.*") && line.matches(".*\\W"+clazzName+"\\W.*")){
						line=line.replaceAll("^.*= *\\\"", "").replaceAll(" *\\\".*$", "");
						break;
					}
					line = null;
				}
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} finally{
				try {
					if(is!=null){
						br.close();
						re.close();
						is.close();
					}
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			
			if(line==null)return null;
			
			//find obj name~~
			String line2=null;
			try {
				is = new FileInputStream(new File(pathTop+line));
				re = new InputStreamReader(is);
				br = new BufferedReader(re);
				line2 = null;
				while((line2=br.readLine())!=null)
				{
					if(line2.matches("^.*result.*property.*") && line2.matches(".*\\W"+columnName+"\\W.*")){
						line2=line2.replaceAll("column.*", "").replaceAll("^.*=\\\"", "").replaceAll("\\\".*$", "");
						break;
					}
					line2 = null;
				}
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}finally{
				try {
					if(is!=null){
						br.close();
						re.close();
						is.close();
					}
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			
//			map.put(columnName+"_"+clazzName, line2);
			return line2;
		}
	}
	
	/**
	 * 	Get db for columns and type 
	 * */
	public LinkedHashMap<String, String> getDBColumnsAndType(String tableName){
		LinkedHashMap<String, String> result = new LinkedHashMap<String, String>();
		java.sql.Connection conn = null;
		ResultSet rs = null;
		ResultSetMetaData rsm = null;
		try {
			conn = ServiceLocator.baseMappingDao.getDataSource().getConnection();
			rs = conn.prepareStatement("SELECT * FROM "+tableName+" LIMIT 0").executeQuery();
			rsm = rs.getMetaData();
			for(int i = 1 ; i <= rsm.getColumnCount() ; i++){
				result.put(rsm.getColumnName(i), rsm.getColumnTypeName(i)+"."+rsm.getPrecision(i));
			}
		} catch (Exception e1) { 
			if(!e1.getMessage().matches(".*Table.*doesn't.*exist.*")) 
				e1.printStackTrace();
		} finally{
			try {
				if(rsm!=null)rsm=null;
				if(rs!=null && !rs.isClosed()){rs.close();rs=null;}
				if(conn!=null && !conn.isClosed()){conn.close();conn=null;}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return result;
	}
	
	/**
	 * 	Get db for contents
	 * */
	@SuppressWarnings("rawtypes")
	public List getDBContents(String tableName,String columns){
		List result = new ArrayList();
		java.sql.Connection conn = null;
		ResultSet rs = null;
		ResultSetMetaData rsm = null;
		try {
			conn = ServiceLocator.baseMappingDao.getDataSource().getConnection();
			rs = conn.prepareStatement("SELECT "+columns+" FROM "+tableName).executeQuery();
			rsm = rs.getMetaData();
			while(rs.next()){
				for(int j = 1;j<=rsm.getColumnCount();j++){
					result.add(String.valueOf(rs.getObject(j)));
				}
				
			}
			String objName=new DataOhteServlet.DataFactory().apObName(tableName).replaceAll("\\d", "");
			ServiceLocator.mcc.delete(DaoCacheUtil.oCacheKey(Class.forName("com.pearl.o2o.pojo."+(objName)), 1));
		}catch(ClassNotFoundException e){
			System.err.println("Class Not Find:"+e.getMessage());
		} catch (Exception e1) { 
			e1.printStackTrace();
		}finally{
			try {
				if(rsm!=null)rsm=null;
				if(rs!=null && !rs.isClosed()){rs.close();rs=null;}
				if(conn!=null && !conn.isClosed()){conn.close();conn=null;}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return result;
	}
	
	/**
	 * 	Get db tables
	 * */
	public LinkedHashMap<String, String> getDBTables(){
		LinkedHashMap<String, String> result = new LinkedHashMap<String, String>();
		java.sql.Connection conn = null;
		ResultSet rs = null;
		ResultSetMetaData rsm = null;
		try {
			conn = ServiceLocator.baseMappingDao.getDataSource().getConnection();
			rs = conn.prepareStatement("SELECT TABLE_NAME FROM INFORMATION_SCHEMA.TABLES WHERE TABLE_SCHEMA=(SELECT DATABASE()) ORDER BY TABLE_NAME").executeQuery();
			int i = 1;
			while(rs.next()){
				//过滤活动 
				if(rs.getString(i).matches(".*achievement.*")||rs.getString(i).matches(".*ACHIEVEMENT.*"))
					continue;
				result.put(rs.getString(i), "String");
			}
		} catch (Exception e1) {
			e1.printStackTrace();
		} finally{
			try {
				if(rsm!=null)rsm=null;
				if(rs!=null && !rs.isClosed()){rs.close();rs=null;}
				if(conn!=null && !conn.isClosed()){conn.close();conn=null;}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return result;
	}
	/**
	 *  查看磁盘上上传下载的文件
	 *  
	 *  Format:file name@file date[upload|down]@file path
	 * */
	public static String lessUploadOrDownFileList(){
		ServiceLocator.fileLog.info("file path:"+PATH_FOLDER);
		File file = new File(PATH_FOLDER);
		File[] files = file.listFiles();
		JsonArray ja = new JsonArray();
		JsonObject jo = null;
		for(File f : files){
			if(f.isFile())
			{
				String name=f.getName();
				String nameTop=name.substring(0,name.lastIndexOf("_"))+(name.lastIndexOf("-Error")>-1?"-error":"");
				String timeStamp = name.substring(name.lastIndexOf("_")+1).replaceAll("[^\\d]", "").trim();
				if (StringUtil.isEmptyString(timeStamp)) {
					continue;
				}
				SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss yyyy/MM/dd");
				
				String key = nameTop+"@"+sdf.format(new Date(Long.valueOf(timeStamp)));
				jo=new JsonObject();
				if(f.getName().indexOf("DOWN")>-1){
					jo.addProperty("DOWN", key+"@"+name);
				}else{
					jo.addProperty("UPLOAD", key+"@"+name);
				}
			}
			ja.add(jo);
		}
		ServiceLocator.fileLog.info("show upload or down File list.."+files.length);
		return ja.toString();
	}
	/**
	 * 删除磁盘上的文件 
	 * */
	public static String delExlFile(String fName){
		File file = new File(PATH_FOLDER+File.separator+fName);
		JsonObject jo = new JsonObject(); 
		jo.addProperty("msg",file.delete());
		ServiceLocator.fileLog.info("delete file \""+fName+"\"..");
		return jo.toString();
	}
}
