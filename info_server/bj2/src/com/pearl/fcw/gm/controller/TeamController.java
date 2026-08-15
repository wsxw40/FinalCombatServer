package com.pearl.fcw.gm.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.pearl.fcw.core.controller.DmController;
import com.pearl.fcw.core.controller.UrlCode;
import com.pearl.fcw.lobby.service.WTeamService;
import com.pearl.fcw.utils.Constants;
import com.pearl.fcw.utils.DataTablesParameter;
import com.pearl.o2o.pojo.GmUser;
import com.pearl.o2o.pojo.Team;

/**
 * GM操作战队
 */
@Controller
@RequestMapping(value = "fcw/gm")
@UrlCode(15)
public class TeamController extends DmController {
    @Resource
	private WTeamService wTeamService;

    /**
	 * 战队列表
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception1
	 */
	@RequestMapping(value = "teams")
    public @ResponseBody Object getPageMap(HttpServletRequest request, HttpServletResponse response, DataTablesParameter param) throws Exception {
		return wTeamService.findPage(param);
    }

    /**
	 * 保存战队数据
	 * @param request
	 * @param response
	 * @param player
	 * @param playerInfo
	 * @throws Exception
	 */
	@RequestMapping(value = "saveTeam")
	public void save(HttpServletRequest request, HttpServletResponse response, Team team) throws Exception {
        GmUser gmUser = (GmUser) request.getSession().getAttribute(Constants.GM_SESSION_KEY);
		wTeamService.updateByGm(team, gmUser);
		response.sendRedirect(request.getContextPath() + "/fcw/#/teams");
    }
}
