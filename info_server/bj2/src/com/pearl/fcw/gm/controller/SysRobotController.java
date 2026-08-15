package com.pearl.fcw.gm.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.pearl.fcw.core.controller.UrlCode;
import com.pearl.fcw.gm.service.WSysRobotService;

/**
 * 系统机器人
 */
@Controller
@RequestMapping(value = "fcw/gm")
@UrlCode(27)
public class SysRobotController {
	@Resource
	private WSysRobotService wSysRobotService;

	/**
	 * 生成指定数量不重复的机器人
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "generateRobots")
	public @ResponseBody Object generateRobots(HttpServletRequest request, HttpServletResponse response) throws Exception {
		int count = Integer.parseInt(request.getParameter("count"));
		int re = 0;//是否全部重新生成机器人
		try {
			re = Integer.parseInt(request.getParameter("re"));
		} catch (Exception e) {
		}
		if (re > 0) {
			if (count >= 100000) {
				return "Amount of Robot can not be more than 100000 . ";
			}
			wSysRobotService.truncate();
		} else {
			if (count + wSysRobotService.findList(null).size() >= 100000) {
				return "Amount of Robot can not be more than 100000 . ";
			}
		}
		wSysRobotService.addRobots(count, 4, 14);
		count = wSysRobotService.findList(null).size();
		return "Robots has generated successfully . There is " + count + " robot .";
	}
}
