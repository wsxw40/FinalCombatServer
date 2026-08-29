/**
 * 
 */
package com.pearl.o2o.servlet.client;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.pearl.o2o.exception.BaseException;
import com.pearl.o2o.utils.CommonUtil;
import com.pearl.o2o.utils.Converter;
import com.pearl.o2o.utils.ExceptionMessage;
import com.pearl.o2o.utils.KeywordFilterUtil;
import com.pearl.o2o.utils.StringUtil;

/**
 * @author bobby
 */
public class CreateTeam extends BaseClientServlet {

	private static final long serialVersionUID = -3352098501534070554L;
	static Logger log=Logger.getLogger(CreateTeam.class.getName());
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
		super.service(req, res);
		PrintWriter out = res.getWriter();	
		try{
			int userId = StringUtil.toInt(req.getParameter("uid"));
			int playerId = StringUtil.toInt(req.getParameter("cid"));
			int type = StringUtil.toInt(req.getParameter("type"));
			int playerItemId = StringUtil.toInt(req.getParameter("pid"));
			//String name = StringUtil.encoding(req.getParameter("name"));
			String name = req.getParameter("name");
			String declaration = req.getParameter("declaration");
			String description = req.getParameter("description");
			String board = req.getParameter("board");
			String logo = req.getParameter("logo");
			boolean isError=false;
			String nameStr="";
			String declarationStr="";
			String descriptionStr="";
			String boardStr="";
			
			if (StringUtil.isEmptyString(name)) {
				isError=true;
				nameStr += ExceptionMessage.EMPTY_STR+",";
			}
			
			if(!KeywordFilterUtil.isLegalInput(name)){
				isError=true;
				nameStr+=ExceptionMessage.ILLEGAL_CHARACTER+",";
				
			}
			int count=getService.fuzzyCountTeam(name);
			if(count>0){
				isError=true;
				nameStr+=ExceptionMessage.TEAM_EXIST+",";
			}
			if (name != null && name.getBytes("GBK").length > 10) {
				isError=true;
				nameStr+=ExceptionMessage.TOO_LONG+",";
			}
			if(!KeywordFilterUtil.isLegalInput(declaration)){
				isError=true;
				declarationStr+=ExceptionMessage.ILLEGAL_CHARACTER+",";
			}
			if (declaration != null && declaration.getBytes("GBK").length > 20*3) {
				isError=true;
				declarationStr+=ExceptionMessage.TOO_LONG+",";
			}
			if(!KeywordFilterUtil.isLegalInput(description)){
				isError=true;
				descriptionStr+=ExceptionMessage.ILLEGAL_CHARACTER+",";
			}
			if (description != null && description.getBytes("GBK").length > 50*3) {
				isError=true;
				descriptionStr+=ExceptionMessage.TOO_LONG+",";
			}
			if(!KeywordFilterUtil.isLegalInput(board)){
				isError=true;
				boardStr+=ExceptionMessage.ILLEGAL_CHARACTER+",";
			}
			if (board != null && board.getBytes("GBK").length > 50*3) {
				isError=true;
				boardStr+=ExceptionMessage.TOO_LONG+",";
			}
			
			if(!isError){
				createService.createTeamByItem(userId, playerId, type, playerItemId, name, declaration, description, board, logo);
			}
			out.write(Converter.creatTeam(CommonUtil.cutLastWord(nameStr), 
					CommonUtil.cutLastWord(declarationStr), CommonUtil.cutLastWord(descriptionStr), CommonUtil.cutLastWord(boardStr)));
		}
		catch (BaseException e) {
			e.printStackTrace();
			log.error(e.getMessage(),e);
			out.write(Converter.commonFeedback(e.getMessage()));
		}
		catch (Exception e) {
			e.printStackTrace();
			log.error(e.getMessage(),e);
			out.write(Converter.commonFeedback("Error happened in CreateTeam. Please contact system administrator"));
		}
	}
}
