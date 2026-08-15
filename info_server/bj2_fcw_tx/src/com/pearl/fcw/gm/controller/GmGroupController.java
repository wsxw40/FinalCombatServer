package com.pearl.fcw.gm.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.pearl.fcw.core.controller.DmController;
import com.pearl.fcw.core.controller.UrlCode;
import com.pearl.fcw.gm.pojo.WGmGroup;
import com.pearl.fcw.gm.pojo.WGmGroupPrivilege;
import com.pearl.fcw.gm.service.WGmUserService;
import com.pearl.fcw.utils.Constants;
import com.pearl.fcw.utils.DataTablesParameter;
import com.pearl.o2o.pojo.GmUser;

/**
 * GM操作GM用户组
 */
@Controller
@RequestMapping("fcw/gm")
@UrlCode(18)
public class GmGroupController extends DmController {

    @Resource
    private WGmUserService wGmUserService;

    /**
     * 列表
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    @RequestMapping("gmGroups")
    public @ResponseBody Object getPage(HttpServletRequest request, HttpServletResponse response, DataTablesParameter param) throws Exception {
        return wGmUserService.findGroupPageMap(param);
    }

    /**
     * 获取全部权限
     * @param request
     * @param response
     * @return
     */
    @RequestMapping("gmPrivileges")
    public @ResponseBody Object getPrivileges(HttpServletRequest request, HttpServletResponse response) throws Exception {
        return wGmUserService.findPrivilegeList(null);
    }

    /**
     * 获取指定用户组的权限
     * @param request
     * @param response
     * @param groupId
     * @return
     * @throws Exception
     */
    @RequestMapping("gmGroupPrivileges")
    public @ResponseBody Object getGroupPrivileges(HttpServletRequest request, HttpServletResponse response, @RequestParam("groupId") Integer groupId) throws Exception {
        WGmGroupPrivilege wGmGroupPrivilege = new WGmGroupPrivilege();
        wGmGroupPrivilege.setGroupId(groupId);
        return wGmUserService.findGroupPrivilegeList(wGmGroupPrivilege);
    }

    /**
     * 保存
     * @param request
     * @param response
     * @param wGmGroup
     * @throws Exception
     */
    @RequestMapping("saveGmGroup")
    public void save(HttpServletRequest request, HttpServletResponse response, WGmGroup wGmGroup, @RequestParam("u") Integer u) throws Exception {
        GmUser gmUser = (GmUser) request.getSession().getAttribute(Constants.GM_SESSION_KEY);
        String[] privileges = request.getParameterValues("privileges");
        List<Integer> privilegeIds = null == privileges ? new ArrayList<>() : Stream.of(privileges).map(Integer::parseInt).collect(Collectors.toList());
        if (null == u) {//新增
            wGmUserService.addGroupByGm(wGmGroup, privilegeIds, gmUser);
        } else {//修改
            wGmUserService.updateGroupByGm(wGmGroup, privilegeIds, gmUser);
        }
        response.sendRedirect(request.getContextPath() + "/fcw/#/gmGroups");
    }

    /**
     * 物理删除
     * @param request
     * @param response
     * @param wGmGroup
     * @throws Exception
     */
    @RequestMapping("deleteGmGroup")
    public void delete(HttpServletRequest request, HttpServletResponse response, WGmGroup wGmGroup) throws Exception {
        GmUser gmUser = (GmUser) request.getSession().getAttribute(Constants.GM_SESSION_KEY);
        wGmUserService.deleteGroupByGm(wGmGroup, gmUser);
        response.sendRedirect(request.getContextPath() + "/fcw/#/gmGroups");
    }
}
