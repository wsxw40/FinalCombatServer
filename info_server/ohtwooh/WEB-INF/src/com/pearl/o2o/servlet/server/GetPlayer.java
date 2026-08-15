package com.pearl.o2o.servlet.server;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.pearl.o2o.pojo.Player;
import com.pearl.o2o.pojo.PlayerItem;
import com.pearl.o2o.utils.BinaryUtil;
import com.pearl.o2o.utils.Constants;
import com.pearl.o2o.utils.Receiver;

public class GetPlayer extends BaseServerServlet {
	private static final long serialVersionUID = -6638020940416102777L;
	static Logger log = Logger.getLogger(GetPlayer.class.getName());

	public GetPlayer(){
		super();
	}
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
		super.service(req, res);
		OutputStream out = res.getOutputStream();
		InputStream in = req.getInputStream();
		byte[] buffer = new byte[32];

		try{
			Receiver r = new Receiver(in, buffer);
			int playerId = r.readInt();
			byte team = r.readByte();
//			String cid		= req.getParameter("cid");
//			String team		= req.getParameter("team");
//			String[] ids 	= cid.split(",");
//			String[] teams  = null;
//			if(team!=null){
//				teams 	= team.split(",");
//			}

//			out.write(BinaryUtil.toByta(Constants.NUM_ONE));
				//Write Player Info
				Player player 	= getService.getPlayerById(playerId);
				List<PlayerItem> cosT = getService.getCostumePackList(playerId, 1, 0);
				List<PlayerItem> cosP = getService.getCostumePackList(playerId, 1, 1);
				player.putOnCostume(0, cosT);
				player.putOnCostume(1, cosP);

//				if(team!=null){
				player.writeByte(out, (int)team);
//				}
//				else{
//					player.writeByte(out, 0);
//				}

				//Write Player Pack Info

				List<List> packs = new ArrayList<List>();

				for (int i=1;i<=Constants.DEFAULT_WEAPON_PACK_SIZE;i++) {
					List pack = getService.getWeaponPackList(playerId, i);
					if (pack !=null && !pack.isEmpty()) {
						packs.add(pack);
					}
				}

				//TODO, change to real WPackSize later
				out.write(BinaryUtil.toByta(5));
				//out.write(BinaryUtil.toByta(packs.size()));
				for(int j=1; j<= 5; j++){
					List pack = getService.getWeaponPackList(playerId, j);
					if (pack != null && !pack.isEmpty()){
						out.write(BinaryUtil.toByta(Constants.SEQ_NUM));
						//Write Each Equipped Player Item
						for(int k=0; k<pack.size(); k++){
							PlayerItem pi = (PlayerItem)pack.get(k);

							if(pi != null){
								if(pi.getWId() == null){
									out.write(BinaryUtil.toByta(0));
								}
								else {
									if(pi.getParts()!=null&&pi.getParts().size()!=0){
	//									pi.putOnPart();
									}else{
										pi.init();
									}
									pi.writeByte(out);
								}
							}
						}
					}
				}
				//buffList
				List<PlayerItem> buffList=player.getBuffList();
				if(buffList!=null){
					out.write(BinaryUtil.toByta(buffList.size()));
					for(int i=0;i<buffList.size();i++){
						PlayerItem pi=buffList.get(i);
						out.write(BinaryUtil.toByta(pi.getIBuffId().byteValue()));
						out.write(BinaryUtil.toByta(pi.getIValue()));
					}
				}else{
					out.write(BinaryUtil.toByta(0));
				}

		}
		catch(Exception e){
			log.error("Error in GetPlayer: ", e);
			return;
		}
	}
	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		super.doGet(req, res);

	}
}

