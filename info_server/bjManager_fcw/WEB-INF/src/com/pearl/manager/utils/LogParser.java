package com.pearl.manager.utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import jxl.Workbook;
import jxl.write.Label;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;

public class LogParser {
	final static int threshold =  8 * 1000;
	
	public static void main(String[] args) throws IOException{
		String path = "D:\\info";
		readLogFile(path);
	}
	
	public static void readLogFile(String file) throws IOException {
		File dir = new File(file);
		
		
//		try {
//			wwb.write();
//			wwb.close();
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
		for (int k=0;k<dir.listFiles().length;k++ ) {
			
			File logFile =dir.listFiles()[k];
			
			if (logFile.getName().endsWith(".txt")) {
				System.out.println(logFile.getAbsoluteFile());
				WritableWorkbook wwb = null;
				try {
					wwb = Workbook.createWorkbook(new File("table"+k+ ".xls"));
				} catch (IOException e) {
					e.printStackTrace();
				}
				if (wwb == null){
		       	 return ;
		        }
				WritableSheet ws = wwb.createSheet(logFile.getName(), 0);
				BufferedReader r = new BufferedReader(new InputStreamReader(new FileInputStream(logFile),"UTF-8"));
				String line = r.readLine();
				int ii = 1;
				// add title
				Label l1 = new Label(0, 0, "id");
				Label l2 = new Label(1, 0, "name");
				Label l3 = new Label(2, 0, "type");
				Label l4 = new Label(3, 0, "value");
				Label l5 = new Label(4, 0, "time");
				Label l6 = new Label(5, 0, "time");
				Label l7 = new Label(6, 0, "time");
				try {
					ws.addCell(l1);
					ws.addCell(l2);
					ws.addCell(l3);
					ws.addCell(l4);
					ws.addCell(l5);
					ws.addCell(l6);
					ws.addCell(l7);
				} catch (Exception e) {
					e.printStackTrace();
				}
				while (line != null) {
//					System.out.println(line);
					String[] segment = line.split("\t");
					String idStr = segment[0];
					String userName = segment[1];
					String name = segment[2];
					String rank = segment[3];
					String lastLoginTime = segment[4];
					String lastLogoutTime = segment[5];
					String createTime = segment[6];
					l1 = new Label(0, ii, idStr);
					l2 = new Label(1, ii, userName);
					l3 = new Label(2, ii, name);
					l4 = new Label(3, ii, rank);
					l5 = new Label(4, ii, lastLoginTime);
					l6 = new Label(5, ii, lastLogoutTime);
					l7 = new Label(6, ii, createTime);
					try {
						ws.addCell(l1);
						ws.addCell(l2);
						ws.addCell(l3);
						ws.addCell(l4);
						ws.addCell(l5);
						ws.addCell(l6);
						ws.addCell(l7);
					} catch (Exception e) {
						e.printStackTrace();
					}
					ii++;
					line = r.readLine();
				}
				try {
					wwb.write();
					wwb.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			
		}
		
	}
}
