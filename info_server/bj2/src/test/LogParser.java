package test;

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

public class LogParser {
	final static int threshold =  8 * 1000;
	
	public static Map<String,List<Integer>> readLogFile(String file) throws IOException{
		Map<String,List<Integer>> result = new HashMap<String,List<Integer>>();
		File dir = new File(file);
		for (File logFile : dir.listFiles()) {
			if (logFile.getName().startsWith("o2o_client_req")) {
				System.out.println("parse log " + logFile.getName());
			
				BufferedReader r = new BufferedReader(new FileReader(logFile));
				String line = r.readLine();
				while(line != null){
					if (line.indexOf("performance") >=0) {//stat the finish time
						int startIndex = line.indexOf("performance");
						int firstIndex = line.indexOf("-",startIndex);
						int msIndex = line.indexOf("ms"); 
						//- s_character_offline 334 
						String shortLine = line.substring(firstIndex,msIndex);
						String[] segment = shortLine.split(" ");
						
						String interfaceName = segment[1];
						if (interfaceName.startsWith("l_")||interfaceName.startsWith("s_") || interfaceName.startsWith("c_")) {
							try{
								
								int ms = Integer.valueOf(segment[2]);
								if (ms < threshold) {
								
									List<Integer> list = result.get(interfaceName);
									if (list == null) {
										list = new ArrayList<Integer>();
										result.put(interfaceName, list);
									}
									list.add(ms);	
								}
							}catch(Exception e){
								e.printStackTrace();
							}
						}
					}
					line = r.readLine();
				}
				r.close();
			}
		}
		
		return result;
	}
	
	public static void writeToExcel(Map<String,List<Integer>> data,String path){
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String fileName = "Performance_Diagnosis_Log_" +  sdf.format(new Date()) + ".xls";
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
         WritableSheet ws = wwb.createSheet("sheet1", 0);
         
         //add title
         Label l1 = new Label(0, 0, "interface");
		 Label l2 = new Label(1, 0, "average(ms)");
		 Label l3 = new Label(2, 0, "max(ms)");
		 Label l4 = new Label(3, 0, "invoke times");
		 Label l5 = new Label(4, 0, "min(ms)");
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
         for (Map.Entry<String, List<Integer>> entry : data.entrySet()) {
 			int sum = 0;
 			int max = 0;
 			int min = -1;
 			for (int i : entry.getValue()){
 				if (i>max) {
 					max = i;
 				}
 				if (min == -1) {//init min
 					min = i;
 				}
 				if (i<min) {
 					min = i;
 				}
 				sum += i;
 			}
 			
 			 l1 = new Label(0, ii, entry.getKey());
 			 l2 = new Label(1, ii, String.valueOf(sum/entry.getValue().size()));
 			 l3 = new Label(2, ii, String.valueOf(max));
 			 l4 = new Label(3, ii, String.valueOf(entry.getValue().size()));
 			 l5 = new Label(4, ii, String.valueOf(min));
 			 
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
		try {
			wwb.write();
			wwb.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void print(Map<String,List<Integer>> data) throws IOException{
		File resultFile = new File("result");
		Writer w = new FileWriter(resultFile,false);
		
		System.out.println("Interface           averageCost               maxCost");
		
		for (Map.Entry<String, List<Integer>> entry : data.entrySet()) {
			int sum = 0;
			int max = 0;
			for (int i : entry.getValue()){
				if (i>max) {
					max = i;
				}
				sum += i;
			}
			String str = entry.getKey() + " " + sum/entry.getValue().size() + "  " + max + "  "  + entry.getValue().size();
			System.out.println(str);
			w.write(str+"\n");
		}
		w.close();
	}
	
	public static void main(String[] args) throws IOException{
		//print(readLogFile("/home/wengjie/apache-tomcat-6.0.24/logs/"));
	//	String path = args[0];
		//String isPrint = args[1];
		String path = "D:/temp/";
		//print(readLogFile(path));
		writeToExcel(readLogFile(path),path);
		/*Map<String,List<Integer>> data = readLogFile(path);
		if (isPrint != null) {
			writeToExcel(readLogFile(path),path);
			print(data);
		}else{
			writeToExcel(data,path);
		}*/
	}
}
