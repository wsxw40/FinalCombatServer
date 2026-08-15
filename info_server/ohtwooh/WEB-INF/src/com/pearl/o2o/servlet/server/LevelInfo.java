package com.pearl.o2o.servlet.server;

import java.io.FilterOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.Unmarshaller;

import org.apache.log4j.Logger;

import com.pearl.o2o.pojo.Hero;
import com.pearl.o2o.pojo.LevelModeInfo;
import com.pearl.o2o.pojo.LevelWeapon;
import com.pearl.o2o.pojo.PlayerItem;
import com.pearl.o2o.pojo.SysItem;
import com.pearl.o2o.pojo.SysModeConfig;
import com.pearl.o2o.pojo.LevelModeInfo.Supply;
import com.pearl.o2o.pojo.LevelModeInfo.TeamPoints;
import com.pearl.o2o.pojo.LevelModeInfo.Vector3;
import com.pearl.o2o.pojo.LevelModeInfo.WeaponPoint;
import com.pearl.o2o.pojo.jaxb.HeroWeapon;
import com.pearl.o2o.pojo.LevelWeapon;
import com.pearl.o2o.pojo.PlayerItem;
import com.pearl.o2o.utils.BinaryOut;
import com.pearl.o2o.utils.BinaryUtil;
import com.pearl.o2o.utils.Constants;
import com.pearl.o2o.utils.WeaponUtil;

public class LevelInfo extends BaseServerServlet {

	private static final long serialVersionUID = 8449242457860139805L;
	private static Logger logger = Logger.getLogger(LevelInfo.class);
	
	
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException{
		super.service(req, res);
		
		try{
			int lid = Integer.valueOf(req.getParameter("lid"));
			
			LevelModeInfo levelAndMode = getService.getLevelModeInfo(lid);
			BinaryOut bOut = new  BinaryOut(new BufferedOutputStream(res.getOutputStream()));
			
			bOut.writeInt(levelAndMode.getId());
			bOut.writeByte(levelAndMode.getType());
			bOut.writeString(levelAndMode.getName());
			//startPoints
			bOut.writeInt(levelAndMode.getStartPoints().size());
			for(TeamPoints tp : levelAndMode.getStartPoints()){
				writeTeamPoints(tp,bOut);
			}
			//blast points
			bOut.writeInt(levelAndMode.getBlastPoints().size());
			for (Vector3 v : levelAndMode.getBlastPoints()) {
				writeVector(v,bOut);
			}
			//banner point
			bOut.writeInt(levelAndMode.getBannerPoint().size());
			for(TeamPoints tp : levelAndMode.getBannerPoint()){
				writeTeamPoints(tp,bOut);
			}
			//lazy load
			List<LevelWeapon> weapons = new ArrayList<LevelWeapon>();
			if (levelAndMode.hasRelatedWeapon()){
				weapons = getService.getLevelWeaponsByLevelId(lid);
			}
			
			//weapon point
			bOut.writeInt(levelAndMode.getWeaponPoints().size());
			for (WeaponPoint v : levelAndMode.getWeaponPoints()) {
				writeWeaponPoints(v, bOut, weapons);
			}
			//supply points
			bOut.writeInt(levelAndMode.getSupplyPoints().size());
			for (Vector3 v : levelAndMode.getSupplyPoints()) {
				writeVector(v,bOut);
			}
			//weapons
			bOut.writeInt(weapons.size());
			for (LevelWeapon lw : weapons) {
				lw.writeByte(bOut.getOutputStream());
			}
			//supplies
			bOut.writeInt(levelAndMode.getSupplies().size());
			for (Supply v : levelAndMode.getSupplies()) {
				writeSupply(v,bOut,weapons);
			}
//			boolean is=false;
//			if(is){
//			         JAXBContext context;  
//			         context = JAXBContext.newInstance(ObjectFactory.class);  
//			         Unmarshaller marshaller = context.createUnmarshaller();
//			         //读取配置
//			         SysModeConfig config=getService.getSysModeConfig(3);
//			         String   str   = config.getConfig(); 
//			         InputStream   inputStream   =   new   ByteArrayInputStream(str.getBytes());
//			         RootInfo root = (RootInfo) marshaller.unmarshal(inputStream);
//			         com.pearl.o2o.pojo.jaxb.Hero hero=new com.pearl.o2o.pojo.jaxb.Hero();
//			         hero=root.getHero();
//			         hero.writeByte(bOut.getOutputStream());
//			         //生化人
//			         List<Zombie> zombieList=root.getZombie();
//			         if(zombieList!=null&&zombieList.size()>0){
//			        	 bOut.writeInt(zombieList.size());
//			        	 for(Zombie z:zombieList){
//			        		 z.writeByte(bOut.getOutputStream());
//			        	 }
//			         }else{
//			        	 bOut.writeInt(0); 
//			         }
//			}
			if(levelAndMode.getType()==Constants.GAMETYPE.BIO.getValue()){
				Hero hero=new Hero();
				String[] resoures ={"gangster04"};
				hero.setResoures(resoures);
				SysItem mainSysItem=getService.getSysItemByItemId(12);
				SysItem subSysItem=getService.getSysItemByItemId(106);
//				PlayerItem mainWeapon=getService.getPlayerItemByItemId(2, 360, 1, 4945);
//				PlayerItem subWeapon=getService.getPlayerItemByItemId(2, 360, 1, 4944);//TODO for test data 
				mainSysItem.setWDamage(80);
				mainSysItem.setWFireTime(0.17f);
				mainSysItem.setWAmmoOneClip(20);
				mainSysItem.setWReloadTime(3f);
				mainSysItem.setWCrossLengthBase(5f);
				mainSysItem.setWCrossLengthFactor(50f);
				mainSysItem.setWCrossDistanceBase(10f);
				mainSysItem.setWCrossDistanceFactor(100f);
				mainSysItem.setWAmmoCount(200);
				
				hero.setMainWeapon(mainSysItem);
				hero.setSubWeapon(subSysItem);
				hero.writeByte(bOut.getOutputStream());
				//生化人
				bOut.writeInt(0);
			}else{//其他模式
				bOut.writeInt(0);
				bOut.writeInt(0);
			}
			
			bOut.flush();
		}catch (Exception e){
			logger.error("error happend when load level info.",e);
			res.sendError(500);
		}
	}
	
