package com.pearl.o2o.utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import jxl.Workbook;
import jxl.write.Label;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;

public class ActivityLogParser {
	final static int threshold =  8 * 1000;
	
	static Map<String,List<ActivityClass>> randomMap = new HashMap<String,List<ActivityClass>>();
	static Map<String,List<ActivityClass>> loginMap = new HashMap<String,List<ActivityClass>>();
	public static void main(String[] args) throws IOException{
		String path = ConfigurationUtil.LOTTERY_PATH;
		readLogFile(path);
		writeToExcel( path);
	}
	
	public static void readLogFile(String file) throws IOException{
		File dir = new File(file);
		for (File logFile : dir.listFiles()) {
			if (logFile.getName().startsWith("lottery")) {
				BufferedReader r = new BufferedReader(new FileReader(logFile));
				String line = r.readLine();
				while(line != null){
					if (line.indexOf("lottery") >=0) {//stat the finish time
						int startIndex = line.indexOf("lottery");
						int firstIndex = line.indexOf("-",startIndex);
						//- s_character_offline 334 
						String shortLine = line.substring(firstIndex+2);
//						System.out.println(shortLine);
						String[] segment = shortLine.split(" ");
						String idStr=segment[0];
						String id=idStr.substring(3);
						String name=segment[1].substring(5);
						String value=segment[4];
						String typeStr=segment[6];
						String timeStr=segment[8].substring(1)+" "+segment[9].substring(0, segment[9].length()-1);
						ActivityClass activityClass=new ActivityClass(id,name,typeStr,timeStr,value);
						if("random".equals(typeStr)){
							List<ActivityClass> list=new ArrayList<ActivityClass>();
							if(randomMap.get(name)==null){
								list.add(activityClass);
								randomMap.put(name, list);
							}else{
								list=randomMap.get(name);
								list.add(activityClass);
								randomMap.put(name, list);
								
							}
							
						}else if("daily".equals(typeStr)){
							List<ActivityClass> list=new ArrayList<ActivityClass>();
							if(loginMap.get(name)==null){
								list.add(activityClass);
								loginMap.put(name, list);
							}else{
								list=loginMap.get(name);
								list.add(activityClass);
								loginMap.put(name, list);
								
							}
						}
					}
					line = r.readLine();
				}
				r.close();
			}
		}
	}
	public static void writeAwardList(String path){
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String fileName = "award_list_" +  sdf.format(new Date()) + ".txt";
		if (!path.trim().endsWith("/")){
			path = path + "/";
		}
		File resultFile=new File(path+fileName);
		try {
			Writer w = new FileWriter(resultFile,false);
			w.write(" ==========随机抽奖========== \n");
			w.write(" id "+" name "+" type "+" value "+" time \n");
			for (Map.Entry<String, List<ActivityClass>> entry : randomMap.entrySet()) {
	 			for (ActivityClass ac : entry.getValue()){
	 				w.write(ac.getId()+" "+ac.getName()+" "+ac.getType()+" "+ac.getValue()+" "+ac.getTime()+" \n");
	 			}
			}
			w.write(" ==========固定时间登陆========== \n");
			w.write(" id "+" name "+" type "+" value "+" time \n");
			for (Map.Entry<String, List<ActivityClass>> entry : loginMap.entrySet()) {
	 			for (ActivityClass ac : entry.getValue()){
	 				w.write(ac.getId()+" "+ac.getName()+" "+ac.getType()+" "+ac.getValue()+" "+ac.getTime()+" \n");
	 			}
			}
			w.flush();
			w.close();
		} catch (IOException e) {
			e.printStackTrace(); 
		}
	}
	public static void writeToExcel(String path){
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String fileName = "award_list_" +  sdf.format(new Date()) + ".xls";
		if (!path.trim().endsWith("/")){
			path = path + "/";
		}
		
		WritableWorkbook wwb = null;
		 try {   
             wwb = Workbook.createWorkbook(new File(path + fileName));   
         } catch (IOException e) {   
            e.printStackTrace();   
         }
         if (wwb == null){
        	 return ;
         }
         WritableSheet ws = wwb.createSheet("随机抽奖", 0);
        
         //add title
         Label l1 = new Label(0, 0, "id");
		 Label l2 = new Label(1, 0, "name");
		 Label l3 = new Label(2, 0, "type");
		 Label l4 = new Label(3, 0, "value");
		 Label l5 = new Label(4, 0, "time");
		 try{
			 ws.addCell(l1);
			 ws.addCell(l2);
			 ws.addCell(l3);
			 ws.addCell(l4);
			 ws.addCell(l5);
		 }catch (Exception e){
			 e.printStackTrace();
		 }
		 
		 
         int ii=1;
         for (Map.Entry<String, List<ActivityClass>> entry : randomMap.entrySet()) {
 			for (ActivityClass ac : entry.getValue()){
 				 l1 = new Label(0, ii, ac.getId());
 	 			 l2 = new Label(1, ii, ac.getName());
 	 			 l3 = new Label(2, ii, ac.getType());
 	 			 l4 = new Label(3, ii, ac.getValue());
 	 			 l5 = new Label(4, ii, ac.getTime());
 	 			try {   
 	 	              ws.addCell(l1);
 	 	              ws.addCell(l2);
 	 	              ws.addCell(l3);
 	 	              ws.addCell(l4);
 	 	              ws.addCell(l5);
 	 	         } catch (Exception e) {   
 	 	             e.printStackTrace();   
 	 	         }
 	 	         ii ++;
 			}
 			
 		}
         
         ws = wwb.createSheet("固定时间登陆", 1);
       //add title
         l1 = new Label(0, 0, "id");
		 l2 = new Label(1, 0, "name");
		 l3 = new Label(2, 0, "type");
		 l4 = new Label(3, 0, "value");
		 l5 = new Label(4, 0, "time");
        
		 try{
			 ws.addCell(l1);
			 ws.addCell(l2);
			 ws.addCell(l3);
			 ws.addCell(l4);
			 ws.addCell(l5);
		 }catch (Exception e){
			 e.printStackTrace();
		 }
		 
		 
         ii=1;
         for (Map.Entry<String, List<ActivityClass>> entry : loginMap.entrySet()) {
 			for (ActivityClass ac : entry.getValue()){
 				 l1 = new Label(0, ii, ac.getId());
 	 			 l2 = new Label(1, ii, ac.getName());
 	 			 l3 = new Label(2, ii, ac.getType());
 	 			 l4 = new Label(3, ii, ac.getValue());
 	 			 l5 = new Label(4, ii, ac.getTime());
 	 			try {   
 	 	              ws.addCell(l1);
 	 	              ws.addCell(l2);
 	 	              ws.addCell(l3);
 	 	              ws.addCell(l4);
 	 	              ws.addCell(l5);
 	 	         } catch (Exception e) {   
 	 	             e.printStackTrace();   
 	 	         }
 	 	         ii ++;
 			}
 			
 		}
		try {
			wwb.write();
			wwb.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	public static class ActivityClass{
		private String id;
		private String name;
		private String type;
		private String time;
		private String value;
		public ActivityClass(String id,String name,String type,String time,String value) {
			this.id=id;
			this.name=name;
			this.type=type;
			this.time=time;
			this.value=value;
		}
		public String getId() {
			return id;
		}
		public void setId(String id) {
			this.id = id;
		}
		public String getName() {
			return name;
		}
		public void setName(String name) {
			this.name = name;
		}
		public String getType() {
			return type;
		}
		public void setType(String type) {
			this.type = type;
		}
		public String getTime() {
			return time;
		}
		public void setTime(String time) {
			this.time = time;
		}
		public String getValue() {
			return value;
		}
		public void setValue(String value) {
			this.value = value;
		}
		
	}
}
