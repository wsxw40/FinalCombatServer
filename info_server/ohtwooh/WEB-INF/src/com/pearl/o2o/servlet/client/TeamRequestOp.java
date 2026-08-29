
package com.pearl.o2o.servlet.client;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.pearl.o2o.pojo.PlayerTeam;
import com.pearl.o2o.utils.Constants;
import com.pearl.o2o.utils.Converter;
import com.pearl.o2o.utils.ExceptionMessage;
import com.pearl.o2o.utils.StringUtil;

/**
 * @author Timon
 */
public class TeamRequestOp extends BaseClientServlet {

	private static final long serialVersionUID = 7794098604530646005L;
	static Logger logger=Logger.getLogger(TeamRequestOp.class.getName());
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
		super.service(req, res);
		PrintWriter out = res.getWriter();
		try{
			int teamId = StringUtil.toInt(req.getParameter("tid"));
			//the target id of players
			String cidStr = req.getParameter("cids");
			//the id of who send this request
			String cid = req.getParameter("cid");
			//target cids
			String [] cids = null;
			if(!StringUtil.isEmptyString(cidStr)){
				cids = cidStr.split(",");
			}
			String action = req.getParameter("action");
			
			PlayerTeam pt = getService.getPlayerTeamByPlayerId(Integer.valueOf(cid));
			//keep the id that fail
			Map<String,List<String>> failList = new HashMap<String,List<String>>();
			
			if ("join".equals(action)) {
				updateService.applyTeam(teamId, Integer.valueOf(cid));
			}else if ("approve".equals(action)){
				if (pt == null || !(pt.getJob().equals(Constants.TEAMJOB.TEAM_CAPTAIN.getValue()) || pt.getJob().equals(Constants.TEAMJOB.TEAM_OFFICER.getValue()))) {
					throw new Exception(ExceptionMessage.TEAM_OP_FAIL);
				}
				failList = updateService.approve(teamId, cids);
			}else if ("reject".equals(action)) {
				if (pt == null || !(pt.getJob().equals(Constants.TEAMJOB.TEAM_CAPTAIN.getValue()) || pt.getJob().equals(Constants.TEAMJOB.TEAM_OFFICER.getValue()))) {
					throw new Exception(ExceptionMessage.TEAM_OP_FAIL);
				}
				updateService.removePlayerTeam(teamId, cids);
			}else if ("quit".equals(action)) {
				if (pt == null || !pt.getTeamId().equals(teamId) || pt.getJob().equals(Constants.TEAMJOB.TEAM_CAPTAIN.getValue())){
					throw new Exception(ExceptionMessage.TEAM_OP_FAIL);
				}
				updateService.quit(teamId, Integer.valueOf(cid));
			}else if ("kick".equals(action)){
				PlayerTeam targetPlayerTeam = getService.getPlayerTeamByPlayerId(Integer.valueOf(cids[0]));
				if (pt == null
						|| targetPlayerTeam == null
						|| !(pt.getJob().equals(Constants.TEAMJOB.TEAM_CAPTAIN.getValue()) || pt.getJob().equals(Constants.TEAMJOB.TEAM_OFFICER.getValue()) )
						|| cids[0] == cid
						|| targetPlayerTeam.getJob().equals(
								Constants.TEAMJOB.TEAM_CAPTAIN.getValue())) {
					throw new Exception(ExceptionMessage.TEAM_OP_FAIL);
				}
				updateService.quit(teamId, Integer.valueOf(cids[0]));
			}else if("handover".equals(action)){
				updateService.handoverLeader(Integer.valueOf(cid), Integer.valueOf(cids[0]));
			}else if ("appoint".equals(action)){
				PlayerTeam targetPlayerTeam = getService.getPlayerTeamByPlayerId(Integer.valueOf(cids[0]));
				int newJob = Integer.valueOf(req.getParameter("job"));
				updateService.updatePlayerJob(pt, targetPlayerTeam, newJob);
			}
			else {
				throw new Exception("unknow action:" + action);
			}
			
			//handle error for approve
			StringBuilder sb = new StringBuilder();
			
			boolean hasFail = false;
			for (Map.Entry<String, List<String>> entry : failList.entrySet()) {
				if (!entry.getValue().isEmpty()) {
					hasFail = true;
					break;
				}
			}
			
			if (!hasFail) {
				out.write("error = nil");
			}else {
				sb.append("以下玩家无法批准入队: ");
				for (Map.Entry<String, List<String>> entry : failList.entrySet()) {
					if (!entry.getValue().isEmpty()) {
						sb.append(entry.getKey() + "(");
						for (String name : entry.getValue()) {
							sb.append(name).append(",");
						}
						sb.append(") ");
					}
				}
				out.write("error=\"" + sb.toString() + "\"");
			}
			
		}catch (Exception e) {
			logger.error("team request op fail:" + e.getMessage(),e);
			if (StringUtil.isEmptyString(e.getMessage())){
				out.write(Converter.error("系统出现异常错误，请联系GM"));
			}else {
				out.write(Converter.error(e.getMessage()));
			}
		}
	}
}