	private void writeVector(LevelModeInfo.Vector3 vector, BinaryOut out) throws IOException{
		out.writeFloat(vector.getX());
		out.writeFloat(vector.getY());
		out.writeFloat(vector.getZ());
	}
	
	private void writeTransform(LevelModeInfo.Transform transform, BinaryOut out ) throws IOException{
		writeVector(transform.getPosition(), out);
		out.writeFloat(transform.getRotate());
	}
	
	private void writeWeaponPoints(LevelModeInfo.WeaponPoint weaponPoint, BinaryOut out, List<LevelWeapon> weapons) throws Exception{
		//write the index  in weapon list instead of id 
		int weaponIndex = getIndexFromWeaponList(weapons,weaponPoint.getId());
		if (weaponIndex <0) {
			throw new Exception("weapon:" + weaponPoint.getId() + "can not be found in weaponlist");
		}
		out.writeByte(weaponIndex);
		writeTransform(weaponPoint.getTransform(),out);
	}
	
	private void writeTeamPoints(LevelModeInfo.TeamPoints tp, BinaryOut out) throws IOException{
		out.writeByte(tp.getTeamId());
		writeTransform(tp.getTransform(),out);
	}
	
	private void writeSupply(LevelModeInfo.Supply supply, BinaryOut out, List<LevelWeapon> weapons) throws Exception{
		out.writeByte(supply.getTeam());
		out.writeByte(supply.getType());
		out.writeString(supply.getName());
		//if type is weapon, value is the index in the weapon-list
		out.writeFloat(supply.getAppearRation());
		if (supply.getType() == 1){//TODO - weapon
			int weaponIndex = getIndexFromWeaponList(weapons,(int) supply.getValue());
			if (weaponIndex <0) {
				throw new Exception("weapon:" + (int) supply.getValue() + "can not be found in weaponlist");
			}
			out.writeByte(weaponIndex);
		}else{
			out.writeFloat(supply.getValue());
		}
		if (supply.getType() >= 20&&supply.getType() < 30) {
			out.writeFloat(supply.getSkillTime());
		}
	}
	
	private static int getIndexFromWeaponList(List<LevelWeapon> weapons, int id){
		for (int i=0;i<weapons.size();i++) {
			if (weapons.get(i).getId() == id) {
				return i;
			}
		}
		return -1;
	}
	
	
	class BufferedOutputStream  extends FilterOutputStream{
		
		public BufferedOutputStream(OutputStream out) {
			super(out);
		}

		private List<Byte> bytes = new ArrayList<Byte>();
		
		
		@Override
		public void write(int b) throws IOException {
			bytes.add((byte)b);
		}
		
		@Override
		public void flush() throws IOException {
			for(Byte b : bytes){
				super.write(b);
			}
			super.flush();
		}
		
	}
	
}
